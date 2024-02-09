package AP_Lab2.ContactsTester;


public class Contact {
    private String date;

    Contact() {
        date = "1980-01-01";
    }

    public Contact(String date) {
        this.date = date;
    }

    public String getDate() {
        return this.date;
    }

    public int getDay() {
        return Integer.parseInt(date.split("-")[2]);
    }

    public int getMonth() {
        return Integer.parseInt(date.split("-")[1]);
    }

    public int getYear() {
        return Integer.parseInt(date.split("-")[0]);
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isNewerThan(Contact c) {
        int year = getYear(), month = getMonth(), day = getDay();

        if(year < c.getYear())  return false;
        if(year == c.getYear() && month < c.getMonth()) return false;
        if(year == c.getYear() && month == c.getMonth() && day < c.getDay()) return false;
        return true;
    }

    public  String getType(){
        return "None";
    }
}
