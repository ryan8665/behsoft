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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "device_type")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DeviceType.findAll", query = "SELECT d FROM DeviceType d ORDER BY d.deviceTypeId DESC"),
    @NamedQuery(name = "DeviceType.findAllCarts", query = "SELECT d FROM DeviceType d WHERE d.briefManual <> '' OR d.maintenance <> '' OR d.userGuide <> '' OR d.keyReference <> ''"),
    @NamedQuery(name = "DeviceType.findByDeviceTypeId", query = "SELECT d FROM DeviceType d WHERE d.deviceTypeId = :deviceTypeId"),
    @NamedQuery(name = "DeviceType.findByModel", query = "SELECT d FROM DeviceType d WHERE d.model LIKE :model"),
    @NamedQuery(name = "DeviceType.findByUserGuide", query = "SELECT d FROM DeviceType d WHERE d.userGuide = :userGuide"),
    @NamedQuery(name = "DeviceType.findByMaintenance", query = "SELECT d FROM DeviceType d WHERE d.maintenance = :maintenance"),
    @NamedQuery(name = "DeviceType.findByBriefManual", query = "SELECT d FROM DeviceType d WHERE d.briefManual = :briefManual"),
    @NamedQuery(name = "DeviceType.findByKeyReference", query = "SELECT d FROM DeviceType d WHERE d.fKdevicename.deviceNameId = :id ORDER BY d.model ASC"),
    @NamedQuery(name = "DeviceType.findByPhoto", query = "SELECT d FROM DeviceType d WHERE d.photo = :photo")})
public class DeviceType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "device_type_id")
    private Integer deviceTypeId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "model")
    private String model;
    @Size(max = 45)
    @Column(name = "user_guide")
    private String userGuide;
    @Size(max = 45)
    @Column(name = "maintenance")
    private String maintenance;
    @Size(max = 45)
    @Column(name = "brief_manual")
    private String briefManual;
    @Size(max = 45)
    @Column(name = "key_reference")
    private String keyReference;
    @Column(name = "photo")
    private Integer photo;
    @JoinColumn(name = "FK_device_name", referencedColumnName = "device_name_id")
    @ManyToOne(optional = false)
    private DeviceName fKdevicename;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKdevicetype")
    private Collection<Piece> pieceCollection;

    public DeviceType() {
    }

    public DeviceType(Integer deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public DeviceType(Integer deviceTypeId, String model) {
        this.deviceTypeId = deviceTypeId;
        this.model = model;
    }

    public Integer getDeviceTypeId() {
        return deviceTypeId;
    }

    public void setDeviceTypeId(Integer deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUserGuide() {
        return userGuide;
    }

    public void setUserGuide(String userGuide) {
        this.userGuide = userGuide;
    }

    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }

    public String getBriefManual() {
        return briefManual;
    }

    public void setBriefManual(String briefManual) {
        this.briefManual = briefManual;
    }

    public String getKeyReference() {
        return keyReference;
    }

    public void setKeyReference(String keyReference) {
        this.keyReference = keyReference;
    }

    public Integer getPhoto() {
        return photo;
    }

    public void setPhoto(Integer photo) {
        this.photo = photo;
    }

    public DeviceName getFKdevicename() {
        return fKdevicename;
    }

    public void setFKdevicename(DeviceName fKdevicename) {
        this.fKdevicename = fKdevicename;
    }

    @XmlTransient
    public Collection<Piece> getPieceCollection() {
        return pieceCollection;
    }

    public void setPieceCollection(Collection<Piece> pieceCollection) {
        this.pieceCollection = pieceCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (deviceTypeId != null ? deviceTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DeviceType)) {
            return false;
        }
        DeviceType other = (DeviceType) object;
        if ((this.deviceTypeId == null && other.deviceTypeId != null) || (this.deviceTypeId != null && !this.deviceTypeId.equals(other.deviceTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.DeviceType[ deviceTypeId=" + deviceTypeId + " ]";
    }
    
}
