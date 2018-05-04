/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entity.MaintenanceStatus;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ryan
 */
@Stateless
@Named
public class MaintenanceStatusFacade extends AbstractFacade<MaintenanceStatus> {

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MaintenanceStatusFacade() {
        super(MaintenanceStatus.class);
    }

    public List<MaintenanceStatus> getMaintenanceStatus() {
        return em.createNamedQuery("MaintenanceStatus.findAll").getResultList();
    }
    
}
