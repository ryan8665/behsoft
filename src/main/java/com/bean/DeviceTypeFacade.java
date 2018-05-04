/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bean;

import com.data.Model;
import com.entity.DeviceType;
import com.entity.Message;
import com.entity.Piece;
import java.sql.ResultSet;
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

public class DeviceTypeFacade extends AbstractFacade<DeviceType> {
   
    private String g1, g2, g3, g4, g5, g6, g7, g8, g9,g10;
    private List<DeviceType> lsMain = new ArrayList<>();
     private List<DeviceType> lsDevice = new ArrayList<>();
    private String search,searchDevice;

    public List<DeviceType> getLsDevice() {
        return lsDevice;
    }

    public void setLsDevice(List<DeviceType> lsDevice) {
        this.lsDevice = lsDevice;
    }

    public String getSearchDevice() {
        return searchDevice;
    }

    public void setSearchDevice(String searchDevice) {
        this.searchDevice = searchDevice;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getG10() {
        return g10;
    }

    public void setG10(String g10) {
        this.g10 = g10;
    }
    private String name, model, user_guide, maintenance, brief_manual, key_reference;
    private String ename, emodel, euser_guide, emaintenance, ebrief_manual, ekey_reference;

    public List<DeviceType> getLsMain() {
        return lsMain;
    }

    public void setLsMain(List<DeviceType> lsMain) {
        this.lsMain = lsMain;
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

    public String getG9() {
        return g9;
    }

    public void setG9(String g9) {
        this.g9 = g9;
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

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getEmodel() {
        return emodel;
    }

    public void setEmodel(String emodel) {
        this.emodel = emodel;
    }

    public String getEuser_guide() {
        return euser_guide;
    }

    public void setEuser_guide(String euser_guide) {
        this.euser_guide = euser_guide;
    }

    public String getEmaintenance() {
        return emaintenance;
    }

    public void setEmaintenance(String emaintenance) {
        this.emaintenance = emaintenance;
    }

    public String getEbrief_manual() {
        return ebrief_manual;
    }

    public void setEbrief_manual(String ebrief_manual) {
        this.ebrief_manual = ebrief_manual;
    }

    public String getEkey_reference() {
        return ekey_reference;
    }

    public void setEkey_reference(String ekey_reference) {
        this.ekey_reference = ekey_reference;
    }
    private String id, hid;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getUser_guide() {
        return user_guide;
    }

    public void setUser_guide(String user_guide) {
        this.user_guide = user_guide;
    }

    public String getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(String maintenance) {
        this.maintenance = maintenance;
    }

    public String getBrief_manual() {
        return brief_manual;
    }

    public void setBrief_manual(String brief_manual) {
        this.brief_manual = brief_manual;
    }

    public String getKey_reference() {
        return key_reference;
    }

    public void setKey_reference(String key_reference) {
        this.key_reference = key_reference;
    }

    @PersistenceContext(unitName = "com.mycompany_mavenproject1_war_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeviceTypeFacade() {
        super(DeviceType.class);
    }

    public List<DeviceType> getDeviceType() {
        return em.createNamedQuery("DeviceType.findAll").getResultList();

    }
    

    public String getNameAjax(String name) {
        Model om = new Model();
        String nameID = "";
        return om.select("SELECT `device_name_id` FROM `device_name` WHERE `name` = '" + name + "'");
    }

    public void setDevise() {
        Model om = new Model();
        boolean res = true;
        String cheak = om.select("SELECT `device_type_id` FROM `device_type` WHERE `model` = '" + model + "' AND `FK_device_name` = '" + getNameAjax(name) + "'");
        if (cheak == null || cheak.equals("")) {

            res = om.insert("INSERT INTO `device_type` (`device_type_id`, `model`, `user_guide`, `maintenance`, `brief_manual`, `key_reference`, `photo`, `FK_device_name`) VALUES "
                    + "(NULL, '" + model + "', '" + user_guide + "', '" + maintenance + "', '" + brief_manual + "', '" + key_reference + "', NULL, '" + getNameAjax(name) + "');");
            if (res) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"لطفا فیلدهای ضروری را پر کنید", "خطا"));
            } else {
                om.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, 'ثبت تجهیز جدید با مدل " + model + "‌', CURRENT_TIMESTAMP, '" + id + "');");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"با موفقیت ثبت شد", "ثبت شد"));
                resetUSerLIst();
                getAllDevice();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "این مارک و مدل در پایگاه داده موجود است", "خطا"));
        }

    }

    public void resetUSerLIst() {
        name = "";
        model = "";
        user_guide = "";
        maintenance = "";
        brief_manual = "";
        key_reference = "";

    }

    public void hIDseter(String id, String hid) {
        this.id = id;
        this.hid = hid;
    }

    public void ShowDetail(String a) {
        this.emodel = "";
        this.euser_guide = "";
        this.emaintenance = "";
        this.ebrief_manual = "";
        this.ekey_reference = "";
        Model om = new Model();
        ResultSet rs;
        rs = om.result("SELECT * FROM `device_type` WHERE `device_type_id` = '" + a + "'");
        try {
            while (rs.next()) {
                this.emodel = rs.getString("model");
                this.euser_guide = rs.getString("user_guide");
                this.emaintenance = rs.getString("maintenance");
                this.ebrief_manual = rs.getString("brief_manual");
                this.ekey_reference = rs.getString("key_reference");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطا", emodel));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "خطا", e.toString()));
        }

    }

    public DeviceType getSingleDevice() {
        List<DeviceType> ls = new ArrayList<>();
        ls = em.createNamedQuery("DeviceType.findByDeviceTypeId").setParameter("1", 1).getResultList();
        return ls.get(0);

    }
    int editid = 0;
    public void onRowSelect(SelectEvent event) {
        this.emodel = "";
        this.euser_guide = "";
        this.emaintenance = "";
        this.ebrief_manual = "";
        this.ekey_reference = "";
        Model om = new Model();
        ResultSet rs;
        editid = ((DeviceType) event.getObject()).getDeviceTypeId();
        rs = om.result("SELECT * FROM `device_type` WHERE `device_type_id` = '" + ((DeviceType) event.getObject()).getDeviceTypeId() + "'");
        try {
            while (rs.next()) {
                this.emodel = rs.getString("model");
                this.euser_guide = rs.getString("user_guide");
                this.emaintenance = rs.getString("maintenance");
                this.ebrief_manual = rs.getString("brief_manual");
                this.ekey_reference = rs.getString("key_reference");

            }
        } catch (Exception e) {

        }

    }
    
    public List<DeviceType> getAllGoldenCarts() {
         lsMain = em.createNamedQuery("DeviceType.findAllCarts").getResultList();
         return lsMain;
    }
     public List<DeviceType> getAllDevice() {
         lsDevice = em.createNamedQuery("DeviceType.findAll").getResultList();
          
         return lsDevice;
    }

    public void onRowSelectGetGoldenCart(SelectEvent event) {
        int id = ((DeviceType) event.getObject()).getDeviceTypeId();
        g1="";g2="";g3="";g4="";g5="";g6="";g7="";g8="";g9="";g10="";
        List<DeviceType> lg = new ArrayList<>();
        lg = em.createNamedQuery("DeviceType.findByDeviceTypeId").setParameter("deviceTypeId", id).getResultList();
        for (DeviceType d : lg) {
            g1 = d.getFKdevicename().getName();
            g2 = d.getModel();
            g3 = d.getFKdevicename().getEmNumber() + "";
            int temp_em = Integer.parseInt(g3);
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
            g4 = temp_toem;

            g5 = d.getMaintenance();
            g5 =  g5.replace("\n\n", "<br>");
            g5 =  g5.replace("\n", "<br>");
            g6 = d.getBriefManual();
             g6 =  g6.replace("\n\n", "<br>");
            g6 =  g6.replace("\n", "<br>");
            

            g7 = d.getUserGuide();
             g7 =  g7.replace("\n\n", "<br>");
            g7 =  g7.replace("\n", "<br>");

            String rep = d.getKeyReference();
            String[] parts2 = rep.split("\\r?\\n");
            int subsize = 0;
            String size = "height:385px;", p1 = "", p2 = "";
            subsize = parts2.length;
            int l = (parts2.length) / 2;
            if (parts2.length % 2 == 0) {
                for (int i = 0; i < l; i++) {
                    g8 += parts2[i] + "<br>";

                }
            } else {
                for (int i = 0; i <= l; i++) {
                    g8 += parts2[i] + "<br>";

                }
            }

            for (int i = Math.abs(l - parts2.length); i < parts2.length; i++) {
                g9 += parts2[i] + "<br>";

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
                g10 = size;
        }

    }
    public void searchMain() {
       
        if (search.length() < 1) {
            lsMain = em.createNamedQuery("DeviceType.findAllCarts").getResultList();
        } else {
            System.out.println("aaaaaaaaa   "+search);
            lsMain = em.createNamedQuery("DeviceType.findByModel")
                    .setParameter("model", "%"+search+"%")
                    .getResultList();
        }

    }
    public void searchDeviceB() {
       
        if (searchDevice.length() < 1) {
            lsDevice = em.createNamedQuery("DeviceType.findAll").getResultList();
        } else {
            System.out.println("aaaaaaaaa   "+searchDevice);
            lsDevice = em.createNamedQuery("DeviceType.findByModel")
                    .setParameter("model", "%"+searchDevice+"%")
                    .getResultList();
        }

    }
    public void editDevise() {
        Model om = new Model();
        boolean res = true;
        

            res = om.insert("UPDATE `ryan`.`device_type` SET `user_guide` = '"+euser_guide+"', `maintenance` = '"+emaintenance+"', `brief_manual` = '"+ebrief_manual+"', `key_reference` = '"+ekey_reference+"' WHERE `device_type`.`device_type_id` = "+editid+";");
            if (res) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"لطفا فیلدهای ضروری را پر کنید", "خطا"));
            } else {
                om.insert("INSERT INTO `log` (`log_id`, `description`, `log_date`, `FK_user`) VALUES (NULL, 'ویرایش تجهیز با مدل " + emodel + "‌', CURRENT_TIMESTAMP, '" + id + "');");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"با موفقیت ویرایش شد", "ویرایش شد"));
                resetUSerLIst();
            }
        

    }
}
