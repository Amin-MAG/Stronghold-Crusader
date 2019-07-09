package Stronghold;

import Stronghold.GameObjects.Building.*;
import Stronghold.GameObjects.GameAnimation;
import Stronghold.GameObjects.Human.*;
import Stronghold.GameObjects.NaturalObject.Chestnut;
import Stronghold.Map.GameMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.stage.Stage;


public class Game  {


    private static String mapName;

    private Map resources;
    private Map resourceRate;
    public GameMap gameMap;
    public HashMap<String, ArrayList<Building>> myBuildings;
    public HashMap<String, ArrayList<Building>> otherBuildings;
    public ArrayList<Human> noneSoldjers;
    public ArrayList<GameAnimation> gameObjectAnimations = new ArrayList<>();
    public static boolean haveCastle = false;


    // Main Objects

    final private Group root = new Group();
    final private Xform world = new Xform();


    // Camera

    final private PerspectiveCamera camera = new PerspectiveCamera(true);

    final private Xform cameraXform = new Xform();
    final private Xform cameraXform2 = new Xform();
    final private Xform cameraXform3 = new Xform();

    private static double CAMERA_INITIAL_DISTANCE = -2000;
    private static final double CAMERA_INITIAL_X_ANGLE = 55;
//    private static final double CAMERA_INITIAL_X_ANGLE = 55;
    private static final double CAMERA_INITIAL_Y_ANGLE = 135;
    private static final double CAMERA_NEAR_CLIP = 0.5;
    private static final double CAMERA_FAR_CLIP = 10000.0;


    // Mouse

//    private static final double CONTROL_MULTIPLIER = 0.1;
//    private static final double SHIFT_MULTIPLIER = 10.0;
//    private static final double ROTATION_SPEED = 2.0;
    private static final double MOUSE_SPEED = 2;
    private static final double TRACK_SPEED = 0.3;

    private double mousePosX;
    private double mousePosY;
    private double mouseOldX;
    private double mouseOldY;
    private double mouseDeltaX;
    private double mouseDeltaY;


    // Axises

    private static final double AXIS_LENGTH = 5000;
    final private Xform axisGroup = new Xform();


    // Groups

    public final static Xform earthGroup = new Xform();
    public final static Xform earthObjects = new Xform();
    private final Xform humanXfrom = new Xform();


    Game(String mapName) {

        // Should be Commented
        ResourceManager.initialization();
        gameMap = new GameMap("MAP-SAMPLE");



        Map jsonMap = ResourceManager.getJson("JSON-GAME");

        resources = (Map) jsonMap.get("initial_resources");
        resourceRate = (Map) jsonMap.get("initial_resource_rate");


        myBuildings = new HashMap<>();
        myBuildings.put("CASTLE", null);
        myBuildings.put("FARM", null);
        myBuildings.put("BARRACKS", null);
        myBuildings.put("WORKSHOP", null);

        addBuilding("CASTLE", -500, 0);

        addHuman("VASSAL-DOWN", -300, 0);
        addHuman("SWORDSMAN-DOWN", 600, 600);
        addHuman("SWORDSMAN-DOWN", -500, 500);

        addAnimation("TREE-CHESTNUT", 200, 800);

        world.getChildren().addAll(humanXfrom);

        /*
            DELAY !
         */

//        Task<Void> sleeper = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) { }
//                return null;
//            }
//        };
//        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent event) {
//                addBuilding("CASTLE", 0, 0);
//            }
//        });
//        new Thread(sleeper).start();

    }

    public void render(Stage primaryStage) {


        Scene scene = new Scene(root, 1600, 900, true);
        root.getChildren().add(world);
        scene.setFill(Color.GREY);
        handleMouse(scene, world);

        primaryStage.setTitle("Game");
        primaryStage.setScene(scene);

        buildCamera();
        startObjectAnimation();
        buildEarth();

//        Task<Void> sleeper = new Task<Void>() {
//            @Override
//            protected Void call() throws Exception {
//                try {
//                    Thread.sleep(5000);
//                } catch (InterruptedException e) {}
//                return null;
//            }
//        };
//        sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
//            @Override
//            public void handle(WorkerStateEvent event) {
//                removeBuilding(myBuildings.get("CASTLE").get(0));
//            }
//        });
//        new Thread(sleeper).start();
//        myBuildings.put("CASTLE", null);
//        System.out.println(world.getChildren());


        addBuilding("WORKSHOP", 0, 100);
        addBuilding("BARRACKS", 0, 300);
        addBuilding("FARM", 750, 0);


        scene.setCamera(camera);


        new AnimationTimer() {
            @Override
            public void handle(long now) {

                primaryStage.setFullScreen(true);

            }
        }.start();


        primaryStage.show();

    }

