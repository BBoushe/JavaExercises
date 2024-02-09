package AP_Lab2.ContactsTester;

public class PhoneContact extends Contact {
    private String phone;
    private Operator operator;

    public PhoneContact(String date, String phone) {
        super(date);
        this.phone = phone;

        char n = this.phone.charAt(2);
        if(n == '0' || n == '1' || n == '2') {
            operator = Operator.TMOBILE;
        } else if(n == '5' || n == '6') {
            operator = Operator.ONE;
        } else {
            operator = Operator.VIP;
        }
    }

    public String getPhone() {
        return phone;
    }

    public String getType(){
        return "Phone";
    }

    public String toString(){
        return phone;
    }

    public Operator getOperator(){
        return operator;
    }
}
