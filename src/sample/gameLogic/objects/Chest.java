/**Класс представляет собой объект сундук
 * Class represent an object CHEST
 * @autor Yaroslav Martynov
 * @version 1.0
 */
package sample.gameLogic.objects;

import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.controller.EndGameController;

public class Chest<B extends Chest.Builder<B>> extends Model {

    /** Поле для хранения результата - открыли сундук или нет
     */
    public boolean open = false;

    /**
     * Конструктор  - создает новый объект СУНДУК
     * @param builder  - объект строитель этого класса
     * @see  Chest#Chest(Builder)
     */
    public Chest(Chest.Builder<B> builder){
        super(builder);
    }

    /**
     * Статический класс шаблона Строитель
     * @param <B> - Generic
     */
    public static class Builder <B extends  Chest.Builder<B>> extends Model.Builder<B> {
        /**
         * Конструктор - создает картинку сундука
         * @param image - картинка Сундука
         * @see Builder#Builder(Image)
         */
        public Builder (Image image) {
            super (image);
        }

        /**
         * Конструктор - создает картинку сундука
         * @param image - картинка Сундука
         * @see Builder#Builder(ImageView)
         */
        public Builder (ImageView image) {
            super (image);
        }

        /**
         * Переопределенный метод шаблона сроитель - строить
         * @return Chest - создает объект - сундук
         */
        @Override
        public Chest<B> build(){
            return new Chest<> (this);
        }
    }

    /**
     * метод предназначенный для анимирования открытия сундука и вызова команды завершения игры
     */
    public void openChest(){
        if (open)
            return;
        getAnimation ().setOffsetY (30);
        getAnimation ().setImagePictures ();
        Thread thread = new Thread (new Runnable () {
            @Override
            public void run () {
                try {
                    Thread.sleep (200);
                    getAnimation ().setOffsetY (60);
                    getAnimation ().setImagePictures ();
                    Thread.sleep (200);
                    getAnimation ().setOffsetY (90);
                    getAnimation ().setImagePictures ();
                    Platform.runLater (() ->EndGameController.actionEndGame ());
                } catch (InterruptedException e) {
                    e.printStackTrace ();
                }
            }
        });
        thread.start ();
        open = true;
    }
}
