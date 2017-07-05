package procesor;

import gui.Widok;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import procesor.baza.*;

/**
 * Klasa tworząca obiekt modelu we wzorcu mvc (model - view - controller)
 * 
 */
public class Model {
    private Widok widok;    

    /**
     * Metoda pozwalająca na zalogowanie użytkownika
     * @return obiekt zalogowanego użytkownika lub null w przypadku niepowodzenia
     * @param login przesłany login
     * @param hasło treść hasła
     */
    public Użytkownik zaloguj(String login, String hasło) {   
        Użytkownik użytkownik = null;
              
        try {
            użytkownik = Połączenie.pobierzInstancję().zaloguj(login, hasło);
            
            if (użytkownik == null) {
                JOptionPane.showMessageDialog(null, "Nieudana próba logowania", "Wystąpił błąd", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NullPointerException | IOException | ClassNotFoundException ex){
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Nie udało się wysłać/przetworzyć żądania", "Wystąpił błąd", JOptionPane.ERROR_MESSAGE);
        }
        
        return użytkownik;
    }

    /**
     * Metoda pozwalająca na wylogowanie użytkownika
     * @return obiekt zalogowanego użytkownika lub null w przypadku niepowodzenia
     * @param login przesłany login
     * @param hasło treść hasła
     */    
    public void wyloguj(String login, String hasło) {
        try {
            Połączenie.pobierzInstancję().wyloguj(login, hasło);
        } catch (IOException ex) {
            System.err.println("Nieudana próba zakończenia połączenia");     
        }
    }

    /**
     * Metoda pozwalająca na przeglądanie oferty dostępnych samochodó
     * @return kolekcja obieków dostępnych samochodów lub null w przypadku niepowodzenia
     */ 
    public ArrayList<Samochód> przeglądaj() {
        ArrayList<Samochód> oferta = null;
        
        try {
            oferta = Połączenie.pobierzInstancję().przeglądaj();
        } catch (NullPointerException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Nie udało się wysłać/przetworzyć żądania", "Wystąpił błąd", JOptionPane.ERROR_MESSAGE);            
        }
        
        return oferta;
    }

    /**
     * Metoda pozwalająca na przeglądanie zarezerwowanych samochodów dla wybranego klienta
     * @return kolekcja obieków zarezerwowanych samochodów lub null w przypadku niepowodzenia
     * @param login przesłany login
     */
    public ArrayList<Rezerwacja> przeglądajRezerwacje(String login) {
        ArrayList<Rezerwacja> rezerwacje = null;
        
        try {
            rezerwacje = Połączenie.pobierzInstancję().przeglądajRezerwacje(login);
        } catch (NullPointerException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Nie udało się wysłać/przetworzyć żądania", "Wystąpił błąd", JOptionPane.ERROR_MESSAGE);            
        }
        
        return rezerwacje;
    }

    /**
     * Metoda pozwalająca na przeglądanie wszystkich zarezerwowanych samochodów
     * @return kolekcja obieków zarezerwowanych samochodów lub null w przypadku niepowodzenia
     */
    public ArrayList<Rezerwacja> przeglądajRezerwacje() {
        ArrayList<Rezerwacja> rezerwacje = null;
        
        try {
            rezerwacje = Połączenie.pobierzInstancję().przeglądajRezerwacje();
        } catch (NullPointerException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Nie udało się wysłać/przetworzyć żądania", "Wystąpił błąd", JOptionPane.ERROR_MESSAGE);            
        }
        
        return rezerwacje;
    }

    /**
     * Metoda pozwalająca na anulowanie rezerwacji samochodu
     * @param login przesłany login
     * @param marka przesłana marka samochodu
     * @param model przesłany model samochodu
     * @param kolor przesłany kolor samochodu
     * @param paliwo przesłany rodzaj paliwa samochodu
     * @param produkcja przesłany rok produkcji
     * @param data_odbioru przesłana data początku rezerwacji
     * @param data_zwrotu przesłana data końca rezerwacji 
     */    
    public void anulujRezerwację(String login, String marka, String model, String kolor, String paliwo, int produkcja, String data_odbioru, String data_zwrotu) {
        
        try {
            Połączenie.pobierzInstancję().anulujRezerwację(login, marka, model, kolor, paliwo, produkcja, data_odbioru, data_zwrotu);
        } catch (NullPointerException | IOException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Nie udało się wysłać/przetworzyć żądania", "Wystąpił błąd", JOptionPane.ERROR_MESSAGE);            
        }
    }

    /**
     * Metoda pozwalająca na zarezerwowanie samochodu
     * @param login przesłany login
     * @param marka przesłana marka samochodu
     * @param model przesłany model samochodu
     * @param kolor przesłany kolor samochodu
     * @param paliwo przesłany rodzaj paliwa samochodu
     * @param produkcja przesłany rok produkcji
     * @param data_odbioru przesłana data początku rezerwacji
     * @param data_zwrotu przesłana data końca rezerwacji 
     */    
    public void rezerwuj(String login, String marka, String model, String kolor, String paliwo, int produkcja, String data_odbioru, String data_zwrotu) {
        try {
            Połączenie.pobierzInstancję().rezerwuj(login, marka, model, kolor, paliwo, produkcja, data_odbioru, data_zwrotu);
        } catch (NullPointerException | IOException | ClassNotFoundException ex) {
            ex.printStackTrace(System.err);
            JOptionPane.showMessageDialog(null, "Nie udało się wysłać/przetworzyć żądania", "Wystąpił błąd", JOptionPane.ERROR_MESSAGE);            
        }        
    }
    
    /**
     * Metoda przypisująca referencję do obiektu widoku
     * @param widok obiekt do którego ma być referencja
     */ 
    public void setReference(Widok widok){
        this.widok = widok;
    }     
}
