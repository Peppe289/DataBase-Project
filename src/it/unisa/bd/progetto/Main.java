package it.unisa.bd.progetto;

import java.sql.*;
class Connessione {
    public static void main(String args[]) throws Exception {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/progetto";
            Connection con = DriverManager.getConnection(url, "root",
                    "password");
            System.out.println("Connessione OK \n");

            String sql = "select descrizione, marca " +
                    "from PRODOTTO P " +
                    "where  marca = 'Bosh'" +
                    "union " +
                    "select descrizione, marca " +
                    "from PRODOTTO P " +
                    "where  marca = 'Silverline';";

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2));
            }

            con.close();
        }
        catch(Exception e) {
            System.out.println("Connessione Fallita \n");
            System.out.println(e);
        }
    }
}
