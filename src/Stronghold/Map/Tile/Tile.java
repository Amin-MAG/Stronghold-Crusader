package Map.Tile;

public abstract class Tile {

    public boolean movementIsPossible;
    public String imageAddress;

    Tile(String image, boolean movementIsPossible) {

        this.imageAddress = image;
        this.movementIsPossible = movementIsPossible;

    }

}
