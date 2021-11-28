/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;

import ae.etisalatdigital.iot.ops.utility.sync.entities.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import org.modelmapper.ModelMapper;

/**
 *
 * @author appadmin
 */
public class BOMGatewayEstDTO implements Serializable,Comparable<BOMGatewayEstDTO>{
    
    private static final long serialVersionUID = 7654321L;
    private BigInteger id;
    private Long bomId; 
    private String gatewaysType;
    private MSTGatewayTypeDTO gatewayModel;
    private String gatewaysTypeProposed;

    private int gatewaysRequired;
    private int metersPerGateway;
    private String EstimatedCableLength;
    
    private String gatewaysVendor;
    private String gatewaysLocation;
    private String gatewaysDaisychain;
    private String gatewaysChainLabel;
    
    private String serialNumber;
    private BigInteger simICCID;
    
    private Boolean powerIntruption;
    private Long signalStrength;
    private String signalStrengthIndicator;
    
    private Boolean antenaRequired;
    
    private Long gatewayRoomId;
    private Long gatewayFloorId;
    
    private Double cableLength;
    private String gatewayFloor;
    private String gatewayRoom;
    
    private SimDetailsDTO simDetailsDTO;
    
    private Set<BOMMeterDTO> bomMeterList;
    
    public BOMGatewayEstDTO() {
    }

    public BOMGatewayEstDTO(BigInteger id, Long bomId, String gatewaysType, MSTGatewayTypes gatewaysTypeProposed, int gatewaysRequired, int metersPerGateway, String EstimatedCableLength, String gatewaysVendor, String gatewaysDaisychain, String gatewaysChainLabel, String serialNumber, BigInteger simICCID, Boolean powerIntruption, Long signalStrength, String signalStrengthIndicator, Boolean antenaRequired, Long gatewayRoomId, Long gatewayFloorId, Double cableLength) {
        this.id = id;
        this.bomId = bomId;
        this.gatewaysType = gatewaysType;
        if(null!=gatewaysTypeProposed){
            this.gatewaysTypeProposed = gatewaysTypeProposed.getGatewayTypeCode();
            this.gatewayModel=new MSTGatewayTypeDTO(gatewaysTypeProposed.getId(),gatewaysTypeProposed.getGatewayTypeCode(),
            gatewaysTypeProposed.getGatewayTypeName());
            this.gatewayModel.setDeviceHESModelId(gatewaysTypeProposed.getDeviceModelId());
            this.gatewayModel.setGatewayTypeDescription(gatewaysTypeProposed.getGatewayTypeDescription());
        }
        this.gatewaysRequired = gatewaysRequired;
        this.metersPerGateway = metersPerGateway;
        this.EstimatedCableLength = EstimatedCableLength;
        this.gatewaysVendor = gatewaysVendor;
        this.gatewaysDaisychain = gatewaysDaisychain;
        this.gatewaysChainLabel = gatewaysChainLabel;
        this.serialNumber = serialNumber;
        this.simICCID = simICCID;
        this.powerIntruption = powerIntruption;
        this.signalStrength = signalStrength;
        this.signalStrengthIndicator = signalStrengthIndicator;
        this.antenaRequired = antenaRequired;
        this.gatewayFloorId = gatewayFloorId;
        this.gatewayRoomId = gatewayRoomId;
        this.cableLength = cableLength; 
    }

