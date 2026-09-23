/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HIK;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author win 10
 */
// Clase para la solicitud de búsqueda
public class SearchRequest {
    @JsonProperty("AcsEventCond")
    private AcsEventCond acsEventCond;
    
    public SearchRequest(AcsEventCond acsEventCond) {
        this.acsEventCond = acsEventCond;
    }
    
    public AcsEventCond getAcsEventCond() { return acsEventCond; }
    public void setAcsEventCond(AcsEventCond acsEventCond) { this.acsEventCond = acsEventCond; }
}