    public void startObjectAnimation() {

        long initialNanoTime = System.nanoTime();

        new AnimationTimer() {

            @Override
            public void handle(long now) {

                earthObjects.getChildren().clear();

                int animationCycle = (int) (((now-initialNanoTime)/100000000));

                for (GameAnimation anime : gameObjectAnimations) {

                    earthObjects.getChildren().add(anime.buildFrame(animationCycle));

                }

            }

        }.start();

        earthObjects.setRotateY(-45);
        earthGroup.getChildren().add(earthObjects);

    }


    private void buildCamera() {

        if (root.getChildren().contains(cameraXform)) root.getChildren().remove(cameraXform);
        root.getChildren().add(cameraXform);
        if (cameraXform.getChildren().contains(cameraXform2)) cameraXform.getChildren().remove(cameraXform2);
        cameraXform.getChildren().add(cameraXform2);
        if (cameraXform2.getChildren().contains(cameraXform3)) cameraXform2.getChildren().remove(cameraXform3);
        cameraXform2.getChildren().add(cameraXform3);
        if (cameraXform3.getChildren().contains(camera)) cameraXform3.getChildren().remove(camera);
        cameraXform3.getChildren().add(camera);
        cameraXform3.setRotateZ(180.0);

        camera.setNearClip(CAMERA_NEAR_CLIP);
        camera.setFarClip(CAMERA_FAR_CLIP);
        camera.setTranslateZ(CAMERA_INITIAL_DISTANCE);
        cameraXform.ry.setAngle(CAMERA_INITIAL_Y_ANGLE);
        cameraXform.rx.setAngle(CAMERA_INITIAL_X_ANGLE);

    }


    private void buildAxes() {


        // X-Axis
        createRect3D(axisGroup, 20,20,AXIS_LENGTH,0,0,0,Color.DARKGREEN, null, false);

        // Y-Axis
        createRect3D(axisGroup, AXIS_LENGTH,20,20,0,0,0,Color.DARKRED, null, false);

        // Z-Axis
        createRect3D(axisGroup, 20,AXIS_LENGTH,20,0,0,0,Color.DARKBLUE, null, false);

        world.getChildren().addAll(axisGroup);

    }


    public void buildEarth() {


        for (int i = 0; i < gameMap.gameBoard.length; i++) {

            for (int j = 0; j < gameMap.gameBoard[0].length; j++) {

                earthGroup.getChildren().add(gameMap.gameBoard[i][j].xform);

            }

        }


        world.getChildren().add(earthGroup);


    }

    public void addHuman(String humanName, int x, int y) {


        switch (humanName) {
            case "VASSAL-DOWN":
                Vassal newVassal = new Vassal("DOWN", new int[] {x+30, y-30});
                humanXfrom.getChildren().addAll(newVassal.xform);
                break;
            case "SWORDSMAN-DOWN":
                Swordsman newSoildier = new Swordsman("DOWN", new int[] {x+30, y-30});
                humanXfrom.getChildren().addAll(newSoildier.xform);                break;
            case "WORKER-DOWN":
                Worker newWorker = new Worker("DOWN", new int[] {x+30, y-30});
                humanXfrom.getChildren().addAll(newWorker.xform);
                break;
            default:
                break;
        }

        humanXfrom.setRotateY(-45);
    }

    public void addAnimation(String animationName, int x, int y) {

        switch (animationName) {

            case "TREE-CHESTNUT":
                Chestnut newChestnut = new Chestnut(new int[] {x, y});
                gameObjectAnimations.add(newChestnut);
                break;
            default:
                break;

        }

    }


