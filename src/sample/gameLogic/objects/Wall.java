/**Класс представляет собой объект Стена
 * Class represent an object WALL
 * @autor Yaroslav Martynov
 * @version 1.0
 */

package sample.gameLogic.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Wall <B extends Wall.Builder<B>> extends Model{

    /** Поле хранит индекс Стены
     */
    private int index;

    /**
     * Конструктор  - создает новый объект СТЕНА
     * @param builder  - объект строитель этого класса
     * @see Wall#Wall(Builder)
     */
    public Wall(Builder<B> builder){
        super(builder);
        index = builder.index;
    }

    /**
     * Статический класс шаблона Строитель
     * @param <B> - Generic
     */
    public static class Builder <B extends  Wall.Builder<B>> extends Model.Builder<B> {
        /** Поле хранит индекс Стены
         */
        private int index;

        /**
         * Конструктор - создает картинку монеты
         * @param image - картинка Cтены
         * @see Builder#Builder(Image, int)
         */
        public Builder (Image image, int index) {
            super (image);
            this.index = index;
        }

        /**
         * Конструктор - создает картинку монеты
         * @param image - картинка Стены
         * @see Builder#Builder(ImageView)
         */
        public Builder (ImageView image, int index) {
            super (image);
            this.index = index;
        }

        /**
         * Переопределенный метод шаблона сроитель - строить
         * @return Wall - создает объект - стена
         */
        @Override
        public Wall<B> build(){
            return new Wall<> (this);
        }
    }

    /**
     * Получения значения свойства index
     * @return int значения свойства index
     */
    public int getIndex () {
        return index;
    }
}
