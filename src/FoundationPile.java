import java.util.Arrays;
import java.util.Stack;

public class FoundationPile extends Pile{
    
    /* This class is for the 4 piles at the top left of the screen
     * - the stack should be from least to greatest starting at Ace. Once all of the piles are full, the game is over
     */


    private Stack<Card> foundationPile;
    private boolean pileFull = false;
    
    public boolean pileFull(){
        if(foundationPile.size() == 13){
            pileFull = true;
        }
        else {
            pileFull = false;
        }
        return pileFull;
    }

    @Override
    public Card topCard() {
        return foundationPile.pop();
    }

    @Override
    public boolean canAcceptCard(Card cardToStackOn){ // cardToStack is the card that will have a new card put on top of it
        Card topCard = topCard(); // card that will be put on the top of a new stack
        String[] ranks = topCard.getRanksArr();
        int index = Arrays.asList(ranks).indexOf(cardToStackOn.getRank()); // index of the card on the stack the user wants to move a card to
        
        if(ranks[index] == ranks[index - 1] && topCard.getSuit() == cardToStackOn.getSuit()){
            return true;
        }
        else{
            return false;
        }
    }
}
