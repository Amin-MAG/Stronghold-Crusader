package Stronghold;

import Stronghold.Gui.Buttons.MainMenuButton;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;


public class GameGui extends Application {

    private Group guiRoot = new Group();
    private static VBox mainBox;
    private static Scene initialScene;

    @Override
    public void start(Stage primaryStage) {


        ResourceManager.initialization();

        primaryStage.setAlwaysOnTop(true);

        AudioClip theMenuMusic = ResourceManager.getSound("GUI-MUSIC");

        MainMenuButton btnCreateServer = new MainMenuButton("GUI-CREATE_SERVER");
        MainMenuButton btnJoin = new MainMenuButton("GUI-JOIN");
        MainMenuButton btnAbout = new MainMenuButton("GUI-ABOUT");
        MainMenuButton btnExit = new MainMenuButton("GUI-EXIT");

        btnJoin.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        Game myGame = new Game("MAP-AP");
                        theMenuMusic.stop();
                        myGame.render(primaryStage);

                    }
                });

        btnExit.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        primaryStage.close();
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


        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        primaryStage.fullScreenProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

                if(newValue != null) primaryStage.setFullScreen(true);

            }

        });


//        theMenuMusic.play();


        new Game("MAP-AP").render(primaryStage);

        primaryStage.show();

    }

    public static Scene getInitialScene() {

        return initialScene;

    }

    public static void main(String[] args) {

        launch(args);

    }

}