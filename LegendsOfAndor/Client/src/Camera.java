import org.minueto.MinuetoFileException;

public class Camera {

	private static Camera camera;
	public int boardZoom;
	public Coordinate currentPos;
	private int bottomOfBoard;
	private int edgeOfBoard;
	private GameStatus gameStatus;
	private Camera() throws MinuetoFileException {
		boardZoom = 3;
		currentPos = new Coordinate(0, 0);
		bottomOfBoard = Constants.TOP_OF_BOARD / boardZoom;
		edgeOfBoard = Constants.SIDE_OF_BOARD / boardZoom;
		gameStatus = GameStatus.getInstance();
	}

	public static Camera getInstance() throws MinuetoFileException {
		if(camera == null)
			camera = new Camera();
		return camera;
	}

	public void moveCamera(int toMoveX, int toMoveY) {
		currentPos.setPos(currentPos.getX() + toMoveX, currentPos.getY() + toMoveY);
		if(currentPos.getX() < edgeOfBoard + gameStatus.screenWidth)
			currentPos.setPos(edgeOfBoard + gameStatus.screenWidth, currentPos.getY());
		else if(currentPos.getX() > 0)
			currentPos.setPos(0, currentPos.getY());
		if(currentPos.getY() < bottomOfBoard + gameStatus.screenHeight - Constants.BOTTOM_SCREEN_SPACE)
			currentPos.setPos(currentPos.getX(), bottomOfBoard + gameStatus.screenHeight - Constants.BOTTOM_SCREEN_SPACE);
		else if(currentPos.getY() > 0)
			currentPos.setPos(currentPos.getX(), 0);

	}

	public void zoomIn() {
		if(boardZoom < 5)
			setBoardZoom(boardZoom + 1);
	}

	public void zoomOut() {
		if(boardZoom > 2)
			setBoardZoom(boardZoom - 1);
	}

	private void setBoardZoom(int boardZoom) {
		this.boardZoom = boardZoom;
		bottomOfBoard = Constants.TOP_OF_BOARD / boardZoom;
		edgeOfBoard = Constants.SIDE_OF_BOARD / boardZoom;
		moveCamera(currentPos.getX(), currentPos.getY());
	}

	/**
	 * @param boardCoords Coordinates on board
	 * @return Coordinate board coordinates on screen position
	 */
	public Coordinate getPosOnScreen(Coordinate boardCoords) {
		return new Coordinate(boardCoords.getX()/boardZoom + currentPos.getX(), boardCoords.getY()/boardZoom + currentPos.getY());
	}
	/**
	 * @return Coordinate board coordinates on screen position
	 */
	public Coordinate getPosOnScreen(int x, int y) {
		return new Coordinate(x/boardZoom + currentPos.getX(), y/boardZoom + currentPos.getY());
	}

	public Coordinate getPosOnBoard(int x, int y) {
		return new Coordinate((x - currentPos.getX())*boardZoom, (y - currentPos.getY())*boardZoom);
	}
}
