import java.util.Stack;

import javafx.scene.layout.StackPane;

public abstract class Pile extends StackPane{
    
    private Stack<Card> pile;
    private boolean isEmpty = false;
    private Deck deck = new Deck();

    public Pile(){
        pile = new Stack<>();
    }

    public void addCard(Card card){
        pile.add(card);
    }

    public Stack<Card> getCards(){
        return pile;
    }

    public void removeCard(Card card){
        pile.remove(card);
    }

    public void isEmpty(){
        if(pile.size() == 0){
            isEmpty = true;
        }
        else{
            isEmpty = false;
        }
    }
    public abstract boolean canAcceptCard(Card card);

    //public abstract void restartPile(); - i think this is for the waste pile, but that should be part of deck
    public abstract Card topCard();
}
