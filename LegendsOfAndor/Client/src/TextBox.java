import org.minueto.MinuetoColor;
import org.minueto.MinuetoFileException;
import org.minueto.handlers.MinuetoKeyboard;
import org.minueto.handlers.MinuetoKeyboardHandler;
import org.minueto.image.MinuetoFont;
import org.minueto.image.MinuetoImage;
import org.minueto.image.MinuetoRectangle;
import org.minueto.image.MinuetoText;
import org.minueto.window.MinuetoFullscreen;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class TextBox implements Inputtable, Serializable{

    public void addMessage(String sender, String message) {
        pastMessages.add(new Message(sender, message));
    }

    private class Message {
        private String messageSender;
        private String message;
        public Message(String messageSender, String message) {
            this.messageSender = messageSender;
            this.message = message;
        }
        public String getMessage() { return this.message; }
        public String getSender() { return this.messageSender; }
    }
    private String currentTypedText;
    private ArrayList<Message> pastMessages;
    private MinuetoFont font = new MinuetoFont("Helvetica",14, false, false);
    private static GameStatus gameStatus;
    private static TextBox textBox;
    private int width = 450;
    private int inputHeight = 30;
    private int outputHeight = 270;
    public MinuetoImage textboxInput = new MinuetoRectangle(width, inputHeight, MinuetoColor.WHITE, true);
    public MinuetoImage textboxOutput = new MinuetoRectangle(width, outputHeight, new MinuetoColor(0, 0, 0), true);
    public MinuetoImage textboxDivider = new MinuetoRectangle(width, 1, MinuetoColor.BLACK, true);

    private TextBox() throws IOException {
        this.currentTypedText = "";
        this.pastMessages = new ArrayList<>();
        gameStatus = GameStatus.getInstance();
    }

    public static TextBox getInstance() throws IOException {
        if(textBox == null)
            textBox = new TextBox();
        return textBox;
    }

    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return inputHeight + outputHeight;
    }
    
    

    public void draw() {
        Client.screen.draw(textboxInput, 0, gameStatus.screenHeight - inputHeight);
        Client.screen.draw(textboxDivider, 0, gameStatus.screenHeight - inputHeight);
        Client.screen.draw(textboxOutput, 0, gameStatus.screenHeight - inputHeight - outputHeight);
        Client.screen.draw(new MinuetoText(this.currentTypedText , font, MinuetoColor.BLACK), 10,
                gameStatus.screenHeight - inputHeight + 10);
        int counter = 0;
        for(int i = pastMessages.size() - 1; i >= 0 && counter < 10; i--) {
            counter++;
            Client.screen.draw(new MinuetoText(pastMessages.get(i).getSender() + ": " + pastMessages.get(i).getMessage(),
                            font, MinuetoColor.WHITE), 10, gameStatus.screenHeight - inputHeight - (counter * 20));
        }
    }

    
    /*  Previous: 
     * 
     * INPUT: y > -gameStatus.screenHeight && x > -width && y < -gameStatus.screenHeight+inputHeight && x < 0;
     * OUTPUT: y > -gameStatus.screenHeight+inputHeight && x > -width && y < -gameStatus.screenHeight+outputHeight && x < 0;
     */
    
    public boolean inputClicked(int x, int y) {
        return  x < width && y > gameStatus.screenHeight - inputHeight;
    }
    public boolean outputClicked(int x, int y) {
        return !inputClicked(x,y) && x < width  && y > gameStatus.screenHeight - inputHeight - outputHeight; 
        }

    public void handleKeyPress(int i) {}
    public void handleKeyRelease(int i) {}
    public void handleKeyType(char c) {
        if(c == MinuetoKeyboard.KEY_BACKSPACE) {
            if (currentTypedText.length() > 0)
                currentTypedText = currentTypedText.substring(0, currentTypedText.length() - 1);
        }
        else if(c == MinuetoKeyboard.KEY_ENTER) {
            if(!currentTypedText.equals("")) {
                this.pastMessages.add(new Message("You", currentTypedText));
//                InputThread.updateVariable("message", currentTypedText);
                InputThread.updateVariable();
                currentTypedText = "";
            }
        }
        else
            this.currentTypedText += c;
    }
    public void handleMousePress(int x, int y, int button) {
        if(!inputClicked(x, y) && !outputClicked(x, y)) {
            gameStatus.focus = gameStatus.lastFocused;
        }
    }
    public void handleMouseRelease(int x, int y, int button) { }
    public void handleMouseMove(int x, int y) { }
    public void handleMouseWheelRotate(int rotation) { }
}
