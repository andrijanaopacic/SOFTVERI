/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author HP
 */
public class Kupac {
    
    private int idKupac;
    private String ime;
    private String prezime;
    private String brojLicneKarte;

    public Kupac() {
    }

    public Kupac(int idKupac, String ime, String prezime, String brojLicneKarte) {
        this.idKupac = idKupac;
        this.ime = ime;
        this.prezime = prezime;
        this.brojLicneKarte = brojLicneKarte;
    }
    
    

    public int getIdKupac() {
        return idKupac;
    }

    public void setIdKupac(int idKupac) {
        this.idKupac = idKupac;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBrojLicneKarte() {
        return brojLicneKarte;
    }

    public void setBrojLicneKarte(String brojLicneKarte) {
        this.brojLicneKarte = brojLicneKarte;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
    
    
}
