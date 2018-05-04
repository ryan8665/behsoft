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
@Table(name = "device_name")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeviceName.findAll", query = "SELECT d FROM DeviceName d ORDER BY d.name ASC"),
    @NamedQuery(name = "DeviceName.findByDeviceNameId", query = "SELECT d FROM DeviceName d WHERE d.deviceNameId = :deviceNameId"),
    @NamedQuery(name = "DeviceName.findByName", query = "SELECT d FROM DeviceName d WHERE d.name = :name"),
    @NamedQuery(name = "DeviceName.findByEmNumber", query = "SELECT d FROM DeviceName d WHERE d.emNumber = :emNumber")})
public class DeviceName implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "device_name_id")
    private Integer deviceNameId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "em_number")
    private int emNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKdevicename")
    private Collection<Question> questionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKdevicename")
    private Collection<DeviceType> deviceTypeCollection;

    public DeviceName() {
    }

    public DeviceName(Integer deviceNameId) {
        this.deviceNameId = deviceNameId;
    }

    public DeviceName(Integer deviceNameId, String name, int emNumber) {
        this.deviceNameId = deviceNameId;
        this.name = name;
        this.emNumber = emNumber;
    }

    public Integer getDeviceNameId() {
        return deviceNameId;
    }

    public void setDeviceNameId(Integer deviceNameId) {
        this.deviceNameId = deviceNameId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getEmNumber() {
        return emNumber;
    }

    public void setEmNumber(int emNumber) {
        this.emNumber = emNumber;
    }

    @XmlTransient
    public Collection<Question> getQuestionCollection() {
        return questionCollection;
    }

    public void setQuestionCollection(Collection<Question> questionCollection) {
        this.questionCollection = questionCollection;
    }

    @XmlTransient
    public Collection<DeviceType> getDeviceTypeCollection() {
        return deviceTypeCollection;
    }

    public void setDeviceTypeCollection(Collection<DeviceType> deviceTypeCollection) {
        this.deviceTypeCollection = deviceTypeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deviceNameId != null ? deviceNameId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeviceName)) {
            return false;
        }
        DeviceName other = (DeviceName) object;
        if ((this.deviceNameId == null && other.deviceNameId != null) || (this.deviceNameId != null && !this.deviceNameId.equals(other.deviceNameId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.DeviceName[ deviceNameId=" + deviceNameId + " ]";
    }
    
}
