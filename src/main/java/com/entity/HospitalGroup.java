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
@Table(name = "hospital_group")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "HospitalGroup.findAll", query = "SELECT h FROM HospitalGroup h"),
    @NamedQuery(name = "HospitalGroup.findByHospitalGroupId", query = "SELECT h FROM HospitalGroup h WHERE h.hospitalGroupId = :hospitalGroupId"),
    @NamedQuery(name = "HospitalGroup.findByHospitalGroupName", query = "SELECT h FROM HospitalGroup h WHERE h.hospitalGroupName = :hospitalGroupName"),
    @NamedQuery(name = "HospitalGroup.findByDescription", query = "SELECT h FROM HospitalGroup h WHERE h.description = :description")})
public class HospitalGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "hospital_group_id")
    private Integer hospitalGroupId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "hospital_group_name")
    private String hospitalGroupName;
    @Size(max = 105)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKhospitalgroupid")
    private Collection<Hospital> hospitalCollection;

    public HospitalGroup() {
    }

    public HospitalGroup(Integer hospitalGroupId) {
        this.hospitalGroupId = hospitalGroupId;
    }

    public HospitalGroup(Integer hospitalGroupId, String hospitalGroupName) {
        this.hospitalGroupId = hospitalGroupId;
        this.hospitalGroupName = hospitalGroupName;
    }

    public Integer getHospitalGroupId() {
        return hospitalGroupId;
    }

    public void setHospitalGroupId(Integer hospitalGroupId) {
        this.hospitalGroupId = hospitalGroupId;
    }

    public String getHospitalGroupName() {
        return hospitalGroupName;
    }

    public void setHospitalGroupName(String hospitalGroupName) {
        this.hospitalGroupName = hospitalGroupName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Hospital> getHospitalCollection() {
        return hospitalCollection;
    }

    public void setHospitalCollection(Collection<Hospital> hospitalCollection) {
        this.hospitalCollection = hospitalCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hospitalGroupId != null ? hospitalGroupId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HospitalGroup)) {
            return false;
        }
        HospitalGroup other = (HospitalGroup) object;
        if ((this.hospitalGroupId == null && other.hospitalGroupId != null) || (this.hospitalGroupId != null && !this.hospitalGroupId.equals(other.hospitalGroupId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.HospitalGroup[ hospitalGroupId=" + hospitalGroupId + " ]";
    }
    
}
