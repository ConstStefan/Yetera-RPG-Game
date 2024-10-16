package PaooGame;

import PaooGame.Game;
import PaooGame.Tiles.Tile;
import PaooGame.object.*;
import PaooGame.object.Obj_coin;

public class AssetSetter {
    Game g2;
    public AssetSetter(Game g2){
        this.g2=g2;

    }
    public void setObjectLvl2(){
        for(int i=0;i<g2.obj.length;i++){
            if(g2.obj[i]!=null)
            {
                g2.obj[i]=null;
            }
        }
        g2.obj[0]=new Obj_coin();
        g2.obj[0].worldX=23* Tile.TILE_WIDTH;
        g2.obj[0].worldY=7*Tile.TILE_HEIGHT;


        g2.obj[1]=new Obj_coin();
        g2.obj[1].worldX=28* Tile.TILE_WIDTH;
        g2.obj[1].worldY=24*Tile.TILE_HEIGHT;

        g2.obj[2]=new Obj_coin();
        g2.obj[2].worldX=45* Tile.TILE_WIDTH;
        g2.obj[2].worldY=10*Tile.TILE_HEIGHT;

        g2.obj[3]=new Obj_door();
        g2.obj[3].worldX=12* Tile.TILE_WIDTH;
        g2.obj[3].worldY=37*Tile.TILE_HEIGHT;

        g2.obj[4]=new Obj_coin();
        g2.obj[4].worldX=41* Tile.TILE_WIDTH;
        g2.obj[4].worldY=29*Tile.TILE_HEIGHT;

        g2.obj[5]=new Obj_Arc();
        g2.obj[5].worldX=6* Tile.TILE_WIDTH;
        g2.obj[5].worldY=9* Tile.TILE_HEIGHT;

        g2.obj[6]=new Obj_Arc();
        g2.obj[6].worldX=2* Tile.TILE_WIDTH;
        g2.obj[6].worldY=36*Tile.TILE_HEIGHT;

        g2.obj[7]=new Obj_Arc();
        g2.obj[7].worldX=25* Tile.TILE_WIDTH;
        g2.obj[7].worldY=4*Tile.TILE_HEIGHT;

        g2.obj[8]=new Obj_Arc();
        g2.obj[8].worldX=45* Tile.TILE_WIDTH;
        g2.obj[8].worldY=44*Tile.TILE_HEIGHT;

        g2.obj[9]=new Obj_Arc();
        g2.obj[9].worldX=23* Tile.TILE_WIDTH;
        g2.obj[9].worldY=33*Tile.TILE_HEIGHT;

        g2.obj[10]=new Obj_Chest();
        g2.obj[10].worldX=12* Tile.TILE_WIDTH;
        g2.obj[10].worldY=34*Tile.TILE_HEIGHT;

        g2.obj[11]=new Obj_coin();
        g2.obj[11].worldX=1* Tile.TILE_WIDTH;
        g2.obj[11].worldY=1*Tile.TILE_HEIGHT;

        g2.obj[12]=new Obj_Arc();
        g2.obj[12].worldX=29* Tile.TILE_WIDTH;
        g2.obj[12].worldY=36*Tile.TILE_HEIGHT;
    }
    public void setObject(){
        for(int i=0;i<g2.obj.length;i++){
            if(g2.obj[i]!=null)
            {
                g2.obj[i]=null;
            }
        }
        g2.obj[0]=new Obj_coin();
        g2.obj[0].worldX=23* Tile.TILE_WIDTH;
        g2.obj[0].worldY=7*Tile.TILE_HEIGHT;


        g2.obj[1]=new Obj_coin();
        g2.obj[1].worldX=28* Tile.TILE_WIDTH;
        g2.obj[1].worldY=24*Tile.TILE_HEIGHT;

        g2.obj[2]=new Obj_coin();
        g2.obj[2].worldX=45* Tile.TILE_WIDTH;
        g2.obj[2].worldY=10*Tile.TILE_HEIGHT;

        g2.obj[3]=new Obj_door();
        g2.obj[3].worldX=12 * Tile.TILE_WIDTH;
        g2.obj[3].worldY=37*Tile.TILE_HEIGHT;

        g2.obj[4]=new Obj_coin();
        g2.obj[4].worldX=35* Tile.TILE_WIDTH;
        g2.obj[4].worldY=28*Tile.TILE_HEIGHT;

        g2.obj[5]=new Obj_Arc();
        g2.obj[5].worldX=4* Tile.TILE_WIDTH;
        g2.obj[5].worldY=3* Tile.TILE_HEIGHT;

        g2.obj[6]=new Obj_Arc();
        g2.obj[6].worldX=2* Tile.TILE_WIDTH;
        g2.obj[6].worldY=36*Tile.TILE_HEIGHT;

        g2.obj[7]=new Obj_Arc();
        g2.obj[7].worldX=25* Tile.TILE_WIDTH;
        g2.obj[7].worldY=2*Tile.TILE_HEIGHT;

        g2.obj[8]=new Obj_Arc();
        g2.obj[8].worldX=44* Tile.TILE_WIDTH;
        g2.obj[8].worldY=44*Tile.TILE_HEIGHT;

        g2.obj[9]=new Obj_Arc();
        g2.obj[9].worldX=23* Tile.TILE_WIDTH;
        g2.obj[9].worldY=41*Tile.TILE_HEIGHT;

        g2.obj[10]=new Obj_Chest();
        g2.obj[10].worldX=12* Tile.TILE_WIDTH;
        g2.obj[10].worldY=34*Tile.TILE_HEIGHT;
    }

}
