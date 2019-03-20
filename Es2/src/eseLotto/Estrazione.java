/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eseLotto;

/**
 *
 * @author franc
 */
public class Estrazione {
    private int[] vettore;

    public Estrazione(int[] vett) {
        this.vettore = vett;
    }

    public int[] getVettore() {
        return vettore;
    }
    public int getVettorePosizione(int p) {
        return vettore[p];
    }

    public void setVettore(int[] vettore) {
        this.vettore = vettore;
    }
    
    
    
}
