/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controler;

import com.entity.Piece;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Ryan
 */
@ManagedBean
@ViewScoped
public class Barcode {
    String property,serial;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
    public void onRowSelect(SelectEvent event) {
        serial = ((Piece) event.getObject()).getSerialNumber();

    }
    
}
