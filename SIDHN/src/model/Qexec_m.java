package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleDataSource;

public class Qexec_m {
    
    private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
    private String userid = "DHN";
    private String password = "DHN";
    private Connection conn;
    private Statement stmt;

    public Qexec_m() {
        super();
    }

    /**
     * @return
     * @throws SQLException
     */
    public Statement getDBConn() throws SQLException{
        OracleDataSource ds;
        ds = new OracleDataSource();
        ds.setURL(jdbcUrl);
        conn = ds.getConnection(userid, password);
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        return stmt;
    }
}