    public void addBuilding(String buildingName, int x, int y) {

        switch (buildingName) {
            case "CASTLE":
                if (!haveCastle) {
                    Castle newCastle = new Castle(new int[]{x, y}, "Amin");
                    newCastle.xform.setRotateY(-45);
                    world.getChildren().addAll(newCastle.xform);
                    haveCastle = true;
                    ArrayList<Building> castleList = new ArrayList<>();
                    castleList.add(newCastle);
                    myBuildings.put("CASTLE", castleList);

                } else System.out.println("We have one castle !!");
                break;
            case "FARM":
                Farm newFarm = new Farm(new int[]{x, y}, "Amin");
                newFarm.xform.setRotateY(-45);
                world.getChildren().addAll(newFarm.xform);
                ArrayList<Building> farmList = new ArrayList<>();
                if (myBuildings.get("FARM") == null) {

                    farmList.add(newFarm);
                    myBuildings.put("FARM", farmList);

                } else {

                    farmList = myBuildings.get("FARM");
                    farmList.add(newFarm);
                    myBuildings.put("FARM", farmList);

                }
                break;
            case "WORKSHOP":
                Workshop newWorkshop = new Workshop(new int[]{x, y}, "Amin");
                newWorkshop.xform.setRotateY(-45);
                world.getChildren().addAll(newWorkshop.xform);
                ArrayList<Building> workshopList = new ArrayList<>();
                if (myBuildings.get("WORKSHOP") == null) {

                    workshopList.add(newWorkshop);
                    myBuildings.put("WORKSHOP", workshopList);

                } else {

                    workshopList = myBuildings.get("WORKSHOP");
                    workshopList.add(newWorkshop);
                    myBuildings.put("WORKSHOP", workshopList);

                }
                break;
            case "BARRACKS":
                if (myBuildings.get("BARRACKS") == null) {

                    Barracks newBarracks = new Barracks(new int[]{x, y}, "Amin");
                    newBarracks.xform.setRotateY(-45);
                    world.getChildren().addAll(newBarracks.xform);
                    ArrayList<Building> barracksList = new ArrayList<>();
                    barracksList.add(newBarracks);
                    myBuildings.put("BARRACKS", barracksList);
                    break;

                } else System.out.println("We have one barracks");
            default:
                break;
        }


    }


    public void removeBuilding(Building buildingObject) {

        world.getChildren().remove(buildingObject.xform);

    }


    public static void createRect3D(Xform group, double width, double height, double depth, double x, double y, double z, Color color, String imageName, boolean rotation) {

        final PhongMaterial myMaterial = new PhongMaterial();
        final Box item = new Box(width, height, depth);


        if (color != null) myMaterial.setDiffuseColor(color);
        else if (imageName != null) {

            Image theImage = ResourceManager.getImage(imageName);

            if (theImage == null) {

                theImage = ResourceManager.getAnimation(imageName);

            }

            myMaterial.setDiffuseMap(theImage);

        }

        item.setMaterial(myMaterial);

        item.setTranslateX(x);
        item.setTranslateY(y);
        item.setTranslateZ(z);

        if (rotation) {

            item.setRotationAxis(new Point3D(1,0,0));
            item.setRotate(40);

        }

        group.getChildren().add(item);

    }


    private void handleMouse(Scene scene, final Node root) {

        scene.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent me) {

                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseOldX = me.getSceneX();
                mouseOldY = me.getSceneY();

//                System.out.println(mousePosX + " " + mousePosY);

            }

        });


        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {

            @Override public void handle(MouseEvent me) {

                mouseOldX = mousePosX;
                mouseOldY = mousePosY;
                mousePosX = me.getSceneX();
                mousePosY = me.getSceneY();
                mouseDeltaX = (mousePosX - mouseOldX);
                mouseDeltaY = (mousePosY - mouseOldY);


                double modifier = 1.0;


//                if (me.isSecondaryButtonDown()) {
//
//                    cameraXform.ry.setAngle(cameraXform.ry.getAngle() - mouseDeltaX * 100);
//                    cameraXform.rx.setAngle(cameraXform.rx.getAngle() + mouseDeltaY * 100);
//
//                } else if (me.isPrimaryButtonDown()) {
                if (me.isPrimaryButtonDown()) {

                    double oldXAngle = cameraXform.ry.getAngle();
                    double oldYAngle = cameraXform.rx.getAngle();


                    if (mouseDeltaY < 0) CAMERA_INITIAL_DISTANCE += mouseDeltaY*0.5;
                    else CAMERA_INITIAL_DISTANCE += mouseDeltaY*0.5;
                    buildCamera();
//                    cameraXform.ry.setAngle(oldYAngle);
//                    cameraXform.rx.setAngle(oldXAngle);
                    cameraXform2.t.setX(cameraXform2.t.getX() + mouseDeltaX * MOUSE_SPEED * TRACK_SPEED);
                    cameraXform2.t.setY(cameraXform2.t.getY() + mouseDeltaY * MOUSE_SPEED * TRACK_SPEED);

                }

            }

        });

    }

}