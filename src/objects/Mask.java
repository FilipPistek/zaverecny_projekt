package objects;

import entities.Entity;
import main.Game;

public class Mask extends Entity {

    Game game;

    /**ObjectMask constructor and file path**/
    public Mask(Game game) {
        super(game);
        name = "Mask";
        image1 = setupEntityImage("/objects/mask", game.tileSize, game.tileSize);
        image2 = setupEntityImage("/objects/empty_mask", game.tileSize, game.tileSize);
    }
}
