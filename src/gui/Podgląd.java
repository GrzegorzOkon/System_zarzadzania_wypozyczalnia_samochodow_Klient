package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import procesor.baza.Samochód;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Klasa wyświetlająca podgląd zarezerwowanych samochodów zalogowanego użytkownika
 * 
 */
public class Podgląd extends JFrame{
    private ArrayList<procesor.baza.Rezerwacja> rezerwacje;
    private JFrame ramka;  
    private JScrollPane jScrollPane;
    private JTable jTable;    

    /**
     * Konstruktor podglądu
     * @param rezerwacje lista obiektów zarezerwowanych samochodów przez zalogowanego użytkownika
     */    
    public Podgląd(ArrayList<procesor.baza.Rezerwacja> rezerwacje) {
        this.rezerwacje = rezerwacje;
    
        ramka = new JFrame("Zarezerwowane samochody");        
        jScrollPane = new JScrollPane();
        jTable = new JTable();
        
        //Generuje wygląd okienka
        ramka.setUndecorated(true);
        ramka.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        ramka.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   
        ramka.setResizable(false);               
        
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [rezerwacje.size()][7],
            new String [] {
                "Marka", "Model", "Kolor", "Paliwo", "Produkcja", "Odbiór", "Zwrot"
            }
        ));        
        for (int i = 0; i < rezerwacje.size(); i++) {
            jTable.getModel().setValueAt(rezerwacje.get(i).pobierzSamochód().pobierzMarkę(), i, 0);
            jTable.getModel().setValueAt(rezerwacje.get(i).pobierzSamochód().pobierzModel(), i, 1);
            jTable.getModel().setValueAt(rezerwacje.get(i).pobierzSamochód().pobierzKolor(), i, 2);
            jTable.getModel().setValueAt(rezerwacje.get(i).pobierzSamochód().pobierzPaliwo(), i, 3);
            jTable.getModel().setValueAt(rezerwacje.get(i).pobierzSamochód().pobierzRok(), i, 4);
            jTable.getModel().setValueAt(rezerwacje.get(i).pobierzDatęOdbioru(), i, 5);
            jTable.getModel().setValueAt(rezerwacje.get(i).pobierzDatęZwrotu(), i, 6);
        }   
        jTable.setEnabled(false);
        jScrollPane.setViewportView(jTable);
        
        ramka.getContentPane().add(jScrollPane, java.awt.BorderLayout.CENTER);
        
        //Określenie rozmiaru okenka oraz jego wyświetlenie
        ramka.setSize(600,300);
        ramka.setLocationRelativeTo(null);   //Ustawienie na środku ekranu
        ramka.setVisible(true);
    }
}
