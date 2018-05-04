/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.data.Model;
import com.entity.Calibration;
import com.entity.CalibrationResult;
import com.entity.Piece;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
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
@ManagedBean
public class CalibrationResultFacade extends AbstractFacade<CalibrationResult> {

    private String resulte, resName, resDisc;

    public String getResDisc() {
        return resDisc;
    }

    public void setResDisc(String resDisc) {
        this.resDisc = resDisc;
    }
    private String idSelected;

    public String getResulte() {
        return resulte;
    }

    public void setResulte(String resulte) {
        this.resulte = resulte;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CalibrationResultFacade() {
        super(CalibrationResult.class);
    }

    public List<CalibrationResult> getResult() {
        return em.createNamedQuery("CalibrationResult.findAll").getResultList();
    }

    public List<Calibration> getResultHistory() {
        return lc;
    }

    public void setCalibrationResult(String selected, String user) {
        Model om = new Model();
        String property = om.select("SELECT `property_number` FROM `piece` WHERE `piece_id` = " + selected);
        boolean res = true;
        String cheak = om.select("SELECT `calibration_result_id` FROM `calibration_result` WHERE `value` = '" + resulte + "'");

        res = om.insert("INSERT INTO `calibration` (`calibration_id`, `execution_date`, `alias`, `description`, `FK_user`, `FK_piece`, `FK_calibration_result`) VALUES "
                + "(NULL, NOW(), '" + resName + "', '" + resDisc + "', '" + user + "', '" + selected + "', '" + cheak + "');");
        if (res) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطا", "لطفا فیلدهای ضروری را پر کنید"));
        } else {
            om.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, 'ثبت کنترل کیفی‌ " + property + "‌', CURRENT_TIMESTAMP, '" + user + "');");
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "با موفقیت ثبت شد"));
        }
    }
    List<Calibration> lc = new ArrayList<>();
    public void Rowselect(SelectEvent e){
           String a =  ((Calibration) e.getObject()).getFKpiece().getPropertyNumber();
           System.out.print("aaaaaaaaaaaaaaaaaaaaaaaaaaaa   "+a);
          lc = em.createNamedQuery("Calibration.findBypId").setParameter("p", a).getResultList();
    }

}
