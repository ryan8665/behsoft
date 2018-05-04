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
@Table(name = "calibration_result")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CalibrationResult.findAll", query = "SELECT c FROM CalibrationResult c"),
    @NamedQuery(name = "CalibrationResult.findByCalibrationResultId", query = "SELECT c FROM CalibrationResult c WHERE c.calibrationResultId = :calibrationResultId"),
    @NamedQuery(name = "CalibrationResult.findByValue", query = "SELECT c FROM CalibrationResult c WHERE c.value = :value")})
public class CalibrationResult implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "calibration_result_id")
    private Integer calibrationResultId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "value")
    private String value;
    @OneToMany(mappedBy = "fKcalibrationresult")
    private Collection<Calibration> calibrationCollection;

    public CalibrationResult() {
    }

    public CalibrationResult(Integer calibrationResultId) {
        this.calibrationResultId = calibrationResultId;
    }

    public CalibrationResult(Integer calibrationResultId, String value) {
        this.calibrationResultId = calibrationResultId;
        this.value = value;
    }

    public Integer getCalibrationResultId() {
        return calibrationResultId;
    }

    public void setCalibrationResultId(Integer calibrationResultId) {
        this.calibrationResultId = calibrationResultId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
        hash += (calibrationResultId != null ? calibrationResultId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CalibrationResult)) {
            return false;
        }
        CalibrationResult other = (CalibrationResult) object;
        if ((this.calibrationResultId == null && other.calibrationResultId != null) || (this.calibrationResultId != null && !this.calibrationResultId.equals(other.calibrationResultId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.CalibrationResult[ calibrationResultId=" + calibrationResultId + " ]";
    }
    
}
