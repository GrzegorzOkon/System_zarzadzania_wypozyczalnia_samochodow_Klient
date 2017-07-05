/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.komunikaty.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;
import kontroler.Kontroler;
import procesor.baza.*;

/**
 * Klasa wyświetlająca okno profilu po zalogowaniu użytkownika
 * 
 */
public class Profil extends JFrame {
    private Kontroler kontroler;
    private JFrame ramka;  
    private JMenuBar pasekMenu;  
    private JMenu file;   
    private JMenuItem quitMenuItem;  
    private JMenu helpMenu;  
    private JMenuItem aboutMenuItem;   
    private JPanel głównyPanel; 
    private JButton przyciskWyloguj;
    private JPanel panelŚrodkowy;
    private JPanel panelLewy;    
    private JLabel wiadomośćPowitalna;
    private JButton przyciskSzczegóły; 
    private JPanel panelPrawy;
    private JLabel etykietaMenu;
    private JPanel panelWyboru;
    private final DefaultListModel<String> przedmiotyMenuWyboru;   
    private JScrollPane panelPrzewijania;
    private JList menuWyboru;
    private JButton przyciskWybierz;    
    
    /**
     * Konstruktor profilu
     * @param kontroler referencja do obiektu klasy kontroler
     * @param użytkownik zalogowany użytkownik
     */
    public Profil(Kontroler kontroler, Użytkownik użytkownik) {
        this.kontroler = kontroler;
        
        ramka = new JFrame("Wypożyczalnia samochodów"); 
        pasekMenu = new JMenuBar(); 
        file = new JMenu();
        quitMenuItem = new JMenuItem();
        helpMenu = new JMenu();
        aboutMenuItem = new JMenuItem();
        głównyPanel = new JPanel(); 
        przyciskWyloguj = new JButton();  
        panelŚrodkowy = new JPanel();
        panelLewy = new JPanel();   
        wiadomośćPowitalna = new JLabel(); 
        przyciskSzczegóły = new JButton(); 
        panelPrawy = new JPanel();
        etykietaMenu = new JLabel(); 
        panelWyboru = new JPanel();
        przedmiotyMenuWyboru = new DefaultListModel<>();
        panelPrzewijania = new JScrollPane();  
        menuWyboru = new JList();        
        przyciskWybierz = new JButton();         

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
        aboutMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JOptionPane.showMessageDialog(aboutMenuItem, 
                        "Wypożyczalnia samochodów v.1.0\nAutor:\nProjekt na zaliczenie",
                        "O programie\u2026",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });        
        helpMenu.add(aboutMenuItem);        
        
        głównyPanel.setLayout(new BorderLayout());
        panelŚrodkowy.setLayout(new java.awt.GridLayout(1, 2, 10, 0));        
        panelLewy.setLayout(new java.awt.GridLayout(0, 1, 0, 10));

        wiadomośćPowitalna.setHorizontalAlignment(SwingConstants.CENTER);
        wiadomośćPowitalna.setText("Witaj " + użytkownik.pobierzImię());
        panelLewy.add(wiadomośćPowitalna);
        
