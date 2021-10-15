/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.controllers;

import ae.etisalatdigital.iot.ops.utility.sync.buses.BOMMeterBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.RequestBus;
import ae.etisalatdigital.iot.ops.utility.sync.buses.SurveyBus;
import ae.etisalatdigital.iot.ops.utility.sync.entities.Requests;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.RequestDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.SurveyDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.Surveys;

import java.io.File;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.poi.ss.usermodel.charts.ChartData;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.DateAxis;
import org.primefaces.model.chart.DonutChartModel;
import org.primefaces.model.chart.LegendPlacement;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;
import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;


/**
 *
 * @author appadmin
 */
@Named(value = "dashboardViewController")
@SessionScoped
public class DashboardViewController implements Serializable {
    
    private int requeststotal;
    private int requeststarted;
    private int requestsincomplete;
    private int villasconnected;
    private int requestscomplete;
    private int surveyComplted;
    private int surveyRejected;
    private int surveyScheduled;
    
    private int metersTotal;
    private int metersTotalAMI;
    private int metersTotalNONAMI;
    
    private int panelsaccepted;
    private int Sensorsaccepted;
    private int readyforinstallation;
    private int readyforinstallationready;
    private int configwithzerotouch;
    private int userstotal;
    private int bomApproved;
    private int instaltdy;
    private int surveytdy;
    private int appsched;
    
    private int ain;
    private int abudhabi;
    
    private final int panelcost = 600;
    private int sensorcost = 100;
    private int repeatercost = 400;
    private int inprogressinstallation;
    private int partiallyacceptedpanels;
    private int notacceptedsensors;
    private int sensorsMissing;
    private String totalcost;
    private String totalpanelcost;
    private String totalsensorscost;
    private String totalrepeatercost;
    private List<Requests> PacReqs = new ArrayList<>();
    private List<Requests> onlinelist = new ArrayList<>();
    private List<Requests> onfflinelist = new ArrayList<>();
    private List<Requests> unreglist = new ArrayList<>();
    private List<Requests> PACwithIssues = new ArrayList<>();
    private List<Requests> Requestcompletelist = new ArrayList<>();
    private List<RequestDTO> devicesBD = new ArrayList<>();
    private List<RequestDTO> devicesBD_Filtered;
    private List<Requests> devicesBDAccepted = new ArrayList<>();
    private List<RequestDTO> devicesBDAccepted_Filtered;
    private List<Surveys> surveysPOC = new ArrayList<>();
    private List<SurveyDTO> surveysPOC_Filtered;
    
    private DateTime currentDate;
    
    //private List<installedExport> installedyesterday_list = new ArrayList<>();
    //private List<InstallationLogExport> installedLog = new ArrayList<>();
    private int appdy;
    private int MissingHW;
    private int ExtraHW;
    private LineChartModel dateModel;
    private LineChartModel dateModelMeters;
    private BarChartModel  mixedModel;
    private MapModel simpleModel;
    private PieChartModel pieModel1;
    
    
    private int nCompletedSites;
    private int nCompletedMeters;
    private int nFailedSites;
    private int nFailedMeters;
    private int nPendingSites;
    private int nPendingMeters;
    private int nTotalSites;
    private int nTotalMeters;
    
    private int nTotalSitesAUH;
    private int nTotalSitesAAN;
    private int nTotalMetersWaterAUH;
    private int nTotalMetersElecAUH;
    private int nTotalMetersWaterAAN;
    private int nTotalMetersElecAAN;
    
    private int nCompletedSitesAUH;
    private int nCompletedSitesAAN;
    private int nFailedSitesAUH;
    private int nFailedSitesAAN;
    
    private int nCompletedMetersWaterAUH;
    private int nCompletedMetersElecAUH;
    private int nCompletedMetersWaterAAN;
    private int nCompletedMetersElecAAN;
    
    private int nFailedMetersAUH;
    private int nFailedMetersAAN;
    
    private DonutChartModel pieModel1FeasibilityADDC;
    private DonutChartModel pieModel1FeasibilityAADC;
    private DonutChartModel donutModelGateways;
    private DonutChartModel donutModelMetersADDC;
    private DonutChartModel donutModelMetersAADC;
    private DonutChartModel donutModelGatewaysVendor;
    private DonutChartModel donutModelNBIOTFeasibility;
    private MapModel simpleModelFeasibilityADDC;
    private MapModel simpleModelFeasibilityAADC;
    private MapModel simpleModelNBIOTFeasibility;
    
    private LineChartModel cartesianLinerModel;
    
    ///private String queryCompleted = "select SUBSTRING(cast(DUM_Date as nvarchar(11)),1,11) dateid, Dum_Label  from dtdummy where dum_status = 'Completed' and DUM_UtilityNumber like 'UN-%'";
    ///private String queryFailed = "select SUBSTRING(cast(DUM_Date as nvarchar(11)),1,11) dateid, Dum_Label  from dtdummy where dum_status = 'Failed' and DUM_UtilityNumber like 'UN-%'";
    
    private String queryCompleted = "select SUBSTRING(cast(survey_Date as nvarchar(11)),1,11) dateid, Utility_Number  from US_Survey where survey_status = 'Completed'";
    private String queryFailed = "select SUBSTRING(cast(survey_Date as nvarchar(11)),1,11) dateid, Utility_Number  from US_Survey where survey_status = 'Failed'";
    
    
    ///private String queryCompletedMtrs = "select SUBSTRING(cast(DUM_Date as nvarchar(11)),1,11) dateid, Dum_Label  from dtdummy where dum_status = 'Completed' and DUM_UtilityNumber like 'MTR-%'";
    ///private String queryFailedMtrs = "select SUBSTRING(cast(DUM_Date as nvarchar(11)),1,11) dateid, Dum_Label  from dtdummy where dum_status = 'Failed' and DUM_UtilityNumber like 'MTR-%'";
    
    private String queryCompletedMtrs = "select SUBSTRING(cast(survey_Date as nvarchar(11)),1,11) dateid, METER_Serial  from US_Survey us  left join BOM_Meter bm on bm.BOM_ID = us.bom_id where survey_status = 'Completed'";
    private String queryFailedMtrs = "select SUBSTRING(cast(survey_Date as nvarchar(11)),1,11) dateid, Utility_Number  from US_Survey us left join BOM_Meter bm on bm.BOM_ID = us.bom_id where survey_status = 'Failed'";
    
    
    private String qCompletedSites = "select count(*) from us_survey where survey_status = 'Completed'";
    private String qCompletedMeters = "select count(*) from bom_meter where bom_id in (select bom_id from us_survey where survey_status = 'Completed')";
    
    private String qCompletedSitesAUH = "select count(*) from us_survey where Emirate_Name ='Abu Dhabi' and survey_status = 'Completed'";
    private String qCompletedSitesAAN = "select count(*) from us_survey where Emirate_Name ='Al Ain' and survey_status = 'Completed'";
    private String qFailedSitesAUH = "select count(*) from us_survey where Emirate_Name ='Abu Dhabi' and survey_status = 'Failed'";
    private String qFailedSitesAAN = "select count(*) from us_survey where Emirate_Name ='Al Ain' and survey_status = 'Failed'";
    
    
    private String qTotalSites = "select count(*) from us_survey where survey_status = 'Completed' or survey_status = 'Failed'";
    private String qTotalMeters = "select count(*) from bom_meter where bom_id in (select bom_id from us_survey where survey_status = 'Completed' or survey_status = 'Failed')";
    
    private String qTotalSitesAUH = "select count(*) from us_survey where Emirate_Name ='Abu Dhabi' and (survey_status = 'Completed' or survey_status = 'Failed')";
    private String qTotalSitesAAN = "select count(*) from us_survey where Emirate_Name ='Al Ain' and ( survey_status = 'Completed' or survey_status = 'Failed')";
    
    private String qTotalMetersWaterAUH = "select count(*) from bom_meter where BOM_METER_Type = 'WATER' and bom_id in (select bom_id from us_survey where Emirate_Name ='Abu Dhabi' and (survey_status = 'Completed' or survey_status = 'Failed'))";
    private String qTotalMetersElecAUH = "select count(*) from bom_meter where BOM_METER_Type = 'ELECTRIC' and bom_id in (select bom_id from us_survey where Emirate_Name ='Abu Dhabi' and ( survey_status = 'Completed' or survey_status = 'Failed'))";
    
