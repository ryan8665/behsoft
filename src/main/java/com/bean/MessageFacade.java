/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.data.Model;
import com.entity.Message;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
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
@RequestScoped
public class MessageFacade extends AbstractFacade<Message> {

    private String rfrom, rdate, rsubject, rbody;
    private String selectedMessage, reply;
    private String unreadMsg = "0", whattoDo = "0";

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }

    public String getUnreadMsg() {
        return unreadMsg;
    }

    public void setUnreadMsg(String unreadMsg) {
        this.unreadMsg = unreadMsg;
    }

    public String getWhattoDo() {
        return whattoDo;
    }

    public void setWhattoDo(String whattoDo) {
        this.whattoDo = whattoDo;
    }

    public String getSelectedMessage() {
        return selectedMessage;
    }

    public void setSelectedMessage(String selectedMessage) {
        this.selectedMessage = selectedMessage;
    }

    public String getRfrom() {
        return rfrom;
    }

    public void setRfrom(String rfrom) {
        this.rfrom = rfrom;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getRsubject() {
        return rsubject;
    }

    public void setRsubject(String rsubject) {
        this.rsubject = rsubject;
    }

    public String getRbody() {
        return rbody;
    }

    public void setRbody(String rbody) {
        this.rbody = rbody;
    }

    public Model getOm() {
        return om;
    }

    public void setOm(Model om) {
        this.om = om;
    }

    private String id;
    Model om = new Model();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessageFacade() {
        super(Message.class);
    }

    public List<Message> getMessage() {

        return em.createNamedQuery("Message.inerjoin").setParameter("userId", Integer.parseInt(id)).getResultList();
        //return em.createNamedQuery("Message.all").getResultList();

    }

    public List<Message> inboxMessage() {

        return em.createNamedQuery("Message.inerjoin").setParameter("userId", Integer.parseInt(id)).getResultList();
        //return em.createNamedQuery("Message.all").getResultList();

    }

    public List<Message> sentMessage() {

        return em.createNamedQuery("Message.inerjoinSent").setParameter("userId", Integer.parseInt(id)).getResultList();
        //return em.createNamedQuery("Message.all").getResultList();

    }

    public void hIDseter(String id) {
        this.id = id;
    }

    public String getUnreadMessage(String a) {
        try {
            return unreadMsg = om.select("SELECT count(`message_id`) FROM `message` inner join user on `FK_user_receiver` = user_id WHERE `is_read` = 0 and `FK_user_receiver` = " + a);
        } catch (Exception e) {
            unreadMsg = "0";
            return "0";
        }

    }

    public String getAlarm(String a) {
        try {
            return whattoDo = om.select("SELECT count(`maintenace_id`) FROM `maintenance` inner join `piece` on `FK_piece` = piece_id inner join section on FK_section = section_id inner join hospital on FK_hospital = hospital_id WHERE hospital_id = " + a + " and `repair_date` is null ");
        } catch (Exception e) {
            whattoDo = "0";
            return "0";
        }

    }

    public String getUnreadMessageUser(String a) {
        try {
            return om.select("SELECT count(`message_id`) FROM `message` inner join user on `FK_user_receiver` = user_id WHERE `is_read` = 0 and user_id = " + a);
        } catch (Exception e) {
            return "0";
        }

    }

    public void onRowSelect(SelectEvent event) {
        List<Message> lv = null;
        rfrom = "";
        rdate = "";
        rsubject = "";
        rbody = "";
        int temID = ((Message) event.getObject()).getMessageId();
        lv = em.createNamedQuery("Message.findByID").setParameter("id", temID).getResultList();
        for (Message m : lv) {
            rsubject = m.getSubject();
            rbody = m.getText();
            rdate = m.getSendingDate().toString();
            rfrom = m.getFKusersender().getName() + " " + m.getFKusersender().getFamily();
            fromID = m.getFKusersender().getUserId().toString();
            toID = m.getFKuserreceiver().getUserId().toString();
        }

        em.createQuery("update Message m set m.isRead = true where m.messageId=" + temID).executeUpdate();

    }

    public void onRowSelectTO(SelectEvent event) {
        List<Message> lv = null;
        rfrom = "";
        rdate = "";
        rsubject = "";
        rbody = "";
        int temID = ((Message) event.getObject()).getMessageId();
        lv = em.createNamedQuery("Message.findByID").setParameter("id", temID).getResultList();
        for (Message m : lv) {
            rsubject = m.getSubject();
            rbody = m.getText();
            rdate = m.getSendingDate().toString();
            rfrom = m.getFKuserreceiver().getName() + " " + m.getFKuserreceiver().getFamily();
            fromID = m.getFKusersender().getUserId().toString();
            toID = m.getFKuserreceiver().getUserId().toString();
        }

    }
    String fromID, toID;

    public void insertMessageReply() {
        boolean res = true;
        res = om.insert("INSERT INTO `message` (`message_id`, `text`, `sending_date`, `is_read`, `FK_user_sender`, `FK_user_receiver`, `subject`) VALUES (NULL, '" + reply + "', CURRENT_TIMESTAMP, 0 , '" + id + "', '" + fromID + "', 'پاسخ به: " + rsubject + "');");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "لطفا فیلدهای ضروری را پر کنید", "خطا"));
        } else {
            com.controler.sendSms.sendMessage(fromID, id);
            om.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, 'ارسال پاسخ به پیام‌', CURRENT_TIMESTAMP, '" + id + "');");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "با موفقیت ثبت شد", "ثبت شد"));
            reply = "";

        }
    }

}
