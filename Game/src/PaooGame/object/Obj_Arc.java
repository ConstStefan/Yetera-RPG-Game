package PaooGame.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_Arc extends SuperObject{
    public Obj_Arc(){
        name="Arc";
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/textures/arc.png"));
        }catch(IOException e) {
            e.printStackTrace();;
        }
    }
}
