package com.utility;

/*
 * Copyright 2009-2014 PrimeTek.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
import com.controler.login;
import com.data.Model;
import java.io.Serializable;
import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;

@ManagedBean
public class ChartView implements Serializable {

    Model om = new Model();
    FacesContext context = FacesContext.getCurrentInstance();
    HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
    HttpSession httpSession = request.getSession(false);
    login reqdObj = (login) httpSession.getAttribute("login");
    String hid = reqdObj.getHospital_ID();
    private LineChartModel lineModel;
    private PieChartModel pieModel;

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    @PostConstruct
    public void init() {
        initLinearModel();
        initPieModel();
    }

    public LineChartModel getLineModel() {
        return lineModel;
    }

    public PieChartModel getPieModel() {
        return pieModel;
    }

    private void initLinearModel() {
        lineModel = new LineChartModel();
        // lineModel.setTitle("گزارش ۵ روز گذشته");
        lineModel.setLegendPosition("e");
        lineModel.setExtender("skinChart");
        lineModel.setAnimate(true);
        lineModel.getAxes().put(AxisType.X, new CategoryAxis(""));
        Axis yAxis = lineModel.getAxis(AxisType.Y);
        yAxis.setMin(0);
        //yAxis.setMax(10);
        String a = "",b="",c="",d="",e="";
        LineChartSeries series1 = new LineChartSeries();
        series1.setLabel("شناسنامه");
        Calendar cal = Calendar.getInstance();
       
        try {
             cal.add(Calendar.DATE, 0);
          a =  Utilities.justDay(cal.getTime());
          cal.add(Calendar.DATE, -1);
           b =  Utilities.justDay(cal.getTime());
            cal.add(Calendar.DATE, -1);
           c =  Utilities.justDay(cal.getTime());
            cal.add(Calendar.DATE, -1);
           d =  Utilities.justDay(cal.getTime());
            cal.add(Calendar.DATE, -1);
           e =  Utilities.justDay(cal.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(ChartView.class.getName()).log(Level.SEVERE, null, ex);
        }
        series1.set(e, Integer.parseInt(om.select("SELECT count(`piece_id`) date FROM `piece` inner join section on `FK_section` = section_id where FK_hospital = " + hid + " AND DATE(`subdate`) = CURDATE() - 4 ")));
        series1.set(d, Integer.parseInt(om.select("SELECT count(`piece_id`) date FROM `piece` inner join section on `FK_section` = section_id where FK_hospital = " + hid + " AND DATE(`subdate`) = CURDATE() - 3 ")));
        series1.set(c, Integer.parseInt(om.select("SELECT count(`piece_id`) date FROM `piece` inner join section on `FK_section` = section_id where FK_hospital = " + hid + " AND DATE(`subdate`) = CURDATE() - 2 ")));
        series1.set(b, Integer.parseInt(om.select("SELECT count(`piece_id`) date FROM `piece` inner join section on `FK_section` = section_id where FK_hospital = " + hid + " AND DATE(`subdate`) = CURDATE() - 1 ")));
        series1.set(a, Integer.parseInt(om.select("SELECT count(`piece_id`) date FROM `piece` inner join section on `FK_section` = section_id where FK_hospital = " + hid + " AND DATE(`subdate`) = CURDATE()")));

        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("تعمیرات");

        series2.set(e, Integer.parseInt(om.select(" SELECT count(`maintenace_id`) date FROM `maintenance` inner join piece on `FK_piece` = piece_id inner join section on `FK_section` = section_id where FK_hospital = " + hid + " AND DATE(`repair_date`) = CURDATE() - 4 ")));
        series2.set(d, Integer.parseInt(om.select(" SELECT count(`maintenace_id`) date FROM `maintenance` inner join piece on `FK_piece` = piece_id inner join section on `FK_section` = section_id where FK_hospital = " + hid + " AND DATE(`repair_date`) = CURDATE() - 3 ")));
        series2.set(c, Integer.parseInt(om.select(" SELECT count(`maintenace_id`) date FROM `maintenance` inner join piece on `FK_piece` = piece_id inner join section on `FK_section` = section_id where FK_hospital = " + hid + " AND DATE(`repair_date`) = CURDATE() - 2 ")));
        series2.set(b, Integer.parseInt(om.select(" SELECT count(`maintenace_id`) date FROM `maintenance` inner join piece on `FK_piece` = piece_id inner join section on `FK_section` = section_id where FK_hospital = " + hid + " AND DATE(`repair_date`) = CURDATE() - 1 ")));
        series2.set(a, Integer.parseInt(om.select(" SELECT count(`maintenace_id`) date FROM `maintenance` inner join piece on `FK_piece` = piece_id inner join section on `FK_section` = section_id where FK_hospital = " + hid + " AND DATE(`repair_date`) = CURDATE() ")));

        LineChartSeries series3 = new LineChartSeries();
        series3.setLabel("IPM");

        series3.set(e, Integer.parseInt(om.select(" SELECT count(DISTINCT `FK_piece`) FROM `answer` inner join piece on `FK_piece` = piece_id inner join section on `FK_section` = section_id where FK_hospital = " + hid + " AND DATE(`doDate`) = CURDATE() - 4 ")));
        series3.set(d, Integer.parseInt(om.select(" SELECT count(DISTINCT `FK_piece`) FROM `answer` inner join piece on `FK_piece` = piece_id inner join section on `FK_section` = section_id where FK_hospital = " + hid + " AND DATE(`doDate`) = CURDATE() - 3 ")));
        series3.set(c, Integer.parseInt(om.select(" SELECT count(DISTINCT `FK_piece`) FROM `answer` inner join piece on `FK_piece` = piece_id inner join section on `FK_section` = section_id where FK_hospital = " + hid + " AND DATE(`doDate`) = CURDATE() - 2 ")));
        series3.set(b, Integer.parseInt(om.select(" SELECT count(DISTINCT `FK_piece`) FROM `answer` inner join piece on `FK_piece` = piece_id inner join section on `FK_section` = section_id where FK_hospital = " + hid + " AND DATE(`doDate`) = CURDATE() - 1 ")));
        series3.set(a, Integer.parseInt(om.select(" SELECT count(DISTINCT `FK_piece`) FROM `answer` inner join piece on `FK_piece` = piece_id inner join section on `FK_section` = section_id where FK_hospital = " + hid + " AND DATE(`doDate`) = CURDATE() ")));

        lineModel.addSeries(series1);
        lineModel.addSeries(series2);
        lineModel.addSeries(series3);
    }

    private void initPieModel() {
        pieModel = new PieChartModel();

        pieModel.set("Brand 1", 540);
        pieModel.set("Brand 2", 325);
        pieModel.set("Brand 3", 702);
        pieModel.set("Brand 4", 421);

        pieModel.setExtender("skinPie");
    }

}
