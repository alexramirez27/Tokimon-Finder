package ca.cmpt213.as3.ui;

import ca.cmpt213.as3.model.Grid;
import ca.cmpt213.as3.model.TokimonTrainer;

/**
 * Class Prompt. This class is a User Interface class.
 * Has several methods that print specific parts of information that we need for the game.
 * @author Alex Ramirez
 * @version 1.0
 */
public class Prompt {
    public static void displayTokiGameFinder() {
        System.out.println("\n***********************************************");
        System.out.println("* Tokimon Finder by Alex Ramirez sn 301314693 *");
        System.out.println("***********************************************");
    }

    public static void displayTurnNum( TokimonTrainer aTokimonTrainer ) {
        System.out.println("\n\nTurn number: " + aTokimonTrainer.getTurnNum());
    }

    public static void displayTwoOptions() {
        System.out.println("Choose your next move:");
        System.out.println("1. Move up, down, left, or right from their current position (using keys W, A, S, or D).");
        System.out.println("2. Use a spell.");
    }

    public static void displaySpellOptions() {
        System.out.println("\nChoose one of the following spells:");
        System.out.println("1. Jump the player to another grid location.");
        System.out.println("2. Randomly reveal the location of one of the Tokimons.");
        System.out.println("3. Randomly eliminate one of the Fokimons.");
    }

    public static void enterKey() {
        System.out.println("Enter key: ");
    }

    public static void displayNumOfTokisRem( Grid aGrid ) {
        System.out.println("The number of tokimons remaining is: " + (aGrid.getNumOfTokis() - aGrid.getTokimonTrainer().getNumOfTokisCollected()) );
    }

    public static void displayCollectedTokimon() {
        System.out.println("\nCongratulations, you collected a Tokimon!");
    }

    public static void displayWin() {
        System.out.println("Congratulations, you have collected all of the tokimons. You win!\n");
    }

    public static void displayGameOver() {
        System.out.println("You landed on a Fokimon. Game over!\n");
    }

    public static void displayLocationOfToki( String position ) {
        System.out.println("A tokimon is located on " + position);
    }

    public static void displayFokiEliminated() {
        System.out.println("One fokimon has been eliminated from the grid!");
    }

    public static void displayNumberOfTokisCollected( Grid aGrid ) {
        System.out.println("\nYou have collected " + aGrid.getTokimonTrainer().getNumOfTokisCollected() + " tokimons so far!");
    }

    public static void displayNumberOfTokisRemaining( Grid aGrid ) {
        System.out.println("There are " + (aGrid.getNumOfTokis() - aGrid.getTokimonTrainer().getNumOfTokisCollected()) + " tokimons remaining!");
    }

    public static void displayNumOfSpellsRem( Grid aGrid ) {
        System.out.println("You have " + aGrid.getTokimonTrainer().getNumOfSpells() + " spells remaining!");
    }
}
