package controller;

import java.sql.SQLException;

import model.Dhn_m;

public class Show_dhn_c {
    private Dhn_m dhn_model;

    public Show_dhn_c() throws SQLException {
    	super();
    	dhn_model = new Dhn_m();
    }

    public ResultSet show_dhn_periode (String periode, int frow, int nrow)
    {
    	ResultSet rset = dhn_model.get_some_a_dhn(periode, "", "", "", frow, nrow);
    	return rset;
    }
}