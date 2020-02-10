import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Enemy {
    Random random = new Random();
    Position position;
    List<Position> enemyShape = new ArrayList<>();
    int direction;
    private int movementPattern;
    int xLimit = random.nextInt(35) + 40;

    public Enemy(Position position, int direction, int movementPattern) {
        this.position = position;
        this.direction = direction;
        this.movementPattern = movementPattern;
        drawEnemyShape();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getMovementPattern(){
        return movementPattern;
    }

    public void drawEnemyShape(){
        enemyShape.clear();
        enemyShape.add(new Position(position.getxPos()+1, position.getyPos()));
        enemyShape.add(new Position(position.getxPos()+2, position.getyPos()));
        enemyShape.add(new Position(position.getxPos()+2, position.getyPos()-1));
        enemyShape.add(new Position(position.getxPos()+2, position.getyPos()+1));
        enemyShape.add(new Position(position.getxPos()+3, position.getyPos()));
        enemyShape.add(new Position(position.getxPos()+3, position.getyPos()-1));
        enemyShape.add(new Position(position.getxPos()+3, position.getyPos()+1));
    }

    public static void movement(ArrayList<Enemy> enemyList) {
        for (Enemy e : enemyList) {
            if (e.position.getxPos() > e.xLimit) {
                e.position.setxPos(e.position.getxPos() - 1);
            }
            if (e.getMovementPattern() == 0) {
                enemyMovementPattern1(e);
            }
            if (e.getMovementPattern() == 1) {
                enemyMovementPattern2(e);
            }
            if (e.getMovementPattern() == 2) {
                enemyMovementPattern3(e);
            }
        }
    }

    public static void enemyMovementPattern1(Enemy e) {
        int direction = e.direction;
        switch (direction) {
            case 1:
                if (Game.getGameTime() % 3 == 0) {
                    e.position.setyPos(e.position.getyPos() - 1);
                    if (e.position.getyPos() == 1) {
                        e.direction = 2;
                    }
                }
                break;
            case 2:
                if (Game.getGameTime() % 3 == 0) {
                    e.position.setyPos(e.position.getyPos() + 1);
                    if (e.position.getyPos() == 23) {
                        e.direction = 1;
                    }
                }
                break;
        }
    }

    public static void enemyMovementPattern2(Enemy e) {
        int direction = e.direction;
        switch (direction) {
            case 1:
                if (Game.getGameTime() % 3 == 0) {
                    e.position.setyPos(e.position.getyPos() - 1);
                    e.position.setxPos(e.position.getxPos() - 1);
                    if (e.position.getyPos() == 1) {
                        e.direction = 2;
                    }
                }
                break;
            case 2:
                if (Game.getGameTime() % 3 == 0) {
                    e.position.setyPos(e.position.getyPos() + 1);
                    e.position.setxPos(e.position.getxPos() + 1);
                    if (e.position.getyPos() == 23) {
                        e.direction = 1;
                    }
                }
                break;
        }
    }

    public static void enemyMovementPattern3(Enemy e) {
        int direction = e.direction;
        switch (direction) {
            case 1:
                if (Game.getGameTime() % 3 == 0) {
                    e.position.setyPos(e.position.getyPos() - 1);
                    e.position.setxPos(e.position.getxPos() + 1);
                    if (e.position.getyPos() == 1) {
                        e.direction = 2;
                    }
                }
                break;
            case 2:
                if (Game.getGameTime() % 3 == 0) {
                    e.position.setyPos(e.position.getyPos() + 1);
                    e.position.setxPos(e.position.getxPos() - 1);
                    if (e.position.getyPos() == 23) {
                        e.direction = 1;
                    }
                }
                break;
        }
    }

    public static void enemyShoot(ArrayList<Enemy> enemyList, ArrayList<Projectile> enemyProjectileList) {
        if (Game.getGameTime() % 110 == 0) {
            for (Enemy e : enemyList) {
                Projectile enemyProjectile = new Projectile(e.position.getxPos() + 1, e.position.getyPos());
                enemyProjectileList.add(enemyProjectileList.size(), enemyProjectile);
            }
        }
    }
}
