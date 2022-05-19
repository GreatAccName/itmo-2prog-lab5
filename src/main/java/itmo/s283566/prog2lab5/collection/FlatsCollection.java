package itmo.s283566.prog2lab5.collection;

import java.time.LocalDate;
import java.util.*;
import java.util.function.BiConsumer;

/**Интерфейс "главного героя" программы. Коллекция представляет собой обертку для {@link Map}.
 */
public interface FlatsCollection {

    /**Метод создает квартиру {@link Flat} в пределах данной коллекции.
     * @param fields поля квартиры {@link FlatFields}.
     * @param states состояния квартиры {@link FlatStates}.
     * @param objects объекты квартиры {@link FlatObjects}.
     * @return новая квартира {@link Flat}.
     */
    Flat newFlat(FlatFields fields, FlatStates states, FlatObjects objects);

    /**Метод предоставляет текущий размер всех квартир, созданных в пределах коллекции.
     * @return размер всех квартир, созданных в пределах коллекции.
     */
    int allFlatsSize();

    /**Метод предоставляет текущий размер коллекции.
     * @return размер коллекции.
     */
    int size();

    /**Метод предоставляет название коллекции.
     * @return название коллекции.
     */
    String getTitle();

    /**Метод предоставляет тип коллекции.
     * @return тип коллекции.
     */
    Class<?> getType();

    /**Метод предоставляет дату создания коллекции.
     * @return дата создания коллекции.
     */
    LocalDate getCreationDate();

    /**Метод предоставляет квартиру по индексу в массиве всех созданных квартир
     * в пределах данной коллекции. Замечание: {@code index + 1 = ID}.
     * @param index индекс вызываемой квартиры.
     * @return квартира по переданному индексу.
     * @throws IllegalArgumentException если индекс выходит за рамки массива всех квартир.
     */
    Flat get(int index) throws IllegalArgumentException;

    /**Метод предоставляет квартиру {@link Flat} по ключу.
     * @param key ключ квартиры.
     * @return квартира по ключу.
     * @throws IllegalArgumentException если ключа нет в коллекции.
     */
    Flat get(String key) throws IllegalArgumentException;

    /**Метод предоставляет квартиру {@link Flat} по ID.
     * @param id переданный ID квартиры.
     * @return квартира из коллекции с данным ID.
     * @throws IllegalArgumentException если квартиры с данным id нет в коллекции.
     */
    Flat getById(Integer id) throws IllegalArgumentException;

    /**Метод пытается поместить в коллекцию квартиру {@link Flat} по ключу.
     * @param flat квартира для помещения в коллекцию.
     * @param key  ключ, по которому будет помещена квартира.
     */
    void put(String key, Flat flat) throws IllegalArgumentException;

    /**
     * Метод пытается удалить квартиру {@link Flat} по ключу.
     * @param key ключ, по которому будет удалена квартира.
     * @throws IllegalArgumentException если ключа нет в коллекции.
     */
    void remove(String key) throws IllegalArgumentException;

    /**Метод очищает коллекцию.
     */
    void clear();

    /**Метод попытается применить метод {@code action} к каждой квартире в коллекции.
     * @param action применяемый метод.
     * @throws IllegalArgumentException при неудачной попытке.
     */
    void forEach(BiConsumer<String, Flat> action) throws IllegalArgumentException;

    /**Метод предоставляет итератор по элементам коллекции.
     * @return итератор по элементам коллекции.
     */
    Iterator<Map.Entry<String, Flat>> iterator();

    /**Возвращает не модифицируемое представление коллекции квартир в обратном порядке.
     * @return не модифицируемое представление коллекции квартир в обратном порядке.
     */
    Map<String, Flat> unmodifiableDescendingCollection();
}
