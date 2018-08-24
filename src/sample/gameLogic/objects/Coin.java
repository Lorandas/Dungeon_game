/**Класс представляет собой объект монета
 * Class represent an object COIN
 * @autor Yaroslav Martynov
 * @version 1.0
 */

package sample.gameLogic.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Coin<B extends Coin.Builder<B>> extends Model {

    /**
     * Конструктор  - создает новый объект МОНЕТА
     * @param builder  - объект строитель этого класса
     * @see Coin#Coin(Builder)
     */
    public Coin(Coin.Builder<B> builder){
        super(builder);
    }

    /**
     * Статический класс шаблона Строитель
     * @param <B> - Generic
     */
    public static class Builder <B extends  Coin.Builder<B>> extends Model.Builder<B> {
        /**
         * Конструктор - создает картинку монеты
         * @param image - картинка Монеты
         * @see Builder#Builder(Image)
         */
        public Builder (Image image) {
            super (image);
        }
        /**
         * Конструктор - создает картинку монеты
         * @param image - картинка Монеты
         * @see Builder#Builder(ImageView)
         */
        public Builder (ImageView image) {
            super (image);
        }

        /**
         * Переопределенный метод шаблона сроитель - строить
         * @return Coin - создает объект - монета
         */
        @Override
        public Coin<B> build(){
            return new Coin<> (this);
        }
    }
}
