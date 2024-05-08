package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBinds implements KeyListener {

    public boolean up, down, left, right;

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
