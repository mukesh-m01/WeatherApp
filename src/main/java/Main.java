import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter city name: ");
        String city = scanner.nextLine();
        String apiKey = "3f80c20184fb50fc9c5c1ad3dbcecf7e"; // Replace with your API key
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            String jsonResponse = response.toString();
            String temp = jsonResponse.split("\"temp\":")[1].split(",")[0];
            String description = jsonResponse.split("\"description\":\"")[1].split("\"")[0];

            double tempInKelvin = Double.parseDouble(temp);
            int tempInCelsius = (int) (tempInKelvin - 273.15);

            System.out.println("Weather in " + city + ":");
            System.out.println("Temperature: " + tempInCelsius + "°C");
            System.out.println("Condition: " + description);
        } catch (Exception e) {
            System.out.println("Error: Unable to fetch weather data.");
            e.printStackTrace();
        }
    }
}