/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eseLotto;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franc
 */
public class Es2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input = new Scanner(System.in);
        try {
            System.out.println("Inserire il numero delle estrazioni da simulare");
            int nE = input.nextInt();
            System.out.println("Inserire il primo numero puntato (1-50)");
            int n1 = input.nextInt();
            System.out.println("Inserire il secondo numero puntato (1-50)");
            int n2 = input.nextInt();

            DatiCondivisi dati = new DatiCondivisi(nE);
            ThGenera tg = new ThGenera(dati);
            ThCercaNumero t1 = new ThCercaNumero(dati, 1, n1);
            ThCercaNumero t2 = new ThCercaNumero(dati, 2, n2);

            tg.start();            
            t1.start();
            t2.start();
            
            //attendo la fine
            System.out.println("main --> in attesa");
            dati.waitFinish();
            System.out.println("main --> fine attesa");
            
            //verifico quanti ambo e su quali ruote sono stati fatti
            int nVincite=0;      
            int vett[];            
            System.out.println("-----------------------------------------------");
            System.out.println("Primo numero puntato --> "+n1);
            System.out.println("Secondo numero puntato --> "+n2);
            for (int i = 0; i < nE; i++) {                
                vett=dati.getVettoriPosizione(i);
                
                System.out.print("Ruota"+i+" --> ");
                for(int j=0;j<5;j++){
                    System.out.print(vett[j]+" ");
                }                
                if(dati.getVincitaPosizione(i)){
                    nVincite++;
                    System.out.println("Ambo");
                }else{
                    System.out.println("");
                }
            }            
            System.out.println("Numero ambo --> "+ nVincite);
            System.out.println("-----------------------------------------------");           
        } catch (InterruptedException ex) {
            Logger.getLogger(Es2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
