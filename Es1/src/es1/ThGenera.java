/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es1;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galimberti_francesco
 */
public class ThGenera extends Thread {

    private DatiCondivisi ptrDati;

    public ThGenera(DatiCondivisi dati) {
        ptrDati = dati;
    }

    @Override
    public void run() {
        try {
            Random rand = new Random();
            int n;
            char r = ' ';

            int ii = ptrDati.getNumeroCaratteri();
            for (int i = 0; i < ii; i++) {

                //generazione casuale da 0 a 27
                n = rand.nextInt(28);

                switch (n) {
                    case 26:
                        r = '.';
                        ptrDati.addBuffer(r);
                        ptrDati.incNumPuntiInseriti();
                        break;

                    case 27:
                        r = ' ';
                        ptrDati.addBuffer(r);
                        ptrDati.incNumSpaziInseriti();
                        break;

                    default:
                        r = (char) (n + 'a');
                        ptrDati.addBuffer(r);
                        break;
                }
                

                ptrDati.signalSemVisualizza();
                ptrDati.signalSemRicercaPunto();
                ptrDati.signalSemRicercaSpazio();

                Thread.sleep(100);
            }     

        } catch (InterruptedException ex) {
            Logger.getLogger(ThGenera.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ptrDati.signalFinish();
    }
}
