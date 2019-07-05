package Stronghold.Human;

import Stronghold.ResourceManager;
import java.util.Map;

public abstract class Human {

    public String humanName;
    public int foodCost;
    public int humanInitialHealth;

    Human(String name) {

        humanName = name;

        Map humanInfo = (Map) ResourceManager.getJson("JSON-HUMAN").get(name);
        System.out.println(humanInfo);

        // Here We Assign Values !!
            /*

            public int foodCost;
            public int humanInitialHealth;

             */

    }

}
