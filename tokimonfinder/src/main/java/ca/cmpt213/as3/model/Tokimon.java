package ca.cmpt213.as3.model;

/**
 * Class Tokimon. Represents a Tokimon object.
 * Has attributes that describe the Tokimon, including its position in the grid. It also has getter and setter methods.
 * @author Alex Ramirez
 * @version 1.0
 */
public class Tokimon {
    private int[] posOfToki = new int[2];
    private String symbolOfToki = "$";

    public void setRowOfToki( int row ) {
        posOfToki[0] = row;
    }

    public void setColOfToki( int col ) {
        posOfToki[1] = col;
    }

    public int getRowOfToki() {
        return posOfToki[0];
    }

    public int getColOfToki() {
        return posOfToki[1];
    }

    public String getTokiSymbol() {
        return symbolOfToki;
    }
}
