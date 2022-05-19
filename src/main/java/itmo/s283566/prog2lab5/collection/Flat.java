package itmo.s283566.prog2lab5.collection;

import itmo.s283566.prog2lab5.collection.limits.FlatFieldsLimits;
import itmo.s283566.prog2lab5.collection.limits.FlatObjectsLimits;
import itmo.s283566.prog2lab5.collection.limits.FlatStatesLimits;

import java.time.LocalDate;
import java.util.Objects;

/**Класс квартиры, элемент {@link FlatsCollection}, часть "главного героя" программы.
 * Квартира может быть создана только в рамках какой-то коллекции {@link FlatsCollection}.
 * Класс разделен на 3 части: поля, состояния и объекты:
 * {@link FlatFields}, {@link FlatStates}, {@link FlatObjects}.
 * У каждого элемента каждой части есть свои ограничения, представляемые в виде классов:
 * {@link FlatFieldsLimits}, {@link FlatStatesLimits}, {@link FlatObjectsLimits}.
 * Одна квартира является элементом коллекции: {@link FlatsCollection}.
 */
public class Flat implements Comparable<Flat> {

    private FlatsCollection collection;
    private final LocalDate creationDate = LocalDate.now();
    private Integer id;

    private FlatFields fields;
    private FlatStates states;
    private FlatObjects objects;

    protected Flat(){}

    /**Защищенный конструктор, задающий поля, состояния и объекты квартиры.
     * @param aCollection коллекция, в пределах которой создается квартира.
     * @param aFields аргумент поля flatFields.
     * @param aStates аргумент поля flatStates.
     * @param aObjects аргумент поля flatObjects.
     */
    protected Flat(FlatsCollection aCollection,
                   FlatFields aFields, FlatStates aStates, FlatObjects aObjects) {
        collection = aCollection;
        id = collection.allFlatsSize() + 1;
        fields = aFields; states = aStates; objects = aObjects;
    }

    /**Возвращает коллекцию {@link FlatsCollection}, в пределах которой создана квартира.
     * @return коллекция, в пределах которой создана квартира.
     */
    public FlatsCollection getCollection() {return collection;}

    /**Возвращает дату создания квартиры.
     * @return дата создания квартиры.
     */
    public LocalDate getCreationDate() {return creationDate;}

    /**Поле ID квартиры, являющееся по сути порядковым по созданию номером.
     * @return ID квартиры.
     */
    public Integer getId() {return id;}

    public FlatFields getFields() {return fields;}

    public FlatStates getStates() {return states;}

    public FlatObjects getObjects() {return objects;}

    /**Единственный доступный сеттер, меняющий поля, состояния и объекты квартиры.
     * @param aFields аргумент поля flatFields.
     * @param aStates аргумент поля flatStates.
     * @param aObjects аргумент поля flatObjects.
     */
    public void setFlat(FlatFields aFields, FlatStates aStates, FlatObjects aObjects) {
        fields = aFields; states = aStates; objects = aObjects;
    }

    /**Метод выводит в стандартный поток вывода в удобном виде для глаза
     * информацию о полях, состояниях и объектах квартиры.
     */
    public void show() {
        System.out.println("Квартира с [ID=" + id + "], созданная [" + creationDate + "]:");
        fields.show();
        states.show();
        objects.show();
    }

    /**Метод для представления квартир в удобном для передачи в формат .csv виде.
     * @return представления квартир в удобном для передачи в формат .csv виде.
     */
    public String getSimple() {return fields.getSimple() + "," + states.getSimple() + "," + objects.getSimple();}

    /**Сравнение по {@code area} (площади); {@link FlatFields#getArea()}.
     * @param aFlat квартира для сравнения.
     * @return меньше 0 при area меньше aArea, равно 0 при area = aArea, больше 0 при area больше aArea,
     * где area - площадь экземпляра, aArea - площадь {@code aFlat}.
     */
    @Override
    public int compareTo(Flat aFlat) {return Float.compare(this.fields.getArea(), aFlat.fields.getArea());}

    @Override
    public String toString() {
        return "[id=" + id + ",creationDate=" + creationDate + ",flatFields=" +
                fields + ",flatStates=" + states + ",flatObjects=" + objects + "]";
    }

    @Override
    public int hashCode() {return Objects.hash(fields, states, objects);}
}
