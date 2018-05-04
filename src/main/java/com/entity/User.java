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
@Table(name = "user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findHospital", query = "SELECT u FROM User u WHERE u.fKhospital.hospitalId = :id"),
    @NamedQuery(name = "User.findByUserId", query = "SELECT u FROM User u WHERE u.userId = :userId"),
    @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
    @NamedQuery(name = "User.findByFamily", query = "SELECT u FROM User u WHERE u.family = :family"),
    @NamedQuery(name = "User.findByTel", query = "SELECT u FROM User u WHERE u.tel = :tel"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password")})
public class User implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKfailureannunciator")
    private Collection<Maintenance> maintenanceCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKrepairannunciator")
    private Collection<Maintenance> maintenanceCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKuser")
    private Collection<UserPreferences> userPreferencesCollection;

    @JoinColumn(name = "FK_user_group", referencedColumnName = "user_group_id")
    @ManyToOne
    private UserGroup fKusergroup;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKuser")
    private Collection<Log> logCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "user_id")
    private Integer userId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "family")
    private String family;
    @Size(max = 11)
    @Column(name = "tel")
    private String tel;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 45)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password")
    private String password;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKusersender")
    private Collection<Message> messageCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKuserreceiver")
    private Collection<Message> messageCollection1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKuser")
    private Collection<Answer> answerCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKuserid")
    private Collection<LoginHistory> loginHistoryCollection;
    @OneToMany(mappedBy = "fKuser")
    private Collection<Calibration> calibrationCollection;
    @JoinColumn(name = "FK_hospital", referencedColumnName = "hospital_id")
    @ManyToOne(optional = false)
    private Hospital fKhospital;


    public User() {
    }

    public User(Integer userId) {
        this.userId = userId;
    }

    public User(Integer userId, String name, String family, String username, String password) {
        this.userId = userId;
        this.name = name;
        this.family = family;
        this.username = username;
        this.password = password;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection() {
        return messageCollection;
    }

    public void setMessageCollection(Collection<Message> messageCollection) {
        this.messageCollection = messageCollection;
    }

    @XmlTransient
    public Collection<Message> getMessageCollection1() {
        return messageCollection1;
    }

    public void setMessageCollection1(Collection<Message> messageCollection1) {
        this.messageCollection1 = messageCollection1;
    }

    @XmlTransient
    public Collection<Answer> getAnswerCollection() {
        return answerCollection;
    }

    public void setAnswerCollection(Collection<Answer> answerCollection) {
        this.answerCollection = answerCollection;
    }

    @XmlTransient
    public Collection<LoginHistory> getLoginHistoryCollection() {
        return loginHistoryCollection;
    }

    public void setLoginHistoryCollection(Collection<LoginHistory> loginHistoryCollection) {
        this.loginHistoryCollection = loginHistoryCollection;
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
        hash += (userId != null ? userId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.userId == null && other.userId != null) || (this.userId != null && !this.userId.equals(other.userId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.User[ userId=" + userId + " ]";
    }

    @XmlTransient
    public Collection<Log> getLogCollection() {
        return logCollection;
    }

    public void setLogCollection(Collection<Log> logCollection) {
        this.logCollection = logCollection;
    }

    public UserGroup getFKusergroup() {
        return fKusergroup;
    }

    public void setFKusergroup(UserGroup fKusergroup) {
        this.fKusergroup = fKusergroup;
    }

    @XmlTransient
    public Collection<Maintenance> getMaintenanceCollection() {
        return maintenanceCollection;
    }

    public void setMaintenanceCollection(Collection<Maintenance> maintenanceCollection) {
        this.maintenanceCollection = maintenanceCollection;
    }

    @XmlTransient
    public Collection<Maintenance> getMaintenanceCollection1() {
        return maintenanceCollection1;
    }

    public void setMaintenanceCollection1(Collection<Maintenance> maintenanceCollection1) {
        this.maintenanceCollection1 = maintenanceCollection1;
    }

    @XmlTransient
    public Collection<UserPreferences> getUserPreferencesCollection() {
        return userPreferencesCollection;
    }

    public void setUserPreferencesCollection(Collection<UserPreferences> userPreferencesCollection) {
        this.userPreferencesCollection = userPreferencesCollection;
    }
    
}
