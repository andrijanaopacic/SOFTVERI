/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package forme.iznajmljivanje;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Iznajmljivanje;
import model.StavkaIznajmljivanja;

/**
 *
 * @author HP
 */
public class ModelTabeleIznajmljivanje extends AbstractTableModel{

    List<StavkaIznajmljivanja> stavke = new ArrayList<>();
    String[] kolone = {"Tip bicikle","Broj dana","Cena po danu","Broj sati","Cena po satu", "Iznos"};
    
    public ModelTabeleIznajmljivanje() {
        stavke = new ArrayList<>();
    }
    
    public ModelTabeleIznajmljivanje(List<StavkaIznajmljivanja> stavke){
        this.stavke = stavke;
    }
    
    @Override
    public int getRowCount() {
        return stavke.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaIznajmljivanja i = stavke.get(rowIndex);
        
        switch (columnIndex){
                case 0:
                    return i.getBicikla().getTip();
                case 1:
                    return i.getBrojDana();
                case 2:
                    return i.getBicikla().getCenaPoDanu();
                case 3:
                    return i.getBrojSati();
                case 4:
                    return i.getBicikla().getCenaPoSatu();
                case 5:
                    return i.getIznos();
                default:
                    return "N/A";
        }
    }

    public List<StavkaIznajmljivanja> getStavke() {
        return stavke;
    }

    public void setStavke(List<StavkaIznajmljivanja> stavke) {
        this.stavke = stavke;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];    
    }

    void dodajStavku(StavkaIznajmljivanja i) {
        
        stavke.add(i);
        fireTableDataChanged();
        
    }

    void obrisi(int selektovaniRed) {
        stavke.remove(selektovaniRed);
        fireTableDataChanged();
    }
    
    public double izracunajUkupanIznos() {
        double ukupanIznos = 0;
        for (StavkaIznajmljivanja stavka : stavke) {
            ukupanIznos += stavka.getIznos();
        }
        return ukupanIznos;
    }
    
}
