package ca.cmpt213.as3.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import ca.cmpt213.as3.model.GridCell.CellType;
import ca.cmpt213.as3.ui.GridDisplayer;
import ca.cmpt213.as3.ui.Prompt;

/**
 * Class Grid. Contains attributes and methods required to instantiate the grid and provide its' functionality.
 * Has aggregation of the Tokimon, Fokimon, and TokimonTrainer class.
 * @author Alex Ramirez
 * @version 1.0
 */
public class Grid {
    private List<List<GridCell>> grid = new ArrayList<>();
    private int numOfTokis = 10;
    private int numOfFokis = 5;
    private List<Tokimon> tokimons = new ArrayList<>();
    private List<Fokimon> fokimons = new ArrayList<>();
    private TokimonTrainer player = new TokimonTrainer();
    boolean landedOnFokimon = false;

    public void initializeRowHeader() {
        List<GridCell> firstRow = new ArrayList<>();

        String firstRowStrings[] = { " ", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };

        for ( String currentString : firstRowStrings ) {
            GridCell currentCell = new GridCell();
            currentCell.setCellType(CellType.ROWHEADER);
            currentCell.setCellContent(currentString);
            currentCell.occupyCell();
            firstRow.add(currentCell);
        }

        grid.add(firstRow);
    }

    public void initializeColumnHeader() {
        String firstColumnStrings[] = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J" };

        for ( String currentString : firstColumnStrings ) {
            List<GridCell> currentRow = new ArrayList<>();

            GridCell currentCell = new GridCell();
            currentCell.setCellType(CellType.COLUMNHEADER);
            currentCell.setCellContent(currentString);
            currentCell.occupyCell();

            currentRow.add(currentCell);

            grid.add(currentRow);
        }
    }

    public void setNumOfTokis(int aNumOfTokis) {
        numOfTokis = aNumOfTokis;
    }

    public int getNumOfTokis() {
        return numOfTokis;
    }

    public void setNumOfFokis(int aNumOfFokis) {
        numOfFokis = aNumOfFokis;
    }

    public int getNumOfFokis() {
        return numOfFokis;
    }

    public void fillOutTheGridWithSpaces() {
        for ( int i = 1; i < 11; i++ ) { // row
            for ( int j = 1; j < 11; j++ ) { // column
                GridCell currentCell = new GridCell();
                currentCell.setCellType(CellType.UNVISITEDLOCATION);
                currentCell.setCellContent(" ");

                grid.get(i).add(currentCell); 
            }
        }
    }

    public void initializeTokiPositions() {
        for ( int i = 0; i < getNumOfTokis(); i++ ) {
            boolean foundCellAvailable = false;
            Tokimon currentTokimon = new Tokimon();

            while ( foundCellAvailable == false ) {
                int randomRow = randomInt(1, 11);
                int randomColumn = randomInt(1, 11);
                
                if ( grid.get(randomRow).get(randomColumn).getOccupiedStatus() == false ) {
                    grid.get(randomRow).get(randomColumn).setCellType(CellType.TOKIMON);
                    grid.get(randomRow).get(randomColumn).setCellContent(currentTokimon.getTokiSymbol());
                    grid.get(randomRow).get(randomColumn).occupyCell();
                    foundCellAvailable = true;

                    currentTokimon.setRowOfToki(randomRow);
                    currentTokimon.setColOfToki(randomColumn);
                }
            }

            tokimons.add(currentTokimon);
        }
    }

    public void initializeFokiPositions() {
        for ( int i = 0; i < getNumOfFokis(); i++ ) {
            boolean foundCellAvailable = false;
            Fokimon currentFokimon = new Fokimon();

            while ( foundCellAvailable == false ) {
                int randomRow = randomInt(1, 11);
                int randomColumn = randomInt(1, 11);
                
                if ( grid.get(randomRow).get(randomColumn).getOccupiedStatus() == false ) {
                    grid.get(randomRow).get(randomColumn).setCellType(CellType.FOKIMON);
                    grid.get(randomRow).get(randomColumn).setCellContent(currentFokimon.getFokiSymbol());
                    grid.get(randomRow).get(randomColumn).occupyCell();
                    foundCellAvailable = true;

                    currentFokimon.setRowOfFoki(randomRow);
                    currentFokimon.setColOfFoki(randomColumn);
                }
            }

            fokimons.add(currentFokimon);
        }
    }

