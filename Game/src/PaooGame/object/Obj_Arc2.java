package PaooGame.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Arc2 extends SuperObject{
    public Obj_Arc2(){
        name="Arc2";
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/textures/arc2.png"));
        }catch(IOException e) {
            e.printStackTrace();;
        }
    }
}
