package Stronghold.GameObjects.Human;

import Stronghold.Game;

public class Vassal extends Human{

    public Vassal(String side, int[] location) {

        super("vassal", location, side);
        buildHuman(side);

    }

    @Override
    public void buildHuman(String side) {

        Game.createRect3D(xform,30,0,60,location[0],15,location[1],null,"HUMAN-VASSAL-" + side,true);

    }

}
