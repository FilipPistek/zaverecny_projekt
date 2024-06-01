package entities;

import main.Game;

import java.util.Random;

public class NPC extends Entity {

    /**NPC constructor**/
    public NPC(Game game) {
        super(game);

        movementSpeed = 1;


        getNPCImage();
        setDialogue();
    }

    /**Method for getting path for images for npc**/
    public void getNPCImage() {
        up1 = setupEntityImage("/player/squire_up_1");
        up2 = setupEntityImage("/player/squire_up_2");
        down1 = setupEntityImage("/player/squire_down_1");
        down2 = setupEntityImage("/player/squire_down_2");
        left1 = setupEntityImage("/player/squire_left_1");
        left2 = setupEntityImage("/player/squire_left_2");
        right1 = setupEntityImage("/player/squire_right_1");
        right2 = setupEntityImage("/player/squire_right_2");
    }

    /**Method for setting what npc says**/
    public void setDialogue() {
        text[0] = "Hello stranger.";
        text[1] = "Could you please kill those slimes?";

    }

    /**Method for npc AI**/
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

    /**Super method from Entity class**/
    public void speak() {
        super.speak();
    }
}