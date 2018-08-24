/**Класс представляет собой абстрактный класс модели объекта
 * Class represent an abstract class object model
 * @autor Yaroslav Martynov
 * @version 1.0
 */

package sample.gameLogic.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import sample.gameLogic.Sprite;

/**
 * Для моделей объектов используем шаблон проектирования Builder
 */
public abstract class Model <B extends Model.Builder<B>> extends Pane {

    /** Поле хранения картинки изображения
     */
    private ImageView imageView;

    /** Поле хранения ссылка на объект спрайтовой анимации
     */
    private Sprite animation;

    //optional
    /** Поле хранения времени анимации
     */
    private Duration duration;

    /** Поле хранения количества кадров анимации
     */
    private int count;

    /** Поле хранения количество столбцов анимации
     */
    private int columns;

    /** Поле хранения смещения картинки по Х
     */
    private int offsetX;

    /** Поле хранения смещения картинки по Y
     */
    private int offsetY;

    /** Поле хранения ширины картинки
     */
    private int width;

    /** Поле хранения высоты картинки
     */
    private int height;

    /**
     * Представления абстрактного класса шаблона строитель Builder
     * @param <B>
     */
    public static abstract class Builder <B extends  Model.Builder<B>>{
        //required
        /** Поле хранения картинки изображения
         */
        private ImageView imageView;

        //optional

        /** Поле хранения времени анимации
         *  по умолчанию 500 миллисекунд
         */
        private Duration duration = Duration.millis (500);

        /** Поле хранения количество кадров нашей анимации
         *  по умолчанию 1 кадр
         */
        private int count = 1;

        /** Поле хранения количество столбцов анимации
         *  по умолчанию 1 столбец
         */
        private int columns = 1;

        /** Поле хранения смещения картинки по Х
         *  по умолчанию 0
         */
        private int offsetX = 0;

        /** Поле хранения смещения картинки по Y
         *  по умолчанию 0
         */
        private int offsetY = 0;

        /** Поле хранения ширины картинки
         *  по умолчанию 32
         */
        private int width = 32;

        /** Поле хранения высоты картинки
         *  по умолчанию 32
         */
        private int height = 32;

        /**
         * Конструктор - создает картинку модели
         * @param image - картинка Модели
         * @see Builder#Builder(Image)
         */
        public Builder(Image image){
            imageView = new ImageView (image);
        }

        /**
         * Конструктор - создает картинку модели
         * @param image - Картинка модели
         * @see Builder#Builder(ImageView)
         */
        public Builder(ImageView image){
            imageView = image;
        }

        /**
         * Создает в шаблоне Строитель время анимации
         * @param value - время анимации
         * @return B возвращает текущий класс Строитель
         */
        public B duration(Duration value){
            duration = value;
            return (B)this;
        }

        /**
         * Создает в шаблоне Строитель количество кадров анимации
         * @param value - количество кадров анимации
         * @return B возвращает текущий класс Строитель
         */
        public B count(int value){
            count = value;
            return (B) this;
        }

        /**
         * Создает в шаблоне Строитель количество столбцов анимации
         * @param value - количество столбцов анимации
         * @return B возвращает текущий класс Строитель
         */
        public B columns(int value){
            columns = value;
            return (B)this;
        }

        /**
         * Создает в шаблоне Строитель смещение по оси Х
         * @param value - смещение по оси Х
         * @return B возвращает текущий класс Строитель
         */
        public B offsetX(int value){
            offsetX = value;
            return (B)this;
        }

        /**
         * Создает в шаблоне Строитель смещение по оси Y
         * @param value - смещение по оси Y
         * @return B возвращает текущий класс Строитель
         */
        public B offsetY(int value){
            offsetY = value;
            return (B)this;
        }

        /**
         * Создает в шаблоне Строитель ширину картинки
         * @param value - ширина картинки
         * @return B возвращает текущий класс Строитель
         */
        public B wigth(int value){
            width = value;
            return (B)this;
        }

        /**
         * Создает в шаблоне Строитель высоту картинки
         * @param value - высоту картинки
         * @return B возвращает текущий класс Строитель
         */
        public B height(int value){
            height = value;
            return (B)this;
        }

        /**
         * абстрактный метод создания модели
         * @return Model<b>
         */
        public abstract Model<B> build();
    }

    /**
     * Конструктор -  инициализиирует созданные строителем объекты в этот класс
     * и инициализирует анимацию
     * @param builder - ссылка на объекст внутреннего класса строителя
     * @see Model#Model(Builder)
     */
    public Model(Builder<B> builder){
        imageView = builder.imageView;
        duration = builder.duration;
        count = builder.count;
        columns = builder.columns;
        offsetX = builder.offsetX;
        offsetY = builder.offsetY;
        width = builder.width;
        height = builder.height;
        animation = new Sprite (imageView, duration,
                count, columns,
                offsetX, offsetY,
                width, height);
        initAnimation ();
    }

    /**
     * Получает значения свойства animation, которые можно задать с помощью метода
     * {@link #setAnimation(Sprite)}
     *
     * @return Spite значения свойства animation
     */
    public Sprite getAnimation () {
        return animation;
    }

    /**
     * Задает значения свойства animation, которое можно получить при помощи метода
     * {@link #getAnimation()}
     *
     * @param animation новое значение свойства animation
     */
    public void setAnimation (Sprite animation) {
        this.animation = animation;
    }

    /**
     * Получает значения свойства imageView, которые можно задать с помощью метода
     * {@link #setImageView(ImageView)}
     *
     * @return ImageView значения свойства imageView
     */
    public ImageView getImageView () {
        return imageView;
    }

    /**
     * Задает значения свойства imageView, которое можно получить при помощи метода
     * {@link #getImageView()}
     *
     * @param imageView новое значение свойства imageView
     */
    public void setImageView (ImageView imageView) {
        this.imageView = imageView;
    }

    /** Метод который позволяет инициализировать анимацию
     * и добавить картинку с анимацией на панель Pane класса Pane
     */
    private void initAnimation () {
        animation.play ();
        getChildren ().add (imageView);
    }

    /**
     * Метод который позволяет инициализировать в существующий объект
     * новую картинку для анимации
     * @param imageView - новая картинка анимации
     */
    public void setNewAnimation(ImageView imageView){
        getChildren ().remove (this.imageView);
        setImageView (imageView);
        animation = new Sprite (imageView, duration,
                count, columns,
                animation.getOffsetX (), animation.getOffsetY (),
                width, height);
        getChildren ().add (imageView);
    }

}
