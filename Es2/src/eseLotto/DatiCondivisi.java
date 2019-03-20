/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eseLotto;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author franc
 */
public class DatiCondivisi {

    private int nRuote;

    private boolean uscitaN1;
    private boolean uscitaN2;

    private ArrayList<Estrazione> vettori;
    //vettore che memorizza se ho fatto ambo
    private boolean[] vincita;

    //se ho finito di effettuare la verifica
    private boolean finito;

    private Semaphore semRicercaN1;
    private Semaphore semRicercaN2;

    private Semaphore finish;

    public DatiCondivisi(int nRuote) {
        this.nRuote = nRuote;

        this.uscitaN1 = false;
        this.uscitaN2 = false;
        this.finito = false;

        this.vincita = new boolean[this.nRuote];
        this.vettori = new ArrayList<Estrazione>();

        semRicercaN1 = new Semaphore(0);
        semRicercaN2 = new Semaphore(0);

        finish = new Semaphore(-2);
    }

    public void waitFinish() throws InterruptedException {
        finish.acquire();
    }

    public void signalFinish() {
        finish.release();
    }

    public void waitSemRicercaN1() throws InterruptedException {
        semRicercaN1.acquire();
    }

    public void signalSemRicercaN1() {
        semRicercaN1.release();
    }

    public void waitSemRicercaN2() throws InterruptedException {
        semRicercaN2.acquire();
    }

    public void signalSemRicercaN2() {
        semRicercaN2.release();
    }

    public synchronized int getnRuote() {
        return nRuote;
    }

    public synchronized boolean isUscitaN1() {
        return uscitaN1;
    }

    public synchronized boolean isUscitaN2() {
        return uscitaN2;
    }

    public synchronized void setUscitaN1(boolean uscitaN1) {
        this.uscitaN1 = uscitaN1;
    }

    public synchronized void setUscitaN2(boolean uscitaN2) {
        this.uscitaN2 = uscitaN2;
    }

    public synchronized boolean isFinito() {
        return finito;
    }

    public synchronized boolean getVincitaPosizione(int p) {
        return vincita[p];
    }

    public synchronized void setFinito(boolean finito) {
        this.finito = finito;
    }

    public synchronized void setVincitaPosizione(boolean vincita, int p) {
        this.vincita[p] = vincita;
    }

    public synchronized int[] getVettoriPosizione(int p) {
        return vettori.get(p).getVettore();
    }

    public synchronized void addVettori(int[] vettore) {
        Estrazione vett = new Estrazione(vettore);
        this.vettori.add(vett);
    }

}
