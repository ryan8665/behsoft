/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
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
@Table(name = "user_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserGroup.findAll", query = "SELECT u FROM UserGroup u"),
    @NamedQuery(name = "UserGroup.findByUserGroupId", query = "SELECT u FROM UserGroup u WHERE u.userGroupId = :userGroupId"),
    @NamedQuery(name = "UserGroup.findByUserGroupName", query = "SELECT u FROM UserGroup u WHERE u.userGroupName = :userGroupName"),
    @NamedQuery(name = "UserGroup.findByDescription", query = "SELECT u FROM UserGroup u WHERE u.description = :description")})
public class UserGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "user_group_id")
    private Integer userGroupId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "user_group_name")
    private String userGroupName;
    @Size(max = 100)
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "fKusergroup")
    private Collection<User> userCollection;

    public UserGroup() {
    }

    public UserGroup(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public UserGroup(Integer userGroupId, String userGroupName) {
        this.userGroupId = userGroupId;
        this.userGroupName = userGroupName;
    }

    public Integer getUserGroupId() {
        return userGroupId;
    }

    public void setUserGroupId(Integer userGroupId) {
        this.userGroupId = userGroupId;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userGroupId != null ? userGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserGroup)) {
            return false;
        }
        UserGroup other = (UserGroup) object;
        if ((this.userGroupId == null && other.userGroupId != null) || (this.userGroupId != null && !this.userGroupId.equals(other.userGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.UserGroup[ userGroupId=" + userGroupId + " ]";
    }
    
}