    public void initializeTokimonTrainerPos() {
        boolean foundCellAvailable = false;

        while ( foundCellAvailable == false ) {
            int randomRow = randomInt(1, 11);
            int randomColumn = randomInt(1, 11);
                
            if ( grid.get(randomRow).get(randomColumn).getOccupiedStatus() == false ) {
                grid.get(randomRow).get(randomColumn).setCellType(CellType.TOKIMONTRAINER);
                grid.get(randomRow).get(randomColumn).setCellContent(player.getTokiTrainerSymbol());
                grid.get(randomRow).get(randomColumn).occupyCell();
                foundCellAvailable = true;
                player.setTokiTrainerRow(randomRow);
                player.setTokiTrainerCol(randomColumn);
            }
        }
        
    }

    public void fillOutRemainingPositions() {
        for ( int i = 1; i < 11; i++ ) { // row
            for ( int j = 1; j < 11; j++ ) { // column
                if ( grid.get(i).get(j).getOccupiedStatus() == false ) {
                    GridCell currentCell = new GridCell();
                    currentCell.setCellType(CellType.UNVISITEDLOCATION);
                    currentCell.setCellContent("~");
                    grid.get(i).add(currentCell); 
                }
            }
        }
    }

    public void moveTokimonTrainer( String key ) {
        switch ( key.toLowerCase() ) {
            case "w":
                if ( player.getPosOfTokiTrainerRow() > 1 ) {
                    if ( grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).getCellType() == CellType.TOKIMON ) {
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(tokimons.get(0).getTokiSymbol());
                    }
                    else {
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellType(CellType.VISITEDLOCATION);
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(" ");
                    }
                    grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).visitedCell();
                    player.decrementTokiTrainerRow();

                    if ( grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).getCellType() == CellType.FOKIMON ) {
                        landedOnFokimon = true;
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(player.getTokiTrainerSymbol() + fokimons.get(0).getFokiSymbol());
                    }
                    else if ( grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).getCellType() == CellType.TOKIMON ) {
                        Prompt.displayCollectedTokimon();
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(player.getTokiTrainerSymbol() + tokimons.get(0).getTokiSymbol());
                        player.incrementNumOfTokisCollected();
                    }
                    else {
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellType(CellType.TOKIMONTRAINER);
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(player.getTokiTrainerSymbol());
                    }
                }
                break;
            case "a":
                if ( player.getPosOfTokiTrainerCol() > 1 ) {
                    if ( grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).getCellType() == CellType.TOKIMON ) {
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(tokimons.get(0).getTokiSymbol());
                    }
                    else {
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellType(CellType.VISITEDLOCATION);
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(" ");
                    }
                    grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).visitedCell();
                    player.decrementTokiTrainerCol();

                    if ( grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).getCellType() == CellType.FOKIMON ) {
                        landedOnFokimon = true;
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(player.getTokiTrainerSymbol() + fokimons.get(0).getFokiSymbol());
                    }
                    else if ( grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).getCellType() == CellType.TOKIMON ) {
                        Prompt.displayCollectedTokimon();
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(player.getTokiTrainerSymbol() + tokimons.get(0).getTokiSymbol());
                        player.incrementNumOfTokisCollected();
                    }
                    else {
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellType(CellType.TOKIMONTRAINER);
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(player.getTokiTrainerSymbol());
                    }
                }
                break;
            case "s":
                if ( player.getPosOfTokiTrainerRow() < 10 ) {
                    if ( grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).getCellType() == CellType.TOKIMON ) {
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(tokimons.get(0).getTokiSymbol());
                    }
                    else {
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellType(CellType.VISITEDLOCATION);
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(" ");
                    }
                    grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).visitedCell();
                    player.incrementTokiTrainerRow();

                    if ( grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).getCellType() == CellType.FOKIMON ) {
                        landedOnFokimon = true;
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(player.getTokiTrainerSymbol() + fokimons.get(0).getFokiSymbol());
                    }
                    else if ( grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).getCellType() == CellType.TOKIMON ) {
                        Prompt.displayCollectedTokimon();
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(player.getTokiTrainerSymbol() + tokimons.get(0).getTokiSymbol());
                        player.incrementNumOfTokisCollected();
                    }
                    else {
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellType(CellType.TOKIMONTRAINER);
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(player.getTokiTrainerSymbol());
                    }
                }
                break;
            case "d":
                if ( player.getPosOfTokiTrainerCol() < 10 ) {
                    if ( grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).getCellType() == CellType.TOKIMON ) {
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(tokimons.get(0).getTokiSymbol());
                    }
                    else {
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellType(CellType.VISITEDLOCATION);
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(" ");
                    }
                    grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).visitedCell();
                    player.incrementTokiTrainerCol();

                    if ( grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).getCellType() == CellType.FOKIMON ) {
                        landedOnFokimon = true;
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(player.getTokiTrainerSymbol() + fokimons.get(0).getFokiSymbol());
                    }
                    else if ( grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).getCellType() == CellType.TOKIMON ) {
                        Prompt.displayCollectedTokimon();
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(player.getTokiTrainerSymbol() + tokimons.get(0).getTokiSymbol());
                        player.incrementNumOfTokisCollected();
                    }
                    else {
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellType(CellType.TOKIMONTRAINER);
                        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellContent(player.getTokiTrainerSymbol());
                    }
                }
                break;
        }

        player.incrementTurnNum();
    }

    public List<Integer> revealLocationOfRandomToki() {
        boolean tokimonNotFound = false;

        int randInt = 0;
        int row = 0;
        int col = 0;

        while ( tokimonNotFound == false ) {
            randInt = randomInt(0, numOfTokis);
            row = tokimons.get(randInt).getRowOfToki();
            col = tokimons.get(randInt).getColOfToki();

            if ( grid.get(row).get(col).getVisitedCellStatus() == false ) {
                tokimonNotFound = true;
            }
        }

        List<Integer> position = new ArrayList<>();
        position.add(row);
        position.add(col);

        player.decrementNumOfSpells();

        player.incrementTurnNum();

        return position;
    }

    public void randomlyEliminateOneFoki() {
        int randInt = randomInt(0, numOfFokis);

        int row = fokimons.get(randInt).getRowOfFoki();
        int col = fokimons.get(randInt).getColOfFoki();

        grid.get(row).get(col).setCellType(CellType.UNVISITEDLOCATION);
        grid.get(row).get(col).setCellContent("~");

        fokimons.remove(randInt);

        player.incrementTurnNum();

        player.decrementNumOfSpells();
    }

    public void jumpPlayerToAnotherPos() {
        int randomRow = randomInt(1, 11);
        int randomColumn = randomInt(1, 11);

        int currentTokiTrainerRow = player.getPosOfTokiTrainerRow();
        int currentTokiTrainerCol = player.getPosOfTokiTrainerCol();

        grid.get(currentTokiTrainerRow).get(currentTokiTrainerCol).setCellType(CellType.VISITEDLOCATION);
        grid.get(currentTokiTrainerRow).get(currentTokiTrainerCol).visitedCell();
        grid.get(currentTokiTrainerRow).get(currentTokiTrainerCol).setCellContent(" ");

        // Case: new position is occupied by a Fokimon
        if ( grid.get(randomRow).get(randomColumn).getCellType() == CellType.FOKIMON ) { 
            landedOnFokimon = true;
            grid.get(randomRow).get(randomColumn).setCellContent(player.getTokiTrainerSymbol() + fokimons.get(0).getFokiSymbol());
        } // Case: new position is occupied by a Tokimon
        else if ( grid.get(randomRow).get(randomColumn).getCellType() == CellType.TOKIMON ) {
            player.incrementNumOfTokisCollected();
            grid.get(randomRow).get(randomColumn).setCellContent(player.getTokiTrainerSymbol() + tokimons.get(0).getTokiSymbol());
            grid.get(randomRow).get(randomColumn).setCellType(CellType.TOKIMONTRAINER);
            Prompt.displayCollectedTokimon();
        }
        else {
            grid.get(randomRow).get(randomColumn).setCellContent(player.getTokiTrainerSymbol());
            grid.get(randomRow).get(randomColumn).setCellType(CellType.TOKIMONTRAINER);
        }

        player.setTokiTrainerRow(randomRow);
        player.setTokiTrainerCol(randomColumn);

        player.decrementNumOfSpells();

        player.incrementTurnNum();

        grid.get(player.getPosOfTokiTrainerRow()).get(player.getPosOfTokiTrainerCol()).setCellType(CellType.TOKIMONTRAINER);
    }

    // Helper function: Get a random int within a range. Min is inclusive and max is exclusive
    public int randomInt(int min, int max) {
        Random random = new Random();
        return random.ints(min, max).findFirst().getAsInt();
    }

    public boolean getLandedOnFokiStatus() {
        return landedOnFokimon;
    }

    public boolean collectedAllTokimons() {
        if ( player.getNumOfTokisCollected() == numOfTokis ) {
            return true;
        }
        else {
            return false;
        }
    }

    public TokimonTrainer getTokimonTrainer() {
        return player;
    }

    public List<List<GridCell>> getGrid() {
        return grid;
    }
    
    public static void mainLogic( Grid gameGrid, Scanner input ) {
        outermostWhileLoop:
        while ( gameGrid.getLandedOnFokiStatus() == false || ( gameGrid.getTokimonTrainer().getNumOfTokisCollected() == gameGrid.getNumOfTokis() ) ) {
            Prompt.displayTurnNum(gameGrid.getTokimonTrainer()); // Display the turn number

            GridDisplayer.displayCurrentGrid(gameGrid);

            if ( gameGrid.getLandedOnFokiStatus() == true ) {
                GridDisplayer.displayFinalGrid(gameGrid);
                break;
            }

            Prompt.displayTwoOptions(); // Display the two options available

            boolean properResponse = false;

            while ( properResponse == false ) {
                System.out.print("> ");
                String optionEntered = input.nextLine();

                if ( optionEntered.equalsIgnoreCase("1") ) {
                    properResponse = true;
                    boolean landedOnTokimon = false;
                    Prompt.enterKey();

                    boolean anotherProperReponse = false;

                    while( anotherProperReponse == false ) {
                        System.out.print("> ");
                        String nextCommand = input.nextLine();

                        if ( nextCommand.equalsIgnoreCase("w") || nextCommand.equalsIgnoreCase("a")
                        || nextCommand.equalsIgnoreCase("s") || nextCommand.equalsIgnoreCase("d") ) {
                            anotherProperReponse = true;
                            gameGrid.moveTokimonTrainer(nextCommand);
                            if ( landedOnTokimon == true ) {
                                Prompt.displayCollectedTokimon();
                                GridDisplayer.displayCurrentGrid(gameGrid);
                            }
                            else if ( gameGrid.getLandedOnFokiStatus() == true ) {
                                GridDisplayer.displayFinalGrid(gameGrid);
                                Prompt.displayGameOver();
                                break outermostWhileLoop;
                            }
                        }
                        else {
                            System.out.println("Error: You did not enter one of the w, a, s, d keys. Please try again!");
                        }
                    }
                }
                else if ( optionEntered.equalsIgnoreCase("2") && ( gameGrid.getTokimonTrainer().getNumOfSpells() > 0 ) ) {
                    properResponse = true;
                    Prompt.displaySpellOptions();

                    boolean yetAnotherProperResponse = false;
                    while ( yetAnotherProperResponse == false ) {
                        System.out.print("> ");
                        String followingCommand = input.nextLine();

                        if ( followingCommand.equalsIgnoreCase("1") || followingCommand.equalsIgnoreCase("2")
                        || followingCommand.equalsIgnoreCase("3") ) {
                            yetAnotherProperResponse = true;

                            switch( followingCommand ) {
                                case "1":
                                    gameGrid.jumpPlayerToAnotherPos();
                                    if ( gameGrid.getLandedOnFokiStatus() == true ) {
                                        GridDisplayer.displayFinalGrid(gameGrid);
                                        Prompt.displayGameOver();
                                        break outermostWhileLoop;
                                    }
                                    break;
                                case "2":
                                    List<Integer> tokiPos = gameGrid.revealLocationOfRandomToki();
                                    int row = tokiPos.get(0);
                                    int column = tokiPos.get(1);
                                    String pos1 = "";

                                    switch(row) {
                                        case 1:
                                            pos1 = "A";
                                            break;
                                        case 2:
                                            pos1 = "B";
                                            break;
                                        case 3:
                                            pos1 = "C";
                                            break;
                                        case 4:
                                            pos1 = "D";
                                            break;
                                        case 5:
                                            pos1 = "E";
                                            break;
                                        case 6:
                                            pos1 = "F";
                                            break;
                                        case 7:
                                            pos1 = "G";
                                            break;
                                        case 8:
                                            pos1 = "H";
                                            break;
                                        case 9:
                                            pos1 = "I";
                                            break;
                                        case 10:
                                            pos1 = "J";
                                            break;
                                    }

                                    String position = pos1 + column;
                                    Prompt.displayLocationOfToki(position);
                                    break;
                                case "3":
                                    gameGrid.randomlyEliminateOneFoki();
                                    Prompt.displayFokiEliminated();
                                    break;
                            }
                        }
                    }
                }
                else if (optionEntered.equalsIgnoreCase("2") && ( gameGrid.getTokimonTrainer().getNumOfSpells() == 0 )) {
                    System.out.println("Cannot cast any more spells because you have run out of spells!");
                }
                else {
                    System.out.println("Error: You did not enter 1 or 2. Please try again!");
                }
            }

            Prompt.displayNumberOfTokisCollected(gameGrid);
            Prompt.displayNumberOfTokisRemaining(gameGrid);
            Prompt.displayNumOfSpellsRem(gameGrid);

            if ( gameGrid.getTokimonTrainer().getNumOfTokisCollected() == gameGrid.getNumOfTokis() ) {
                GridDisplayer.displayFinalGrid(gameGrid);
                Prompt.displayWin();
                break outermostWhileLoop;
            }
        }
    }

}
