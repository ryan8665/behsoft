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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "log")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Log.findAll", query = "SELECT l FROM Log l WHERE l.fKuser.fKhospital.hospitalId = :id ORDER BY l.logId DESC"),
     @NamedQuery(name = "Log.findAllAdmin", query = "SELECT l FROM Log l ORDER BY l.logId DESC"),
    @NamedQuery(name = "Log.findAllPerson", query = "SELECT l FROM Log l WHERE l.fKuser.userId = :id ORDER BY l.logId DESC"),
    @NamedQuery(name = "Log.findByLogId", query = "SELECT l FROM Log l WHERE l.logId = :logId"),
    @NamedQuery(name = "Log.findByDescription", query = "SELECT l FROM Log l WHERE l.description = :description"),
    @NamedQuery(name = "Log.findByLogDate", query = "SELECT l FROM Log l WHERE l.logDate = :logDate")})
public class Log implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "log_id")
    private Integer logId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Column(name = "log_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date logDate;
    @JoinColumn(name = "FK_user", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User fKuser;

    public Log() {
    }

    public Log(Integer logId) {
        this.logId = logId;
    }

    public Log(Integer logId, String description, Date logDate) {
        this.logId = logId;
        this.description = description;
        this.logDate = logDate;
    }

    public Integer getLogId() {
        return logId;
    }

    public void setLogId(Integer logId) {
        this.logId = logId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public User getFKuser() {
        return fKuser;
    }

    public void setFKuser(User fKuser) {
        this.fKuser = fKuser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (logId != null ? logId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Log)) {
            return false;
        }
        Log other = (Log) object;
        if ((this.logId == null && other.logId != null) || (this.logId != null && !this.logId.equals(other.logId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Log[ logId=" + logId + " ]";
    }
    
}
