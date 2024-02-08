package it.unisa.bd.progetto;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

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
    private JButton avviaBtn;
    private JTextArea outputArea;
    private JPanel outputPanel;
    private JPanel start;
    private Connect connect;
    private ItemBox ib;

    public ProgettoGUI() {
        connect = new Connect("progetto", "root", "password");
        BoxOpt opt = Operation.createDefaultOpt();
        ArrayList<JTextField> inputText = new ArrayList<>();
        inputText.add(inputTxt1);
        inputText.add(inputTxt2);
        inputText.add(inputTxt3);
        inputText.add(inputTxt4);
        inputText.add(inputTxt5);

        for (int i = 0; i < opt.getSize(); ++i) {
            optList.addItem(opt.getIndex(i));
        }
        outputPanel.setVisible(false);
        inputPanel.setVisible(false);


        setBtn.addActionListener(e -> {
            ib = (ItemBox) optList.getItemAt(optList.getSelectedIndex());
            //System.out.println(ib.getCmd());

            /**
             * Solo con SELECT dobbiamo togliere l'input e lasciare l'output
             */
            outputPanel.setVisible(ib.getType() == ItemBox.Type.SELECT);
            inputPanel.setVisible(!(ib.getType() == ItemBox.Type.SELECT));

            if (ItemBox.Type.SELECT == ib.getType()) {
                //System.out.println(ib.getCmd());
                try {
                    ResultSet rs = Operation.runSelect(connect, ib);
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
            } else {
                /**
                 * Definiamo quanti input text servono
                 */
                String str = ib.getCmd();
                char find = '?';
                int counter = 0;

                /**
                 * Resettiamo eventuali vecchi input
                 */
                for (JTextField tmp : inputText) {
                    tmp.setText("");
                    tmp.setVisible(false);
                }

                for (int i = 0; i < str.length() && counter < inputText.size(); i++) {
                    if (str.charAt(i) == find) {
                        inputText.get(counter).setVisible(true);
                        counter++;
                    }
                }
            }
        });

        avviaBtn.addActionListener(e -> {
            if (ItemBox.Type.INSERT == ib.getType()) {
                try {
                    ArrayList<String> str = new ArrayList<>();

                    /**
                     * Prendimao gli input per quanti servono
                     */
                    String string = ib.getCmd();
                    char find = '?';
                    int counter = 0;

                    for (int i = 0; i < string.length() && counter < inputText.size(); i++) {
                        if (string.charAt(i) == find) {
                            str.add(inputText.get(counter).getText());
                            counter++;
                        }
                    }

                    int ret = Operation.runUpdate(connect, ib, str);

                    if (ret > 0) {
                        System.out.println("Dato inserito con successo");
                    } else {
                        throw new RuntimeException("Errore nell'input");
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
