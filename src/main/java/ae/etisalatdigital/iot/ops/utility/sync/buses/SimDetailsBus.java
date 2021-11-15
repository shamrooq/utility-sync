/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.buses;

import ae.etisalatdigital.iot.ops.utility.sync.daos.SimDetailsDAO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.SimDetailsDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.SimDetails;
import java.math.BigInteger;
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
public class SimDetailsBus {
    
    @Inject
    private SimDetailsDAO simDetailsDAO;
    
    public List<SimDetails> findAll(){
        return simDetailsDAO.findAll();
    }
    
    public List<SimDetailsDTO> findBySimICCID(BigInteger simICCID){
        return simDetailsDAO.findBySimICCID(simICCID);
    }
    public void addNewSimDetails(SimDetailsDTO simDetailsDTO){
        simDetailsDAO.addNewSimDetails(simDetailsDTO);
    }
    public SimDetailsDTO findByIdOrSimIccid(SimDetailsDTO simDetailsDTO){
        return simDetailsDAO.findByIdorSimIccid(simDetailsDTO);
    }
}
