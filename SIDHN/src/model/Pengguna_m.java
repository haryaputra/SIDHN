package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Pengguna_m {
    
    private Statement stmt;
	private ResultSet rset = null;
	private String query;

    private Qexec_m eksekutor;

    /**
     * @throws SQLException
     */
    public Pengguna_m() throws SQLException {
		super();
		eksekutor = new Qexec_m();
    }	

    // input : user_id (string)
	// output :
	// - null (jika user tidak terdaftar)
	// - 1 tupple user denga user_id sesuai input

    /**
     * @param userid
     * @return
     * @throws SQLException
     */
    public ResultSet get_user_userid (String userid) throws SQLException {
		query = "SELECT * FROM T_USER WHERE USER_ID='"+userid+"'";
		rset = stmt.executeQuery(query);
		if(!rset.next()){
			rset=null;
		}
		return rset;
	}

	// asumsi : user terdaftar (pastikan fungsi dipanggil setelah pengecekan user)
	// input : user_id (string)
	// output : 
	// - null (role tidak terdaftar / user tidak terdaftar)
	// - 1 tupple role

    /**
     * @param userid
     * @return
     * @throws SQLException
     */
    public ResultSet get_role_userid (String userid) throws SQLException {
		
		ResultSet pengguna = get_user_userid(userid);

		if(pengguna != null){
			String role_id = rset.getString("ROLE_ID");
			query = "SELECT * FROM R_USER_ROLE WHERE ROLE_ID='"+role_id+"'";
			rset = stmt.executeQuery(query);
			if(!rset.next()){
				return null;
			}else{
				return rset;	
			}
		}else{
			return null;
		}
	}

	// input : user_id (string)
	// output : 
	// - null (user tidak terdaftar)
	// - status user

    /**
     * @param userid
     * @return
     * @throws SQLException
     */
    public String get_status_userid (String userid) throws SQLException {
		
		ResultSet pengguna = get_user_userid(userid);

		if(pengguna != null){
			return pengguna.getString("STATUS_AKTIF");
		}else{
			return null;
		}
	}

	// input : user_id (string)
	// output : 
	// - false (blocking gagal)
	// - true (blocking berhasil)

    /**
     * @param userid
     * @return
     * @throws SQLException
     */
    public boolean block_user (String userid) throws SQLException {
		query = "UPDATE T_USER SET STATUS_AKTIF=2 WHERE USER_ID='"+userid+"'";
		boolean hsl = stmt.execute(query);
		return hsl;
	}

	// unblock pengguna
	public boolean unblock_user (String userid) throws SQLException {
		query = "UPDATE T_USER SET STATUS_AKTIF='1', LOGIN_TRIAL='0' WHERE USER_ID='"+userid+"'";
		boolean hsl = stmt.execute(query);
		return hsl;
	}

	// get jumlah gagal login
	public int get_fail_userid(String userid)  throws SQLException {
		ResultSet pengguna = get_user_userid(userid);
		if(pengguna != null){
			return pengguna.getInt("LOGIN_TRIAL");
		}else{
			return 999;
		}
	}

	// add jumlah gagal login
	public boolean add_fail_userid (String userid)  throws SQLException {
		ResultSet pengguna = get_user_userid(userid);
		if(pengguna != null){
			int old_fail = pengguna.getInt("LOGIN_TRIAL");
			old_fail++;
                        System.out.println("jml fail baru: "+old_fail);
			query = "UPDATE T_USER SET LOGIN_TRIAL='"+old_fail+"' WHERE USER_ID='"+userid+"'";
			boolean hsl = stmt.execute(query);
			return hsl;
		}else{
			return false;
		}
	}

}