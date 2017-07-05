package gui;

import gui.komunikaty.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import kontroler.Kontroler;

/**
 * Klas widoku tj. głównego okna aplikacji
 * 
 */
public class Widok extends JFrame {
    private Kontroler kontroler;
    private JFrame ramka;  
    private JMenuBar pasekMenu;  
    private JMenu file;   
    private JMenuItem quitMenuItem;  
    private JMenu helpMenu;  
    private JMenuItem aboutMenuItem; 
    private JPanel głównyPanel;    
    private JLabel etykietaLoginu;    
    private JTextField poleLoginu;
    private JLabel etykietaHasła;
    private JPasswordField poleHasła;     
    private JButton przyciskZaloguj;
    private JButton przyciskPrzeglądajOfertę;
    
    /**
     * Konstruktor widoku tj. obiektu klasy pojawiającej się zaraz po uruchomieniu
     */
    public Widok() {
        ramka = new JFrame("Wypożyczalnia samochodów");
        pasekMenu = new JMenuBar(); 
        file = new JMenu();
        quitMenuItem = new JMenuItem();
        helpMenu = new JMenu();
        aboutMenuItem = new JMenuItem(); 
        głównyPanel = new JPanel();        
        etykietaLoginu = new JLabel();  
        poleLoginu = new JTextField();        
        etykietaHasła = new JLabel();
        poleHasła = new JPasswordField();
        przyciskZaloguj = new JButton();
        przyciskPrzeglądajOfertę = new JButton();      

        //Generuje wygląd okienka
        ramka.setUndecorated(true);
        ramka.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        ramka.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   
        ramka.setResizable(false);
               
        //Utworzenie menu i podmenu
        ramka.setJMenuBar(pasekMenu);
        
        file.setMnemonic('f');
        file.setText("Plik");        
        pasekMenu.add(file);
        
        quitMenuItem.setMnemonic('x');
        quitMenuItem.setText("Zamknij");
        quitMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                System.exit(0); 
            }
        });        
        file.add(quitMenuItem);
        
        helpMenu.setMnemonic('h');
        helpMenu.setText("Pomoc");        
        pasekMenu.add(helpMenu);
        
        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("O programie...");
        aboutMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae){
                JOptionPane.showMessageDialog(aboutMenuItem, 
                        "Wypożyczalnia samochodów v.1.0\nAutor:\nProjekt na zaliczenie",
                        "O programie\u2026",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });        
        helpMenu.add(aboutMenuItem);        
        
        głównyPanel.setLayout(new java.awt.GridLayout(0, 1, 0, 5));
        
        etykietaLoginu.setHorizontalAlignment(SwingConstants.CENTER);
        etykietaLoginu.setText("Login:");
        głównyPanel.add(etykietaLoginu);
 
        poleLoginu.setHorizontalAlignment(JTextField.CENTER);
        głównyPanel.add(poleLoginu);        

        etykietaHasła.setHorizontalAlignment(SwingConstants.CENTER);
        etykietaHasła.setText("Hasło:");
        głównyPanel.add(etykietaHasła);

        poleHasła.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        głównyPanel.add(poleHasła); 

        przyciskZaloguj.setText("Zaloguj");
        przyciskZaloguj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String login = poleLoginu.getText();
                String hasło = new String(poleHasła.getPassword());
                
                if (!login.isEmpty() && !hasło.isEmpty()) {
                    kontroler.wykonajAkcję(new ZalogujKomunikat(login, hasło));
                } else {
                    JOptionPane.showMessageDialog(null, "Niewypełniono poprawnie pół formularza", "Uwaga", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        głównyPanel.add(przyciskZaloguj);

        przyciskPrzeglądajOfertę.setText("Przeglądaj ofertę");
        przyciskPrzeglądajOfertę.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kontroler.wykonajAkcję(new PrzeglądajNiezalogowanyKomunikat());
            }
        });
        głównyPanel.add(przyciskPrzeglądajOfertę);
        
        głównyPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        //Fragment odpowiadający za rozmieszczenie elementów grafiznych według menedzera rozkładu BorderLayout
        ramka.getContentPane().setLayout(new BorderLayout());
        ramka.getContentPane().add(głównyPanel, BorderLayout.CENTER);        

        //Określenie rozmiaru okenka oraz jego wyświetlenie
        ramka.setSize(300,300);
        ramka.setLocationRelativeTo(null);   //Ustawienie na środku ekranu
        ramka.setVisible(true);
    } 

     /**
     * Metoda pokazująca okno 
     */
    public void pokażWidok() {
        ramka.setVisible(true);
    }

     /**
     * Metoda ukrywająca okno 
     */    
    public void ukryjWidok() {
        ramka.setVisible(false);
    }

    /**
     * Metoda przypisująca referencję do obiektu kontrolera
     * @param kontroler obiekt do którego ma być referencja
     */    
    public void setReference(Kontroler kontroler){
        this.kontroler = kontroler;
    }     
}
