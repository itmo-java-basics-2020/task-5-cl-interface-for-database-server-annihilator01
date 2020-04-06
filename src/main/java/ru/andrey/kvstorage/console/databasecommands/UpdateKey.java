package ru.andrey.kvstorage.console.databasecommands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class UpdateKey implements DatabaseCommand {

    private ExecutionEnvironment env;
    private String databaseName;
    private String tableName;
    private String key;
    private String value;

    UpdateKey(ExecutionEnvironment env, String databaseName, String tableName, String key, String value) {
        this.env = env;
        this.databaseName = databaseName;
        this.tableName = tableName;
        this.key = key;
        this.value = value;
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> optionalDatabase = env.getDatabase(databaseName);

        if (optionalDatabase.isEmpty()) {
            return DatabaseCommandResult.error("UPDATE_KEY: database '" + databaseName + "' doesn't exist in execution environment");
        }

        Database db = optionalDatabase.get();
        try {
            db.write(tableName, key, value);
            return DatabaseCommandResult.success("UPDATE_KEY: key '" + key + "' associated with value '" + value +
                                                 "' in table '" + tableName + "' in database '" + databaseName + "'");
        } catch (DatabaseException de) {
            return DatabaseCommandResult.error(de.getMessage());
        }
    }
}
