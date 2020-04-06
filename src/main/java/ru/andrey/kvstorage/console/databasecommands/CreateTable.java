package ru.andrey.kvstorage.console.databasecommands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.DatabaseCommandResult;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;
import ru.andrey.kvstorage.logic.Database;

import java.util.Optional;

public class CreateTable implements DatabaseCommand {

    private ExecutionEnvironment env;
    private String databaseName;
    private String tableName;

    CreateTable(ExecutionEnvironment env, String databaseName, String tableName) {
        this.env = env;
        this.databaseName = databaseName;
        this.tableName = tableName;
    }

    @Override
    public DatabaseCommandResult execute() {
        Optional<Database> optionalDatabase = env.getDatabase(databaseName);

        if (optionalDatabase.isEmpty()) {
            return DatabaseCommandResult.error("CREATE_TABLE: database '" + databaseName + "' doesn't exist in execution environment");
        }

        Database db = optionalDatabase.get();
        try {
            db.createTableIfNotExists(tableName);
            return DatabaseCommandResult.success(null);
        } catch (DatabaseException e) {
            return DatabaseCommandResult.error(e.getMessage());
        }
    }
}