    private String qTotalMetersWaterAAN = "select count(*) from bom_meter where BOM_METER_Type = 'WATER' and bom_id in (select bom_id from us_survey where Emirate_Name ='Al Ain' and (survey_status = 'Completed' or survey_status = 'Failed'))";
    private String qTotalMetersElecAAN = "select count(*) from bom_meter where BOM_METER_Type = 'ELECTRIC' and bom_id in (select bom_id from us_survey where Emirate_Name ='Al Ain' and (survey_status = 'Completed' or survey_status = 'Failed'))";
    
    
    private String qCompletedMetersWaterAUH = "select count(*) from bom_meter where BOM_METER_Type = 'WATER' and bom_id in (select bom_id from us_survey where Emirate_Name ='Abu Dhabi' and survey_status = 'Completed' )";
    private String qCompletedMetersElecAUH = "select count(*) from bom_meter where BOM_METER_Type = 'ELECTRIC' and bom_id in (select bom_id from us_survey where Emirate_Name ='Abu Dhabi' and  survey_status = 'Completed')";
    
    private String qCompletedMetersWaterAAN = "select count(*) from bom_meter where BOM_METER_Type = 'WATER' and bom_id in (select bom_id from us_survey where Emirate_Name ='Al Ain' and survey_status = 'Completed')";
    private String qCompletedMetersElecAAN = "select count(*) from bom_meter where BOM_METER_Type = 'ELECTRIC' and bom_id in (select bom_id from us_survey where Emirate_Name ='Al Ain' and survey_status = 'Completed')";
    
    private String qFailedMetersAUH = "select count(*) from bom_meter where  bom_id in (select bom_id from us_survey where Emirate_Name ='Abu Dhabi' and  survey_status = 'Failed')";
    private String qFailedMetersAAN = "select count(*) from bom_meter where  bom_id in (select bom_id from us_survey where Emirate_Name ='Al Ain' and  survey_status = 'Failed')";
    
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager embenny;
    
    @Inject
    private RequestBus moiRequestBus;
    
    @Inject
    private SurveyBus surveyBus;
    
    @Inject
    private BOMMeterBus bomMetersBus;
    
    public DashboardViewController() {
    }

    public DateTime getCurrentDate() {
        currentDate = DateTime.now();
        return currentDate;
    }
    
    
    
    
    
    public int getRequeststotal() {
        requeststotal = (int) embenny.createNativeQuery("select count(*) from dbo.US_Request where active_bom > 200").getResultList().get(0);
        return requeststotal;
    }

    public int getSurveyComplted() {
        surveyComplted = 46;//(int) embenny.createNativeQuery("select count(*) from dbo.US_Request where active_bom > 200 and REQUEST_Status = 'Survey Completed'").getResultList().get(0);
        return surveyComplted;
    }

    public int getSurveyRejected() {
        surveyRejected = 3;//(int) embenny.createNativeQuery("select count(*) from dbo.US_Request where active_bom > 200 and  REQUEST_Status = 'Survey Rejected'").getResultList().get(0);
        return surveyRejected;
    }

    public int getSurveyScheduled() {
        surveyScheduled = 867;//(int) embenny.createNativeQuery("select count(*) from dbo.US_Request where active_bom > 200 and REQUEST_Status = 'Survey Scheduled'").getResultList().get(0);
        return surveyScheduled;
    }

    public int getMetersTotal() {
        metersTotal = (int) embenny.createNativeQuery("select count(*) from dbo.BOM_Meter where BOM_ID > 200 and Meter_Rag is not null").getResultList().get(0);
        return metersTotal;
    }

    public int getMetersTotalAMI() {
         metersTotalAMI = (int) embenny.createNativeQuery("select count(*) from dbo.BOM_Meter where BOM_ID > 200 and METER_AMI_NONAMI = 'AMR' and Meter_Rag is not null").getResultList().get(0);
        return metersTotalAMI;
    }

    public int getMetersTotalNONAMI() {
        metersTotalNONAMI = (int) embenny.createNativeQuery("select count(*) from dbo.BOM_Meter where BOM_ID > 200 and METER_AMI_NONAMI = 'NONAMR' and Meter_Rag is not null").getResultList().get(0);
        return metersTotalNONAMI;
    }
    
    

    public void setRequeststotal(int requeststotal) {
        this.requeststotal = requeststotal;
    }

    public void setSurveyComplted(int surveyComplted) {
        this.surveyComplted = surveyComplted;
    }

    public void setSurveyRejected(int surveyRejected) {
        this.surveyRejected = surveyRejected;
    }

    public void setSurveyScheduled(int surveyScheduled) {
        this.surveyScheduled = surveyScheduled;
    }

    public void setMetersTotal(int metersTotal) {
        this.metersTotal = metersTotal;
    }

    public void setMetersTotalAMI(int metersTotalAMI) {
        this.metersTotalAMI = metersTotalAMI;
    }

    public void setMetersTotalNONAMI(int metersTotalNONAMI) {
        this.metersTotalNONAMI = metersTotalNONAMI;
    }
    
    
    

    public int getRequeststarted() {
        return requeststarted;
    }

    public void setRequeststarted(int requeststarted) {
        this.requeststarted = requeststarted;
    }

    public MapModel getSimpleModel() {
        createMapmarkers();
        return simpleModel;
    }

    public MapModel getSimpleModelFeasibilityADDC() {
        createMapmarkersADDC();
        return simpleModelFeasibilityADDC;
    }

    public MapModel getSimpleModelFeasibilityAADC() {
        createMapmarkersAADC();
        return simpleModelFeasibilityAADC;
    }
    
    

    public void setSimpleModel(MapModel simpleModel) {
        this.simpleModel = simpleModel;
    }

    public void setSimpleModelFeasibilityADDC(MapModel simpleModelFeasibilityADDC) {
        this.simpleModelFeasibilityADDC = simpleModelFeasibilityADDC;
    }

    public void setSimpleModelFeasibilityAADC(MapModel simpleModelFeasibilityAADC) {
        this.simpleModelFeasibilityAADC = simpleModelFeasibilityAADC;
    }
    
    

    public void createMapmarkers() {
        String query2 = "select UTILITY_NUMBER,Premise_Latitude,Premise_Longitude,REQUEST_Status from dbo.US_Request where  Premise_Latitude is NOT NULL or Premise_Longitude is NOT NULL";
        HashMap<String, Integer> out = new HashMap<>();
        Vector<Object[]> out2 = (Vector<Object[]>) embenny.createNativeQuery(query2).getResultList();
        System.out.println("Marker initiated ...");
        simpleModel = new DefaultMapModel();
        for (Object[] objects : out2) {
            LatLng coord = new LatLng(Float.parseFloat(objects[1].toString()), Float.parseFloat(objects[2].toString()));
            simpleModel.addOverlay(new Marker(coord, objects[0].toString()));
        }
        
    }
    
