/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author shamr
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommunicationEquipmentModel {
    private String code;

    private String description;

    private String channelGroup;

    private String communicationsEquipmentType;
    
    @JsonProperty("properties")
    private Property property;
    
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return this.code;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void setChannelGroup(String channelGroup){
        this.channelGroup = channelGroup;
    }
    public String getChannelGroup(){
        return this.channelGroup;
    }
    public void setCommunicationsEquipmentType(String communicationsEquipmentType){
        this.communicationsEquipmentType = communicationsEquipmentType;
    }
    public String getCommunicationsEquipmentType(){
        return this.communicationsEquipmentType;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }
}
