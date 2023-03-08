package org.example.weather_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.json.JSONObject;

public class Controller {

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TextField city;
    @FXML
    private Text temp_info;
    @FXML
    private Button getData;
    @FXML
    private Text temp_feels;
    @FXML
    void initialize() {
        getData.setOnAction(event -> {
            String getUserCity = city.getText().trim();
            if(!getUserCity.equals("")) {
                String output = getUrlContent("https://history.openweathermap.org/data/2.5/aggregated/year?q="+getUserCity+
                        "&appid=41407c07725bcdfb2b03b35d82ecf16b");

                if (!output.isEmpty()) { // Нет ошибки и такой город есть
                    JSONObject obj = new JSONObject(output);
                    temp_info.setText("Temperature: " + obj.getJSONObject("main").getDouble("temp"));
                    temp_feels.setText("feels like: " + obj.getJSONObject("main").getDouble("feels_like"));
                }
            }
        });
    }

    // Обработка URL адреса и получение данных с него
    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } //catch(Exception e) {
        catch (MalformedURLException e) {
            throw new RuntimeException( e );
        } catch (IOException e) {
            throw new RuntimeException( e );
        }
        //System.out.println("City not found: ");
        //}
        return content.toString();
    }

}