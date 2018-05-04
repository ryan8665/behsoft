/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.data.Model;
import com.entity.Section;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
@ManagedBean
public class SectionFacade extends AbstractFacade<Section> {
    private String Sname;
    private  String Sdiscr;
    private String id,hid;

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String Sname) {
        this.Sname = Sname;
    }

    public String getSdiscr() {
        return Sdiscr;
    }

    public void setSdiscr(String Sdiscr) {
        this.Sdiscr = Sdiscr;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    public void hIDseter(String id , String hid){
         this.id = id;
          this.hid = hid;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SectionFacade() {
        super(Section.class);
    }

    public List<Section> getSection() {
        return em.createNamedQuery("Section.findBySectionId").setParameter("id", Integer.parseInt(hid)).getResultList();
    }
     public List<Section> getSection2(String a) {
        return em.createNamedQuery("Section.findBySectionId").setParameter("id", Integer.parseInt(a)).getResultList();
    }
    public void setSection(){
        Model om = new Model();
        boolean res = true;
        res =  om.insert("INSERT INTO `section` (`section_id`, `name`, `FK_hospital`, `location`) VALUES (NULL, '"+Sname+"', '"+hid+"', '"+Sdiscr+"');");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"لطفا فیلدهای ضروری را پر کنید", "خطا"));
        } else {
            om.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, 'ثبت بخش "+Sname+"‌', CURRENT_TIMESTAMP, '" + id + "');");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"با موفقیت ثبت شد", "ثبت شد"));
            resetUSerLIst();
        }
       
    }
      public void resetUSerLIst() {

        Sname = "";
        Sdiscr = "";
    }
    
}
