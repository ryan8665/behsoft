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
@Table(name = "maintenance_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MaintenanceStatus.findAll", query = "SELECT m FROM MaintenanceStatus m"),
    @NamedQuery(name = "MaintenanceStatus.findByMaintenanceStatusId", query = "SELECT m FROM MaintenanceStatus m WHERE m.maintenanceStatusId = :maintenanceStatusId"),
    @NamedQuery(name = "MaintenanceStatus.findByValue", query = "SELECT m FROM MaintenanceStatus m WHERE m.value = :value"),
    @NamedQuery(name = "MaintenanceStatus.findByDescription", query = "SELECT m FROM MaintenanceStatus m WHERE m.description = :description")})
public class MaintenanceStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "maintenance_status_id")
    private Integer maintenanceStatusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "value")
    private String value;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKmaintenacestatus")
    private Collection<Maintenance> maintenanceCollection;

    public MaintenanceStatus() {
    }

    public MaintenanceStatus(Integer maintenanceStatusId) {
        this.maintenanceStatusId = maintenanceStatusId;
    }

    public MaintenanceStatus(Integer maintenanceStatusId, String value) {
        this.maintenanceStatusId = maintenanceStatusId;
        this.value = value;
    }

    public Integer getMaintenanceStatusId() {
        return maintenanceStatusId;
    }

    public void setMaintenanceStatusId(Integer maintenanceStatusId) {
        this.maintenanceStatusId = maintenanceStatusId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Maintenance> getMaintenanceCollection() {
        return maintenanceCollection;
    }

    public void setMaintenanceCollection(Collection<Maintenance> maintenanceCollection) {
        this.maintenanceCollection = maintenanceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maintenanceStatusId != null ? maintenanceStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaintenanceStatus)) {
            return false;
        }
        MaintenanceStatus other = (MaintenanceStatus) object;
        if ((this.maintenanceStatusId == null && other.maintenanceStatusId != null) || (this.maintenanceStatusId != null && !this.maintenanceStatusId.equals(other.maintenanceStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.MaintenanceStatus[ maintenanceStatusId=" + maintenanceStatusId + " ]";
    }
    
}
