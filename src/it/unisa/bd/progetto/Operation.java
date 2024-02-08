package it.unisa.bd.progetto;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * Metodi statici per le operazioni
 */
public class Operation {

    static public int runUpdate(PreparedStatement ps) throws SQLException {
        return ps.executeUpdate();
    }

    static public int runUpdate(Connect con, ItemBox item) throws SQLException {
        PreparedStatement ps = con.getCon().prepareStatement(item.getCmd());
        return runUpdate(ps);
    }

    static public int runUpdate(Connect con, ItemBox item, ArrayList<String> ps_set) throws SQLException {
        PreparedStatement ps = con.getCon().prepareStatement(item.getCmd());

        // setta lo statement con l'array che abbiamo passato
        for (int i = 1; i <= ps_set.size(); ++i) {
            ps.setString(i, ps_set.get(i - 1));
        }

        return runUpdate(ps);
    }

    static public ResultSet runSelect(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }

    static public ResultSet runSelect(Connect con, ItemBox item) throws SQLException {
        PreparedStatement ps = con.getCon().prepareStatement(item.getCmd());
        return runSelect(ps);
    }

    static public ResultSet runSelect(Connect con, ItemBox item, ArrayList<String> ps_set) throws SQLException {
        PreparedStatement ps = con.getCon().prepareStatement(item.getCmd());

        // setta lo statement con l'array che abbiamo passato
        for (int i = 1; i <= ps_set.size(); ++i) {
            ps.setString(i, ps_set.get(i - 1));
        }

        return runSelect(ps);
    }

    static BoxOpt createDefaultOpt() {
        BoxOpt boxOpt = new BoxOpt();
        boxOpt.addBoxOpt(new ItemBox("Elencare i prodotti di categoria edilizia o giardinaggio  che hanno dimendione minore di 10", "select Descrizione, marca\n" +
                "from PRODOTTO \n" +
                "where (sigla in('Edilizia','Giardinaggio')) and dimensione < 30;", ItemBox.Type.SELECT));

        boxOpt.addBoxOpt(new ItemBox("Elencare i prodotti di categoria edilizia", "select C.Sigla, P.descrizione, P.marca\n" +
                "from PRODOTTO P join CATEGORIA C on C.Sigla = P.sigla\n" +
                "where C.Sigla = 'Edilizia';", ItemBox.Type.SELECT));

        boxOpt.addBoxOpt(new ItemBox("Ricavato totale negozio", "select sum((P.costo * O.quantita )) AS RicavoTotaleNegozio\n" +
                "from PRODOTTO P join ORDINE O on P.ID_prodotto = O.id_prodotto;", ItemBox.Type.SELECT));

        boxOpt.addBoxOpt(new ItemBox("Elencare la spesa totale per ogni cliente", "select C.nome, SUM((P.costo * O.quantita )) AS tot\n" +
                "from CLIENTE C join ORDINE O on C.ID_cliente = O.id_cliente\n" +
                "join PRODOTTO P on P.ID_prodotto = O.id_prodotto\n" +
                "group by C.ID_cliente, C.nome\n" +
                "order by C.nome desc;", ItemBox.Type.SELECT));

        boxOpt.addBoxOpt(new ItemBox("Elencare i prodotti con media recensioni maggiori di 5", "select P.ID_prodotto, AVG(R.valutazione) as media\n" +
                "from PRODOTTO P join RECENSIONE R on P.ID_prodotto = R.id_prodotto\n" +
                "group by P.ID_prodotto \n" +
                "having media > 5\n" +
                "order by media desc;", ItemBox.Type.SELECT));

        boxOpt.addBoxOpt(new ItemBox("Elencare prodotti di marca Bosh e Silverline", "select descrizione, marca \n" +
                "from PRODOTTO P\n" +
                "where  marca = 'Bosh'\n" +
                "union\n" +
                "select descrizione, marca \n" +
                "from PRODOTTO P\n" +
                "where  marca = 'Silverline';", ItemBox.Type.SELECT));

        boxOpt.addBoxOpt(new ItemBox("Elencare il nome e la mail dei clienti che hanno recensito tutti i prodotti dando una valutazione maggiore o uguale di 6", "SELECT  nome , email\n" +
                "FROM CLIENTE C\n" +
                "where not exists (\n" +
                "\tselect *\n" +
                "\tfrom PRODOTTO P\n" +
                "\twhere not exists (\n" +
                "\t\tselect *\n" +
                "        from RECENSIONE R\n" +
                "        where R.id_cliente = C.ID_cliente and R.id_prodotto = P.ID_prodotto AND R.valutazione > 5\n" +
                "\t)\n" +
                ");", ItemBox.Type.SELECT));

        boxOpt.addBoxOpt(new ItemBox("Inserimento dati nella tabella PRODOTTO", "INSERT INTO PRODOTTO (ID_prodotto, marca, dimensione, costo, descrizione, sigla)" +
                "VALUES (?, 'Bosh', 20.5, 300, 'Trapano a percussione', 'Edilizia');", ItemBox.Type.INSERT));

        return boxOpt;
    }


}
