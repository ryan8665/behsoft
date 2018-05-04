/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "login_history")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoginHistory.findAll", query = "SELECT l FROM LoginHistory l"),
    @NamedQuery(name = "LoginHistory.findByLoginHistoryId", query = "SELECT l FROM LoginHistory l WHERE l.loginHistoryId = :loginHistoryId"),
    @NamedQuery(name = "LoginHistory.findByValue", query = "SELECT l FROM LoginHistory l WHERE l.value = :value")})
public class LoginHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "login_history_id")
    private Integer loginHistoryId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    @Temporal(TemporalType.TIMESTAMP)
    private Date value;
    @JoinColumn(name = "FK_user_id", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User fKuserid;

    public LoginHistory() {
    }

    public LoginHistory(Integer loginHistoryId) {
        this.loginHistoryId = loginHistoryId;
    }

    public LoginHistory(Integer loginHistoryId, Date value) {
        this.loginHistoryId = loginHistoryId;
        this.value = value;
    }

    public Integer getLoginHistoryId() {
        return loginHistoryId;
    }

    public void setLoginHistoryId(Integer loginHistoryId) {
        this.loginHistoryId = loginHistoryId;
    }

    public Date getValue() {
        return value;
    }

    public void setValue(Date value) {
        this.value = value;
    }

    public User getFKuserid() {
        return fKuserid;
    }

    public void setFKuserid(User fKuserid) {
        this.fKuserid = fKuserid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (loginHistoryId != null ? loginHistoryId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LoginHistory)) {
            return false;
        }
        LoginHistory other = (LoginHistory) object;
        if ((this.loginHistoryId == null && other.loginHistoryId != null) || (this.loginHistoryId != null && !this.loginHistoryId.equals(other.loginHistoryId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.LoginHistory[ loginHistoryId=" + loginHistoryId + " ]";
    }
    
}
