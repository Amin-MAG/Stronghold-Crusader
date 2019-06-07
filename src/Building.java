import java.util.HashMap;


import java.io.FileReader;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public abstract class Building {

    final static String jsonBuildingSettingAddress = "JsonFiles/building_settings.json";

    public int buildingLevel;
    public Object resourceChanges;
    public int buildingInitialHealth;

    Building(String name) {

        JSONParser parser = new JSONParser();

        try {

            Object mapObject = parser.parse(new FileReader(jsonBuildingSettingAddress));

            JSONObject jsonObject = (JSONObject) mapObject;

            Map buildingInfo = (Map) jsonObject.get(name);


            // Here We Assign Values !!!

//            buildingLevel = 1;
//            resourceChanges = buildingInfo.get("resource_changes");
//            buildingInitialHealth = ((Map)(buildingInfo.get("building_initial_health"))).get("1");
//            System.out.println(resourceChanges);

        } catch (Exception e) {}



    }

}
