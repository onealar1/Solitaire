import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

public class SolitareController {
    private Deck deck;
    private SolitareView view;
    private Card selectedCard = null;
    private Label label;
    Timeline timeline = new Timeline();

    public SolitareController(SolitareView view) {
        this.view = view;
        this.deck = new Deck();
        setUpEventHandlers();
    }

    public void setUpEventHandlers(){

        // for (Card card : view.getDeck()){
        //     handleCardClick(selectedCard);
        // }

        for(Pile pile : view.getAllPiles()){
            handlePileClick(pile);
        }
    }

    public void handlePileClick(Pile pile){
        pile.setOnMouseClicked(e -> pileClicked(pile));
    }

    public void handleCardClick(Card card){
        card.setOnAction(e -> cardClicked(card));
    }

    public void flashInvalidMove(Card card){
        String originalStyle = card.getStyle();

        KeyFrame flashRed = new KeyFrame(Duration.seconds(0.2),
            new KeyValue(card.styleProperty(), "-fx-background-color: red;"));

        KeyFrame flashOriginal = new KeyFrame(Duration.seconds(0.4),
            new KeyValue(card.styleProperty(), originalStyle));

        timeline.getKeyFrames().addAll(flashRed, flashOriginal);

        timeline.setCycleCount(6);
        timeline.setAutoReverse(false);

        timeline.setOnFinished(e -> card.setStyle(originalStyle));

        timeline.play();
    }

    public void pileClicked(Pile pile){
        if(selectedCard != null){
            if(pile.canAcceptCard(selectedCard)){
                pile.addCard(selectedCard);
                view.updatePileDisplay(pile);
                selectedCard = null;
            }
            else{
                flashInvalidMove(selectedCard);
                label = new Label("Invalid Move");
            }
        }
    }

    public void cardClicked(Card card){
        if(card == selectedCard){
            selectedCard = null;
            card.setStyle("");
        }
        else{
            if(selectedCard != null){
                selectedCard.setStyle("");
            }
            selectedCard = card;
            card.setStyle("-fx-border-color: yellow; -fx-border-width: 3;");
        }
    }

    public void startGame() {

    }
}
