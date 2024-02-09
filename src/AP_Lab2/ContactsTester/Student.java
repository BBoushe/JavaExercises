package AP_Lab2.ContactsTester;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student {

    private Contact[] contacts;
    private String firstName;
    private String lastName;
    private String city;
    private int age;
    private long index;

    private int numOfEmail = 0;
    private int numOfPhone = 0;

    public Student(String firstName, String lastName, String city, int age, long index) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.age = age;
        this.index = index;
        this.contacts = new Contact[0];
    }

    public String getCity() {
        return city;
    }

    public long getIndex() {
        return index;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getNumOfEmail() {
        return numOfEmail;
    }

    public int getNumOfPhone() {
        return numOfPhone;
    }

    public String getFullName(){
        return this.firstName + " " + this.lastName;
    }

    public int getNumOfContacts(){
        return getNumOfEmail() + getNumOfPhone();
    }


    public void addEmailContact(String date, String email) {
        Contact [] newContacts = new Contact[contacts.length + 1];
        for(int i = 0; i < contacts.length; i++) newContacts[i] = contacts[i];
        newContacts[newContacts.length - 1] = new EmailContact(date, email);
        contacts = Arrays.copyOf(newContacts, newContacts.length);

        numOfEmail++;
    }

    public void addPhoneContact(String date, String phone) {
        Contact [] newContacts = new Contact[contacts.length + 1];
        for(int i = 0; i < contacts.length; i++) newContacts[i] = contacts[i];
        newContacts[newContacts.length - 1] = new PhoneContact(date, phone);
        contacts = Arrays.copyOf(newContacts, newContacts.length);
        numOfPhone++;
    }

    public Contact[] getEmailContacts(){

        Contact[] emailContact = new Contact[numOfEmail];

        for (int j = 0, x =0; j < contacts.length; j++) {
            if(this.contacts[j].getType().equals("Email")){
                emailContact[x++] = this.contacts[j];
            }
        }

        return emailContact;
    }

    public Contact[] getPhoneContacts(){

        Contact[] phoneContacts = new Contact[numOfPhone];

        for (int j = 0, x =0; j < contacts.length; j++) {
            if(contacts[j].getType().equals("Phone")){
                phoneContacts[x++] = contacts[j];
            }
        }

        return phoneContacts;
    }

    public Contact getLatestContact() throws ParseException {
        int idx = -1;

        for (int i = 0; i < contacts.length; i++) {
            if(idx == -1 || contacts[i].isNewerThan(contacts[idx])){
                idx = i;
            }
        }

        return contacts[idx];
    }

    public String toString(){
        StringBuilder jsonStudent = new StringBuilder();


        jsonStudent.append("{\"ime\":\"").append(getFirstName()).append("\", \"prezime\":\"").append(getLastName())
                .append("\", \"vozrast\":").append(age).append(", \"grad\":\"").append(getCity()).append("\", \"indeks\":")
                .append(getIndex()).append(", \"telefonskiKontakti\":[");

        Contact[] phoneContacts = getPhoneContacts();
        Contact[] emailContacts = getEmailContacts();

        for(int i = 0; i < numOfPhone; i++) {
            jsonStudent.append("\"").append(phoneContacts[i]).append("\"");
            if(i < phoneContacts.length - 1) {
                jsonStudent.append(", ");
            }
        }

        jsonStudent.append("], \"emailKontakti\":[");

        for (int i = 0; i < numOfEmail; i++) {
            jsonStudent.append("\"").append(emailContacts[i]).append("\"");
            if(i < numOfEmail - 1){
                jsonStudent.append(", ");
            }
        }

        jsonStudent.append("]}");

        return jsonStudent.toString();
    }
}