    /**
     * this constructor will be called when the named query BOMGatewaysEst.findSomeByBomId is called
     * @param gateway
     * @param simDetails
     * @param gatewayFloor
     * @param gatewayRoom
     */
    public BOMGatewayEstDTO(BOMGatewaysEst gateway, SimDetails simDetails, String gatewayFloor, String gatewayRoom) {
        this.id = gateway.getId();
        this.bomId = gateway.getBom().getId();
        this.gatewaysType = gateway.getGatewaysType();
        if(null!=gateway.getGatewayModel()){
            this.gatewayModel=new MSTGatewayTypeDTO(gateway.getGatewayModel().getId(),gateway.getGatewayModel().getGatewayTypeCode(),
                    gateway.getGatewayModel().getGatewayTypeName());
            this.gatewaysTypeProposed = gateway.getGatewayModel().getGatewayTypeCode();
            this.gatewayModel.setDeviceHESModelId(gateway.getGatewayModel().getDeviceModelId());
            this.gatewayModel.setGatewayTypeDescription(gateway.getGatewayModel().getGatewayTypeDescription());
        }
        this.gatewaysRequired = gateway.getGatewaysRequired();
        this.metersPerGateway = gateway.getMetersPerGateway();
        this.EstimatedCableLength = gateway.getEstimatedCableLength();
        this.gatewaysVendor = gateway.getGatewaysVendor();
        this.signalStrength = gateway.getSignalStrength();
        this.signalStrengthIndicator = gateway.getSignalStrengthIndicator();
        this.gatewaysDaisychain = gateway.getGatewaysDaisychain();
        this.gatewaysChainLabel = gateway.getGatewaysChainLabel();
        this.serialNumber=gateway.getSerialNumber();
        if(simDetails !=null){
            simDetailsDTO = new SimDetailsDTO(simDetails.getId(),simDetails.getSimICCID(),simDetails.getCommunicationEquipmentType(),
                    simDetails.getIp(),simDetails.getPort());
            simDetailsDTO.setDescription(simDetails.getDescription());
            this.simICCID=simDetails.getSimICCID();
        }
        this.gatewayFloor=gatewayFloor;
        this.gatewayRoom=gatewayRoom;
    }
    private BOMGatewayEstDTO(Builder builder){
        this.id=builder.id;
        this.bomId=builder.bomId;
        this.gatewaysType=builder.gatewaysType;
        this.serialNumber=builder.serialNumber;
        this.gatewayModel=builder.gatewayModel;
        if(null!=builder.floorDTO)
        {
            this.gatewayFloorId=builder.floorDTO.getId();
            this.gatewayFloor=builder.floorDTO.getFloorCode();
        }
        if(null!=builder.roomDTO){
            this.gatewayRoom=builder.roomDTO.getRoomCode();
            this.gatewayRoomId=builder.roomDTO.getId();
        }
        if(null!=builder.meterList){
            this.bomMeterList = builder.meterList;
        }
        if(null!=builder.signalStrength){
            this.signalStrength=builder.signalStrength;
        }
        if(null!=builder.signalStrengthIndicator){
            this.signalStrengthIndicator=builder.signalStrengthIndicator;
        }
    }
    public static class Builder{
        private BigInteger id;
        private Long bomId; 
        private String gatewaysType;
        private MSTGatewayTypeDTO gatewayModel;
        private String serialNumber;
        private MSTFloorDTO floorDTO;
        private MSTRoomDTO roomDTO;
        private Set<BOMMeterDTO> meterList;
        private Long signalStrength;
        private String signalStrengthIndicator;
        public Builder(BigInteger id,Long bomId){
            this.id=id;
            this.bomId=bomId;
        }
        public Builder type(String gatewaysType){
            this.gatewaysType=gatewaysType;
            return this;
        }
        public Builder model(MSTGatewayTypeDTO gatewayTypeDTO){
            this.gatewayModel = gatewayTypeDTO;
            return this;
        }
        public Builder model(MSTGatewayTypes gatewayType){
            this.gatewayModel = new ModelMapper().map(gatewayType, MSTGatewayTypeDTO.class);
            return this;
        }
        public Builder serialNumber(String serialNumber){
            this.serialNumber=serialNumber;
            return this;
        }
        public Builder floor(MSTFloorDTO floorDTO){
            this.floorDTO = floorDTO;
            return this;
        }
        public Builder floor(MSTFloor floor){
            this.floorDTO = new MSTFloorDTO(floor.getId(),floor.getFloorCode(),floor.getFloorDescription());
            return this;
        }
        public Builder room(MSTRoomDTO roomDTO){
            this.roomDTO = roomDTO;
            return this;
        }
        public Builder room(MSTRoom room){
            this.roomDTO = new ModelMapper().map(room, MSTRoomDTO.class);
            return this;
        }
        public Builder meters(List<BOMMeters> bomMeters){
            if(bomMeters!=null && bomMeters.size()>0){
                this.meterList = new TreeSet<>();
            }
            for(BOMMeters meter: bomMeters){
                this.meterList.add(new ModelMapper().map(meter, BOMMeterDTO.class));
            }
            return this;
        }
        public Builder signalStrength(Long strength,String strengthIndicator){
            this.signalStrength=strength;
            this.signalStrengthIndicator=strengthIndicator;
            return this;
        }
        public BOMGatewayEstDTO build(){
            BOMGatewayEstDTO bomGatewayEstDTO = new BOMGatewayEstDTO(this);
            return bomGatewayEstDTO;
        }
    }
    public BigInteger getId() {
        return id;
    }

    public Long getBomId() {
        return bomId;
    }

    public String getGatewaysType() {
        return gatewaysType;
    }

    public String getGatewaysTypeProposed() {
        return gatewaysTypeProposed;
    }

    public int getGatewaysRequired() {
        return gatewaysRequired;
    }

    public int getMetersPerGateway() {
        return metersPerGateway;
    }

    public String getEstimatedCableLength() {
        return EstimatedCableLength;
    }


    public String getGatewaysVendor() {
        return gatewaysVendor;
    }

    public String getGatewaysLocation() {
        return gatewaysLocation;
    }

    public String getGatewaysDaisychain() {
        return gatewaysDaisychain;
    }

