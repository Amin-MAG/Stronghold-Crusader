package Stronghold;

import Stronghold.GameObjects.Building.*;
import Stronghold.GameObjects.Building.Farm;
import Stronghold.Gui.GameMenu;
import Stronghold.Map.Tile.Tile;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class GameController implements EventHandler<MouseEvent> {

    public static boolean buildingFarmIsSelected = false;
    public static boolean buildingWorkshopIsSelected = false;
    public static boolean buildingBarracksIsSelected = false;
    public static Farm newFarm = null;
    public static Barracks newBarracks = null;
    public static Workshop newWorkshop = null;
    public String evenName;
    public Tile thisEarth;

    public GameController(String name) {

        super();
        evenName = name;

    }

    public GameController(String name, Tile tile) {

        super();
        evenName = name;
        thisEarth = tile;

    }


    @Override
    public void handle(MouseEvent event) {


        if (evenName.equals("EARTH")) {

            Game.mousePosOnEarth = new double[] {thisEarth.xform.getChildren().get(0).getTranslateX(), thisEarth.xform.getChildren().get(0).getTranslateZ()};
            if (event.isPrimaryButtonDown()) {

                Game.gameMenu.changeMode(GameMenu.MODES.MAIN);

            }
//            thisEarth.xform.getChildren().get(0).setRotate(65);
//            System.out.println(Arrays.toString(Game.mousePosOnEarth));

        }

        if (evenName.equals("BARRACKS") && !GameController.buildingBarracksIsSelected) {

            GameController.buildingBarracksIsSelected = true;
            newBarracks = new Barracks(new int[]{(int) event.getX(), (int) event.getY()}, "Amin");
            Game.world.getChildren().addAll(newBarracks.xform);

        }

        if (evenName.equals("FARM") && !GameController.buildingFarmIsSelected) {

            GameController.buildingFarmIsSelected = true;
            newFarm = new Farm(new int[]{(int) event.getX(), (int) event.getY()}, "Amin");
            Game.world.getChildren().addAll(newFarm.xform);

        }

        if (evenName.equals("WORKSHOP") && !GameController.buildingWorkshopIsSelected) {

            GameController.buildingWorkshopIsSelected = true;
            newWorkshop = new Workshop(new int[]{(int) event.getX(), (int) event.getY()}, "Amin");
            Game.world.getChildren().addAll(newWorkshop.xform);

        }



//        if (!GameController.buildingFarmIsSelected) {
//
//            System.out.println("construction selected");
//            GameController.buildingFarmIsSelected = true;
//            newFarm = new Farm(new int[]{(int)event.getX(),(int)event.getY()}, "Amin");
//            Game.world.getChildren().addAll(newFarm.xform);
//
//        }
//
//        if (!GameController.buildingWorkshopIsSelected) {
//
//            System.out.println("construction selected");
//            GameController.buildingWorkshopIsSelected = true;
//            newWorkshop = new Workshop(new int[]{(int)event.getX(),(int)event.getY()}, "Amin");
//            Game.world.getChildren().addAll(newWorkshop.xform);
//
//        }


//
//        if (mouseEventName.equals("FARM-CONSTRUCTION")) {
//
//            if(!GameController.buildingFarmIsSelected) {
//
//                System.out.println("construction selected");
//                GameController.buildingBarracksIsSelected = true;
//                newFarm = new Farm(new int[]{(int)event.getX(), (int)event.getY()}, "Amin");
//                newFarm.xform.setRotateY(-45);
//                Game.world.getChildren().addAll(newFarm.xform);
//
//
//                while (true) {
//
//                    if
//
//                }
//
//
//            } else {
//
//                System.out.println("location selected");
//                GameController.buildingBarracksIsSelected = false;
//
//            }
//
//        }
//
//        if (newFarm != null && GameController.buildingFarmIsSelected) {
//
//            System.out.println("changing");
//            newFarm.xform.setTranslate(event.getX(), event.getY());
//
//        }






    }


}
