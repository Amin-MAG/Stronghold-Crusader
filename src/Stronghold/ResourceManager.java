package Stronghold;

import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.io.FileReader;

import javafx.scene.layout.*;
import javafx.scene.media.AudioClip;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ResourceManager{

    public final static String gameAddress = Paths.get("").toAbsolutePath().toString();
    private final static String jsonResourseSettingAddress = "JsonFiles/resource_settings.json";

    private static HashMap<String, Image> IMAGES = new HashMap<>();
    private static HashMap<String, AudioClip> SOUNDS = new HashMap<>();
    private static HashMap<String, Map> JSONS = new HashMap<>();

    ResourceManager() {

        initialization();

    }

    public static void initialization(){

        JSONParser parser = new JSONParser();

        try{

            Object resourceParser = parser.parse(new FileReader(jsonResourseSettingAddress));
            JSONObject jsonObject = (JSONObject) resourceParser;
            Map RESOURCES;


            // Images

            RESOURCES  = (Map) jsonObject.get("images");
            for (Object key : RESOURCES.keySet()) {

                IMAGES.put( (String) key, new Image(new FileInputStream(gameAddress + (RESOURCES.get(key)))));

            }

            // Sounds

            RESOURCES  = (Map) jsonObject.get("sounds");
            for (Object key : RESOURCES.keySet()) {

                SOUNDS.put( (String) key, new AudioClip(new File(ResourceManager.gameAddress + RESOURCES.get(key)).toURI().toString()) );

            }

            // Jsons

            RESOURCES  = (Map) jsonObject.get("jsons");
            for (Object key : RESOURCES.keySet()) {

                JSONS.put( (String) key, (Map) parser.parse(new FileReader(ResourceManager.gameAddress + RESOURCES.get(key))));

            }


        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    public static Image getImage(String name) {

        return IMAGES.get(name);

    }

    public static Map getJson(String name) {

        return JSONS.get(name);

    }



    public static BackgroundImage getBackgroundImage(String name) {

        return (new BackgroundImage(
                getImage(name),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(
                        BackgroundSize.AUTO,BackgroundSize.AUTO, true, true, true, true)
        ));

    }

    public static Background getBackground(String name) {

        return (new Background(getBackgroundImage(name)));

    }

    public static AudioClip getSound(String name) {

        return SOUNDS.get(name);

    }


    public static void main(String[] args) {

        ResourceManager amin = new ResourceManager();

    }

}
