package main;

import entities.Entity;
import objects.Mask;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    Game game;
    Graphics2D graphics2D;
    Font font;
    BufferedImage fullMask, emptyMask;
    public String currentText = "";
    public  int command = 0;

    /**UI constructor**/
    public UI(Game game) {
        this.game = game;
        font = new Font("Arial", Font.PLAIN, 40);

        Entity mask = new Mask(game);
        fullMask = mask.image1;
        emptyMask = mask.image2;
    }

    /**Method for drawing UI**/
    public void drawUI(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;

        graphics2D.setFont(font);
        graphics2D.setColor(Color.white);

        if (game.gameState == game.titleState) {
            drawTitleScreen();
        }

        if (game.gameState == game.playState) {
            drawPlayerMasks();
        }
        if (game.gameState == game.pauseState) {
            drawPlayerMasks();
            drawPausedScreen();
        }
        if (game.gameState == game.talkingState) {
            drawPlayerMasks();
            drawInteractionScreen();
        }
    }

    /**Method for drawing player's health**/
    public void drawPlayerMasks() {
        int x = game.tileSize/2;
        int y = game.tileSize/2;
        int i = 0;

        while (i < game.player.maxLife) {
            graphics2D.drawImage(emptyMask, x, y, null);
            i++;
            x += game.tileSize;
        }

        x = game.tileSize/2;
        y = game.tileSize/2;
        i = 0;

        while (i < game.player.currentLife) {
            graphics2D.drawImage(fullMask, x, y, null);
            i++;
            x += game.tileSize;
        }
    }

    /**Method for drawing title screen (menu)**/
    public void drawTitleScreen() {
        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 120F));

        String text = "Hollow Squire";
        int x = game.screenWidth / 2 - (game.screenWidth / 48) * 15 - game.screenWidth / 96;
        int y = game.tileSize * 3;
        graphics2D.setColor(Color.white);
        graphics2D.drawString(text, x, y);

        x = game.screenWidth / 2 - (game.tileSize * 2) / 2;
        y += game.tileSize * 2;
        graphics2D.drawImage(game.player.down1, x, y, game.tileSize * 2, game.tileSize * 2, null);

        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 60F));

        text = "PLAY GAME";
        x = game.screenWidth / 2 - (game.screenWidth / 48) * 7;
        y += game.tileSize * 5;
        graphics2D.drawString(text, x, y);
        if (command == 0) {
            graphics2D.drawString(">", x - game.tileSize, y);
        }

        text = "EXIT GAME";
        x = game.screenWidth / 2 - (game.screenWidth / 48) * 6 - game.screenWidth / 96;
        y += game.tileSize * 2;
        graphics2D.drawString(text, x, y);
        if (command == 1) {
            graphics2D.drawString(">", x - game.tileSize, y);
        }
    }

    /**Method for drawing string "paused"**/
    public void drawPausedScreen() {
        String text = "PAUSED";
        int x = game.screenWidth / 2 - (game.screenWidth / 48) * 3 - game.screenWidth / 96;
        int y = game.screenHeight / 2 - (game.screenHeight / 48) * 4;

        graphics2D.drawString(text, x, y);
    }

    /**Method for drawing interaction screen**/
    public void drawInteractionScreen() {
        int x = game.tileSize * 2;
        int y = game.tileSize / 2;
        int width = game.screenWidth - (game.tileSize * 4);
        int height = game.tileSize * 5;
        drawSuppWindow(x, y, width, height);

        graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 32F));
        x += game.tileSize;
        y += game.tileSize;

        for (String line : currentText.split("\n")) {
            graphics2D.drawString(line, x, y);
            y += 40;
        }

    }

    /**Method for drawing supportive window**/
    public void drawSuppWindow(int x, int y, int width, int height) {
        Color color = new Color(0, 0, 0, 180);
        graphics2D.setColor(color);
        graphics2D.fillRoundRect(x, y, width, height, 36, 36);

        color = new Color(255, 255, 255);
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 25, 25);
    }
}