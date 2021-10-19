/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.dtos;

/**
 *
 * @author appadmin
 */
public class MSTRoomDTO {
    
    private Long id;
    private String roomCode;
    private String roomDescription;

    public MSTRoomDTO() {
    }

    public MSTRoomDTO(Long id, String roomCode, String roomDescription) {
        this.id = id;
        this.roomCode = roomCode;
        this.roomDescription = roomDescription;
    }

    public Long getId() {
        return id;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public String getRoomDescription() {
        return roomDescription;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public void setRoomDescription(String roomDescription) {
        this.roomDescription = roomDescription;
    }
    
    
}
