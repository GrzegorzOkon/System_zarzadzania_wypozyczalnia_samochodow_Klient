package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import procesor.baza.Samochód;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Klasa wyświetlająca okno dostępnej oferty
 * 
 */
public class Oferta extends JFrame{
    private ArrayList<Samochód> oferta;
    private JFrame ramka;  
    private JScrollPane jScrollPane;
    private JTable jTable;    

    /**
     * Konstruktor oferty
     * @param oferta lista obiektów samochodów dostępnych do zarezerwowania
     * @param sortowanie znacznik umożliwiający włączenie lub wyłączenie sortowania danych po kolumnach
     */    
    public Oferta(ArrayList<Samochód> oferta, boolean sortowanie) {
        this.oferta = oferta;
    
        ramka = new JFrame("Dostępne samochody");        
        jScrollPane = new JScrollPane();
        jTable = new JTable();
        
        //Generuje wygląd okienka
        ramka.setUndecorated(true);
        ramka.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
        ramka.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);   
        ramka.setResizable(false);               
        
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [oferta.size()][5],
            new String [] {
                "Marka", "Model", "Kolor", "Paliwo", "Produkcja"
            }
        ));        
        for (int i = 0; i < oferta.size(); i++) {
            jTable.getModel().setValueAt(oferta.get(i).pobierzMarkę(), i, 0);
            jTable.getModel().setValueAt(oferta.get(i).pobierzModel(), i, 1);
            jTable.getModel().setValueAt(oferta.get(i).pobierzKolor(), i, 2);
            jTable.getModel().setValueAt(oferta.get(i).pobierzPaliwo(), i, 3);
            jTable.getModel().setValueAt(oferta.get(i).pobierzRok(), i, 4);
        }   
        if (sortowanie == true) {
            jTable.setAutoCreateRowSorter(true);
        }
        jTable.setEnabled(false);
        jScrollPane.setViewportView(jTable);
        
        ramka.getContentPane().add(jScrollPane, java.awt.BorderLayout.CENTER);
        
        //Określenie rozmiaru okenka oraz jego wyświetlenie
        ramka.setSize(400,300);
        ramka.setLocationRelativeTo(null);   //Ustawienie na środku ekranu
        ramka.setVisible(true);
    }
}
