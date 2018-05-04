/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.data.Model;
import com.entity.News;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Ryan
 */
@Stateless
@Named
public class NewsFacade extends AbstractFacade<News> {

    private String id, subject, body, date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NewsFacade() {
        super(News.class);
    }

    public List<News> getNews() {
        return em.createNamedQuery("News.findAll").getResultList();
    }

    public void onRowSelect(SelectEvent event) throws SQLException {
        int temID = ((News) event.getObject()).getId();
        Model om = new Model();
        ResultSet rs = om.result("SELECT * FROM `news` WHERE `id` = " + temID);
        while (rs.next()) {
            subject = rs.getString("disc");
            body = rs.getString("body");
            date = rs.getString("date");

        }
    }

}
