/**Класс представляет собой объект Игрок
 * Class represent an object PLAYER
 * @autor Yaroslav Martynov
 * @version 1.0
 */
package sample.gameLogic.objects;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.controller.GameController;
import sample.gameLogic.level.GameLevel;
import sample.gameLogic.objects.conduct.ActionModel;

public class Player <B extends Player.Builder<B>> extends Model implements ActionModel{

    /** Поле хранения логического значения - соприкоснулся ли игрок с стеной
     */
    private boolean touch;

    /** Поле для хранения полового различия Игрока
     */
    private String gender;

    /** Поле для хранения подсчета количества собраных монет игроком
     */
    private static int scoreCoin = 0;

    /**
     * Конструктор  - создает новый объект ИГРОК
     * @param builder  - объект строитель этого класса
     * @see Player#Player(Builder)
     */
    public Player(Player.Builder<B> builder){
        super(builder);
        gender = builder.gender;
    }

    /**
     * Статический класс шаблона Строитель
     * @param <B> - Generic
     */
    public static class Builder <B extends  Player.Builder<B>> extends Model.Builder<B> {
        /** Поле для хранения полового различия Игрока
         */
        private String gender;

        /**
         * Конструктор - создает картинку игрока
         * @param image - картинка Игрока
         * @see Builder#Builder(Image, String)
         */
        public Builder (Image image, String gender) {
            super (image);
            this.gender = gender;
        }

        /**
         * Конструктор - создает картинку игрока
         * @param image - картинка Игрока
         * @see Builder#Builder(ImageView, String)
         */
        public Builder (ImageView image, String gender) {
            super (image);
            this.gender = gender;
        }

        /**
         * Переопределенный метод шаблона сроитель - строить
         * @return Player - создает объект - Игрок
         */
        @Override
        public Player<B> build(){
            return new Player<> (this);
        }
    }

    /**
     * Переопредленный метод интерфейса ActionModel
     * @param x значение по оси X
     * @return boolean
     */
    @Override
    public boolean moveX (int x) {
        boolean right = x>0?true:false;
        moveX (x, right);
        touch = false;
        isWall ();
        if(touch) {
            moveX (x, !right);
            return touch;
        }
        if(right)
            isBox ("right", x);
        else
            isBox ("left", x);
        if(touch) {
            moveX (x, !right);
            return touch;
        }
        isCoin ();
        isLever ();
        isChest ();
        if(touch) {
            moveX (x, !right);
            return touch;
        }
        return touch;
    }

    /**
     * Переопредленный метод интерфейса ActionModel
     * @param x значение по оси X
     * @param right значение право или лево
     */
    @Override
    public void moveX(int x, boolean right){
        for (int i = 0; i < Math.abs (x); i++) {
            if (right)
                this.setTranslateX (this.getTranslateX () + 1);
            else
                this.setTranslateX (this.getTranslateX () - 1);
        }
    }

    /**
     * Переопредленный метод интерфейса ActionModel
     * @param y значение по оси Y
     * @return boolean
     */
    @Override
    public boolean moveY (int y) {
        boolean down = y > 0 ? true : false;
        moveY (y, down);
        touch = false;
        isWall ();
        if(touch) {
            moveY (y, !down);
            return touch;
        }
        if(down)
            isBox ("down", y);
        else
            isBox ("up", y);
        if(touch) {
            moveY (y, !down);
            return touch;
        }
        isCoin ();
        isLever ();
        isChest ();
        if(touch) {
            moveY (y, !down);
            return touch;
        }
        return touch;
    }

    /**
     * Переопредленный метод интерфейса ActionModel
     * @param y значение по оси Y
     * @param down значение вниз или вверх
     */
    @Override
    public void moveY(int y, boolean down){
        for (int i = 0; i < Math.abs (y); i++) {
            if (down)
                this.setTranslateY (this.getTranslateY () + 1);
            else
                this.setTranslateY (this.getTranslateY () - 1);
        }
    }

    /**
     *  Метод определяющий столкнулся ли объект Игрок с объектом Коробка
     */
    public void isBox(String move, int value){
        GameLevel.getBoxes ().forEach (box -> {
            if(this.getBoundsInParent ().intersects (box.getBoundsInParent ())){
                System.out.println ("КАСАНИЕ PLAYER -> BOX!");
                if(move.equals ("right") || move.equals ("left"))
                    touch =  box.moveX (value);
                if(move.equals ("up") || move.equals ("down"))
                    touch =  box.moveY (value);
                return;
            }
        });
    }

    /**
     *  Метод определяющий столкнулся ли объект Игрок с объектом Монета
     */
    public void isCoin(){
        GameLevel.getCoins ().forEach (coin -> {
            if(this.getBoundsInParent ().intersects (coin.getBoundsInParent ())){
                Platform.runLater (() -> GameLevel.getCoins ().remove (coin));
                Platform.runLater (() -> GameController.staticHolstPane.getChildren ().remove (coin));
                System.out.println ("КАСАНИЕ PLAYER -> COIN!");
                Platform.runLater (() -> incrementScoreCoin ());
                Platform.runLater (() -> GameController.staticCountCoin.setText (getScoreCoin () + ""));
                return;
            }
        });
    }

    /**
     *  Метод определяющий столкнулся ли объект Игрок с объектом Рычаг
     */
    public void isLever(){
        GameLevel.getLevers ().forEach (lever ->{
            if(this.getBoundsInParent ().intersects (lever.getBoundsInParent ())){
                lever.switching ();
                touch = true;
                return;
            }
        });
    }

    /**
     *  Метод определяющий столкнулся ли объект Игрок с объектом Стена
     */
    public void isWall(){
        GameLevel.getWalls ().forEach (wall -> {
            if(this.getBoundsInParent ().intersects (wall.getBoundsInParent ())){
                System.out.println ("КАСАНИЕ PLAYER -> WALL!");
                touch = true;
                return;
            }
        });
    }

    /**
     *  Метод определяющий столкнулся ли объект Игрок с объектом Сундук
     */
    public void isChest(){
        if(this.getBoundsInParent ().intersects (GameLevel.getChest ().getBoundsInParent ())){
            System.out.println ("КАСАНИЕ PLAYER -> CHEST");
            GameLevel.getChest ().openChest ();
            touch = true;
        }
    }

    /**
     * Задает значения свойства gender, которое можно получить при помощи метода
     * {@link #getGender()}
     *
     * @param gender новое значения свойства gender
     */
    public void setGender (String gender) {
        this.gender = gender;
    }

    /**
     * Получает значения свойства gender, которое можно задать с помощью метода
     * {@link #setGender(String)}
     * @return String значения свойства gender
     */
    public String getGender () {
        return gender;
    }

    /**
     * Метод инкриментит на +1 значения свойство scoreCoin
     */
    public static void incrementScoreCoin(){
        Player.scoreCoin += 1;
    }

    /**
     * Получает значения свойства scoreCoin
     * @return int значения scoreCoin
     */
    public static int getScoreCoin () {
        return scoreCoin;
    }

    /**
     * Задает новое значения свойства scoreCoin
     * @param scoreCoin новое значения свойства scoreCoin
     */
    public static void setScoreCoin (int scoreCoin) {
        Player.scoreCoin = scoreCoin;
    }


}
