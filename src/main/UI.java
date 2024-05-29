package main;

import java.awt.*;

public class UI {

    Game game;
    Graphics2D graphics2D;
    Font font;
    public String currentText = "";

    public UI(Game game) {
        this.game = game;
        font = new Font("Arial",Font.PLAIN, 40);
    }

    public void drawUI(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;

        graphics2D.setFont(font);
        graphics2D.setColor(Color.white);

        if (game.gameState == game.playState) {

        }
        if (game.gameState == game.pauseState) {
            drawPausedScreen();
        }
        if (game.gameState == game.talkingState) {
            drawTalkingScreen();
        }
    }

    public void drawPausedScreen() {
        String text = "PAUSED";
        int x = game.screenWidth / 2 - (game.screenWidth / 48) * 3 - game.screenWidth/96;
        int y = game.screenHeight / 2 - (game.screenHeight / 48) * 4;

        graphics2D.drawString(text, x, y);
    }

    public void drawTalkingScreen() {
         int x = game.tileSize*2;
         int y = game.tileSize/2;
         int width = game.screenWidth - (game.tileSize*4);
         int height = game.tileSize*5;
         drawSuppWindow(x, y, width, height);

         graphics2D.setFont(graphics2D.getFont().deriveFont(Font.PLAIN, 32F));
         x += game.tileSize;
         y += game.tileSize;

         for (String line : currentText.split("\n")) {
             graphics2D.drawString(line, x, y);
             y += 40;
         }

    }

    public void drawSuppWindow(int x, int y, int width, int height) {
        Color color = new Color(0,0,0, 180);
        graphics2D.setColor(color);
        graphics2D.fillRoundRect(x, y, width, height, 36, 36);

        color = new Color(255,255,255);
        graphics2D.setColor(color);
        graphics2D.setStroke(new BasicStroke(5));
        graphics2D.drawRoundRect(x+5, y+5, width-10, height-10, 25, 25);
    }
}