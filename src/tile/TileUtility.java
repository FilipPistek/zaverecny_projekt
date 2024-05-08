package tile;

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

    public TileUtility(Game game) {

        this.game = game;
        tile = new Tile[10];
        mapTileXY = new int[game.maxWorldColumn][game.maxWorldRow];

        getTileImage();
        loadMap("/maps/world_map.txt");
    }

    public void getTileImage() {

        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pine_tree.png"));
            tile[2].tileCollision = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

                graphics2D.drawImage(tile[tileNum].image, screenX, screenY, game.tileSize, game.tileSize, null);
            }

            worldColumn++;

            if (worldColumn == game.maxWorldColumn) {
                worldColumn = 0;
                worldRow++;
            }
        }
    }
}
