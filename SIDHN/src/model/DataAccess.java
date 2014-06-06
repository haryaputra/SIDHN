package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.OracleDataSource;

/**
 * @param q
 * @return
 * @throws SQLException
 */
public class DataAccess {
    public DataAccess() {
        super();
    }
    

    String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:XE";
    String userid = "DHN";
    String password = "DHN";
    Connection conn;
    
    Statement stmt;
    ResultSet rset;
    String query;
    String sqlString;

    @SuppressWarnings("oracle.jdeveloper.java.tag-is-missing")
    public void getDBConnection() throws SQLException{
        OracleDataSource ds;
        ds = new OracleDataSource();
        ds.setURL(jdbcUrl);
        conn = ds.getConnection(userid, password);
    }

    /**
     * @param q
     * @return
     * @throws SQLException
     */
    public ResultSet getQuery(String q) throws SQLException{
        getDBConnection();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
        //QueryHandler qh = new QueryHandler();
        //query = qh.getQuery(q);
        query = q;
        //System.out.println("\nExecuting query: "+query);
        rset = stmt.executeQuery(query);
        return rset;
    }

    /**
     * @param q
     * @param firstRow
     * @param recordPerPage
     * @return
     * @throws SQLException
     */
    public ResultSet getSelQuery(String q,int firstRow,int recordPerPage) throws SQLException{
        getDBConnection();
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
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
                }
                else {
                        found = true;
                        sb2.append(split[i]);
                        sb2.append(" ");
                    }
                i++;
                }
            query = sb.toString()+"FROM ("+sb.toString()+",ROWNUM r "+sb2.toString()+")WHERE r>="+firstRow+" AND r<="+(firstRow+recordPerPage);
        rset = stmt.executeQuery(query);
        return rset;
        }

    /**
     * @param rset
     * @return
     * @throws SQLException
     */
    public int getColumnCount(ResultSet rset) throws SQLException{
        
        ResultSetMetaData rmd = rset.getMetaData();
        int count = rmd.getColumnCount();
        return count;
        }

    /**
     * @param rset
     * @return
     * @throws SQLException
     */
    public int getLastRow (ResultSet rset) throws SQLException{
        rset.last();
        return (rset.getRow());
        }

    /**
     * @param rset
     * @param ColNum
     * @return
     * @throws SQLException
     */
    public String getColumnName(ResultSet rset, int ColNum) throws SQLException{
        
        ResultSetMetaData rmd = rset.getMetaData();
        String Name = rmd.getColumnName(ColNum);
        return Name;
        }
}
