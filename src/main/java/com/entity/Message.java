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
@Table(name = "message")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Message.all", query = "SELECT m FROM Message m WHERE m.fKuserreceiver = :userId"),
    @NamedQuery(name = "Message.findAll", query = "SELECT u FROM Message m JOIN m.fKuserreceiver u WHERE u.userId = :userId"),
    @NamedQuery(name = "Message.inerjoin", query = "SELECT m FROM Message m WHERE m.fKuserreceiver.userId = :userId ORDER BY m.messageId DESC"),
    @NamedQuery(name = "Message.inerjoinSent", query = "SELECT m FROM Message m WHERE m.fKusersender.userId = :userId ORDER BY m.messageId DESC"),
    @NamedQuery(name = "Message.findByMessageId", query = "SELECT m FROM Message m WHERE m.messageId = :messageId"),
    @NamedQuery(name = "Message.findByText", query = "SELECT m FROM Message m WHERE m.text = :text"),
    @NamedQuery(name = "Message.findByID", query = "SELECT m FROM Message m WHERE m.messageId = :id"),
    @NamedQuery(name = "Message.findByIsRead", query = "SELECT m FROM Message m WHERE m.isRead = :isRead")})
public class Message implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "subject")
    private String subject;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "message_id")
    private Integer messageId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "text")
    private String text;
    @Basic(optional = false)
    @NotNull
    @Column(name = "sending_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendingDate;
    @Column(name = "is_read")
    private Boolean isRead;
    @JoinColumn(name = "FK_user_sender", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User fKusersender;
    @JoinColumn(name = "FK_user_receiver", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User fKuserreceiver;

    public Message() {
    }

    public Message(Integer messageId) {
        this.messageId = messageId;
    }

    public Message(Integer messageId, String text, Date sendingDate) {
        this.messageId = messageId;
        this.text = text;
        this.sendingDate = sendingDate;
    }

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSendingDate() {
        return sendingDate;
    }

    public void setSendingDate(Date sendingDate) {
        this.sendingDate = sendingDate;
    }

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(Boolean isRead) {
        this.isRead = isRead;
    }

    public User getFKusersender() {
        return fKusersender;
    }

    public void setFKusersender(User fKusersender) {
        this.fKusersender = fKusersender;
    }

    public User getFKuserreceiver() {
        return fKuserreceiver;
    }

    public void setFKuserreceiver(User fKuserreceiver) {
        this.fKuserreceiver = fKuserreceiver;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (messageId != null ? messageId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Message)) {
            return false;
        }
        Message other = (Message) object;
        if ((this.messageId == null && other.messageId != null) || (this.messageId != null && !this.messageId.equals(other.messageId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Message[ messageId=" + messageId + " ]";
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
}
