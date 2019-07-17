package Stronghold;

import Stronghold.Gui.Text.ResourceText;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;

public class GameMenu {

    public Group menuPage = new Group();
    public Group buildingBtn = new Group();
    public Group castleInfo = new Group();
    public Group farmInfo = new Group();
    public Group workshopInfo = new Group();
    public Group barracksInfo = new Group();
    public Group resourceBox = new Group();

    public ResourceText woodText;
    public ResourceText goldText;
    public ResourceText foodText;


    private MODES mode;
    public static enum MODES {

        MAIN,
        CASTLE,
        FARM,
        BARRACKS,
        WORKSHOP

    }


    GameMenu(MODES mode) {

        this.mode = mode;

        // Create Buildings Button

        Group farmButton = new Group();
        Group barracksButton = new Group();
        Group workshopButton = new Group();

        Game.createRect(menuPage, 1925,282, -3, 801, "GAME-MENU");
        Game.createRect(farmButton, 170, 150, 500, 930, "BUILDING-FARM");
        Game.createRect(barracksButton, 250, 160, 680, 920, "BUILDING-BARRACKS");
        Game.createRect(workshopButton, 91, 84, 930, 940, "BUILDING-WORKSHOP");

        buildingBtn.getChildren().add(farmButton);
        buildingBtn.getChildren().add(barracksButton);
        buildingBtn.getChildren().add(workshopButton);

        buildingBtn.getChildren().get(0).addEventHandler(MouseEvent.MOUSE_CLICKED, new GameController("FARM"));
        buildingBtn.getChildren().get(1).addEventHandler(MouseEvent.MOUSE_CLICKED, new GameController("BARRACKS"));
        buildingBtn.getChildren().get(2).addEventHandler(MouseEvent.MOUSE_CLICKED, new GameController("WORKSHOP"));


        // Castle Building

        Group addVassal = new Group();
        Game.createRect(addVassal, 75,60,850,1000,"GAME-MENU-ADD-VASSAL");

        castleInfo.getChildren().add(getLevels(1));
        castleInfo.getChildren().add(addVassal);


        // Farm Building

        farmInfo.getChildren().add(getLevels(3));


        // Barracks

        barracksInfo.getChildren().add(getLevels(2));

        // Workshop

        workshopInfo.getChildren().add(getLevels(5));

        // Initial TEXT Rendering

        woodText = new ResourceText("wood",1190,972);
        goldText = new ResourceText("gold",1180,1005);
        foodText = new ResourceText("food",1170,1035);

        resourceBox.getChildren().add(woodText);
        resourceBox.getChildren().add(goldText);
        resourceBox.getChildren().add(foodText);

        menuPage.getChildren().add(resourceBox);

        // Render !

        render();

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

    public void changeMode(MODES mode) {

        delete();
        this.mode = mode;
        render();

    }

    public void delete() {

//        menuPage.getChildren().remove(1);

    }

    public void render() {

        // Game Menu


        switch (mode) {

            case MAIN:

//                menuPage.getChildren().add(buildingBtn);

                break;

            case CASTLE:

                menuPage.getChildren().add(castleInfo);
                break;

            case FARM:

                menuPage.getChildren().add(farmInfo);
                break;

            case BARRACKS:

                menuPage.getChildren().add(barracksInfo);
                break;

            case WORKSHOP:

                menuPage.getChildren().add(workshopInfo);
                break;
                
            default:
                System.out.println("Default");
                break;

        }

    }

    public void updateResource() {



    }



}
