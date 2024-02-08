package it.unisa.bd.progetto;

import java.util.ArrayList;

public class BoxOpt {
    /**
     * Lista dei comandi utilizzabili.
     * Requesiti: non dobbiamo avere duplicati di questi comandi.
     * Accoppiamento con la classe ItemBox
     */
    public ArrayList<ItemBox> list;

    public BoxOpt() {
        list = new ArrayList<>();
    }

    public void addBoxOpt(ItemBox opt) {
        if (list.contains(opt)) {
            throw new RuntimeException("Operazione già contenuta");
        }

        list.add(opt);
    }

    public ItemBox getIndex(int index) {
        return list.get(index);
    }
/*
    public void defaultOption() {
        list.add("select Descrizione, marca\n" +
                "from PRODOTTO \n" +
                "where (sigla in('Edilizia','Giardinaggio')) and dimensione < 30;");
        list.add("select C.Sigla, P.descrizione, P.marca\n" +
                "from PRODOTTO P join CATEGORIA C on C.Sigla = P.sigla\n" +
                "where C.Sigla = 'Edilizia';\n");
        list.add("select sum((P.costo * O.quantita )) AS RicavoTotaleNegozio\n" +
                "from PRODOTTO P join ORDINE O on P.ID_prodotto = O.id_prodotto;");
        list.add("select C.nome, SUM((P.costo * O.quantita )) AS tot\n" +
                "from CLIENTE C join ORDINE O on C.ID_cliente = O.id_cliente\n" +
                "join PRODOTTO P on P.ID_prodotto = O.id_prodotto\n" +
                "group by C.ID_cliente, C.nome\n" +
                "order by C.nome desc;");
        list.add("select P.ID_prodotto, AVG(R.valutazione) as media\n" +
                "from PRODOTTO P join RECENSIONE R on P.ID_prodotto = R.id_prodotto\n" +
                "group by P.ID_prodotto \n" +
                "having media > 5\n" +
                "order by media desc;");
    }*/


}
