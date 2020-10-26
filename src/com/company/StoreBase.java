package com.company;
import java.util.ArrayList;

    public class StoreBase {
        ArrayList<PrisOgTid> storeBase = new ArrayList<>();
        int ECOsales = 0;
        int STANDARDsales = 0;
        int DELUXEsales = 0;
        final int ECOPRICE = 50;
        final int STANDARDPRICE = 90;
        final int DELUXEPRICE = 120;

        public StoreBase() {

        }

        // Tilføj et salg til databasen med tid og pris.
        public void addSaleManual(String date, int pris) {
            PrisOgTid salg = new PrisOgTid(date, pris);
            this.storeBase.add(salg);
        }

        public void storeSale(String tid, int pris) {
            if (pris == ECOPRICE) {
                ECOsales++;
            } else if (pris == STANDARDPRICE) {
                STANDARDsales++;
            } else DELUXEsales++;


            addSaleManual(tid, pris);
            System.out.println("ECO sales: " + ECOsales + "  STANDARD sales: " + STANDARDsales + "  DELUXE sales: " + DELUXEsales);
        }

        public void printAll() {
            for (PrisOgTid salg : storeBase) {
                System.out.println(salg);
            }

        }
    }



