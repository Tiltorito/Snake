/**
 * Created by mpampis on 17/2/2017.
 */
public class Map {
    private int rows;
    private int columns;
    private Character[][] map;
    private Food food;
    private Snake snake;

    public Map(int rows,int columns) {
        this.rows = rows;
        this.columns = columns;
        map = new Character[rows][columns];
        initMap();
    }

    public void displayMap() {
        for(int i = 0;i < map.length;i++) {
            for(int c = 0;c < map[i].length;c++) {
                System.out.print(map[i][c]);
            }
            System.out.println();
        }
    }

    public void addSnake(Snake snake) {
        Coordinate cord = snake.getDefaultCord();

        for(int i = 0;i < snake.getSize();i++) {
            map[cord.getY()][cord.getX()+i] = '*';
        }

        this.snake = snake;
    }

    public void initFood() {
        Coordinate cord = new Coordinate(getRandomColumn(),getRandomRow());
        while(map[cord.getY()][cord.getX()] == '*') {
            cord.setY(getRandomRow());
            cord.setX(getRandomColumn());
        }
        food = new Food(cord);
        map[cord.getY()][cord.getX()] = Food.value;
    }

    public void spawnFood() {
        int row = getRandomRow();
        int column = getRandomColumn();
        while(map[row][column] == '*') {
            row = getRandomRow();
            column = getRandomColumn();
        }

        food.getCoordinates().setY(row);
        food.getCoordinates().setX(column);
        map[row][column] = Food.value;
    }

    private int getRandomRow() {
        return (int)(1 + Math.random() * (rows - 2));
    }

    private int getRandomColumn() {
        return (int)(1 + Math.random() * (columns - 2));
    }

    public Character getCharAt(int row,int column) {
        return map[row][column];
    }

    public void setCharAt(int row,int column,char ch) {
        map[row][column] = ch;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public Snake getSnake() {
        return snake;
    }

    public void setSnake(Snake snake) {
        this.snake = snake;
    }

    private void initMap() {
        initArray(map,' ');
        // First row
        for(int c = 0;c < columns;c++) {
            map[0][c] = '-';
        }
        // Others
        for(int r = 1;r < rows;r++) {
            map[r][0] = '|';
            map[r][columns-1] = '|';
        }

        // Last row
        for(int c = 0;c < columns;c++) {
            map[rows - 1][c] = '-';
        }
    }

    private <T> void initArray(T[][] array, T value) {
        for(int i = 0;i < array.length;i++) {
            for(int c = 0;c < array[i].length;c++) {
                array[i][c] = value;
            }
        }
    }


}
