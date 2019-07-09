package Stronghold.GameObjects.Building;

public class Workshop extends Building {

    public final static String buildingImageName = "BUILDING-WORKSHOP";

    public Workshop(int[] location, String owner) {

        super("workshop", location, owner, buildingImageName);

    }

}
