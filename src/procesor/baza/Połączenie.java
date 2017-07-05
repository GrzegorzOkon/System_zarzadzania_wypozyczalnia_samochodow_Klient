package procesor.baza;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 * Klasa umożliwiająca połączenie z serwerem za pomocą protokołu TCP/IP.
 * Wykorzystano wzorzec Singleton.
 */
public class Połączenie extends Thread {  
    private static Połączenie połączenie;
    
    public final static String HOSTNAME = "localhost";
    public final static int PORT = 1300;
    
    // strumienie służące do przesyłania danych
    private ObjectInputStream ois;
    private ObjectOutputStream oos;

    /**
     * Metoda pozwalająca na pobranie instancji obiektu
     * @return zainicjalizowany obiekt klasy 
     * @param marka przesłana marka samochodu
     * @throws IOException
     */     
    public static Połączenie pobierzInstancję() throws IOException {
        if (połączenie == null) {
            połączenie = new Połączenie();
            return połączenie;
        } else {
            return połączenie;
        }
    }

    /**
     * Konstruktor połączenia
     * @throws IOException
     */     
    private Połączenie() throws IOException {
        Socket socket = new Socket(HOSTNAME, PORT);
        ois = new ObjectInputStream(socket.getInputStream());
        oos = new ObjectOutputStream(socket.getOutputStream());
    }
    
    /**
     * Metoda nawiązująca połączenie z serwerem
     * @param login login do konta
     * @param hasło hasło do konta
     * @return zainicjalizowany obiekt użytkownika lub null w przypadku niepowodzenia
     * @throws IOException 
     * @throws java.lang.ClassNotFoundException 
     */
    public Użytkownik zaloguj(String login, String hasło) throws IOException, ClassNotFoundException {
        wyślijWiadomość("Zaloguj<" + login + "/" + hasło);        
        Użytkownik użytkownik = null;
        String wiadomość = odbierzWiadomość();
        
        if (wiadomość.equals("Dane do konta zostały pomyślnie zweryfikowane")) {
            System.out.println("Trwa odbieranie danych z konta użytkownika...");
            użytkownik = (Użytkownik) ois.readObject();
        }
        
        System.out.println(wiadomość);
        
        return użytkownik;
    }    

    /**
     * Metoda pozwalająca na zakończenie wątku oraz wylogowanie się użytkownika
     * @param login przesłany login użytkownika
     * @param hasło przesłane hasło użytkownika
     * @throws java.io.IOException
     */
    public void wyloguj(String login, String hasło) throws IOException {
        wyślijWiadomość("Wyloguj<" + login + "/" + hasło);
        interrupt();
    }
 
    /**
     * Metoda pozwalająca na przeglądanie oferty dostępnych samochodów
     * @return lista obiektów dostępnych samochodów
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException 
     */
    public ArrayList<Samochód> przeglądaj() throws IOException, ClassNotFoundException {
        wyślijWiadomość("Przeglądaj ofertę<");
        ArrayList<Samochód> oferta = null;
        String wiadomość = odbierzWiadomość();
        
        if (wiadomość.equals("Udało się pobrać ofertę")) {
            System.out.println("Trwa odbieranie danych z konta użytkownika...");
            oferta = (ArrayList<Samochód>) ois.readObject();
        }
        
        return oferta;
    }

    /**
     * Metoda pozwalająca na przeglądanie rezerwacji wybranych użytkowników
     * return lista zarezerwowanych obiektów samochodów
     * @param login login zalogowanego użytkownika
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException 
     */
    public ArrayList<Rezerwacja> przeglądajRezerwacje(String login) throws IOException, ClassNotFoundException {
        wyślijWiadomość("Przeglądaj rezerwacje<" + login);
        ArrayList<Rezerwacja> rezerwacje = null;
        String wiadomość = odbierzWiadomość();
        
        if (wiadomość.equals("Udało się pobrać rezerwacje")) {
            System.out.println("Trwa odbieranie danych z konta użytkownika...");
            rezerwacje = (ArrayList<Rezerwacja>) ois.readObject();
        }
        
        return rezerwacje;
    }

