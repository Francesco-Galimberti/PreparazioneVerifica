/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eseLotto;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franc
 */
public class ThGenera extends Thread {

    private DatiCondivisi ptrDati;

    public ThGenera(DatiCondivisi dati) {
        this.ptrDati = dati;
    }

    @Override
    public void run() {
        Random rand = new Random();
        try {
            int nE = ptrDati.getnRuote();
            int[] vett;

            //numero estrazioni
            for (int i = 0; i < nE; i++) {

                vett = new int[5];

                //generazione
                for (int j = 0; j < 5; j++) {
                    vett[j] = rand.nextInt(50) + 1;
                }
                ptrDati.addVettori(vett);

                ptrDati.signalSemRicercaN1();
                ptrDati.signalSemRicercaN2();

                Thread.sleep(100);
            }
            
            ptrDati.signalFinish();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ThGenera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
