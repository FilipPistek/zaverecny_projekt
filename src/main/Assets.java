package main;

import entities.NPC;
import monsters.InfectedSlime;

public class Assets {
    Game game;

    /**AssetSetup constructor**/
    public Assets(Game game) {
        this.game = game;
    }

    /**Method for setting up objects**/
    public void setupObject() {

    }

    /**Method for spawning npc**/
    public void setupNPC() {
        game.npc[0] = new NPC(game);
        game.npc[0].worldX = game.tileSize * 16;
        game.npc[0].worldY = game.tileSize * 35;
    }

    public void setupMonster() {
        game.monster[0] = new InfectedSlime(game);
        game.monster[0].worldX = game.tileSize * 28;
        game.monster[0].worldY = game.tileSize * 35;

        game.monster[1] = new InfectedSlime(game);
        game.monster[1].worldX = game.tileSize * 24;
        game.monster[1].worldY = game.tileSize * 33;

        game.monster[2] = new InfectedSlime(game);
        game.monster[2].worldX = game.tileSize * 33;
        game.monster[2].worldY = game.tileSize * 34;

        game.monster[3] = new InfectedSlime(game);
        game.monster[3].worldX = game.tileSize * 22;
        game.monster[3].worldY = game.tileSize * 19;

        game.monster[4] = new InfectedSlime(game);
        game.monster[4].worldX = game.tileSize * 20;
        game.monster[4].worldY = game.tileSize * 13;

        game.monster[6] = new InfectedSlime(game);
        game.monster[6].worldX = game.tileSize * 32;
        game.monster[6].worldY = game.tileSize * 12;
    }
}