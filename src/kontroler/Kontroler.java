package kontroler;

import gui.*;
import gui.komunikaty.*;
import java.util.ArrayList;
import procesor.baza.*;
import procesor.Model;

/**
 * Klasa tworząca obiekt kontrolera we wzorcu mvc (model - view - controller)
 * 
 */
public class Kontroler {
    private Model model;
    private Widok widok;
    private Profil profil;
    private Oferta oferta;
    private gui.Rezerwacja rezerwacja;
    private Podgląd podgląd;
    private Anulowanie anulowanie;
    private Klienci klienci;
    
    private ArrayList<Samochód> listaSamochodów;
    private ArrayList<procesor.baza.Rezerwacja> listaRezerwacji;
    
    /**
     * Konstruktor kontrolera
     * @param model obiekt klasy modelu
     * @param widok obiekt klasy widoku
     */ 
    public Kontroler(Model model, Widok widok) {
        this.model = model;
        this.widok = widok;
    }   
    
    /**
     * Metoda korzystająca ze wzorca strategia w celu wybrania odpowiedniego dziąłania
     * @param klik będący komunikatem wygenerowanym po naciśnięciu przycisku
     */
    public void wykonajAkcję(Komunikat klik){
        //Sprawdza czy przesłany komunikat jest odpowiednią instancją
        if (klik instanceof ZalogujKomunikat){             
            Użytkownik użytkownik = model.zaloguj(((ZalogujKomunikat)klik).pobierzLogin(), ((ZalogujKomunikat)klik).pobierzHasło());
            
            if (użytkownik != null) {
                profil = new Profil(this, użytkownik);
                widok.ukryjWidok();
            }           
        } else if (klik instanceof PrzeglądajNiezalogowanyKomunikat) {
            listaSamochodów = model.przeglądaj();
            
            if (listaSamochodów != null) {
                oferta = new Oferta(listaSamochodów, false);
            }
        } else if (klik instanceof PrzeglądajZalogowanyKomunikat) {
            listaSamochodów = model.przeglądaj();
            
            if (listaSamochodów != null) {
                oferta = new Oferta(listaSamochodów, true);
            }            
        } else if (klik instanceof RezerwujKomunikat){
            if (((RezerwujKomunikat)klik).pobierzMarkę() == null) {
                listaSamochodów = model.przeglądaj();
            
                if (listaSamochodów != null) {
                    rezerwacja = new gui.Rezerwacja(this, ((RezerwujKomunikat)klik).pobierzLogin(), listaSamochodów);                
                }                 
            } else {
                rezerwacja.zamknijRezerwację();
                model.rezerwuj(((RezerwujKomunikat)klik).pobierzLogin(), ((RezerwujKomunikat)klik).pobierzMarkę(), ((RezerwujKomunikat)klik).pobierzModel(), ((RezerwujKomunikat)klik).pobierzKolor(), ((RezerwujKomunikat)klik).pobierzPaliwo(), ((RezerwujKomunikat)klik).pobierzRok(), ((RezerwujKomunikat)klik).pobierzDatęOdbioru(), ((RezerwujKomunikat)klik).pobierzDatęZwrotu());
            }
        } else if (klik instanceof PrzeglądajRezerwacjeKomunikat) {
            listaRezerwacji = model.przeglądajRezerwacje(((PrzeglądajRezerwacjeKomunikat)klik).pobierzLogin());
            
            if (listaRezerwacji != null) {
                podgląd = new Podgląd(listaRezerwacji);
            }   
        } else if (klik instanceof AnulujRezerwacjęKomunikat) {
            listaRezerwacji = model.przeglądajRezerwacje(((AnulujRezerwacjęKomunikat)klik).pobierzLogin());
            
            if (listaRezerwacji != null) {
                anulowanie = new Anulowanie(this, ((AnulujRezerwacjęKomunikat)klik).pobierzLogin(), listaRezerwacji);
            }  
        } else if (klik instanceof AnulujKomunikat) {
            anulowanie.zamknijAnulowanie();
            model.anulujRezerwację(((AnulujKomunikat)klik).pobierzLogin(), ((AnulujKomunikat)klik).pobierzMarkę(), ((AnulujKomunikat)klik).pobierzModel(), ((AnulujKomunikat)klik).pobierzKolor(), ((AnulujKomunikat)klik).pobierzPaliwo(), ((AnulujKomunikat)klik).pobierzRok(), ((AnulujKomunikat)klik).pobierzDatęOdbioru(), ((AnulujKomunikat)klik).pobierzDatęZwrotu());  
        } else if (klik instanceof WylogujKomunikat) {
            model.wyloguj(((WylogujKomunikat)klik).pobierzLogin(), ((WylogujKomunikat)klik).pobierzHasło());
             
            widok.pokażWidok();
            profil.zamknijProfil();         
        } else if (klik instanceof PrzeglądajKlientówKomunikat) {
            listaRezerwacji = model.przeglądajRezerwacje();
            
            if (listaSamochodów != null) {
                klienci = new Klienci(listaRezerwacji);
            }            
        }        
    }
}
