package Stronghold.Map.Tile;

import Stronghold.Game;
import Stronghold.Xform;

public abstract class Tile {

    public boolean movementIsPossible;
    public String imageAddress;
    public final Xform xform = new Xform();
    public int[] location = new int[2];


    Tile(String image, boolean movementIsPossible, int[] loacation) {

        this.imageAddress = image;
        this.movementIsPossible = movementIsPossible;
        this.location = loacation;


        buildTile();

    }

    public void buildTile() {

        Game.createRect3D(xform, 350,0, 350,location[0],0 , location[1],null,imageAddress ,false);

    }

}
