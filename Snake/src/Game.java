/**
 * Created by mpampis on 17/2/2017.
 */
public class Game {

    private Map map;
    private static final int DEFAULT_SNAKE_SIZE = 2;
    private Queue<Coordinate> moves = new Queue<>();
    private char[] enemys = {'-','_','|','*'};

    public Game(int rows,int columns) {
        map = new Map(rows,columns);
        Snake snake = new Snake(DEFAULT_SNAKE_SIZE);
        map.addSnake(snake);
        map.initFood();
        snake.addKeyListener();
        moves = snake.getMoves();
    }

    public void start() throws Exception {
        boolean alive = true;
        boolean flag = false;
        while(alive) {

            if(!moves.isEmpty() && moves.peek().equals(map.getSnake().getTail())) {
                map.getSnake().getTail().setDirection(moves.remove().getDirection());
            }
            map.setCharAt(map.getSnake().getHead().getY(), map.getSnake().getHead().getX(), '*');
            map.getSnake().moveHead();
            if(map.getSnake().getHead().equals(map.getFood().getCoordinates())) {
                map.spawnFood();
                map.getSnake().increaseSize();
                flag = true;
            }
            if(!flag) {
                map.setCharAt(map.getSnake().getTail().getY(), map.getSnake().getTail().getX(), ' ');
                map.getSnake().moveTail();
            }

            if(isEnemy(map.getSnake().getHead())) {
                alive = false;
            }
            else {
                map.setCharAt(map.getSnake().getHead().getY(), map.getSnake().getHead().getX(), '#');
                clearScreen();
                map.displayMap();
                System.out.println("Tail: "+map.getSnake().getTail());
                System.out.println("Head: "+map.getSnake().getHead());
                Thread.sleep(512);
                flag = false;
            }



        }

        System.out.println("Λυπαμαι... εχασες!");
    }

    private void clearScreen() {
        for(int i = 0;i < 20;i++) {
            System.out.println();
        }
    }

    private boolean isEnemy(Coordinate coordinate) {
        char ch = map.getCharAt(coordinate.getY(),coordinate.getX());

        for(int i = 0;i < enemys.length;i++) {
            if(ch == enemys[i]) {
                return true;
            }
        }

        return false;
    }
}
