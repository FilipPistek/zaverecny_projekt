package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBinds implements KeyListener {

    Game game;

    public boolean up, down, left, right;
    boolean drawTime = false;

    public KeyBinds(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent k) {

    }

    @Override
    public void keyPressed(KeyEvent k) {

        int keyCode = k.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            up = true;
        }
        if (keyCode == KeyEvent.VK_S) {
            down = true;
        }
        if (keyCode == KeyEvent.VK_A) {
            left = true;
        }
        if (keyCode == KeyEvent.VK_D) {
            right = true;
        }
        if (keyCode == KeyEvent.VK_ESCAPE) {
            if (game.gameState == game.playState) {
                game.gameState = game.pauseState;
            } else if (game.gameState == game.pauseState) {
                game.gameState = game.playState;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent k) {

        int keyCode = k.getKeyCode();

        if (keyCode == KeyEvent.VK_W) {
            up = false;
        }
        if (keyCode == KeyEvent.VK_S) {
            down = false;
        }
        if (keyCode == KeyEvent.VK_A) {
            left = false;
        }
        if (keyCode == KeyEvent.VK_D) {
            right = false;
        }
    }
}