package ru.andrey.kvstorage.console.databasecommands;

import ru.andrey.kvstorage.console.DatabaseCommand;
import ru.andrey.kvstorage.console.ExecutionEnvironment;
import ru.andrey.kvstorage.exception.DatabaseException;

public enum DatabaseCommands {

    CREATE_DATABASE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) throws DatabaseException {
            if (args.length != 2) {
                throw new DatabaseException("CREATE_DATABASE: command requires 1 argument, not " + (args.length - 1));
            }

            String databaseName = args[DB_NAME_INDEX];

            return new CreateDatabase(env, databaseName);
        }
    },

    CREATE_TABLE {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) throws DatabaseException {
            if (args.length != 3) {
                throw new DatabaseException("CREATE_TABLE: command requires 2 arguments, not " + (args.length - 1));
            }

            String databaseName = args[DB_NAME_INDEX];
            String tableName = args[TABLE_NAME_INDEX];

            return new CreateTable(env, databaseName, tableName);
        }
    },

    UPDATE_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) throws DatabaseException {
            if (args.length != 5) {
                throw new DatabaseException("UPDATE_KEY: command requires 4 arguments, not " + (args.length - 1));
            }

            String databaseName = args[DB_NAME_INDEX];
            String tableName = args[TABLE_NAME_INDEX];
            String key = args[KEY_INDEX];
            String value = args[VALUE_INDEX];

            return new UpdateKey(env, databaseName, tableName, key, value);

        }
    },

    READ_KEY {
        @Override
        public DatabaseCommand getCommand(ExecutionEnvironment env, String... args) throws DatabaseException {
            if (args.length != 4) {
                throw new DatabaseException("READ_KEY: command requires 3 arguments, not " + (args.length - 1));
            }

            String databaseName = args[DB_NAME_INDEX];
            String tableName = args[TABLE_NAME_INDEX];
            String key = args[KEY_INDEX];

            return new ReadKey(env, databaseName, tableName, key);
        }
    };

    private static int CMD_NAME_INDEX = 0;
    private static int DB_NAME_INDEX = 1;
    private static int TABLE_NAME_INDEX = 2;
    private static int KEY_INDEX = 3;
    private static int VALUE_INDEX = 4;

    public abstract DatabaseCommand getCommand(ExecutionEnvironment env, String... args) throws DatabaseException;
}
