package almusbahi.riyad.monkeypoxtracker.services;

import almusbahi.riyad.monkeypoxtracker.models.GlobalStats;
import almusbahi.riyad.monkeypoxtracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class MonkeypoxDataService {

    private static String GLOBAL_DATA_URL = "https://raw.githubusercontent.com/globaldothealth/monkeypox/main/timeseries-confirmed.csv";
    private static String LOCATION_DATA_URL = "https://raw.githubusercontent.com/globaldothealth/monkeypox/main/timeseries-country-confirmed.csv";

    private List<GlobalStats> globalStats = new ArrayList<>();
    private List<LocationStats> locationStats = new ArrayList<>();

    public List<GlobalStats> getGlobalStats() {
        return globalStats;
    }

    public List<LocationStats> getLocationStats() {
        return locationStats;
    }

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusGlobalData () throws IOException, InterruptedException {
        List<GlobalStats> newGlobalStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(GLOBAL_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());


        StringReader csvReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
        for (CSVRecord record : records) {
            GlobalStats globalStats = new GlobalStats();

            globalStats.setDate(record.get("Date"));
            globalStats.setCases(record.get("Cases"));
            globalStats.setCumulativeCases(record.get("Cumulative_cases"));

//            System.out.println(globalStats);
            newGlobalStats.add(globalStats);

        }
        this.globalStats = newGlobalStats;
    }


    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchVirusLocationlData () throws IOException, InterruptedException {
        List<LocationStats> newLocationStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(LOCATION_DATA_URL))
                .build();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvReader = new StringReader(httpResponse.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats();

            locationStat.setCountry(record.get("Country"));
            locationStat.setDate(record.get("Date"));
            locationStat.setCases(Integer.valueOf(record.get("Cases")));
            locationStat.setCumulativeCases(Integer.valueOf(record.get("Cumulative_cases")));

//            System.out.println(locationStat);
            newLocationStats.add(locationStat);

        }
        this.locationStats = newLocationStats;
    }



}
