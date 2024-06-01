package main;

import entities.Entity;
import entities.Player;
import tile.TileUtility;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Game extends JPanel implements Runnable {

    public final int originalTileSize = 16;
    public int scaling = 3;
    public final int tileSize = originalTileSize * scaling;
    public final int maxScreenColumn = 24;
    public final int maxScreenRow = 18;
    public final int screenWidth = tileSize * maxScreenColumn;
    public final int screenHeight = tileSize * maxScreenRow;
    public final int maxWorldColumn = 50;
    public final int maxWorldRow =  50;
    public int fps = 60;
    public int gameState;
    public final int titleState = 0;
    public final int pauseState = 1;
    public final int playState = 2;
    public final int talkingState = 3;

    Thread gameThread;
    public KeyBinds keyBinds = new KeyBinds(this);
    TileUtility tileUtility = new TileUtility(this);
    Sound soundEffect = new Sound();
    public UI ui = new UI(this);
    AssetSetup assetSetup = new AssetSetup(this);
    public CollisionChecker collisionChecker = new CollisionChecker(this);
    public Player player = new Player(this, keyBinds);
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[20];
    public Entity object[] = new Entity[10];
    ArrayList<Entity> entityList = new ArrayList<>();

    /**Game constructor**/
    public Game() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyBinds);
        this.setFocusable(true);
    }

    /**Method for starting the game**/
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**Method for setting up the game**/
    public void setupGame() {
        assetSetup.setupObject();
        assetSetup.setupNPC();
        assetSetup.setupMonster();
        gameState = titleState;
    }

    /**Method for tick rate (fps)**/
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

    /**Method for updating everything**/
    public void update() {
        if (gameState == playState) {
            player.update();

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    npc[i].update();
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    monster[i].update();
                }
            }
        }
        if (gameState == pauseState) {

        }
    }

    /**Method for drawing everything**/
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics;

        if (gameState == titleState) {
            ui.drawUI(graphics2D);

        } else {
            tileUtility.drawTile(graphics2D);

            entityList.add(player);

            for (int i = 0; i < object.length; i++) {
                if (object[i] != null) {
                    entityList.add(object[i]);
                }
            }
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    entityList.add(npc[i]);
                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    entityList.add(monster[i]);
                }
            }
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            for (int i = 0; i < entityList.size(); i++) {
                entityList.get(i).drawEntity(graphics2D);
            }
            for (int i = 0; i < entityList.size(); i++) {
                entityList.remove(i);
            }

            ui.drawUI(graphics2D);

            graphics2D.dispose();
        }
    }

    /**Method for playing sound effects**/
    public void playSoundEffect(int i) {
        soundEffect.setFileSound(i);
        soundEffect.playSound();
    }
}