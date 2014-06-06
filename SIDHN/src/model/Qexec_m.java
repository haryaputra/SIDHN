package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleDataSource;

public class Qexec_m {
    
    private String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
    private String userid = "DHN";
    private String password = "dhn";
    private Connection conn;

    public Qexec_m() throws SQLException {
        super();
        OracleDataSource ds;
        ds = new OracleDataSource();
        ds.setURL(jdbcUrl);
        conn = ds.getConnection(userid, password);
    }

    // untuk beberapa tupple
    public ResultSet get_some_result (String q, int firstRow,int recordPerPage) throws SQLException
    {
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rset;
        
        String[] split = q.split(" ");
        StringBuilder sb = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
            
        //convert
        int i = 0;
        boolean found = false;
        while (i<split.length){
            if (!split[i].equalsIgnoreCase("FROM")&& !found){
                sb.append(split[i]);
                sb.append(" ");
            }else{
                found = true;
                sb2.append(split[i]);
                sb2.append(" ");
            }
            i++;
        }

        String query = sb.toString()+"FROM ("+sb.toString()+",ROWNUM r "+sb2.toString()+")WHERE r>="+firstRow+" AND r<="+(firstRow+recordPerPage);
        rset = stmt.executeQuery(query);
        return rset;
    }

    // untuk satu tupple
    public ResultSet get_one_result (String q) throws SQLException {
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rset;
        rset = stmt.executeQuery(q);
        if(rset.next()){
            return rset;
        }else{
            return null;
        }
    }

    // untuk semua tupple
    public ResultSet get_all_result (String q) throws SQLException {
        Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rset;
        rset = stmt.executeQuery(q);
        return rset;
    }


}

