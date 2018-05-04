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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "calibration")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Calibration.findAll", query = "SELECT c FROM Calibration c"),
    @NamedQuery(name = "Calibration.findByCalibrationId", query = "SELECT c FROM Calibration c WHERE c.calibrationId = :calibrationId"),
    @NamedQuery(name = "Calibration.findByPID", query = "SELECT c FROM Calibration c WHERE c.fKpiece.pieceId = :p"),
    @NamedQuery(name = "Calibration.findBypId", query = "SELECT c FROM Calibration c WHERE c.fKpiece.propertyNumber = :p"),
    @NamedQuery(name = "Calibration.findByExecutionDate", query = "SELECT c FROM Calibration c WHERE c.executionDate = :executionDate"),
    @NamedQuery(name = "Calibration.findHid", query = "SELECT c FROM Calibration c WHERE c.fKpiece.fKsection.fKhospital.hospitalId = :id ORDER BY c.calibrationId DESC")})
public class Calibration implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "description")
    private String description;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "calibration_id")
    private Integer calibrationId;
    @Column(name = "execution_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date executionDate;
    @Size(max = 45)
    @Column(name = "alias")
    private String alias;
    @JoinColumn(name = "FK_user", referencedColumnName = "user_id")
    @ManyToOne
    private User fKuser;
    @JoinColumn(name = "FK_piece", referencedColumnName = "piece_id")
    @ManyToOne
    private Piece fKpiece;
    @JoinColumn(name = "FK_calibration_result", referencedColumnName = "calibration_result_id")
    @ManyToOne
    private CalibrationResult fKcalibrationresult;

    public Calibration() {
    }

    public Calibration(Integer calibrationId) {
        this.calibrationId = calibrationId;
    }

    public Integer getCalibrationId() {
        return calibrationId;
    }

    public void setCalibrationId(Integer calibrationId) {
        this.calibrationId = calibrationId;
    }

    public Date getExecutionDate() {
        return executionDate;
    }

    public void setExecutionDate(Date executionDate) {
        this.executionDate = executionDate;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public User getFKuser() {
        return fKuser;
    }

    public void setFKuser(User fKuser) {
        this.fKuser = fKuser;
    }

    public Piece getFKpiece() {
        return fKpiece;
    }

    public void setFKpiece(Piece fKpiece) {
        this.fKpiece = fKpiece;
    }

    public CalibrationResult getFKcalibrationresult() {
        return fKcalibrationresult;
    }

    public void setFKcalibrationresult(CalibrationResult fKcalibrationresult) {
        this.fKcalibrationresult = fKcalibrationresult;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (calibrationId != null ? calibrationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Calibration)) {
            return false;
        }
        Calibration other = (Calibration) object;
        if ((this.calibrationId == null && other.calibrationId != null) || (this.calibrationId != null && !this.calibrationId.equals(other.calibrationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Calibration[ calibrationId=" + calibrationId + " ]";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
