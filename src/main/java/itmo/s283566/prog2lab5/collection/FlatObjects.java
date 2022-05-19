package itmo.s283566.prog2lab5.collection;

import itmo.s283566.prog2lab5.collection.limits.FlatObjectsLimits;

import java.util.Objects;

/**Класс объектов квартиры. Ограничения: {@link FlatObjectsLimits}.
 * Включает в себя {@link Coordinates}, {@link House}. Является частью {@link Flat}.
 */
public class FlatObjects implements FlatPart{

    private Coordinates coordinates;
    private House house;

    protected FlatObjects(){}

    /**Единственный доступный конструктор, задающий и проверяющий все объекты.
     * Использует {@link #setFlatObjects(Coordinates, House)}.
     * @param aCoordinates аргумент поля coordinates.
     * @param aHouse аргумент поля house.
     * @throws IllegalArgumentException в случае неудачной проверки.
     */
    public FlatObjects(Coordinates aCoordinates, House aHouse)
            throws IllegalArgumentException {
        setFlatObjects(aCoordinates, aHouse);
    }

    public Coordinates getCoordinates() {return coordinates;}

    public House getHouse() {return house;}

    /**Единственный доступный сеттер, меняющий и проверяющий все объекты.
     * @param aCoordinates аргумент поля coordinates.
     * @param aHouse аргумент поля house.
     * @throws IllegalArgumentException в случае неудачной проверки.
     */
    public void setFlatObjects(Coordinates aCoordinates, House aHouse)
            throws IllegalArgumentException {
        if (!FlatObjectsLimits.isCoordinatesGood(aCoordinates)) {
            throw new IllegalArgumentException(FlatObjectsLimits.getCoordinatesLimitInfo());
        }
        coordinates = aCoordinates; house = aHouse;
    }

    @Override
    public void show() {
        System.out.println("Объекты:");
        coordinates.show();
        if (house != null) {house.show();}
    }

    @Override
    public String getSimple() {
        if (house == null) {return coordinates.getSimple();}
        return coordinates.getSimple() + "," + house.getSimple();
    }

    @Override
    public String toString() {return "[coordinates=" + coordinates + ",house=" + house + "]";}

    @Override
    public int hashCode() {return Objects.hash(coordinates, house);}
}
