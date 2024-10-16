package PaooGame.object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Obj_door extends SuperObject{
    public Obj_door(){
        name="door";
        try{
            image= ImageIO.read(getClass().getResourceAsStream("/textures/door.png"));
        }catch(IOException e) {
            e.printStackTrace();;
        }
        collision=true;
    }
}
