package monsters;

import entities.Entity;
import main.Game;

import java.util.Random;

public class InfectedSlime extends Entity {

    Game game;

    public InfectedSlime(Game game) {
        super(game);

        movementSpeed = 1;
        maxLife = 3;
        currentLife = maxLife;
        type = 2;

        getMonsterImage();
    }

    public void getMonsterImage() {
        up1 = setupEntityImage("/monsters/infected_slime_1", game.tileSize, game.tileSize);
        up2 = setupEntityImage("/monsters/infected_slime_2", game.tileSize, game.tileSize);
        down1 = setupEntityImage("/monsters/infected_slime_1", game.tileSize, game.tileSize);
        down2 = setupEntityImage("/monsters/infected_slime_2", game.tileSize, game.tileSize);
        left1 = setupEntityImage("/monsters/infected_slime_1", game.tileSize, game.tileSize);
        left2 = setupEntityImage("/monsters/infected_slime_2", game.tileSize, game.tileSize);
        right1 = setupEntityImage("/monsters/infected_slime_1", game.tileSize, game.tileSize);
        right2 = setupEntityImage("/monsters/infected_slime_2", game.tileSize, game.tileSize);
    }

    public void setAction() {
        actionHold++;

        if (actionHold == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 25) {
                direction = "up";
            }
            if (i > 25 && i <= 50) {
                direction = "down";
            }
            if (i > 50 && i <= 75) {
                direction = "left";
            }
            if (i > 75 && i < 100) {
                direction = "right";
            }
            actionHold = 0;
        }
    }
}
