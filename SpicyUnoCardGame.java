/*
Rules:

If the user plays a 7, they Swap Hands with a random player
If they have one card left they must type 'Spicy Uno!'
If the user plays a 3, the user across from them draws one card
If the user plays an Ace, the hands rotate clockwise
The winning card must be signed
If the user plays a jack  the person next to them draws 4 cards and the suit changes to the suit of the jack you played (Jack’s can be played any time)
If the user wins they get to pick a prize
If the computer wins they get to pick a prize

*/

import java.util.ArrayList;
import java.util.*;
import java.util.Scanner;
import java.util.Arrays;

public class SpicyUnoCardGame {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

// Initializing Variables
    String[] deck = new String[52];
    ArrayList<String> useableDeckList = new ArrayList<String>();
    ArrayList<String> hand1 = new ArrayList<String>();
    ArrayList<String> hand2 = new ArrayList<String>();
    ArrayList<String> hand3 = new ArrayList<String>();
    ArrayList<String> userHand = new ArrayList<String>();
    ArrayList<String> discardDeck = new ArrayList<String>() ;
    ArrayList<String> topDiscard = new ArrayList<String>();
    List<String> topDiscard888 = new ArrayList<String>();
    String[] topDiscardBuff = new String[3];
    String[] cardNumber = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"} ;
    String[] symbol = {"Hearts", "Diamonds", "Clubs", "Spades"};

    cls();
// Initial set up of the game
    popDeckArr(deck, cardNumber, symbol); // populate deck Array
    useableDeckList = popDeckList(deck, cardNumber, symbol); // Populate deck ArrayList
    popPlayerHands(useableDeckList, hand1, hand2, hand3, userHand); // Populate player hands
    Boolean check = lengthCheck(hand1, hand2, hand3, userHand); // checks to see if a hand has 0 cards in hand
    discardPile(discardDeck,useableDeckList,topDiscard888, topDiscardBuff, topDiscard); // starts the discard pile

// Initial Text Box
    System.out.println();
    System.out.println("Welcome to SpUNO! The party game all about strategy and good luck!");
    System.out.println("To play, you will have to play a card that matches suits or symbols.");
    System.out.println("Press enter to cotinue to the rules. ");
    input.nextLine(); //waits until the enter key is press to continue
    System.out.println();
    System.out.println("The objective is to run out of cards by playing either a matching number or suit card.");
    System.out.println();
    System.out.println("The first player to run out of cards is the winner!");
    System.out.println();
    System.out.println("-----------------------------------------------------");
    System.out.println();
    System.out.println("However, there are some twists that must be explained...");
    System.out.println();
    System.out.println("The First Rule is that when a player plays a 7 of any suit, that player's hand switches randomly with another player.");
    System.out.println();
    System.out.println("The Second Rule is that when a player plays their second to last card, they must say(type) 'Spicy Uno'");
    System.out.println();
    System.out.println("The Third Rule is that when a player plays a 3 of any suit, the player across from them draws one card.");
    System.out.println();
    System.out.println("The Fourth Rule is that when a player plays an Ace of any suit, every players hand rotates clockwise(sequential order) one player.");
    System.out.println();
    System.out.println("The Fifth Rule is that when a player plays a Jack of any suit, the next player in order draws four cards and the suit changes to the suit of the played Jack.");
    System.out.println();
    System.out.println("The Sixth Rule is that when a player plays their last card of their hand, the player must sign the winning card.");
    System.out.println();
    System.out.println("The Seventh Rule is that when the user wins, he/she gets to pick a prize.");
    System.out.println();
    System.out.println("The Eighth Rule is that when the computer wins it gets to pick a prize.");
    System.out.println();
    System.out.println("Once you have read all the rules, please type anything to continue.  :)");
    input.nextLine();
    cls();
    System.out.println("-----------------------------------------------------");
    System.out.println();
    System.out.println("The first card is " + printTopDiscard(topDiscard));
    System.out.println();
    System.out.println("-----------------------------------------------------");
    System.out.println();

// Calling methods to play
    while(check){
      compTurn(useableDeckList, discardDeck, hand1, topDiscard, topDiscard888, 1, hand2, hand3, userHand); // Hand 1 turn
      check = lengthCheck(hand1, hand2, hand3, userHand);
      if(check == false){
        break;
      }
  //    printUseableDeck(useableDeckList);
      compTurn(useableDeckList, discardDeck, hand2, topDiscard, topDiscard888, 2, hand1, hand3, userHand); // Hand 2
      check = lengthCheck(hand1, hand2, hand3, userHand);
      if(check == false){
        break;
      }
    //  printUseableDeck(useableDeckList);
      compTurn(useableDeckList, discardDeck, hand3, topDiscard, topDiscard888, 3, hand1, hand2, userHand); // Hand 3
      check = lengthCheck(hand1, hand2, hand3, userHand);
      if(check == false){
        break;
      }
    //  printUseableDeck(useableDeckList);
      userTurn(useableDeckList, discardDeck, userHand, topDiscard, topDiscard888, 4, hand1, hand2, hand3); // User turn
      check = lengthCheck(hand1, hand2, hand3, userHand);
      if(check == false){
        break;
      }
    //  printUseableDeck(useableDeckList);
      check = lengthCheck(hand1, hand2, hand3, userHand);
    } // end while

