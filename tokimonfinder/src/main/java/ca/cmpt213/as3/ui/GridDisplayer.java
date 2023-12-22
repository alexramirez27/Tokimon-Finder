package ca.cmpt213.as3.ui;

import ca.cmpt213.as3.model.Grid;
import ca.cmpt213.as3.model.GridCell.CellType;

/**
 * Class Prompt. This class is a User Interface class.
 * Has a few methods which are used to display the grid in the game. These include the current grid, the final grid, and the cheat mode grid.
 * @author Alex Ramirez
 * @version 1.0
 */
public class GridDisplayer {
    public static void displayCurrentGrid( Grid aGrid ) {
        System.out.println("\nGame Grid:\n");

        // Print the row header (1-10)
        System.out.print("   ");
        for ( int j = 0; j < 11; j++ ) {
            if ( aGrid.getGrid().get(0).get(j).getCellContent().length() > 1 ) {
                System.out.print( aGrid.getGrid().get(0).get(j).getCellContent() + " " );
            }
            else {
                System.out.print( " " + aGrid.getGrid().get(0).get(j).getCellContent() + " " );
            }
        }

        System.out.println();

        // Print the remaining rows
        for ( int i = 1; i < 11; i++ ) {
            System.out.print("   ");
            for ( int j = 0; j < 11; j++ ) {
                if ( aGrid.getGrid().get(i).get(j).getVisitedCellStatus() == true ) {
                    System.out.print( " " + aGrid.getGrid().get(i).get(j).getCellContent() + " " );
                }
                else if ( aGrid.getGrid().get(i).get(j).getCellContent().length() > 1
                && ( aGrid.getGrid().get(i).get(j).getCellContent().equalsIgnoreCase("X")
                || aGrid.getGrid().get(i).get(j).getCellContent().equalsIgnoreCase("$") ) ) { // Do not print X or S
                    System.out.print( " ~" );
                }
                else if ( aGrid.getGrid().get(i).get(j).getCellContent().equalsIgnoreCase("X") // Do not print X or $
                || aGrid.getGrid().get(i).get(j).getCellContent().equalsIgnoreCase("$") ) {
                    System.out.print( " ~ " );
                }
                else if ( aGrid.getGrid().get(i).get(j).getCellContent().length() > 1 ) {
                    System.out.print( " " + aGrid.getGrid().get(i).get(j).getCellContent() );
                }
                else if ( aGrid.getGrid().get(i).get(j).getCellType() == CellType.TOKIMONTRAINER ) {
                    System.out.print( " " + aGrid.getGrid().get(i).get(j).getCellContent() + " " );
                }
                else if ( aGrid.getGrid().get(i).get(j).getCellType() == CellType.UNVISITEDLOCATION ) {
                    System.out.print( " ~ " );
                }
                else if ( aGrid.getGrid().get(i).get(j).getVisitedCellStatus() == true ) {
                    System.out.print("   ");
                }
                else {
                    System.out.print( " " + aGrid.getGrid().get(i).get(j).getCellContent() + " " );
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void displayFinalGrid( Grid aGrid ) {
        System.out.println("\nGame Grid:\n");

        // Print the row header (1-10)
        System.out.print("   ");
        for ( int j = 0; j < 11; j++ ) {
            if ( aGrid.getGrid().get(0).get(j).getCellContent().length() > 1 ) {
                System.out.print( aGrid.getGrid().get(0).get(j).getCellContent() + " " );
            }
            else {
                System.out.print( " " + aGrid.getGrid().get(0).get(j).getCellContent() + " " );
            }
        }

        System.out.println();

        // Print the remaining rows
        for ( int i = 1; i < 11; i++ ) {
            System.out.print("   ");
            for ( int j = 0; j < 11; j++ ) {
                if ( aGrid.getGrid().get(i).get(j).getCellContent().length() > 1 && !aGrid.getGrid().get(i).get(j).getCellContent().equalsIgnoreCase("~") ) {
                    System.out.print( " " + aGrid.getGrid().get(i).get(j).getCellContent() );
                }
                else if ( !aGrid.getGrid().get(i).get(j).getCellContent().equalsIgnoreCase("~") ) {
                    System.out.print( " " + aGrid.getGrid().get(i).get(j).getCellContent() + " " );
                }
                else {
                    System.out.print( "   " );
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void displayCheatModeGrid( Grid aGrid ) {
        System.out.println("\nCheat Mode Game Grid:\n");

        // Print the row header (1-10)
        System.out.print("   ");
        for ( int j = 0; j < 11; j++ ) {
            if ( aGrid.getGrid().get(0).get(j).getCellContent().length() > 1 ) {
                System.out.print( aGrid.getGrid().get(0).get(j).getCellContent() + " " );
            }
            else {
                System.out.print( " " + aGrid.getGrid().get(0).get(j).getCellContent() + " " );
            }
        }

        System.out.println();

        // Print the remaining rows
        for ( int i = 1; i < 11; i++ ) {
            System.out.print("   ");
            for ( int j = 0; j < 11; j++ ) {
                if ( aGrid.getGrid().get(i).get(j).getCellContent().length() > 1 ) { 
                    System.out.print( " " + aGrid.getGrid().get(i).get(j).getCellContent() );
                }
                else {
                    System.out.print( " " + aGrid.getGrid().get(i).get(j).getCellContent() + " " );
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
