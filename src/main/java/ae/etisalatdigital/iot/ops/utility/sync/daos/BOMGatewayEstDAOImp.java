/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.buses.SimDetailsBus;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.SimDetailsDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.BOMGatewaysEst;
import ae.etisalatdigital.iot.ops.utility.sync.entities.SimDetails;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.modelmapper.ModelMapper;

/**
 *
 * @author au_mobility
 */
@Stateless
public class BOMGatewayEstDAOImp implements BOMGatewayEstDAO {
    
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    @Inject
    private SimDetailsBus simDetailsBus;
    @Override
    public List<BOMGatewayEstDTO> findAll() {
        return entityManager.createNamedQuery("BOMGatewaysEst.findAll", BOMGatewayEstDTO.class).getResultList();
    }
    
    @Override
    public List<BOMGatewayEstDTO> findAllByBomId(Long bomId){
        TypedQuery<BOMGatewayEstDTO> query;
        query = entityManager.createNamedQuery("BOMGatewaysEst.findAllByBOMID", BOMGatewayEstDTO.class);
        query.setParameter("bomId", bomId);
              return  query.getResultList();
    }
    
    @Override
    public List<BOMGatewayEstDTO> findAllByBomIdAndBomMeterType(Long bomId, String bomMeterType){
        TypedQuery<BOMGatewayEstDTO> query;
        query = entityManager.createNamedQuery("BOMGatewayEstDTO.findAllByBomIdAndBomMetertype", BOMGatewayEstDTO.class);
        query.setParameter("bomId", bomId);
        query.setParameter("bomMeterType", bomMeterType);
              return  query.getResultList();
    }
    
    @Override
    public void updateGatewayDetail(BOMGatewayEstDTO dto)throws DataAccessException {
        BOMGatewaysEst entity = getEntity(dto.getId());
        if(entity == null) {
            throw new DataAccessException("Incident not found");
        }
        entity.setSerialNumber(dto.getSerialNumber());
        //entity.
        if(null!=dto.getSimDetailsDTO().getSimICCID())
        {
            SimDetailsDTO simDetailsExists = simDetailsBus.findByIdOrSimIccid(dto.getSimDetailsDTO());
            SimDetails simDetailsNew = new ModelMapper().map(dto.getSimDetailsDTO(), SimDetails.class);
            if(simDetailsExists!=null){
                simDetailsNew.setId(simDetailsExists.getId());
                entity.setSimDetails(simDetailsNew);
            }
        }
        //new ModelMapper().map(dto, entity);
        entityManager.merge(entity);
    }
    
    private BOMGatewaysEst getEntity(BigInteger id) {
        BOMGatewaysEst entity;
        
        try {
            entity = entityManager.find(BOMGatewaysEst.class, id);
        } catch(NoResultException ex) {
            return null;
        }
        
        return entity;
    }
    
    
    
    /**
     * 
     * @param mmsReference
     * @param status 
     */
    
    @Override
    public void updateRequestStatus(String mmsReference, Integer status){
        TypedQuery<BOMGatewaysEst> query = entityManager.createNamedQuery("BOMGatewaysEst.updateRequestStatus", BOMGatewaysEst.class);
        query.setParameter("requestStatus", status);
        query.setParameter("mmsReference", mmsReference);

        int update = query.executeUpdate();

        if (update == 0) {
            throw new DataAccessException("Update Status failed");
        }
    }
    
    @Override
    public Boolean addNewGatewayEstByBomId(Long bomId, String gatewayBomType, String proposedGateType, int noOfGateways, int metersPerGateway,String csbleEtimation,String gatewayLocation){
        
        try{
            Query query = entityManager.createNativeQuery("exec [dbo].[SP_InsertNewGateways_Est] '"+gatewayBomType+"','"+proposedGateType+"',"+bomId+","+noOfGateways+","+metersPerGateway+",'"+csbleEtimation+"','"+gatewayLocation+"'");
            System.out.println("BOMGatewayEstDAOImp.addNewGatewayEstByBomId()-"+query);
            query.executeUpdate();
            return true;
        }catch(Exception exp){
            System.out.println("add New Gateway Failed:"+exp.getMessage());
        }

        return false;
    }

    @Override
    public Boolean addNewGatewayEstByBomId(Long bomId, String gatewayBomType, String proposedGateType, int noOfGateways, int metersPerGateway,Double cableLength,Long gatewayRoomId, Long gatewayFloorId, Boolean powerIntruption, Long signalStrength, Boolean antenaRequired){

        try{
            Query query = entityManager.createNativeQuery("exec [dbo].[SP_InsertNewGateways_Est] '"+gatewayBomType+"','"+proposedGateType+"',"+bomId+","+noOfGateways+","+metersPerGateway+","+cableLength+","+gatewayRoomId+","+gatewayFloorId+","+powerIntruption+","+signalStrength+","+antenaRequired);
            System.out.println("BOMGatewayEstDAOImp.addNewGatewayEstByBomId()-"+query);
            query.executeUpdate();
            return true;
        }catch(Exception exp){
            System.out.println("add New Gateway Failed:"+exp.getMessage());
        }

        return false;
    }
    
    @Override
    public Boolean delete(Long id) {
        try{
            Query query = entityManager.createNamedQuery("BOMGatewaysEst.DELETE");
            query.setParameter("id", id);
            query.executeUpdate();
            return true;
        }catch(Exception exp){
            System.out.println("add New Meter Failed:"+exp.getMessage());
        }
        
        return false;
    }
    @Override
    public List<BOMGatewayEstDTO> findSomeByBomId(Long bomId){
        TypedQuery<BOMGatewayEstDTO> query;
        query = entityManager.createNamedQuery("BOMGatewaysEst.findSomeByBomId", BOMGatewayEstDTO.class);
        query.setParameter("bomId", bomId);
        return  query.getResultList();
    }
}
