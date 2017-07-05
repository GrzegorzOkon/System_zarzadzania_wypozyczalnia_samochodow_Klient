package gui.komunikaty;

/**
 * Klasa tworząca komunikat anulowania rezerwacji po wybraniu opcji anulowania rezeracji
 * 
 */
public class AnulujRezerwacjęKomunikat extends Komunikat {
    private final String login;

    /**
     * Konstruktor komunikatu anulowania rezerwacji
     * @param login zalogowany użytkownik
     */     
    public AnulujRezerwacjęKomunikat(String login){
        this.login = login;
    } 
    
    public String pobierzLogin() {
        return login;
    }     
}
