/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.data.Model;
import com.entity.Hospital;
import com.entity.HospitalGroup;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Ryan
 */
@Stateless
@Named
public class HospitalGroupFacade extends AbstractFacade<HospitalGroup> {

    private String id, hid, hgid,tempHid;
    private String newHospitalName, newHospitalDiscr;
    private Boolean disabled = true;
    private String name, family, tel, email, user, pass, hospital;

    public String getTempHid() {
        return tempHid;
    }

    public void setTempHid(String tempHid) {
        this.tempHid = tempHid;
    }

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

    public String getNewHospitalName() {
        return newHospitalName;
    }

    public void setNewHospitalName(String newHospitalName) {
        this.newHospitalName = newHospitalName;
    }

    public String getNewHospitalDiscr() {
        return newHospitalDiscr;
    }

    public void setNewHospitalDiscr(String newHospitalDiscr) {
        this.newHospitalDiscr = newHospitalDiscr;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getHgid() {
        return hgid;
    }

    public void setHgid(String hgid) {
        this.hgid = hgid;
    }
    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public HospitalGroupFacade() {
        super(HospitalGroup.class);
    }

    public List<Hospital> getHospitalGroup() {
        return em.createNamedQuery("Hospital.findByGroup").setParameter("id", Integer.parseInt(hgid)).getResultList();
    }

    public void hIDseter(String id, String hid, String hgid) {
        this.id = id;
        this.hid = hid;
        this.hgid = hgid;
    }

    public void onRowSelect(SelectEvent event) {
        tempHid = ((Hospital) event.getObject()).getHospitalId()+"";
        disabled = false;
    }

    public void newHospital() {
        Model om = new Model();
        boolean res = true;
        res = om.insert("INSERT INTO `ryan`.`hospital` (`hospital_id`, `Hospital_name`, `description`, `FK_hospital_group_id`) VALUES "
                + "(NULL, '" + newHospitalName + "', '" + newHospitalDiscr + "', '" + hgid + "');");
        if (res) {

            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطا", "خطا"));
        } else {
            om.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, 'ثبت مرکز " + newHospitalName + "‌', CURRENT_TIMESTAMP, '" + id + "');");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"با موفقیت ثبت شد", "ثبت شد"));
            newHospitalDiscr = "";
            newHospitalName = "";
            disabled = true;

        }
    }

    public boolean userValidator() {
        Model om = new Model();
        String tUser = om.select("SELECT username FROM `user` WHERE `username` = '" + user + "'");

        if (user.trim().length() <= 4) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL,"طول نام کاربری باید حداقل ۴ کاراکتر باشد", "خطا" ));
            return false;

        } else if (user.equals(tUser)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "این نام کاربری در دسترس نمی‌باشد","خطا"));

            return false;
        }
        return true;

    }

    public void newUser() {
        Model om = new Model();
        if (userValidator()) {

            boolean res = true;
            res = om.insert("INSERT INTO `user` (`user_id`, `name`, `family`, `tel`, `email`, `username`, `password`, `FK_hospital`, `FK_user_group`) VALUES "
                    + "(null, '" + name + "', '" + family + "', '" + tel + "', '" + email + "', '" + user + "', '" + pass + "', '" + tempHid + "', '2');");

            if (res) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطا", "خطا"));
            } else {
                om.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, ' ثبت کاربر جدید (" + name + " " + family + ") ', CURRENT_TIMESTAMP, '" + id + "');");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "با موفقیت ثبت شد","ثبت شد"));

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
}
