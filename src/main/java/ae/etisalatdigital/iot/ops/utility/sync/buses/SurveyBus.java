/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.buses;

import ae.etisalatdigital.iot.ops.utility.sync.daos.SurveyDAO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMMeterDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.RequestDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.SurveyDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.Surveys;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/**
 *
 * @author au_mobility
 */
@Stateless
public class SurveyBus {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    
    @Inject
    private SurveyDAO surveyDao;
    
    public List<Surveys> findAll(){
        return surveyDao.findAll();
    }
    
    public List<Surveys> findAllPOC(){
        return surveyDao.findAllPOC();
    }
    
    public List<SurveyDTO> findAllByORD(String ordNumber){
        return surveyDao.findAllByORD(ordNumber);
    }
    
    public SurveyDTO findAllByUN(String utilityNumber){
        return surveyDao.findAllByORD(utilityNumber).get(0);
    }
    
    public void updateSurveyDetails(SurveyDTO dto){
        surveyDao.updateSRDetail(dto);
    }
    
    public void updateRequestStatus(String utilityNumber, Integer status)
    {
        surveyDao.updateRequestStatus(utilityNumber, status);
    }
   
    public SurveyDTO updateGPSCoordinates(String latitudeAsStr, String longitudeAsStr){
        String errorMessage = "Success";
        try{
            
        }catch(Exception exp){
            
        }
        return null;
    }
    
    public boolean submitSurvey(RequestDTO reqDetails, BOMMeterDTO bomDetails){
        return true;
    }
    
  
}
