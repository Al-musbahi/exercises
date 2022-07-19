package almusbahi.riyad.monkeypoxtracker.models;

public class GlobalStats {

    private String date;
    private String cases;
    private String cumulativeCases;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getCumulativeCases() {
        return cumulativeCases;
    }

    public void setCumulativeCases(String cumulativeCases) {
        this.cumulativeCases = cumulativeCases;
    }

    @Override
    public String toString() {
        return "DateStats{" +
                "date='" + date + '\'' +
                ", cases='" + cases + '\'' +
                ", cumulativeCases='" + cumulativeCases + '\'' +
                '}';
    }
}
