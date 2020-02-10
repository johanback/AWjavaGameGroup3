import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Player {
    Position position;
    List<Position> playerAvatar = new ArrayList<>();
    int hp = 3;

    public Player(Position position) {
        this.position = position;
        drawPlayerAvatar();
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void drawPlayerAvatar(){
        playerAvatar.clear();
        playerAvatar.add(new Position(position.getxPos()-1, position.getyPos()));
        playerAvatar.add(new Position(position.getxPos()-2, position.getyPos()));
        playerAvatar.add(new Position(position.getxPos()-2, position.getyPos()-1));
        playerAvatar.add(new Position(position.getxPos()-2, position.getyPos()+1));
        playerAvatar.add(new Position(position.getxPos()-3, position.getyPos()));
        playerAvatar.add(new Position(position.getxPos()-3, position.getyPos()-1));
        playerAvatar.add(new Position(position.getxPos()-3, position.getyPos()+1));
    }

    public void movement(KeyType type, Player playerObject) throws IOException {
        if (type != null) {
            switch (type) {
                case ArrowUp:
                    if ((playerObject.position.getyPos() - 1) >= 0)
                        playerObject.position.setyPos(playerObject.position.getyPos() - 1);
                    break;
                case ArrowDown:
                    if ((playerObject.position.getyPos() + 1) < 24)
                        playerObject.position.setyPos(playerObject.position.getyPos() + 1);
                    break;
                case ArrowLeft:
                    if ((playerObject.position.getxPos() - 1) >= 0)
                        playerObject.position.setxPos(playerObject.position.getxPos() - 1);
                    break;
                case ArrowRight:
                    if ((playerObject.position.getxPos() + 1) < 80)
                        playerObject.position.setxPos(playerObject.position.getxPos() + 1);
                    break;
                case Character:
                    Projectile projectile = new Projectile(playerObject.position.getxPos() + 1, playerObject.position.getyPos());
                    Game.projectileList.add(projectile);
                    Game.sfx.play("retro-laser-shot-04.wav");
                    break;
                case Backspace:
                    break;
            }
        }
    }
}
