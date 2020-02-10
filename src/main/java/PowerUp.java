import java.util.ArrayList;

public class PowerUp {
    private char heart = '\u2665';
    Position position;

    PowerUp(Position position) {
        this.position = position;
    }

    public char getHeart() {
        return heart;
    }

    public static void powerUpCollision(ArrayList<PowerUp> powerUpPositions, Player playerObject) throws Exception {
        PowerUp pickUpHeart = null;
        for (PowerUp p : powerUpPositions) {
            if (p.position.getxPos() == playerObject.position.getxPos() && p.position.getyPos() == playerObject.position.getyPos()) {
                if (playerObject.hp < 3) {
                    playerObject.hp++;
                    Game.screenFlashHealing();
                    Game.sfx.play("heartUp.mp3");
                    pickUpHeart = p;
                }
            }
        }
        for (PowerUp p : powerUpPositions) {
            for (Position p2 : playerObject.playerAvatar) {
                if (p.position.getxPos() == p2.getxPos() && p.position.getyPos() == p2.getyPos()) {
                    if (playerObject.hp < 3) {
                        playerObject.hp++;
                        Game.screenFlashHealing();
                        Game.sfx.play("heartUp.mp3");
                        pickUpHeart = p;
                    }
                }
            }
        }
        if (pickUpHeart != null) {
            powerUpPositions.remove(pickUpHeart);
        }
    }
}
