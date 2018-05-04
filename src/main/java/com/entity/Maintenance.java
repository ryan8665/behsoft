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
@Table(name = "maintenance")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Maintenance.findAll", query = "SELECT m FROM Maintenance m"),
    @NamedQuery(name = "Maintenance.findByMPieceID", query = "SELECT m FROM Maintenance m WHERE m.fKpiece.pieceId = :p ORDER BY m.maintenaceId DESC"),
    @NamedQuery(name = "Maintenance.findByMaintenaceId", query = "SELECT m FROM Maintenance m WHERE m.maintenaceId = :maintenaceId"),
    @NamedQuery(name = "Maintenance.findByFailureDate", query = "SELECT m FROM Maintenance m WHERE m.failureDate = :failureDate"),
    @NamedQuery(name = "Maintenance.findByRepairDate", query = "SELECT m FROM Maintenance m WHERE m.repairDate = :repairDate"),
    @NamedQuery(name = "Maintenance.findByRep", query = "SELECT m FROM Maintenance m WHERE m.repairDate IS NULL AND m.fKpiece.fKsection.fKhospital.hospitalId = :h"),
    @NamedQuery(name = "Maintenance.findByproved", query = "SELECT m FROM Maintenance m WHERE m.fKpiece.fKpiecestatus.pieceStatusId = 3 AND m.fKpiece.fKsection.fKhospital.hospitalId = :h GROUP BY m.fKpiece.pieceId"),
    @NamedQuery(name = "Maintenance.findByDescription", query = "SELECT m FROM Maintenance m WHERE m.description = :description")})
public class Maintenance implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "maintenace_id")
    private Integer maintenaceId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "failure_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date failureDate;
    @Column(name = "repair_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date repairDate;
    @Size(max = 200)
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "FK_piece", referencedColumnName = "piece_id")
    @ManyToOne(optional = false)
    private Piece fKpiece;
    @JoinColumn(name = "FK_maintenace_status", referencedColumnName = "maintenance_status_id")
    @ManyToOne(optional = false)
    private MaintenanceStatus fKmaintenacestatus;
    @JoinColumn(name = "FK_failure_annunciator", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User fKfailureannunciator;
    @JoinColumn(name = "FK_repair_annunciator", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User fKrepairannunciator;

    public Maintenance() {
    }

    public Maintenance(Integer maintenaceId) {
        this.maintenaceId = maintenaceId;
    }

    public Maintenance(Integer maintenaceId, Date failureDate) {
        this.maintenaceId = maintenaceId;
        this.failureDate = failureDate;
    }

    public Integer getMaintenaceId() {
        return maintenaceId;
    }

    public void setMaintenaceId(Integer maintenaceId) {
        this.maintenaceId = maintenaceId;
    }

    public Date getFailureDate() {
        return failureDate;
    }

    public void setFailureDate(Date failureDate) {
        this.failureDate = failureDate;
    }

    public Date getRepairDate() {
        return repairDate;
    }

    public void setRepairDate(Date repairDate) {
        this.repairDate = repairDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Piece getFKpiece() {
        return fKpiece;
    }

    public void setFKpiece(Piece fKpiece) {
        this.fKpiece = fKpiece;
    }

    public MaintenanceStatus getFKmaintenacestatus() {
        return fKmaintenacestatus;
    }

    public void setFKmaintenacestatus(MaintenanceStatus fKmaintenacestatus) {
        this.fKmaintenacestatus = fKmaintenacestatus;
    }

    public User getFKfailureannunciator() {
        return fKfailureannunciator;
    }

    public void setFKfailureannunciator(User fKfailureannunciator) {
        this.fKfailureannunciator = fKfailureannunciator;
    }

    public User getFKrepairannunciator() {
        return fKrepairannunciator;
    }

    public void setFKrepairannunciator(User fKrepairannunciator) {
        this.fKrepairannunciator = fKrepairannunciator;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (maintenaceId != null ? maintenaceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maintenance)) {
            return false;
        }
        Maintenance other = (Maintenance) object;
        if ((this.maintenaceId == null && other.maintenaceId != null) || (this.maintenaceId != null && !this.maintenaceId.equals(other.maintenaceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Maintenance[ maintenaceId=" + maintenaceId + " ]";
    }

}
