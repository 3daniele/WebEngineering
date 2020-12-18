package it.FRED.GuidaTv.framework.data;

import java.sql.Connection;

public class DAO {

    protected final DataLayer dataLayer;
    protected final Connection connection;

    public DAO(DataLayer d) {
        this.dataLayer = d;
        this.connection = d.getConnection();
    }

    public DAO() {
        this.dataLayer = null;
        this.connection = null;
    }

    protected DataLayer getDataLayer() {
        return dataLayer;
    }

    protected Connection getConnection() {
        return connection;
    }

    public void init() throws DataException {

    }

    public void destroy() throws DataException {

    }
}
