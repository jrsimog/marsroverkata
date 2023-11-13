package ATL.ACADEMY.RoverMarsKATA.validator;

public class CommandValidator {
    public static void validate(char command) {
        if (command != 'F' && command != 'L' && command != 'B' && command != 'R') {
            throw new IllegalArgumentException("Invalid command: " + command);
        }
    }
}
