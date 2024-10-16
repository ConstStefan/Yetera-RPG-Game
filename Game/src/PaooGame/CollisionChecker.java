package PaooGame;

import PaooGame.GameWindow.GameWindow;
import PaooGame.Tiles.TileManager;
import PaooGame.Tiles.Tile;
import PaooGame.Tiles.TileManager;
import PaooGame.entity.Entity;

public class CollisionChecker {

    Game game;
    TileManager map;
    GameWindow gp;

    public CollisionChecker(Game game, TileManager map,GameWindow gp){
        this.game = game;
        this.map = map;
        this.gp=gp;
    }

    public void checkTile(Entity entity){

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / Tile.TILE_WIDTH;
        int entityRightCol = entityRightWorldX / Tile.TILE_WIDTH;
        int entityTopRow = entityTopWorldY / Tile.TILE_HEIGHT;
        int entityBottomRow = entityBottomWorldY / Tile.TILE_HEIGHT;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / Tile.TILE_HEIGHT;
                tileNum1 = map.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = map.mapTileNum[entityRightCol][entityTopRow];
                if(Tile.tiles[tileNum1].IsSolid() || Tile.tiles[tileNum2].IsSolid()){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY - entity.speed) / Tile.TILE_HEIGHT;
                tileNum1 = map.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = map.mapTileNum[entityRightCol][entityBottomRow];
                if(Tile.tiles[tileNum1].IsSolid() || Tile.tiles[tileNum2].IsSolid()){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / Tile.TILE_HEIGHT;
                tileNum1 = map.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = map.mapTileNum[entityLeftCol][entityBottomRow];
                if(Tile.tiles[tileNum1].IsSolid() || Tile.tiles[tileNum2].IsSolid()){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / Tile.TILE_HEIGHT;
                tileNum1 = map.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = map.mapTileNum[entityRightCol][entityBottomRow];
                if(Tile.tiles[tileNum1].IsSolid() || Tile.tiles[tileNum2].IsSolid()){
                    entity.collisionOn = true;
                }
                break;
        }
    }
    public int checkObject(Entity entity,boolean player) {
        int index = 999;
        for (int i = 0; i < game.obj.length; i++) {
            if (game.obj[i] != null) {
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                game.obj[i].solidArea.x = game.obj[i].worldX + game.obj[i].solidArea.x;
                game.obj[i].solidArea.y = game.obj[i].worldY + game.obj[i].solidArea.y;

                switch (entity.direction) {
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if (entity.solidArea.intersects(game.obj[i].solidArea)) {
                           if(game.obj[i].collision==true)
                           {
                               entity.collisionOn=true;
                           }
                           if(player==true){
                               index=i;
                           }
                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if (entity.solidArea.intersects(game.obj[i].solidArea)) {
                            if(game.obj[i].collision==true)
                            {
                                entity.collisionOn=true;
                            }
                            if(player==true){
                                index=i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if (entity.solidArea.intersects(game.obj[i].solidArea)) {
                            if(game.obj[i].collision==true)
                            {
                                entity.collisionOn=true;
                            }
                            if(player==true){
                                index=i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidArea.x += entity.speed;
                        if (entity.solidArea.intersects(game.obj[i].solidArea)) {
                            if(game.obj[i].collision==true)
                            {
                                entity.collisionOn=true;
                            }
                            if(player==true){
                                index=i;
                            }
                            break;
                        }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                game.obj[i].solidArea.x = game.obj[i].solidAreaDefaultX;
                game.obj[i].solidArea.y = game.obj[i].solidAreaDefaultY;

            }

        }

        return index;
    }
}
