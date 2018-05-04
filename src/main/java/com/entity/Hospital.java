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
@Table(name = "hospital")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Hospital.findAll", query = "SELECT h FROM Hospital h"),
    @NamedQuery(name = "Hospital.findByHospitalId", query = "SELECT h FROM Hospital h WHERE h.hospitalId = :hospitalId"),
    @NamedQuery(name = "Hospital.findByHospitalname", query = "SELECT h FROM Hospital h WHERE h.hospitalname = :hospitalname"),
    @NamedQuery(name = "Hospital.findByGroup", query = "SELECT h FROM Hospital h WHERE h.fKhospitalgroupid.hospitalGroupId = :id"),
    @NamedQuery(name = "Hospital.findByDescription", query = "SELECT h FROM Hospital h WHERE h.description = :description")})
public class Hospital implements Serializable {

    @JoinColumn(name = "FK_hospital_group_id", referencedColumnName = "hospital_group_id")
    @ManyToOne(optional = false)
    private HospitalGroup fKhospitalgroupid;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "hospital_id")
    private Integer hospitalId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "Hospital_name")
    private String hospitalname;
    @Size(max = 45)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKhospital")
    private Collection<Section> sectionCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKhospital")
    private Collection<User> userCollection;

    public Hospital() {
    }

    public Hospital(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Hospital(Integer hospitalId, String hospitalname) {
        this.hospitalId = hospitalId;
        this.hospitalname = hospitalname;
    }

    public Integer getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Section> getSectionCollection() {
        return sectionCollection;
    }

    public void setSectionCollection(Collection<Section> sectionCollection) {
        this.sectionCollection = sectionCollection;
    }

    @XmlTransient
    public Collection<User> getUserCollection() {
        return userCollection;
    }

    public void setUserCollection(Collection<User> userCollection) {
        this.userCollection = userCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (hospitalId != null ? hospitalId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hospital)) {
            return false;
        }
        Hospital other = (Hospital) object;
        if ((this.hospitalId == null && other.hospitalId != null) || (this.hospitalId != null && !this.hospitalId.equals(other.hospitalId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Hospital[ hospitalId=" + hospitalId + " ]";
    }

    public HospitalGroup getFKhospitalgroupid() {
        return fKhospitalgroupid;
    }

    public void setFKhospitalgroupid(HospitalGroup fKhospitalgroupid) {
        this.fKhospitalgroupid = fKhospitalgroupid;
    }
    
}
