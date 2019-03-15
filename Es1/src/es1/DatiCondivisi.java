/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es1;

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
    private Semaphore semRicercaPunto;
    private Semaphore semRicercaSpazio;
    
    //necessario per la sincronizzazione per la visualizzazione
    private Semaphore semVisualizza;
    
    //necessario per la fine
    private Semaphore finish;

    public DatiCondivisi(int nC) {
        buffer = new ArrayList<Carattere>();
        numeroCaratteri = nC;

        numSpaziInseriti = 0;
        numPuntiInseriti = 0;
        numSpaziLetti = 0;
        numPuntiLetti = 0;
        
        semRicercaPunto=new Semaphore(0);
        semRicercaSpazio=new Semaphore(0);
        semVisualizza=new Semaphore(1);
        finish=new Semaphore(-3);
    }
    
    public synchronized void waitSemRicercaPunto() throws InterruptedException{
        semRicercaPunto.acquire();
    }
    public synchronized void signalSemRicercaPunto(){
        semRicercaPunto.release();
    }
    public synchronized void waitSemRicercaSpazio() throws InterruptedException{
        semRicercaSpazio.acquire();
    }
    public synchronized void signalSemRicercaSpazio(){
        semRicercaSpazio.release();
    }
    public synchronized void waitSemVisualizza() throws InterruptedException{
        semVisualizza.acquire();
    }
    public synchronized void signalSemVisualizza(){
        semVisualizza.release();
    }
    public synchronized void waitFinish() throws InterruptedException{
        semVisualizza.acquire();
    }
    public synchronized void signalFinish(){
        semVisualizza.release();
    }

    public synchronized int getNumeroCaratteri() {
        return numeroCaratteri;
    }

    public synchronized char getBuffer(int p) {
        return buffer.get(p).getCarattere();
    }
    public synchronized ArrayList<Carattere> getBuffer() {
        return buffer;
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

    public synchronized void setNumeroCaratteri(int numeroCaratteri) {
        this.numeroCaratteri = numeroCaratteri;
    }

    public synchronized void addBuffer(char carattere) {
        Carattere c=new Carattere(carattere);
        this.buffer.add(c);
    }

    public synchronized void setNumSpaziLetti(int numSpaziLetti) {
        this.numSpaziLetti = numSpaziLetti;
    }

    public synchronized void setNumPuntiLetti(int numPuntiLetti) {
        this.numPuntiLetti = numPuntiLetti;
    }
    
    public synchronized void incNumSpaziInseriti(){
        this.numSpaziInseriti++;
    }
    public synchronized void incNumPuntiInseriti(){
        this.numPuntiInseriti++;
    }
    public synchronized void incNumSpaziLetti(){
        this.numSpaziLetti++;
    }
    public synchronized void incNumPuntiLetti(){
        this.numPuntiLetti++;
    }

}
