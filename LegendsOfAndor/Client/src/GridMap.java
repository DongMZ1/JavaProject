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
	
	
}
