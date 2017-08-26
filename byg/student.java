package michaelkim.byg;

/**
 * Created by Michael Kim on 8/25/2017.
 */

public class student {

    String name, grade, birthday, phone, address, city, state, zip;

    public student() {

    }

    public student(String n, String g, String b, String p, String a, String c, String s, String z){
        this.name = n;
        this.grade = g;
        this.birthday = b;
        this.phone = p;
        this.address = a;
        this.city = c;
        this.state = s;
        this.zip = z;
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

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
