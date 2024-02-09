package AP_Lab1.BankTester;

import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;

public class Bank {
    private String bankName;
    //    private double totalTransactionAmount;
//    private double totalProvisionAmount;
    private Account[] accounts;

    private double totalTransfer = 0;
    private double totalProvision = 0;

    public Bank(String bankName, Account[] accounts) {
        this.bankName = bankName;
        Account[] copyOfAccs = Arrays.copyOf(accounts, accounts.length); // not sure why I'm doing this other than the instructions
        this.accounts = copyOfAccs;
    }

    public boolean makeTransaction(Transaction t) {
        int sender = getAccount(t.getFromId());
        int receiver = getAccount(t.getToId());

        // parsing the string because it's of format X.YY$ -> this will be used everywhere where necessary
        double senderBalance = Double.parseDouble(accounts[sender].getBalance().replace("$", ""));
        double receiverBalance = Double.parseDouble(accounts[receiver].getBalance().replace("$", ""));
        double amount = Double.parseDouble(t.getAmount().replaceAll("[^\\d.]", ""));
        double fee = 0;

        if (t instanceof FlatAmountProvisionTransaction) {
            String flatAmountAsString = ((FlatAmountProvisionTransaction) t).getFlatAmount().replaceAll("[^\\d.]", "");
            fee = Double.parseDouble(flatAmountAsString);
        } else if (t instanceof FlatPercentProvisionTransaction) {
            fee = (int) amount * (((FlatPercentProvisionTransaction) t).getCentsPerDollar() / 100.00);
        }

        if ((sender != -1) || (receiver != -1)) {
            if ((amount + fee) <= senderBalance) {
                // if it's flat amount then just add the flat amount to the transaction and subtract it from the fromId account balance
                if (t instanceof FlatAmountProvisionTransaction) {
                    totalTransfer += amount;
                    totalProvision += fee;

                    if (sender == receiver) {
                        accounts[sender].setBalance(String.valueOf(senderBalance - fee)); // if you are sending money to yourself then you only get charged the fee
                    } else {
                        accounts[sender].setBalance(String.valueOf(senderBalance - (amount + fee)));
                        accounts[receiver].setBalance(String.valueOf(receiverBalance + amount));
                    }
                    return true;
                }
                // if it's percent amount then get the final value from the amount*percent and then add it to the amount, after which subtract
                else if (t instanceof FlatPercentProvisionTransaction) {
                    totalTransfer += amount;
                    totalProvision += fee;

                    if (sender == receiver) {
                        accounts[sender].setBalance(String.valueOf(senderBalance - fee)); // if you are sending money to yourself then you only get charged the fee
                    } else {
                        accounts[sender].setBalance(String.valueOf(senderBalance - (amount + fee)));
                        accounts[receiver].setBalance(String.valueOf(receiverBalance + amount));
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public String totalProvision() {
        return String.format(Locale.US, "%.2f$", totalProvision);
    }

    public String totalTransfers() {
        return String.format(Locale.US, "%.2f$", totalTransfer);
    }

    public int getAccount(long id) {
        int i = 0;
        for (Account account : accounts) {
            if (account.getId() == id)
                return i;
            else i++;
        }
        i = -1;
        return i;
    }

    @Override
    public String toString() {
        StringBuilder contents = new StringBuilder("Name: ");
        contents.append(this.bankName).append("\n").append("\n");
        int i = 0;

        for (Account account : accounts) {
            contents.append(String.valueOf(i++)).append(" ").append(account.toString());
        }

        return contents.toString();
    }

    public Account[] getAccounts() {
        return accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Double.compare(totalTransfer, bank.totalTransfer) == 0 && Double.compare(totalProvision, bank.totalProvision) == 0 && Objects.equals(bankName, bank.bankName) && Arrays.equals(accounts, bank.accounts);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(bankName, totalTransfer, totalProvision);
        result = 31 * result + Arrays.hashCode(accounts);
        return result;
    }
}
