/**Класс представляет собой интерфейс
 * который предназначен для движения объектов
 *
 * Class represent is interface
 * which is intended for the movement of objects
 *
 * @autor Yaroslav Martynov
 * @version 1.0
 */

package sample.gameLogic.objects.conduct;

public interface ActionModel {

    /**
     * Метод который проверяет возможно-ли движение и возращает
     * результат операции - возможно ли движение (true or false)
     * @param y значение по оси Y
     * @return boolean
     */
    boolean moveY(int y);

    /**
     * Метод который делает непосредственное движение в направении
     * по оси Y
     * @param y значение по оси Y
     * @param down значение вниз или вверх
     */
    void moveY(int y, boolean down);

    /**
     * Метод который проверяет возможно-ли движение и возращает
     * результат операции - возможно ли движение (true or false)
     * @param x значение по оси X
     * @return boolean
     */
    boolean moveX(int x);

    /**
     * Метод который делает непосредственное движение в направении
     * по оси X
     * @param x значение по оси X
     * @param right значение право или лево
     */
    void moveX(int x, boolean right);
}
