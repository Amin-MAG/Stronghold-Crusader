package Stronghold.Building;

public class Farm extends Building {

    public final static String buildingImageName = "BUILDING-FARM";

    public Farm(int[] location, String owner) {

        super("farm", location, owner, buildingImageName);

    }

}
