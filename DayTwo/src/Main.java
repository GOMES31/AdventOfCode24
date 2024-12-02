import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "day2.txt";

        int safeCount = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                List<Integer> parsed = parseLine(line);
                if (isLineSafeWithOneRemoval(parsed)) {
                    safeCount++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        System.out.println("Safe count: " + safeCount);
    }

    private static List<Integer> parseLine(String line) {
        String[] lineParts = line.trim().split("\\s+");

        List<Integer> parsedLine = new ArrayList<>();

        for (String part: lineParts) {
            parsedLine.add(Integer.parseInt(part));
        }

        return parsedLine;
    }
    private static boolean isLineSafe(List<Integer> parsedLine) {

        int initialDiff = parsedLine.get(1) - parsedLine.get(0);
        boolean isIncreasing = initialDiff > 0;

        for (int i = 0; i < parsedLine.size() - 1; i++) {
            int current = parsedLine.get(i);
            int next = parsedLine.get(i + 1);


            if((isIncreasing && next <= current) || (!isIncreasing && next >= current)) {
                return false;
            }

            int diff = Math.abs(current - next);

            if (diff < 1 || diff > 3) {
                return false;
            }
        }

        return true;
    }

    private static boolean isLineSafeWithOneRemoval(List<Integer> parsedLine){
        if (isLineSafe(parsedLine)) {
            return true;
        }

        for (int i = 0; i < parsedLine.size(); i++) {
            List<Integer> modifiedLine = new ArrayList<>(parsedLine);

            modifiedLine.remove(i);
            if (isLineSafe(modifiedLine)) {
                return true;
            }
        }

        return false;

    }
}
