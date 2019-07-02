package Stronghold;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.stage.Stage;

import java.awt.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Stronghold extends Application {

    final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final static String gameAddress = Paths.get("").toAbsolutePath().toString();

    @Override
    public void start(Stage primaryStage) {

        System.out.println(gameAddress);

    }

    public static void main(String[] args) {

        launch(args);

    }

}
