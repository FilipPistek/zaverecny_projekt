package main;

import java.awt.*;

public class UI {

    Game game;
    Graphics2D graphics2D;
    Font font;

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
    }

    public void drawPausedScreen() {

        String text = "PAUSED";
        int x = game.screenWidth / 2 - (game.screenWidth / 48) * 3 - game.screenWidth/96;
        int y = game.screenHeight / 2 - (game.screenHeight / 48) * 4;

        graphics2D.drawString(text, x, y);
    }
}