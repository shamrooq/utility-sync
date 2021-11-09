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
import javax.persistence.TypedQuery;
import org.modelmapper.ModelMapper;

/**
 *
 * @author au_mobility
 */
@Stateless
public class SimDetailsDAOImpl implements SimDetailsDAO {

    @PersistenceContext(unitName = "com.mycompany_UTIL_war_1.0-SNAPSHOTPU")
    private EntityManager entityManager;

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

    private SimDetails getEntity(BigInteger id) {
        SimDetails entity;
        try {
            entity = entityManager.find(SimDetails.class, id);
        } catch (NoResultException ex) {
            return null;
        }
        return entity;
    }
    
    @Override
    public void addNewSimDetails(SimDetailsDTO simDetailsDTO){
        SimDetails entity= new SimDetails();
        new ModelMapper().map(simDetailsDTO, entity);
        entityManager.persist(entity);
    }
    @Override
    public void updateSimDetails(SimDetailsDTO dto) throws DataAccessException{
        SimDetails entity = getEntity(dto.getId());
        if (entity == null) {
            throw new DataAccessException("Entity with id "+dto.getId() + "not found");
        }
        new ModelMapper().map(dto, entity);
        entityManager.merge(entity);
    }
}
