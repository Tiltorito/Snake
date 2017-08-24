/**
 * Created by mpampis on 17/2/2017.
 */
public class Food {
    private Coordinate coordinates;
    private boolean eaten = false;

    public static final char value = '@';

    public Food(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    public boolean isEaten() {
        return eaten;
    }

    public void setEaten(boolean eaten) {
        this.eaten = eaten;
    }

    public static char getValue() {
        return value;
    }
}