    //Signing winning card
    signWinningCard(deck, discardDeck, hand1, hand2, hand3, userHand);

    //Creating an arrayList of Prizes
    ArrayList<String> prizes = new ArrayList<String>();
    prizes.add("1. Teddy Bear");
    prizes.add("2. A Chocolate Bar");
    prizes.add("3. A slice of cake");
    prizes.add("4. A Bouncy Ball");
    prizes.add("5. A Cookie");

    System.out.println("Here is your choices for prizes: ");

    for(int p = 0; p<prizes.size(); p++)
    {
      System.out.println(prizes.get(p));
    }

    //Giving prize to user if they won
    if (userHand.size() == 0)
    {
      Scanner choice = new Scanner(System.in);
      System.out.println("Pick a prize and enter the number of the prize you chose: ");
      int prize = 0;
      prize = choice.nextInt();

      if(prize == 1)
      {
        System.out.println("Here is your Teddy Bear!\nThanks for Playing!");
      }

      else if(prize == 2)
      {
        System.out.println("Here is your Chocolate Bar!\nThanks for Playing!");
      }

      else if(prize == 3)
      {
        System.out.println("Here is your Slice of Cake!\nThanks for Playing!");
      }

      else if(prize == 4)
      {
        System.out.println("Here is your Bouncy Ball!\nThanks for Playing!");
      }

      else if(prize == 5)
      {
        System.out.println("Here is your Cookie!\nThanks for Playing!");
      }

      else
      {
        System.out.println("You chose a secret prize! You get a Iphone!\nThanks for Playing!");
      }
    }

