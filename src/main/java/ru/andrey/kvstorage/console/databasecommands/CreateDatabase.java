package ru.andrey.kvstorage.console.databasecommands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;

public class CreateDatabase implements DatabaseCommand {

    private ExecutionEnvironment env;
    private String databaseName;

    CreateDatabase(ExecutionEnvironment env, String databaseName) {
        this.env = env;
        this.databaseName = databaseName;
    }

    @Override
    public DatabaseCommandResult execute() {
        return null;
    }
}
