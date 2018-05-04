/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "piece")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Piece.findAll", query = "SELECT p FROM Piece p"),
    @NamedQuery(name = "Piece.findByPieceId", query = "SELECT p FROM Piece p WHERE p.pieceId = :pieceId"),
    @NamedQuery(name = "Piece.findByPropertyNumber", query = "SELECT p FROM Piece p WHERE p.pieceId = :propertyNumber"),
    @NamedQuery(name = "Piece.findBySerialNumber", query = "SELECT p FROM Piece p WHERE p.serialNumber = :serialNumber"),
    @NamedQuery(name = "Piece.findBySarch", query = "SELECT p FROM Piece p WHERE p.fKsection.fKhospital.hospitalId = :hid AND p.propertyNumber LIKE :pid ORDER BY P.pieceId DESC"),
    @NamedQuery(name = "Piece.findByHId", query = "SELECT p FROM Piece p WHERE p.fKsection.fKhospital.hospitalId = :hid AND p.fKpiecestatus.pieceStatusId = 1 ORDER BY P.pieceId DESC")})
public class Piece implements Serializable {

    @JoinColumn(name = "FK_piece_status", referencedColumnName = "piece_status_id")
    @ManyToOne(optional = false)
    private PieceStatus fKpiecestatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKpiece")
    private Collection<Maintenance> maintenanceCollection;

    @Basic(optional = false)
    @NotNull
    @Column(name = "subdate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date subdate;

    @Size(max = 11)
    @Column(name = "device_id")
    private String deviceId;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "piece_id")
    private Integer pieceId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "property_number")
    private String propertyNumber;
    @Size(max = 45)
    @Column(name = "serial_number")
    private String serialNumber;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKpiece")
    private Collection<Answer> answerCollection;
    @JoinColumn(name = "FK_device_type", referencedColumnName = "device_type_id")
    @ManyToOne(optional = false)
    private DeviceType fKdevicetype;
    @JoinColumn(name = "FK_section", referencedColumnName = "section_id")
    @ManyToOne(optional = false)
    private Section fKsection;
    @OneToMany(mappedBy = "fKpiece")
    private Collection<Calibration> calibrationCollection;

    public Piece() {
    }

    public Piece(Integer pieceId) {
        this.pieceId = pieceId;
    }

    public Piece(Integer pieceId, String propertyNumber) {
        this.pieceId = pieceId;
        this.propertyNumber = propertyNumber;
    }

    public Integer getPieceId() {
        return pieceId;
    }

    public void setPieceId(Integer pieceId) {
        this.pieceId = pieceId;
    }

    public String getPropertyNumber() {
        return propertyNumber;
    }

    public void setPropertyNumber(String propertyNumber) {
        this.propertyNumber = propertyNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @XmlTransient
    public Collection<Answer> getAnswerCollection() {
        return answerCollection;
    }

    public void setAnswerCollection(Collection<Answer> answerCollection) {
        this.answerCollection = answerCollection;
    }

    public DeviceType getFKdevicetype() {
        return fKdevicetype;
    }

    public void setFKdevicetype(DeviceType fKdevicetype) {
        this.fKdevicetype = fKdevicetype;
    }

    public Section getFKsection() {
        return fKsection;
    }

    public void setFKsection(Section fKsection) {
        this.fKsection = fKsection;
    }

    @XmlTransient
    public Collection<Calibration> getCalibrationCollection() {
        return calibrationCollection;
    }

    public void setCalibrationCollection(Collection<Calibration> calibrationCollection) {
        this.calibrationCollection = calibrationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pieceId != null ? pieceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Piece)) {
            return false;
        }
        Piece other = (Piece) object;
        if ((this.pieceId == null && other.pieceId != null) || (this.pieceId != null && !this.pieceId.equals(other.pieceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Piece[ pieceId=" + pieceId + " ]";
    }

    public Date getSubdate() {
        return subdate;
    }

    public void setSubdate(Date subdate) {
        this.subdate = subdate;
    }

    public PieceStatus getFKpiecestatus() {
        return fKpiecestatus;
    }

    public void setFKpiecestatus(PieceStatus fKpiecestatus) {
        this.fKpiecestatus = fKpiecestatus;
    }

    @XmlTransient
    public Collection<Maintenance> getMaintenanceCollection() {
        return maintenanceCollection;
    }

    public void setMaintenanceCollection(Collection<Maintenance> maintenanceCollection) {
        this.maintenanceCollection = maintenanceCollection;
    }

   
    
}
