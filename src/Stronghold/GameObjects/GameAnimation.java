package Stronghold.GameObjects;

import Stronghold.Game;
import Stronghold.ResourceManager;
import Stronghold.Xform;
import org.json.simple.JSONArray;

import java.util.Map;

public class GameAnimation {

    public int[] location;
    public final Xform xform = new Xform();
    public int cycle = 13;
    public double[] size;
    public String animationName;
    public String animationImage;


    public GameAnimation(String animName, int[] location) {

        Map objectInfo = (Map) ResourceManager.getJson("JSON-OBJECTS").get(animName);

        JSONArray sizeJsonArr = (JSONArray) objectInfo.get("size");

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

        return xform;

    }

}


