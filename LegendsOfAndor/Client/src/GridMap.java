import org.minueto.MinuetoFileException;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoImageFile;

public class GridMap {

	private Grid[][] gridMap;
    private MinuetoImageFile boardFile;
    private MinuetoImage croppedBoard;
    private int gridWidth;
    private int gridHeight;
    private int numRows;
    private int numCols;
    
	
	public GridMap(MinuetoImageFile board, int gridWidth, int gridHeight) throws MinuetoFileException {
			this.boardFile = boardFile;	
			this.gridWidth = gridWidth;
			this.gridHeight = gridHeight;
	}
	
	public void preProcessFile() {
		int newWidth = (int)(boardFile.getWidth() / gridWidth) * gridWidth;
		int newHeight = (int)(boardFile.getHeight() / gridHeight) * gridHeight;
		croppedBoard = boardFile.crop(0, 0, newWidth, newHeight);
		numCols = (int)(newWidth / gridWidth);
		numRows = (int)(newHeight / gridHeight);
		
	}
	

	public void createGrids(int width, int height) {
		// TODO map grids to tiles they are contained in
		// unfortunately manually done. use paint processing software 
		// to visualize grids in the board
		
		int cornerX, cornerY;
		for (int  i = 0; i < numRows; i++) {
			cornerX = i * gridWidth;
			for (int j = 0; j < numCols; j++) {
				cornerY = j * gridHeight;
				gridMap[i][j] = new Grid(croppedBoard.crop(cornerX, cornerY, gridWidth, gridHeight));
			}
		}
	}
	
	public void mapGrids() {
		
		for(int i = 2; i < 6; i++)
			Tile.get(0).addGrids(gridMap[i][6], gridMap[i][7], gridMap[i][8], gridMap[i][9]);
		for(int i = 6; i < 8; i++)
			Tile.get(0).addGrids(gridMap[i][5], gridMap[i][6], gridMap[i][7], gridMap[i][8]);
		for(int i = 8; i < 10; i++)
			Tile.get(0).addGrids(gridMap[i][6], gridMap[i][7], gridMap[i][8]);
		
		Tile.get(1).addGrids(gridMap[1][10], gridMap[1][11]);
		
		for(int i = 9; i < 11; i++)
			Tile.get(2).addGrids(gridMap[i][10], gridMap[i][11]);
		Tile.get(2).addGrids(gridMap[11][11]);

		for(int i = 7; i < 11; i++)
			Tile.get(3).addGrids(gridMap[i][13]);
		
		Tile.get(4).addGrids(gridMap[3][12]);
		for(int i = 4; i < 6; i++)
			Tile.get(4).addGrids(gridMap[i][11], gridMap[i][12]);
		
		for(int i = 0; i < 2; i++)
			Tile.get(5).addGrids(gridMap[i][10], gridMap[i][11]);
		Tile.get(5).addGrids(gridMap[2][11]);
		
		for(int i = 10; i < 13; i++)
			Tile.get(6).addGrids(gridMap[i][9]);
		for(int i = 13; i < 16; i++)
			Tile.get(6).addGrids(gridMap[i][10]);
		
		Tile.get(7).addGrids(gridMap[5][2], gridMap[5][3], gridMap[5][4]);
		Tile.get(7).addGrids(gridMap[6][2]);
		Tile.get(7).addGrids(gridMap[7][3], gridMap[7][4]);
		
		Tile.get(8).addGrids(gridMap[9][3], gridMap[9][4]);
		for(int i = 10; i < 13; i++)
			Tile.get(8).addGrids(gridMap[i][4]);
		
		Tile.get(9).addGrids(gridMap[7][1], gridMap[8][1]);
		
		Tile.get(10).addGrids(gridMap[9][15], gridMap[10][15]);
		
		for(int i = 10; i < 12; i++)
			Tile.get(11).addGrids(gridMap[i][6], gridMap[i][7]);
		Tile.get(11).addGrids(gridMap[12][6], gridMap[12][7], gridMap[12][8]);
		
		
	}
}





















