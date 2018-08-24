package sample.gameLogic.level;

import javafx.collections.ObservableList;
import javafx.util.Duration;
import sample.gameLogic.links.GameObjects;
import sample.gameLogic.objects.Box;
import sample.gameLogic.objects.Coin;
import sample.gameLogic.objects.Lever;
import sample.gameLogic.objects.Wall;

import java.io.*;
import java.util.Scanner;

public class LevelOne {
    public static final void initWall(ObservableList<Wall> walls){
        final String name = "sample/resources/game_level/level_1/wall.txt";
        InputStream inputStream = null;
        inputStream = LevelOne.class.getClassLoader().getResourceAsStream(name);
        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (inputStream));
        Scanner scanner = new Scanner (bufferedReader);
        while (scanner.hasNext ()) {
            Wall wall = new Wall.Builder<> (GameObjects.WALL_TWO, scanner.nextInt ()).count (1).columns (1).duration (Duration.ZERO).offsetX (0).offsetY (0).height (32).wigth (32).build ();
            wall.setTranslateY (scanner.nextInt ());
            wall.setTranslateX (scanner.nextInt ());
            walls.add (wall);
        }
    }

    public static final void initCoin(ObservableList<Coin> coins){
        final String name = "sample/resources/game_level/level_1/coin.txt";
        InputStream inputStream = null;
        inputStream = LevelOne.class.getClassLoader().getResourceAsStream(name);
        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (inputStream));
        Scanner scanner = new Scanner (bufferedReader);
        while (scanner.hasNext ()) {
            Coin coin = new Coin.Builder<> (GameObjects.COIN).count (10).columns (10).duration (Duration.millis (1000)).offsetX (0).offsetY (0).height (20).wigth (20).build ();
            coin.setTranslateY (scanner.nextInt ());
            coin.setTranslateX (scanner.nextInt ());
            coins.add (coin);
        }
    }

    public static final void initBox(ObservableList<Box> boxes){
        final String name = "sample/resources/game_level/level_1/box.txt";
        InputStream inputStream = null;
        inputStream = LevelOne.class.getClassLoader().getResourceAsStream(name);
        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (inputStream));
        Scanner scanner = new Scanner (bufferedReader);
        while (scanner.hasNext ()) {
            Box box = new Box.Builder<> (GameObjects.BOX_THREE).count (1).columns (1).duration (Duration.ZERO).offsetX (0).offsetY (0).height (32).wigth (32).build ();
            box.setTranslateY (scanner.nextInt ());
            box.setTranslateX (scanner.nextInt ());
            boxes.add (box);
        }
    }

    public static final void initLever(ObservableList<Lever> levers){
        final String name = "sample/resources/game_level/level_1/lever.txt";
        InputStream inputStream = null;
        inputStream = LevelOne.class.getClassLoader().getResourceAsStream(name);
        BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (inputStream));
        Scanner scanner = new Scanner (bufferedReader);
        while (scanner.hasNext ()) {
            Lever lever = new Lever.Builder<> (GameObjects.LEVER, scanner.nextInt ()).count (4).columns (4).duration (Duration.millis (1000)).offsetX (0).offsetY (0).height (32).wigth (32).build ();
            lever.setTranslateY (scanner.nextInt ());
            lever.setTranslateX (scanner.nextInt ());
            levers.add (lever);
        }
    }
}
