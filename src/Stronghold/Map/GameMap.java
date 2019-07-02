package Stronghold.Map;

import java.io.FileReader;

import Stronghold.Map.Tile.Tile;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class GameMap {

    Tile[][] gameBoard;

    public GameMap(String jsonFile) {

        String name;
        JSONParser parser = new JSONParser();

        try {

            Object mapObject = parser.parse(new FileReader(jsonFile));

            JSONObject jsonObject = (JSONObject) mapObject;

            String mapName = (String) jsonObject.get("name");
            JSONArray mapBoard = (JSONArray) jsonObject.get("board");

            System.out.println(mapName);
            System.out.println(mapBoard);


        } catch (Exception e) {}


    }


}
