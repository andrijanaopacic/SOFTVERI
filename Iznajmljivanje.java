/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.List;

/**
 *
 * @author HP
 */
public class Iznajmljivanje {
    
    private int idIznajmljivanje;
    private double ukupanIznos;
    private List<StavkaIznajmljivanja> listaStavkiIznajmljivanja;
    private Kupac kupac;
    private Prodavac prodavac;

    public Iznajmljivanje() {
    }

    public Iznajmljivanje(int idIznajmljivanje, double ukupanIznos, List<StavkaIznajmljivanja> listaStavkiIznajmljivanja, Kupac kupac, Prodavac prodavac) {
        this.idIznajmljivanje = idIznajmljivanje;
        this.ukupanIznos = ukupanIznos;
        this.listaStavkiIznajmljivanja = listaStavkiIznajmljivanja;
        this.kupac = kupac;
        this.prodavac = prodavac;
    }

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public Prodavac getProdavac() {
        return prodavac;
    }

    public void setProdavac(Prodavac prodavac) {
        this.prodavac = prodavac;
    }

    

    public List<StavkaIznajmljivanja> getListaStavkiIznajmljivanja() {
        return listaStavkiIznajmljivanja;
    }

    public void setListaStavkiIznajmljivanja(List<StavkaIznajmljivanja> listaStavkiIznajmljivanja) {
        this.listaStavkiIznajmljivanja = listaStavkiIznajmljivanja;
    }
    
    

    public int getIdIznajmljivanje() {
        return idIznajmljivanje;
    }

    public void setIdIznajmljivanje(int idIznajmljivanje) {
        this.idIznajmljivanje = idIznajmljivanje;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    @Override
    public String toString() {
        return "Iznajmljivanje{" + "idIznajmljivanje=" + idIznajmljivanje + ", ukupanIznos=" + ukupanIznos + 
                ", listaStavkiIznajmljivanja=" + listaStavkiIznajmljivanja + ", kupac=" + kupac + ", prodavac=" + prodavac + '}';
    }

    
    
    
}
