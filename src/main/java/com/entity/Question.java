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
@Table(name = "question")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Question.findAll", query = "SELECT q FROM Question q"),
    @NamedQuery(name = "Question.findByQuestionId", query = "SELECT q FROM Question q WHERE q.questionId = :questionId"),
    @NamedQuery(name = "Question.findByText", query = "SELECT q FROM Question q WHERE q.text = :text"),
    @NamedQuery(name = "Question.findByGroup", query = "SELECT q FROM Question q GROUP BY q.fKdevicename.deviceNameId")})
public class Question implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "EnterDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date enterDate;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "question_id")
    private Integer questionId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "text")
    private String text;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ratio")
    private int ratio;
    @JoinColumn(name = "FK_question_type", referencedColumnName = "question_type_id")
    @ManyToOne(optional = false)
    private QuestionType fKquestiontype;
    @JoinColumn(name = "FK_device_name", referencedColumnName = "device_name_id")
    @ManyToOne(optional = false)
    private DeviceName fKdevicename;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fKquestion")
    private Collection<Answer> answerCollection;

    public Question() {
    }

    public Question(Integer questionId) {
        this.questionId = questionId;
    }

    public Question(Integer questionId, String text, int ratio) {
        this.questionId = questionId;
        this.text = text;
        this.ratio = ratio;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRatio() {
        return ratio;
    }

    public void setRatio(int ratio) {
        this.ratio = ratio;
    }

    public QuestionType getFKquestiontype() {
        return fKquestiontype;
    }

    public void setFKquestiontype(QuestionType fKquestiontype) {
        this.fKquestiontype = fKquestiontype;
    }

    public DeviceName getFKdevicename() {
        return fKdevicename;
    }

    public void setFKdevicename(DeviceName fKdevicename) {
        this.fKdevicename = fKdevicename;
    }

    @XmlTransient
    public Collection<Answer> getAnswerCollection() {
        return answerCollection;
    }

    public void setAnswerCollection(Collection<Answer> answerCollection) {
        this.answerCollection = answerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (questionId != null ? questionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.questionId == null && other.questionId != null) || (this.questionId != null && !this.questionId.equals(other.questionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Question[ questionId=" + questionId + " ]";
    }

    public Date getEnterDate() {
        return enterDate;
    }

    public void setEnterDate(Date enterDate) {
        this.enterDate = enterDate;
    }
    
}
