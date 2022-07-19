package almusbahi.riyad.monkeypoxtracker.controllers;

import almusbahi.riyad.monkeypoxtracker.models.LocationStats;
import almusbahi.riyad.monkeypoxtracker.services.MonkeypoxDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Controller
public class LocationStatsManager {

    @Autowired
    MonkeypoxDataService monkeypoxDataService;

    public List<LocationStats> getAllLocationStats(){
        List<String> allCountries = getAllCountries();
        List<LocationStats> lastDayAllLocationStats = new ArrayList<>();
        for (String country: allCountries) {
            List<LocationStats> countryStats = getCountryStats(country);
            LocationStats lastDayStats = getLastDayStats(countryStats);
            lastDayAllLocationStats.add(lastDayStats);
        }return lastDayAllLocationStats;
    }




    private List<String> getAllCountries() {
        List<String> allCountries = new ArrayList<>();
        String[] locales = Locale.getISOCountries();
        for (String countryCode : locales) {
            Locale obj = new Locale("", countryCode);
            allCountries.add(obj.getDisplayCountry());
        }return allCountries;
    }

    private LocationStats getLastDayStats(List<LocationStats> countryStats) {
        return countryStats.get(countryStats.size() - 1 );
    }

    private List<LocationStats> getCountryStats(String country) {
        List<LocationStats> countryStats = new ArrayList<>();
        List<LocationStats> locationStats = monkeypoxDataService.getLocationStats();
        for (LocationStats stat: locationStats) {
            if (stat.getCountry() == country){
                countryStats.add(stat);
            }
            stat.setCountry(country);
            stat.setCases(0);
            stat.setCumulativeCases(0);
        }
        return countryStats;
    }
}