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
@Table(name = "section")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Section.findAll", query = "SELECT s FROM Section s"),
    @NamedQuery(name = "Section.findBySectionId", query = "SELECT s FROM Section s WHERE s.fKhospital.hospitalId = :id"),
    @NamedQuery(name = "Section.findByName", query = "SELECT s FROM Section s WHERE s.name = :name"),
    @NamedQuery(name = "Section.findByLocation", query = "SELECT s FROM Section s WHERE s.location = :location")})
public class Section implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "section_id")
    private Integer sectionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Size(max = 45)
    @Column(name = "location")
    private String location;
    @JoinColumn(name = "FK_hospital", referencedColumnName = "hospital_id")
    @ManyToOne(optional = false)
    private Hospital fKhospital;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKsection")
    private Collection<Piece> pieceCollection;

    public Section() {
    }

    public Section(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public Section(Integer sectionId, String name) {
        this.sectionId = sectionId;
        this.name = name;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Hospital getFKhospital() {
        return fKhospital;
    }

    public void setFKhospital(Hospital fKhospital) {
        this.fKhospital = fKhospital;
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
        hash += (sectionId != null ? sectionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Section)) {
            return false;
        }
        Section other = (Section) object;
        if ((this.sectionId == null && other.sectionId != null) || (this.sectionId != null && !this.sectionId.equals(other.sectionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Section[ sectionId=" + sectionId + " ]";
    }
    
}
