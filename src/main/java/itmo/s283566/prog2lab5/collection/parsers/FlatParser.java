package itmo.s283566.prog2lab5.collection.parsers;

import itmo.s283566.prog2lab5.collection.*;

/**Parser для {@link Flat}. Формат строки: key,{@link FlatFields},{@link FlatStates},{@link FlatObjects}.
 * Parsers для частей квартиры: {@link FlatFieldsParser}, {@link FlatStatesParser}, {@link FlatObjectsParser}.
 */
public class FlatParser {

    /**Разбирает переданный массив {@link String} объектов и выдает объект {@link Flat}.
     * @param collection коллекция {@link FlatsCollection}, в пределах которой создается квартира.
     * @param lineArray массив всех {@link String} объектов.
     * @return полученный объект {@link Flat}.
     * @throws IllegalArgumentException при некорректном задании массива.
     */
    public static Flat parseLineArray(FlatsCollection collection, String[] lineArray)
            throws IllegalArgumentException {
        //key,name,area,numberOfRooms,isNew,transport,view,cX,cY,hName,hYear,hNumberOfLifts
        FlatFields fields = FlatFieldsParser.parseLineArray(lineArray, 1);
        FlatStates states = FlatStatesParser.parseLineArray(lineArray, 5);
        FlatObjects objects = FlatObjectsParser.parseLineArray(lineArray, 7);
        return collection.newFlat(fields, states, objects);
    }
}
