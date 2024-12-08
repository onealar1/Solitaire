import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

import javafx.scene.image.Image;

public class Deck {
    private Stack<Card> cards;
    String[] suits = {"hearts", "diamonds", "spades", "clubs"};
    String[] ranks = {"ace", "king", "queen", "jack", "10", "9", "8", "7", "6", "5", "4", "3", "2"};

    public Deck() {
        cards = new Stack<>();
    }

    public void addCardsToDeck() {
        for (int i = 0; i < suits.length; i ++) {
            for (int j = 0; j < ranks.length; j++) {
                String filename = suits[i] + "/" + ranks[j];
                String filepath = "/images/" + filename + ".png";
                Image cardImage = new Image(filepath);
                Card card = new Card(suits[i], ranks[j], cardImage);
                card.setFaceUp(false);
                cards.add(card);
            }
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(cards);
    }

    public Stack<Card> getDeck() {
        return cards;
    }

    public Card peekTopCard(){
        return cards.peek();
    }

    public Card popTopCard(){
        return cards.pop();
    }

    public int getSize(){
        return cards.size();
    }
}
