package ru.andrey.kvstorage.console.databasecommands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class ReadKey implements DatabaseCommand {

    private ExecutionEnvironment env;
    private String databaseName;
    private String tableName;
    private String key;

    ReadKey(ExecutionEnvironment env, String databaseName, String tableName, String key) {
        this.env = env;
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> optionalDatabase = env.getDatabase(databaseName);

        if (optionalDatabase.isEmpty()) {
            return DatabaseCommandResult.error("READ_KEY: database '" + databaseName + "' doesn't exist in execution environment");
        }

        Database db = optionalDatabase.get();
        try {
            String result = db.read(tableName, key);
            return DatabaseCommandResult.success(result);
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}
