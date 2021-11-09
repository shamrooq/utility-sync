/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;

import java.io.Serializable;
import java.math.BigInteger;


/**
 *
 * @author appadmin
 */
public class SimDetailsDTO implements Serializable{
    private BigInteger id;
    private BigInteger simICCID;
    private String communicationEquipmentType;
    private String description;
    private String channelGroup;
    private String ip;
    private String port;

    public SimDetailsDTO() {
    }

    public SimDetailsDTO(BigInteger id, BigInteger simICCID, String communicationEquipmentType, String ip, String port) {
        this.id = id;
        this.simICCID = simICCID;
        this.communicationEquipmentType = communicationEquipmentType;
        this.ip = ip;
        this.port = port;
    }

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public BigInteger getSimICCID() {
        return simICCID;
    }

    public void setSimICCID(BigInteger simICCID) {
        this.simICCID = simICCID;
    }

    public String getCommunicationEquipmentType() {
        if(this.communicationEquipmentType==null){
            this.communicationEquipmentType="TCP/IP";
        }
        return communicationEquipmentType;
    }

    public void setCommunicationEquipmentType(String communicationEquipmentType) {
        this.communicationEquipmentType = communicationEquipmentType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getChannelGroup() {
        return channelGroup;
    }

    public void setChannelGroup(String channelGroup) {
        this.channelGroup = channelGroup;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
