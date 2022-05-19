package itmo.s283566.prog2lab5.collection;

import java.time.LocalDate;
import java.util.*;
import java.util.function.BiConsumer;

/**Класс коллекции квартир звезд 90-х.
 */
public class FlatsOf90sStars implements FlatsCollection {
    private final LocalDate creationDate = LocalDate.now();
    private final Map<String, Flat> flats;
    private final ArrayList<Flat> allFlats;

    /**Единственный конструктор, создающий пустую коллекцию.
     */
    public FlatsOf90sStars() {flats = new HashMap<>(); allFlats = new ArrayList<>();}

    @Override
    public Flat newFlat(FlatFields fields, FlatStates states, FlatObjects objects) {
        Flat flat = new Flat(this, fields, states, objects);
        allFlats.add(flat);
        return flat;
    }

    @Override
    public int allFlatsSize() {return allFlats.size();}

    @Override
    public int size() {return flats.size();}

    @Override
    public String getTitle() {return "Квартиры звезд 90-х";}

    @Override
    public Class<?> getType() {return flats.getClass();}

    @Override
    public LocalDate getCreationDate() {return creationDate;}

    @Override
    public Flat get(String key) throws IllegalArgumentException {
        if (key == null || !flats.containsKey(key)) {
            throw new IllegalArgumentException("Нет ключа \"" + key + "\" в коллекции.");
        }
        return flats.get(key);
    }

    @Override
    public Flat get(int index) throws IllegalArgumentException {
        if (index < 0 || index >= allFlats.size()) {
            throw new IllegalArgumentException("Индекс=" + index + ", а создано квартир " + allFlatsSize());
        }
        return allFlats.get(index);
    }

    @Override
    public Flat getById(Integer id) throws IllegalArgumentException {
        for (Map.Entry<String, Flat> entry : flats.entrySet()) {
            Flat flat = entry.getValue();
            if (flat.getId().equals(id)) {return flat;}
        }
        throw new IllegalArgumentException("В коллекции нет элемента с ID " + id + ".");
    }

    @Override
    public void put(String key, Flat flat) throws IllegalArgumentException {
        if (key == null) {throw new IllegalArgumentException("Ключ \"null\" недопустим.");}
        flats.put(key, flat);
    }

    @Override
    public void remove(String key) throws IllegalArgumentException {
        if (key == null || !flats.containsKey(key)) {
            throw new IllegalArgumentException("Нет ключа \"" + key + "\" в коллекции.");
        }
        flats.remove(key);
    }

    @Override
    public void clear() {flats.clear();}

    @Override
    public void forEach(BiConsumer<String, Flat> action) throws IllegalArgumentException {
        for (Map.Entry<String, Flat> entry : flats.entrySet()) {
            action.accept(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Iterator<Map.Entry<String, Flat>> iterator() {return flats.entrySet().iterator();}

    @Override
    public Map<String, Flat> unmodifiableDescendingCollection() {
        return Collections.unmodifiableMap(new TreeMap<>(flats).descendingMap());
    }
}
