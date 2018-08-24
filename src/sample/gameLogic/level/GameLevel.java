package sample.gameLogic.level;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
import sample.gameLogic.links.GameObjects;
import sample.gameLogic.objects.*;


public class GameLevel {
    private static ObservableList<Coin> coins = FXCollections.observableArrayList ();
    private static ObservableList<Wall> walls = FXCollections.observableArrayList ();
    private static ObservableList<Box> boxes = FXCollections.observableArrayList ();
    private static ObservableList<Lever> levers = FXCollections.observableArrayList ();
    private static Player player;
    private static Chest chest;

    public static final void levelOne(AnchorPane holstPane){
        clearList ();
        chest = new Chest.Builder<> (GameObjects.CHEST).duration (Duration.ZERO).columns (1).count (1).height (30).wigth (32).offsetX (0).offsetY (0).build ();
        chest.setTranslateX (560);
        chest.setTranslateY (208);
//        chest.setTranslateX (48);
//        chest.setTranslateY (96);
        player = new Player.Builder<> (GameObjects.MALE, "male").duration (Duration.millis (500)).columns (3).count (3).height (32).wigth (32).offsetX (0).offsetY (0).build ();
        player.setTranslateX (48);
        player.setTranslateY (48);
        LevelOne.initWall (walls);
        LevelOne.initCoin (coins);
        LevelOne.initBox (boxes);
        LevelOne.initLever (levers);
        initList (holstPane);
    }

    private static final void clearList(){
        coins.clear ();
        walls.clear ();
        boxes.clear ();
        levers.clear ();
    }

    private static final void initList(AnchorPane holstPane){
        for(Coin coin : coins){
            holstPane.getChildren ().add (coin);
        }
        for(Box box : boxes){
            holstPane.getChildren ().add (box);
        }
        for(Wall wall : walls){
            holstPane.getChildren ().add (wall);
        }
        for(Lever lever : levers){
            holstPane.getChildren ().add (lever);
        }
        player.getAnimation ().stop ();
        chest.getAnimation ().stop ();
        holstPane.getChildren ().add (player);
        holstPane.getChildren ().add (chest);
    }


    public static Player getPlayer () {
        return player;
    }

    public static Chest getChest () {
        return chest;
    }

    public static ObservableList<Wall> getWalls () {
        return walls;
    }

    public static ObservableList<Box> getBoxes () {
        return boxes;
    }

    public static ObservableList<Coin> getCoins () {
        return coins;
    }

    public static ObservableList<Lever> getLevers () {
        return levers;
    }
}
