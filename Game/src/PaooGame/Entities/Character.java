package PaooGame.Entities;

import PaooGame.Graphics.Assets;
import PaooGame.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class Character {
    Graphics g;
    KeyHandler keyH;

    private int x = 40;
    private int y = 60;
    private int width = 18;
    private int height = 32;
    private int speed = 4;


    public Character(Graphics g, KeyHandler keyH) {
        this.g = g;
        this.keyH = keyH;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public void update() {
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            if (keyH.upPressed == true) {
                if (y - speed > 0) {
                    y -= speed;
                } else {
                    y -= y;
                }
            } else if (keyH.downPressed == true) {
                if (y + speed < 720 - height) {
                    y += speed;
                } else {
                    y += (720 - height - y);
                }
            } else if (keyH.leftPressed == true) {
                if (x - speed > 0) {
                    x -= speed;
                } else {
                    x -= x;
                }
            } else if (keyH.rightPressed == true) {
                if (x + speed < 1280 - width) {
                    x += speed;
                } else {
                    x += (1280 - width - x);
                }
            }
        }
    }

    public void draw(Graphics g){
        BufferedImage image = null;
        image = Assets.staticCharacter;
        g.drawImage(image, x, y, width, height, null);
    }
}
