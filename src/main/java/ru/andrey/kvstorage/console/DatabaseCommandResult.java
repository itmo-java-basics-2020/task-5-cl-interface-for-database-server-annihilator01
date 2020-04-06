package ru.andrey.kvstorage.console;

import java.util.Optional;

public interface DatabaseCommandResult {

    Optional<String> getResult();

    DatabaseCommandStatus getStatus();

    boolean isSuccess();

    String getErrorMessage();

    static DatabaseCommandResult success(String result) {
        return new DatabaseSingleCommandResult(result, DatabaseCommandStatus.SUCCESS, true, null);
    }

    static DatabaseCommandResult error(String message) {
        return new DatabaseSingleCommandResult(null, DatabaseCommandStatus.FAILED, false, message);
    }

    enum DatabaseCommandStatus {
        SUCCESS, FAILED
    }

    class DatabaseSingleCommandResult implements DatabaseCommandResult {

        private String result;
        private DatabaseCommandStatus status;
        private boolean isSuccess;
        private String errorMessage;

        private DatabaseSingleCommandResult(String result, DatabaseCommandStatus status,
                                            boolean isSuccess, String errorMessage) {
            this.result = result;
            this.status = status;
            this.isSuccess = isSuccess;
            this.errorMessage = errorMessage;
        }

        @Override
        public Optional<String> getResult() {
            return Optional.ofNullable(result);
        }

        @Override
        public DatabaseCommandStatus getStatus() {
            return status;
        }

        @Override
        public boolean isSuccess() {
            return isSuccess;
        }

        @Override
        public String getErrorMessage() {
            return errorMessage;
        }
    }
}