    else
    {
      //Giving random prize to Cpu if they won
      int randomPrize = 0;
      randomPrize = (int)(Math.random() * 5) + 1;

      if(randomPrize == 1)
      {
        System.out.println("\nThey chose prize Number 1!");
        System.out.println("Here is your Teddy Bear!\nThanks for Playing!");
      }

      else if(randomPrize == 2)
      {
        System.out.println("\nThey chose prize Number 2!");
        System.out.println("Here is your Chocolate Bar!\nThanks for Playing!");
      }

      else if(randomPrize == 3)
      {
        System.out.println("\nThey chose prize Number 3!");
        System.out.println("Here is your Slice of Cake!\nThanks for Playing!");
      }

      else if(randomPrize == 4)
      {
        System.out.println("\nThey chose prize Number 4!");
        System.out.println("Here is your Bouncy Ball!\nThanks for Playing!");
      }

      else if(randomPrize == 5)
      {
        System.out.println("\nThey chose prize Number 5!");
        System.out.println("Here is your Cookie!\nThanks for Playing!");
      }
    }

    } // end main

    //----------------------------START OF RULES-------------------------------

    //Creating a big special rules method to swap hands and draw cards when special cards are played
    public static int rules(ArrayList<String> deck, ArrayList<String> dis, ArrayList<String> hand1
    , ArrayList<String> topDis, List<String> topCard888,
    ArrayList<String> hand2, ArrayList<String> hand3, ArrayList<String> hand4, int player )
    {
      //Stopping the game if someone wins and exiting the rules method
      Boolean t = true;
      ArrayList<String> temp = new ArrayList<String>();
      Boolean length;
      length = lengthCheck(hand1, hand2, hand3, hand4);
      if(length == false)
      {
        return 1; //
      }

      //Performing the rule for playing a 7
      else if(topDis.contains("7")) //if the card played has x to decide which rule to use
      {
        int playerChosen = 0;
        while(t)
        {
          int rand = 0;
          rand = (int)(Math.random() * 4) + 1;
          playerChosen = rand;  //randomly assign hand to a player
          if(playerChosen != player) //make sure player the deck is assigned to is not the player who played the 7
          {
            t = false;
          }//end player if
        }//end while
        if(player == 1)   //The next 108 lines are for the 7 rule which switch hands with other players
        {
          if(playerChosen == 2)
          {
              swapHands(hand1,hand2);
              System.out.println();
              System.out.println("They randomly swapped hands with player 2.");
              System.out.println();
          }

          else if(playerChosen == 3)
          {
              swapHands(hand1,hand3);
              System.out.println();
              System.out.println("They randomly swapped hands with player 3.");
              System.out.println();
          }

          else if(playerChosen == 4)
          {
              swapHands(hand1,hand4);
              System.out.println();
              System.out.println("They randomly swapped hands with you.");
              System.out.println();
          }
        }

        else if(player == 2)
        {
          if(playerChosen == 1)
          {
              swapHands(hand2,hand1);
              System.out.println();
              System.out.println("They randomly swapped hands with player 1.");
              System.out.println();
          }

          else if(playerChosen == 3)
          {
              swapHands(hand2,hand3);
              System.out.println();
              System.out.println("They randomly swapped hands with player 3.");
              System.out.println();
          }

          else if(playerChosen == 4)
          {
              swapHands(hand2,hand4);
              System.out.println();
              System.out.println("They randomly swapped hands with you.");
              System.out.println();
          }
        }
        else if(player == 3)
        {
            if(playerChosen == 1)
            {
              swapHands(hand3,hand1);
              System.out.println();
              System.out.println("They randomly swapped hands with player 1.");
              System.out.println();
            }

            else if(playerChosen == 2)
            {
              swapHands(hand3,hand2);
              System.out.println();
              System.out.println("They randomly swapped hands with player 2.");
              System.out.println();
            }

            else if(playerChosen == 4)
            {
              swapHands(hand3,hand4);
              System.out.println();
              System.out.println("They randomly swapped hands with you.");
              System.out.println();
            }
        }

        else if(player == 4)
        {
            if(playerChosen == 1)
            {
              swapHands(hand4,hand1);
              System.out.println();
              System.out.println("You randomly swapped hands with player 1.");
              System.out.println();
            }

            else if(playerChosen == 2)
            {
              swapHands(hand4,hand2);
              System.out.println();
              System.out.println("You randomly swapped hands with player 2.");
              System.out.println();
            }

            else if(playerChosen == 3)
            {
              swapHands(hand4,hand3);
              System.out.println();
              System.out.println("You randomly swapped hands with player 3.");
              System.out.println();
            }

        }

        return 1;

      }//end rule for 7

      //Asking user to enter spicy Uno if they have one card left after playing
      else if((hand4.size() == 1) && (player == 4))
      {
        Scanner kb = new Scanner(System.in);
        System.out.println("You have one card left! Enter the words: 'Spicy Uno' : ");
        String spice = "";
        spice = kb.nextLine();
        spice = spice.toLowerCase();
          if (spice.contains("spicy uno"))
          {
            System.out.println();
            System.out.println();
            System.out.println("Be aware everyone! Player 4 has one card left!");
            System.out.println();
            System.out.println();
          }//end if
          else
          {
            System.out.println();
            System.out.println();
            hand4.add(deck.get(0));
            deck.remove(0);
            System.out.println("Player 4 failed to type 'Spicy Uno'. They have been penalized.");
            System.out.println();
            System.out.println();
          }//end else
      }

      //Printing out that the cpu has 1 card left
      if((hand3.size()==1) && (player == 3))
      {
        System.out.println();
        System.out.println();
        System.out.println("Be aware everyone! Player 3 has one card left!");
        System.out.println();
        System.out.println();
      }

      if((hand2.size()==1) && (player == 2))
      {
        System.out.println();
        System.out.println();
        System.out.println("Be aware everyone! Player 2 has one card left!");
        System.out.println();
        System.out.println();
      }

      if((hand1.size()==1) && (player == 1))
      {
        System.out.println();
        System.out.println();
        System.out.println("Be aware everyone! Player 1 has one card left!");
        System.out.println();
        System.out.println();
      }

      //Performing the rule for playing a 3
      else if(topDis.contains("3"))
      {
        if(player == 1)
        {
          hand3.add(deck.get(0)); // adds first card from deck to hand
          deckCheck(deck, dis);
          deck.remove(0);
          System.out.println();
          System.out.println("The player across from them draws one card.");
        }

        else if(player == 2)
        {
          hand4.add(deck.get(0)); // adds first card from deck to hand
          deckCheck(deck, dis);
          deck.remove(0);
          System.out.println();
          System.out.println("The player across from them draws one card.");
        }

        else if(player == 3)
        {
          hand1.add(deck.get(0)); // adds first card from deck to hand
          deckCheck(deck, dis);
          deck.remove(0);
          System.out.println();
          System.out.println("The player across from them draws one card.");
        }

        else if(player == 4)
        {
          hand2.add(deck.get(0)); // adds first card from deck to hand
          deckCheck(deck, dis);
          deck.remove(0);
          System.out.println();
          System.out.println("The player across from you draws one card.");
          System.out.println();
        }
      }

      //Performing the rule for playing an Ace
      else if(topDis.contains("A"))
      {
        swapHands(hand1,hand4);
        swapHands(hand2,hand4);
        swapHands(hand3,hand4);
        System.out.println();
        System.out.println("All of the hands rotate clockwise (sequential hand order).");
        System.out.println();
      }

      //Performing the rule for playing a Jack
      else if(topDis.contains("J"))
      {
        if(player == 1)
        {
          for (int i = 0 ; i<4;i++ ) {
            deckCheck(deck, dis);
            hand2.add(deck.get(0)); // adds first card from deck to hand
            deck.remove(0);
          }
          System.out.println();
          System.out.println("The player after them draws 4 cards and the suit changes to their Jack's suit.");
          System.out.println();
        }

        else if(player == 2)
        {
          for (int i = 0 ; i<4;i++ ) {
            deckCheck(deck, dis);
            hand3.add(deck.get(0)); // adds first card from deck to hand
            deck.remove(0);
          }
          System.out.println();
          System.out.println("The player after them draws 4 cards and the suit changes to their Jack's suit.");
          System.out.println();
        }

        else if(player == 3)
        {
          for (int i = 0 ; i<4;i++ ) {
            deckCheck(deck, dis);
            hand4.add(deck.get(0)); // adds first card from deck to hand
            deck.remove(0);
          }
          System.out.println();
          System.out.println("The player after them draws 4 cards and the suit changes to their Jack's suit.");
          System.out.println();
        }

        else if(player == 4)
        {
          for (int i = 0 ; i<4;i++ ) {
            deckCheck(deck, dis);
            hand1.add(deck.get(0)); // adds first card from deck to hand
            deck.remove(0);
          }
          System.out.println();
          System.out.println("The player after you draws 4 cards and the suit changes to your Jack's suit.");
          System.out.println();
        }
      }
      return 1;
    } // ends rules




