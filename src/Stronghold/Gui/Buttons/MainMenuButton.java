
package Stronghold.Gui.Buttons;

import Stronghold.GameGui;
import Stronghold.ResourceManager;

import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class MainMenuButton extends Button {

    public MainMenuButton(String mainImageName) {

        super("");

        addEventHandler(MouseEvent.MOUSE_ENTERED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        setBackground(ResourceManager.getBackground(mainImageName + "_HOVER"));
                        ResourceManager.getSound("GUI-HOVER_BTN").play(1.0);
                        GameGui.getInitialScene().setCursor(Cursor.HAND);

                    }
                });

        addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {

                        setBackground(ResourceManager.getBackground(mainImageName));
                        GameGui.getInitialScene().setCursor(Cursor.DEFAULT);

                    }
                });


        setMaxHeight(53);
        setMinHeight(53);
        setMaxWidth(361);
        setMinWidth(361);
        setBackground(ResourceManager.getBackground(mainImageName));

    }

}
