/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es1;

import java.util.ArrayList;

/**
 *
 * @author galimberti_francesco
 */
public class ThVisualizza extends Thread {

    private DatiCondivisi ptrDati;
    private boolean finito;

    public ThVisualizza(DatiCondivisi dati) {
        ptrDati = dati;
        finito = true;
    }

    public void termina() {
        finito = false;
    }

    @Override
    public void run() {
        while (finito) {
            System.out.println("-------------------------------------------");

            System.out.println("Spazi Inseriti: " + ptrDati.getNumSpaziInseriti());
            System.out.println("Spazi Letti: " + ptrDati.getNumSpaziLetti());
            System.out.println("Punti Inseriti: " + ptrDati.getNumPuntiInseriti());
            System.out.println("Spazi Letti: " + ptrDati.getNumSpaziLetti());

            System.out.print("Buffer --> ");
            for (int i = 0; i < ptrDati.getBuffer().size(); i++) {
                System.out.print(ptrDati.getBuffer(i));
            }

            System.out.println("\n" + "-------------------------------------------");
        }
    }
}
