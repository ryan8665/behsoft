/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.data.Model;
import com.entity.Maintenance;
import java.util.ArrayList;
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
public class MaintenanceFacade extends AbstractFacade<Maintenance> {

    private String result, reson, discrib;
    private String id, hid;
     int idSelected;
     String amvalSelected;
     int amvalSelectedid;
    public String getDiscrib() {
        return discrib;
    }

    public void setDiscrib(String discrib) {
        this.discrib = discrib;
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

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaintenanceFacade() {
        super(Maintenance.class);
    }

    public List<Maintenance> getMaintenance() {
        return em.createNamedQuery("Maintenance.findByRep").setParameter("h", Integer.parseInt(hid)).getResultList();
    }
    
     public List<Maintenance> getMaintenanceProved() {
        return em.createNamedQuery("Maintenance.findByproved").setParameter("h", Integer.parseInt(hid)).getResultList();
    }
      public List<Maintenance> getMaintenanceHistory() {
        return em.createNamedQuery("Maintenance.findByMPieceID").setParameter("p", amvalSelectedid).getResultList();
    }
      List<Maintenance> hlist = new ArrayList<>();

    public void hIDseter(String id, String hid) {
        this.id = id;
        this.hid = hid;
    }

    public void setRepair() {
        Model om = new Model();
        String reso = om.select("SELECT `piece_status_id` FROM `piece_status` WHERE `value` = '" + reson + "'");
        String resu = om.select("SELECT `maintenance_status_id` FROM `maintenance_status` WHERE `value` = '" + result + "'");
      
        boolean res = true;
        res = om.Delete("UPDATE `ryan`.`maintenance` SET `repair_date` = NOW(),`description`='"+discrib+"',`FK_maintenace_status`="+resu+",`FK_repair_annunciator`="+id+" WHERE `maintenance`.`maintenace_id` = "+idSelected+";");
           
            if (res) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطا", "خطای رخ داده لطفا دوباره تلاش کنید"));
            } else {
                om.Delete("UPDATE `piece` SET `FK_piece_status`= "+reso+" WHERE `piece_id` = "+amvalSelectedid);
                om.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, 'ثبت نتیجه تعمیرات " + amvalSelected + "‌', CURRENT_TIMESTAMP, '" + id + "');");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "با موفقیت ثبت شد"));
               getMaintenance();
               discrib = "";
            }
    }
     public void onRowSelect(SelectEvent event) {
         idSelected = ((Maintenance) event.getObject()).getMaintenaceId();
         amvalSelected = ((Maintenance) event.getObject()).getFKpiece().getPropertyNumber();
         amvalSelectedid = ((Maintenance) event.getObject()).getFKpiece().getPieceId();
        
    }
}
