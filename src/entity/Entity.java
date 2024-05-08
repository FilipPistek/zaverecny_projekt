package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {

    public int worldX, worldY;
    public int movementSpeed;
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int imageCounter = 0;
    public int imageNumber = 1;
    public Rectangle hitBox;
    public int hitBoxDefaultX, hitBoxDefaultY;
    public boolean entityCollision = false;
}
