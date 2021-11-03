/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.models;

import java.util.List;

/**
 *
 * @author shamr
 */
public class TelemetryConfiguration {
 private String communicationaddress;
 private String communicationsEquipment;
 List<Object> configuration;


 // Getter Methods 

 public String getCommunicationaddress() {
  return communicationaddress;
 }

 public String getCommunicationsEquipment() {
  return communicationsEquipment;
 }

 // Setter Methods 

 public void setCommunicationaddress(String communicationaddress) {
  this.communicationaddress = communicationaddress;
 }

 public void setCommunicationsEquipment(String communicationsEquipment) {
  this.communicationsEquipment = communicationsEquipment;
 }
}
