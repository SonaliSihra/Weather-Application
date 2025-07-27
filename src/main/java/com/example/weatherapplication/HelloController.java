package com.example.weatherapplication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;

public class HelloController {
    @FXML
    private TextField locationtext;
    @FXML
    private Text weather;
    @FXML
    private Text temp;
    @FXML
    private Text observationtime;

    @FXML
    private ImageView weatherimage;

    @FXML
    private Text country;

    @FXML
    private Text localTime;

    @FXML
    private Button searchbutton;

    @FXML
    public void initialize() {
        searchbutton.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                searchbutton.fire(); // Programmatically fires the button's action
            }
        });
    }

    @FXML
    protected void onSearchButtonClick(ActionEvent event) throws IOException, InterruptedException {
        initialize();
        String locationSearch = locationtext.getText();
        String encodedCity = URLEncoder.encode(locationSearch, StandardCharsets.UTF_8);
        String accessKey = GetPropertyValues.accessKey;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.weatherstack.com/current?access_key=" + accessKey + "&query=" + encodedCity))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = null;

        //try {
        response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(response.body().toString()).get("current");

        Map<String, ArrayList<Object>> map = mapper.convertValue(jsonNode, Map.class);

        System.out.println(map);
        System.out.println("imageicon url : " + map.get("weather_icons").get(0));

        weatherimage.setImage(new Image(map.get("weather_icons").get(0).toString()));

        weather.setText("Weather: " + map.get("weather_descriptions").get(0));
        temp.setText("Temperature: " + map.get("temperature"));
        observationtime.setText("Checked at: " + map.get("observation_time"));

        ObjectMapper locMapper = new ObjectMapper();
        JsonNode jsonNode1 = locMapper.readTree(response.body().toString()).get("location");
        Map<String, ArrayList<Object>> locationMap = locMapper.convertValue(jsonNode1, Map.class);

        country.setText("Country: " + locationMap.get("country"));
        localTime.setText("localTime: " + locationMap.get("localtime"));

    }
}