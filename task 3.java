import java.util.Scanner;

public class TemperatureConverter {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Temperature Converter");

        // Get user input for temperature value
        System.out.print("Enter temperature value: ");
        double temperature = getValidTemperature(scanner);

        // Get user input for source scale
        String sourceScale = getValidScale(scanner, "Enter source scale (Celsius, Fahrenheit, Kelvin): ");

        // Get user input for target scale
        String targetScale = getValidScale(scanner, "Enter target scale (Celsius, Fahrenheit, Kelvin): ");

        // Convert the temperature
        double convertedTemperature = convertTemperature(temperature, sourceScale, targetScale);
        
        // Display the result
        System.out.printf("%.2f degrees %s is %.2f degrees %s.%n", temperature, sourceScale, convertedTemperature, targetScale);

        scanner.close();
    }

    private static double getValidTemperature(Scanner scanner) {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a numeric temperature value: ");
            }
        }
    }

    private static String getValidScale(Scanner scanner, String prompt) {
        String scale;
        while (true) {
            System.out.print(prompt);
            scale = scanner.nextLine().trim().toLowerCase();
            if (scale.equals("celsius") || scale.equals("fahrenheit") || scale.equals("kelvin")) {
                return scale.substring(0, 1).toUpperCase() + scale.substring(1);
            } else {
                System.out.println("Invalid scale. Please enter Celsius, Fahrenheit, or Kelvin.");
            }
        }
    }

    private static double convertTemperature(double temperature, String sourceScale, String targetScale) {
        // Convert the temperature to Celsius first
        double tempInCelsius;
        
        switch (sourceScale) {
            case "Celsius":
                tempInCelsius = temperature;
                break;
            case "Fahrenheit":
                tempInCelsius = (temperature - 32) * 5 / 9;
                break;
            case "Kelvin":
                tempInCelsius = temperature - 273.15;
                break;
            default:
                throw new IllegalArgumentException("Unknown scale: " + sourceScale);
        }

        // Convert from Celsius to the target scale
        switch (targetScale) {
            case "Celsius":
                return tempInCelsius;
            case "Fahrenheit":
                return (tempInCelsius * 9 / 5) + 32;
            case "Kelvin":
                return tempInCelsius + 273.15;
            default:
                throw new IllegalArgumentException("Unknown scale: " + targetScale);
        }
    }
}