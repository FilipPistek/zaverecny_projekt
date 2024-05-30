package main;

import entity.NPC;

public class AssetSetup {
    Game game;

    public AssetSetup(Game game) {
        this.game = game;
    }

    public void setupObject() {

    }

    public void setupNPC() {
        game.npc[0] = new NPC(game);
        game.npc[0].worldX = game.tileSize * 30;
        game.npc[0].worldY = game.tileSize * 30;
    }
}