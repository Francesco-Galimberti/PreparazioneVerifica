/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es1;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galimberti_francesco
 */
public class ThCerca extends Thread {

    private DatiCondivisi ptrDati;
    private char daCercare;

    public ThCerca(DatiCondivisi dati, char c) {
        ptrDati = dati;
        daCercare = c;
    }

    @Override
    public void run() {
        try {
            int n = 0;

            int ii = ptrDati.getNumeroCaratteri();
            for (int i = 0; i < ii; i++) {

                switch (daCercare) {
                    case ' ':
                        ptrDati.waitSemRicercaSpazio();
                        break;
                        
                    case '.':
                        ptrDati.waitSemRicercaPunto();
                        break;
                }
                
                if (daCercare == ptrDati.getBuffer(i)) {
                    n++;
                }

                switch (daCercare) {
                    case ' ':
                        ptrDati.setNumSpaziLetti(n);
                        ptrDati.signalSemVisualizza();
                        break;
                        
                    case '.':
                        ptrDati.setNumPuntiLetti(n);
                        ptrDati.signalSemVisualizza();
                        break;
                }

            }
        } catch (InterruptedException ex) {
            //Logger.getLogger(ThCerca.class.getName()).log(Level.SEVERE, null, ex);
        }
        ptrDati.signalFinish();
    }
}
