/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eseLotto;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franc
 */
public class ThCercaNumero extends Thread {

    private DatiCondivisi ptrDati;
    private int nCercato;
    private int nThread;

    public ThCercaNumero(DatiCondivisi dati, int nThread, int n) {
        this.ptrDati = dati;
        this.nCercato = n;
        this.nThread = nThread;
    }

    @Override
    public void run() {
        try {
            int nE = ptrDati.getnRuote();
            int[] vett;

            //numero estrazioni
            for (int i = 0; i < nE; i++) {
                
                
                
                if (nThread == 1) {                        
                    ptrDati.waitSemRicercaN1(); 
                    ptrDati.setUscitaN1(false);
                } else {                    
                    ptrDati.waitSemRicercaN2();
                    ptrDati.setUscitaN2(false);
                }

                vett = ptrDati.getVettoriPosizione(i);

                //ricerca
                int j = 0;
                int k = -1;
                while ((j < 5) && (k == -1)) {
                    if (nCercato == vett[j]) {
                        if (nThread == 1) {
                            ptrDati.setUscitaN1(true);
                        } else {
                            ptrDati.setUscitaN2(true);
                        }
                        k = j;
                    }
                    j++;
                }

                Thread.sleep((int) (Math.random() * 150 + Math.random() * 150 - Math.random() * 100)); 
                
                //se l'atro THREAD è terminato
                if (ptrDati.isFinito()) {
                    //verifico se ambo
                    if (ptrDati.isUscitaN1() && ptrDati.isUscitaN2()) {
                        ptrDati.setVincitaPosizione(true, i);
                    } else {
                        ptrDati.setVincitaPosizione(false, i);
                    }
                    //annullo variabile 
                    ptrDati.setFinito(false);

                } else {
                    //altrimenti dico che ho finito
                    ptrDati.setFinito(true);
                }
            }
            
            ptrDati.signalFinish();

        } catch (InterruptedException ex) {
            Logger.getLogger(ThCercaNumero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
