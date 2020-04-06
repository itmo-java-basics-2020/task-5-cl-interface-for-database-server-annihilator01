package ru.andrey.kvstorage;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.console.databasecommands.DatabaseCommands;
import ru.andrey.kvstorage.exception.DatabaseException;

public class DatabaseServer {

    private final ExecutionEnvironment env;

    public DatabaseServer(ExecutionEnvironment env) {
        this.env = env;
    }

    public static void main(String[] args) {
    }

    DatabaseCommandResult executeNextCommand(String commandText) {
        if (commandText == null) {
            return DatabaseCommandResult.error("Database Server: incoming command text can't be null");
        }

        String[] commandArgs = commandText.split(" ");
        String commandName = commandArgs[0];
        DatabaseCommand databaseCommand;

        try {
            DatabaseCommands commandWrapper = DatabaseCommands.valueOf(commandName);
            databaseCommand = commandWrapper.getCommand(env, commandArgs);
        } catch (IllegalArgumentException iae) {
            return DatabaseCommandResult.error("Database Server: command '" + commandName + "' doesn't exist");
        } catch (DatabaseException de) {
            return DatabaseCommandResult.error(de.getMessage());
        }

        return databaseCommand.execute();
    }
}
