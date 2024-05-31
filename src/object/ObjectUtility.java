package object;

import main.DrawHelper;
import main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ObjectUtility {

    public BufferedImage image, image1, image2;
    public String name;
    public boolean objectCollision = false;
    public int worldX, worldY;
    public Rectangle hitBox = new Rectangle(0, 0, 48, 48);
    public int hitBoxDefaultX = 0;
    public int hitBoxDefaultY = 0;
    DrawHelper drawHelper = new DrawHelper();

    public void drawObject(Graphics2D graphics2D, Game game) {
        int screenX = worldX - game.player.worldX + game.player.screenX;
        int screenY = worldY - game.player.worldY + game.player.screenY;

        if (worldX + game.tileSize > game.player.worldX - game.player.screenX &&
            worldX - game.tileSize < game.player.worldX + game.player.screenX &&
            worldY + game.tileSize > game.player.worldY - game.player.screenY &&
            worldY - game.tileSize < game.player.worldY + game.player.screenY) {
            graphics2D.drawImage(image, screenX, screenY, game.tileSize, game.tileSize, null);
        }
    }
}
