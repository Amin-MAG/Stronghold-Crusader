package Stronghold.GameObjects.Building;

public class Castle extends Building{

    public final static String buildingImageName = "BUILDING-CASTLE";

    public Castle(int[] location, String owner){

        super("castle", location, owner, buildingImageName);

    }

}
