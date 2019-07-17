package Stronghold.Gui;

import Stronghold.Gui.Text.ResourceText;

import Stronghold.Game;
import Stronghold.GameController;

import javafx.scene.Group;
import javafx.scene.input.MouseEvent;

public class GameMenu {

    public Group menuPage = new Group();
    public Group buildingBtn = new Group();
    public Group resourceBox = new Group();

    public BuildingInfoPage castleInfo = new BuildingInfoPage("CASTLE");
    public BuildingInfoPage farmInfo = new BuildingInfoPage("FARM");
    public BuildingInfoPage workshopInfo = new BuildingInfoPage("WORKSHOP");
    public BuildingInfoPage barracksInfo = new BuildingInfoPage("BARRACKS");

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


    public GameMenu(MODES mode) {

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


        // Building info Rendering

        castleInfo.render();

        farmInfo.render();

        barracksInfo.render();

        workshopInfo.render();

        // Initial TEXT Rendering

        woodText = new ResourceText("wood",1183,972);
        goldText = new ResourceText("gold",1175,1005);
        foodText = new ResourceText("food",1168,1035);

        resourceBox.getChildren().add(woodText);
        resourceBox.getChildren().add(goldText);
        resourceBox.getChildren().add(foodText);

        menuPage.getChildren().add(resourceBox);

        // Render !

        render();

    }

    public void changeMode(MODES mode) {

        delete();
        this.mode = mode;
        render();

    }

    public void delete() {

        for (int i = 1; i < menuPage.getChildren().size(); i++) {

            if ( menuPage.getChildren().get(i) != resourceBox ) menuPage.getChildren().remove(i);

        }


    }

    public void render() {

        // Game Menu


        switch (mode) {

            case MAIN:

                menuPage.getChildren().add(buildingBtn);
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

        foodText.updateText();
        woodText.updateText();
        goldText.updateText();

    }



}