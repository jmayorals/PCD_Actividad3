package actividad3;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class LogAnalyzer {

    public static void main(String[] args) {
        String logFilePath = "C:\\Users\\Javier\\eclipse-workspace\\PCD\\src\\actividad3\\logs.txt";
        Map<String, Map<String, Integer>> logCounts = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                processLine(line, logCounts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        printLogCounts(logCounts);
    }

    private static void processLine(String line, Map<String, Map<String, Integer>> logCounts) {
        String[] parts = line.split("\\|");
        if (parts.length > 3) {
            String appName = parts[0].trim();
            String logLevel = parts[1].trim();
            logCounts.putIfAbsent(appName, new HashMap<>());
            Map<String, Integer> appLogCounts = logCounts.get(appName);
            appLogCounts.put(logLevel, appLogCounts.getOrDefault(logLevel, 0) + 1);
        }
    }

    private static void printLogCounts(Map<String, Map<String, Integer>> logCounts) {
        for (String appName : logCounts.keySet()) {
            System.out.println("Aplicacion: " + appName);
            Map<String, Integer> appLogCounts = logCounts.get(appName);
            for (String logLevel : appLogCounts.keySet()) {
                System.out.println("  " + logLevel + ": " + appLogCounts.get(logLevel));
            }
        }
    }
}
