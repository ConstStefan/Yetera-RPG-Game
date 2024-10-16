package PaooGame.Entities;

import PaooGame.Graphics.Assets;
import PaooGame.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Door {
    Graphics g;

    private int x ;
    private int y ;
    private int width = 85;
    private int height = 72;



    public Door(Graphics g,int x,int y) {
        this.g = g;
        this.x = x;
        this.y=y;
    }



    public void draw(Graphics g){
        BufferedImage image = null;
        image = Assets.door;
        g.drawImage(image, x, y, width, height, null);
    }
}