// User Turn method
    public static void userTurn(ArrayList<String> deck, ArrayList<String> dis, ArrayList<String> uH, ArrayList<String> topDis,
    List<String> topCard888, int player, ArrayList<String> hand1, ArrayList<String> hand2, ArrayList<String> hand3){
      int house = 0;
      Scanner input = new Scanner(System.in);
      try {
        // to sleep 1 second
        Thread.sleep(1000);
      } catch (InterruptedException e) {
          // recommended because catching InterruptedException clears interrupt flag
          Thread.currentThread().interrupt();
          return;
        }
      //Printing out info that you need for the current round
      System.out.println();
      System.out.println("===================================================");
      System.out.println();
      System.out.println("The card on top of the pile is " + printTopDiscard(topDis) + "...");
      System.out.println();
      System.out.println();
      System.out.println("Your hand:\n \n" + uH + "\n");
      System.out.println();
      printUserCardIndex(uH);
      System.out.println();
      System.out.println("===================================================");
      System.out.println();
      System.out.println("Enter 0 if you do not have a playable card.");
      System.out.println("If you have a playable card, which card number would you like to play? ");
      System.out.println();
      System.out.print("I would like to play card number: ");
      int playerCard = input.nextInt();
      int cardIndex = playerCard -1;

      cls(); //clear after each round of turns
      while((playerCard > uH.size()) || (playerCard < 0)) // validates card index
      {
        System.out.println("The card on top of the pile is " + printTopDiscard(topDis) + "...");
        System.out.println();
        System.out.println("Enter 0 if you do not have a playable card.");
        System.out.println("Your hand:\n \n" + uH + "\n");
        System.out.println();
        printUserCardIndex(uH);
        System.out.println();
        System.out.println("Invalid input. Choose another card.");
        System.out.print("I would like to play card number: ");
        playerCard = input.nextInt();
        cardIndex = playerCard -1;
      } // end while

      //Checking to see if user played a mismatching card
      if ((playerCard > 0) && (uH.get(cardIndex).contains(topDis.get(0)) || uH.get(cardIndex).contains(topDis.get(2)) || uH.get(cardIndex).contains("J"))){
        dis.add(uH.get(cardIndex)); // adds card played to discard pile
        newDiscard(topDis, uH.get(cardIndex), topCard888); // sets a new top card
        System.out.println();
        System.out.println();
        System.out.println("You played the " + uH.get(cardIndex));
        System.out.println();
        System.out.println("-------------------------------------------------");
        uH.remove(cardIndex);
        house = rules(deck, dis, hand1, topDis, topCard888, hand2, hand3, uH, player);
      } // end if
      else {
        if(cardIndex == -1){
          System.out.println("You do not have a matching card.");
        }
        else {
          System.out.println("You played a miss matching card.");
        }

        System.out.println();
        System.out.println("You drew one card.");
        System.out.println();
        deckCheck(deck, dis);
        uH.add(deck.get(0)); // adds first card from deck to hand
        deck.remove(0);
        System.out.println();
      } // end else
      System.out.println("Next round of turns:");
      System.out.println();
    } // end User turn




