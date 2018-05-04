/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.entity.UserPreferences;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Ryan
 */
@Stateless
public class UserPreferencesFacade extends AbstractFacade<UserPreferences> {

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserPreferencesFacade() {
        super(UserPreferences.class);
    }
    
}
