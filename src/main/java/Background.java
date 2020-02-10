import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Background {
    ArrayList<Position> starsBG = new ArrayList<>();
    ArrayList<Position> starsBG2 = new ArrayList<>();
    static GFX gfx = new GFX();
    static Random random = new Random();

    public void moveAndRenderStars(Terminal terminal) throws IOException {
        Position starToBeRemoved = null;
        Position star2ToBeRemoved = null;
        for (Position p : starsBG) {
            if (p.getxPos() >= 0) {
                if (Game.getGameTime() % 1 == 0) {
                    p.setxPos(p.getxPos() - 1);
                }
                if (p.getxPos() < 80) {
                    terminal.setForegroundColor(gfx.starColor1);
                    terminal.setCursorPosition(p.getxPos(), p.getyPos());
                    terminal.putCharacter('\u2737');
                }
            }
            if (p.getxPos() < 0) {
                starToBeRemoved = p;
            }
        }
        starsBG.remove(starToBeRemoved);
        for (Position p : starsBG2) {
            if (p.getxPos() >= 0) {
                if (Game.getGameTime() % 2 == 0) {
                    p.setxPos(p.getxPos() - 1);
                }
                if (p.getxPos() < 80) {
                    terminal.setForegroundColor(gfx.starColor2);
                    terminal.setCursorPosition(p.getxPos(), p.getyPos());
                    terminal.putCharacter('\u2726');
                }
            }
            if (p.getxPos() < 0) {
                star2ToBeRemoved = p;
            }
        }
        starsBG2.remove(star2ToBeRemoved);
        if (starsBG.size() < 20) {
            starsBG.add(new Position(random.nextInt(85) + 85, random.nextInt(25)));
        }
        if (starsBG2.size() < 10) {
            starsBG2.add(new Position(random.nextInt(85) + 85, random.nextInt(25)));
        }
    }

    public void generateStars() {
        for (int i = 0; i < 20; i++) {
            starsBG.add(new Position(random.nextInt(85) + 85, random.nextInt(25)));
        }
        for (int i = 0; i < 10; i++) {
            starsBG2.add(new Position(random.nextInt(85) + 85, random.nextInt(25)));
        }
    }
}
