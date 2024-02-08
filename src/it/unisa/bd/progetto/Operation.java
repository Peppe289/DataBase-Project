package it.unisa.bd.progetto;

import com.mysql.cj.x.protobuf.MysqlxPrepare;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Operation {
    public BoxOpt query;

    public Operation() {
        query = new BoxOpt();
    }

    public void addOperationList(String str) {
        query.addBoxOpt(str);
    }

    public ResultSet runOperation(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }

    public ResultSet runOperation(Connect con, Option str) throws SQLException {
        PreparedStatement ps = con.getCon().prepareStatement(str.toString());
        return runOperation(ps);
    }

    public ResultSet runOperation(Connect con, Option str, ArrayList<String> ps_set) throws SQLException {
        PreparedStatement ps = con.getCon().prepareStatement(str.toString());

        // setta lo statement con l'array che abbiamo passato
        for (int i = 0; i != ps_set.size(); ++i) {
            ps.setString(i, ps_set.get(i));
        }

        return runOperation(ps);
    }


}
