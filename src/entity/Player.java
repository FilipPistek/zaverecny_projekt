package entity;

import main.KeyBinds;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {
    KeyBinds keyBinds;

    public final int screenX;
    public final int screenY;

    public Player(Game game, KeyBinds keyBinds) {
        super(game);
        this.keyBinds = keyBinds;

        screenX = game.screenWidth / 2 - game.tileSize / 2;
        screenY = game.screenHeight / 2 - game.tileSize;

        hitBox.x = 8;
        hitBox.y = 16;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;
        hitBox.width = 30;
        hitBox.height = 32;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = game.tileSize * 25;
        worldY = game.tileSize * 30;
        movementSpeed = 3;
        direction = "down";

        maxLife = 5;
        currentLife = maxLife;
    }

    public void getPlayerImage() {
        up1 = setupEntityImage("/squire/knight_up_1");
        up2 = setupEntityImage("/squire/knight_up_2");
        down1 = setupEntityImage("/squire/knight_down_1");
        down2 = setupEntityImage("/squire/knight_down_2");
        left1 = setupEntityImage("/squire/knight_left_1");
        left2 = setupEntityImage("/squire/knight_left_2");
        right1 = setupEntityImage("/squire/knight_right_1");
        right2 = setupEntityImage("/squire/knight_right_2");
    }

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
            game.collisionChecker.checkTile(this);

            int npcIndex = game.collisionChecker.checkEntity(this, game.npc);
            npcInteraction(npcIndex);

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
    }

    public void npcInteraction(int i) {
        if (i != 999) {
            if (game.keyBinds.e == true) {
                game.gameState = game.talkingState;
                game.npc[i].speak();
            }
        }
        game.keyBinds.e = false;
    }

    public void drawPlayer(Graphics2D graphics2D) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (imageNumber == 1) {
                    image = up1;
                }
                if (imageNumber == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (imageNumber == 1) {
                    image = down1;
                }
                if (imageNumber == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (imageNumber == 1) {
                    image = left1;
                }
                if (imageNumber == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (imageNumber == 1) {
                    image = right1;
                }
                if (imageNumber == 2) {
                    image = right2;
                }
                break;
        }
        graphics2D.drawImage(image, screenX, screenY, null);
    }
}