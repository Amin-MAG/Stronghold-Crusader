package Stronghold.GameObjects.Animation;

import Stronghold.Game;
import Stronghold.ResourceManager;
import Stronghold.Xform;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;
import org.json.simple.JSONArray;

import java.util.Map;

public class GameAnimation {

    public int[] location;
    public final Xform xform = new Xform();
    public int cycle;
    public double[] size;
    public String animationName;
    public String animationImage;


    public GameAnimation(String animName, int[] location) {

        Map objectInfo = (Map) ResourceManager.getJson("JSON-OBJECTS").get(animName);

        JSONArray sizeJsonArr = (JSONArray) objectInfo.get("size");

        cycle = Integer.parseInt(objectInfo.get("cycle").toString());

        size = new double[] {
                Double.parseDouble(sizeJsonArr.get(0).toString()),
                Double.parseDouble(sizeJsonArr.get(1).toString()),
                Double.parseDouble(sizeJsonArr.get(2).toString())
        };

        this.location = location;
        this.animationName = animName;
        this.animationImage = objectInfo.get("animation_image").toString();

    }

    public Xform buildFrame(int n) {

        xform.getChildren().clear();
        Game.createRect3D(xform, size[0],0, size[1], location[0], size[2], location[1],null,animationImage + "-" + (n%cycle),true);

        try {
            String pos = animationName.split("-")[1];


            if (pos.equals("right")) {

                location[0]++;

            } else if (pos.equals("left")) {

                location[0]--;

            } else if (pos.equals("up")) {

                location[1]--;

            } else if (pos.equals("down")) {

                location[1]++;

            }


        } catch (Exception e) {}
        return xform;

    }

}


