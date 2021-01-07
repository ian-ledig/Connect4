package connect.gamerule;

public abstract class GameRule {

    private final int columnNumber;
    private final int rowNumber;
    private final int chipsToWin;
    private final boolean fillGrid;
    private final boolean removeChip;
    private final boolean sliders;

    public GameRule(int columnNumber, int rowNumber, int chipsToWin, boolean fillGrid, boolean removeChip, boolean sliders){
        this.columnNumber = columnNumber;
        this.rowNumber = rowNumber;
        this.chipsToWin = chipsToWin;
        this.fillGrid = fillGrid;
        this.removeChip = removeChip;
        this.sliders = sliders;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getChipsToWin() {
        return chipsToWin;
    }

    public boolean isFillGrid() {
        return fillGrid;
    }

    public boolean isRemoveChip() {
        return removeChip;
    }

    public boolean isSliders() {
        return sliders;
    }
}
