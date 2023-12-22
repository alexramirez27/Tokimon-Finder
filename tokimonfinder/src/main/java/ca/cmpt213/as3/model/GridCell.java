package ca.cmpt213.as3.model;

/**
 * Class GridCell. Represents one cell in the Grid which contains some data.
 * Has several attributes and methods. Most of these methods are getters and setters. Also contains an Enum type.
 * @author Alex Ramirez
 * @version 1.0
 */
public class GridCell {
    public enum CellType {
        ROWHEADER,
        COLUMNHEADER,
        TOKIMON,
        FOKIMON,
        TOKIMONTRAINER,
        UNVISITEDLOCATION,
        VISITEDLOCATION
    }

    private CellType cellType;
    private String cellContent;
    private boolean occupiedStatus = false;
    private boolean visitedCellStatus = false;
    
    public void setCellType( CellType aCellType ) {
        cellType = aCellType;
    }

    public CellType getCellType() {
        return cellType;
    }

    public void setCellContent( String content ) {
        cellContent = content;
    }

    public String getCellContent() {
        return cellContent;
    }

    public void occupyCell() {
        occupiedStatus = true;
    }

    public boolean getOccupiedStatus() {
        return occupiedStatus;
    }

    public void visitedCell() {
        visitedCellStatus = true;
    }

    public boolean getVisitedCellStatus() {
        return visitedCellStatus;
    }

}
