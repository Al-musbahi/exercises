package almusbahi.riyad.monkeypoxtracker.controllers;

import almusbahi.riyad.monkeypoxtracker.models.GlobalStats;
import almusbahi.riyad.monkeypoxtracker.models.LocationStats;
import almusbahi.riyad.monkeypoxtracker.services.MonkeypoxDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class StatsManager {

    @Autowired
    MonkeypoxDataService monkeypoxDataService;

    public String getTotalReportedCases(){
        List<GlobalStats> globalStats = monkeypoxDataService.getGlobalStats();
        return globalStats.get(globalStats.size() -1 ).getCumulativeCases();
    }

    public String getTodayscases(){
        List<GlobalStats> globalStats = monkeypoxDataService.getGlobalStats();
        return globalStats.get(globalStats.size() - 1 ).getCases();
    }






}
