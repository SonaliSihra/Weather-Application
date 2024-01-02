package com.example.weatherapplication;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
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
    private ImageView weatherimage;

    @FXML
    private Button searchbutton;

    @FXML
    protected void onSearchButtonClick(ActionEvent event) throws IOException, InterruptedException {

        String locationSearch = locationtext.getText();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://api.weatherstack.com/current?access_key=" + "de22ca3008b54bf36cda075680ca92b2" + "&query=" + locationSearch))
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

        weather.setText((String) map.get("weather_descriptions").get(0));
        temp.setText(String.valueOf(map.get("temperature")));
    }
}