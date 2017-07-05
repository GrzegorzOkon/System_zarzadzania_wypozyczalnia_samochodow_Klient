package gui;

import gui.komunikaty.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.*;
import java.text.*;
import java.time.LocalDate;
import java.util.Date;
import kontroler.Kontroler;
import procesor.baza.*;

/**
 * Klasa wyświetlająca okno anulowania
 * 
 */
public class Anulowanie extends JFrame {
    private Kontroler kontroler;
    private ArrayList<procesor.baza.Rezerwacja> rezerwacje;
    private JFrame ramka;  
    private JPanel głównyPanel;  
    private final DefaultListModel<String> przedmiotyMenuWyboru; 
    private JScrollPane panelPrzewijania;
    private JList menuWyboru;  
    private JButton przyciskAnuluj; 

    /**
     * Konstruktor anulowania
     * @param kontroler referencja do obiektu klasy kontroler
     * @param login zalogowany użytkownik
     * @param rezerwacje lista obiektów zarezerwowanych samochodów do wyświetlenia
     */
    public Anulowanie(Kontroler kontroler, String login, ArrayList<procesor.baza.Rezerwacja> rezerwacje) {
        this.kontroler = kontroler;
        this.rezerwacje = rezerwacje;
    
        ramka = new JFrame("Anulowanie rezerwacji"); 
        głównyPanel = new JPanel();
        przedmiotyMenuWyboru = new DefaultListModel<>();
        panelPrzewijania = new JScrollPane();  
        menuWyboru = new JList();        
        przyciskAnuluj = new JButton();
        
        //Generuje wygląd okienka
        ramka.setUndecorated(true);
        ramka.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        ramka.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   
        ramka.setResizable(false);              
        
        głównyPanel.setLayout(new BorderLayout());
        
        for (int i = 0; i < rezerwacje.size(); i++) {
            przedmiotyMenuWyboru.addElement(rezerwacje.get(i).pobierzSamochód().pobierzMarkę() + " " + rezerwacje.get(i).pobierzSamochód().pobierzModel() + ", " + rezerwacje.get(i).pobierzSamochód().pobierzKolor() + ", " + rezerwacje.get(i).pobierzSamochód().pobierzPaliwo() + ", " + rezerwacje.get(i).pobierzSamochód().pobierzRok() + ", od " + rezerwacje.get(i).pobierzDatęOdbioru() + " do " + rezerwacje.get(i).pobierzDatęZwrotu());
        }                 
        panelPrzewijania.setPreferredSize(new Dimension(150, 132));
        menuWyboru.setModel(przedmiotyMenuWyboru);
        menuWyboru.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelPrzewijania.setViewportView(menuWyboru);        
        głównyPanel.add(panelPrzewijania, BorderLayout.CENTER);       
        
        przyciskAnuluj.setText("Anuluj");
        przyciskAnuluj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {                    
                if (menuWyboru.isSelectionEmpty() == false) {
                    kontroler.wykonajAkcję(new AnulujKomunikat(login, rezerwacje.get(menuWyboru.getSelectedIndex()).pobierzSamochód().pobierzMarkę(), rezerwacje.get(menuWyboru.getSelectedIndex()).pobierzSamochód().pobierzModel(), rezerwacje.get(menuWyboru.getSelectedIndex()).pobierzSamochód().pobierzKolor(), rezerwacje.get(menuWyboru.getSelectedIndex()).pobierzSamochód().pobierzPaliwo(), rezerwacje.get(menuWyboru.getSelectedIndex()).pobierzSamochód().pobierzRok(), rezerwacje.get(menuWyboru.getSelectedIndex()).pobierzDatęOdbioru().toString(), rezerwacje.get(menuWyboru.getSelectedIndex()).pobierzDatęZwrotu().toString()));
                } else {
                    JOptionPane.showMessageDialog(null, "Niewybrano samochodu do anulowania rezerwacji", "Uwaga", JOptionPane.WARNING_MESSAGE);
                }   
            }
        });
        głównyPanel.add(przyciskAnuluj, BorderLayout.SOUTH);
               
        głównyPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        ramka.getContentPane().add(głównyPanel, java.awt.BorderLayout.CENTER);
        
        //Określenie rozmiaru okenka oraz jego wyświetlenie
        ramka.setSize(500,300);
        ramka.setLocationRelativeTo(null);   //Ustawienie na środku ekranu
        ramka.setVisible(true);
    }  

    /**
     * Metoda zamykająca okno 
     */      
    public void zamknijAnulowanie() {
        ramka.dispose();
    }    
}
