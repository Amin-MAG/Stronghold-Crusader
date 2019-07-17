package Stronghold.GameObjects.Building;

import java.util.*;

import Stronghold.Game;
import Stronghold.ResourceManager;
import Stronghold.Xform;
import org.json.simple.JSONArray;

public abstract class Building {

    String buildingName;

    public int buildingLevel;
    public Map buildingResourceRate;
    public HashMap<String, Integer> resourceRate = new HashMap<>();
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


        Game.createRect3D(xform, size[0],0, size[1],location[0],size[2], -location[1],null,imageName,true);


        // Level Assigning

        buildingLevel = 1;

        // Resource Effect

        buildingResourceRate = (Map) (buildingInfo.get("resource_changes"));
        setResourceRate();

//            public int buildingInitialHealth;


//            buildingLevel = 1;
//            resourceChanges = buildingInfo.get("resource_changes");
//            buildingInitialHealth = ((Map)(buildingInfo.get("building_initial_health"))).get("1");
//            System.out.println(resourceChanges);

    }

    public void setResourceRate() {

        resourceRate.put("wood", Integer.parseInt( ( (Map)(buildingResourceRate.get("wood")) ).get(buildingLevel+ "").toString() ));
        resourceRate.put("food", Integer.parseInt( ( (Map)(buildingResourceRate.get("food")) ).get(buildingLevel+ "").toString() ));
        resourceRate.put("gold", Integer.parseInt( ( (Map)(buildingResourceRate.get("gold")) ).get(buildingLevel+ "").toString() ));

    }

}