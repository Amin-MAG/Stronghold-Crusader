import Building.Building;
import Human.Human;
import Human.Soldier;
import Map.GameMap;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Game {

    final static String jsonGameSettingAddress = "JsonFiles/game_settings.json";

    public Map resources;
    public Map resourceRate;
    public Object test;
    public GameMap map;
    public ArrayList<Building> buildings;
    public ArrayList<Human> noneSoldjers;
    public ArrayList<Soldier> soldjers;

    Game() {

        JSONParser parser = new JSONParser();

        try {

            Object mapObject = parser.parse(new FileReader(jsonGameSettingAddress));

            JSONObject jsonObject = (JSONObject) mapObject;

            resources = (Map) jsonObject.get("initial_resources");
            resourceRate = (Map) jsonObject.get("initial_resource_rate");

        } catch (Exception e) {}

    }

}