// CPU's turn
    public static void compTurn(ArrayList<String> deck, ArrayList<String> dis, ArrayList<String> currentHand, ArrayList<String> topDis,
    List<String> topCard888, int player, ArrayList<String> hand1, ArrayList<String> hand2, ArrayList<String> hand3){
      int house = 0;
      Boolean draw = true;
      for (int i = 0; i < currentHand.size() ; i++ ) {
        if(currentHand.get(i).contains(topDis.get(0)) || currentHand.get(i).contains(topDis.get(2)) || currentHand.get(i).contains("J")){ // if a card matches a number/sym remove from hand and add to discard
          dis.add(currentHand.get(i));
          newDiscard(topDis, currentHand.get(i), topCard888); // top card becomes the last card in discard pile
          try {
            // to sleep 1 second
            Thread.sleep(1000);
          } catch (InterruptedException e) {
              // recommended because catching InterruptedException clears interrupt flag
              Thread.currentThread().interrupt();
              return;
            }
          System.out.println();
          System.out.println("Player " + player + " played " + currentHand.get(i));
          System.out.println();
          currentHand.remove(i);
          draw = false;
          if(player == 1) //if the player is player 1 we return a specific order to keep rules as the same variables
          {
            rules(deck, dis, currentHand, topDis, topCard888, hand1, hand2, hand3, player);
          }//end player if
          else if (player == 2) //repeat above but for each computer hand
          {
            rules(deck, dis, hand1, topDis, topCard888, currentHand, hand2, hand3, player);
          }//else if player 2
          else if (player == 3) //repeat above but for each computer hand
          {
            house = rules(deck, dis, hand1, topDis, topCard888, hand2, currentHand, hand3, player);
          }//else if player 3
          break; // first card to match breaks out of loop
        } // end if
      } // end for i
      if (draw){ // if no hand has no cards that matches draw one card
        deckCheck(deck, dis);
        currentHand.add(deck.get(0)); // adds first card from deck to hand
        deck.remove(0);
        System.out.println();
        System.out.println("Player " + player + " could not play and drew one card.");

      } // end if draw
      System.out.println();
    } // end Player 2 turn


