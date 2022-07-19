package almusbahi.riyad.monkeypoxtracker.controllers;


import almusbahi.riyad.monkeypoxtracker.services.MonkeypoxDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Home {

    @Autowired
    MonkeypoxDataService monkeypoxDataService;
    @Autowired
    StatsManager statsManager;

    @Autowired
    LocationStatsManager locationStatsManager;


    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("totalReportedCases", statsManager.getTotalReportedCases());
        model.addAttribute("locationStats", monkeypoxDataService.getLocationStats());
        model.addAttribute("todaysCases", statsManager.getTodayscases());
        model.addAttribute("locationStats", locationStatsManager.getAllLocationStats());

        return "home";
    }
}
