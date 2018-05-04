/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "user_option")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserOption.findAll", query = "SELECT u FROM UserOption u"),
    @NamedQuery(name = "UserOption.findByUserOptionId", query = "SELECT u FROM UserOption u WHERE u.userOptionId = :userOptionId"),
    @NamedQuery(name = "UserOption.findByTitle", query = "SELECT u FROM UserOption u WHERE u.title = :title"),
    @NamedQuery(name = "UserOption.findByDescription", query = "SELECT u FROM UserOption u WHERE u.description = :description")})
public class UserOption implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_option_id")
    private Integer userOptionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "title")
    private String title;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKuseroption")
    private Collection<UserPreferences> userPreferencesCollection;

    public UserOption() {
    }

    public UserOption(Integer userOptionId) {
        this.userOptionId = userOptionId;
    }

    public UserOption(Integer userOptionId, String title) {
        this.userOptionId = userOptionId;
        this.title = title;
    }

    public Integer getUserOptionId() {
        return userOptionId;
    }

    public void setUserOptionId(Integer userOptionId) {
        this.userOptionId = userOptionId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<UserPreferences> getUserPreferencesCollection() {
        return userPreferencesCollection;
    }

    public void setUserPreferencesCollection(Collection<UserPreferences> userPreferencesCollection) {
        this.userPreferencesCollection = userPreferencesCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userOptionId != null ? userOptionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserOption)) {
            return false;
        }
        UserOption other = (UserOption) object;
        if ((this.userOptionId == null && other.userOptionId != null) || (this.userOptionId != null && !this.userOptionId.equals(other.userOptionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.UserOption[ userOptionId=" + userOptionId + " ]";
    }
    
}