// Signing the winning Card
    public static void signWinningCard(String[] deck, ArrayList<String> discardDeck, ArrayList<String> cpu1, ArrayList<String> cpu2,
    ArrayList<String> cpu3, ArrayList<String> userHand){
      Scanner input = new Scanner(System.in);
      String sign = "";
      String winningCard = "";
      if (cpu1.size() == 0){
        System.out.println("CPU 1 won SpUNO");
        winningCard = discardDeck.get(discardDeck.size() - 4);
        System.out.println("They decide to sign the card " + winningCard + " with 'Howdy!' ");
        sign = " Howdy!" ;
      }
      else if (cpu2.size() == 0){
        System.out.println("CPU 2 won SpUNO");
        winningCard = discardDeck.get(discardDeck.size() - 3);
        System.out.println("They decide to sign the card " + winningCard + " with 'Gig 'em!' ");
        sign = " Gig 'em!";
      }
      else if (cpu3.size() == 0){
        System.out.println("CPU 3 won SpUNO");
        winningCard = discardDeck.get(discardDeck.size() - 2);
        System.out.println("They decide to sign the card " + winningCard +  " with 'OFO on the Rise!' ");
        sign = " OFO on the Rise!";
      }
      else if (userHand.size() == 0){
        System.out.println("You won SpUNO!");
        winningCard = discardDeck.get(discardDeck.size() - 1);
        System.out.println("You get to sign the winning card!");
        System.out.println("What will you sign? ");
        sign = input.nextLine();
      }

      for (int i = 0; i<52 ; i++) {
        if (deck[i] == winningCard){
          deck[i] = deck[i] + sign;
        } // end if
      } // end for
    } // end signWinningCard

// Switching hands
    public static void swapHands(ArrayList<String> player, ArrayList<String> randPlayer){
      ArrayList<String> temp = new ArrayList<String>() ;
      for (String card : player) {
        temp.add(card);
      }

      player.clear();

      for (String card : randPlayer) {
        player.add(card);
      }

      randPlayer.clear();

      for (String card : temp) {
        randPlayer.add(card);
      }

    } // end

// Print User card index
    public static void printUserCardIndex(ArrayList<String> uH){
      int j;
      System.out.print("\t");
      for (int i = 0;i<uH.size() ; i++) {
        j = i + 1;
        System.out.print( j + "            ");
      }
      System.out.println();
      System.out.println();
    } // end print user card index


// New top discard card
    public static void newDiscard(ArrayList<String> topDis, String card, List<String> topCard888){
      topCard888 = Arrays.asList(card.split(" "));
      topDis.clear();
      for (int i = 0; i<3; i++ ) {
        topDis.add(topCard888.get(i));
      } // end for i
    } // end new discard



