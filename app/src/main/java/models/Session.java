package models;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.io.Serializable;

@DatabaseTable(tableName = "sistemas")
public class Session implements Serializable {
    private static final long serialVersionUID = 1L;
    @DatabaseField(id = true)
    private int id;
    @DatabaseField(canBeNull = false, columnName = "llave")
    private String llave;
    @DatabaseField(canBeNull = false, columnName = "valor")
    private String valor;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLlave() {
        return llave;
    }

    public void setLlave(String llave) {
        this.llave = llave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Session{" +
                "id=" + id +
                ", llave='" + llave + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}
