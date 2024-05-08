package main;

import entity.Entity;

public class CollisionChecker {

    Game game;

    public CollisionChecker(Game game) {

        this.game = game;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.hitBox.x;
        int entityRightWorldX = entity.worldX + entity.hitBox.x + entity.hitBox.width;
        int entityTopWorldY = entity.worldY + entity.hitBox.y;
        int entityBottomWorldY = entity.worldY + entity.hitBox.y + entity.hitBox.height;

        int entityLeftColumn = entityLeftWorldX / game.tileSize;
        int entityRightColumn = entityRightWorldX / game.tileSize;
        int entityTopRow = entityTopWorldY / game.tileSize;
        int entityBottomRow = entityBottomWorldY / game.tileSize;

        int tileNumber1, tileNumber2;

        switch (entity.direction) {

            case "up":
                entityTopRow = (entityTopWorldY - entity.movementSpeed) / game.tileSize;
                tileNumber1 = game.tileUtility.mapTileXY[entityLeftColumn][entityTopRow];
                tileNumber2 = game.tileUtility.mapTileXY[entityRightColumn][entityTopRow];
                if (game.tileUtility.tile[tileNumber1].tileCollision == true || game.tileUtility.tile[tileNumber2].tileCollision == true) {
                    entity.entityCollision = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY - entity.movementSpeed) / game.tileSize;
                tileNumber1 = game.tileUtility.mapTileXY[entityLeftColumn][entityBottomRow];
                tileNumber2 = game.tileUtility.mapTileXY[entityRightColumn][entityBottomRow];
                if (game.tileUtility.tile[tileNumber1].tileCollision == true || game.tileUtility.tile[tileNumber2].tileCollision == true) {
                    entity.entityCollision = true;
                }
                break;
            case "left":
                entityLeftColumn = (entityLeftWorldX - entity.movementSpeed) / game.tileSize;
                tileNumber1 = game.tileUtility.mapTileXY[entityLeftColumn][entityTopRow];
                tileNumber2 = game.tileUtility.mapTileXY[entityLeftColumn][entityBottomRow];
                if (game.tileUtility.tile[tileNumber1].tileCollision == true || game.tileUtility.tile[tileNumber2].tileCollision == true) {
                    entity.entityCollision = true;
                }
                break;
            case "right":
                entityRightColumn = (entityRightWorldX - entity.movementSpeed) / game.tileSize;
                tileNumber1 = game.tileUtility.mapTileXY[entityRightColumn][entityTopRow];
                tileNumber2 = game.tileUtility.mapTileXY[entityRightColumn][entityBottomRow];
                if (game.tileUtility.tile[tileNumber1].tileCollision == true || game.tileUtility.tile[tileNumber2].tileCollision == true) {
                    entity.entityCollision = true;
                }
                break;
        }
    }
}
