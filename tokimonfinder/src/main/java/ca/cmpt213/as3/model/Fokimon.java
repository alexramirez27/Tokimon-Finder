package ca.cmpt213.as3.model;

/**
 * Class Fokimon. Represents a Fokimon object.
 * Has attributes that describe the Fokimon, including its position in the grid. It also has getter and setter methods.
 * @author Alex Ramirez
 * @version 1.0
 */
public class Fokimon {
    private int[] posOfFoki = new int[2];
    private String fokiSymbol = "X";

    public void setRowOfFoki( int row ) {
        posOfFoki[0] = row;
    }

    public void setColOfFoki( int col ) {
        posOfFoki[1] = col;
    }

    public int getRowOfFoki() {
        return posOfFoki[0];
    }

    public int getColOfFoki() {
        return posOfFoki[1];
    }

    public String getFokiSymbol() {
        return fokiSymbol;
    }
}
