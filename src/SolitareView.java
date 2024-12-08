import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class SolitareView extends BorderPane{

    GridPane startingGrid = new GridPane();
    GridPane suitPiles = new GridPane();
    GridPane wastePile = new GridPane();
    int sceneWidth = 1000;
    int sceneHeight = 600;
    Stage stage;
    Deck deck;  
    ArrayList<Button> buttons = new ArrayList<>();
    Map<Integer, TableauPile> tabPiles;
    Map<Integer, FoundationPile> foundationPiles;

    public SolitareView(Stage stage) {
        this.stage = stage;
        this.deck = new Deck();
        this.deck.shuffleDeck();
        tabPiles = new HashMap<>();
        foundationPiles = new HashMap<>();
        displaySetup();
    }

    public void displaySetup() {
        Scene scene = new Scene(this, sceneWidth, sceneHeight);
        this.setStyle("-fx-background-color: green;");
        startingGrid.setHgap(5);
        startingGrid.setVgap(-10);
        //startingGrid.setPrefSize(400, 800);
        startingGrid.setStyle("-fx-padding: 0;");

        suitPiles.setHgap(10);
        suitPiles.setStyle("-fx-padding: 20");

        wastePile.setHgap(10);
        wastePile.setStyle("-fx-padding: 10;");

        setTop(suitPiles);
        setRight(wastePile);
        setCenter(startingGrid);

        stage.setScene(scene);
        stage.setResizable(false);
        displayCards();
    }

    
    public void displayCards(){
        deck = new Deck();
        deck.addCardsToDeck();
        deck.shuffleDeck();

        for(int i = 0; i < 7; i++){
            TableauPile tabPile = new TableauPile();
            tabPiles.put(i, tabPile);
        }

        for(int i = 0; i < 4; i++){
            FoundationPile foundationPile = new FoundationPile();
            foundationPiles.put(i, foundationPile);

        }

        for(int x = 0; x < 7; x++){
            for(int y = 0; y <= x; y++ ){
                Card card = deck.popTopCard();
                tabPiles.get(x).addCard(card);
                card.setFaceUp(y == x);
                //Button cardButton = buttonMaker(card);
                card.setDisable(y < x);
                startingGrid.add(card, (x + 2), y);
            }
        }

        for (int x = 0; x < 7; x++) {
            RowConstraints rowControl = new RowConstraints();
            rowControl.setPrefHeight(40);  
            startingGrid.getRowConstraints().add(rowControl);
        }

        for (int y = 0; y < 7; y++) {
            ColumnConstraints columnControl = new ColumnConstraints();
            columnControl.setMinWidth(100);
            columnControl.setMaxWidth(100);
            columnControl.setPrefWidth(100); 
            startingGrid.getColumnConstraints().add(columnControl);
        }

        Button deckButton = new Button(" deck ");
        deckButton.setPrefSize(80, 120);
        deckButton.setStyle("-fx-border-color: black; " +
            "-fx-border-width: 2; " +
            "-fx-background-color: purple; " +
            "-fx-padding: 10;");
        deckButton.setText(" back ");

        suitPiles.add(deckButton, 40, 0);

        Button wasteButton = new Button("Waste");
        wasteButton.setPrefSize(80,120);
        wasteButton.setStyle("-fx-background-color: lightgray; -fx-border-color: black;");
        suitPiles.add(wasteButton, 38, 0);

        String[] suits = {"Hearts","Dimaons","Spades","Clubs"};
        for(int x = 0; x < 4; x++){
            Button suiteButton = new Button(suits[x]);
            suiteButton.setPrefSize(80, 120);
            suiteButton.setStyle("-fx-background-color: lightgrey; -fx-border-color: black;");
            suitPiles.add(suiteButton, x, 0);
        }

    }

    // public Card getAllCards(){
    //     // for(Card card : deck){

    //     // }
    // }

    public void updatePileDisplay(Pile pile){
        // add stuff to update
    }

    public List<Pile> getAllPiles(){
        List<Pile> allPiles = new ArrayList<>();

        allPiles.addAll(foundationPiles.values());
        allPiles.addAll(tabPiles.values());

        return allPiles;
    }

    public Button buttonMaker(Card card){
        Button button = new Button();
        if (card.isFaceUp()) {
            String cardColor = "white";
            if(card.getSuit().equals("Heart") || card.getSuit().equals("Diamond")){
                cardColor = "red";
            }
            else{
                cardColor = "black";
            }
            button.setPrefSize(80, 120);
            button.setText(card.getRank() + " of "+ card.getSuit());
            //System.out.println(card.getRank() + " of "+ card.getSuit()); // debugline
            button.setStyle(
            "-fx-border-color: black; " +
            "-fx-border-width: 2; " +
            "-fx-background-color:" + cardColor + "; " +
            "-fx-text-fill: white; " +
            "-fx-font-size: 8; " +
            "-fx-padding: 10;");
           
        }
        else{
            button.setText(" back ");
            button.setPrefSize(80, 2);
            button.setStyle("-fx-border-color: black; " +
            "-fx-background-color: purple; " +
            "-fx-font-size: 6;" +
            "-fx-padding: 0;"
        );

        }
        //button.setPrefSize(80,120);
        button.setOnAction(e -> card.flipCard());
        return button;
    }

    public void show(){
        stage.show();
        requestFocus();
    }
}
