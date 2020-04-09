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
	/*
	public void mapGridsToTiles() {
		
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

		for(int i = 28; i < 32; i++)
			Tile.get(42).addGrids(gridMap[i][18]);
		
		for(int i = 32; i < 34; i++)
			Tile.get(43).addGrids(gridMap[i][19], gridMap[i][20], gridMap[i][21], gridMap[i][22]);
		
		for(int i = 32; i < 34; i++)
			Tile.get(44).addGrids(gridMap[i][17]);
		for(int i = 27; i < 33; i++)
			Tile.get(44).addGrids(gridMap[i][16]);
		
		for(int i = 34; i < 36; i++)
			Tile.get(45).addGrids(gridMap[i][15], gridMap[i][16], gridMap[i][17]);
		
		for(int i = 31; i < 34; i++)
			Tile.get(46).addGrids(gridMap[i][15]);
		for(int i = 28; i < 33; i++)
			Tile.get(46).addGrids(gridMap[i][13], gridMap[i][14]);
		Tile.get(46).addGrids(gridMap[33][14]);
		
		for(int i = 29; i < 33; i++)
			Tile.get(47).addGrids(gridMap[i][9], gridMap[i][10], gridMap[i][11]);
		
		for(int i = 26; i < 28; i++)
			Tile.get(48).addGrids(gridMap[i][8], gridMap[i][9], gridMap[i][10], gridMap[i][11]);
		for(int i = 24; i < 26; i++)
			Tile.get(48).addGrids(gridMap[i][7], gridMap[i][8], gridMap[i][9], gridMap[i][10]);
		
		for(int i = 21; i < 23; i++)
			Tile.get(49).addGrids(gridMap[i][6], gridMap[i][7], gridMap[i][8]);
		for(int i = 18; i < 21; i++)
			Tile.get(49).addGrids(gridMap[i][5], gridMap[i][6]);
	
		Tile.get(50).addGrids(gridMap[24][6]);
		for(int i = 23; i < 25; i++)
			Tile.get(50).addGrids(gridMap[i][5]);
		for(int i = 21; i < 24; i++)
			Tile.get(50).addGrids(gridMap[i][4]);
		
		for(int i = 28; i < 30; i++)
			Tile.get(51).addGrids(gridMap[i][5], gridMap[i][6]);
		Tile.get(51).addGrids(gridMap[27][6]);

		for(int i = 26; i < 28; i++)
			Tile.get(52).addGrids(gridMap[i][4]);
		for(int i = 24; i < 27; i++)
			Tile.get(52).addGrids(gridMap[i][3]);
		
		for(int i = 29; i < 32; i++)
			Tile.get(53).addGrids(gridMap[i][7], gridMap[i][8]);
		
		for(int i = 32; i < 35; i++)
			Tile.get(54).addGrids(gridMap[i][7]);
		Tile.get(54).addGrids(gridMap[33][8]);
		for(int i = 32; i < 34; i++)
			Tile.get(54).addGrids(gridMap[i][7]);
		
		for(int i = 30; i < 34; i++)
			Tile.get(55).addGrids(gridMap[i][2], gridMap[i][3], gridMap[i][4]);
		
		for(int i = 34; i < 37; i++)
			Tile.get(56).addGrids(gridMap[i][9], gridMap[i][10], gridMap[i][11]);
		
		for(int i = 36; i < 40; i++)
			Tile.get(57).addGrids(gridMap[i][5], gridMap[i][6], gridMap[i][7]);
		
		for(int i = 43; i < 45; i++)
			Tile.get(58).addGrids(gridMap[i][6], gridMap[i][7], gridMap[i][8]);
		
		for(int i = 40; i < 43; i++)
			Tile.get(59).addGrids(gridMap[i][3], gridMap[i][4], gridMap[i][5]);
		
		for(int i = 43; i < 46; i++)
			Tile.get(60).addGrids(gridMap[i][2], gridMap[i][3], gridMap[i][4]);
		
		for(int i = 44; i < 47; i++)
			Tile.get(61).addGrids(gridMap[i][9], gridMap[i][10], gridMap[i][11], gridMap[i][12]);	
		
		for(int i = 45; i < 47; i++)
			Tile.get(62).addGrids(gridMap[i][6], gridMap[i][7], gridMap[i][8]);
		
		for(int i = 38; i < 43; i++)
			Tile.get(63).addGrids(gridMap[i][9], gridMap[i][10], gridMap[i][11]);
		
		for(int i = 35; i < 43; i++)
			Tile.get(64).addGrids(gridMap[i][12], gridMap[i][13], gridMap[i][14]);
		
		for(int i = 38; i < 41; i++)
			Tile.get(65).addGrids(gridMap[i][16], gridMap[i][17]);
		
		for(int i = 41; i < 43; i++)
			Tile.get(66).addGrids(gridMap[i][30]);
		for(int i = 42; i < 44; i++)
			Tile.get(66).addGrids(gridMap[i][21]);
		
		Tile.get(67).addGrids(gridMap[41][22]);
		for(int i = 42; i < 44; i++)
			Tile.get(67).addGrids(gridMap[i][23]);
		
		Tile.get(68).addGrids(gridMap[40][24]);
		for(int i = 41; i < 43; i++)
			Tile.get(68).addGrids(gridMap[i][24], gridMap[i][25]);
		
		for(int i = 38; i < 40; i++)
			Tile.get(69).addGrids(gridMap[i][25], gridMap[i][26]);
		Tile.get(69).addGrids(gridMap[40][26]);
		
		for(int i = 38; i < 40; i++)
			Tile.get(70).addGrids(gridMap[i][27], gridMap[i][28]);
		
		for(int i = 35; i < 37; i++)
			Tile.get(71).addGrids(gridMap[i][21], gridMap[i][22]);
		Tile.get(71).addGrids(gridMap[36][20]);

		for(int i = 9; i < 12; i++)
			Tile.get(72).addGrids(gridMap[i][18], gridMap[i][19], gridMap[i][20]);
		Tile.get(72).addGrids(gridMap[12][19], gridMap[12][20]);

		for(int i = 42; i < 46; i++)
			Tile.get(80).addGrids(gridMap[i][27], gridMap[i][28], gridMap[i][29], gridMap[i][30]);
		
		for(int i = 37; i < 39; i++)
			Tile.get(81).addGrids(gridMap[i][29], gridMap[i][30]);
		Tile.get(81).addGrids(gridMap[39][30]);

		for(int i = 34; i < 37; i++)
			Tile.get(82).addGrids(gridMap[i][30], gridMap[i][31]);

		for(int i = 44; i < 47; i++)
			Tile.get(83).addGrids(gridMap[i][16], gridMap[i][17], gridMap[i][18]);
		
		for(int i = 32; i < 35; i++)
			Tile.get(84).addGrids(gridMap[i][27], gridMap[i][28], gridMap[i][29]);
			
	}
	 */
}





















