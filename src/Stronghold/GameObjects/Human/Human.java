package Stronghold.GameObjects.Human;

import Stronghold.ResourceManager;
import Stronghold.Xform;

import java.util.Map;

public abstract class Human {

    public int foodCost;
    public int humanInitialHealth;
    public String humanName;
    public String side;
    public int[] location;
    public Map humanInfo;
    public final Xform xform = new Xform();

    Human(String name, int[] location, String side) {

        this.location = location;
        this.humanName= name;
        this.side = side;

        humanInfo = (Map) ResourceManager.getJson("JSON-HUMAN").get(name);

        foodCost = Integer.parseInt(humanInfo.get("food_cost").toString());
        humanInitialHealth = Integer.parseInt(humanInfo.get("human_initial_health").toString());

    }

    abstract public void buildHuman(String humanName);

}
