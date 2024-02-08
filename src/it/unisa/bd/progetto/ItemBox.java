package it.unisa.bd.progetto;

public class ItemBox {
    /**
     * @name: Nome mostrato sul JComboBox
     * @cmd: Comando effettivo
     * @type: Tipo di comando
     */
    public String name;
    public String cmd;

    enum Type {
        SELECT,
        INSERT
    }

    Type type; // tipo di comando

    public ItemBox(String name, String cmd, Type type) {
        this.name = name;
        this.cmd = cmd;
        this.type = type;
    }

    public String getCmd() {
        return cmd;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return name;
    }
}
