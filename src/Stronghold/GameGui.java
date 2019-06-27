package Stronghold;

import Stronghold.Gui.Buttons.MainMenuButton;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

import java.io.File;
import java.nio.file.Paths;


public class GameGui extends Application {

    private Group guiRoot = new Group();
    private static VBox mainBox;
    private static Scene initialScene;

    @Override
    public void start(Stage primaryStage) {

        ResourceManager.initialization();

        MainMenuButton btnCreateServer = new MainMenuButton("GUI-CREATE_SERVER");
        MainMenuButton btnJoin = new MainMenuButton("GUI-JOIN");
        MainMenuButton btnAbout = new MainMenuButton("GUI-ABOUT");
        MainMenuButton btnExit = new MainMenuButton("GUI-EXIT");

        btnExit.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.exit(0);
                    }
                });


        VBox mainMenuBtnBox = new VBox(btnCreateServer, btnJoin, btnAbout, btnExit);
        mainMenuBtnBox.setTranslateX(500);
        mainMenuBtnBox.setTranslateY(500);
        mainMenuBtnBox.setSpacing(30);

        mainBox = new VBox(mainMenuBtnBox);

        initialScene = new Scene(mainBox, Stronghold.screenSize.width, Stronghold.screenSize.height);
        initialScene.setCursor(Cursor.DEFAULT);

        primaryStage.setScene(initialScene);
        primaryStage.setTitle("Stronghold");
        primaryStage.setMaxHeight(Stronghold.screenSize.height);
        primaryStage.setMaxWidth(Stronghold.screenSize.width);
        primaryStage.setMinHeight(Stronghold.screenSize.height);
        primaryStage.setMinWidth(Stronghold.screenSize.width);

        mainBox.setBackground(ResourceManager.getBackground("GUI-BACKGROUND"));


//        Canvas menuPage = new Canvas(Stronghold.screenSize.width, Stronghold.screenSize.height);
//        guiRoot.getChildren().add(menuPage);
//
//        GraphicsContext gcPage = menuPage.getGraphicsContext2D();
//
        new AnimationTimer() {
            @Override
            public void handle(long now) {
//
//                gcPage.setFill(new Color(0,0,0,1));
//                gcPage.fillRect(0,0,Stronghold.screenSize.width, Stronghold.screenSize.width);
//
                primaryStage.setFullScreen(true);

            }
        }.start();


        ResourceManager.getSound("GUI-Music").play(1.0);

        primaryStage.show();

    }

    public static VBox getMainBox() {

        return mainBox;

    }

    public static Scene getInitialScene() {

        return initialScene;

    }

    public static void main(String[] args) {

        launch(args);

    }

}