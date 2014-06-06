package controller;

import java.sql.ResultSet;
import java.sql.SQLException;

import model.Pengguna_m;


public class Login_c {
    
    private Pengguna_m user_model;
    private int fail_login;
    private boolean statusLogin;
    private boolean hsl_tmp;

    
    /**
     * @throws SQLException
     */
    public Login_c() throws SQLException {
        super();
        user_model = new Pengguna_m();
        fail_login = 0;
        statusLogin = false;
    }

    /**
    * @param userid
    * @param password
    * @return
    * @throws SQLException
    */

    /*
        mengidentifikasi kondisi login
        1 = user tidak terdaftar
        2 = status user tidak aktif
        3 = password salah
        3 = login berhasil
    */
    public int getLogStat (String userid, String password) throws SQLException {
        
        ResultSet pengguna = user_model.get_user_userid(userid);
        int status_penggguna;
        String password_pengguna;
        this.statusLogin = false;
        fail_login = user_model.get_fail_userid(userid);

        System.out.println("login trial : "+fail_login);

        if(pengguna != null){
            if(fail_login < 3){
                pengguna = user_model.get_user_userid(userid);
                status_penggguna = pengguna.getInt("STATUS_AKTIF");
                //System.out.println("status aktif : "+pengguna.getInt("STATUS_AKTIF"));
                if(status_penggguna == 1){
                    pengguna = user_model.get_user_userid(userid);
                    password_pengguna = pengguna.getString("PASSWORD_AKTIF");
                    //System.out.println("password aktif : "+password_pengguna);
                    if(password_pengguna.equals(password)){
                        return 4;
                    }else{
                        hsl_tmp = user_model.add_fail_userid(userid);
                        return 3;
                    }
                }else{
                    return 2;
                }
            }else{
                if(fail_login >= 3){
                    hsl_tmp = user_model.block_user(userid);
                }
                return 5;
            }
        }else{
            return 1;
        }
    }

    /*
        pengarahan pengambilan string notifikasi alert berdasarkan kondisi yang ditemukan
    */
    public String getAlert (String user, String pass) throws SQLException {
            if (user.equals("") || pass.equals("")){
                return getAlertText(0);
            }
            else {
                return getAlertText(getLogStat(user,pass));
            }
        }

        /*
        pengambilan string notifikasi alert
    */
    public String getAlertText (int logStat){
            String alert = "";
            switch (logStat){
                case 0 : alert = "username atau password tidak boleh kosong";break;
                case 1 : alert = "username tidak ada dalam daftar pengguna";break;
                case 2 : alert = "username tidak aktif";break;
                case 3 : alert = ("password salah, percobaan "+(fail_login+1)+" dari 3");break;
                case 4 : this.statusLogin=true;break;
                case 5 : alert = "anda sudah mencapai batas percobaan login! Akun diblokir";break;
            }
            return alert;
        }
    
    public boolean getStatusLogin (){
        return this.statusLogin;
        }
}