import java.util.*;
import java.util.stream.Collectors;

import static file_utils.FileReader.readFile;

public class Calories {
    public static void main(String[] args) {

        System.out.println(getTopCaloriesValue(getElvesAndCalories(), 3));
//        System.out.println("Elv with the most number of calories " + elvWithMostCalories());
//        System.out.println("He carries " + howMuchCaloriesIsCarrying(elvWithMostCalories()) + " calories");
    }

    private static Map<Integer, Integer> getElvesAndCalories() {
        List<String> caloriesList = readFile();
        Map<Integer, Integer> elvesAndCalories = new HashMap<>();
        int elvNumber = 1;
        int caloriesOfElv = 0;
        assert caloriesList != null;
        for (String calory : caloriesList) {
            try {
                caloriesOfElv += Integer.parseInt(calory);
            } catch (NumberFormatException exc) {
                elvesAndCalories.put(elvNumber, caloriesOfElv);
                caloriesOfElv = 0;
                elvNumber += 1;
            }
        }
        //elvesAndCalories.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));
        return elvesAndCalories;
    }

    public static void printMap(Map<Integer, Integer> mapToPrint) {
        mapToPrint.forEach((k, v) -> System.out.println("Key: " + k + " Value: " + v));
    }

    public static int elvWithMostCalories() {
        Map<Integer, Integer> elvesAndCalories = getElvesAndCalories();
        return elvesAndCalories
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .get().getKey();
    }

    public static int howMuchCaloriesIsCarrying(int elvNumber) {
        Map<Integer, Integer> elvesAndCalories = getElvesAndCalories();
        return elvesAndCalories.get(elvNumber);
    }

    private static int getTopCaloriesValue(Map<Integer, Integer> mapToSort, long numberOfElements) {

        Map<Integer, Integer> sortedAndLimitedMap = mapToSort.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .limit(numberOfElements)
                .collect(Collectors.toMap((Map.Entry::getKey), (Map.Entry::getValue)));

        int topCaloriesValue = 0;
        for (var calory : sortedAndLimitedMap.entrySet()) {
            topCaloriesValue += calory.getValue();
        }
        return topCaloriesValue;
    }
}