    /**
     * Metoda pozwalająca na przeglądanie wszystkich zarezerwowanych samochodów wraz z przypisanymi klientami
     * @return lista obiektów rezerwacji lub null w przypadku niepowodzenia
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */
    public ArrayList<Rezerwacja> przeglądajRezerwacje() throws IOException, ClassNotFoundException {
        wyślijWiadomość("Przeglądaj wszystkie rezerwacje<");
        ArrayList<Rezerwacja> rezerwacje = null;
        String wiadomość = odbierzWiadomość();
        
        if (wiadomość.equals("Udało się pobrać rezerwacje")) {
            System.out.println("Trwa odbieranie danych z konta użytkownika...");
            rezerwacje = (ArrayList<Rezerwacja>) ois.readObject();
        }
        
        return rezerwacje;
    }

    /**
     * Metoda pozwalająca na zarezerwowanie wybranego samochodu przez zalogowanego użytkownika
     * @return lista obiektów rezerwacji lub null w przypadku niepowodzenia
     * @param login przesłany login
     * @param marka przesłana marka samochodu
     * @param model przesłany model samochodu
     * @param kolor przesłany kolor samochodu
     * @param paliwo przesłany rodzaj paliwa samochodu
     * @param produkcja przesłany rok produkcji
     * @param data_odbioru przesłana data początku rezerwacji
     * @param data_zwrotu przesłana data końca rezerwacji 
     * @throws java.io.IOException
     * @throws java.lang.ClassNotFoundException
     */    
    public void rezerwuj(String login, String marka, String model, String kolor, String paliwo, int produkcja, String data_odbioru, String data_zwrotu) throws IOException, ClassNotFoundException {
        wyślijWiadomość("Rezerwuj<" + login + "/" + marka + "/" + model + "/" + kolor + "/" + paliwo + "/" + produkcja + "/" + data_odbioru + "/" + data_zwrotu);
        
        String wiadomość = odbierzWiadomość();
        
        if (wiadomość.equals("Nieudana próba rezerwacji")) {
            System.out.println("Nieudana próba rezerwacji");
        } else if (wiadomość.equals("Zarezerwowano samochód")) {
            System.out.println("Zarezerwowano samochód");
        }
    }

    /**
     * Metoda pozwalająca na anulowanie rezerwacji wybranego samochodu przez zalogowanego użytkownika
     * @param login przesłany login
     * @param marka przesłana marka samochodu
     * @param model przesłany model samochodu
     * @param kolor przesłany kolor samochodu
     * @param paliwo przesłany rodzaj paliwa samochodu
     * @param produkcja przesłany rok produkcji
     * @param data_odbioru przesłana data początku rezerwacji
     * @param data_zwrotu przesłana data końca rezerwacji 
     * @throws java.io.IOException
     */ 
    public void anulujRezerwację(String login, String marka, String model, String kolor, String paliwo, int produkcja, String data_odbioru, String data_zwrotu) throws IOException {
        wyślijWiadomość("Anuluj rezerwację<" + login + "/" + marka + "/" + model + "/" + kolor + "/" + paliwo + "/" + produkcja + "/" + data_odbioru + "/" + data_zwrotu);
    }
    
    /**
     * Metoda umożliwiająca wysłanie łańcucha znaków do strumienia wyjściowego
     * @param wiadomość łańcuch znaków
     * @throws IOException 
     */
    public void wyślijWiadomość(String wiadomość) throws IOException {
        oos.writeObject(wiadomość);
        oos.flush();
    }
    
    /**
     * Metoda umożliwiająca pobranie łańcucha znaków ze strumienia
     * @return łańcuch znaków
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public String odbierzWiadomość() throws IOException, ClassNotFoundException {
        String wiadomość = (String) ois.readObject();
        return wiadomość.trim();
    } 
    
    @Override
    public void run() {
        while (true) {
            try {
                String wiadomość = odbierzWiadomość();
                System.out.println("Odebrano: " + wiadomość);
            } catch (IOException | ClassNotFoundException ex) {
                System.err.println("Wystąpił błąd podczas odbierania wiadomości");
                JOptionPane.showMessageDialog(null, "Serwer nie odpowiada", "Wystąpił błąd", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
        }
    }    
}
