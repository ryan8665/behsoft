/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controler;

import com.data.Model;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ryan
 */
@ManagedBean
@ViewScoped

public class User {

    private String name, family, tel, email, user, pass, hospital, editTemp = "asf";
    private String id, hid,hgid;
    private String epass="", reepass="", olpass="";

    public String getHgid() {
        return hgid;
    }

    public void setHgid(String hgid) {
        this.hgid = hgid;
    }

    public String getOlpass() {
        return olpass;
    }

    public void setOlpass(String olpass) {
        this.olpass = olpass;
    }

    public String getEpass() {
        return epass;
    }

    public void setEpass(String epass) {
        this.epass = epass;
    }

    public String getReepass() {
        return reepass;
    }

    public void setReepass(String reepass) {
        this.reepass = reepass;
    }
    private List<com.entity.User> userEdit;

    public List<com.entity.User> getUserEdit() {
        return userEdit;
    }

    public void setUserEdit(List<com.entity.User> userEdit) {
        this.userEdit = userEdit;
    }

    public String getId() {
        return id;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEditTemp() {
        return editTemp;
    }

    public void setEditTemp(String editTemp) {
        this.editTemp = editTemp;
    }
    Model om = new Model();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public void gholi(String id) {
        editTemp = id;
    }

    public boolean userValidator() {
        String tUser = om.select("SELECT username FROM `user` WHERE `username` = '" + user + "'");

        if (user.trim().length() <= 4) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "طول نام کاربری باید حداقل ۴ کاراکتر باشد","طول نام کاربری باید حداقل ۴ کاراکتر باشد"));
            return false;

        } else if (user.equals(tUser)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"این نام کاربری در دسترس نمی‌باشد", "این نام کاربری در دسترس نمی‌باشد" ));

            return false;
        }
        return true;

    }

    public void showInfo() {
        if (userValidator()) {

            boolean res = true;
            res = om.insert("INSERT INTO `ryan`.`user` (`user_id`, `name`, `family`, `tel`, `email`, `username`, `password`, `FK_hospital`, `FK_user_group`) VALUES "
                    + "(null, '" + name + "', '" + family + "', '" + tel + "', '" + email + "', '" + user + "', '" + pass + "', '" + hid + "', '" + hgid + "');");

            if (res) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"لطفا فیلدهای ضروری را پر کنید", "خطا"));
            } else {
                om.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, ' ثبت کاربر جدید (" + name + " " + family + ") ', CURRENT_TIMESTAMP, '" + id + "');");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"با موفقیت ثبت شد", "ثبت شد"));
                com.controler.sendSms.sendnew(tel,name,user,pass);

                resetUSerLIst();
            }
        }
    }

    public void resetUSerLIst() {
        name = "";
        family = "";
        tel = "";
        email = "";
        user = "";
        pass = "";

    }

    public void hIDseter(String id, String hid, String hgid) {
        this.id = id;
        this.hid = hid;
        this.hgid = hgid;
    }

    public void setUserIDForEdit(List<com.entity.User> id) {
        this.userEdit = id;

    }

    public void editUser() {
        boolean res = true;
        res = om.insert("UPDATE `ryan`.`user` SET `password` = '" + epass + "' WHERE `user`.`user_id` = " + id + ";");

        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"لطفا فیلدهای ضروری را پر کنید", "خطا"));
        } else {
            om.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, ' ویرایش مشخصات کاربری  ', CURRENT_TIMESTAMP, '" + id + "');");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"با موفقیت ویرایش شد", "ویرایش شد"));
            epass = "";
            reepass = "";
            olpass = "";

        }

    }

    public void changePass() {
        String tempOldPass = om.select("SELECT `password` FROM `user` WHERE `password` = '" + olpass + "'");
        if (epass.trim().length() >= 5) {
            if (tempOldPass.equals(olpass)) {
                if (epass.equals(reepass)) {
                    boolean res = true;
                    res = om.insert("UPDATE `ryan`.`user` SET `password` = '" + epass + "' WHERE `user`.`user_id` = " + id + ";");

                    if (res) {
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"لطفا فیلدهای ضروری را پر کنید", "خطا"));
                    } else {
                        om.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, ' ویرایش مشخصات کاربری  ', CURRENT_TIMESTAMP, '" + id + "');");
                        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"با موفقیت ویرایش شد", "ویرایش شد"));      
                    }
                } else {
                    epass = "";
                    reepass = "";
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"گذرواژه اشتباه می‌باشد", "خطا"));
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "گذرواژه وارد شده صحیح نمی‌باشد", "گذرواژه وارد شده صحیح نمی‌باشد"));
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "گذرواژه وارد شده نامعتبر می باشد", "گذرواژه وارد شده نامعتبر می باشد"));
        }

    }

}
