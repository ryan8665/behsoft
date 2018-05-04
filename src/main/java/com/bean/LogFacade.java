/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entity.Log;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ryan
 */

@Named
@ManagedBean
@ViewScoped
public class LogFacade extends AbstractFacade<Log> {

       private String id, hid;

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public String getId() {
        return id;
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

    public LogFacade() {
        super(Log.class);
    }

    public List<Log> getLog() {
        if(id.equals("1")){
            return em.createNamedQuery("Log.findAllAdmin").getResultList();
        }else{
             return em.createNamedQuery("Log.findAll").setParameter("id", Integer.parseInt(hid)).getResultList();
        }
           
       
    }
    
    public List<Log> getLogPerson() {
        return em.createNamedQuery("Log.findAllPerson").setParameter("id", Integer.parseInt(id)).getResultList();
    }

    public void hIDseter(String id,String hid) {
        this.id = id;
         this.hid = hid;
    }

}
