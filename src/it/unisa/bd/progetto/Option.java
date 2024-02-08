package it.unisa.bd.progetto;

import java.sql.Connection;

public class Option {
    String option;

    public Option(String opt) {
        option = opt;
    }

    @Override
    public String toString() {
        return option;
    }
}
