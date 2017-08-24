/**
 * Created by mpampis on 17/2/2017.
 */
public enum Direction {
    UP(Constants.UP),DOWN(Constants.DOWN),LEFT(Constants.LEFT),RIGHT(Constants.RIGHT);

    private final int value;

    Direction(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

}
