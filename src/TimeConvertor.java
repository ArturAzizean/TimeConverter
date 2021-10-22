import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TimeConvertor {
    private static final String CALCULATE = "calculate";
    private static final String STOP = "stop";
    private static final String HISTORY = "history";


    public static void main(String[] args) throws IOException {
        List<String> allCommands = new ArrayList<>();

        int allMinutes = 0;

        while (true) {
            System.out.println("Current: " + calculate(allMinutes));

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = reader.readLine();
            allCommands.add(input);

            if (isStop(input)) return;
            if (isLogHistory(input)) allCommands.forEach(System.out::println);

            else if (isCalculate(input)) {
                System.out.println(calculate(allMinutes));

                allMinutes = 0;

            } else if (isHoursOrMinutes(input))
                allMinutes = allMinutes + getMinutesFromString(input);
            else {
                try {
                    allMinutes = allMinutes + Integer.parseInt(input);
                } catch (Exception e) {
                    System.out.println("Unknown command");
                }
            }

        }
    }

    private static String calculate(int allMinutes) {
        int restMinutes = allMinutes % 60;
        int hours = (allMinutes / 60);

        return String.format("%dh %02dm \n", hours, restMinutes);
    }

    private static boolean isHoursOrMinutes(String input) {
        // example 2h 5m
        return input.toLowerCase().contains("h") || input.toLowerCase().contains("m");
    }

    private static boolean isStop(String input) {
        String cleanedInput = input.trim().toLowerCase();
        return STOP.contains(cleanedInput);
    }

    private static boolean isLogHistory(String input) {
        String cleanedInput = input.trim().toLowerCase();
        return HISTORY.contains(cleanedInput);
    }

    private static boolean isCalculate(String input) {
        String cleanedInput = input.trim().toLowerCase();
        return CALCULATE.contains(cleanedInput);
    }

    public static int getMinutesFromString(String input) {
        final char h = 'h';
        final char m = 'm';
        boolean subtraction = false;

        String inputCleaned = input.trim().toLowerCase();
        StringBuilder sb = new StringBuilder(inputCleaned.length());
        boolean firstHours = false;
        int hours = 0;
        int secondHours = 0;
        int minutes = 0;

        for (int i = 0; i < inputCleaned.length(); i++) {
            final char c = inputCleaned.charAt(i);
            // if number
            if (c > 47 && c < 58) {
                sb.append(c);
            } else if (c == h) {
                if (!firstHours) {
                    hours = Integer.parseInt(sb.toString());
                    firstHours = true;
                } else
                    secondHours = Integer.parseInt(sb.toString());

                sb = new StringBuilder();

            } else if (c == m) {
                minutes = Integer.parseInt(sb.toString());
            } else if (c == '-') {
                subtraction = true;
            }
        }

        if (subtraction) {
            if (hours != 0) {
                hours = (hours * 60);
                if (secondHours != 0) {
                    secondHours = (secondHours * 60);
                    minutes = hours - secondHours;
                } else if (minutes != 0)
                    minutes = hours - minutes;
                else minutes = -hours;
            }

        } else
            minutes = minutes + (hours * 60);

        return minutes;
    }
}
