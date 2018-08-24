/**Класс представляет собой объект коробка
 * Class represent an object BOX
 * @autor Yaroslav Martynov
 * @version 1.0
 */

package sample.gameLogic.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.gameLogic.level.GameLevel;
import sample.gameLogic.objects.conduct.ActionModel;

public class Box<B extends Box.Builder<B>> extends Model implements ActionModel {

    /** Поле для хранения информации соприкоснулся-ли объект с препятствием
     */
    private boolean touch;

    /**
     *  Конструктор  - создает новый объект КОРОБКА
     * @param builder  - объект строитель этого класса
     * @see  Box#Box(Builder)
     */
    public Box(Box.Builder<B> builder){
        super(builder);
    }

    /**
     * Статический класс шаблона Строитель
     * @param <B> - Generic
     */
    public static class Builder <B extends  Box.Builder<B>> extends Model.Builder<B> {
        /**
         * Конструктор - создает картинку коробки
         * @param image - картинка Коробки
         * @see Builder#Builder(Image)
         */
        public Builder (Image image) {
            super (image);
        }

        /**
         * Конструктор - создает картинку коробки
         * @param image - картинка Коробки
         * @see Builder#Builder(ImageView)
         */
        public Builder (ImageView image) {
            super (image);
        }

        /**
         * Переопределенный метод шаблона сроитель - строить
         * @return Box - создает объект - коробка
         */
        @Override
        public Box<B> build(){
            return new Box<> (this);
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
     *  Метод определяющий столкнулся ли объект Коробка с объектом Стена
     */
    public void isWall(){
        GameLevel.getWalls ().forEach (wall -> {
            if(this.getBoundsInParent ().intersects (wall.getBoundsInParent ())){
                System.out.println ("КАСАНИЕ BOX -> WALL!");
                touch = true;
                return;
            }
        });
    }

    /**
     *  Метод определяющий столкнулся ли объект Коробка с объектом Коробка
     */
    public void isBox(String move, int value){
        GameLevel.getBoxes ().forEach (box -> {
            if(this.getBoundsInParent ().intersects (box.getBoundsInParent ()) && this != box){
                System.out.println ("КАСАНИЕ BOX -> BOX!");
                if(move.equals ("right") || move.equals ("left"))
                    touch =  box.moveX (value);
                if(move.equals ("up") || move.equals ("down"))
                    touch =  box.moveY (value);
                return;
            }
        });
    }
}
