/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eseStatistiche;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 *
 * @author galimberti_francesco
 */
public class DatiCondivisi {

    private int numeroCaratteri;
    private ArrayList<Carattere> buffer;

    private int numSpaziInseriti;
    private int numPuntiInseriti;

    private int numSpaziLetti;
    private int numPuntiLetti;

    //necessario per la sincronizzazione dei thread genera e cerca
    private Semaphore semGeneraPunto;
    private Semaphore semGeneraSpazio;
    private Semaphore semRicercaPunto;
    private Semaphore semRicercaSpazio;

    //necessario per la sincronizzazione per la visualizzazione
    private Semaphore semVisualizza1;
    private Semaphore semVisualizza2;

    //necessario per la fine
    private Semaphore finish;

    public DatiCondivisi(int nC) {
        buffer = new ArrayList<Carattere>();
        numeroCaratteri = nC;

        numSpaziInseriti = 0;
        numPuntiInseriti = 0;
        numSpaziLetti = 0;
        numPuntiLetti = 0;

        semGeneraPunto = new Semaphore(1);
        semGeneraSpazio = new Semaphore(1);
        semRicercaPunto = new Semaphore(0);
        semRicercaSpazio = new Semaphore(0);

        semVisualizza1 = new Semaphore(1);
        semVisualizza2 = new Semaphore(0);

        finish = new Semaphore(-2);
    }

    public void waitSemVisualizza1() throws InterruptedException {
        semVisualizza1.acquire();
    }

    public void signalSemVisualizza1() {
        semVisualizza1.release();
    }

    public void waitSemVisualizza2() throws InterruptedException {
        semVisualizza2.acquire();
    }

    public void signalSemVisualizza2() {
        semVisualizza2.release();
    }

    public void waitSemGeneraPunto() throws InterruptedException {
        semGeneraPunto.acquire();
    }

    public void signalSemGeneraPunto() {
        semGeneraPunto.release();
    }

    public void waitSemGeneraSpazio() throws InterruptedException {
        semGeneraSpazio.acquire();
    }

    public void signalSemGeneraSpazio() {
        semGeneraSpazio.release();
    }

    public void waitSemRicercaPunto() throws InterruptedException {
        semRicercaPunto.acquire();
    }

    public void signalSemRicercaPunto() {
        semRicercaPunto.release();
    }

    public void waitSemRicercaSpazio() throws InterruptedException {
        semRicercaSpazio.acquire();
    }

    public void signalSemRicercaSpazio() {
        semRicercaSpazio.release();
    }

    public void waitFinish() throws InterruptedException {
        finish.acquire();
    }

    public void signalFinish() {
        finish.release();
    }

    public synchronized int getNumeroCaratteri() {
        return numeroCaratteri;
    }

    public synchronized int getNumSpaziInseriti() {
        return numSpaziInseriti;
    }

    public synchronized int getNumPuntiInseriti() {
        return numPuntiInseriti;
    }

    public synchronized int getNumSpaziLetti() {
        return numSpaziLetti;
    }

    public synchronized int getNumPuntiLetti() {
        return numPuntiLetti;
    }

    public synchronized void incNumSpaziInseriti() {
        this.numSpaziInseriti++;
    }

    public synchronized void incNumPuntiInseriti() {
        this.numPuntiInseriti++;
    }

    public synchronized void incNumSpaziLetti() {
        this.numSpaziLetti++;
    }

    public synchronized void incNumPuntiLetti() {
        this.numPuntiLetti++;
    }

    public void addBuffer(char c) {
        Carattere car = new Carattere(c);
        buffer.add(car);
    }

    public synchronized char getBuffer(int p) {
        return buffer.get(p).getCarattere();
    }

    public ArrayList<Carattere> getBuffer() {
        return buffer;
    }

}
