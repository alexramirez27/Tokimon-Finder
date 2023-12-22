package ca.cmpt213.as3.model;

/**
 * Class TokimonTrainer. Represents a TokimonTrainer object which will be used for the player.
 * Has attributes that describe the TokimonTrainer, including its position in the grid. It also has getter and setter methods.
 * @author Alex Ramirez
 * @version 1.0
 */
public class TokimonTrainer {
    private int numOfSpells = 3;
    private int turnNum = 1;
    private int numOfTokisCollected = 0;
    private String tokiTrainerSymbol = "@";
    private int[] posOfTokiTrainer = new int[2];

    public int getNumOfSpells() {
        return numOfSpells;
    }

    public void decrementNumOfSpells() {
        numOfSpells--;
    }

    public int getTurnNum() {
        return turnNum;
    }

    public int getNumOfTokisCollected() {
        return numOfTokisCollected;
    }

    public void incrementNumOfTokisCollected() {
        numOfTokisCollected++;
    }

    public void setTokiTrainerRow( int row ) {
        posOfTokiTrainer[0] = row;
    }

    public void setTokiTrainerCol( int col ) {
        posOfTokiTrainer[1] = col;
    }

    public int getPosOfTokiTrainerRow() {
        return posOfTokiTrainer[0];
    }
    
    public int getPosOfTokiTrainerCol() {
        return posOfTokiTrainer[1];
    }

    public void incrementTurnNum() {
        turnNum++;
    }

    public void decrementTokiTrainerRow() {
        posOfTokiTrainer[0]--;
    }

    public void incrementTokiTrainerRow() {
        posOfTokiTrainer[0]++;
    }

    public void decrementTokiTrainerCol() {
        posOfTokiTrainer[1]--;
    }

    public void incrementTokiTrainerCol() {
        posOfTokiTrainer[1]++;
    }

    public String getTokiTrainerSymbol() {
        return tokiTrainerSymbol;
    }

}
