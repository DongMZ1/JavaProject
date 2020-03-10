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
		
		for(int i = 14; i < 17; i++)
			Tile.get(12).addGrids(gridMap[i][7]);
		
		for(int i = 17; i < 21; i++)
			Tile.get(13).addGrids(gridMap[i][9], gridMap[i][10]);
		
		for(int i = 12; i < 14; i++)
			Tile.get(14).addGrids(gridMap[i][12], gridMap[i][13], gridMap[i][14], gridMap[i][15]);
		
		for(int i = 3; i < 6; i++)
			Tile.get(15).addGrids(gridMap[i][1]);
		
		for(int i = 20; i < 22; i++)
			Tile.get(16).addGrids(gridMap[i][12], gridMap[i][13], gridMap[i][14]);
		
		for(int i = 14; i < 19; i++)
			Tile.get(17).addGrids(gridMap[i][13], gridMap[i][14], gridMap[i][15]);
		
		for(int i = 12; i < 16; i++)
			Tile.get(18).addGrids(gridMap[i][17], gridMap[i][18]);
		
		for(int i = 6; i < 8; i++)
			Tile.get(19).addGrids(gridMap[i][15], gridMap[i][16]);
		Tile.get(19).addGrids(gridMap[8][15], gridMap[8][16], gridMap[8][17]);
		
		for(int i = 3; i < 5; i++)
			Tile.get(20).addGrids(gridMap[i][14], gridMap[i][15], gridMap[i][16]);
		
		for(int i = 0; i < 2; i++)
			Tile.get(21).addGrids(gridMap[i][13], gridMap[i][14], gridMap[i][15], gridMap[i][16]);
		Tile.get(21).addGrids(gridMap[2][13], gridMap[2][14]);

		Tile.get(22).addGrids(gridMap[2][17]);
		for(int i = 3; i < 5; i++)
			Tile.get(22).addGrids(gridMap[i][17], gridMap[i][18]);
		Tile.get(22).addGrids(gridMap[4][19]);
		Tile.get(22).addGrids(gridMap[5][18]);

		Tile.get(23).addGrids(gridMap[3][22]);
		Tile.get(23).addGrids(gridMap[4][21], gridMap[4][22]);
		for(int i = 5; i < 7; i++)
			Tile.get(23).addGrids(gridMap[i][20], gridMap[i][21], gridMap[i][22]);
		Tile.get(23).addGrids(gridMap[7][18], gridMap[7][19], gridMap[7][20], gridMap[8][21]);
		
		Tile.get(24).addGrids(gridMap[0][18], gridMap[0][19], gridMap[0][20]);
		for(int i = 1; i < 3; i++)
			Tile.get(24).addGrids(gridMap[i][19], gridMap[i][20], gridMap[i][21]);
		
		for(int i = 0; i < 3; i++)
			Tile.get(25).addGrids(gridMap[i][23], gridMap[i][24], gridMap[i][25]);
		
		for(int i = 0; i < 2; i++)
			Tile.get(27).addGrids(gridMap[i][26], gridMap[i][27], gridMap[i][28], gridMap[i][29], gridMap[i][30]);
		
		for(int i = 15; i < 20; i++)
			Tile.get(28).addGrids(gridMap[i][20], gridMap[i][21]);
		
		for(int i = 12; i < 16; i++)
			Tile.get(29).addGrids(gridMap[i][22], gridMap[i][23]);
		
		for(int i = 1; i < 15; i++)
			Tile.get(30).addGrids(gridMap[i][25], gridMap[i][26]);
		
		Tile.get(31).addGrids(gridMap[4][24], gridMap[4][25], gridMap[4][26], gridMap[4][27]);
		Tile.get(31).addGrids(gridMap[5][27], gridMap[5][28], gridMap[5][29]);
		Tile.get(31).addGrids(gridMap[6][28], gridMap[6][29]);
		
		for(int i = 24; i < 27; i++)
			Tile.get(32).addGrids(gridMap[i][13], gridMap[i][14], gridMap[i][15]);
		
		for(int i = 21; i < 25; i++)
			Tile.get(33).addGrids(gridMap[i][17], gridMap[i][18], gridMap[i][19]);
		
		Tile.get(34).addGrids(gridMap[8][22]);
		for(int i = 9; i < 11; i++)
			Tile.get(34).addGrids(gridMap[i][21], gridMap[i][22], gridMap[i][23]);

		for(int i = 6; i < 8; i++)
			Tile.get(35).addGrids(gridMap[i][24], gridMap[i][25]);
		Tile.get(35).addGrids(gridMap[8][24]);
		
		for(int i = 17; i < 20; i++)
			Tile.get(36).addGrids(gridMap[i][17], gridMap[i][18]);
		
		for(int i = 11; i < 15; i++)
			Tile.get(37).addGrids(gridMap[i][29], gridMap[i][30], gridMap[i][31]);

		for(int i = 21; i < 25; i++)
			Tile.get(38).addGrids(gridMap[i][17], gridMap[i][18], gridMap[i][19]);

		for(int i = 25; i < 30; i++)
			Tile.get(39).addGrids(gridMap[i][20], gridMap[i][21], gridMap[i][22]);
		
		for(int i = 19; i < 25; i++)
			Tile.get(40).addGrids(gridMap[i][23]);
		for(int i = 20; i < 23; i++)
			Tile.get(40).addGrids(gridMap[i][22]);

		for(int i = 14; i < 19; i++)
			Tile.get(41).addGrids(gridMap[i][24], gridMap[i][25]);
		
		
		

		

		
		
		
		
		
		
	}
}





















