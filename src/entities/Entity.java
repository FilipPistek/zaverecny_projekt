package entities;

import main.DrawHelper;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {

    Game game;

    public int worldX, worldY;
    public int movementSpeed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String direction = "down";
    public int imageCounter = 0;
    public int imageNumber = 1;
    public Rectangle hitBox;
    public int hitBoxDefaultX, hitBoxDefaultY;
    public boolean entityCollision = false;
    public int actionHold = 0;
    public String text[] = new String[20];
    public BufferedImage image1, image2;
    public String name;
    public int textIndex = 0;
    public int maxLife;
    public int currentLife;
    public boolean untargetable = false;
    public int untargetableCounter = 0;
    public boolean attacking = false;
    public int type;

    /**Entity constructor**/
    public Entity(Game game) {
        this.game = game;

        hitBox = new Rectangle();
        hitBox.x = 8;
        hitBox.y = 16;
        hitBox.width = 32;
        hitBox.height = 32;
    }

    public void setAction() {

    }

    /**Method for interaction with npc**/
    public void speak() {
        if (text[textIndex] == null) {
            textIndex = 0;
        }
        game.ui.currentText = text[textIndex];
        textIndex++;

        switch (game.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
                break;
        }
    }

    /**Method for updating entity images**/
    public void update() {
        setAction();
        entityCollision = false;
        game.collisions.checkTile(this);
        game.collisions.checkEntity(this, game.npc);
        game.collisions.checkEntity(this, game.monster);
        boolean playerContact = game.collisions.checkPlayer(this);

        if (this.type == 2 && playerContact == true) {
            if (game.player.untargetable == false) {
                game.player.currentLife -= 2;
                game.player.untargetable = true;
            }
        }

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

    /**Method for drawing entity**/
    public void drawEntity(Graphics2D graphics2D) {
        int screenX = worldX - game.player.worldX + game.player.screenX;
        int screenY = worldY - game.player.worldY + game.player.screenY;

        if (worldX + game.tileSize > game.player.worldX - game.player.screenX &&
                worldX - game.tileSize < game.player.worldX + game.player.screenX &&
                worldY + game.tileSize > game.player.worldY - game.player.screenY &&
                worldY - game.tileSize < game.player.worldY + game.player.screenY) {

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

    /**Method for better drawing images**/
    public BufferedImage setupEntityImage(String ImageName, int width, int height) {
        DrawHelper drawHelper = new DrawHelper();
        BufferedImage image = null;

        try {
            image = ImageIO.read(getClass().getResourceAsStream(ImageName + ".png"));
            image = drawHelper.scaleImage(image, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}