package Human;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.Map;

public abstract class Human {

    final static String jsonHumanSettingAddress = "JsonFiles/human_settings.json";

    public String humanName;
    public int foodCost;
    public int humanInitialHealth;

    Human(String name) {

        humanName = name;

        JSONParser parser = new JSONParser();

        try {

            Object mapObject = parser.parse(new FileReader(jsonHumanSettingAddress));

            JSONObject jsonObject = (JSONObject) mapObject;

            Map humanInfo = (Map) jsonObject.get(name);
            System.out.println(humanInfo);


            // Here We Assign Values !!
            /*

            public int foodCost;
            public int humanInitialHealth;

             */

        } catch (Exception e){}

    }

}
