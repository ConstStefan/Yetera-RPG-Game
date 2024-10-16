package PaooGame.Tiles;

import PaooGame.Exceptions.MapException;
import PaooGame.Game;
import PaooGame.GameWindow.GameWindow;
import PaooGame.Tiles.Tile;
import PaooGame.entity.Player;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

   // private static final int NO_MAPS = 2;
   // public static TileManager[] maps = new TileManager[NO_MAPS]; // contine hartile
   // private static String[] mapPaths = {"/maps/worldmap.txt"}; // contine path-urile catre fiecare harta
    public int[][] mapTileNum = new int[GameWindow.maxWorldCol][GameWindow.maxWorldRow]; // va incarca harta propriu-zisa
    //protected final int id;
    Player player;

   // public static int mapNo = 0;

    InputStream is;
    BufferedReader br;

    public TileManager(Player player){

            this.player = player;
        try {
            loadMap("/maps/worldmap.txt");
        } catch (MapException e) {
            e.printStackTrace();
        }
        // loadMap("/maps/worldmaplvl2.txt",1);

            //maps[mapNo] = new Level1Map(0, this.player);
            /*try{
                InputStream is = getClass().getResourceAsStream("/maps/worldmap.txt");
                BufferedReader br = new BufferedReader(new InputStreamReader(is));

                int col = 0;
                int row = 0;

                while(col < GameWindow.maxWorldCol && row < GameWindow.maxWorldRow){
                    String line = br.readLine();

                    while(col < GameWindow.maxWorldCol){
                        String numbers[] = line.split(" ");

                        int num = Integer.parseInt(numbers[col]);

                        mapTileNum[col][row] = num;
                        col++;
                    }
                    if(col == GameWindow.maxWorldCol){
                        col = 0;
                        row++;
                    }

                }
                br.close();
            }catch(Exception e){
                System.out.println(e);
            }*/
    }
    public void loadMap(String filePath) throws MapException {
        try{
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            System.out.println(br);
            int col = 0;
            int row = 0;

            while(col < GameWindow.maxWorldCol && row < GameWindow.maxWorldRow){
                String line = br.readLine();

                while(col < GameWindow.maxWorldCol){
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if(col == GameWindow.maxWorldCol){
                    col = 0;
                    row++;
                }

            }
            br.close();
        }catch(Exception e){
            throw new MapException(e + " loadmap path problem!");
        }
    }
    public void Draw(Graphics g){
       int worldCol = 0;
       int worldRow = 0;

       while(worldCol < GameWindow.maxWorldCol && worldRow < GameWindow.maxWorldRow){

           int tileNum = mapTileNum[worldCol][worldRow];

           int worldX = worldCol * Tile.TILE_WIDTH;
           int worldY = worldRow * Tile.TILE_HEIGHT;
           int screenX = worldX - player.worldX + player.screenX;
           int screenY = worldY - player.worldY + player.screenY;

           if(worldX + Tile.TILE_WIDTH > player.worldX - player.screenX &&
              worldX - Tile.TILE_WIDTH < player.worldX + player.screenX &&
              worldY + Tile.TILE_HEIGHT > player.worldY - player.screenY &&
              worldY - Tile.TILE_HEIGHT < player.worldY + player.screenY) {
               Tile.tiles[tileNum].Draw(g, screenX, screenY);
           }
           worldCol++;

           if(worldCol == GameWindow.maxWorldCol){
               worldCol = 0;
               worldRow++;
           }
       }
    }

   // public static TileManager getMap(){
        //return maps[mapNo];
    //}
}
