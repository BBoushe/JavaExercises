package AP_Lab1.BankTester;

import java.util.Objects;

public class FlatPercentProvisionTransaction extends Transaction {
    private int centsPerDollar;

    FlatPercentProvisionTransaction (long fromId, long toId, String amount, int centsPerDolar){
        super(fromId, toId, "FlatPercent", amount);
        this.centsPerDollar = centsPerDolar;
    }

    public int getCentsPerDollar() {
        return centsPerDollar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlatPercentProvisionTransaction that = (FlatPercentProvisionTransaction) o;
        return centsPerDollar == that.centsPerDollar;
    }

    @Override
    public long getFromId() {
        return super.getFromId();
    }

    @Override
    public long getToId() {
        return super.getToId();
    }

    @Override
    public String getAmount() {
        return super.getAmount();
    }

    @Override
    public int hashCode() {
        return Objects.hash(centsPerDollar);
    }
}
