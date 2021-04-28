package com.example.mirea_app.ui.main;

import java.util.ArrayList;
import java.util.List;

public class NewsListItem {
    private String head;
    private String body;
    private String url;

    public static final List<NewsListItem> ITEMS = new ArrayList<NewsListItem>();
    static {
        String names[] = {
                "Clouds",
                "Sun",
                "Partial clouds",
                "Snow",
                "Sleet",
                "Mist",
                "Clear",
                "Rain",
                "Rain thunder",
                "Fog"
        };
        String urls[] = {
                "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/angry_clouds.png",
                "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/day_clear.png",
                "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/night_half_moon_partial_cloud.png",
                "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/night_half_moon_snow.png",
                "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/sleet.png",
                "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/mist.png",
                "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/night_full_moon_clear.png",
                "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/night_half_moon_rain.png",
                "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/rain_thunder.png",
                "https://www.dovora.com/resources/weather-icons/showcase/modern_showcase/fog.png"};
        for(int i=0;i<names.length;i++) {
            ITEMS.add(new NewsListItem(names[i], names[i], urls[i]));
        }
    }

    public NewsListItem(String head, String body, String url) {
        this.head = head;
        this.body = body;
        this.url = url;
    }

    public String getHead() {
        return head;
    }

    public String getBody() {
        return body;
    }

    public String getUrl() {
        return url;
    }
}
