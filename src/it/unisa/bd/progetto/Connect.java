package it.unisa.bd.progetto;

import java.sql.*;

public class Connect {
    private Connection con;
    private String name_db;
    private String user;
    public static final String addr = "jdbc:mysql://";
    public int port;
    public static final String default_url = "jdbc:mysql://localhost:3306/";
    private String url;

    public Connect(String name_db, String user, String password) {
        this.name_db = name_db;
        this.user = user;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // usa l'url di default se non è specificato
            this.url = default_url + name_db;
            this.con = DriverManager.getConnection(url, user, password);
            //System.out.println("Connessione OK \n");
        }
        catch(Exception e) {
            System.out.println("Connessione Fallita\n");
            System.out.println(e);
        }
    }

    public Connect(String name_db, String user, String password, String addr, int port) {
        this.name_db = name_db;
        this.user = user;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // usa l'url di default se non è specificato
            this.url = Connect.addr + addr + ":" + port + "/" + name_db;
            this.con = DriverManager.getConnection(url, user, password);
            //System.out.println("Connessione OK \n");
        }
        catch(Exception e) {
            System.out.println("Connessione Fallita\n");
            System.out.println(e);
        }
    }

    public void disconnect() throws SQLException {
        con.close();
    }

    public Connection getCon() {
        return con;
    }

    public String getName_db() {
        return name_db;
    }

    public String getUser() {
        return user;
    }

    public int getPort() {
        return port;
    }

    public String getUrl() {
        return url;
    }
}
