package it.unisa.bd.progetto;

import java.util.ArrayList;

public class BoxOpt {
    public ArrayList<Option> list;


    public BoxOpt() {
        list = new ArrayList<>();
    }

    public void addBoxOpt(Option opt) {
        if (list.contains(opt)) {
            throw new RuntimeException("Operazione già contenuta");
        }

        list.add(opt);
    }

    public void addBoxOpt(String str) {
        Option opt = new Option(str);
        addBoxOpt(opt);
    }

}
