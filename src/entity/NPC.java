package entity;

import main.Game;

import java.util.Random;

public class NPC extends Entity {

    public NPC(Game game) {
        super(game);

        direction = "down";
        movementSpeed = 1;

        getNPCImage();
    }

    public void getNPCImage() {

        up1 = setupEntityImage("/player/knight_up_1");
        up2 = setupEntityImage("/player/knight_up_2");
        down1 = setupEntityImage("/player/knight_down_1");
        down2 = setupEntityImage("/player/knight_down_2");
        left1 = setupEntityImage("/player/knight_left_1");
        left2 = setupEntityImage("/player/knight_left_2");
        right1 = setupEntityImage("/player/knight_right_1");
        right2 = setupEntityImage("/player/knight_right_2");
    }

    public void setAction() {

        actionHold ++;

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
            if (i > 75 && i <= 100) {
                direction = "right";
            }
            actionHold = 0;
        }
    }
}
