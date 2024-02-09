package AaDS_Lab1.QuarterlySales;

import java.util.Scanner;

class QuarterlySales {

    private int numOfSales;
    private int[] revenues;
    private int quarterNo;

    public QuarterlySales(int numOfSales, int[] revenues, int quarterNo) {
        this.numOfSales = numOfSales;
        this.revenues = revenues;
        this.quarterNo = quarterNo;
    }

    @Override
    public String toString() {
        StringBuilder sales = new StringBuilder();

        for (int sale : revenues)
            sales.append(sale).append("\n");

        return sales.toString();
    }

    public int quarterlySum() {
        int sum = 0;
        for (int revenue : revenues)
            sum += revenue;

        return sum;
    }

    public void setRevenues(int[] revenues) {
        this.revenues = revenues;
    }

    public void setQuarterNo(int quarterNo) {
        this.quarterNo = quarterNo;
    }
}

class SalesPerson {

    private String name;
    private QuarterlySales[] quarters;

    public SalesPerson(String name, QuarterlySales[] quarters) {
        this.name = name;
        this.quarters = quarters;
    }

    public String getName() {
        return name;
    }

    public int totalSales() {
        int sum = 0;
        for (QuarterlySales sale : quarters)
            sum += sale.quarterlySum();

        return sum;
    }

    @Override
    public String toString() {
        StringBuilder person = new StringBuilder();
        person.append(getName()).append("   ");
        int totalSum = 0;

        for (QuarterlySales quarter : quarters) {
            person.append(quarter.quarterlySum()).append("   ");
            totalSum += quarter.quarterlySum();
        }
        person.append(totalSum).append("\n");

        return person.toString();
    }

    public void setName(String name) {
        this.name = name;
    }
}


public class Main {

    public static SalesPerson salesChampion(SalesPerson[] arr) {
        int max = 0;
        SalesPerson champion = arr[0];

        for (SalesPerson person : arr) {
            if (max < person.totalSales()) {
                max = person.totalSales();
                champion = person;
            }
        }
        return champion;
    }

    public static void table(SalesPerson[] arr) {
        System.out.println("SP   1   2   3   4   Total");
        StringBuilder allSales = new StringBuilder();

        for (SalesPerson person : arr) {
            allSales.append(person);
        }

        System.out.println(allSales);
    }

    public static void main(String[] args) {

        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        input.nextLine();
        SalesPerson[] arr = new SalesPerson[n];

        for (int i = 0; i < n; i++) {
            String name = input.nextLine();
            QuarterlySales[] quarterlySales = new QuarterlySales[4];

            for (int j = 0; j < 4; j++) {
                int numSales = input.nextInt();
                input.nextLine();

                int[] sales = new int[numSales];
                for (int k = 0; k < numSales; k++) {
                    sales[k] = input.nextInt();
                }
                input.nextLine();
                quarterlySales[j] = new QuarterlySales(numSales, sales, j + 1);
            }
            arr[i] = new SalesPerson(name, quarterlySales);
        }

        table(arr);
        System.out.println("SALES CHAMPION: " + salesChampion(arr).getName());
    }
}
