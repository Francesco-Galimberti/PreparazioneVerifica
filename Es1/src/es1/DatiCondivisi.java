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
    private Semaphore semRicerca;
    
    //necessario per la sincronizzazione per la visualizzazione
    private Semaphore semVisualizza1;
    private Semaphore semVisualizza2;

    public DatiCondivisi(int nC) {
        buffer = new ArrayList<Carattere>();
        numeroCaratteri = nC;

        numSpaziInseriti = 0;
        numPuntiInseriti = 0;
        numSpaziLetti = 0;
        numPuntiLetti = 0;
        
        semRicerca=new Semaphore(2);
        semVisualizza1=new Semaphore(1);
        semVisualizza2=new Semaphore(0);
    }
    
    public synchronized void waitSemRicerca() throws InterruptedException{
        semRicerca.acquire();
    }
    public synchronized void signalSemRicerca(){
        semRicerca.release();
    }
    public synchronized void waitSemVisualizza1() throws InterruptedException{
        semVisualizza1.acquire();
    }
    public synchronized void signalSemVisualizza1(){
        semVisualizza1.release();
    }
    public synchronized void waitSemVisualizza2() throws InterruptedException{
        semVisualizza2.acquire();
    }
    public synchronized void signalSemVisualizza2(){
        semVisualizza2.release();
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
