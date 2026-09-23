/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HIK;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author win 10
 */
// Clase AcsEvent
@JsonIgnoreProperties(ignoreUnknown = true)
public class AcsEvent {
    private String searchID;
    private int totalMatches;
    private String responseStatusStrg;
    private int numOfMatches;
    
    @JsonProperty("InfoList")
    private List<EventInfo> infoList;
    
    // Getters y Setters
    public String getSearchID() { return searchID; }
    public void setSearchID(String searchID) { this.searchID = searchID; }
    
    public int getTotalMatches() { return totalMatches; }
    public void setTotalMatches(int totalMatches) { this.totalMatches = totalMatches; }
    
    public String getResponseStatusStrg() { return responseStatusStrg; }
    public void setResponseStatusStrg(String responseStatusStrg) { this.responseStatusStrg = responseStatusStrg; }
    
    public int getNumOfMatches() { return numOfMatches; }
    public void setNumOfMatches(int numOfMatches) { this.numOfMatches = numOfMatches; }
    
    public List<EventInfo> getInfoList() { return infoList; }
    public void setInfoList(List<EventInfo> infoList) { this.infoList = infoList; }
}
