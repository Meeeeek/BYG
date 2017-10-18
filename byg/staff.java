package michaelkim.byg;

/**
 * Created by Michael Kim on 8/25/2017.
 */

public class staff {

    String name, email, phone;

    public staff() {

    }

    public staff(String n, String e, String p){
        this.name = n;
        this.email = e;
        this.phone = p;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
