package it.unisa.bd.progetto;

import java.sql.*;
import java.util.Scanner;

class Connessione {
    public static void main(String args[]) throws Exception {
        Connect connect = new Connect("progetto", "root", "password");

        String InsertQuery = "select Descrizione, marca\n" +
                "from PRODOTTO \n" +
                "where (sigla in('Edilizia','Giardinaggio')) and dimensione < 30;";

        try (PreparedStatement statementInsert = connect.getCon().prepareStatement(InsertQuery)) {
            ResultSet rs = statementInsert.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }
        connect.disconnect();
    }
}
