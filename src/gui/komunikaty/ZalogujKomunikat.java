package gui.komunikaty;

/**
 * Klasa tworząca komunikat zalogowania po kliknięciu na prycisk zalogowania
 * 
 */
public class ZalogujKomunikat extends Komunikat {
    private final String login, hasło;

    /**
     * Konstruktor komunikatu zaloguj
     * @param login zalogowany użytkownik
     * @param hasło przesłane hasło
     */     
    public ZalogujKomunikat(String login, String hasło){
        this.login = login;
        this.hasło = hasło;
    } 
    
    public String pobierzLogin() {
        return login;
    }
    
    public String pobierzHasło() {
        return hasło;
    }    
}
