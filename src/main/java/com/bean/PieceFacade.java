/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.data.Model;
import com.entity.Calibration;
import com.entity.DeviceType;
import com.entity.Maintenance;
import com.entity.Message;
import com.entity.Piece;
import com.entity.Section;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javax.ejb.Stateless;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.ServletException;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Ryan
 */
@Stateless
@Named
@ManagedBean
@ViewScoped
public class PieceFacade extends AbstractFacade<Piece> {

    private String id, hid, ipm;
    private String section, devicename, deviceType, amval, serial, shenase, search, amvalSelected,deviceid;

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getIpm() {
        return ipm;
    }

    public String getAmvalSelected() {
        return amvalSelected;
    }

    public void setAmvalSelected(String amvalSelected) {
        this.amvalSelected = amvalSelected;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
    private Boolean disabled = true;
    private int idSelected;
    private String g1, g2, g3, g4, g5, g6, g7, g8, g9, h10, g11, g12, g13, g14;

    public String getG1() {
        return g1;
    }

    public void setG1(String g1) {
        this.g1 = g1;
    }

    public String getG2() {
        return g2;
    }

    public void setG2(String g2) {
        this.g2 = g2;
    }

    public String getG3() {
        return g3;
    }

    public void setG3(String g3) {
        this.g3 = g3;
    }

    public String getG4() {
        return g4;
    }

    public void setG4(String g4) {
        this.g4 = g4;
    }

    public String getG5() {
        return g5;
    }

    public void setG5(String g5) {
        this.g5 = g5;
    }

    public String getG6() {
        return g6;
    }

    public void setG6(String g6) {
        this.g6 = g6;
    }

    public String getG7() {
        return g7;
    }

    public void setG7(String g7) {
        this.g7 = g7;
    }

    public String getG8() {
        return g8;
    }

    public void setG8(String g8) {
        this.g8 = g8;
    }

    public String getG9() {
        return g9;
    }

    public void setG9(String g9) {
        this.g9 = g9;
    }

    public String getH10() {
        return h10;
    }

    public void setH10(String h10) {
        this.h10 = h10;
    }

    public String getG11() {
        return g11;
    }

    public void setG11(String g11) {
        this.g11 = g11;
    }

    public String getG12() {
        return g12;
    }

    public void setG12(String g12) {
        this.g12 = g12;
    }

    public String getG13() {
        return g13;
    }

    public void setG13(String g13) {
        this.g13 = g13;
    }

    public int getIdSelected() {
        return idSelected;
    }

    public void setIdSelected(int idSelected) {
        this.idSelected = idSelected;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
    Model om = new Model();

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getDevicename() {
        return devicename;
    }

    public void setDevicename(String devicename) {
        this.devicename = devicename;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getAmval() {
        return amval;
    }

    public void setAmval(String amval) {
        this.amval = amval;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getShenase() {
        return shenase;
    }

    public void setShenase(String shenase) {
        this.shenase = shenase;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PieceFacade() {
        super(Piece.class);
    }

    public void hIDseter(String id, String hid) {
        this.id = id;
        this.hid = hid;
    }
    List<Piece> lsMain = new ArrayList<>();

    public List<Piece> getLsMain() {
        return lsMain;
    }

    public void setLsMain(List<Piece> lsMain) {
        this.lsMain = lsMain;
    }

    public List<Piece> getPiece() {
        lsMain = em.createNamedQuery("Piece.findByHId").setParameter("hid", Integer.parseInt(hid)).getResultList();
        return lsMain;
    }

    public String getTodayPiece() {

        try {
            return om.select("SELECT count(`piece_id`) FROM `piece` inner join section on FK_section = section_id WHERE DATE(subdate) = DATE(NOW()) and FK_hospital = " + hid);
        } catch (Exception e) {
            return "0";
        }

    }

    public void setDeviceTypeAjx() {
        this.deviceType = deviceType;
    }

    public String todayDate() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/dd/MM");
        Date date = new Date();
        return "sdsfgsdg";
    }

    private List<DeviceType> ls = new ArrayList<>();

    public List<DeviceType> getLs() {
        return ls;
    }

    public void setLs(List<DeviceType> ls) {
        this.ls = ls;
    }

    public void chandDIv() {
        String dname;
        deviceType = "";
        try {
            String res = dname = om.select("SELECT `device_name_id` FROM `device_name` WHERE `name` = '" + devicename + "'");
            ls = em.createNamedQuery("DeviceType.findByKeyReference").setParameter("id", Integer.parseInt(res)).getResultList();
        } catch (Exception e) {

        }

    }
    /////////////////

    public boolean cheakAmval() {

        try {
            String rsamval = om.select("SELECT `property_number` FROM `piece` inner join section on `FK_section` = section_id WHERE `property_number` = '" + amval + "' AND FK_hospital = " + hid);
            if (rsamval.equals(amval)) {

                amval = "";
                serial = "";
                shenase = "";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطا", "این شماره اموال موجود است"));
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطا", e.toString()));
            return false;
        }

    }

    public void cheakSerial() {

        try {

            String rsserial = om.select("SELECT `serial_number` FROM `piece` inner join section on `FK_section` = section_id WHERE `serial_number` = '" + serial + "' AND FK_hospital = " + hid);
            if (rsserial.equals(serial)) {

                amval = "";
                serial = "";
                shenase = "";
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطا", "این شماره سریال موجود است"));

            } else {

            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطا", e.toString()));

        }

    }

    public void setSectionForm() {
        this.section = section;
    }

    public void setShenasname() {
        if (!cheakAmval()) {
            String dvpype = om.select("SELECT `device_type_id` FROM `device_type` WHERE `model` = '" + deviceType + "'");
            String dvsection = om.select("SELECT `section_id` FROM `section` WHERE `name` = '" + section + "'");

            boolean res = true;
            res = om.insert("INSERT INTO `piece` (`property_number`, `serial_number`, `device_id`, `FK_device_type`, `FK_section`) VALUES ('" + amval + "', '" + serial + "', '" + shenase + "', '" + dvpype + "', '" + dvsection + "');");
            if (res) {

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطا", dvpype));
            } else {
                om.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, 'ثبت شناسنامه جدید " + amval + "‌', CURRENT_TIMESTAMP, '" + id + "');");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "ثبت شد", "با موفقیت ثبت شد"));
                amval = "";
                serial = "";
                shenase = "";
                disabled = true;
                getPiece();

            }
        }

    }

    public void onRowSelect(SelectEvent event) {
        idSelected = ((Piece) event.getObject()).getPieceId();
        amvalSelected = ((Piece) event.getObject()).getPropertyNumber();
        deviceid = ((Piece) event.getObject()).getFKdevicetype().getModel();
        disabled = false;
    }

    public String showIPM() {
        return idSelected + "";
    }

    public void setDisable() {
        disabled = true;
        search = "";
    }
    List<Piece> lg = new ArrayList<>();

    public List<Piece> getLg() {
        return lg;
    }

    public void setLg(List<Piece> lg) {
        this.lg = lg;
    }

    public void newCart() {
        try {
            g1 = "";
            g2 = "";
            g3 = "";
            g4 = "";
            g5 = "";
            g6 = "";
            g7 = "";
            g8 = "";
            g9 = "";
            h10 = "";
            g11 = "";
            g12 = "";
            g13 = "";

            lg = em.createNamedQuery("Piece.findByPropertyNumber").setParameter("propertyNumber", idSelected).getResultList();
            for (Piece p : lg) {
                g1 = p.getFKdevicetype().getFKdevicename().getName();
                g2 = p.getFKdevicetype().getModel();
                g3 = p.getPropertyNumber();
                g4 = p.getSerialNumber();
                g5 = p.getDeviceId();
                g6 = p.getFKdevicetype().getFKdevicename().getEmNumber() + "";
                int temp_em = Integer.parseInt(g6);
                String temp_toem = "";
                if (temp_em >= 19) {
                    temp_toem = "چهار ماه یکبار";
                }
                if (temp_em >= 15 && temp_em <= 18) {
                    temp_toem = "شش ماه یکبار";
                }
                if (temp_em >= 12 && temp_em <= 14) {
                    temp_toem = "یکسال یکبار";
                }
                if (temp_em < 12) {
                    temp_toem = "بازرسی اتفاقی‌";
                }
                g7 = temp_toem;
                g8 = p.getFKsection().getName();
                g9 = p.getFKdevicetype().getMaintenance();
                g9 = g9.replace("\n\n", "<br>");
                g9 = g9.replace("\n", "<br>");
                h10 = p.getFKdevicetype().getBriefManual();
                h10 = h10.replace("\n\n", "<br>");
                h10 = h10.replace("\n", "<br>");
                g11 = "";
                g12 = p.getFKdevicetype().getUserGuide();
                g12 = g12.replace("\n\n", "<br>");
                g12 = g12.replace("\n", "<br>");
                g13 = "";
                g14 = "";
                String rep = p.getFKdevicetype().getKeyReference();
                String[] parts2 = rep.split("\\r?\\n");
                int subsize = 0;
                String size = "height:385px;", p1 = "", p2 = "";
                subsize = parts2.length;
                int l = (parts2.length) / 2;
                if (parts2.length % 2 == 0) {
                    for (int i = 0; i < l; i++) {
                        g11 += parts2[i] + "<br>";

                    }
                } else {
                    for (int i = 0; i <= l; i++) {
                        g11 += parts2[i] + "<br>";

                    }
                }

                for (int i = Math.abs(l - parts2.length); i < parts2.length; i++) {
                    g13 += parts2[i] + "<br>";

                }
                if (rep.equals("") || rep == null) {

                    size = "height:385px;";
                } else if (subsize < 15) {

                    size = "height:385px;";
                } else if (subsize >= 15 && subsize < 18) {

                    size = "height:368px;";
                } else if (subsize >= 18 && subsize < 23) {

                    size = "height:340px;";
                } else if (subsize >= 23 && subsize < 40) {

                    size = "height:312px;";
                }
                g14 = size;
            }
        } catch (Exception e) {
            //g1 = e.toString();
        }

    }

    public String getG14() {
        return g14;
    }

    public void setG14(String g14) {
        this.g14 = g14;
    }

    public void goldenCarts() {

        em.createNamedQuery("Piece.findByPropertyNumber").setParameter("propertyNumber", idSelected).getResultList();
    }

    public void searchMain() {
        disabled = true;
        if (search.length() < 1) {
            lsMain = em.createNamedQuery("Piece.findByHId").setParameter("hid", Integer.parseInt(hid)).getResultList();
        } else {
            lsMain = em.createNamedQuery("Piece.findBySarch")
                    .setParameter("hid", Integer.parseInt(hid))
                    .setParameter("pid", "%" + search + "%")
                    .getResultList();
        }

    }

    public void setRepair() {
        System.out.println("000000000000000000000000000000000000");
        Model om = new Model();
        boolean res = true;
        res = om.insert("INSERT INTO `ryan`.`maintenance` (`maintenace_id`, `FK_failure_annunciator`, `failure_date`, `FK_repair_annunciator`, `repair_date`, `description`, `FK_maintenace_status`, `FK_piece`) VALUES "
                + "(NULL, '" + id + "', CURRENT_TIMESTAMP, '" + id + "', NULL, NULL, '1', '" + idSelected + "');");

        if (res) {
            System.out.println("1111111111111111111111111111111111111");
        } else {
            System.out.println("22222222222222222222222222222");
            om.Delete("UPDATE `piece` SET `FK_piece_status`= 2 WHERE `piece_id` = " + idSelected);
            om.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, 'ثبت گزارش خرابی‌ " + amvalSelected + "‌', CURRENT_TIMESTAMP, '" + id + "');");
            getPiece();
        }
        disabled = true;
        getPiece();
    }

    public List<Maintenance> getMaintenanceHistory() {
        return em.createNamedQuery("Maintenance.findByMPieceID").setParameter("p", idSelected).getResultList();
    }

    public List<Calibration> getCalibration() {
        return em.createNamedQuery("Calibration.findByPID").setParameter("p", idSelected).getResultList();
    }

    public void ipmid() {
        ipm = idSelected + "";
    }
}
