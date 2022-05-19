package itmo.s283566.prog2lab5.collection;

import itmo.s283566.prog2lab5.collection.limits.CoordinatesLimits;

import java.util.Objects;

/**Класс координат x и y. Ограничения: {@link CoordinatesLimits}. Входит в состав: {@link FlatObjects}.
 */
public class Coordinates implements FlatPart {

    private double x;
    private int y;

    protected Coordinates(){}

    /**Единственный конструктор координат x и y, проверяющий и задающий все поля.
     * Использует {@link #setCoordinates(double, int)}.
     * @param aX аргумент поля x.
     * @param aY аргумент поля y.
     * @throws IllegalArgumentException в случае неудачной проверки полей.
     */
    public Coordinates(double aX, int aY) throws IllegalArgumentException {
        setCoordinates(aX, aY);
    }

    public double getX() {return x;}

    public int getY() {return y;}

    /**Единственный сеттер координат x и y, проверяющий и меняющий все поля.
     * @param aX аргумент поля x.
     * @param aY аргумент поля y.
     * @throws IllegalArgumentException в случае неудачной проверки полей.
     */
    public void setCoordinates(double aX, int aY) throws IllegalArgumentException {
        if (!CoordinatesLimits.isXGood(aX)) {
            throw new IllegalArgumentException(CoordinatesLimits.getXLimitInfo());
        }
        x = aX; y = aY;
    }

    @Override
    public void show() {
        System.out.print("Координаты:");
        System.out.print(" [x=" + x + "],");
        System.out.println(" [y=" + y + "].");
    }

    @Override
    public String getSimple() {return x + "," + y;}

    @Override
    public String toString() {return "[x=" + x + ",y=" + y + "]";}

    @Override
    public int hashCode() {return Objects.hash(x, y);}
}
