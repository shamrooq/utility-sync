/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.daos;

import ae.etisalatdigital.commonUtils.exception.DataAccessException;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.SimDetailsDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.SimDetails;
import java.math.BigInteger;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;
import org.apache.log4j.Logger;
import org.modelmapper.ModelMapper;

/**
 *
 * @author au_mobility
 */
@Stateless
public class SimDetailsDAOImpl implements SimDetailsDAO {

    private static final Logger logger = Logger.getLogger(SimDetailsDAOImpl.class);
    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;
    private SimDetails entity;
    
    @Override
    public List<SimDetails> findAll() {
        return entityManager.createQuery("SELECT sd FROM SimDetails sd", SimDetails.class).getResultList();
    }

    @Override
    public List<SimDetailsDTO> findBySimICCID(BigInteger simICCID) {
        TypedQuery<SimDetailsDTO> query;
        query = entityManager.createQuery("SELECT sd FROM SimDetails sd where sd.simICCID=:simIccid", SimDetailsDTO.class);
        query.setParameter("simIccid", simICCID);
        return query.getResultList();
    }

    private SimDetails findByIdorSimIccid(BigInteger id) {
        try {
            this.entity = entityManager.find(SimDetails.class, id);
        } catch (NoResultException ex) {
            return null;
        }
        return entity;
    }
    
    @Override
    public SimDetailsDTO findByIdorSimIccid(SimDetailsDTO dto) {
        try {
            if(null!=dto.getId()){
                this.entity = entityManager.find(SimDetails.class, dto.getId());
            }
            else if(null!=dto.getSimICCID()){
               TypedQuery<SimDetails> query = entityManager.createQuery("SELECT s FROM SimDetails s where s.simICCID=:simICCID",SimDetails.class);
               query.setParameter("simICCID", dto.getSimICCID());
               this.entity = query.getSingleResult();
            }
        } catch (NoResultException ex) {
            return null;
        }
        return new ModelMapper().map(this.entity, SimDetailsDTO.class);
    }

    @Override
    public void addNewSimDetails(SimDetailsDTO simDetailsDTO){
        try{
            if(simEntryExists(simDetailsDTO)){
                update(simDetailsDTO);
            }
            else{
                insert(simDetailsDTO);
            }
            logger.info("Sim with ICCID "+simDetailsDTO.getSimICCID() + "successfully added");
        }
        catch(PersistenceException e){
            logger.error("Sim with ICCID "+simDetailsDTO.getSimICCID() + "could not be added",e);
        }
    }
    private boolean simEntryExists(SimDetailsDTO simDetailsDTO){
        try {
            findByIdorSimIccid(simDetailsDTO);
        } catch (Exception e) {
            logger.error("Entity with id "+simDetailsDTO.getId() + "not found",e);
            return false;
        }
        return this.entity != null;
    }
    @Override
    public void updateSimDetails(SimDetailsDTO dto) throws DataAccessException{
        simEntryExists(dto);
        if (this.entity == null) {
            throw new DataAccessException("Entity with id "+dto.getId() + "not found");
        }
        update(dto);
    }
    private void insert(SimDetailsDTO simDetailsDTO) throws PersistenceException{
        entity = new ModelMapper().map(simDetailsDTO, SimDetails.class);
        entityManager.persist(entity);
        logger.info("Sim with Id "+entity.getId() + "successfully added");
    }
    private void update(SimDetailsDTO simDetailsDTO) throws PersistenceException{
        this.entity = new ModelMapper().map(simDetailsDTO, SimDetails.class);
        this.entity = entityManager.merge(entity);
        logger.info("Sim with Id "+entity.getId() + "successfully updated");
   }
}
 