package ru.itis.gilyazov.impl.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.SneakyThrows;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.gilyazov.api.dto.Weather;

import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class WeatherServiceApi {

    @Value("${openweather.host}")
    private String host;

    @Value("${openweather.token}")
    private String token;

    @Value("${openweather.timeout}")
    private String timeout;

    private Date date;

    private Weather weather;


    @SneakyThrows
    public Weather getWeatherNow() {

        Date currentDate = new Date();

        if (date == null || currentDate.getTime() - date.getTime() >= Integer.parseInt(timeout) * 1000 || weather == null) {
            Weather weather = getWeather();

            System.out.println(weather);
            date = currentDate;
        }

        return weather;
    }

    @SneakyThrows
    private Weather getWeather() {
        weather = new Weather();

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(host);

        List<NameValuePair> urlParameters = new ArrayList<>();

        urlParameters.add(new BasicNameValuePair("appid", token));
        urlParameters.add(new BasicNameValuePair("q", "Казань"));
        urlParameters.add(new BasicNameValuePair("units", "metric"));
        urlParameters.add(new BasicNameValuePair("lang", "en"));

        URI uri = new URIBuilder(httpGet.getURI())
                .addParameters(urlParameters)
                .build();

        httpGet.setURI(uri);

        CloseableHttpResponse response = client.execute(httpGet);

        if (response.getStatusLine().getStatusCode() == 200) {
            JsonParser parser = new JsonParser();
            String result = EntityUtils.toString(response.getEntity());

            JsonObject object = (JsonObject) parser.parse(result);
            JsonObject weatherObject = object.getAsJsonArray("weather").get(0).getAsJsonObject();
            weather.setStatus(weatherObject.get("description").getAsString());

            JsonObject mainObject = object.getAsJsonObject("main");
            weather.setTemperature(mainObject.get("temp").getAsDouble());
        }

        return weather;

    }
}
