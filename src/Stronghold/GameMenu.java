package Stronghold;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

import javax.sound.sampled.LineEvent;
import java.util.zip.GZIPOutputStream;

public class GameMenu {

    public Group constructionMenu = new Group();
    public Group buildingBtn = new Group();
    public Group castleInfo = new Group();
    public Group farmInfo = new Group();
    public Group workshopInfo = new Group();
    public Group barracksInfo = new Group();

    public Group upgrade = new Group();

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

        Game.createRect(constructionMenu, 1925,282, -3, 801, "GAME-MENU");
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

        Group levels = new Group();
        Group level[] = new Group[]{
                new Group(),
                new Group(),
                new Group(),
                new Group(),
                new Group()
        };
        Group addVassal = new Group();

        Game.createRect(level[0],50,50,500 - 60, 1010,"GAME-MENU-LEVEL1-UPGRADED");
        Game.createRect(level[1],50,50,555 - 60, 1010,"GAME-MENU-LEVEL2");
        Game.createRect(level[2],50,50,610 - 60, 1010,"GAME-MENU-LEVEL3");
        Game.createRect(level[3],50,50,665 - 60, 1010,"GAME-MENU-LEVEL4");
        Game.createRect(level[4],50,50,720 - 60, 1010,"GAME-MENU-LEVEL5");

        Game.createRect(upgrade,70,70,720, 1000,"GAME-MENU-UPGRADE");
        Game.createRect(addVassal, 75,60,850,1000,"GAME-MENU-ADD-VASSAL");

        levels.getChildren().addAll(level);
        levels.getChildren().add(upgrade);

        castleInfo.getChildren().add(levels);
        castleInfo.getChildren().add(addVassal);

        // Render !

        render();

    }

    public void changeMode(MODES mode) {

        delete();
        this.mode = mode;
        render();

    }

    public void delete() {

        constructionMenu.getChildren().remove(1);

    }

    public void render() {

        // Game Menu


        switch (mode) {

            case MAIN:

                constructionMenu.getChildren().add(buildingBtn);

                break;

            case CASTLE:

                constructionMenu.getChildren().add(castleInfo);
                break;

            default:
                System.out.println("Default");
                break;

        }



    }

    public static void main(String[] args) throws InterruptedException {

        GameMenu amin = new GameMenu(MODES.MAIN);
        amin.render();

    }


}
