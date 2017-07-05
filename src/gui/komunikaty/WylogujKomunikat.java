package gui.komunikaty;

/**
 * Klasa tworząca komunikat wylogowania po kliknięciu na prycisk wylogowania
 * 
 */
public class WylogujKomunikat extends Komunikat {
    private final String login, hasło;

    /**
     * Konstruktor komunikatu wylogowania
     * @param login login użytkownika
     * @param hasło hasło zalogowanego użytkownika
     */     
    public WylogujKomunikat(String login, String hasło){
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
