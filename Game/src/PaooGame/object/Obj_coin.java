package PaooGame.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_coin extends SuperObject{
    public Obj_coin(){
        name="Coin";
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/textures/coin_bronze.png"));
        }catch(IOException e) {
            e.printStackTrace();;
        }
        collision=true;
    }

}
