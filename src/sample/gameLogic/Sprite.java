/**Класс служит для анимирования 2D анимации из спрайтов
 * @autor Yaroslav Martynov
 * @version 1.0
 */

package sample.gameLogic;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Sprite extends Transition {
    /** Поле хранения картинки нашей анимации
     */
    private ImageView imageView;

    /** Поле для хранения количества кадров анимации
     */
    private final int count;

    /** Поле для хранения количество столбцов анимации
     */
    private final int columns;

    /** Поле для хранения смещение первого кадра по X
     */
    private int offsetX;

    /** Поле для хранения смещение первого кадра по Y
     */
    private int offsetY;

    /** Поле для хранения размера кадра по ширине
     */
    private final int width;

    /** Поле для хранения размера кадра по высоте
     */
    private final int height;

    /**
     * Конструктор - создает новый объект и инициализирует ряд методов
     * @param imageView - картинка анимации
     * @param duration - время дейтвия анимации
     * @param count - количество кадров анимации
     * @param columns - количество столбцов анимации
     * @param offsetX - смещение первого кадра по Х
     * @param offsetY - смещение первого кадра по Y
     * @param width - ширина картинки
     * @param height - высота картинки
     * @see Sprite#Sprite(ImageView, Duration, int, int, int, int, int, int)
     */
    public Sprite(
            ImageView imageView,
            Duration duration,
            int count, int columns,
            int offsetX, int offsetY,
            int width, int height
    ){
        this.imageView = imageView;
        this.count = count;
        this.columns = columns;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        setCycleDuration(duration);
        setCycleCount(Animation.INDEFINITE);
        setInterpolator(Interpolator.LINEAR);
        setImagePictures ();
    }

    /** Задает значения свойства Х, которое можно получить при помощи метода
     * {@link #getOffsetX()}
     * @param x новое значение свойства offsetX
     */
    public void setOffsetX(int x){
        this.offsetX = x;
    }

    /** Задает значения свойства Y, которое можно получить при помощи метода
     * {@link #getOffsetY()}
     * @param y новое значение свойства offsetY
     */
    public void setOffsetY(int y){
        this.offsetY = y;
    }

    /**
     * Получает значения свойства offsetX
     * @return int значения свойства offsetX
     */
    public int getOffsetX () {
        return offsetX;
    }

    /**
     * Получает значения свойства offsetY
     * @return int значения свойства offsetY
     */
    public int getOffsetY () {
        return offsetY;
    }

    /**
     * Получает значения свойства width
     * @return int значения свойства width
     */
    public int getWidth () {
        return width;
    }

    /**
     * Получает значения свойства height
     * @return int значения свойства height
     */
    public int getHeight () {
        return height;
    }

    /**
     * Получает значения свойства count
     * @return int значения свойства count
     */
    public int getCount () {
        return count;
    }

    /**
     * Получает значения свойства columns
     * @return int значения свойства columns
     */
    public int getColumns () {
        return columns;
    }

    /**
     * Получает значения свойства imageView
     * @return ImageView значения свойства imageView
     */
    public ImageView getImageView () {
        return imageView;
    }

    /**
     * Задает новое значение картинке используя вписанные в данный класс значения
     */
    public void setImagePictures(){
        imageView.setViewport(new Rectangle2D (offsetX, offsetY, width, height));
    }

    /**
     * Метод переопределенный от наследования класса Transition
     * вызываетя в каждом кадре анимации пока она действует, определяет повидение анимации
     * @param frac double принимате значение от 0 до 1(позиция анимации)
     */
    protected void interpolate(double frac) {
        final int index = Math.min((int)Math.floor(count*frac), count-1);
        final int x = (index%columns)*width+offsetX;
        final int y = (index/columns)*height+offsetY;
        imageView.setViewport(new Rectangle2D(x, y, width, height));
    }

}
