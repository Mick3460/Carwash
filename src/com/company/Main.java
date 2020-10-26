package com.company;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    static Scanner scan = new Scanner(System.in);

    public static void main (String[]args) {
        boolean mainLoop = true; //Til vores welcome-loop

        //Opretter en database og tilføjer vores ene kunde.
        Database database = new Database();
        database.addCostumerID("123", 222);

        //Opretter en database for gamle salg, som admin kan se (sælger)
        StoreBase storebase = new StoreBase();
        storebase.addSaleManual("d. 07-11-2011, klokken 9:39", storebase.ECOPRICE);
        storebase.addSaleManual("d. 20-04.2020, klokken 16:20", storebase.ECOPRICE);

    while (mainLoop == true) {
        //^^ Det her er vores main loop, som kun skal stoppe ved køb eller logaf

        //Variable
        String currentDay = database.isItMonday();
        final int ECO = 50;
        final int STANDARD = 90;
        final int DELUXE = 120;
        int tal;
        boolean overView = false; //Starter som false, login for true
        boolean accountDetails = false; // Menu 2, skal aktiveres i switch
        boolean chooseWash = false;
        boolean beginWashing = false;
        boolean adminStorage = false;


        //Beder om ID til LOGIN.
        System.out.println("Log ind med dit ID nummer");
        String customer123 = scan.nextLine();
        if (customer123.equals("123")) {
            overView = true;
        }else System.out.println("Det var forkert, prøv igen");


        // Overview (Hovedmenu)
        while (overView == true) {

         showMenu();

         //Variable
         tal = indtastTal(); //kalder en metode som beder om et input til vores switch-case.
         scan.nextLine(); //Scannerbuggen kommer vi udenom her.

         //Handlinger i forhold til valg
         switch (tal) {
             case 1: //Smid os over i accountDetails
                 accountDetails = true;
                 overView = false;
                 break;

             case 2: //Smid os over i Købe-menuen
                 overView = false;
                 chooseWash = true;
                 break;
             case 3: //Smid os over i Admin menuen
                 adminStorage = true;
                 overView = false;
                 break;
             default:
                //Luk programmet
                System.exit(0);
         }


            // AccountManagement
         while (accountDetails == true){

          showAccount();
          tal = indtastTal(); //kalder en metode som beder om et input til vores switch-case.
          scan.nextLine(); //Scannerbuggen kommer vi udenom her.

            switch (tal) {
                case 1: //Giv balance til kunde
                    System.out.println("Din nuværende balance er på: "+ database.customerBalance(customer123));

                    break;

                case 2: //Opfyldning af konto.
                    System.out.println("Angiv hvor mange penge du vil indsætte på dit kort? ");
                    System.out.println("Du må maks have 1000kr på din konto.");
                    System.out.println("\nTast 1 for at vælge beløb.");
                    System.out.println("Tast 2 for at fylde den op til 1000kr.");

                    int userChoice = indtastTal();

                    //Ved ikke om nested switch er dårlig stil, but here we go.
                    //Giver to forskellige muligheder for opfyldning
                    switch (userChoice) {
                        case 1:
                            System.out.println("Angiv beløbet du vil tilføje:");
                            int addition = indtastTal();
                            database.changeBalance(customer123, addition);
                            break;
                        case 2:
                            database.balanceFillUp(customer123);
                            break;
                        default:
                            System.exit(0);
                    }

                    System.out.println("Beløbet på kontoen er nu: " + database.customerBalance(customer123));

                    break;
                case 3:
                    //displayHistory(customer123);  Fik vi ikke ind i tide.

                case 9: //Går til Hovedmenu
                    accountDetails = false;
                    overView = true;
                    break;


                default:
                    overView = true;

            }
        } //accountDetails


         // CHOOSE CARWASH OPTION
         while (chooseWash == true) { //Det her er vores loop og switch som afgør hvilken vask vi sælger.

             // VALIDERING AF KONTOBELØB
             int belob = database.customerBalance(customer123);
                if (belob >= 200)
                {showWashes();

                 tal = indtastTal(); //kalder en metode som beder om et input til vores switch-case.
                 scan.nextLine(); //Scannerbuggen kommer vi udenom her.

                 switch (tal) {
                     case 1:
                         System.out.println("Du har valgt Eco vasken til 50kr!");
                         database.withdrawBalance(customer123, ECO);
                         storebase.storeSale(currentDay, ECO);
                         chooseWash = false;
                         beginWashing = true;
                         overView = false;
                         break;
                     case 2:
                         System.out.println("Du har valgt Standard vasken til 90kr!");
                         database.withdrawBalance(customer123, STANDARD);
                         storebase.storeSale(currentDay, STANDARD);
                         chooseWash = false;
                         beginWashing = true;
                         overView = false;
                         break;
                     case 3:
                         System.out.println("Du har valgt Deluxe vasken til 120kr!");
                         database.withdrawBalance(customer123, DELUXE);
                         storebase.storeSale(currentDay, DELUXE);
                         chooseWash = false;
                         beginWashing = true;
                         overView = false;
                         break;
                     case 9: //Gå til Hovedmenu
                         chooseWash = false;
                         overView = true;
                         break;
                 }//switch slut
             } //if slutning
             else {System.out.println("Du har ikke nok penge på kontoen!");
                 chooseWash = false;
                 overView = true;
             }

         } //chooseWash loop


            // HER HAR VI ADMIN MENUEN OG SWITCHCASE
         while (adminStorage == true) {
            showRecords();
            tal = indtastTal(); //kalder en metode som beder om et input til vores switch-case.
            scan.nextLine(); //Scannerbuggen kommer vi udenom her.

                switch (tal) {
                    case 1:
                        storebase.printAll();
                        break;
                    case 9:
                        adminStorage = false;
                        overView = true;
                        break;

                }

        }//adminStorage Loop

            //BARE PRINT NOGET... VASKER LIDT
            while (beginWashing == true) {
                System.out.println("\n   WASHYWASHY \n\n");
                beginWashing = false;
            }

    }//overView Loop

        System.out.println("Tak for besøget, vi håber du får en god dag :)\n \n \n \n \n");
    } //mainloop

    } //main

    //Skriv metoder under her


    public static void showMenu () {
        System.out.println("\nHovedmenu, 0 afslutter");
        System.out.println(LocalTime.now());
        System.out.println("1 Se konto detaljer");
        System.out.println("2 Jeg vil gerne vaske min bil!");
        System.out.println("3 Hvis du er en admin!");
        System.out.println("Indtast Menu nummer\n");
    }

    public static void showAccount() {
        System.out.println("\nDette er din konto informationer. ");
        //System.out.println("\nKlik på 0 for at gå tilbage");
        System.out.println("1 Se din kontobalance");
        System.out.println("2 Tilbøj penge til din konto");
        System.out.println("9 Gå tilbage til Hovedmenuen.\n");
    }

    public static void showWashes() {
        System.out.println("\nDette er dine vaske muligheder:");
        System.out.println("1 Eco vask: 50kr");
        System.out.println("2 Standard vask: 90kr");
        System.out.println("3 Deluxe vask: 120kr\n");
        System.out.println("VI LAVER EN ÆNDRING OG KOMMENTAR TIL GITHUB");
    }

    public static void showRecords() {
        System.out.println("\nDette er admin mulighederne:");
        System.out.println("1 For at se salg og tidspunkterne for salget.");
        System.out.println("9 For at komme til Hovedmenuen.\n");
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

}

