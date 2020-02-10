import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    static Random random = new Random();
    static Background background = new Background();
    static Player playerObject = new Player(new Position(5, 5));
    static GFX gfx = new GFX();
    static MP3Player bgMusic = new MP3Player();
    public static MP3Player sfx = new MP3Player();
    static ArrayList<Enemy> enemyList = new ArrayList<>();
    static ArrayList<Position> starPositions = new ArrayList<>();
    static ArrayList<PowerUp> powerUpPositions = new ArrayList<>();
    static ArrayList<Projectile> projectileList = new ArrayList<>();
    static ArrayList<Projectile> enemyProjectileList = new ArrayList<>();
    private static int gameTime = 0;
    static boolean gameRunning = true;
    static KeyType type = null;
    static int score = 0;
    static int highScore = 0;
    static int noOfEnemies = 2;
    static DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
    static Terminal terminal;
    static {
        try {
            terminal = terminalFactory.createTerminal();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Menu createMenu() throws Exception {
        Menu menu = new Menu(terminal);
        return menu;
    }

    public void initializeGame() throws IOException {
        gameRunning = true;
        enemyList.clear();
        enemyProjectileList.clear();
        projectileList.clear();
        gameTime = 0;
        score = 0;
        noOfEnemies = 2;
        playerObject.hp = 3;
        background.generateStars();
        bgMusic.play("Pokemon.mp3", true);
        terminal.setForegroundColor(gfx.normalColor);
        terminal.setCursorVisible(false);
        terminal.setCursorPosition(playerObject.position.getxPos(), playerObject.position.getyPos());
        terminal.putCharacter(gfx.player);
        starPositions.add(new Position(random.nextInt(80), random.nextInt(24)));
        enemyList.add(new Enemy(new Position(85, random.nextInt(5) + 12), 1, 0));
        enemyList.add(new Enemy(new Position(85, random.nextInt(5) + 12), 2, 1));
    }

    public void mainGameLoop() throws Exception {
        while (gameRunning) {
            updateScreen();
        }
    }

    public void updateScreen() throws Exception {
        Thread.sleep(5);
        terminal.clearScreen();
        readPlayerInput();
        movementHandler();
        Enemy.enemyShoot(enemyList, enemyProjectileList);
        renderHandler();
        collisionHandler();
        updateGameTime();
        terminal.flush();
    }

    public void movementHandler() throws IOException {
        playerObject.movement(type, playerObject);
        if (gameTime % 5 == 0)
            Enemy.movement(enemyList);
    }

    private void renderHandler() throws IOException {
        background.moveAndRenderStars(terminal);
        renderEnemies();
        renderPlayer();
        renderPowerUps();
        renderProjectiles();
        renderHUD();
    }

    private void collisionHandler() throws Exception {
        playerProjectileCollisionCheck();
        enemyProjectileCollisionCheck();
        PowerUp.powerUpCollision(powerUpPositions, playerObject);
        projectileRemovalCheck();
    }

    public void updateGameTime() throws IOException {
        gameTime++;
        if (gameTime % 1500 == 0) {
            noOfEnemies++;
        }
    }

    public void projectileRemovalCheck() {
        Projectile projectileToBeRemoved = null;
        for (Projectile p : projectileList) {
            if (p.getxPos() > 80) {
                projectileToBeRemoved = p;
            }
        }
        projectileList.remove(projectileToBeRemoved);

        Projectile enemyProjectileToBeRemoved = null;
        for (Projectile p : enemyProjectileList) {
            if (p.getxPos() < 0) {
                enemyProjectileToBeRemoved = p;
            }
        }
        enemyProjectileList.remove(enemyProjectileToBeRemoved);
    }

    public void playerProjectileCollisionCheck() {
        Enemy enemyToRemove = null;
        Projectile projectileToRemove = null;
        for (Projectile p : projectileList) {
            for (Enemy e : enemyList) {
                if (p.getxPos() == e.position.getxPos() && p.getyPos() == e.position.getyPos()) {
                    sfx.play("enemy_hit2.mp3");
                    enemyToRemove = e;
                    projectileToRemove = p;
                    score++;
                }
                for (Position p2 : e.enemyShape) {
                    if (p.getxPos() == p2.getxPos() && p.getyPos() == p2.getyPos()) {
                        sfx.play("enemy_hit2.mp3");
                        enemyToRemove = e;
                        projectileToRemove = p;
                        score++;

                    }
                }
            }
        }
        if (enemyToRemove != null) {
            enemyList.remove(enemyToRemove);
            int randomHeartSpawn = random.nextInt(20);
            if (randomHeartSpawn == 1)
                powerUpPositions.add(new PowerUp(new Position(enemyToRemove.position.getxPos(), enemyToRemove.position.getyPos())));

        }
        if (projectileToRemove != null) {
            projectileList.remove(projectileToRemove);
        }

        if (enemyList.size() < noOfEnemies) {
            enemyList.add(enemyList.size(), new Enemy(new Position(85, random.nextInt(5) + 12), random.nextInt(2) + 1, random.nextInt(3)));
        }
    }

    public void enemyProjectileCollisionCheck() throws Exception {
        Projectile enemyProjectileToRemove = null;
        for (Projectile p : enemyProjectileList) {
            if (p.getxPos() == playerObject.position.getxPos() && p.getyPos() == playerObject.position.getyPos()) {
                playerObject.hp--;
                sfx.play("explosion-player.mp3");
                screenFlashDamage();
                enemyProjectileToRemove = p;
            }
            for (Position p2 : playerObject.playerAvatar) {
                if (p.getxPos() == p2.getxPos() && p.getyPos() == p2.getyPos()) {
                    playerObject.hp--;
                    sfx.play("explosion-player2.mp3");
                    screenFlashDamage();
                    enemyProjectileToRemove = p;
                }
            }
        }
        if (enemyProjectileToRemove != null)
            enemyProjectileList.remove(enemyProjectileToRemove);
        if (playerObject.hp == 0) {
            gameOver();
        }
    }

    private void gameOver() throws Exception {
        gameRunning = false;
        bgMusic.stopAll();
        sfx.play("game-over-you-failed.mp3");
        terminal.clearScreen();
        gfx.renderGameOver(terminal);
        terminal.flush();
        bgMusic.play("the-sad-piano.mp3");
        Thread.sleep(1000);
        String wordScore = "Score: ";
        for (int i = 0; i < wordScore.length(); i++) {
            terminal.setCursorPosition(20 + i, 17);
            terminal.putCharacter(wordScore.charAt(i));
        }
        String highScore = String.valueOf(score);
        for (int i = 0; i < highScore.length(); i++) {
            terminal.setCursorPosition(27 + i, 17);
            terminal.putCharacter(highScore.charAt(i));
        }
        String str = "Press Enter To Continue.";
        for (int i = 0; i < str.length(); i++) {
            terminal.setCursorPosition(20 + i, 19);
            terminal.putCharacter(str.charAt(i));
        }
        terminal.flush();
        KeyType type = null;
        do {
            KeyStroke newKeyStroke = terminal.pollInput();
            Thread.sleep(5);
            try {
                type = newKeyStroke.getKeyType();
            } catch (NullPointerException e) {
            }
        } while (type != KeyType.Enter);
        bgMusic.stopAll();
    }

    private void screenFlashDamage() throws Exception {
        terminal.clearScreen();
        terminal.setBackgroundColor(gfx.damageTaken);
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < 24; j++) {
                terminal.setCursorPosition(i, j);
                terminal.putCharacter(' ');
            }
        }
        terminal.flush();
        Thread.sleep(10);
        terminal.setBackgroundColor(gfx.blackBg);
    }

    public static void screenFlashHealing() throws Exception {
        terminal.clearScreen();
        terminal.setBackgroundColor(gfx.healingTaken);
        for (int i = 0; i < 80; i++) {
            for (int j = 0; j < 24; j++) {
                terminal.setCursorPosition(i, j);
                terminal.putCharacter(' ');
            }
        }
        terminal.flush();
        Thread.sleep(10);
        terminal.setBackgroundColor(gfx.blackBg);
    }

    public void readPlayerInput() throws IOException {
        KeyStroke newKeyStroke = terminal.pollInput();
        if (newKeyStroke == null) {
            type = KeyType.Backspace;
            return;
        } else
            type = newKeyStroke.getKeyType();
    }

    public void renderPowerUps() throws IOException {
        terminal.setForegroundColor(gfx.heartColor);
        PowerUp heartsToBeRemoved = null;
        for (PowerUp p : powerUpPositions) {
            terminal.setCursorPosition(p.position.getxPos(), p.position.getyPos());
            terminal.putCharacter(p.getHeart());

            if (p.position.getxPos() >= 0) {
                if (gameTime % 8 == 0) {
                    p.position.setxPos(p.position.getxPos() - 1);
                }
            }
            if (p.position.getxPos() < 0) {
                heartsToBeRemoved = p;
            }
        }
        powerUpPositions.remove(heartsToBeRemoved);
    }

    public void renderPlayer() throws IOException {
        terminal.setForegroundColor(gfx.playerColor);
        terminal.setCursorPosition(playerObject.position.getxPos(), playerObject.position.getyPos());
        terminal.putCharacter(gfx.player);
        playerObject.drawPlayerAvatar();
        for (Position p : playerObject.playerAvatar) {
            terminal.setCursorPosition(p.getxPos(), p.getyPos());
            terminal.putCharacter(gfx.playerBody);
        }
    }

    public void renderProjectiles() throws IOException {
        for (Projectile p : projectileList) {
            terminal.setForegroundColor(gfx.playerLaser);
            if (gameTime % 3 == 0)
                p.setxPos(p.getxPos() + 1);
            if ((p.getxPos() < 80)) {
                terminal.setCursorPosition(p.getxPos(), p.getyPos());
                terminal.putCharacter(gfx.playerProjectile);
            }
        }
        for (Projectile e : enemyProjectileList) {
            terminal.setForegroundColor(gfx.enemyLaser);
            if (gameTime % 3 == 0)
                e.setxPos(e.getxPos() - 1);
            if ((e.getxPos() >= 0)) {
                terminal.setCursorPosition(e.getxPos(), e.getyPos());
                terminal.putCharacter(gfx.enemyProjectile);
            }
        }
    }

    public void renderEnemies() throws IOException {
        terminal.setForegroundColor(gfx.enemyColor);
        for (Enemy e : enemyList) {
            if ((e.position.getxPos() < 80) && (e.position.getxPos() >= 0) && (e.position.getyPos() > 0) && (e.position.getyPos() <= 24)) {
                terminal.setCursorPosition(e.position.getxPos(), e.position.getyPos());
                terminal.putCharacter(gfx.enemy);
            }
            e.drawEnemyShape();
            for (Position p : e.enemyShape) {
                if ((e.position.getxPos() < 80) && (e.position.getxPos() >= 0) && (e.position.getyPos() > 0) && (e.position.getyPos() <= 24)) {
                    terminal.setCursorPosition(p.getxPos(), p.getyPos());
                    terminal.putCharacter(gfx.enemyBody);
                }
            }
        }
    }

    public static int getGameTime() {
        return gameTime;
    }

    public void renderHUD() throws IOException {
        terminal.setForegroundColor(gfx.hudColor);
        String wordScore = "Score: ";
        String highScore = String.valueOf(score);
        for (int i = 0; i < wordScore.length(); i++) {
            terminal.setCursorPosition(i + 32, 1);
            terminal.putCharacter(wordScore.charAt(i));
        }
        for (int i = 0; i < highScore.length(); i++) {
            terminal.setCursorPosition(i + 40, 1);
            terminal.putCharacter(highScore.charAt(i));
        }
        terminal.setForegroundColor(gfx.heartColor);
        for (int i = 0; i < playerObject.hp; i++) {
            terminal.setCursorPosition(25 + i, 1);
            terminal.putCharacter(gfx.Hp2);
        }
    }
}
