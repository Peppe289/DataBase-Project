package it.unisa.bd.progetto;

import javax.swing.*;
import java.awt.*;

public class ProgettoGUI {
    private JComboBox opt;
    private JButton setBtn;
    private JPanel setCmd;
    private JPanel inputGUI;
    private JTextField inputTxt1;
    private JTextField inputTxt2;
    private JTextField inputTxt3;
    private JTextField inputTxt4;
    private JTextField inputTxt5;
    private JButton caricaButton;
    private JTextArea outputArea;
    private JPanel outputPanel;
    private JPanel start;

    public ProgettoGUI() {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ProgettoGUI");
        frame.setContentPane(new ProgettoGUI().start);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(new Dimension(300, 400));
        frame.setPreferredSize(new Dimension(500, 400));
        frame.pack();
        frame.setVisible(true);
    }
}
