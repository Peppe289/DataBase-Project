package it.unisa.bd.progetto;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class ProgettoGUI {
    private JComboBox optList;
    private JButton setBtn;
    private JPanel setCmd;
    private JPanel inputPanel;
    private JTextField inputTxt1;
    private JTextField inputTxt2;
    private JTextField inputTxt3;
    private JTextField inputTxt4;
    private JTextField inputTxt5;
    private JButton caricaButton;
    private JTextArea outputArea;
    private JPanel outputPanel;
    private JPanel start;
    private Connect connect;

    public ProgettoGUI() {
        connect = new Connect("progetto", "root", "password");
        BoxOpt opt = Operation.createDefaultOpt();

        optList.addItem(opt.getIndex(0));

        optList.addItem(opt.getIndex(1));

        outputPanel.setVisible(false);
        inputPanel.setVisible(false);


        setBtn.addActionListener(e -> {
            ItemBox ib = (ItemBox) optList.getItemAt(optList.getSelectedIndex());
            //System.out.println(ib.getCmd());

            /**
             * Solo con SELECT dobbiamo togliere l'input e lasciare l'output
             */
            outputPanel.setVisible(ib.getType() == ItemBox.Type.SELECT);
            inputPanel.setVisible(!(ib.getType() == ItemBox.Type.SELECT));

            if (ItemBox.Type.SELECT == ib.getType()) {
                System.out.println(ib.getCmd());
                try {
                    ResultSet rs = Operation.runOperation(connect, ib);
                    outputArea.setText("");
                    /**
                     * Prendi i metadati per poter capire quante colonne stampare
                     */
                    ResultSetMetaData metadata = rs.getMetaData();
                    int column = metadata.getColumnCount();
                    while (rs.next()) {
                        /**
                         * Da ricordarsi che getString da la colonna nella riga corrente:
                         * Il numero di colonne parte da 1
                         */
                        for (int i = 1; i <= column; ++i) {
                            outputArea.append(rs.getString(i) + (i == column ? " " : " - "));
                        }

                        outputArea.append("\n");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //optList.addItem(opt.getIndex(0));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("ProgettoGUI");
        frame.setContentPane(new ProgettoGUI().start);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.setSize(new Dimension(300, 400));
        frame.setPreferredSize(new Dimension(800, 400));
        frame.pack();
        frame.setVisible(true);
    }
}
