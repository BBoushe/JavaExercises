package AP_Lab1.BankTester;

import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class Account {
    private String name;
    private long id;
    private String balance;

    public Account(String name, String balance) {
        this.name = name;
        Random random = new Random();
        id = random.nextLong();
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return this.name;
    }

    public String getBalance() {
        return balance;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return String.format(Locale.US, "Name: %s\nBalance: %.2f$\n", this.getName(), Double.parseDouble(this.getBalance().replaceAll("[$]", "")));
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && Objects.equals(name, account.name) && Objects.equals(balance, account.balance);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
