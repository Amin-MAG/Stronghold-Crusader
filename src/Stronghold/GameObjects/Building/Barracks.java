package Stronghold.GameObjects.Building;

public class Barracks extends Building {

    public final static String buildingImageName = "BUILDING-BARRACKS";

    public Barracks(int[] location, String owner) {

        super("barracks", location, owner, buildingImageName);

    }

}
