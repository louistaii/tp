package exception;

public enum ExceptionMessage {
    INPUT_EMPTY("Input line cannot be empty!"),
    INVALID_FORMAT("Invalid command format!"),
    INVALID_SHIFTADD_FORMAT("Invalid shift add format! Input as:" +
            " shift add [s/START_TIME] [e/END_TIME] [d/DATE] [st/SHIFT_TASK]"),
    INVALID_TIME_FORMAT("Invalid time format! Input as HH:mm"),
    INVALID_DATE_FORMAT("Invalid date format! Input as YYYY-MM-DD"),
    SHIFT_TASK_EMPTY("Shift task cannot be empty!"),
    INVALID_START_TIME("Start time must be before end time."),
    INVALID_SHIFTDEL_FORMAT("Invalid shift del format! Input as:" +
            " shift del [sn/SHIFT_NUMBER]"),
    INVALID_SHIFT_NUMBER("Shift index must be a positive integer!"),
    INVALID_COMMAND("Invalid command! Use 'add' or 'del'."),
    PARSING_ERROR("Error parsing command!");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