    public String getGatewaysChainLabel() {
        return gatewaysChainLabel;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public BigInteger getSimICCID() {
        if(this.simICCID==null && this.simDetailsDTO!=null){
            this.simICCID = this.simDetailsDTO.getSimICCID();
        }
        return simICCID;
    }

    public Boolean getPowerIntruption() {
        return powerIntruption;
    }

    public Long getSignalStrength() {
        return signalStrength;
    }

    public String getSignalStrengthIndicator() {
        return signalStrengthIndicator;
    }

    public Boolean getAntenaRequired() {
        return antenaRequired;
    }

    public Long getGatewayRoomId() {
        return gatewayRoomId;
    }

    public Long getGatewayFloorId() {
        return gatewayFloorId;
    }

    public Double getCableLength() {
        return cableLength;
    }
    
    
    
    

    /**
     * 
     * @param id 
     */
    public void setId(BigInteger id) {
        this.id = id;
    }

    public void setBomId(Long bomId) {
        this.bomId = bomId;
    }

    public void setGatewaysType(String gatewaysType) {
        this.gatewaysType = gatewaysType;
    }

    public void setGatewaysTypeProposed(String gatewaysTypeProposed) {
        this.gatewaysTypeProposed = gatewaysTypeProposed;
    }

    public void setGatewaysRequired(int gatewaysRequired) {
        this.gatewaysRequired = gatewaysRequired;
    }

    public void setMetersPerGateway(int metersPerGateway) {
        this.metersPerGateway = metersPerGateway;
    }

    public void setEstimatedCableLength(String EstimatedCableLength) {
        this.EstimatedCableLength = EstimatedCableLength;
    }


    public void setGatewaysVendor(String gatewaysVendor) {
        this.gatewaysVendor = gatewaysVendor;
    }

    public void setGatewaysLocation(String gatewaysLocation) {
        this.gatewaysLocation = gatewaysLocation;
    }

    public void setGatewaysDaisychain(String gatewaysDaisychain) {
        this.gatewaysDaisychain = gatewaysDaisychain;
    }

    public void setGatewaysChainLabel(String gatewaysChainLabel) {
        this.gatewaysChainLabel = gatewaysChainLabel;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setSimICCID(BigInteger simICCID) {
        this.simICCID = simICCID;
    }

    public void setPowerIntruption(Boolean powerIntruption) {
        this.powerIntruption = powerIntruption;
    }

    public void setSignalStrength(Long signalStrength) {
        this.signalStrength = signalStrength;
    }

    public void setSignalStrengthIndicator(String signalStrengthIndicator) {
        this.signalStrengthIndicator = signalStrengthIndicator;
    }

    public void setAntenaRequired(Boolean antenaRequired) {
        this.antenaRequired = antenaRequired;
    }

    public void setGatewayRoomId(Long gatewayRoomId) {
        this.gatewayRoomId = gatewayRoomId;
    }

    public void setGatewayFloorId(Long gatewayFloorId) {
        this.gatewayFloorId = gatewayFloorId;
    }

    public void setCableLength(Double cableLength) {
        this.cableLength = cableLength;
    }
    public String getGatewayFloor() {
        return gatewayFloor;
    }

    public void setGatewayFloor(String gatewayFloor) {
        this.gatewayFloor = gatewayFloor;
    }

    public String getGatewayRoom() {
        return gatewayRoom;
    }

    public void setGatewayRoom(String gatewayRoom) {
        this.gatewayRoom = gatewayRoom;
    }

    public MSTGatewayTypeDTO getGatewayModel() {
        return gatewayModel;
    }

    public void setGatewayModel(MSTGatewayTypeDTO gatewayModel) {
        this.gatewayModel = gatewayModel;
    }

    public void handleKeyEvent() {
        if(signalStrength <= -111){
            signalStrengthIndicator = "LOW";
        }else if(signalStrength <= -101){
            signalStrengthIndicator = "MEDIUM";
        }else{
            signalStrengthIndicator = "HIGH";
        }
        
    }

    public Set<BOMMeterDTO> getBomMeterList() {
        return bomMeterList;
    }

    public void setBomMeterList(Set<BOMMeterDTO> bomMeterList) {
        this.bomMeterList = bomMeterList;
    }
    
    public SimDetailsDTO getSimDetailsDTO() {
        if(null==simDetailsDTO)
        {
            simDetailsDTO = new SimDetailsDTO();
        }
        return simDetailsDTO;
    }

    public void setSimDetailsDTO(SimDetailsDTO simDetailsDTO) {
        this.simDetailsDTO = simDetailsDTO;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BOMGatewayEstDTO other = (BOMGatewayEstDTO) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int compareTo(BOMGatewayEstDTO t) {
        return this.id.compareTo(t.getId());
    }

}
