package utils;

import java.sql.SQLException;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

import models.*;

public class Model {
    private String databaseUrl = "jdbc:sqlite:/home/pepe/Documentos/sqlite/db_accesos.db";
    private Dao<?, String> dao;
    private String databaseUser = "";
    private String databasePassword = "";

    public Model(String modelString){
        try {
            Class model = Class.forName("models." + modelString);
            ConnectionSource connectionSource = new JdbcConnectionSource(this.databaseUrl);
            this.dao = DaoManager.createDao(connectionSource, model);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void setDao(String modelString){
        try {
            Class model = Class.forName("pe.softweb.models." + modelString);
            ConnectionSource connectionSource = new JdbcConnectionSource(this.databaseUrl);
            this.dao = DaoManager.createDao(connectionSource, model);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Dao<?, String> getDao() {
        return dao;
    }
}
