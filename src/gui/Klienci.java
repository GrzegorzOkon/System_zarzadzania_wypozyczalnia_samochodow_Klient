package gui;

import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Klasa wyświetlająca okno przeglądania rezerwacji z powiązanymi klientami
 * 
 */
public class Klienci extends JFrame{
    private ArrayList<procesor.baza.Rezerwacja> rezerwacje;
    private JFrame ramka;  
    private JScrollPane jScrollPane;
    private JTable jTable;    

    /**
     * Konstruktor klientów
     * @param rezerwacje lista obiektów zarezerwowanych samochodów oraz użytkownków do wyświetlenia
     */    
    public Klienci(ArrayList<procesor.baza.Rezerwacja> rezerwacje) {
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
            new Object [rezerwacje.size()][9],
            new String [] {
                "Marka", "Model", "Kolor", "Paliwo", "Rok", "Imię", "Nazwisko", "Od", "Do"
            }
        ));        
        for (int i = 0; i < rezerwacje.size(); i++) {
            jTable.getModel().setValueAt(rezerwacje.get(i).pobierzSamochód().pobierzMarkę(), i, 0);
            jTable.getModel().setValueAt(rezerwacje.get(i).pobierzSamochód().pobierzModel(), i, 1);
            jTable.getModel().setValueAt(rezerwacje.get(i).pobierzSamochód().pobierzKolor(), i, 2);
            jTable.getModel().setValueAt(rezerwacje.get(i).pobierzSamochód().pobierzPaliwo(), i, 3);
            jTable.getModel().setValueAt(rezerwacje.get(i).pobierzSamochód().pobierzRok(), i, 4);
            jTable.getModel().setValueAt(rezerwacje.get(i).pobierzUżytkownika().pobierzImię(), i, 5);
            jTable.getModel().setValueAt(rezerwacje.get(i).pobierzUżytkownika().pobierzNazwisko(), i, 6);
            jTable.getModel().setValueAt(rezerwacje.get(i).pobierzDatęOdbioru(), i, 7);
            jTable.getModel().setValueAt(rezerwacje.get(i).pobierzDatęZwrotu(), i, 8);
        }   
        jTable.setEnabled(false);
        jScrollPane.setViewportView(jTable);
        
        ramka.getContentPane().add(jScrollPane, java.awt.BorderLayout.CENTER);
        
        //Określenie rozmiaru okenka oraz jego wyświetlenie
        ramka.setSize(700,300);
        ramka.setLocationRelativeTo(null);   //Ustawienie na środku ekranu
        ramka.setVisible(true);
    }
}
