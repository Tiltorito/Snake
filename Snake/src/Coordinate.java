/**
 * Created by mpampis on 17/2/2017.
 */
public class Coordinate implements Cloneable {
    private int x;
    private int y;
    private Direction direction;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(int x,int y,Direction direction) {
        this(x,y);
        this.direction = direction;
    }



    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void increaseX(int value) {
        x += value;
    }

    public void increaseX() {
        increaseX(1);
    }

    public void increaseY(int value) {
        y += value;
    }
    public void increaseY() {
        increaseY(1);
    }

    public void decreaseX(int value) {
        x -= value;
    }
    public void decreaseX() {
        decreaseX(1);
    }

    public void decreaseY(int value) {
        y -= value;
    }
    public void decreaseY() {
        decreaseY(1);
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public boolean hasDirection() {
        return direction != null;
    }

    @Override
    public String toString() {
        String s = "("+x+","+y+")";
        if(hasDirection()) {
            s = s+"->"+direction;
        }
        return s;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Coordinate) {
            Coordinate temp = (Coordinate)o;
            return this.x == temp.getX() && this.y == temp.getY();
        }

        return false;
    }
}
