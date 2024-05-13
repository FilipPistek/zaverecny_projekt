package entity;

import main.KeyBinds;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    Game game;
    KeyBinds keyBinds;

    public final int screenX;
    public final int screenY;

    public Player(Game game, KeyBinds keyBinds) {

        this.game = game;
        this.keyBinds = keyBinds;

        screenX = game.screenWidth/2 - game.tileSize/2;
        screenY = game.screenHeight/2 - game.tileSize;

        hitBox = new Rectangle();
        hitBox.x = 8;
        hitBox.y = 16;
        hitBoxDefaultX = hitBox.x;
        hitBoxDefaultY = hitBox.y;
        hitBox.width = 32;
        hitBox.height = 32;

        setDefaultPosition();
        getPlayerImage();
    }

    public void setDefaultPosition() {

        worldX = game.tileSize * 12;
        worldY = game.tileSize * 9;
        movementSpeed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/knight_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/knight_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/knight_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/knight_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/knight_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/knight_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/knight_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/knight_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        graphics2D.drawImage(image, screenX, screenY, game.tileSize, game.tileSize, null);
    }
}
