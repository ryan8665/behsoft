/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.data.Model;
import com.entity.Calibration;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
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
public class CalibrationFacade extends AbstractFacade<Calibration> {
     private String id,hid;
     List<Calibration> ls = new ArrayList<Calibration>();
    Model om = new Model();
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


    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalibrationFacade() {
        super(Calibration.class);
    }

    public List<Calibration> getCalibration() {
        return em.createNamedQuery("Calibration.findHid").setParameter("id", Integer.parseInt(hid)).getResultList();
    }
    
     public void hIDseter(String id , String hid){
         this.id = id;
          this.hid = hid;
    }
     public String getTodayCalibration(String a) {
        try {
            return om.select("select count(`calibration_id`) from `calibration` inner join piece on `FK_piece` = piece_id inner join section on FK_section = section_id where DATE(`execution_date`) = DATE(NOW()) and FK_hospital = "+a);
        } catch (Exception e) {
            return "0";
        }

    }
     
     public String getTodayCalibrationUser(String a) {
        try {
            return om.select("select count(`calibration_id`) from `calibration` as c inner join user as u on c.`FK_user` = u.user_id where DATE(`execution_date`) = DATE(NOW()) and u.user_id = "+a);
        } catch (Exception e) {
            return "0";
        }

    }
     
}
