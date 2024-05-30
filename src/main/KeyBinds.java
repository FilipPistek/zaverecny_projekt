package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBinds implements KeyListener {

    Game game;
    public boolean up, down, left, right, e;

    public KeyBinds(Game game) {
        this.game = game;
    }

    @Override
    public void keyTyped(KeyEvent k) {

    }

    @Override
    public void keyPressed(KeyEvent k) {
        int keyCode = k.getKeyCode();

        if (game.gameState == game.titleState) {
            if (keyCode == KeyEvent.VK_W) {
                game.ui.command--;
                if (game.ui.command < 0) {
                    game.ui.command = 2;
                }
            }
            if (keyCode == KeyEvent.VK_S) {
                game.ui.command++;
                if (game.ui.command > 2) {
                    game.ui.command = 0;
                }
            }
            if (keyCode == KeyEvent.VK_ENTER) {
                if (game.ui.command == 0) {
                    game.gameState = game.playState;
                }
                if (game.ui.command == 1) {

                }
                if (game.ui.command == 2) {
                    System.exit(0);
                }
            }
        }

        if (game.gameState == game.playState) {
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
                game.gameState = game.pauseState;
            }
            if (keyCode == KeyEvent.VK_E) {
                e = true;
            }
        } else if (game.gameState == game.pauseState) {
            if (keyCode == KeyEvent.VK_ESCAPE) {
                game.gameState = game.playState;
            }
        } else if (game.gameState == game.talkingState) {
            if (keyCode == KeyEvent.VK_E) {
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