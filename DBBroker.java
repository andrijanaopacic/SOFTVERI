/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package baza;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Bicikla;
import model.Iznajmljivanje;
import model.Kupac;
import model.Prodavac;
import model.StavkaIznajmljivanja;

/**
 *
 * @author HP
 */
public class DBBroker {
    
    private Prodavac ulogovaniProdavac = null;

    public Prodavac login(String korisnickoIme, String sifra) {
        
        String upit = "SELECT * FROM PRODAVAC";
        
        try {
           
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
                int id = rs.getInt("idProdavac");
                String ime = rs.getString("ime");
                String prezime = rs.getString("prezime");
                String korisnickoImeProdavca = rs.getString("korisnickoIme");
                String sifraProdavca = rs.getString("sifra");
                
                if(korisnickoImeProdavca.equals(korisnickoIme) && sifraProdavca.equals(sifra)) {
                Prodavac prodavac = new Prodavac(id, ime, prezime, korisnickoImeProdavca, sifraProdavca);
                return prodavac;  // Vraćaš odmah kada nađeš odgovarajućeg prodavca
                }
            }
           
            
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }

    public boolean sacuvajProdavcaUBazu(Prodavac prodavac) {
        try {
            String upit = "INSERT INTO prodavac(ime, prezime, korisnickoIme, sifra) VALUES (?,?,?,?)";
            
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit);
            
            ps.setString(1,prodavac.getIme() );
            ps.setString(2, prodavac.getPrezime());
            ps.setString(3, prodavac.getKorisnickoIme());
            ps.setString(4, String.valueOf(prodavac.getSifra()));
            
            
            int affectedRows = ps.executeUpdate();
            Konekcija.getInstance().getConnection().commit();
            
            if(affectedRows > 0){
                return true;
            }
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public List<Prodavac> vratiSveProdavceIzBaze() {
        List<Prodavac> prodavci = new ArrayList<>();
        String upit = "SELECT * FROM PRODAVAC ORDER BY IME ASC";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
                int id = rs.getInt("idProdavac");
                String ime  = rs.getString("ime");
                String prezime  = rs.getString("prezime");
                String korisnickoIme  = rs.getString("korisnickoIme");
                String sifra  = rs.getString("sifra");
                Prodavac p = new Prodavac(id, ime, prezime, korisnickoIme, sifra);
                prodavci.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return prodavci;
    }

    public List<Kupac> vratiSveKupceIzBaze() {
       List<Kupac> kupci = new ArrayList<>();
        String upit = "SELECT * FROM KUPAC ORDER BY IME ASC";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
                int id = rs.getInt("idKupac");
                String ime  = rs.getString("ime");
                String prezime  = rs.getString("prezime");
                String brojLicneKarte  = rs.getString("brojLicneKarte");
                Kupac k = new Kupac(id, ime, prezime, brojLicneKarte);
                kupci.add(k);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return kupci;
    }

    public List<Bicikla> vratiSveBicikleIzBaze() {
        List<Bicikla> bicikle = new ArrayList<>();
        String upit = "SELECT * FROM BICIKLA ORDER BY TIP ASC";
        try {
            Statement st = Konekcija.getInstance().getConnection().createStatement();
            ResultSet rs = st.executeQuery(upit);
            while(rs.next()){
                int id = rs.getInt("idBicikla");
                String tip  = rs.getString("tip");
                double cenaPoSatu = rs.getDouble("cenaPoSatu");
                double cenaPoDanu = rs.getDouble("cenaPoDanu");
                Bicikla b = new Bicikla(id, tip, cenaPoSatu, cenaPoDanu);
                bicikle.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return bicikle;
    }

    public boolean sacuvajIznajmljivanjeUBazu(Iznajmljivanje iznajmljivanje) {
        
        try {

            // Kreiranje upita za unos Iznajmljivanja
            String upit = "INSERT INTO iznajmljivanje (ukupanIznos, idKupac, idProdavac) VALUES (?,?,?)";
            PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit, Statement.RETURN_GENERATED_KEYS);

            // Postavljanje vrednosti u upit
            ps.setDouble(1, iznajmljivanje.getUkupanIznos());
            ps.setInt(2, iznajmljivanje.getKupac().getIdKupac());
            ps.setInt(3, iznajmljivanje.getProdavac().getIdProdavac());

            // Izvršavanje upita
            int affectedRows = ps.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Greška prilikom unosa Iznajmljivanja.");
            }else{
            ResultSet generisaniKljucevi = ps.getGeneratedKeys();
            if (generisaniKljucevi.next()) {
                long idIznajmljivanje = generisaniKljucevi.getLong(1);  // Preuzimanje generisanog ID-ja
                String upit2 = "INSERT INTO stavkaiznajmljivanja (idBicikla, cena, brojSati, brojDana, vremeOd, vremeDo, idIznajmljivanje, iznos) VALUES (?,?,?,?,?,?,?,?)";
                ps = Konekcija.getInstance().getConnection().prepareStatement(upit2);

            for (StavkaIznajmljivanja stavka : iznajmljivanje.getListaStavkiIznajmljivanja()) {
                ps.setInt(1, stavka.getBicikla().getIdBicikla());  // ID bicikla
                System.out.println("\nSTA JE DODALO"+stavka.getBicikla().getIdBicikla());
                ps.setDouble(2, stavka.getCena());
                ps.setInt(3, stavka.getBrojSati());
                ps.setInt(4, stavka.getBrojDana());
                ps.setDouble(affectedRows, affectedRows);
                ps.setTimestamp(5, Timestamp.valueOf(stavka.getVremeOd()));  // VremeOd
                ps.setTimestamp(6, Timestamp.valueOf(stavka.getVremeDo()));  // VremeDo
                ps.setLong(7, idIznajmljivanje);  // ID Iznajmljivanje
                ps.setDouble(8, stavka.getIznos());

                ps.addBatch();  // Dodavanje stavke u batch
            }

            // Izvršavanje batch operacije
            ps.executeBatch();

            // Potvrda transakcije
            Konekcija.getInstance().getConnection().commit();
            }else{
                Konekcija.getInstance().getConnection().rollback();
            }
        }
            return true;
        } catch (SQLException ex) {
            try {
                // Ukoliko dođe do greške, rollback
                Konekcija.getInstance().getConnection().rollback();
            } catch (SQLException e) {
                Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, e);
            }
            Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
}
public boolean daLiIznajmljivanjePostoji(Iznajmljivanje iznajmljivanje) {
    int brojStavki = iznajmljivanje.getListaStavkiIznajmljivanja().size();
    int brojIstih = 0;

    String upit = "SELECT * " +
                  "FROM bicikla b " +
                  "JOIN stavkaiznajmljivanja si ON b.idBicikla = si.idBicikla " +
                  "JOIN iznajmljivanje i ON i.idIznajmljivanje = si.idIznajmljivanje " +
                  "WHERE i.ukupanIznos = ? " +
                  "AND i.idProdavac = ? " +
                  "AND i.idKupac = ?";

    try (PreparedStatement ps = Konekcija.getInstance().getConnection().prepareStatement(upit)) {
        ps.setDouble(1, iznajmljivanje.getUkupanIznos());
        ps.setLong(2, iznajmljivanje.getProdavac().getIdProdavac());
        ps.setLong(3, iznajmljivanje.getKupac().getIdKupac());

        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String tipBicikla = rs.getString("b.tip");
                double cena = rs.getDouble("si.cena");
                double iznos = rs.getDouble("si.iznos");
                int brojSati = rs.getInt("si.brojSati");
                int brojDana = rs.getInt("si.brojDana");
                Timestamp vremeOd = rs.getTimestamp("si.vremeOd");
                Timestamp vremeDo = rs.getTimestamp("si.vremeDo");

                for (StavkaIznajmljivanja stavka : iznajmljivanje.getListaStavkiIznajmljivanja()) {
                    boolean poklapanjeVremena = vremeOd != null && vremeDo != null &&
                            vremeOd.toLocalDateTime().equals(stavka.getVremeOd()) &&
                            vremeDo.toLocalDateTime().equals(stavka.getVremeDo());

                    System.out.println("vreme "+poklapanjeVremena);
                    System.out.println("tipBicikla" + tipBicikla + " "+stavka.getBicikla().getTip());
                    if (tipBicikla.equals(stavka.getBicikla().getTip())){
                        System.out.println("bajs" +true);
                    }
                    if (cena == stavka.getCena()){
                        System.out.println("cena" +true);
                    }
                    if (iznos == stavka.getIznos()){
                        System.out.println("iznos"+true);
                    }
                    if(brojSati == stavka.getBrojSati() &&
                        brojDana == stavka.getBrojDana()){
                        System.out.println("brojsati/dana"+true);
                    }
                    
                    if (tipBicikla == stavka.getBicikla().getTip()&&
                        cena == stavka.getCena() &&
                        iznos == stavka.getIznos() &&
                        brojSati == stavka.getBrojSati() &&
                        brojDana == stavka.getBrojDana() && poklapanjeVremena ) {
                        System.out.println("DOSAO DO OVDE\n");
                        brojIstih++;
                        //break; // Prelazak na sledeću stavku iz baze
                    }
                }
            }
        }
        System.out.println("broj istih " + brojIstih + " broj stavki = " + brojStavki);
        if(brojIstih == brojStavki)
            return true;
    } catch (SQLException ex) {
        Logger.getLogger(DBBroker.class.getName()).log(Level.SEVERE, null, ex);
        return false;
    }

    return false;
}


}



  







       



    
    

