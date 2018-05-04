/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entity.Question;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;
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
@ManagedBean
public class QuestionFacade extends AbstractFacade<Question> {
private String ipmID = "1";

    public String getIpmID() {
        return ipmID;
    }

    public void setIpmID(String ipmID) {
        this.ipmID = ipmID;
    }
    private String selectedID;

    public String getSelectedID() {
        return selectedID;
    }

    public void setSelectedID(String selectedID) {
        this.selectedID = selectedID;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public QuestionFacade() {
        super(Question.class);
    }

    public List<Question> getIPMfourm() {
        return em.createNamedQuery("Question.findByGroup").getResultList();
    }

    public void Rowselect(SelectEvent e) {
         selectedID =  ((Question) e.getObject()).getFKdevicename().getDeviceNameId()+"";
         
    }
  

}
