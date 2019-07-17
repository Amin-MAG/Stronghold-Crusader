package Stronghold.Gui;

import Stronghold.Game;
import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
import javafx.scene.Group;

public class BuildingInfoPage extends Group {

    public String buildingName;
    private int level = 1;

    public BuildingInfoPage(String buildingName) {

        super();

        this.buildingName = buildingName;

    }

    public void render() {

        switch (buildingName) {

            case "CASTLE":

                Group addVassal = new Group();
                Game.createRect(addVassal, 75,60,850,1000,"GAME-MENU-ADD-VASSAL");
                getChildren().add(getLevels(level));
                getChildren().add(addVassal);
                break;

            case "FARM":

                getChildren().add(getLevels(3));
                break;

            case "WORKSHOP":

                getChildren().add(getLevels(5));
                break;

            case "BARRACKS":

                getChildren().add(getLevels(4));
                break;

            default:
                break;

        }

    }

    public Group getLevels(int n) {

        Group levels = new Group();
        Group level[] = new Group[]{
                new Group(),
                new Group(),
                new Group(),
                new Group(),
                new Group()
        };
        Group upgrade = new Group();

        for (int i = 1; i < 6; i++) {

            String imageName = "GAME-MENU-LEVEL" + i;
            if (i <= n) imageName += "-UPGRADED";
            Game.createRect(level[i-1],50,50,440 + 55*(i-1), 1010,imageName);

        }


        if (n != 5) Game.createRect(upgrade,70,70,720, 1000,"GAME-MENU-UPGRADE");

        levels.getChildren().addAll(level);
        levels.getChildren().add(upgrade);

        return levels;

    }

    public void setLevel(int level) {

        this.level = level;

    }
}
