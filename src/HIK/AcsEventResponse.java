/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HIK;

/**
 *
 * @author win 10
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

// Clase principal de respuesta
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcsEventResponse {
    @JsonProperty("AcsEvent")
    private AcsEvent acsEvent;
    
    public AcsEvent getAcsEvent() { return acsEvent; }
    public void setAcsEvent(AcsEvent acsEvent) { this.acsEvent = acsEvent; }
}