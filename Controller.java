/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import baza.DBBroker;
import java.util.List;
import model.Bicikla;
import model.Iznajmljivanje;
import model.Kupac;
import model.Prodavac;

/**
 *
 * @author HP
 */
public class Controller {
    
    private static Controller instance;
    private DBBroker dbb;
    
    
    public Controller() {
        dbb = new DBBroker();
    }
    
    public static Controller getInstance(){
        if(instance == null)
            instance = new Controller();
        return instance;
    }

    public Prodavac login(String korisnickoIme, String sifra) {
        return dbb.login(korisnickoIme,sifra);
    }

    public boolean sacuvajProdavcaUBazu(Prodavac prodavac) {
        return dbb.sacuvajProdavcaUBazu(prodavac);
    }

    public List<Prodavac> vratiSveProdavceIzBaze() {
        return  dbb.vratiSveProdavceIzBaze();
    }

    public List<Kupac> vratiSveKupceIzBaze() {
        return dbb.vratiSveKupceIzBaze();
    }

    public List<Bicikla> vratiSveBicikleIzBaze() {
        return dbb.vratiSveBicikleIzBaze();
    }

    public boolean sacuvajIznajmljivanjeUBazu(Iznajmljivanje iznajmljivanje) {
        return dbb.sacuvajIznajmljivanjeUBazu(iznajmljivanje);
    }

    public boolean daLiIznajmljivanjePostoji(Iznajmljivanje iznajmljivanje) {
        return dbb.daLiIznajmljivanjePostoji(iznajmljivanje);
    }

    
}
