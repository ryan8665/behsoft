/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controler;

import com.data.Model;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class login implements Serializable {

    Model om = new Model();
    ResultSet rs;

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }
    public String h, u;
    public static String Hid, Uid;
    private String user;
    private String pass;
    private String user_id;
    private String Hospital_group;
    private String Name;
    private String Family;
    private String Hospital_ID;
    private String Email;
    private String Phone;
    private String Last_login;
    private Date LastSeen;
    private boolean isLogin = false;
    private String ftel;

    public String getFtel() {
        return ftel;
    }

    public void setFtel(String ftel) {
        this.ftel = ftel;
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }

    public Date getLastSeen() {
        return LastSeen;
    }

    public void setLastSeen(Date LastSeen) {
        this.LastSeen = LastSeen;
    }

    public void setLast_login(String Last_login) {
        this.Last_login = Last_login;
    }

    public String getLast_login() {
        return Last_login;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setFamily(String Family) {
        this.Family = Family;
    }

    public void setHospital_ID(String Hospital_ID) {
        this.Hospital_ID = Hospital_ID;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getName() {
        return Name;
    }

    public String getFamily() {
        return Family;
    }

    public String getHospital_ID() {
        return Hospital_ID;
    }

    public String getEmail() {
        return Email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public String getHospital_group() {
        return Hospital_group;
    }

    public void setHospital_group(String Hospital_group) {
        this.Hospital_group = Hospital_group;
    }

    public String login() throws IOException {

        FacesContext context = FacesContext.getCurrentInstance();
        if (user.trim().length() <= 0 || pass.trim().length() <= 0) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطا", "لطفا فیلدهای ضروری را پر کنید"));
            return null;
        } else {
            String temp_user = "";
            rs = om.result("SELECT * FROM `user` inner join hospital as h on"
                    + " `FK_hospital` = h.hospital_id inner join `hospital_group` on FK_hospital_group_id ="
                    + " hospital_group_id WHERE `username`= '" + user + "' and `password` = '" + pass + "'");
            try {
                while (rs.next()) {
                    temp_user = rs.getString("username");
                    Uid = user_id = rs.getString("user_id");
                    Name = rs.getString("name");
                    Family = rs.getString("family");
                    Phone = rs.getString("tel");
                    Email = rs.getString("email");
                    Hospital_group = rs.getString("hospital_group_id");
                    Hid = Hospital_ID = rs.getString("FK_hospital");

                }
                if (user.equals(temp_user)) {
                    LastSeen = om.Date("SELECT value FROM `login_history` ");
                    om.insert("INSERT INTO `login_history` (`login_history_id`, `value`, `FK_user_id`) VALUES (NULL, CURRENT_TIMESTAMP, '" + user_id + "');");
                    om.insert("INSERT INTO `ryan`.`log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, 'ورود به سیستم', CURRENT_TIMESTAMP, '" + user_id + "');");
                    if(Integer.parseInt(user_id) != 1){
                        com.controler.sendSms.send(Phone, Name);
                    }   
                    isLogin = true;
                    FacesContext.getCurrentInstance().getExternalContext().redirect("dashboard.xhtml");

                    return null;
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"نام کاربری  یا پسورد اشتباه می باشد", "خطا"));
                    return null;
                }
            } catch (SQLException ex) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطا", ex.toString()));
                return null;
            }finally{
                user ="";
                pass="";
            }

        }

    }

    public String invalidatSession() throws IOException {

        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();

        return "/login.xhtml";

    }

    public void forgetpass() {

        String temptel = om.select("SELECT `tel` FROM `user` WHERE `tel` = '" + ftel + "'");
        if (temptel.equals(ftel)) {
            try {
                String smspass = "", smsname = "", smsuser = "";
                ResultSet r = om.result("SELECT `username`,`password`,`name` FROM `user` WHERE `tel` = '" + ftel + "'");
                while (r.next()) {
                    smsuser = r.getString("username");
                    smsname = r.getString("name");
                    smspass = r.getString("password");

                }
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "گذرواژه به شماره تلفن همراه ارسال شد", ""));
                com.controler.sendSms.sendPass(ftel, smsname, smsuser, smspass);
                ftel = "";
            } catch (SQLException ex) {
                Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ftel = "";
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"شماره تلفن همراه معتبر نمی‌باشد", "خطا"));
        }

    }

}