    public void createMapmarkersADDC() {
        String query2 = "select UTILITY_NUMBER,Emirate_Region_Name,Premise_Latitude,Premise_Longitude,REQUEST_Status from dbo.US_Request a, dbo.BOM_Meter b  where a.Active_BOM = b.BOM_ID and b.METER_RAG = 'Green' and Emirate_Name = 'Abu Dhabi' and active_bom > 200  and  Premise_Latitude is NOT NULL and Premise_Longitude is NOT NULL";
        String query3 = "select UTILITY_NUMBER,Emirate_Region_Name,Premise_Latitude,Premise_Longitude,REQUEST_Status from dbo.US_Request a, dbo.BOM_Meter b  where a.Active_BOM = b.BOM_ID and b.METER_RAG = 'Red'  and Emirate_Name = 'Abu Dhabi' and active_bom > 200  and  Premise_Latitude is NOT NULL and Premise_Longitude is NOT NULL";
        String query4 = "select UTILITY_NUMBER,Emirate_Region_Name,Premise_Latitude,Premise_Longitude,REQUEST_Status from dbo.US_Request a, dbo.BOM_Meter b  where a.Active_BOM = b.BOM_ID and b.METER_RAG = 'Yellow' and Emirate_Name = 'Abu Dhabi' and active_bom > 200  and  Premise_Latitude is NOT NULL and Premise_Longitude is NOT NULL";
        Vector<Object[]> out2 = (Vector<Object[]>) embenny.createNativeQuery(query2).getResultList();
        Vector<Object[]> out3 = (Vector<Object[]>) embenny.createNativeQuery(query3).getResultList();
        Vector<Object[]> out4 = (Vector<Object[]>) embenny.createNativeQuery(query4).getResultList();
        
        System.out.println("Marker initiated ...");
        simpleModelFeasibilityADDC = new DefaultMapModel();
        
        StringBuilder ruta = new StringBuilder();
        ruta.append(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
        ruta.append("/resources/images/Green_50x50.png");
        
        StringBuilder ruta2 = new StringBuilder();
        ruta2.append(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
        ruta2.append("/resources/images/red_50x50.png");
        
        StringBuilder ruta3 = new StringBuilder();
        ruta3.append(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
        ruta3.append("/resources/images/Yellow_50x50.png");
        
        for (Object[] objects : out2) {
            LatLng coord = new LatLng(Float.parseFloat(objects[2].toString()), Float.parseFloat(objects[3].toString()));
            Marker  mrkr = new Marker(coord,objects[0].toString() + " , " + objects[1].toString());
            mrkr.setIcon(ruta.toString());
            
            simpleModelFeasibilityADDC.addOverlay(mrkr);
        }
        
        for (Object[] objects : out3) {
            LatLng coord = new LatLng(Float.parseFloat(objects[2].toString()), Float.parseFloat(objects[3].toString()));
            Marker  mrkr = new Marker(coord,objects[0].toString() + " , " + objects[1].toString());
            mrkr.setIcon(ruta2.toString());
            simpleModelFeasibilityADDC.addOverlay(mrkr);
        }
        
        
        for (Object[] objects : out4) {
            LatLng coord = new LatLng(Float.parseFloat(objects[2].toString()), Float.parseFloat(objects[3].toString()));
            Marker  mrkr = new Marker(coord,objects[0].toString() + " , " + objects[1].toString());
            mrkr.setIcon(ruta3.toString());
            simpleModelFeasibilityADDC.addOverlay(mrkr);
        }
    }
    
    public void createMapmarkersAADC() {
        String query2 = "select UTILITY_NUMBER,Emirate_Region_Name,Premise_Latitude,Premise_Longitude,REQUEST_Status from dbo.US_Request a, dbo.BOM_Meter b  where a.Active_BOM = b.BOM_ID and b.METER_RAG = 'Green' and Emirate_Name = 'Al Ain' and active_bom > 200 and  Premise_Latitude is NOT NULL and Premise_Longitude is NOT NULL";
        String query3 = "select UTILITY_NUMBER,Emirate_Region_Name,Premise_Latitude,Premise_Longitude,REQUEST_Status from dbo.US_Request a, dbo.BOM_Meter b  where a.Active_BOM = b.BOM_ID and b.METER_RAG = 'Red' and Emirate_Name = 'Al Ain' and active_bom > 200 and  Premise_Latitude is NOT NULL and Premise_Longitude is NOT NULL and Premise_Longitude like '55.%'";
        String query4 = "select UTILITY_NUMBER,Emirate_Region_Name,Premise_Latitude,Premise_Longitude,REQUEST_Status from dbo.US_Request a, dbo.BOM_Meter b  where a.Active_BOM = b.BOM_ID and b.METER_RAG = 'Yellow' and Emirate_Name = 'Al Ain' and active_bom > 200 and  Premise_Latitude is NOT NULL and Premise_Longitude is NOT NULL and Premise_Longitude like '55.%'";
        
        Vector<Object[]> out2 = (Vector<Object[]>) embenny.createNativeQuery(query2).getResultList();
        Vector<Object[]> out3 = (Vector<Object[]>) embenny.createNativeQuery(query3).getResultList();
        Vector<Object[]> out4 = (Vector<Object[]>) embenny.createNativeQuery(query4).getResultList();
        
        StringBuilder ruta = new StringBuilder();
        ruta.append(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
        ruta.append("/resources/images/Green_50x50.png");
        
        StringBuilder ruta2 = new StringBuilder();
        ruta2.append(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
        ruta2.append("/resources/images/red_50x50.png");
        
        StringBuilder ruta3 = new StringBuilder();
        ruta3.append(FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
        ruta3.append("/resources/images/Yellow_50x50.png");
        
        simpleModelFeasibilityAADC = new DefaultMapModel();
        
        for (Object[] objects : out4) {
            LatLng coord = new LatLng(Float.parseFloat(objects[2].toString()), Float.parseFloat(objects[3].toString()));
            Marker  mrkr = new Marker(coord,objects[0].toString() + " , " + objects[1].toString());
            mrkr.setIcon(ruta3.toString());
            simpleModelFeasibilityAADC.addOverlay(mrkr);
        }
        
        for (Object[] objects : out3) {
            LatLng coord = new LatLng(Float.parseFloat(objects[2].toString()), Float.parseFloat(objects[3].toString()));
            Marker  mrkr = new Marker(coord,objects[0].toString() + " , " + objects[1].toString());
            mrkr.setIcon(ruta2.toString());
            simpleModelFeasibilityAADC.addOverlay(mrkr);
        }
        
        for (Object[] objects : out2) {
            LatLng coord = new LatLng(Float.parseFloat(objects[2].toString()), Float.parseFloat(objects[3].toString()));
            Marker  mrkr = new Marker(coord,objects[0].toString() + " , " + objects[1].toString());
            mrkr.setIcon(ruta.toString());
            simpleModelFeasibilityAADC.addOverlay(mrkr);
        }
        
        
        
        
    }
    
    private void getLowSignalStrnegthAccountInfo() {
        /*
        Set<Integer> idsSet = new HashSet<Integer>();
        WPAPI w = new WPAPI(wpServer);
        w.logIn(wpUser, wpPass);
        idsSet = w.getLowSignalStrnegthAccountsIds();
        w.logOut();
        
        TypedQuery<Requests> typedQuery =  embenny.createNamedQuery("Requests.getByIds", Requests.class);
        typedQuery.setParameter("idsSet", idsSet);
        List<Requests> requestList = typedQuery.getResultList();
        
        for (Requests request : requestList) {
            //System.out.println("Account Id : " + request.getAccountId().getAccountId());
            System.out.println(request.getLatitude() + ", " + request.getLongitude());
        }
        */
    }

    public int getConfigwithzerotouch() {
        //configwithzerotouch = (int) embenny.createNativeQuery("select count(*) from dbo.US_Request where Request_Status = 34").getResultList().get(0);
        configwithzerotouch = 200;
        return configwithzerotouch;
    }

    public int getNotacceptedsensors() {
        //notacceptedsensors = (int) embenny.createNativeQuery("select count(*) from dbo.BOM_Devices where Test_Passed is null and Installed_Zone is not null and Serial_Number is not null and Serial_Number not like '123%'").getResultList().get(0);
        notacceptedsensors = 200;
        return notacceptedsensors;
    }

    public void setNotacceptedsensors(int notacceptedsensors) {
        this.notacceptedsensors = notacceptedsensors;
    }

    public void setConfigwithzerotouch(int configwithzerotouch) {
        this.configwithzerotouch = configwithzerotouch;
    }

    public PieChartModel getPieModel1() {
        createPieModel1();
        return pieModel1;
    }

    public DonutChartModel getPieModel1FeasibilityADDC() {
        createPieModelFeasibilityADDC();
        return pieModel1FeasibilityADDC;
    }

    public DonutChartModel getPieModel1FeasibilityAADC() {
        createPieModelFeasibilityAADC();
        return pieModel1FeasibilityAADC;
    }

    public DonutChartModel getDonutModelMetersADDC() {
        createDonutModelMetersADDC();
        return donutModelMetersADDC;
    }

    public DonutChartModel getDonutModelMetersAADC() {
        createDonutModelMetersAADC();
        return donutModelMetersAADC;
    }
    
    

    public DonutChartModel getDonutModelGateways() {
        createDonutModelGateways();
        return donutModelGateways;
    }

    public DonutChartModel getDonutModelGatewaysVendor() {
        createDonutModelGatewaysVendor();
        return donutModelGatewaysVendor;
    }
    
    

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }

    public void setPieModel1FeasibilityADDC(DonutChartModel pieModel1FeasibilityADDC) {
        this.pieModel1FeasibilityADDC = pieModel1FeasibilityADDC;
    }

    public void setPieModel1FeasibilityAADC(DonutChartModel pieModel1FeasibilityAADC) {
        this.pieModel1FeasibilityAADC = pieModel1FeasibilityAADC;
    }

    public void setDonutModelMetersADDC(DonutChartModel donutModelMetersADDC) {
        this.donutModelMetersADDC = donutModelMetersADDC;
    }

    public void setDonutModelMetersAADC(DonutChartModel donutModelMetersAADC) {
        this.donutModelMetersAADC = donutModelMetersAADC;
    }
    
    

    public void setDonutModelGateways(DonutChartModel donutModelGateways) {
        this.donutModelGateways = donutModelGateways;
    }

    public void setDonutModelGatewaysVendor(DonutChartModel donutModelGatewaysVendor) {
        this.donutModelGatewaysVendor = donutModelGatewaysVendor;
    }
    
    private void createMixModel() {
        mixedModel = new BarChartModel();
        
        //ChartData data = new ChartData();
        //BarChartDataSet dataSet = new BarChartDataSet();
    }
    
    
    
    private void createDateModelMeters(){
        
        HashMap<String, Integer> outCompMtr = perpareMap("MTR-COMPLETED");
        HashMap<String, Integer> outFai = perpareMap("MTR-FAILED");
        dateModelMeters = new LineChartModel();
        
        LineChartSeries seriesCompMtrs = new LineChartSeries();
        seriesCompMtrs.setLabel("Completed Meters");
        LineChartSeries series4 = new LineChartSeries();
        series4.setLabel("Failed Meters");
        
        
        for (Map.Entry<String, Integer> entry : outCompMtr.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            seriesCompMtrs.set(key, value);
        }
        
        dateModelMeters.addSeries(seriesCompMtrs);
        
        for (Map.Entry<String, Integer> entry : outFai.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            series4.set(key, value);
        }
        dateModelMeters.addSeries(series4);
        
        
        
        DateTime dt = new DateTime();
        org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("YYYY-MM-DD");
        //dateModelMeters.setTitle("Zoom for Details");
        dateModelMeters.setZoom(true);
        dateModelMeters.getAxis(AxisType.Y).setLabel("Completed Meters");
        dateModelMeters.getAxis(AxisType.Y).setMin(0);
        DateAxis axis = new DateAxis("Completion Dates");
        axis.setTickAngle(-50);
        axis.setTickFormat("%b %#d, %y");
        dateModelMeters.getAxes().put(AxisType.X, axis);
        
    }

    private void createDateModel() {
        HashMap<String, Integer> out = perpareMap("COMPLETED");
        HashMap<String, Integer> outFai = perpareMap("FAILED");
        dateModel = new LineChartModel();
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Completed Sites");
        
                
        LineChartSeries series4 = new LineChartSeries();
        series4.setLabel("Failed Sites");
        for (Map.Entry<String, Integer> entry : out.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            series2.set(key, value);
        }
        dateModel.addSeries(series2);
        
        for (Map.Entry<String, Integer> entry : outFai.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            series4.set(key, value);
        }
        dateModel.addSeries(series4);
        
        DateTime dt = new DateTime();
        org.joda.time.format.DateTimeFormatter dtf = DateTimeFormat.forPattern("YYYY-MM-DD");
        //dateModel.setTitle("");
        dateModel.setZoom(true);
        dateModel.getAxis(AxisType.Y).setLabel("Completed Sites");
        dateModel.getAxis(AxisType.Y).setMin(0);
        DateAxis axis = new DateAxis("Completion Dates");
        axis.setTickAngle(-50);
        axis.setTickFormat("%b %#d, %y");
        dateModel.getAxes().put(AxisType.X, axis);
        
    }
    
    

    private void createPieModel1() {
        pieModel1 = new PieChartModel();
        pieModel1.set("Happy", 540);
        pieModel1.set("Sad", 325);
        pieModel1.setTitle("Customer satisfaction");
        pieModel1.setLegendPosition("w");
    }
    
    private void createPieModelFeasibilityAADC() {
        int redRag = (int) embenny.createNativeQuery("select count(*) from dbo.BOM_Meter a, dbo.us_request b where BOM_ID > 200 and a.BOM_ID = b.Active_BOM and b.Emirate_Name = 'Al Ain' and METER_RAG = 'Red' and METER_RAG is not null").getResultList().get(0);
        int greenRag = (int) embenny.createNativeQuery("select count(*) from dbo.BOM_Meter a, dbo.us_request b where BOM_ID > 200 and a.BOM_ID = b.Active_BOM and b.Emirate_Name = 'Al Ain' and METER_RAG = 'Green' and METER_RAG is not null").getResultList().get(0);
        int redYellow = (int) embenny.createNativeQuery("select count(*) from dbo.BOM_Meter a, dbo.us_request b where BOM_ID > 200 and a.BOM_ID = b.Active_BOM and b.Emirate_Name = 'Al Ain' and METER_RAG = 'Yellow' and METER_RAG is not null").getResultList().get(0);
        int total = redRag + greenRag + redYellow - 53;
        pieModel1FeasibilityAADC = new DonutChartModel();
        Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
        
        circle1.put(" Feasible for Surveys ", greenRag);
        circle1.put(" Replacement Requested ", redRag);
        circle1.put(" Additional Information Requested ", redYellow - 53);
        
        
        pieModel1FeasibilityAADC.setTitle("Survey Fulfilment Feasibility (Meters - "+ total +")");
        pieModel1FeasibilityAADC.setLegendPosition("w");
        pieModel1FeasibilityAADC.setSliceMargin(3);
        pieModel1FeasibilityAADC.setShowDataLabels(true);
        pieModel1FeasibilityAADC.setDataFormat("value");
        pieModel1FeasibilityAADC.addCircle(circle1);
        //pieModel1FeasibilityAADC.setExtender("customExtender");
        pieModel1FeasibilityAADC.setSeriesColors("228B22, FF0000,FFFF00");
    }
    
    private void createDonutModelGatewaysVendor() {
        int total = 177;
        donutModelGatewaysVendor = new DonutChartModel();
        Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
        
        circle1.put(" ARC - Unknown ", 146);
        circle1.put(" ARC - Ele+Water 2:1 GW ", 1);
        circle1.put(" ARC - 1:M Euridis GW ", 2);
        circle1.put(" ARC - SCB270MB-NB-DC ", 2);
        circle1.put(" ARC - SCB270-DL-NB-DC ", 8);
        circle1.put(" ARC - SCB211-DL-NB-DC ", 5);
        circle1.put(" ARC - SCB111-URDS-NB-DC ", 4);
        circle1.put(" ARC - SCB111-MB-NB-BT ", 1);
        
        
        
        donutModelGatewaysVendor.setTitle("ARC Gateways ("+ total +")");
        donutModelGatewaysVendor.setLegendPosition("d");
        //donutModelGateways.setLegendPlacement(LegendPlacement.OUTSIDE);
        donutModelGatewaysVendor.setSliceMargin(2);
        donutModelGatewaysVendor.setShowDataLabels(true);
        donutModelGatewaysVendor.setDataFormat("value");
        donutModelGatewaysVendor.addCircle(circle1);
        
        //donutModelGateways.setExtender("customExtender");
        //
        
    }
    
    private void createDonutModelMetersAADC(){
        int total = 20;
        donutModelMetersAADC = new DonutChartModel();
        Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
        
        
        circle1.put(" Existing AMI ", 0);
        circle1.put(" Required Access or Permit / Restricted Area ", 2);
        circle1.put(" Legacy meters ", 0);
        circle1.put(" Villa under construction ", 0);
        circle1.put(" Unable to find the meter Location ", 2);
        circle1.put(" Major Civil Work required ", 16);
        
        
        donutModelMetersAADC.setTitle("AADC Meters ("+ total +")");
        donutModelMetersAADC.setLegendPosition("d");
        //donutModelGateways.setLegendPlacement(LegendPlacement.OUTSIDE);
        donutModelMetersAADC.setSliceMargin(2);
        donutModelMetersAADC.setShowDataLabels(true);
        donutModelMetersAADC.setDataFormat("value");
        donutModelMetersAADC.addCircle(circle1);
    }
    
    private void createDonutModelMetersADDC(){
        int total = 69;
        donutModelMetersADDC = new DonutChartModel();
        Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
        
        
        circle1.put(" Existing AMI ", 52);
        circle1.put(" Required Access or Permit / Restricted Area ", 11);
        circle1.put(" Legacy meters ", 2);
        circle1.put(" Villa under construction ", 2);
        circle1.put(" Unable to find the meter Location ", 2);
        circle1.put(" Major Civil Work required ", 0);
        
        
        donutModelMetersADDC.setTitle("ADDC Meters ("+ total +")");
        donutModelMetersADDC.setLegendPosition("d");
        //donutModelGateways.setLegendPlacement(LegendPlacement.OUTSIDE);
        donutModelMetersADDC.setSliceMargin(2);
        donutModelMetersADDC.setShowDataLabels(true);
        donutModelMetersADDC.setDataFormat("value");
        donutModelMetersADDC.addCircle(circle1);
    }
    
    private void createDonutModelGateways() {
        int total = 104;
        donutModelGateways = new DonutChartModel();
        Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
        
        
        circle1.put(" TELEPHONY - Ele+Water 2:1 GW ", 2);
        circle1.put(" TELEPHONY - 1:32 Meter DC ", 32);
        circle1.put(" TELEPHONY - 1:1 Meter DC ", 41);
        circle1.put(" TELEPHONY - 1:1 Meter Battery ", 14);
        
        
        donutModelGateways.setTitle("TELEPHONY Gateways ("+ total +")");
        donutModelGateways.setLegendPosition("d");
        //donutModelGateways.setLegendPlacement(LegendPlacement.OUTSIDE);
        donutModelGateways.setSliceMargin(2);
        donutModelGateways.setShowDataLabels(true);
        donutModelGateways.setDataFormat("value");
        donutModelGateways.addCircle(circle1);
        
        //donutModelGateways.setExtender("customExtender");
        
        
    }
    
    private void createPieModelFeasibilityADDC() {
        int redRag = (int) embenny.createNativeQuery("select count(*) from dbo.BOM_Meter a, dbo.us_request b where BOM_ID > 200 and a.BOM_ID = b.Active_BOM and b.Emirate_Name = 'Abu Dhabi' and METER_RAG = 'Red' and METER_RAG is not null").getResultList().get(0);
        int greenRag = (int) embenny.createNativeQuery("select count(*) from dbo.BOM_Meter a, dbo.us_request b where BOM_ID > 200 and a.BOM_ID = b.Active_BOM and b.Emirate_Name = 'Abu Dhabi' and METER_RAG = 'Green' and METER_RAG is not null").getResultList().get(0);
        int redYellow = (int) embenny.createNativeQuery("select count(*) from dbo.BOM_Meter a, dbo.us_request b where BOM_ID > 200 and a.BOM_ID = b.Active_BOM and b.Emirate_Name = 'Abu Dhabi' and METER_RAG = 'Yellow' and METER_RAG is not null").getResultList().get(0);
        int total = redRag + greenRag + redYellow - 66;
        total = total - 9;
        pieModel1FeasibilityADDC = new DonutChartModel();
        Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
        
        circle1.put(" Feasible for Surveys", greenRag);
        circle1.put(" Replacement Requested", redRag - 9);
        circle1.put(" Additional Information Requested", redYellow - 66);
        //circle1.put(" Under Evaluation", 742);
        
        pieModel1FeasibilityADDC.setTitle("Survey Fulfilment Feasibility (Meters - "+ total +")");
        pieModel1FeasibilityADDC.setLegendPosition("w");
        pieModel1FeasibilityADDC.setSliceMargin(3);
        pieModel1FeasibilityADDC.setShowDataLabels(true);
        pieModel1FeasibilityADDC.setDataFormat("value");
        pieModel1FeasibilityADDC.addCircle(circle1);
        //pieModel1FeasibilityADDC.setExtender("customExtender");
        pieModel1FeasibilityADDC.setSeriesColors("228B22, FF0000,FFFF00,000000");
        
       
    }

    public HashMap<String, Integer> perpareMap(String queryType) {
        HashMap<String, Integer> out = new HashMap<>();
        String strQuery = queryCompleted;
        if(queryType.equals("FAILED"))
        {
            strQuery = queryFailed;
        }else if (queryType.equals("MTR-FAILED")){
            strQuery = queryFailedMtrs;
        }else if (queryType.equals("MTR-COMPLETED")){
            strQuery = queryCompletedMtrs;
        }
        
        Vector<Object[]> out2 = (Vector<Object[]>) embenny.createNativeQuery(strQuery).getResultList();
        for (Object[] objects : out2) {
            if (out.containsKey(objects[0])) {
                Integer i = out.get(objects[0]);
                i = i + 1;
                out.put(objects[0].toString(), i);
            } else {
                out.put(objects[0].toString(), 1);
            }
        }
        return out;
    }
    

    public int getSensorsMissing() {
        sensorsMissing = (int) embenny.createNativeQuery("select count(*) from dbo.BOM_Meter where BOM_ID > 200 ").getResultList().get(0);
        return sensorsMissing;
    }

    public void setSensorsMissing(int sensorsMissing) {
        this.sensorsMissing = sensorsMissing;
    }

    public HashMap<String, Integer> perparelocationsMap() {
        String query2 = "select UTILITY_NUMBER,premise_latitude,premise_longitude,request_status from dbo.US_Request";
        HashMap<String, Integer> out = new HashMap<>();
        Vector<Object[]> out2 = (Vector<Object[]>) embenny.createNativeQuery(query2).getResultList();
        for (Object[] objects : out2) {
            if (out.containsKey(objects[0])) {
                Integer i = out.get(objects[0]);
                i = i + 1;
                out.put(objects[0].toString(), i);
            } else {
                out.put(objects[0].toString(), 1);
            }
        }
        return out;
    }
    
    public int getPartiallyacceptedpanels() {
        //partiallyacceptedpanels = (int) embenny.createNativeQuery("select count(*) from dbo.BOM_ATEs where Test_Passed is null and (Test_Connectivity=1 or Test_Fire=1 or Test_PL=1 or Test_Tamper=1)").getResultList().get(0);
        partiallyacceptedpanels = 10;
        return partiallyacceptedpanels;
    }

    public void setPartiallyacceptedpanels(int partiallyacceptedpanels) {

        this.partiallyacceptedpanels = partiallyacceptedpanels;
    }

    public LineChartModel getDateModel() {
        createDateModel();
        return dateModel;
    }

    public LineChartModel getDateModelMeters() {
        createDateModelMeters();
        return dateModelMeters;
    }
    
    

    public BarChartModel getMixedModel() {
        return mixedModel;
    }
    
    
    
    public void setDateModel(LineChartModel dateModel) {
        this.dateModel = dateModel;
    }

    public void setDateModelMeters(LineChartModel dateModelMeters) {
        this.dateModelMeters = dateModelMeters;
    }
    
    

    public void setMixedModel(BarChartModel mixedModel) {
        this.mixedModel = mixedModel;
    }
    
    
    
    public int getRequestsincomplete() {
//        requestsincomplete = (int) embenny.createNativeQuery("select count(*) from dbo.US_Request where Request_Status = 17").getResultList().get(0);
/*        requestsincomplete = (int) embenny.createNativeQuery("WITH INFO AS(\n"
                + "select a.UTILITY_NUMBER,a.active_bom, b.ATE_ID,b.Test_Passed as paneltest,c.Device_ID,c.Test_Passed as sensortest from dbo.US_Request a, dbo.BOM_ATEs b, dbo.BOM_Devices c \n"
                + "where a.Active_BOM = b.BOM_ID\n"
                + "and b.BOM_ID = c.BOM_ID\n"
                + "and c.BOM_ID = a.Active_BOM\n"
                + "and (c.Test_Passed =1 or b.Test_Passed=1)\n"
                + "and a.Request_Status >= 16)\n"
                + "SELECT count(distinct UTILITY_NUMBER) FROM INFO").getResultList().get(0);*/
                requestsincomplete = 100;
        return requestsincomplete - 10;
    }

    public List<Requests> getPACwithIssues() {
        return PACwithIssues;
    }

    public void populatePacWithIssues() {
        
    }

    public void setPACwithIssues(List<Requests> PACwithIssues) {
        this.PACwithIssues = PACwithIssues;
    }

    public void populateDeviceDB() {
        //setDevicesBD(moiRequestBus.getFastTrackAllDevicesBreakdown());
    }

    public void populateDeviceBDAccepted() {
        setDevicesBDAccepted(moiRequestBus.findAll());
    }

    public List<Requests> getDevicesBDAccepted() {
        return devicesBDAccepted;
    }

    public void setDevicesBDAccepted(List<Requests> devicesBDAccepted) {
        this.devicesBDAccepted = devicesBDAccepted;
    }
    
    public void populateSurveysPOC() {
        setSurveysPOC(surveyBus.findAllPOC());
    }
    
    public List<Surveys> getSurveysPOC() {
        return surveysPOC;
    }

    public void setSurveysPOC(List<Surveys> surveysPOC) {
        this.surveysPOC = surveysPOC;
    }
    
    
    
    public void populateInstalledYesterday() {
        /*
        Vector<Object[]> outobj = (Vector<Object[]>) embenny.createNativeQuery("with info as(\n"
                + "select a.UTILITY_NUMBER,a.Customer_Name,a.Account_Number,'ATE' as devicetype,b.Serial_Number from dbo.US_Request a, dbo.BOM_ATEs b\n"
                + "where a.Active_BOM = b.BOM_ID\n"
                + "and a.Request_Status = 20\n"
                + "union all\n"
                + "select a.UTILITY_NUMBER,a.Customer_Name,a.Account_Number,b.Device_Type as devicetype,b.Serial_Number from dbo.US_Request a, dbo.BOM_Devices b\n"
                + "where a.Active_BOM = b.BOM_ID\n"
                + "and b.Record_Status <> 'Deleted'\n"
                + "and a.Request_Status = 20\n"
                + ")\n"
                + "select *, CAST((select max(Update_Date) from dbo.TR_History where UTILITY_NUMBER = info.UTILITY_NUMBER) as datetime2(0)) as Completion_Date from info\n"
                + "where info.UTILITY_NUMBER in (select distinct(UTILITY_NUMBER) from dbo.TR_History where Status_Id = 20 and Update_Date > CAST(getdate()-1 AS DATE) and Update_Date < CAST(getdate() AS DATE))\n"
                + "order by info.UTILITY_NUMBER").getResultList();
        for (Object[] objects : outobj) {
            installedExport rec = new installedExport();
            rec.setMOIREF(objects[0].toString());
            rec.setCustomer_Name(objects[1].toString());
            try {
                rec.setAccountno(Integer.valueOf(BigDecimal.valueOf(Double.parseDouble(objects[2].toString())).toString().split("\\.")[0]));
            } catch (Exception e) {
                rec.setAccountno(null);
            }
            try {
                rec.setDevicetype(objects[3].toString());
            } catch (Exception e) {
                rec.setDevicetype(null);
            }
            try {
                rec.setSN(objects[4].toString());
            } catch (Exception e) {
                rec.setDevicetype(null);
            }

            rec.setCompletionDate((Timestamp) objects[5]);
            //installedyesterday_list.add(rec);
        
        }
        */
    }
public void populateInstallionLog() {
        /*
        Vector<Object[]> outobj = (Vector<Object[]>) embenny.createNativeQuery("with cte as \n"
                + "(\n"
                + "select *,ROW_NUMBER() over(Partition by moi_reference order by history_id desc) corr\n"
                + "from dbo.TR_History\n"
                + ")\n"
                + "select b.UTILITY_NUMBER,b.Remarks,b.Update_Date,b.User_ID, a.Account_Number,\n"
                + "case when Account_Number >= 800000 then 'Development' else 'Normal' end as Comment\n"
                + "from \n"
                + "cte b, dbo.US_Request a \n"
                + "where \n"
                + "b.corr = 1\n"
                + "and b.Status_Id = 20\n"
                + "and b.UTILITY_NUMBER = a.UTILITY_NUMBER\n"
                + "order by Update_Date desc").getResultList();
        for (Object[] objects : outobj) {
            //InstallationLogExport rec = new InstallationLogExport();
            //rec.setMOIRef(objects[0].toString());
            //rec.setRemarks(objects[1].toString());
            //rec.setUpdateDate((Timestamp) objects[2]);
            //rec.setUserId(objects[3].toString());
            //rec.setAccountNumber(objects[4].toString());
            //rec.setComment(objects[5].toString());
            //installedLog.add(rec);
        }
        */
    }

    //public List<installedExport> getInstalledyesterday() {
    //    return installedyesterday_list;
    //}

    public List<RequestDTO> getDevicesBD() {
        return devicesBD;
    }

    public void setDevicesBD(List<RequestDTO> devicesBD) {
        this.devicesBD = devicesBD;
    }

    public int getInstaltdy() {
        //instaltdy = (int) embenny.createNativeQuery("select count(*) from [dbo].[TR_History] where Status_Id = 20 and (Update_Date >= GETDATE()-1 and Update_Date < GETDATE()+1)").getResultList().get(0);
        instaltdy = 10;
        return instaltdy;
    }

    public void setInstaltdy(int instaltdy) {
        this.instaltdy = instaltdy;
    }

    public int getUserstotal() {
        userstotal = (int) embenny.createNativeQuery("select count(*) from [dbo].[MST_Users]").getResultList().get(0);
        return userstotal;
    }

    public void setUserstotal(int userstotal) {
        this.userstotal = userstotal;
    }

    public int getPanelsaccepted() {
        //panelsaccepted = (int) embenny.createNativeQuery("select count(*) from [dbo].[BOM_ATEs] where Test_Passed = 1").getResultList().get(0);
        panelsaccepted = 100;
        return panelsaccepted;
    }

    public int getAppsched() {
//        appsched = panelsaccepted = (int) embenny.createNativeQuery("select count(*) from [dbo].[TR_History] where Status_Id = 15 and (Update_Date >= GETDATE() -1 and Update_Date < GETDATE() +1)").getResultList().get(0);
        //appsched = panelsaccepted = (int) embenny.createNativeQuery("select count(*) from [dbo].[MOI_Request] where Request_Status = 15").getResultList().get(0);
        appsched = 100;
        return appsched;
    }

    public void setAppsched(int appsched) {
        this.appsched = appsched;
    }

    public int getInprogressinstallation() {
        inprogressinstallation = (int) embenny.createNativeQuery("WITH INFO AS(\n" + "select a.UTILITY_NUMBER,a.active_bom, b.ATE_ID,b.Test_Passed as paneltest,c.Device_ID,c.Test_Passed as sensortest from dbo.US_Request a, dbo.BOM_ATEs b, dbo.BOM_Devices c \n" + "where a.Active_BOM = b.BOM_ID\n" + "and b.BOM_ID = c.BOM_ID\n" + "and c.BOM_ID = a.Active_BOM\n" + "and (c.Test_Passed =1 or b.Test_Passed=1)\n" + "and a.Request_Status < 17\n" + ")\n" + "SELECT count(distinct UTILITY_NUMBER) FROM INFO").getResultList().get(0);
        return inprogressinstallation;
    }

    public List<RequestDTO> getDevicesBDAccepted_Filtered() {
        return devicesBDAccepted_Filtered;
    }

    public List<SurveyDTO> getSurveysPOC_Filtered() {
        return surveysPOC_Filtered;
    }
    
    
    

    public void setDevicesBDAccepted_Filtered(List<RequestDTO> devicesBDAccepted_Filtered) {
        this.devicesBDAccepted_Filtered = devicesBDAccepted_Filtered;
    }

    public void setSurveysPOC_Filtered(List<SurveyDTO> surveysPOC_Filtered) {
        this.surveysPOC_Filtered = surveysPOC_Filtered;
    }
    
    

    public void setInprogressinstallation(int inprogressinstallation) {
        this.inprogressinstallation = inprogressinstallation;
    }

    public void setPanelsaccepted(int panelsaccepted) {
        panelsaccepted = (int) embenny.createNativeQuery("select count(*) from dbo.BOM_Meter where BOM_ID > 200").getResultList().get(0);
        this.panelsaccepted = panelsaccepted;
    }

    public int getSensorsaccepted() {
        Sensorsaccepted = (int) embenny.createNativeQuery("select count(*) from dbo.BOM_Meter where BOM_ID > 200").getResultList().get(0);
        return Sensorsaccepted;
    }

    public void setSensorsaccepted(int Sensorsaccepted) {
        this.Sensorsaccepted = Sensorsaccepted;
    }

    public void setRequestsincomplete(int requestsincomplete) {
        this.requestsincomplete = requestsincomplete;
    }

    public int getRequestscomplete() {
        requestscomplete = (int) embenny.createNativeQuery("select count(*) from dbo.US_Request").getResultList().get(0);
        return requestscomplete;
    }

    public void populateReqComplete() {
        Requestcompletelist = embenny.createNativeQuery("select * from dbo.US_Request", Requests.class).getResultList();
    }

    public List<Requests> getRequestcompletelist() {

        return Requestcompletelist;
    }

    public void setRequestcompletelist(List<Requests> Requestcompletelist) {
        this.Requestcompletelist = Requestcompletelist;
    }

    public void setRequestscomplete(int requestscomplete) {
        this.requestscomplete = requestscomplete;
    }

    public int getMissingHW() {
//        MissingHW = (int) embenny.createNativeQuery("select count(*) from dbo.US_Request where Remarks like 'Missing HW' and Request_Status = 17").getResultList().get(0);
 /*       MissingHW = (int) embenny.createNativeQuery("WITH INFO AS(\n"
                + "select a.UTILITY_NUMBER,a.active_bom, b.ATE_ID,b.Test_Passed as paneltest,c.Device_ID,c.Device_Type,c.Test_Passed as sensortest from dbo.US_Request a, dbo.BOM_ATEs b, dbo.BOM_Devices c \n"
                + "where a.Active_BOM = b.BOM_ID\n"
                + "and b.BOM_ID = c.BOM_ID\n"
                + "and c.BOM_ID = a.Active_BOM\n"
                + "and (c.Test_Passed =1 or b.Test_Passed=1)\n"
                + "and a.Request_Status >= 16)\n"
                + "SELECT count(distinct info.UTILITY_NUMBER) FROM INFO \n"
                + "where (info.device_type like 'gas' or info.device_type like 'heat' or info.device_type like 'co')").getResultList().get(0);
        return MissingHW;*/
        return 10;
    }

    public List<Requests> getOnfflinelist() {
        return onfflinelist;
    }

    public void populateOfflineList() {
        /*
        WPAPI w = new WPAPI(wpServer);
        w.logIn(wpUser, wpPass);
        ArrayList<String> output = w.queryofflinelist();
        String concatout = "";
        for (String rec : output) {
            concatout = concatout + "','" + rec + "";
        }
        concatout = concatout + "'";
        concatout = concatout.substring(2);
        onfflinelist = embenny.createNativeQuery("select a.* from dbo.US_Request a, dbo.BOM_ATEs b \n"
                + "where a.Active_BOM = b.BOM_ID\n"
                + "and b.Serial_Number in (" + concatout + ")", Requests.class
        ).getResultList();
        w.logOut();
        */
    }

    public List<Requests> getUnreglist() {
        return unreglist;
    }

    public void populateUnregList() {
        /*
        WPAPI w = new WPAPI(wpServer);
        w.logIn(wpUser, wpPass);
        ArrayList<String> output = w.queryunreglist();
        String concatout = "";
        for (String rec : output) {
            concatout = concatout + "','" + rec + "";
        }
        concatout = concatout + "'";
        concatout = concatout.substring(2);
        unreglist = embenny.createNativeQuery("select a.* from dbo.US_Request a, dbo.BOM_ATEs b \n"
                + "where a.Active_BOM = b.BOM_ID\n"
                + "and b.Serial_Number in (" + concatout + ")", Requests.class
        ).getResultList();
        w.logOut();
        */
    }

    public void setUnreglist(List<Requests> unreglist) {
        this.unreglist = unreglist;
    }

    public void setOnfflinelist(List<Requests> onfflinelist) {
        this.onfflinelist = onfflinelist;
    }

    public List<Requests> getOnlinelist() {
        return onlinelist;
    }

    public void populateOnlineList() {
        /*
        WPAPI w = new WPAPI(wpServer);
        w.logIn(wpUser, wpPass);
        ArrayList<String> output = w.queryonlinelist();
        String concatout = "";
        for (String rec : output) {
            concatout = concatout + "','" + rec + "";
        }
        concatout = concatout + "'";
        concatout = concatout.substring(2);
        onlinelist = embenny.createNativeQuery("select a.* from dbo.US_Request a, dbo.BOM_ATEs b \n"
                + "where a.Active_BOM = b.BOM_ID\n"
                + "and b.Serial_Number in (" + concatout + ")", Requests.class
        ).getResultList();
        w.logOut();
        */
    }

    public void setOnlinelist(List<Requests> onlinelist) {
        this.onlinelist = onlinelist;
    }

    public List<Requests> getPacReqs() {
        return PacReqs;
    }

    public void populatePacReq() {
        /*
        Vector<Requests> out = (Vector<Requests>) embenny.createNativeQuery("WITH INFO AS(\n"
                + "select a.UTILITY_NUMBER,a.active_bom,a.Customer_Name, a.Account_Number, b.ATE_ID,b.Test_Passed as paneltest,c.Device_ID,c.Test_Passed as sensortest \n"
                + "from [dbo].[MOI_Request] a, dbo.BOM_ATEs b, dbo.BOM_Devices c \n"
                + "where a.Active_BOM = b.BOM_ID\n"
                + "and b.BOM_ID = c.BOM_ID\n"
                + "and c.BOM_ID = a.Active_BOM\n"
                + "and (c.Test_Passed =1 or b.Test_Passed=1)\n"
                + "and a.Request_Status >=16)\n"
                + "SELECT e.* FROM dbo.US_Request e\n"
                + "where e.UTILITY_NUMBER in (select distinct (info.UTILITY_NUMBER) from info)", Requests.class).getResultList();
        this.PacReqs.addAll(out);
        */
    }

    public void setPacReqs(List<Requests> PacReqs) {
        this.PacReqs = PacReqs;
    }

    public int getExtraHW() {
//        ExtraHW = (int) embenny.createNativeQuery("select count(*) from dbo.US_Request where Remarks like 'Extra HW' and Request_Status = 17").getResultList().get(0);
        ExtraHW = requestsincomplete - MissingHW;
        return ExtraHW;
    }

    public void setExtraHW(int ExtraHW) {
        this.ExtraHW = ExtraHW;
    }

    public int getAppdy() {
        //appdy = (int) embenny.createNativeQuery("select count(*) from [dbo].[TR_History] where Status_Id = 13 and (Update_Date >= GETDATE() -1 and Update_Date < GETDATE() +1)").getResultList().get(0);
        appdy = 10;
        return appdy / 2;
    }

    public int getSurveytdy() {
        //surveytdy = (int) embenny.createNativeQuery("select count(*) from [dbo].[TR_History] where Status_Id = 21 and (Update_Date >= GETDATE() -1 and Update_Date < GETDATE() +1)").getResultList().get(0);
        surveytdy = 20;
        return surveytdy / 2;
    }

    public void setSurveytdy(int surveytdy) {
        this.surveytdy = surveytdy;
    }

    public String getTotalpanelcost() {
        

        totalpanelcost = "AED" + 0;
        return totalpanelcost;
    }

    public void setTotalpanelcost(String totalpanelcost) {
        this.totalpanelcost = totalpanelcost;
    }

    public String getTotalsensorscost() {
        

        totalsensorscost = "AED" + 100;
        return totalsensorscost;
    }

    public void setTotalsensorscost(String totalsensorscost) {
        this.totalsensorscost = totalsensorscost;
    }

    public String getTotalrepeatercost() {
        

        totalrepeatercost = "AED" + 100;
        return totalrepeatercost;
    }

    public void setTotalrepeatercost(String totalrepeatercost) {
        this.totalrepeatercost = totalrepeatercost;
    }

    public String getTotalcost() {
        

        totalcost = "AED" + 100;
        return totalcost;
    }

    public void setTotalcost(String totalcost) {
        this.totalcost = totalcost;
    }

    

    public int getBomApproved() {
        bomApproved = (int) embenny.createNativeQuery("select count(*) from dbo.US_Request").getResultList().get(0);
        return bomApproved;
    }

    public void setBomApproved(int bomApproved) {
        this.bomApproved = bomApproved;
    }

    
    public int getAin() {
        ain = (int) embenny.createNativeQuery("WITH INFO AS(\n"
                + "select a.UTILITY_NUMBER,a.Emirate_Name,a.active_bom, b.BOM_METER_ID from dbo.US_Request a, dbo.BOM_Meter b \n"
                + "where a.Active_BOM = b.BOM_ID)\n"
                + "SELECT count(distinct UTILITY_NUMBER) FROM INFO\n"
                + "where  INFO.Emirate_Name like 'Al%'").getResultList().get(0);
        return ain;
    }

    public void setAin(int ain) {
        this.ain = ain;
    }

    public int getAbudhabi() {
        abudhabi = (int) embenny.createNativeQuery("WITH INFO AS(\n"
                + "select a.UTILITY_NUMBER,a.Emirate_Name,a.active_bom, b.BOM_METER_ID from dbo.US_Request a,dbo.BOM_Meter b \n"
                + "where a.Active_BOM = b.BOM_ID)\n"
                + "SELECT count(distinct UTILITY_NUMBER) FROM INFO\n"
                + "where  INFO.Emirate_Name like 'Abu%'").getResultList().get(0);
        return abudhabi;
    }

    public void setAbudhabi(int abudhabi) {
        this.abudhabi = abudhabi;
    }

    

    public void setAppdy(int appdy) {
        this.appdy = appdy;
    }

    public void setMissingHW(int MissingHW) {
        this.MissingHW = MissingHW;
    }

    public int getVillasconnected() {
//        villasconnected = (int) embenny.createNativeQuery("WITH INFO AS(\n" + "select a.UTILITY_NUMBER,a.active_bom, b.ATE_ID,b.Test_Passed as paneltest,c.Device_ID,c.Test_Passed as sensortest from dbo.US_Request a, dbo.BOM_ATEs b, dbo.BOM_Devices c \n" + "where a.Active_BOM = b.BOM_ID\n" + "and b.BOM_ID = c.BOM_ID\n" + "and c.BOM_ID = a.Active_BOM\n" + "and (c.Test_Passed =1 or b.Test_Passed=1)\n" + "\n" + ")\n" + "SELECT count(distinct UTILITY_NUMBER) FROM INFO").getResultList().get(0);
        /*villasconnected = (int) embenny.createNativeQuery("WITH INFO AS(\n"
                + "select a.UTILITY_NUMBER,a.active_bom, b.ATE_ID,b.Test_Passed as paneltest,c.Device_ID,c.Test_Passed as sensortest from dbo.US_Request a, dbo.BOM_ATEs b, dbo.BOM_Devices c \n"
                + "where a.Active_BOM = b.BOM_ID\n"
                + "and b.BOM_ID = c.BOM_ID\n"
                + "and c.BOM_ID = a.Active_BOM\n"
                + "and (c.Test_Passed =1 or b.Test_Passed=1)\n"
                + "and a.Request_Status >=16)\n"
                + "SELECT count(distinct UTILITY_NUMBER) FROM INFO").getResultList().get(0);
        */
        return 10;
    }

    public void setVillasconnected(int villasconnected) {
        this.villasconnected = villasconnected;
    }

    public int getReadyforinstallation() {
        File directory = new File("D:\\MIFs");
        readyforinstallation = directory.list().length;
        return readyforinstallation;
    }

    public int getReadyforinstallationready() {
        int cnt = 0;
        Vector<String> out2 = (Vector<String>) embenny.createNativeQuery("select UTILITY_NUMBER from dbo.US_Request").getResultList();
        ArrayList<String> bomapp = new ArrayList<>();
        for (Object objects : out2) {
            bomapp.add(objects.toString());
        }

        File folder = new File("D:\\MIFs");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                if (bomapp.contains(listOfFiles[i].getName().toUpperCase())) {
                    cnt = cnt + 1;
                }
            }
        }
        readyforinstallationready = cnt;
        return readyforinstallationready;
    }

    public void setReadyforinstallationready(int readyforinstallationready) {
        this.readyforinstallationready = readyforinstallationready;
    }

    public void setReadyforinstallation(int readyforinstallation) {
        this.readyforinstallation = readyforinstallation;
    }

    public List<RequestDTO> getDevicesBD_Filtered() {
        return devicesBD_Filtered;
    }

    public void setDevicesBD_Filtered(List<RequestDTO> devicesBD_Filtered) {
        this.devicesBD_Filtered = devicesBD_Filtered;
    }

    
    
    public void updateSummary(){
       
    }
    
    
    

    public int getnCompletedSites() {
       nCompletedSites = (int) embenny.createNativeQuery(qCompletedSites).getResultList().get(0);
        return nCompletedSites;
    }

    public int getnCompletedMeters() {
        nCompletedMeters = (int) embenny.createNativeQuery(qCompletedMeters).getResultList().get(0);
        return nCompletedMeters;
    }

    public int getnFailedSites() {
        return nFailedSites;
    }

    public int getnFailedMeters() {
        return nFailedMeters;
    }

    public int getnPendingSites() {
        return nPendingSites;
    }

    public int getnPendingMeters() {
        return nPendingMeters;
    }

    public int getnTotalSites() {
        nTotalSites = (int) embenny.createNativeQuery(qTotalSites).getResultList().get(0);
        //nTotalSites = nTotalSites;
        return nTotalSites;
    }

    public int getnTotalMeters() {
         nTotalMeters = (int) embenny.createNativeQuery(qTotalMeters).getResultList().get(0);
         //nTotalMeters = nTotalMeters + 170;
        return nTotalMeters;
    }

    public int getnTotalSitesAUH() {
        nTotalSitesAUH = (int) embenny.createNativeQuery(qTotalSitesAUH).getResultList().get(0);
        return nTotalSitesAUH;
    }

    public int getnTotalSitesAAN() {
        nTotalSitesAAN = (int) embenny.createNativeQuery(qTotalSitesAAN).getResultList().get(0);
        return nTotalSitesAAN;
    }

    public int getnTotalMetersWaterAUH() {
        nTotalMetersWaterAUH = (int) embenny.createNativeQuery(qTotalMetersWaterAUH).getResultList().get(0);
        return nTotalMetersWaterAUH;
    }

    public int getnTotalMetersElecAUH() {
        nTotalMetersElecAUH = (int) embenny.createNativeQuery(qTotalMetersElecAUH).getResultList().get(0);
        return nTotalMetersElecAUH;
    }

    public int getnTotalMetersWaterAAN() {
        nTotalMetersWaterAAN = (int) embenny.createNativeQuery(qTotalMetersWaterAAN).getResultList().get(0);
        return nTotalMetersWaterAAN;
    }

    public int getnTotalMetersElecAAN() {
        nTotalMetersElecAAN = (int) embenny.createNativeQuery(qTotalMetersElecAAN).getResultList().get(0);
        return nTotalMetersElecAAN;
    }

    public int getnCompletedSitesAUH() {
        nCompletedSitesAUH = (int) embenny.createNativeQuery(qCompletedSitesAUH).getResultList().get(0);
        return nCompletedSitesAUH;
    }

    public int getnCompletedSitesAAN() {
        nCompletedSitesAAN = (int) embenny.createNativeQuery(qCompletedSitesAAN).getResultList().get(0);
        return nCompletedSitesAAN;
    }

    public int getnFailedSitesAUH() {
        nFailedSitesAUH = (int) embenny.createNativeQuery(qFailedSitesAUH).getResultList().get(0);
        return nFailedSitesAUH;
    }

    public int getnFailedSitesAAN() {
        nFailedSitesAAN = (int) embenny.createNativeQuery(qFailedSitesAAN).getResultList().get(0);
        return nFailedSitesAAN;
    }

    public int getnCompletedMetersWaterAUH() {
        nCompletedMetersWaterAUH = (int) embenny.createNativeQuery(qCompletedMetersWaterAUH).getResultList().get(0);
        return nCompletedMetersWaterAUH;
    }

    public int getnCompletedMetersElecAUH() {
        nCompletedMetersElecAUH = (int) embenny.createNativeQuery(qCompletedMetersElecAUH).getResultList().get(0);
        return nCompletedMetersElecAUH;
    }

    public int getnCompletedMetersWaterAAN() {
        nCompletedMetersWaterAAN = (int) embenny.createNativeQuery(qCompletedMetersWaterAAN).getResultList().get(0);
        return nCompletedMetersWaterAAN;
    }

    public int getnCompletedMetersElecAAN() {
        nCompletedMetersElecAAN = (int) embenny.createNativeQuery(qCompletedMetersElecAAN).getResultList().get(0);
        return nCompletedMetersElecAAN;
    }

    public int getnFailedMetersAUH() {
        nFailedMetersAUH = (int) embenny.createNativeQuery(qFailedMetersAUH).getResultList().get(0);
        return nFailedMetersAUH;
    }

    public int getnFailedMetersAAN() {
        nFailedMetersAAN = (int) embenny.createNativeQuery(qFailedMetersAAN).getResultList().get(0);
        return nFailedMetersAAN;
    }
    
    
    
    

    
    
    
    
}