        przyciskSzczegóły.setText("Wyświetl szczegóły");
        przyciskSzczegóły.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {                                                
                String wiadomość = "Jesteś zalogowany jako: " + użytkownik.pobierzImię() + " " + użytkownik.pobierzNazwisko() + "\n";
                wiadomość += "Adres zamieszkania: " + użytkownik.pobierzAdres().toString() + "\n";
                if (użytkownik instanceof Pracownik) {
                    Pracownik pracownik = (Pracownik) użytkownik;
                    wiadomość += "Stanowisko: " + pracownik.pobierzStanowisko().pobierzNazwę() + "\n";
                    wiadomość += "Wynagrodzenie: " + pracownik.pobierzStanowisko().pobierzWynagrodzenie() + "\n";
                }
                JOptionPane.showMessageDialog(null, wiadomość, "Szczegółowe informacje na temat konta", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        panelLewy.add(przyciskSzczegóły);   
        
        panelPrawy.setLayout(new BorderLayout());

        etykietaMenu.setFont(new java.awt.Font("Tahoma", 1, 11));
        etykietaMenu.setHorizontalAlignment(SwingConstants.CENTER);
        etykietaMenu.setText("Menu");
        panelPrawy.add(etykietaMenu, BorderLayout.PAGE_START);

        // dodajemy opcję dla poszczególnych pracowników
        if (użytkownik instanceof Pracownik) {
            przedmiotyMenuWyboru.addElement("Przeglądaj ofertę");
            przedmiotyMenuWyboru.addElement("Przeglądaj klientów"); 
        } else {
            przedmiotyMenuWyboru.addElement("Przeglądaj ofertę");
            przedmiotyMenuWyboru.addElement("Rezerwuj samochód"); 
            przedmiotyMenuWyboru.addElement("Przeglądaj rezerwacje");
            przedmiotyMenuWyboru.addElement("Anuluj rezerwację");
        }
        
        panelWyboru.setLayout(new java.awt.BorderLayout());
        panelPrzewijania.setPreferredSize(new Dimension(150, 132));
        menuWyboru.setModel(przedmiotyMenuWyboru);
        menuWyboru.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelPrzewijania.setViewportView(menuWyboru);
        panelWyboru.add(panelPrzewijania, BorderLayout.CENTER);         
        panelPrawy.add(panelWyboru, java.awt.BorderLayout.CENTER);  
        
        przyciskWybierz.setText("Wybierz");
        przyciskWybierz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                String wybraneMenu = (String) menuWyboru.getSelectedValue();
                switch (wybraneMenu) {
                    case "Przeglądaj ofertę":
                        kontroler.wykonajAkcję(new PrzeglądajZalogowanyKomunikat());
                        break;
                    case "Rezerwuj samochód":
                        kontroler.wykonajAkcję(new RezerwujKomunikat(użytkownik.pobierzLogin()));
                        break;
                    case "Przeglądaj rezerwacje":
                        kontroler.wykonajAkcję(new PrzeglądajRezerwacjeKomunikat(użytkownik.pobierzLogin()));
                        break;    
                    case "Anuluj rezerwację":
                        kontroler.wykonajAkcję(new AnulujRezerwacjęKomunikat(użytkownik.pobierzLogin()));
                        break;    
                    case "Przeglądaj klientów":
                        kontroler.wykonajAkcję(new PrzeglądajKlientówKomunikat());
                        break;                         
                }
            }
        });
        panelPrawy.add(przyciskWybierz, java.awt.BorderLayout.PAGE_END);  
        
        panelŚrodkowy.add(panelLewy);
        panelŚrodkowy.add(panelPrawy);
        
        panelŚrodkowy.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        głównyPanel.add(panelŚrodkowy, BorderLayout.CENTER);

        przyciskWyloguj.setText("Wyloguj");
        przyciskWyloguj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kontroler.wykonajAkcję(new WylogujKomunikat(użytkownik.pobierzLogin(), użytkownik.pobierzHasło()));
            }
        });
        głównyPanel.add(przyciskWyloguj, BorderLayout.SOUTH);
        
        głównyPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        //Fragment odpowiadający za rozmieszczenie elementów grafiznych według menedzera rozkładu BorderLayout
        ramka.getContentPane().setLayout(new BorderLayout());
        ramka.getContentPane().add(głównyPanel, BorderLayout.CENTER); 

        //Określenie rozmiaru okenka oraz jego wyświetlenie
        ramka.setSize(400,300);
        ramka.setLocationRelativeTo(null);   //Ustawienie na środku ekranu
        ramka.setVisible(true);
    }

    /**
     * Metoda zamykająca okno 
     */     
    public void zamknijProfil() {
        ramka.dispose();
    }
}