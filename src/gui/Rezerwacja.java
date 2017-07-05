package gui;

import com.toedter.calendar.JDateChooser;
import gui.komunikaty.RezerwujKomunikat;
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
 * Klasa wyświetlająca okno rezerwacji samochodu
 * 
 */
public class Rezerwacja extends JFrame {
    private Kontroler kontroler;
    private ArrayList<Samochód> oferta;
    private JFrame ramka;  
    private JPanel głównyPanel;    
    private final DefaultListModel<String> przedmiotyMenuWyboru;   
    private JScrollPane panelPrzewijania;
    private JList menuWyboru;
    private JPanel panelRezerwowania;
    private JPanel panelZakresówDat;
    private JLabel etykietaOdbioru; 
    private JDateChooser poleOdbioru;
    private JLabel etykietaZwrotu; 
    private JDateChooser poleZwrotu;    
    private JButton przyciskRezerwuj; 

    /**
     * Konstruktor rezerwacji
     * @param kontroler referencja do obiektu klasy kontroler
     * @param login zalogowany użytkownik
     * @param oferta lista obiektów dostępnych samochodów
     */    
    public Rezerwacja(Kontroler kontroler, String login, ArrayList<Samochód> oferta) {
        this.kontroler = kontroler;
        this.oferta = oferta;
    
        ramka = new JFrame("Rezerwacja samochodu"); 
        głównyPanel = new JPanel();
        przedmiotyMenuWyboru = new DefaultListModel<>();
        panelPrzewijania = new JScrollPane();  
        menuWyboru = new JList(); 
        panelRezerwowania = new JPanel();
        panelZakresówDat = new JPanel();
        etykietaOdbioru = new JLabel();
        poleOdbioru = new JDateChooser();
        etykietaZwrotu = new JLabel();
        poleZwrotu = new JDateChooser();        
        przyciskRezerwuj = new JButton();
        
        //Generuje wygląd okienka
        ramka.setUndecorated(true);
        ramka.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        ramka.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   
        ramka.setResizable(false);              
        
        głównyPanel.setLayout(new BorderLayout());
        panelRezerwowania.setLayout(new BorderLayout());
        panelZakresówDat.setLayout(new GridLayout(0, 4));
        
        for (int i = 0; i < oferta.size(); i++) {
            przedmiotyMenuWyboru.addElement(oferta.get(i).pobierzMarkę() + " " + oferta.get(i).pobierzModel() + ", " + oferta.get(i).pobierzKolor() + ", " + oferta.get(i).pobierzPaliwo() + ", " + oferta.get(i).pobierzRok());
        }                 
        panelPrzewijania.setPreferredSize(new Dimension(150, 132));
        menuWyboru.setModel(przedmiotyMenuWyboru);
        menuWyboru.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelPrzewijania.setViewportView(menuWyboru);        
        panelRezerwowania.add(panelPrzewijania, BorderLayout.CENTER);       
        
        etykietaOdbioru.setText("Data odbioru: ");
        panelZakresówDat.add(etykietaOdbioru);   
        panelZakresówDat.add(poleOdbioru);
        etykietaZwrotu.setText("  Data zwrotu: ");
        panelZakresówDat.add(etykietaZwrotu);
        panelZakresówDat.add(poleZwrotu);
        panelZakresówDat.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelRezerwowania.add(panelZakresówDat, BorderLayout.SOUTH);
        głównyPanel.add(panelRezerwowania, BorderLayout.CENTER);
        
        przyciskRezerwuj.setText("Rezerwuj");
        przyciskRezerwuj.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {    
                Date odbior = poleOdbioru.getDate();
                Date zwrot = poleZwrotu.getDate();
                
                try {
                    if (menuWyboru.isSelectionEmpty() == false && odbior.before(zwrot) && odbior.after(new Date(new java.util.Date().getTime()-(24*60*60*1000)))) {
                        kontroler.wykonajAkcję(new RezerwujKomunikat(login, oferta.get(menuWyboru.getSelectedIndex()).pobierzMarkę(), oferta.get(menuWyboru.getSelectedIndex()).pobierzModel(), oferta.get(menuWyboru.getSelectedIndex()).pobierzKolor(), oferta.get(menuWyboru.getSelectedIndex()).pobierzPaliwo(), oferta.get(menuWyboru.getSelectedIndex()).pobierzRok(), DateFormat.getDateInstance().format(poleOdbioru.getDate()), DateFormat.getDateInstance().format(poleZwrotu.getDate())));
                    } else {
                        JOptionPane.showMessageDialog(null, "Niewypełniono poprawnie pól formularza", "Uwaga", JOptionPane.WARNING_MESSAGE);
                    }   
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Niewypełniono poprawnie pól formularza", "Uwaga", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        głównyPanel.add(przyciskRezerwuj, BorderLayout.SOUTH);
               
        głównyPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        ramka.getContentPane().add(głównyPanel, java.awt.BorderLayout.CENTER);
        
        //Określenie rozmiaru okenka oraz jego wyświetlenie
        ramka.setSize(400,300);
        ramka.setLocationRelativeTo(null);   //Ustawienie na środku ekranu
        ramka.setVisible(true);
    }  
 
    /**
     * Metoda zamykająca okno 
     */    
    public void zamknijRezerwację() {
        ramka.dispose();
    }    
}
