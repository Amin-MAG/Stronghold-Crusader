package Stronghold.Gui.Text;

import Stronghold.Game;
import javafx.scene.text.Text;

public class ResourceText extends Text {

    public String resource;

    public ResourceText(String resource,double x, double y) {

        super("Loading...");

        this.resource = resource;

        setX(x);
        setY(y);

        updateText();

    }

    public String updateText() {

        String theValue = Game.resources.get(resource).toString();
        setText(theValue);

        return theValue;

    }

}
