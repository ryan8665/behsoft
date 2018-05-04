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
@Table(name = "piece_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PieceStatus.findAll", query = "SELECT p FROM PieceStatus p WHERE p.pieceStatusId <> 2"),
    @NamedQuery(name = "PieceStatus.findByPieceStatusId", query = "SELECT p FROM PieceStatus p WHERE p.pieceStatusId = :pieceStatusId"),
    @NamedQuery(name = "PieceStatus.findByValue", query = "SELECT p FROM PieceStatus p WHERE p.value = :value"),
    @NamedQuery(name = "PieceStatus.findByDesciption", query = "SELECT p FROM PieceStatus p WHERE p.desciption = :desciption")})
public class PieceStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "piece_status_id")
    private Integer pieceStatusId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "value")
    private String value;
    @Size(max = 100)
    @Column(name = "desciption")
    private String desciption;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKpiecestatus")
    private Collection<Piece> pieceCollection;

    public PieceStatus() {
    }

    public PieceStatus(Integer pieceStatusId) {
        this.pieceStatusId = pieceStatusId;
    }

    public PieceStatus(Integer pieceStatusId, String value) {
        this.pieceStatusId = pieceStatusId;
        this.value = value;
    }

    public Integer getPieceStatusId() {
        return pieceStatusId;
    }

    public void setPieceStatusId(Integer pieceStatusId) {
        this.pieceStatusId = pieceStatusId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesciption() {
        return desciption;
    }

    public void setDesciption(String desciption) {
        this.desciption = desciption;
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
        hash += (pieceStatusId != null ? pieceStatusId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PieceStatus)) {
            return false;
        }
        PieceStatus other = (PieceStatus) object;
        if ((this.pieceStatusId == null && other.pieceStatusId != null) || (this.pieceStatusId != null && !this.pieceStatusId.equals(other.pieceStatusId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.PieceStatus[ pieceStatusId=" + pieceStatusId + " ]";
    }
    
}
