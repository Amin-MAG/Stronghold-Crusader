package Stronghold.Building;

import java.io.FileReader;
import java.util.*;

import Stronghold.Game;
import Stronghold.ResourceManager;
import Stronghold.Xform;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;


public abstract class Building {

    final static String jsonBuildingSettingAddress = "\\JsonFiles\\building_settings.json";

    String buildingName;

    public int buildingLevel;
    public Object resourceChanges;
    public int buildingInitialHealth;
    public String owner;

    private ArrayList<Integer> size;
    public int[] location;
    public final Xform xform = new Xform();

    Building(String name, int[] location, String owner, String imageName) {

        buildingName = name;
        this.location = location;
        this.owner = owner;

        JSONParser parser = new JSONParser();

        try {

            Object mapObject = parser.parse(new FileReader(ResourceManager.gameAddress + jsonBuildingSettingAddress));

            JSONObject jsonObject = (JSONObject) mapObject;

            Map buildingInfo = (Map) jsonObject.get(buildingName);

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

        } catch (Exception e) {}



    }

}
