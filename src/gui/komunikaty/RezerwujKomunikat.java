package gui.komunikaty;

import procesor.baza.*;

/**
 * Klasa tworząca komunikat rezerwowania po wybraniu przycisku rezerwuj
 * 
 */
public class RezerwujKomunikat extends Komunikat {
    private final String login, marka, model, kolor, paliwo, data_odbioru, data_zwrotu;
    private final int rok; 

    /**
     * Konstruktor komunikatu rezerwowania
     * @param login zalogowany użytkownik
     */     
    public RezerwujKomunikat(String login){
        this.login = login;
        this.marka = null;
        this.model = null;
        this.kolor = null;      
        this.paliwo = null;
        this.rok = 0;  
        this.data_odbioru = null; 
        this.data_zwrotu = null; 
    }

    /**
     * Konstruktor rezerwowaniaKomunikatu
     * @param login zalogowany użytkownik
     * @param marka marka anulowanego samochodu
     * @param model model anulowanego samochodu
     * @param kolor kolor anulowanego samochodu
     * @param paliwo paliwo anulowanego samochodu
     * @param rok data produkcji anulowanego samochodu
     * @param data_odbioru data odbioru anulowanego samochodu
     * @param data_zwrotu data zwrotu anulowanego samochodu
     */     
    public RezerwujKomunikat(String login, String marka, String model, String kolor, String paliwo, int rok, String data_odbioru, String data_zwrotu){
        this.login = login;
        this.marka = marka;
        this.model = model;
        this.kolor = kolor; 
        this.paliwo = paliwo;
        this.rok = rok;  
        this.data_odbioru = data_odbioru;
        this.data_zwrotu = data_zwrotu;                 
    } 

    public String pobierzLogin() {
        return login;
    }
    
    public String pobierzMarkę() {
        return marka;
    }    
    
    public String pobierzModel() {
        return model;
    }   
    
    public String pobierzKolor() {
        return kolor;
    } 

    public String pobierzPaliwo() {
        return kolor;
    } 

    public int pobierzRok() {
        return rok;
    }
    
    public String pobierzDatęOdbioru() {
        return data_odbioru;
    } 

    public String pobierzDatęZwrotu() {
        return data_zwrotu;
    }    
}
