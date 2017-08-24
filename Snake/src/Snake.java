import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;

/**
 * Created by mpampis on 17/2/2017.
 */
public class Snake implements Constants {
    private Coordinate head;
    private Coordinate tail;
    private int size;
    private static boolean run = true;
    private static Thread hookThread;
    private Queue<Coordinate> moves;

    private static final int DEFAULT_X = 1;
    private static final int DEFAULT_Y = 5;

    public Snake(int size) {
        moves = new Queue<>();
        tail = new Coordinate(DEFAULT_X,DEFAULT_Y,Direction.RIGHT);
        head = new Coordinate(DEFAULT_X+size-1,DEFAULT_Y,Direction.RIGHT);
        this.size = size;
    }

    public void addKeyListener() {
        Runnable runnable = () -> {
            // might throw a UnsatisfiedLinkError if the native library fails to load or a RuntimeException if hooking fails
            GlobalKeyboardHook keyboardHook = new GlobalKeyboardHook();
            keyboardHook.addKeyListener(new GlobalKeyAdapter() {
                @Override public void keyPressed(GlobalKeyEvent event) {
                    Direction temp = head.getDirection();
                    switch(event.getVirtualKeyCode()) {
                        case Constants.UP:
                            temp = Direction.UP;
                            break;
                        case Constants.DOWN:
                            temp = Direction.DOWN;
                            break;
                        case Constants.RIGHT:
                            temp = Direction.RIGHT;
                            break;
                        case Constants.LEFT:
                            temp = Direction.LEFT;
                            break;
                        default:
                    }
                    if(head.getDirection() != temp && Math.abs(head.getDirection().getValue() - temp.getValue()) != 2) {
                        System.out.println("[INFO]Head Direction changed from: "+head.getDirection()+" to "+temp);
                        head.setDirection(temp);
                        try {
                            moves.add((Coordinate) head.clone());
                            System.out.println("[INFO]Added: "+head+" on moves");
                        }
                        catch(Exception ex) {

                        }
                    }
                }
            });

            try {
                while(true)
                    Thread.sleep(128);
            } catch(InterruptedException e) { /* nothing to do here */ }
            finally { keyboardHook.shutdownHook(); }
        };
        hookThread = new Thread(runnable);
        hookThread.start();
    }

    public void removeKeyListener() {
        hookThread.interrupt();
    }


    public void moveTail(Direction direction) {
        moveElement(tail,direction);
    }

    public void moveTail() {
        moveElement(tail,tail.getDirection());
    }

    public void moveHead(Direction direction) {
        moveElement(head,direction);
    }

    public void moveHead() {
        moveElement(head,head.getDirection());
    }


    public Coordinate getHead() {
        return head;
    }

    public Coordinate getTail() {
        return tail;
    }

    public Coordinate getDefaultCord() {
        return new Coordinate(DEFAULT_X,DEFAULT_Y);
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void increaseSize(int value) {
        size += value;
    }
    public void increaseSize() {
        increaseSize(1);
    }


    public Queue<Coordinate> getMoves() {
        return moves;
    }

    private static void moveElement(Coordinate elem, Direction direction) {
        switch (direction) {
            case UP:
                elem.decreaseY();
                break;
            case DOWN:
                elem.increaseY();
                break;
            case RIGHT:
                elem.increaseX();
                break;
            case LEFT:
                elem.decreaseX();
            default:
        }
    }

}
