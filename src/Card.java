import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.paint.ImagePattern;

public class Card extends Button{

    private static final int CARD_WIDTH = 75;
    private static final int CARD_HEIGHT = 120;
    private String suit; // 0 for heart, 1 for diamond, 2 for spade, 3 for club
    private String rank; // ex) Ace, King, Queen, 1, 2, 3
    private boolean faceUp; // if false the back of the card should be showing
    private String color; // 0 for red, 1 for black
    private Image backImage = new Image("/images/back.png");
    private Image image;
    private ImageView imageView;
    private String[] suits = {"hearts", "diamonds", "spades", "clubs"};
    private String[] ranks = {"king", "queen", "jack", "10", "9", "8", "7", "6", "5", "4", "3", "2", "ace"};

    public Card(String suit, String rank, Image image){
        this.suit = suit;
        this.rank = rank;
        this.image = image;
        setUpCards();
    }

    public void setUpCards(){
        imageView = new ImageView(image);
        imageView.setFitHeight(CARD_HEIGHT);
        imageView.setFitWidth(CARD_WIDTH);
        imageView.setPreserveRatio(true);
        setGraphic(imageView);
        setPrefWidth(CARD_WIDTH);
        setPrefHeight(CARD_HEIGHT);
        setBackground(Background.EMPTY);
        setBorder(Border.EMPTY);
    }

    public void setColor(String suit) {
        if (suit == "heart" || suit == "diamond") { // heart/diamond
            this.color = "red"; // red
        }
        else { // spade/club
            this.color = "black"; // black
        }
    }

    public void flipCard() {
        if (faceUp) {
            faceUp = false;
            this.setTextFill(new ImagePattern(backImage));
        }
        else {
            faceUp = true;
            this.setTextFill(new ImagePattern(image));
        }
    }

    public boolean isFaceUp() {
        return faceUp;
    }

    public String getRank() {
        return this.rank;
    }

    public String getSuit() {
        return this.suit;
    }

    public String getColor() {
        return this.color;
    }

    public static int getCardWidth(){
        return CARD_WIDTH;
    }

    public static int getCardHeight(){
        return CARD_HEIGHT;
    }

    public void setFaceUp(boolean faceUp){
        this.faceUp = faceUp;
    }

    public String[] getSuitsArr(){
        return suits;
    }
    
    public String[] getRanksArr(){
        return ranks;
    }
}
