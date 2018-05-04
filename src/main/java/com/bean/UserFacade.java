/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.controler.login;
import com.entity.User;
import java.util.List;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ryan
 */
@Stateless
@Named

public class UserFacade extends AbstractFacade<User> {

   private String id,hid,hgid;
   private String UserIDEdit;
   private List<User> userEdit;

    public String getHgid() {
        return hgid;
    }

    public void setHgid(String hgid) {
        this.hgid = hgid;
    }

    public List<User> getUserEdit() {
        return userEdit;
       
    }

    public void setUserEdit(List<User> userEdit) {
        this.userEdit = userEdit;
    }

    public String getUserIDEdit() {
        return UserIDEdit;
    }

    public void setUserIDEdit(String UserIDEdit) {
        this.UserIDEdit = UserIDEdit;
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
    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    public List<User> getUser() {
        return em.createNamedQuery("User.findHospital").setParameter("id", Integer.parseInt(hid)).getResultList();
        //a = em.createNamedQuery("User.findAll").getResultList();

    }

    public void SelectedRow() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "خطا", "لطفا فیلدهای ضروری را پر کنید"));
    }
   public void hIDseter(String id, String hid, String hgid) {
        this.id = id;
        this.hid = hid;
        this.hgid = hgid;
    }
   
   public void setUserIDForEdit(List<User> id){
       this.userEdit = id;
       
   }
   
 
}
