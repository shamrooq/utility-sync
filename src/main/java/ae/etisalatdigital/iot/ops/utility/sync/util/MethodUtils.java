/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ae.etisalatdigital.iot.ops.utility.sync.util;

import ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.models.EquipmentResponseModel;
import org.apache.http.HttpStatus;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.Optional;

/**
 *
 * @author shamr
 */
public class MethodUtils {
    /**
     * 
     * @param clientId
     * @param equipmentResponseModel
     * @param message
     * @return 
     */
    public static int addMessage(String clientId,EquipmentResponseModel equipmentResponseModel,String message) {
        FacesMessage facesMessage;
        if (null != equipmentResponseModel) {
            if (Long.valueOf(200).equals(equipmentResponseModel.getCode())) {
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "", message==null?
                        equipmentResponseModel.getDescription():message);
                FacesContext.getCurrentInstance().addMessage(clientId, facesMessage);
            } else if (null != equipmentResponseModel.getErrorNumber()) {
                facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, String.valueOf(equipmentResponseModel.getErrorNumber()),
                        Optional.ofNullable(equipmentResponseModel.getErrorCode()).orElse("").concat(
                        equipmentResponseModel.getStackTrace()==null?"":equipmentResponseModel.getStackTrace()));
                FacesContext.getCurrentInstance().addMessage(clientId, facesMessage);
                return HttpStatus.SC_INTERNAL_SERVER_ERROR;
            }
        } else {
            facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, ""+HttpStatus.SC_INTERNAL_SERVER_ERROR,
                    message==null?"Internal Server Error":message);
            FacesContext.getCurrentInstance().addMessage(clientId, facesMessage);
            return HttpStatus.SC_INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.SC_OK;
    }
    /**
     * 
     * @param severity
     * @param clientId
     * @param summary
     * @param detail 
     */
    public static void addMessage(FacesMessage.Severity severity, String clientId, String summary, String detail) {
        if(null==severity){
            severity = FacesMessage.SEVERITY_INFO;
        }
        FacesMessage facesMessage = new FacesMessage(severity, summary==null?
                        "":summary,detail==null?
                        "":detail);
        FacesContext.getCurrentInstance().addMessage(clientId, facesMessage);
    }
}
