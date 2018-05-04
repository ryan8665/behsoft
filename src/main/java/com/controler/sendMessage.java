/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controler;

import com.data.Model;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Ryan
 */
@ManagedBean
@ViewScoped
public class sendMessage {

    private String SenderID;
    private String messageSubject;
    private String messageBody;
    Model om = new Model();
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderID() {
        return SenderID;
    }

    public void setSenderID(String SenderID) {
        this.SenderID = SenderID;
    }

    public String getMessageSubject() {
        return messageSubject;
    }

    public void setMessageSubject(String messageSubject) {
        this.messageSubject = messageSubject;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public void insertMessage() {
        boolean res = true;
        res = om.insert("INSERT INTO `message` (`message_id`, `text`, `sending_date`, `is_read`, `FK_user_sender`, `FK_user_receiver`, `subject`) VALUES (NULL, '" + messageBody + "', CURRENT_TIMESTAMP, 0 , '" + id + "', '1', '" + messageSubject + "');");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "لطفا فیلدهای ضروری را پر کنید", "خطا"));
        } else {
            com.controler.sendSms.sendMessage("1", id);
            om.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, 'ارسال درخواست به پشتیبانی‌', CURRENT_TIMESTAMP, '" + id + "');");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "با موفقیت ثبت شد", "ثبت شد"));
            resetUSerLIst();
        }
    }
    
    

    public void resetUSerLIst() {

        messageSubject = "";
        messageBody = "";
    }

    public void hIDseter(String id) {
        this.id = id;
    }

}
