package main;

import entities.Entity;

public class Collisions {

    Game game;

    /**CollisionChecker constructor**/
    public Collisions(Game game) {
        this.game = game;
    }

    /**Method for checking collisions with tiles**/
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

    /**Method for checking collisions (player to entity)**/
    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null) {
                entity.hitBox.x = entity.worldX + entity.hitBox.x;
                entity.hitBox.y = entity.worldY + entity.hitBox.y;

                target[i].hitBox.x = target[i].worldX + target[i].hitBox.x;
                target[i].hitBox.y = target[i].worldY + target[i].hitBox.y;

                switch (entity.direction) {
                    case "up":
                        entity.hitBox.y -= entity.movementSpeed;
                        break;
                    case "down":
                        entity.hitBox.y += entity.movementSpeed;
                        break;
                    case "left":
                        entity.hitBox.x -= entity.movementSpeed;
                        break;
                    case "right":
                        entity.hitBox.x += entity.movementSpeed;
                        break;
                }
                if (entity.hitBox.intersects(target[i].hitBox)) {
                    if (target[i] != entity) {
                        entity.entityCollision = true;
                        index = i;
                    }
                }
                entity.hitBox.x = entity.hitBoxDefaultX;
                entity.hitBox.y = entity.hitBoxDefaultY;
                target[i].hitBox.x = target[i].hitBoxDefaultX;
                target[i].hitBox.y = target[i].hitBoxDefaultY;
            }
        }
        return index;
    }

    /**Method for checking collisions (entity to player)**/
    public boolean checkPlayer(Entity entity) {
        boolean playerContact = false;

        entity.hitBox.x = entity.worldX + entity.hitBox.x;
        entity.hitBox.y = entity.worldY + entity.hitBox.y;

        game.player.hitBox.x = game.player.worldX + game.player.hitBox.x;
        game.player.hitBox.y = game.player.worldY + game.player.hitBox.y;

        switch (entity.direction) {
            case "up":
                entity.hitBox.y -= entity.movementSpeed;
                break;
            case "down":
                entity.hitBox.y += entity.movementSpeed;
                break;
            case "left":
                entity.hitBox.x -= entity.movementSpeed;
                break;
            case "right":
                entity.hitBox.x += entity.movementSpeed;
                break;
        }

        if (entity.hitBox.intersects(game.player.hitBox)) {
            entity.entityCollision = true;
            playerContact = true;
        }

        entity.hitBox.x = entity.hitBoxDefaultX;
        entity.hitBox.y = entity.hitBoxDefaultY;
        game.player.hitBox.x = game.player.hitBoxDefaultX;
        game.player.hitBox.y = game.player.hitBoxDefaultY;

        return playerContact;
    }
}