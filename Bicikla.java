/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author HP
 */
public class Bicikla {
    
    private int idBicikla;
    private String tip;
    private double cenaPoSatu;
    private double cenaPoDanu;

    public Bicikla() {
    }

    public Bicikla(int idBicikla, String tip, double cenaPoSatu, double cenaPoDanu) {
        this.idBicikla = idBicikla;
        this.tip = tip;
        this.cenaPoSatu = cenaPoSatu;
        this.cenaPoDanu = cenaPoDanu;
    }

    

    public int getIdBicikla() {
        return idBicikla;
    }

    public void setIdBicikla(int idBicikla) {
        this.idBicikla = idBicikla;
    }

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }

    public double getCenaPoSatu() {
        return cenaPoSatu;
    }

    public void setCenaPoSatu(double cenaPoSatu) {
        this.cenaPoSatu = cenaPoSatu;
    }

    public double getCenaPoDanu() {
        return cenaPoDanu;
    }

    public void setCenaPoDanu(double cenaPoDanu) {
        this.cenaPoDanu = cenaPoDanu;
    }

    
    
    @Override
    public String toString() {
        return tip;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Bicikla other = (Bicikla) obj;
        return Objects.equals(this.tip, other.tip);
    }
    
    
    
}
