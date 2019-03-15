/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eseStatistiche;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galimberti_francesco
 */
public class ThVisualizza extends Thread {

    private DatiCondivisi ptrDati;
    private boolean finito;

    public ThVisualizza(DatiCondivisi dati) {
        ptrDati = dati;
        finito = false;
    }

    public void termina() {
        finito = true;
    }

    @Override
    public void run() {
        while (!finito) {
            try {
                ptrDati.waitSemVisualizza1();
                if(Thread.currentThread().isInterrupted()){
                    break;
                }else{                   
                    continua();
                }
                ptrDati.signalSemVisualizza2();
                
            } catch (InterruptedException ex) {
            }
        }  
    }

    private void continua() {
        System.out.println("-------------------------------------------");
        System.out.println("Spazi Inseriti: " + ptrDati.getNumSpaziInseriti());
        System.out.println("Spazi Letti: " + ptrDati.getNumSpaziLetti());
        System.out.println("Punti Inseriti: " + ptrDati.getNumPuntiInseriti());
        System.out.println("Punti Letti: " + ptrDati.getNumPuntiLetti());
        System.out.print("Buffer --> ");
        ArrayList<Carattere> b = ptrDati.getBuffer();
        for (int i = 0; i < b.size(); i++) {
            System.out.print(b.get(i).getCarattere());
        }
        System.out.println("\n" + "-------------------------------------------");
    }
}
