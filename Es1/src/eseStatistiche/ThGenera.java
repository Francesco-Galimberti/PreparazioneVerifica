/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eseStatistiche;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galimberti_francesco
 */
public class ThGenera extends Thread {

    private final DatiCondivisi ptrDati;
    private final int time;

    public ThGenera(DatiCondivisi dati, int nC) {
        ptrDati = dati;
        time = nC;
    }

    @Override
    public void run() {
        Random rand = new Random();
        int n;
        char r = ' ';
        try {
            for (int i = 0; i < time; i++) {                
                
                //attendo che visualizza abbia finito
                ptrDati.waitSemVisualizza2();
                
                //attendo che abbiano cercato
                ptrDati.waitSemGeneraPunto();
                ptrDati.waitSemGeneraSpazio();

                //generazione casuale da 0 a 27
                n = rand.nextInt(28);
                switch (n) {
                    case 27:
                        r = '.';
                        ptrDati.addBuffer(r);
                        ptrDati.incNumPuntiInseriti();
                        break;

                    case 26:
                        r = ' ';
                        ptrDati.addBuffer(r);
                        ptrDati.incNumSpaziInseriti();
                        break;

                    default:
                        r = (char) (n + 'a');
                        ptrDati.addBuffer(r);
                        break;
                }                
                //notifico un cambiamento
                ptrDati.signalSemVisualizza1();
                //notifico che possono cercare
                ptrDati.signalSemRicercaPunto();
                ptrDati.signalSemRicercaSpazio();
                
                Thread.sleep(100);
            }
            ptrDati.signalFinish();
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ThGenera.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
