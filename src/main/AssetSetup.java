package main;

import entities.NPC;
import monsters.InfectedSlime;

public class AssetSetup {
    Game game;

    /**AssetSetup constructor**/
    public AssetSetup(Game game) {
        this.game = game;
    }

    /**Method for setting up objects**/
    public void setupObject() {

    }

    /**Method for spawning npc**/
    public void setupNPC() {
        game.npc[0] = new NPC(game);
        game.npc[0].worldX = game.tileSize * 29;
        game.npc[0].worldY = game.tileSize * 11;
    }

    public void setupMonster() {
        game.monster[0] = new InfectedSlime(game);
        game.monster[0].worldX = game.tileSize * 28;
        game.monster[0].worldY = game.tileSize * 35;

        game.monster[1] = new InfectedSlime(game);
        game.monster[1].worldX = game.tileSize * 24;
        game.monster[1].worldY = game.tileSize * 33;

        game.monster[0] = new InfectedSlime(game);
        game.monster[0].worldX = game.tileSize * 33;
        game.monster[0].worldY = game.tileSize * 34;
    }
}