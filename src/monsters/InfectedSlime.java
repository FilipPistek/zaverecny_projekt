package monsters;

import entities.Entity;
import main.Game;

import java.util.Random;

public class InfectedSlime extends Entity {

    public InfectedSlime(Game game) {
        super(game);

        movementSpeed = 1;
        maxLife = 3;
        currentLife = maxLife;



        getMonsterImage();
    }

    public void getMonsterImage() {
        up1 = setupEntityImage("/monsters/infected_slime_1");
        up2 = setupEntityImage("/monsters/infected_slime_2");
        down1 = setupEntityImage("/monsters/infected_slime_1");
        down2 = setupEntityImage("/monsters/infected_slime_2");
        left1 = setupEntityImage("/monsters/infected_slime_1");
        left2 = setupEntityImage("/monsters/infected_slime_2");
        right1 = setupEntityImage("/monsters/infected_slime_1");
        right2 = setupEntityImage("/monsters/infected_slime_2");
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
