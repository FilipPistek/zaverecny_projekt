package entities;

import main.KeyBinds;
import main.Game;

public class Player extends Entity {
    KeyBinds keyBinds;

    public final int screenX;
    public final int screenY;

    /**Player constructor**/
    public Player(Game game, KeyBinds keyBinds) {
        super(game);
        this.keyBinds = keyBinds;

        screenX = game.screenWidth / 2 - game.tileSize / 2;
        screenY = game.screenHeight / 2 - game.tileSize;

        setDefaultValues();
        getPlayerImage();
    }

    /**Method for player values and spawn location**/
    public void setDefaultValues() {
        worldX = game.tileSize * 15;
        worldY = game.tileSize * 37;
        movementSpeed = 3;
        direction = "down";

        maxLife = 5;
        currentLife = maxLife;
    }

    /**Method for getting path for player images**/
    public void getPlayerImage() {
        up1 = setupEntityImage("/player/squire_up_1");
        up2 = setupEntityImage("/player/squire_up_2");
        down1 = setupEntityImage("/player/squire_down_1");
        down2 = setupEntityImage("/player/squire_down_2");
        left1 = setupEntityImage("/player/squire_left_1");
        left2 = setupEntityImage("/player/squire_left_2");
        right1 = setupEntityImage("/player/squire_right_1");
        right2 = setupEntityImage("/player/squire_right_2");
    }

    /**Method for updating player images**/
    public void update() {
        if (keyBinds.up == true || keyBinds.down == true || keyBinds.left == true || keyBinds.right == true) {

            if (keyBinds.up == true) {
                direction = "up";

            } else if (keyBinds.down == true) {
                direction = "down";

            } else if (keyBinds.left == true) {
                direction = "left";

            } else if (keyBinds.right == true) {
                direction = "right";
            }

            entityCollision = false;
            game.collisions.checkTile(this);

            int npcIndex = game.collisions.checkEntity(this, game.npc);
            npcInteraction(npcIndex);

            int monsterIndex = game.collisions.checkEntity(this, game.monster);
            monsterContact(monsterIndex);

            if (entityCollision == false) {
                switch (direction) {
                    case "up":
                        worldY -= movementSpeed;
                        break;
                    case "down":
                        worldY += movementSpeed;
                        break;
                    case "left":
                        worldX -= movementSpeed;
                        break;
                    case "right":
                        worldX += movementSpeed;
                        break;
                }
            }

            imageCounter++;
            if (imageCounter > 10) {
                if (imageNumber == 1) {
                    imageNumber = 2;
                } else if (imageNumber == 2) {
                    imageNumber = 1;
                }
                imageCounter = 0;
            }
        }
        if (untargetable == true) {
             untargetableCounter++;
             if (untargetableCounter > 60) {
                 untargetable = false;
                 untargetableCounter = 0;
             }
        }
    }

    /**Method for interaction with npc**/
    public void npcInteraction(int i) {
        if (i != 999) {
            if (game.keyBinds.e == true) {
                game.gameState = game.talkingState;
                game.npc[i].speak();
            }
        }
        game.keyBinds.e = false;
    }

    /**Method for player taking damage when in collision with monster**/
    public void monsterContact(int i) {
        if (i != 999) {
            if (untargetable == false) {
                currentLife -=1;
                untargetable = true;
            }
        }
    }
}