package itmo.s283566.prog2lab5.commands;

import java.util.HashMap;
import java.util.Map;

/**Класс ассоциирует каждую команду с ее именем.
 */
public class AllCommands {
    private static final Map<String, Command> allCommands = new HashMap<>();

    static {
        allCommands.put("help", new Help());
        allCommands.put("info", new Info());
        allCommands.put("show", new Show());
        allCommands.put("clear", new Clear());
        allCommands.put("save", new Save());
        allCommands.put("exit", new Exit());
        allCommands.put("print_descending", new PrintDescending());
        allCommands.put("remove_lower", new RemoveLower());
        allCommands.put("update", new Update());
        allCommands.put("min_by_id", new MinById());
        allCommands.put("execute_script", new ExecuteScript());
        allCommands.put("insert", new Insert());
        allCommands.put("remove_key", new RemoveKey());
        allCommands.put("filter_starts_with_name", new FilterStartsWithName());
        allCommands.put("replace_if_greater", new ReplaceIfGreater());
        allCommands.put("replace_if_lower", new ReplaceIfLower());
    }

    /**Возвращает команду {@link Command} по ее имени.
     * @param commandName имя команды.
     * @return команда {@link Command} по ее имени.
     * @throws IllegalArgumentException если нет команды по данному имени.
     */
    public static Command getCommand(String commandName) throws IllegalArgumentException {
        Command command = allCommands.get(commandName);
        if (command == null) {
            throw new IllegalArgumentException("Нет команды \"" + commandName + "\".");
        }
        return command;
    }
}
