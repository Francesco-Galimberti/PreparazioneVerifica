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
public class ThCerca extends Thread {

    private DatiCondivisi ptrDati;
    private final char daCercare;

    public ThCerca(DatiCondivisi dati, char c) {
        ptrDati = dati;
        daCercare = c;
    }

    @Override
    public void run() {
        try {
            int ii = ptrDati.getNumeroCaratteri();

            for (int i = 0; i < ii; i++) {

                switch (daCercare) {
                    case ' ':
                        //attendo che posso cercare
                        ptrDati.waitSemRicercaSpazio();
                        
                        if (daCercare == ptrDati.getBuffer(i)) {
                            ptrDati.incNumSpaziLetti();
                            
                            //attendo che visualizza abbia finito
                            ptrDati.waitSemVisualizza2();
                            //notifico un cambiamento
                            ptrDati.signalSemVisualizza1();   
                        }
                        
                        //notifico che puo generare
                        ptrDati.signalSemGeneraSpazio();
                        break;

                    case '.':
                        //attendo che posso cercare
                        ptrDati.waitSemRicercaPunto();
                        if (daCercare == ptrDati.getBuffer(i)) {
                            ptrDati.incNumPuntiLetti();
                            
                            //attendo che visualizza abbia finito
                            ptrDati.waitSemVisualizza2();
                            //notifico un cambiamento
                            ptrDati.signalSemVisualizza1(); 
                            
                        }
                        //notifico che puo generare
                        ptrDati.signalSemGeneraPunto();
                        break;
                }
                Thread.sleep(100);
            }        
            
            switch (daCercare) {
                    case ' ':
                        ptrDati.signalFinish();
                        break;
                    case '.':
                        ptrDati.signalFinish();
                        break;
            }
            
        } catch (InterruptedException ex) {
            //Logger.getLogger(ThCerca.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
