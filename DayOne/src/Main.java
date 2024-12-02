import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args){
        String filePath = "day1.txt";

        List<Integer> firstColumn = new ArrayList<>();
        List<Integer> secondColumn = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] columns = line.trim().split("\\s+");
                if (columns.length >= 2) {
                    try {
                        firstColumn.add(Integer.parseInt(columns[0]));
                        secondColumn.add(Integer.parseInt(columns[1]));
                    } catch (NumberFormatException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return;
        }

        Collections.sort(firstColumn);
        Collections.sort(secondColumn);

        Map<Integer,Integer> frequency = new HashMap<>();


        for (Integer number : secondColumn) {
           frequency.put(number,frequency.getOrDefault(number, 0) + 1);
        }

        // Similarity Score
        int similarityScore = 0;
        for(Integer number : firstColumn){
            int numberFrequency = frequency.getOrDefault(number, 0);
            similarityScore += number *+ numberFrequency;
        }

        System.out.println("Similarity Score: " + similarityScore);

        // Total Sum
        // First Column size = Second Column size
        int sum = 0;
        for(int i = 0; i < firstColumn.size(); i++){
            sum+=Math.abs(firstColumn.get(i) - secondColumn.get(i));
        }

        System.out.println("Total Sum: " + sum);
    }

}
