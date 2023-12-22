package ca.cmpt213.as3;

import java.util.Scanner;

import ca.cmpt213.as3.model.Grid;
import ca.cmpt213.as3.ui.GridDisplayer;
import ca.cmpt213.as3.ui.Prompt;

/**
 * Class TokimonFinder. This is the main class which will be used to create all the required objects, display the UI, and call the required methods 
 * @author Alex Ramirez
 * @version 1.0
 */
public class TokimonFinder 
{
    public static void main( String[] args )
    {
        Prompt.displayTokiGameFinder(); // Welcome the player

        Grid gameGrid = new Grid();

        Scanner input = new Scanner(System.in);

        // Check if too many arguments have been passed
        if ( args.length > 3 ) {
            System.out.println("Error: Too many arguments passed!");
        }

        // Set the number of tokimons and fokimons
        for ( int i = 0; i < args.length; i++ ) {
            // Check if an argument is passed
            if ( !( args[i].contains("--numToki=") || args[i].contains("--numFoki=") || args[i].equalsIgnoreCase("--cheat") ) ) {
                System.out.println("Error: Invalid argument(s) passed!");
                System.exit(-1);
            }
            else if ( args[i].contains("--numToki=") ) {
                String[] content = args[i].split("=");
                int numOfTokimons = gameGrid.getNumOfTokis();

                try {
                    numOfTokimons = Integer.parseInt(content[1]);
                    gameGrid.setNumOfTokis(numOfTokimons);
                }
                catch ( NumberFormatException e ) {
                    System.out.println("\nError: Did not pass in a number to set the number of tokimons!\n");
                    System.exit(-1);
                }
            }
            else if ( args[i].contains("--numFoki=") ) {
                String[] content = args[i].split("=");
                int numOfFokimons = gameGrid.getNumOfFokis();

                try {
                    numOfFokimons = Integer.parseInt(content[1]);
                    gameGrid.setNumOfFokis(numOfFokimons);
                }
                catch ( NumberFormatException e ) {
                    System.out.println("\nError: Did not pass in a number to set the number of fokimons!\n");
                    System.exit(-1);
                }
            }
        }

        // If the number of tokimons plus the number of tokimons is greater than 99, display an error
        if ( gameGrid.getNumOfTokis() + gameGrid.getNumOfFokis() > 99 ) {
            System.out.println("\nError: The number of tokimons plus the number of tokimons is greater than 99!\n");
            System.exit(-1);
        }

        gameGrid.initializeRowHeader();
        gameGrid.initializeColumnHeader();
        gameGrid.fillOutTheGridWithSpaces();
        gameGrid.initializeTokiPositions();
        gameGrid.initializeFokiPositions();
        gameGrid.initializeTokimonTrainerPos();
        gameGrid.fillOutRemainingPositions();

        for ( int i = 0; i < args.length; i++ ) {
            if ( args[i].equalsIgnoreCase("--cheat") ) {
                GridDisplayer.displayCheatModeGrid(gameGrid);
            }
        }

        Grid.mainLogic(gameGrid, input);

        input.close();
    }
}
