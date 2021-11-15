package ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author shamr
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TelemetryConfiguration {

    private String communicationaddress;
    private String communicationsEquipment;
    @JsonProperty("configuration")
    List<Property> configuration;

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

    public List<Property> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(List<Property> configuration) {
        this.configuration = configuration;
    }
}
