import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

public class Menu {
    static Terminal terminal;
    static boolean menuRunning = true;
    static ArrayList<Position> selections = new ArrayList<>();
    static int currentSelection = 0;
    static KeyType type = null;
    static TextColor selectionColor = new TextColor.RGB(143, 33, 106);
    static TextColor defaultColor = new TextColor.RGB(0, 0, 0);
    MP3Player menuMusic = new MP3Player();
    MP3Player menuSFX = new MP3Player();
    MP3Player menuQuitSoundBecauseShitIsFucked = new MP3Player();
    MP3Player owenWilsonMachine = new MP3Player();
    public Menu(Terminal terminal) throws Exception {
        this.terminal = terminal;
    }
    static String theHighScore;
    static GFX gfx = new GFX();


    public void initializeMenu() throws Exception {
        terminal.clearScreen();
        terminal.setCursorVisible(false);
        selections.add(new Position(34, 16));
        selections.add(new Position(34, 18));
        selections.add(new Position(34, 20));
        menuMusic.play("intro_music.mp3");
        GFX.renderTitle(terminal);
        renderButtons();
        terminal.flush();

    }

    public void menuLoop() throws Exception {
        while (menuRunning) {
            Thread.sleep(5);
            terminal.clearScreen();
            playerInput();
            Game.background.moveAndRenderStars(terminal);
            GFX.renderTitle(terminal);
            renderButtons();
            menuSelection();
            updateHighScore();
            printHighScore(theHighScore);
            terminal.flush();
        }
    }

    public static void updateHighScore() throws Exception {
        RandomAccessFile r = new RandomAccessFile("t.text", "rw");
        if(r.length() > 0)
            Game.highScore = r.readInt();
        if(Game.score > Game.highScore){
            r.seek(0);
            r.writeInt(Game.score);
        }
        r.close();
        theHighScore = String.valueOf(Game.highScore);
    }

    private static void printHighScore(String s) throws Exception {
        for (int i = 0 ; i < s.length() ; i++) {
            terminal.setCursorPosition(i+48, 18);
            terminal.putCharacter(s.charAt(i));
        }
    }

    private void selectOption() throws Exception {
        switch(currentSelection){
            case 0:
                menuMusic.stopAll();
                menuSFX.play("merge_from_ofoct.mp3");
                Thread.sleep(2500);
                Main.game.initializeGame();
                Main.game.mainGameLoop();
                initializeMenu();
                break;
            case 1:
                owenWilsonMachine.play("owenwowson5.mp3");
                break;
            case 2:
                menuMusic.stopAll();
                menuQuitSoundBecauseShitIsFucked.playFX("explosion-player2.mp3");
                Thread.sleep(1000);
                terminal.close();
                menuRunning = false;
                Main.programRunning = false;
                break;
            default:
                break;
        }
    }



    public void menuSelection() throws Exception {
        if (type == KeyType.ArrowDown){
            menuSFX.play("button-3.mp3");
            if (currentSelection == 2)
                currentSelection = 0;
            else
                currentSelection++;
        } else if (type == KeyType.ArrowUp){
            menuSFX.play("button-3.mp3");
            if (currentSelection == 0)
                currentSelection = 2;
            else
                currentSelection--;
        } else if (type == KeyType.Enter){
            selectOption();
        }
        switch(currentSelection){
            case 0:
                terminal.setCursorPosition(selections.get(0).getxPos(), selections.get(0).getyPos());
                terminal.putCharacter('>');
                break;
            case 1:
                terminal.setCursorPosition(selections.get(1).getxPos(), selections.get(1).getyPos());
                terminal.putCharacter('>');
                break;
            case 2:
            terminal.setCursorPosition(selections.get(2).getxPos(), selections.get(2).getyPos());
            terminal.putCharacter('>');
                break;
        }
    }



    public void playerInput() throws IOException {
        KeyStroke newKeyStroke = terminal.pollInput();
        if (newKeyStroke == null) {
            type = KeyType.Backspace;
            return;
        } else{
            type = newKeyStroke.getKeyType();
        }

    }

    public void renderButtons() throws IOException {
        String play = "Play";
        String hiScore = "High Score:";
        String quit = "Quit";
        for (int i = 0; i < play.length(); i++) {
            if (currentSelection==0){
                terminal.setBackgroundColor(selectionColor);
            } else {
                terminal.setBackgroundColor(defaultColor);
            }
            terminal.setCursorPosition(i+36, 16);
            terminal.putCharacter(play.charAt(i));
        }
        for (int i = 0; i < hiScore.length(); i++) {
            if (currentSelection==1){
                terminal.setBackgroundColor(selectionColor);
            } else {
                terminal.setBackgroundColor(defaultColor);
            }
            terminal.setCursorPosition(i+36, 18);
            terminal.putCharacter(hiScore.charAt(i));
        }
        for (int i = 0; i < quit.length(); i++) {
            if (currentSelection==2){
                terminal.setBackgroundColor(selectionColor);
            } else {
                terminal.setBackgroundColor(defaultColor);
            }
            terminal.setCursorPosition(i+36, 20);
            terminal.putCharacter(quit.charAt(i));
        }
        terminal.setBackgroundColor(defaultColor);
    }




}
