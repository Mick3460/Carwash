package com.company;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class Database {

    final int ECO = 50;
    final int STANDARD = 90;
    final int DELUXE = 120;
    static Scanner scan = new Scanner(System.in);

    private ArrayList<Person> database = new ArrayList<>();

    public Database() {
    }

    // Tilføj person med navn og nummer (Constructor)
    public void addCostumerID(String costumerID, int balance) {
        Person person = new Person(costumerID, balance);
        this.database.add(person);
    }

    //Henter vores balance hvis login matcher en i databasen
    public int customerBalance(String inputCustomer) {
        for (Person person : database) {
            if (inputCustomer.compareTo(person.getCostumerID()) == 0) {
                return person.getBalance();
            }
        }
        return -1;
    }

            //TILLÆGGER penge til konto
    public String changeBalance(String inputCustomer, int addition) {
        for (Person person : database) {
            if (inputCustomer.compareTo(person.getCostumerID()) == 0) {
                if (person.getBalance() + addition >1000) {
                    System.out.println("Du må maks have 1000kr stående!");
                } else {
                    int newBalance = (person.getBalance() + addition);
                    person.setBalance(newBalance);
                    }
                return "Fejl";
            }
        }
        return "Fejl";
    }

    public void balanceFillUp(String inputCustomer) {
        for (Person person : database) {
            if (inputCustomer.compareTo(person.getCostumerID()) == 0) {
                person.setBalance(1000);
                System.out.println("Din konto er nu fyldt!");
            return;}
        }
        }

    public static int indtastTal() {

        int tal = 0;
        while (!scan.hasNextInt()) {
            System.out.println("Du skal skrive et tal!");
            System.out.println("Indtast menu nummer: ");
            scan.nextLine();
        }
        tal = scan.nextInt();
        return tal;
    }

    public void withdrawBalance(String inputCustomer, int deduction) {

        if (deduction == ECO || deduction == STANDARD){
            deduction = applyDiscount(deduction);
        }

        System.out.println("Ønsker de en kvittering?");
        System.out.println("1 for Ja         2 for Nej");
        int balanceBeforeWithdrawal = 0;
        int yesOrNo = indtastTal();

            for (Person person : database) {
                if (inputCustomer.compareTo(person.getCostumerID()) == 0) {
                    if (yesOrNo == 1) {
                        balanceBeforeWithdrawal = person.getBalance();
                    }
                }
                if (inputCustomer.compareTo(person.getCostumerID()) == 0) {
                    int newBalance = (person.getBalance() - deduction);
                    person.setBalance(newBalance);
                }

                if (yesOrNo == 1) {
                    System.out.println("Dit kontobalance var: " + balanceBeforeWithdrawal);
                    System.out.println("Efter dit køb, så er den: " + person.getBalance());
                }
            }


    }//withDrawBalance slut


        //Her sætter vi en dato og henter dens værdi.
        //Vi hardcoder det til mandag for at spare tid.
    public int DaysOfWeek() {
        LocalDate localDate
                = LocalDate.of(2020,
                Month.OCTOBER, 12);
        // Find the day from the local date
        DayOfWeek dayOfWeek = DayOfWeek.from(localDate);

        int mondayHardcoded = dayOfWeek.getValue();
        return mondayHardcoded;
    }

    public String isItMonday() {
        LocalDate localDate
                = LocalDate.of(2020,
                Month.OCTOBER, 12);

        // Find the day from the local date
        DayOfWeek dayOfWeek
                = DayOfWeek.from(localDate);


        String currentDay = dayOfWeek.name();

        return currentDay;
    }

    public int applyDiscount(int price) {
        if (LocalTime.now().isBefore(LocalTime.of(14,00,00)) && DaysOfWeek() == 1) {
            System.out.println("Du er inde for vores Earlybird tidspunkter, så du har fået 20% Rabat! :)");
            return (int) Math.round(price - (price * 0.2));
        }
        return price;
    }



}