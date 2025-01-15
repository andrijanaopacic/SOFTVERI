/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;
import java.util.Date;

/**
 *
 * @author HP
 */
public class StavkaIznajmljivanja {
    
    private Bicikla bicikla;
    private int idStavkaIznajmljivanja;
    private double iznos;
    private double cena;
    private LocalDateTime vremeOd;
    private LocalDateTime vremeDo;
    private int brojSati;
    private int brojDana;
    private Iznajmljivanje iznajmljivanje;

    public StavkaIznajmljivanja() {
    }

    public StavkaIznajmljivanja(Bicikla bicikla, int idStavkaIznajmljivanja, double iznos, double cena, LocalDateTime vremeOd, LocalDateTime vremeDo, int brojSati, int brojDana, Iznajmljivanje iznajmljivanje) {
        this.bicikla = bicikla;
        this.idStavkaIznajmljivanja = idStavkaIznajmljivanja;
        this.vremeOd = vremeOd;
        this.vremeDo = vremeDo;
        this.brojSati = brojSati;
        this.brojDana = brojDana;
        this.iznajmljivanje = iznajmljivanje;
        if (brojDana > 0) {
            this.cena = bicikla.getCenaPoDanu();
        } else {
            this.cena = bicikla.getCenaPoSatu();
        }
        if (brojDana > 0) {
            this.iznos = this.cena * brojDana; 
        } else {
            this.iznos = this.cena * brojSati; 
        }
    }
    
    

    public Bicikla getBicikla() {
        return bicikla;
    }

    public void setBicikla(Bicikla bicikla) {
        this.bicikla = bicikla;
    }

    public Iznajmljivanje getIznajmljivanje() {
        return iznajmljivanje;
    }

    public void setIznajmljivanje(Iznajmljivanje iznajmljivanje) {
        this.iznajmljivanje = iznajmljivanje;
    }

    public int getIdStavkaIznajmljivanja() {
        return idStavkaIznajmljivanja;
    }

    public void setIdStavkaIznajmljivanja(int idStavkaIznajmljivanja) {
        this.idStavkaIznajmljivanja = idStavkaIznajmljivanja;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public LocalDateTime getVremeOd() {
        return vremeOd;
    }

    public void setVremeOd(LocalDateTime vremeOd) {
        this.vremeOd = vremeOd;
    }

    public LocalDateTime getVremeDo() {
        return vremeDo;
    }

    public void setVremeDo(LocalDateTime vremeDo) {
        this.vremeDo = vremeDo;
    }

    public int getBrojSati() {
        return brojSati;
    }

    public void setBrojSati(int brojSati) {
        this.brojSati = brojSati;
    }

    public int getBrojDana() {
        return brojDana;
    }

    public void setBrojDana(int brojDana) {
        this.brojDana = brojDana;
    }
    
    

//    @Override
//    public String toString() {
//        return iznos + " RSD";
//    }

    @Override
    public String toString() {
        return "StavkaIznajmljivanja{" + " idStavkaIznajmljivanja=" + idStavkaIznajmljivanja + ", iznos=" + iznos + ", cena=" + cena + " brojSati=" + brojSati + ", brojDana=" + brojDana + ", iznajmljivanje=" + iznajmljivanje.getIdIznajmljivanje() + "bicikla = " +bicikla.getIdBicikla()+'}';
    }
    
    
    
}
