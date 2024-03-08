import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
public class GameRunner {


    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS =
            {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};


    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS =
            {"♠", "♥", "♦", "♣"};


    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES =
            {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};




    public static void main(String[] args)
    {
        //beginningDeck is the Deck we start with.  When we deal, it gets split into two
        //Decks for each player
        Deck beginningDeck = new Deck(RANKS,SUITS,POINT_VALUES);
        beginningDeck.shuffle();
        //System.out.println(beginningDeck);


        Deck computerDeck = new Deck();
        Deck playerDeck = new Deck();


        while(!beginningDeck.isEmpty()) {
            computerDeck.addToTop(beginningDeck.deal());
            playerDeck.addToTop(beginningDeck.deal());
        }


        System.out.println("Cards have been dalt, each player has 26!");


        String quit = "n";
        int plays = 0;
        Scanner scan = new Scanner(System.in);
        Deck playedDeck = new Deck();
        while(!quit.equals("y")) {
            Card computerCard = computerDeck.deal();
            System.out.println("Computer Played: " + computerCard);
            playedDeck.addToTop(computerCard);
            System.out.println("Press Enter to play!");
            scan.nextLine();
            Card playerCard = playerDeck.deal();
            System.out.println("You Played: " + playerCard);
            playedDeck.addToTop(playerCard);
            plays++;
            if(computerCard.pointValue() > playerCard.pointValue()) {
                System.out.println("Computer Wins!");
                System.out.println(computerCard + " beats " + playerCard);
                while(!playedDeck.isEmpty()) {
                    computerDeck.addToBottom(playedDeck.deal());
                }
                System.out.println("Computer has " + computerDeck.size() + " cards.");
                System.out.println("Player has " + playerDeck.size() + " cards.");
            } else if(computerCard.pointValue() < playerCard.pointValue()) {
                System.out.println("Player Wins!");
                System.out.println(playerCard + " beats " + computerCard);
                while(!playedDeck.isEmpty()) {
                    playerDeck.addToBottom(playedDeck.deal());
                }
                System.out.println("Computer has " + computerDeck.size() + " cards.");
                System.out.println("Player has " + playerDeck.size() + " cards.");
            } else {
                System.out.println("WAR!");
                for(int i = 0; i < 3; i++) {
                    playedDeck.addToBottom(computerDeck.deal());
                    playedDeck.addToBottom(playerDeck.deal());
                }
                System.out.println("It's a TIE! Each player laid down 3 cards.");
            }
            if(plays % 26 == 0) {
                computerDeck.shuffle();
                playerDeck.shuffle();
                System.out.println("You've played " + plays + " times, quit? (y/n): ");
                quit = scan.nextLine();
            }

            if(playerDeck.size() == 0) {
                System.out.println("You lose!");
                System.out.println("Computer won with " + computerDeck.size() + " cards and player had " + playerDeck.size() + " cards.");
                quit = "y";
            }


            if(computerDeck.size() == 0) {
                System.out.println("You Win!");
                System.out.println("Player won with " + playerDeck.size() + " cards and computer had " + computerDeck.size() + " cards.");
                quit = "y";
            }
        }
        System.out.println("Game Over!");
    }
}

