package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Dhn_m {
	private Statement stmt;
	private ResultSet rset = null;
	private String query;

    private Qexec_m eksekutor;


    public Dhn_m() throws SQLException {
    	super();
    	eksekutor = new Qexec_m();
    }

    // mengambil data DHN dengan :
    // - periode
    // - kode kantor bank
    // - kode institusi bank
    // - nama nasabah (like atau exact)

    public ResultSet get_some_dhn (String periode, String kantor_bank, String inst_bank, String nama_nasabah) throws SQLException {

    	boolean firstly = true;
    	query = "SELECT * FROM T_DHN WHERE ";

    	if(!periode.equals("")){
    		if(!firstly){
    			query += "AND ";
    		}else{
                firstly = false;
            }
    		query += "NOMOR_DH='"+periode+"' ";
    	}

    	if(!kantor_bank.equals("")){
    		if(!firstly){
                query += "AND ";
            }else{
                firstly = false;
            }
    		query += "SANDI_KANTOR_BANK='"+kantor_bank+"' ";
    	}

    	if(!inst_bank.equals("")){
    		if(!firstly){
                query += "AND ";
            }else{
                firstly = false;
            }
    		query += "SANDI_INSTITUSI_BANK='"+inst_bank+"' ";
    	}

    	if(!nama_nasabah.equals("")){
    		if(!firstly){
                query += "AND ";
            }else{
                firstly = false;
            }
    		query += "NAMA_NASABAH LIKE '%"+nama_nasabah+"%' ";
    	}

    	query += "ORDER BY NOMOR_DH";
        System.out.println(query);

    	//rset = stmt.executeQuery(query);
    	return eksekutor.get_all_result(query);
    }

    public ResultSet get_some_a_dhn (String periode, String kantor_bank, String inst_bank, String nama_nasabah, int frow, int nrow) throws SQLException {

        boolean firstly = true;
        query = "SELECT * FROM T_DHN WHERE ";

        if(!periode.equals("")){
            if(!firstly){
                query += "AND ";
            }else{
                firstly = false;
            }
            query += "NOMOR_DH='"+periode+"' ";
        }

        if(!kantor_bank.equals("")){
            if(!firstly){
                query += "AND ";
            }else{
                firstly = false;
            }
            query += "SANDI_KANTOR_BANK='"+kantor_bank+"' ";
        }

        if(!inst_bank.equals("")){
            if(!firstly){
                query += "AND ";
            }else{
                firstly = false;
            }
            query += "SANDI_INSTITUSI_BANK='"+inst_bank+"' ";
        }

        if(!nama_nasabah.equals("")){
            if(!firstly){
                query += "AND ";
            }else{
                firstly = false;
            }
            query += "NAMA_NASABAH LIKE '%"+nama_nasabah+"%' ";
        }

        query += "ORDER BY NOMOR_DH";
        System.out.println(query);

        //rset = stmt.executeQuery(query);
        return eksekutor.get_some_result(query, frow, nrow);
    }

    // mengambil DHN by periode+nomor_urut
    public ResultSet get_dhn_pk (String periode, String urutan) throws SQLException {
    	query = "SELECT * FROM T_DHN WHERE NOMOR_DH='"+periode+"' AND NO_URUT='"+urutan+"' ";
    	return eksekutor.get_one_result(query);
    }

    // mengambil DHN by nomor rekening
    public ResultSet get_dhn_rekening (String no_rekening) throws SQLException {
    	query = "SELECT * FROM T_DHN WHERE REKENING='"+no_rekening+"' ";
    	return eksekutor.get_one_result(query);
    }

}