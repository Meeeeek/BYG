package michaelkim.byg;

/**
 * Created by Michael Kim on 11/16/2017.
 */

public class announcement {

    String announcement, person;

    public announcement() {
    }

    public announcement(String a, String p) {
        this.announcement = a;
        this.person = p;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}
