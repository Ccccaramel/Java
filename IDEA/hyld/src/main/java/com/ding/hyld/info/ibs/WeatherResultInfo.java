package com.ding.hyld.info.ibs;

import java.util.List;

public class WeatherResultInfo {
    private WeatherResultLocationInfo location;
    private WeatherResultNowInfo now;
    private List<WeatherResultForecastsInfo> forecasts;

    public WeatherResultLocationInfo getLocation() {
        return location;
    }

    public void setLocation(WeatherResultLocationInfo location) {
        this.location = location;
    }

    public WeatherResultNowInfo getNow() {
        return now;
    }

    public void setNow(WeatherResultNowInfo now) {
        this.now = now;
    }

    public List<WeatherResultForecastsInfo> getForecasts() {
        return forecasts;
    }

    public void setForecasts(List<WeatherResultForecastsInfo> forecasts) {
        this.forecasts = forecasts;
    }
}
