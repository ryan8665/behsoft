/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "user_preferences")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserPreferences.findAll", query = "SELECT u FROM UserPreferences u"),
    @NamedQuery(name = "UserPreferences.findByUserPreferencesId", query = "SELECT u FROM UserPreferences u WHERE u.userPreferencesId = :userPreferencesId"),
    @NamedQuery(name = "UserPreferences.findByValue", query = "SELECT u FROM UserPreferences u WHERE u.value = :value")})
public class UserPreferences implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_preferences_id")
    private Integer userPreferencesId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    private int value;
    @JoinColumn(name = "FK_user", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User fKuser;
    @JoinColumn(name = "FK_user_option", referencedColumnName = "user_option_id")
    @ManyToOne(optional = false)
    private UserOption fKuseroption;

    public UserPreferences() {
    }

    public UserPreferences(Integer userPreferencesId) {
        this.userPreferencesId = userPreferencesId;
    }

    public UserPreferences(Integer userPreferencesId, int value) {
        this.userPreferencesId = userPreferencesId;
        this.value = value;
    }

    public Integer getUserPreferencesId() {
        return userPreferencesId;
    }

    public void setUserPreferencesId(Integer userPreferencesId) {
        this.userPreferencesId = userPreferencesId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public User getFKuser() {
        return fKuser;
    }

    public void setFKuser(User fKuser) {
        this.fKuser = fKuser;
    }

    public UserOption getFKuseroption() {
        return fKuseroption;
    }

    public void setFKuseroption(UserOption fKuseroption) {
        this.fKuseroption = fKuseroption;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userPreferencesId != null ? userPreferencesId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserPreferences)) {
            return false;
        }
        UserPreferences other = (UserPreferences) object;
        if ((this.userPreferencesId == null && other.userPreferencesId != null) || (this.userPreferencesId != null && !this.userPreferencesId.equals(other.userPreferencesId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.UserPreferences[ userPreferencesId=" + userPreferencesId + " ]";
    }
    
}
