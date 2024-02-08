package it.unisa.bd.progetto;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Metodi statici per le operazioni
 */
public class Operation {

    static public ResultSet runOperation(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }

    static public ResultSet runOperation(Connect con, ItemBox item) throws SQLException {
        PreparedStatement ps = con.getCon().prepareStatement(item.getCmd());
        return runOperation(ps);
    }

    static public ResultSet runOperation(Connect con, ItemBox item, ArrayList<String> ps_set) throws SQLException {
        PreparedStatement ps = con.getCon().prepareStatement(item.getCmd());

        // setta lo statement con l'array che abbiamo passato
        for (int i = 0; i != ps_set.size(); ++i) {
            ps.setString(i, ps_set.get(i));
        }

        return runOperation(ps);
    }

    static BoxOpt createDefaultOpt() {
        BoxOpt boxOpt = new BoxOpt();
        boxOpt.addBoxOpt(new ItemBox("Elencare i prodotti di categoria edilizia o giardinaggio  che hanno dimendione minore di 10", "select Descrizione, marca " +
                "from PRODOTTO " +
                "where (sigla in('Edilizia','Giardinaggio')) and dimensione < 30;", ItemBox.Type.SELECT));

        boxOpt.addBoxOpt(new ItemBox("Inserimento dati nella tabella PRODOTTO", "INSERT INTO PRODOTTO (marca, dimensione, costo, descrizione, sigla)" +
                "VALUES ('Bosh', 20.5, 300, 'Trapano a percussione', 'Edilizia')," +
                "('Silverline', 15, 15, 'Cazzuola', 'Edilizia');", ItemBox.Type.INSERT));

        return boxOpt;
    }


}
