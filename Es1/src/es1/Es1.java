/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es1;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author galimberti_francesco
 */
public class Es1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here        
        try {
            Scanner input = new Scanner(System.in);

            System.out.println("Quanti caratteri si devono generare?");
            int nC = input.nextInt();

            DatiCondivisi dati = new DatiCondivisi(nC);
            ThGenera tg = new ThGenera(dati);
            ThCerca tp = new ThCerca(dati, '.');
            ThCerca ts = new ThCerca(dati, ' ');
            ThVisualizza tv = new ThVisualizza(dati);

            //generazione e ricerca
            tg.start();
            tp.start();
            ts.start();
            tv.start();    
            
            dati.waitFinish();
            
            /*
            System.out.println(" _____________________________________________________________________________");
            if(dati.getNumPuntiInseriti()==dati.getNumPuntiLetti()){
                System.out.println("Numero punti uguali");
            }else{
                System.out.println("Numero punti diversi");
            }
            System.out.println("Punti inseriti --> "+dati.getNumPuntiInseriti());
            System.out.println("Punti letti --> "+dati.getNumPuntiLetti());            
            
            if(dati.getNumSpaziInseriti()==dati.getNumSpaziLetti()){
                System.out.println("Numero spazi uguali");
            }else{
                System.out.println("Numero spazi diversi");
            }
            System.out.println("Spazi inseriti --> "+dati.getNumSpaziInseriti());
            System.out.println("Spazi letti --> "+dati.getNumSpaziLetti());
            System.out.println(" _____________________________________________________________________________");
            */

        } catch (InterruptedException ex) {
            Logger.getLogger(Es1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
