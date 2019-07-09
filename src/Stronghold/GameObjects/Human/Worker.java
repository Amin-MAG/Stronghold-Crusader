package Stronghold.GameObjects.Human;

import Stronghold.Game;

public class Worker extends Human {

    public Worker(String side, int[] location) {

        super("worker", location, side);
        buildHuman(side);

    }

    @Override
    public void buildHuman(String humanName) {

        Game.createRect3D(xform,30,0,60,location[0],15,location[1],null,"HUMAN-WORKER-" + side,true);

    }

}
