package main;

import entity.Player;
import tile.TileUtility;

import javax.swing.*;
import java.awt.*;

public class Game extends JPanel implements Runnable {

    public final int originalTileSize = 16;
    public int scaling = 3;
    public final int tileSize = originalTileSize * scaling;
    public final int maxScreenColumn = 24;
    public final int maxScreenRow = 18;
    public final int screenWidth = tileSize * maxScreenColumn;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxWorldColumn = 100;
    public final int maxWorldRow = 100;

    public int fps = 60;

    Thread gameThread;
    KeyBinds keyBinds = new KeyBinds();
    public Player player = new Player(this, keyBinds);
    TileUtility tileUtility = new TileUtility(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);

    public Game() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyBinds);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double interval = 1000000000 / fps;
        double nextInterval = System.nanoTime() + interval;

        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextInterval - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextInterval += interval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void update() {

        player.update();
    }

    public void paintComponent(Graphics graphics) {

        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        tileUtility.drawTile(graphics2D);
        player.drawPlayer(graphics2D);
        graphics2D.dispose();
    }
}