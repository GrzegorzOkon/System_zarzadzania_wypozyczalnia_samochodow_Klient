package gui.komunikaty;

/**
 * Klasa tworząca komunikat przeglądania rezerwacji po wybraniu przycisku przeglądania rezerwacji
 * 
 */
public class PrzeglądajRezerwacjeKomunikat extends Komunikat {
    private final String login;

    /**
     * Konstruktor komunikatu przeglądania rezerwacji
     * @param login zalogowany użytkownik
     */     
    public PrzeglądajRezerwacjeKomunikat(String login){
        this.login = login;
    } 
    
    public String pobierzLogin() {
        return login;
    }     
}
