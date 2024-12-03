import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String filePath = "day3.txt";

        int total = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {

            StringBuilder inputContent = new StringBuilder();
            String line;

            while((line = br.readLine()) != null) {
                inputContent.append(line);
            }

            total = calculateTotal(inputContent.toString());

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Total Multiplication: " + total);

    }

    private static int calculateTotal(String inputContent) {
        int total = 0;
        boolean multiplicationEnabled = true;

        String regex = "\\bdo\\(\\)|\\bdon't\\(\\)|mul\\((\\d+),(\\d+)\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(inputContent);

        while (matcher.find()) {
            String command = matcher.group(); // Entire match

            if ("do()".equals(command)) {
                multiplicationEnabled = true;
            } else if ("don't()".equals(command)) {
                multiplicationEnabled = false;
            } else if (multiplicationEnabled && command.startsWith("mul")) {
                int x = Integer.parseInt(matcher.group(1));
                int y = Integer.parseInt(matcher.group(2));
                total += x * y;
            }
        }

        return total;
    }

}