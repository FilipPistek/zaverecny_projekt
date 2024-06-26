package tiles;

import main.DrawHelper;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileUtility {

    Game game;
    public Tile[] tile;
    public int mapTileXY[][];

    /**TileUtility constructor**/
    public TileUtility(Game game) {
        this.game = game;
        tile = new Tile[10];
        mapTileXY = new int[game.maxWorldColumn][game.maxWorldRow];

        getTileImage();
        loadMap("/maps/map_1.txt");
    }

    /**Method for getting tile images via their index**/
    public void getTileImage() {
        setupTileImage(0, "dark_rock", false);
        setupTileImage(1, "dark_rock_spikes", false);
        setupTileImage(2, "dark_rock_wall", true);
    }

    /**Method for better path finding for tile images**/
    public void setupTileImage(int index, String name, boolean collision) {
        DrawHelper drawHelper = new DrawHelper();

        try {
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/" + name + ".png"));
            tile[index].image = drawHelper.scaleImage(tile[index].image, game.tileSize, game.tileSize);
            tile[index].tileCollision = collision;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Method for loading map from .txt file**/
    public void loadMap(String filePath) {
        try {
            InputStream inputStream = getClass().getResourceAsStream(filePath);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

            int column = 0;
            int row = 0;

            while (column < game.maxWorldColumn && row < game.maxWorldRow) {
                String line = bufferedReader.readLine();

                while (column < game.maxWorldColumn) {
                    String index[] = line.split(" ");
                    int numberOfIndex = Integer.parseInt(index[column]);

                    mapTileXY[column][row] = numberOfIndex;
                    column++;
                }
                if (column == game.maxWorldColumn) {
                    column = 0;
                    row++;
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**Method for drawing tiles**/
    public void drawTile(Graphics2D graphics2D) {
        int worldColumn = 0;
        int worldRow = 0;

        while (worldColumn < game.maxWorldColumn && worldRow < game.maxWorldRow) {
            int tileNum = mapTileXY[worldColumn][worldRow];

            int worldX = worldColumn * game.tileSize;
            int worldY = worldRow * game.tileSize;
            int screenX = worldX - game.player.worldX + game.player.screenX;
            int screenY = worldY - game.player.worldY + game.player.screenY;

            if (worldX + game.tileSize > game.player.worldX - game.player.screenX &&
                    worldX - game.tileSize - game.tileSize < game.player.worldX + game.player.screenX &&
                    worldY + game.tileSize > game.player.worldY - game.player.screenY &&
                    worldY - game.tileSize - game.tileSize < game.player.worldY + game.player.screenY) {

                graphics2D.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldColumn++;

            if (worldColumn == game.maxWorldColumn) {
                worldColumn = 0;
                worldRow++;
            }
        }
    }
}