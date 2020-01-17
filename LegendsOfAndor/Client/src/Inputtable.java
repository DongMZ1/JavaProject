public interface Inputtable {

	void handleKeyPress(int key);
	void handleKeyRelease(int key);
	void handleKeyType(char c);
	void handleMousePress(int x, int y, int button);
	void handleMouseRelease(int x, int y, int button);
	void handleMouseMove(int x, int y);
	void handleMouseWheelRotate(int rotation);

}
