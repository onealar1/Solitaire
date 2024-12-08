import java.util.Arrays;
import java.util.Stack;

public class TableauPile extends Pile{

    /* This class is for the 7 piles in the middle of the screen
     * - the top card of the stack should be the smallest number in the stack when sorting
     * - when the game begins, these stacks will be shuffled, so the order does not matter
     */

    private Stack<Card> tableauPile;

    public TableauPile(){
        this.tableauPile = new Stack<>();
    }

    @Override
    public Card topCard() {
        return tableauPile.pop();
    }

    @Override
    public boolean canAcceptCard(Card cardToStackOn){ // cardToStack is the card that will have a new card put on top of it
        Card topCard = topCard(); // card that will be put on the top of a new stack
        String[] ranks = topCard.getRanksArr();
        int index = Arrays.asList(ranks).indexOf(cardToStackOn.getRank()); // index of the card on the stack the user wants to move a card to
        
        if(topCard.getColor() != cardToStackOn.getColor() && ranks[index] == ranks[index + 1]){
            return true;
        }
        else{
            return false;
        }
    }
}
