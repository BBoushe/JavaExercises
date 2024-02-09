package AP_Lab2.ContactsTester;

public class EmailContact extends Contact {
    private String email;

    public EmailContact(String date, String email) {
        super(date);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getType(){
        return "Email";
    }
    @Override
    public String toString(){
        return email;
    }
}
