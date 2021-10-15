/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.beans;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author appadmin
 */
@Named
@RequestScoped
public class PickListView {

    //@Inject
    //private CountryService service;

    private DualListModel<String> utilities;
    private DualListModel<String> protocols;

    @PostConstruct
    public void init() {
        //Utilities
        List<String> utilitiesSource = new ArrayList<>();
        List<String> utilitiesTarget = new ArrayList<>();
        
        
        utilitiesSource.add("Electricity");
        utilitiesSource.add("Water");
        utilitiesSource.add("Gas");
        utilitiesSource.add("Communication");
        
        utilities = new DualListModel<>(utilitiesSource, utilitiesTarget);

        //Protocols
        List<String> protocolsSource = new ArrayList<>();
        List<String> protocolsTarget = new ArrayList<>();;
        protocolsSource.add("DLMS");
        protocolsSource.add("Euridis");
        protocolsSource.add("MBus");
        protocolsSource.add("Modbus");
        protocolsSource.add("Pulse");
        protocolsSource.add("Severn Trent");
        protocolsSource.add("Wireless Mbus");
        protocolsSource.add("HART");
        protocols = new DualListModel<>(protocolsSource, protocolsTarget);
        
    }

    public DualListModel<String> getUtilities() {
        return utilities;
    }

    public void setUtilities(DualListModel<String> utilities) {
        this.utilities = utilities;
    }

    public DualListModel<String> getProtocols() {
        return protocols;
    }

    public void setProtocols(DualListModel<String> protocols) {
        this.protocols = protocols;
    }
    
    
    
    /**
    public CountryService getService() {
        return service;
    }

    public void setService(CountryService service) {
        this.service = service;
    }

    public DualListModel<Country> getCountries() {
        return countries;
    }

    public void setCountries(DualListModel<Country> countries) {
        this.countries = countries;
    }
    * */
    public void onTransfer(TransferEvent event) {
        StringBuilder builder = new StringBuilder();
        for(Object item : event.getItems()) {
            builder.append(((Country) item).getName()).append("<br />");
        }

        FacesMessage msg = new FacesMessage();
        msg.setSeverity(FacesMessage.SEVERITY_INFO);
        msg.setSummary("Items Transferred");
        msg.setDetail(builder.toString());

        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
    public void onSelect(SelectEvent<Country> event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Selected", event.getObject().getName()));
    }

    public void onUnselect(UnselectEvent<Country> event) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Item Unselected", event.getObject().getName()));
    }
    * */
    
    public void onReorder() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "List Reordered", null));
    }
}
