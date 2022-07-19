package almusbahi.riyad.monkeypoxtracker.models;

public class LocationStats {

    private String country;
    private String Date;
    private Integer cases;
    private Integer cumulativeCases;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public Integer getCases() {
        return cases;
    }

    public void setCases(Integer cases) {
        this.cases = cases;
    }

    public Integer getCumulativeCases() {
        return cumulativeCases;
    }

    public void setCumulativeCases(Integer cumulativeCases) {
        this.cumulativeCases = cumulativeCases;
    }

    @Override
    public String toString() {
        return "LocationStats{" +
                "country='" + country + '\'' +
//                ", Date='" + Date + '\'' +
                ", cases=" + cases +
                ", cumulativeCases=" + cumulativeCases +
                '}';
    }
}
