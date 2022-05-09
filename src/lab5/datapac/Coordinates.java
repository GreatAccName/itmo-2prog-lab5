package lab5.datapac;

import java.util.Objects;

import lab5.exceptions.BadInputException;

/**Класс координат квартиры.*/
public class Coordinates {
    /**Максимальное значение {@code x}*/
    public static final double MAX_X = 165D;
    /**Максимальное значение {@code y}*/
    public static final int MAX_Y = 165;
    /**Максимальное значение поля: 165.*/
    private double x;
    public double getX() { return x; }
    /**Максимальное значение поля: 165.*/
    private int y;
    public int getY() { return y; }

    /**
     * Проверяет {@code argX}.
     * @param argX значение {@code x}.
     * @return {@code true} при {@code 0 <= x <= MAX_X},
     * иначе - {@code false}.
    */
    public static boolean isXgood(double argX) {
        return Double.compare(argX, 0) >= 0 &&
               Double.compare(argX, MAX_X) <= 0;
    }
    /**
     * Проверяет {@code argY}.
     * @param argY значение {@code y}.
     * @return {@code true} при {@code 0 <= y <= MAX_Y},
     * иначе - {@code false}.
    */
    public static boolean isYgood(int argY) {
        return argY >= 0 && argY <= MAX_Y;
    }
    
    /**Зануляет поля.*/
    public Coordinates() { x = 0D; y = 0; }
    /**
     * Инициализирует поля с проверкой параметров.
     * @param argX значение {@code x}.
     * @param argY значение {@code y}.
     * @throws BadInputException при плохих параметрах.
    */
    public Coordinates(double argX, int argY)
    throws BadInputException {
        if (isXgood(argX) && isYgood(argY)) {
            x = argX;
            y = argY;
        }
        else { throw new BadInputException(
            "Ошибка в каком-то параметрe Coordinates:" +
            "\n argX: " + argX +
            "\n argY: " + argY
        ); }
    }

    /**
     * Переводит даные о координатах в простой вид.
     * @return объект {@code String},
     * где каждое поле записано через пробел в единственной строке.
     */
    public String getSimpleCoordinates() {
        return Double.valueOf(x).toString() + " " +
               Integer.valueOf(y).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null ||
            !o.getClass().equals(this.getClass())) {
            return false;
        }
        Coordinates otherCoordinates = (Coordinates) o;
        return  ( Double.valueOf(x) ).equals(otherCoordinates.getX()) &&
                y == otherCoordinates.getY();
    }
    @Override
    public String toString() {
        return("[x=" + x + ", y=" + y + "]");
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}