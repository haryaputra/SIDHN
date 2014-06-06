package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Dhn_m;

public class Testing {
    public Testing() {
    }

    public static void main(String[] args) throws SQLException {
        //Testing testing = new Testing();
        Dhn_m dhn_model = new Dhn_m();

        ResultSet some_dhn;
        ResultSet a_dhn;

        a_dhn = dhn_model.get_dhn_pk("1/06/08", "00029");
        System.out.println(a_dhn.getString("NOMOR_DH")+", "+a_dhn.getString("NAMA_BANK")+", "+a_dhn.getString("NAMA_NASABAH"));
        System.out.println();

        some_dhn = dhn_model.get_some_a_dhn("", "", "", "muhammad");
        while(some_dhn.next()){
        	System.out.println(some_dhn.getString("NOMOR_DH")+", "+some_dhn.getString("NAMA_BANK")+", "+some_dhn.getString("NAMA_NASABAH"));
        }
        System.out.println();

        System.out.println("Nama sebelumnya : "+a_dhn.getString("NAMA_NASABAH"));
    }
}
