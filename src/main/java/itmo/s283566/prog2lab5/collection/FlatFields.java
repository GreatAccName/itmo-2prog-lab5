package itmo.s283566.prog2lab5.collection;

import itmo.s283566.prog2lab5.collection.limits.FlatFieldsLimits;

import java.util.Objects;

/**Класс полей квартиры. Ограничения: {@link FlatFieldsLimits}. Является частью {@link Flat}.
 */
public class FlatFields implements FlatPart {

    private String name;
    private float area;
    private int numberOfRooms;
    private Boolean isNew;

    protected FlatFields() {}

    /**Единственный доступный конструктор полей квартиры, задающий и проверяющий все поля.
     * Использует {@link #setFlatFields(String, float, int, Boolean)}.
     * @param aName аргумент имени.
     * @param aArea аргумент площади.
     * @param aNumberOfRooms аргумент количества комнат.
     * @param aIsNew аргумент новизны.
     * @throws IllegalArgumentException в случае неудачной проверки аргумента.
     */
    public FlatFields(String aName, float aArea, int aNumberOfRooms, Boolean aIsNew)
            throws IllegalArgumentException {
        setFlatFields(aName, aArea, aNumberOfRooms, aIsNew);
    }

    public String getName() {return name;}

    /**Геттер площади квартиры.
     * @return площадь квартиры.
     */
    public float getArea() {return area;}

    public int getNumberOfRooms() {return numberOfRooms;}

    public Boolean getIsNew() {return isNew;}

    /**Единственный сеттер полей квартиры, меняющий и проверяющий все поля.
     * @param aName аргумент поля name.
     * @param aArea аргумент поля area.
     * @param aNumberOfRooms аргумент поля numberOfRooms.
     * @param aIsNew аргумент поля isNew.
     * @throws IllegalArgumentException в случае неудачной проверки аргумента.
     */
    public void setFlatFields(String aName, float aArea, int aNumberOfRooms, Boolean aIsNew)
            throws IllegalArgumentException {
        if (!FlatFieldsLimits.isNameGood(aName)) {
            throw new IllegalArgumentException(FlatFieldsLimits.getNameLimitInfo());
        }
        if (!FlatFieldsLimits.isAreaGood(aArea)) {
            throw new IllegalArgumentException(FlatFieldsLimits.getAreaLimitInfo());
        }
        if (!FlatFieldsLimits.isNumberOfRoomsGood(aNumberOfRooms)) {
            throw new IllegalArgumentException(FlatFieldsLimits.getNumberOfRoomsLimitInfo());
        }
        if (!FlatFieldsLimits.isIsNewGood(aIsNew)) {
            throw new IllegalArgumentException(FlatFieldsLimits.getIsNewLimitInfo());
        }
        name = aName; area = aArea; numberOfRooms = aNumberOfRooms; isNew = aIsNew;
    }

    @Override
    public void show() {
        System.out.print("Поля:");
        System.out.print(" [name=" + name + "],");
        System.out.print(" [area=" + area + "],");
        System.out.print(" [numberOfRooms=" + numberOfRooms + "],");
        System.out.println(" [isNew=" + isNew + "].");
    }

    @Override
    public String getSimple() {return name + "," + area + "," + numberOfRooms + "," + isNew;}

    @Override
    public String toString() {
        return "[name=" + name +
                ",area=" + area + ",numberOfRooms=" + numberOfRooms + ",isNew=" + isNew + "]";
    }

    @Override
    public int hashCode() {return Objects.hash(name, area, numberOfRooms, isNew);}
}
