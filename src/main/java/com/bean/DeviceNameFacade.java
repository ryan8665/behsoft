/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entity.DeviceName;
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
public class DeviceNameFacade extends AbstractFacade<DeviceName> {

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeviceNameFacade() {
        super(DeviceName.class);
    }

    public List<DeviceName> getDeviceName() {
        return em.createNamedQuery("DeviceName.findAll").getResultList();
    }
    
    
}
