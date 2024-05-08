package main;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        JFrame window = new JFrame();
        Game game = new Game();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("2D_RPG_Game");

        window.add(game);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        game.startGameThread();
    }
}