package Stronghold.GameObjects.Building;

import java.util.*;

import Stronghold.Game;
import Stronghold.ResourceManager;
import Stronghold.Xform;
import org.json.simple.JSONArray;

public abstract class Building {

    String buildingName;

    public int buildingLevel;
    public Object resourceChanges;
    public int buildingInitialHealth;
    public String owner;

    public int[] location;
    public final Xform xform = new Xform();

    Building(String name, int[] location, String owner, String imageName) {

        buildingName = name;
        this.location = location;
        this.owner = owner;

        Map buildingInfo = (Map) ResourceManager.getJson("JSON-BUILDING").get(name);

        JSONArray sizeJsonArr = (JSONArray) buildingInfo.get("size");
        double[] size = new double[] {
                Double.parseDouble(sizeJsonArr.get(0).toString()),
                Double.parseDouble(sizeJsonArr.get(1).toString()),
                Double.parseDouble(sizeJsonArr.get(2).toString())
        };


        Game.createRect3D(xform, size[0],0, size[1],location[0],size[2], location[1],null,imageName,true);


        // Here We Assign Values !!!
            /*

            public int buildingLevel;
            public Object resourceChanges;
            public int buildingInitialHealth;

             */

//            buildingLevel = 1;
//            resourceChanges = buildingInfo.get("resource_changes");
//            buildingInitialHealth = ((Map)(buildingInfo.get("building_initial_health"))).get("1");
//            System.out.println(resourceChanges);

    }

}
