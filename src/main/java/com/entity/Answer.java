/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ryan
 */
@Entity
@Table(name = "answer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Answer.findAll", query = "SELECT a FROM Answer a"),
    @NamedQuery(name = "Answer.findByAnswerId", query = "SELECT a FROM Answer a WHERE a.answerId = :answerId"),
    @NamedQuery(name = "Answer.findByAnswer", query = "SELECT a FROM Answer a WHERE a.answer = :answer"),
    @NamedQuery(name = "Answer.findByRequired", query = "SELECT a FROM Answer a WHERE a.required = :required"),
    @NamedQuery(name = "Answer.findByDone", query = "SELECT a FROM Answer a WHERE a.done = :done")})
public class Answer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "answer_id")
    private Integer answerId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "answer")
    private String answer;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "required")
    private String required;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "done")
    private String done;
    @JoinColumn(name = "FK_user", referencedColumnName = "user_id")
    @ManyToOne(optional = false)
    private User fKuser;
    @JoinColumn(name = "FK_piece", referencedColumnName = "piece_id")
    @ManyToOne(optional = false)
    private Piece fKpiece;
    @JoinColumn(name = "FK_question", referencedColumnName = "question_id")
    @ManyToOne(optional = false)
    private Question fKquestion;

    public Answer() {
    }

    public Answer(Integer answerId) {
        this.answerId = answerId;
    }

    public Answer(Integer answerId, String answer, String required, String done) {
        this.answerId = answerId;
        this.answer = answer;
        this.required = required;
        this.done = done;
    }

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getRequired() {
        return required;
    }

    public void setRequired(String required) {
        this.required = required;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
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

    public Question getFKquestion() {
        return fKquestion;
    }

    public void setFKquestion(Question fKquestion) {
        this.fKquestion = fKquestion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (answerId != null ? answerId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Answer)) {
            return false;
        }
        Answer other = (Answer) object;
        if ((this.answerId == null && other.answerId != null) || (this.answerId != null && !this.answerId.equals(other.answerId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Answer[ answerId=" + answerId + " ]";
    }
    
}
