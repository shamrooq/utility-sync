package ae.etisalatdigital.iot.ops.utility.sync.entities;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author shamr
 */
@Entity
@Table(name = "sim_details")
public class SimDetails implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger id;
    @Column(name = "sim_iccid",unique = true)
    private BigInteger simICCID;
    
    @OneToOne(mappedBy = "simDetails")
    private BOMGatewaysEst gateway;

    @Column(name = "channel_type")
    private String channelType;
    @Column(name = "communication_equipment_type")
    private String communicationEquipmentType;
    private String ip;
    private String port;

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

    public String getChannelType() {
        return channelType;
    }

    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }

    public String getCommunicationEquipmentType() {
        return communicationEquipmentType;
    }

    public void setCommunicationEquipmentType(String communicationEquipmentType) {
        this.communicationEquipmentType = communicationEquipmentType;
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

    public BOMGatewaysEst getGateway() {
        return gateway;
    }

    public void setGateway(BOMGatewaysEst gateway) {
        this.gateway = gateway;
    }
}