/**Класс представляет собой объект рычаг
 * Class represent an object LEVER
 * @autor Yaroslav Martynov
 * @version 1.0
 */
package sample.gameLogic.objects;

import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import sample.controller.GameController;
import sample.gameLogic.Sprite;
import sample.gameLogic.level.GameLevel;

public class Lever<B extends Lever.Builder<B>> extends Model {

    /** Поле хранит индекс объекта
     */
    private int index;

    /** Поле хранит состояние объекта (включен или выключен)
     */
    private boolean check = false;

    /**
     * Конструктор  - создает новый объект РЫЧАГ
     * @param builder  - объект строитель этого класса
     * @see Lever#Lever(Builder)
     */
    public Lever(Lever.Builder<B> builder){
        super(builder);
        index = builder.index;
    }

    /**
     * Статический класс шаблона Строитель
     * @param <B> - Generic
     */
    public static class Builder <B extends  Lever.Builder<B>> extends Model.Builder<B> {

        /** Поле хранит индекс объекта
         */
        private int index;

        /**
         * Конструктор - создает картинку монеты
         * @param image - картинка Рычаг
         * @see Builder#Builder(Image, int)
         */
        public Builder (Image image, int index) {
            super (image);
            this.index = index;
        }

        /**
         * Конструктор - создает картинку монеты
         * @param image - картинка Рычаг
         * @see Builder#Builder(ImageView, int)
         */
        public Builder (ImageView image, int index) {
            super (image);
            this.index = index;
        }

        /**
         * Переопределенный метод шаблона сроитель - строить
         * @return Lever - создает объект - рычаг
         */
        @Override
        public Lever<B> build(){
            return new Lever<> (this);
        }
    }

    /**
     *  метод который вызывается при сталкивании с объектом РЫЧАГ
     *  проверяет нужно ли вызывать метод processSwitching ()
     *  который меняет положение рычага
     */
    public void switching(){
        if(check != true) {
            processSwitching ();
        }
    }

    /**
     * Метод который позволяет остановить анимацию рычага и задействовать другую анимацию при соприкосновении с ним
     * вызывает процес нажатия рычага и вызова метода удаления по индексу нужные стенки
     */
    private void processSwitching(){
        getAnimation ().stop ();
        Thread thread = new Thread (new Runnable () {
            @Override
            public void run () {
                try {
                    Sprite animation = new Sprite (getAnimation ().getImageView (), Duration.ZERO,
                            0, 0,
                            getAnimation ().getOffsetX (), 32,
                            getAnimation ().getWidth (), getAnimation ().getHeight ());
                    setAnimation (animation);
                    Thread.sleep (400);
                    animation = new Sprite (getAnimation ().getImageView (), Duration.ZERO,
                            0, 0,
                            getAnimation ().getOffsetX (), 64,
                            getAnimation ().getWidth (), getAnimation ().getHeight ());
                    setAnimation (animation);
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            }
        });
        thread.start ();
        deleteWall ();
        check = true;
    }

    /**
     * метод удаления стенок по индексу совпадающим с индексом рычага
     */
    private void deleteWall(){
        for(Wall wall : GameLevel.getWalls ()){
            if(index == wall.getIndex ()){
                Platform.runLater (() -> GameController.staticHolstPane.getChildren ().remove (wall));
                Platform.runLater (() -> GameLevel.getWalls ().remove (wall));
            }
        }
    }

}