import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main(String[] args) throws IOException {
//        int allMinutes = 0;
//
//        Pattern p = Pattern.compile("\\d");
//        Matcher m = p.matcher("2h 5m");
//
//
//        String group = m.group();
//        allMinutes = allMinutes + (Integer.parseInt(group) * 60);
//        System.out.printf("Hours: %s ", group);
//
//        group = m.group();
//        allMinutes = allMinutes + Integer.parseInt(group);
//        System.out.println("Minutes: " + group);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = reader.readLine();

        int minutesFromString = getMinutesFromString(input);
        System.out.println(minutesFromString);
    }

    public static int getMinutesFromString(String input) {
        final char h = 'h';
        final char m = 'm';

        String inputCleaned = input.trim().toLowerCase();
        StringBuilder sb = new StringBuilder(inputCleaned.length());
        int hours = 0;
        int minutes = 0;

        for (int i = 0; i < inputCleaned.length(); i++) {
            final char c = inputCleaned.charAt(i);
            if (c > 47 && c < 58) {
                sb.append(c);
            } else if (c == h){
                hours = Integer.parseInt(sb.toString());
                sb = new StringBuilder();
            } else if (c == m) {
                minutes = Integer.parseInt(sb.toString());
            }
        }

        minutes = minutes + (hours * 60);
        return minutes;
    }
}
