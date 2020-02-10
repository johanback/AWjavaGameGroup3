import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class GFX {

    public static final char player = '\u25B6';
    public final char playerBody = '\u25E8';
    public final char enemy = '\u25E5';
    public final char enemyBody = '\u25E9';
    public final char Hp2 = '\u2665';
    public final char playerProjectile = '\u25AC';
    public final char enemyProjectile = '\u2BC2';
    public final TextColor normalColor = new TextColor.RGB(0, 255, 26);
    public final TextColor playerColor = new TextColor.RGB(255, 255, 0);
    public final TextColor enemyColor = new TextColor.RGB(125, 0, 100);
    public final TextColor starColor1 = new TextColor.RGB(40, 42, 56);
    public final TextColor starColor2 = new TextColor.RGB(32, 32, 36);
    public final TextColor playerLaser = new TextColor.RGB(0, 255, 0);
    public final TextColor enemyLaser = new TextColor.RGB(255, 0, 0);
    public final TextColor damageTaken = new TextColor.RGB(255, 0, 0);
    public final TextColor healingTaken = new TextColor.RGB(0, 255, 0);
    public final TextColor blackBg = new TextColor.RGB(0, 0, 0);
    public final TextColor hudColor = new TextColor.RGB(255, 255, 255);
    public final TextColor heartColor = new TextColor.RGB(212, 175, 55);
    public static final TextColor fade = new TextColor.RGB(192, 43, 137);
    public static final TextColor fade1 = new TextColor.RGB(198, 46, 131);
    public static final TextColor fade2 = new TextColor.RGB(196, 61, 122);
    public static final TextColor fade3 = new TextColor.RGB(197, 69, 107);
    public static final TextColor fade4 = new TextColor.RGB(194, 78, 96);
    public static final TextColor fade5 = new TextColor.RGB(204, 94, 90);
    public static final TextColor fade6 = new TextColor.RGB(204, 101, 70);
    public static final TextColor fade7 = new TextColor.RGB(196, 117, 65);
    public static final TextColor fade8 = new TextColor.RGB(203, 133, 55);
    public static final TextColor fade9 = new TextColor.RGB(199, 141, 38);
    public static final TextColor fade10 = new TextColor.RGB(197, 150, 41);
    public static final TextColor fade11 = new TextColor.RGB(194, 153, 23);
    public static final TextColor fade12 = new TextColor.RGB(197, 165, 22);

    public static void renderGameOver(Terminal terminal) throws IOException {
        String go1 = "  _______      ___      .___  ___.  _______   ";
        String go2 = " /  _____|    /   \\     |   \\/   | |   ____|  ";
        String go3 = "|  |  __     /  ^  \\    |  \\  /  | |  |__     ";
        String go4 = "|  | |_ |   /  /_\\  \\   |  |\\/|  | |   __|    ";
        String go5 = "|  |__| |  /  _____  \\  |  |  |  | |  |____   ";
        String go6 = " \\______| /__/     \\__\\ |__|  |__| |_______|  ";
        String go7 = "  ______   ____    ____  _______ .______      ";
        String go8 = " /  __  \\  \\   \\  /   / |   ____||   _  \\     ";
        String go9 = "|  |  |  |  \\   \\/   /  |  |__   |  |_)  |    ";
        String go10 = "|  |  |  |   \\      /   |   __|  |      /     ";
        String go11 = "|  `--'  |    \\    /    |  |____ |  |\\  \\----.";
        String go12 = " \\______/      \\__/     |_______|| _| `._____|";
        for (int y = 0; y < go1.length(); y++) {
            terminal.setForegroundColor(fade);
            terminal.setCursorPosition(y + 9, 3);
            terminal.putCharacter(go1.charAt(y));
            terminal.setForegroundColor(fade1);
            terminal.setCursorPosition(y + 9, 4);
            terminal.putCharacter(go2.charAt(y));
            terminal.setForegroundColor(fade2);
            terminal.setCursorPosition(y + 9, 5);
            terminal.putCharacter(go3.charAt(y));
            terminal.setForegroundColor(fade3);
            terminal.setCursorPosition(y + 9, 6);
            terminal.putCharacter(go4.charAt(y));
            terminal.setForegroundColor(fade4);
            terminal.setCursorPosition(y + 9, 7);
            terminal.putCharacter(go5.charAt(y));
            terminal.setForegroundColor(fade5);
            terminal.setCursorPosition(y + 9, 8);
            terminal.putCharacter(go6.charAt(y));
            terminal.setForegroundColor(fade6);
            terminal.setCursorPosition(y + 9, 9);
            terminal.putCharacter(go7.charAt(y));
            terminal.setForegroundColor(fade7);
            terminal.setCursorPosition(y + 9, 10);
            terminal.putCharacter(go8.charAt(y));
            terminal.setForegroundColor(fade8);
            terminal.setCursorPosition(y + 9, 11);
            terminal.putCharacter(go9.charAt(y));
            terminal.setForegroundColor(fade9);
            terminal.setCursorPosition(y + 9, 12);
            terminal.putCharacter(go10.charAt(y));
            terminal.setForegroundColor(fade10);
            terminal.setCursorPosition(y + 9, 13);
            terminal.putCharacter(go11.charAt(y));
            terminal.setForegroundColor(fade11);
            terminal.setCursorPosition(y + 9, 14);
            terminal.putCharacter(go12.charAt(y));
        }
    }

    public static void renderTitle(Terminal terminal) throws IOException {
        String t1 = " __    __  .__   __.  __    ______   ______    _______   _______ ";
        String t2 = "|  |  |  | |  \\ |  | |  |  /      | /  __  \\  |       \\ |   ____|";
        String t3 = "|  |  |  | |   \\|  | |  | |  ,----'|  |  |  | |  .--.  ||  |__   ";
        String t4 = "|  |  |  | |  . `  | |  | |  |     |  |  |  | |  |  |  ||   __|  ";
        String t5 = "|  `--'  | |  |\\   | |  | |  `----.|  `--'  | |  '--'  ||  |____ ";
        String t6 = " \\______/  |__| \\__| |__|  \\______| \\______/  |_______/ |_______|";
        String t7 = "                                                                 ";
        String t8 = "      ____    __    ____  ___      .______          _______.     ";
        String t9 = "      \\   \\  /  \\  /   / /   \\     |   _  \\        /       |     ";
        String t10 = "       \\   \\/    \\/   / /  ^  \\    |  |_)  |      |   (----`     ";
        String t11 = "        \\            / /  /_\\  \\   |      /        \\   \\         ";
        String t12 = "         \\    /\\    / /  _____  \\  |  |\\  \\----.----)   |        ";
        String t13 = "          \\__/  \\__/ /__/     \\__\\ | _| `._____|_______/         ";
        for (int y = 0; y < t1.length(); y++) {

            terminal.setForegroundColor(fade);
            terminal.setCursorPosition(y + 7, 1);
            terminal.putCharacter(t1.charAt(y));
            terminal.setForegroundColor(fade1);
            terminal.setCursorPosition(y + 7, 2);
            terminal.putCharacter(t2.charAt(y));
            terminal.setForegroundColor(fade2);
            terminal.setCursorPosition(y + 7, 3);
            terminal.putCharacter(t3.charAt(y));
            terminal.setForegroundColor(fade3);
            terminal.setCursorPosition(y + 7, 4);
            terminal.putCharacter(t4.charAt(y));
            terminal.setForegroundColor(fade4);
            terminal.setCursorPosition(y + 7, 5);
            terminal.putCharacter(t5.charAt(y));
            terminal.setForegroundColor(fade5);
            terminal.setCursorPosition(y + 7, 6);
            terminal.putCharacter(t6.charAt(y));
            terminal.setForegroundColor(fade6);
            terminal.setCursorPosition(y + 7, 7);
            terminal.putCharacter(t7.charAt(y));
            terminal.setForegroundColor(fade7);
            terminal.setCursorPosition(y + 7, 8);
            terminal.putCharacter(t8.charAt(y));
            terminal.setForegroundColor(fade8);
            terminal.setCursorPosition(y + 7, 9);
            terminal.putCharacter(t9.charAt(y));
            terminal.setForegroundColor(fade9);
            terminal.setCursorPosition(y + 7, 10);
            terminal.putCharacter(t10.charAt(y));
            terminal.setForegroundColor(fade10);
            terminal.setCursorPosition(y + 7, 11);
            terminal.putCharacter(t11.charAt(y));
            terminal.setForegroundColor(fade11);
            terminal.setCursorPosition(y + 7, 12);
            terminal.putCharacter(t12.charAt(y));
            terminal.setForegroundColor(fade12);
            terminal.setCursorPosition(y + 7, 13);
            terminal.putCharacter(t13.charAt(y));
        }
    }


}
