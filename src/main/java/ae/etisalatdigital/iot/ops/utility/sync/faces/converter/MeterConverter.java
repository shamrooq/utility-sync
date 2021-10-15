/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.faces.converter;

import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMMeterDTO;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.model.DualListModel;
import org.primefaces.component.picklist.PickList;

/**
 *
 * @author shamr
 */
@FacesConverter(value = "meterConverter")
@ManagedBean
@RequestScoped
public class MeterConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent comp, String value) {
        DualListModel<BOMMeterDTO> model = (DualListModel<BOMMeterDTO>) ((PickList) comp).getValue();
        for (BOMMeterDTO meter : model.getSource()) {
            if (meter.getId().equals(Long.parseLong(value))) {
                return meter;
            }
        }
        for (BOMMeterDTO meter : model.getTarget()) {
            if (meter.getId().equals(Long.parseLong(value))) {
                return meter;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent comp, Object value) {
        return ((BOMMeterDTO) value).getId().toString();
    }
}
