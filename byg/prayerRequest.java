package michaelkim.byg;

/**
 * Created by Michael Kim on 10/30/2017.
 */

public class prayerRequest {

    String time, date, name, grade, prayerRequest;

    public prayerRequest (){

    }

    public prayerRequest (String t, String d, String n, String g, String pR){
        this.time = t;
        this.date = d;
        this.name = n;
        this.grade = g;
        this.prayerRequest = pR;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getPrayerRequest() {
        return prayerRequest;
    }

    public void setPrayerRequest(String prayerRequest) {
        this.prayerRequest = prayerRequest;
    }
}