// Checks length of deck
  public static void deckCheck(ArrayList<String> deck, ArrayList<String> dis){
    if (deck.size() == 0){
      for (int i = 0; i<dis.size() ;i++ ) {
        deck.add(dis.get(i));
        dis.remove(i);
      } // end for i
    } // end if
    for (int i=0; i < 10; i++){ // shuffles 10 time
      Collections.shuffle(deck);
    } // end shuffle
  } // end deck check



// Populate Discard Pile
    public static void discardPile(ArrayList<String> dis,ArrayList<String> deck, List<String> topCard888, String[] buff, ArrayList<String> topCard){
      dis.add(deck.get(0));
      buff = dis.get(0).split(" ");
      topCard888 = Arrays.asList(buff);
      deck.remove(0);
      for (int i = 0; i<3; i++ ) {
        topCard.add(topCard888.get(i));
      } // end for i
    } // end discard



// Print discard card
    public static String printTopDiscard(ArrayList<String> topDis){
      String card = "";
      for (int i = 0; i<3 ; i++ ) {
        card = card + topDis.get(i) + " ";
      } // end for i
      return card;
    } // end print topDiscard


//check length of hands
    public static Boolean lengthCheck(ArrayList<String> p1, ArrayList<String> p2, ArrayList<String> p3, ArrayList<String> uH){
      Boolean h1 = (p1.size() != 0);
      Boolean h2 = (p2.size() != 0);
      Boolean h3 = (p3.size() != 0);
      Boolean uHL = (uH.size() != 0);
      Boolean check = (h2 && h1 && h3 && uHL);
      return check;
    } // end length check



// Populate Player hands
  public static void popPlayerHands(ArrayList<String> deck, ArrayList<String> h1, ArrayList<String> h2, ArrayList<String> h3, ArrayList<String> uH){
    int cards = 7;
    for (int i = 0; i < cards; i++){
      h1.add(deck.get(0));
      deck.remove(0);
      h2.add(deck.get(0));
      deck.remove(0);
      h3.add(deck.get(0));
      deck.remove(0);
      uH.add(deck.get(0));
      deck.remove(0);
    }//end for
  } // end popPlayerHands



//Print deck array list
  public static void printUseableDeck (ArrayList<String> uD){
    System.out.println("Shuffled Deck size " + uD.size());
    // for (int i = 0; i<uD.size(); i+=4){
    //   System.out.printf("\t" + uD.get(i) + "\t" + uD.get(i+1) + "\t"+ uD.get(i+2) + "\t" + uD.get(i+3)+ "\n");
    // } // end for
    System.out.println();
  } // end printUseableDeck

// Print Into text

// Print out organized deck array (fresh out the box)
    public static void printDeck(String[] d){
      System.out.println();
      System.out.println("Sorted Deck:");

      for (int i = 0; i<52 ; i += 4 ){
        System.out.printf("\t" + d[i] + "\t\t" + d[i+1] + "\t\t" + d[i+2] + "\t\t" + d[i+3] + "\n");
      } // end print columbs

      System.out.println();
    } // end printDeck



// Populating Deck Array
  public static void popDeckArr (String[] d, String[] n, String[] s){
    int deckPlace = 0;
    for (int i = 0; i<13; i++){
      for ( int j = 0; j < 4; j++){
        d[deckPlace] = n[i] + " of " + s[j] ;
        deckPlace += 1;
      } // end for j
    } // end for i
  } // end popDeckArr

// Print Intro Text
public static void printIntro(){

} // end print intro

// Populate and Shuffle Deck ArrayList
  public static ArrayList<String> popDeckList (String[] d, String[] n, String[] s){
    ArrayList<String> deckList = new ArrayList<String>();
    for (int i = 0; i < 52; i++ ){
      deckList.add(d[i]);
    } // end for useableDeck
    for (int i=0; i < 10; i++){ // shuffles 5 time
      Collections.shuffle(deckList);
    } // end shuffle
    return deckList;
  } // end popDeckList

  //this cls method allows for Powershell or Terminal to erase and displays a clean page
  public static void cls(){
      try{
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
      }
      catch (Exception e){
        System.out.println("\033[\033[2J");
      }
    }//end clear

} // end class
