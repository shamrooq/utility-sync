/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.views;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import org.primefaces.PrimeFaces;



/**
 *
 * @author appadmin
 */
@Named("dfView")
@RequestScoped
public class DFView {
    
    public void showMessage() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Message", " Always Bet on Prime!");
        PrimeFaces.current().dialog().showMessageDynamic(message);
    }
}
