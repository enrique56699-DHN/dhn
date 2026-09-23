/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HIK;

/**
 *
 * @author win 10
 */
// Clase para condiciones de búsqueda
public class AcsEventCond {
    private String searchID;
    private int searchResultPosition;
    private int maxResults;
    private int major;
    private int minor;
    private String startTime;
    private String endTime;
    private boolean timeReverseOrder;
    
    // Constructor
    public AcsEventCond(String searchID, int maxResults, String startTime, String endTime) {
        this.searchID = searchID;
        this.searchResultPosition = maxResults;
        this.maxResults = 30;
        this.major = 0;
        this.minor = 0;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeReverseOrder = false;
    }
    
    // Getters y Setters
    public String getSearchID() { return searchID; }
    public void setSearchID(String searchID) { this.searchID = searchID; }
    
    public int getSearchResultPosition() { return searchResultPosition; }
    public void setSearchResultPosition(int searchResultPosition) { this.searchResultPosition = searchResultPosition; }
    
    public int getMaxResults() { return maxResults; }
    public void setMaxResults(int maxResults) { this.maxResults = maxResults; }
    
    public int getMajor() { return major; }
    public void setMajor(int major) { this.major = major; }
    
    public int getMinor() { return minor; }
    public void setMinor(int minor) { this.minor = minor; }
    
    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    
    public String getEndTime() { return endTime; }
    public void setEndTime(String endTime) { this.endTime = endTime; }
    
    public boolean isTimeReverseOrder() { return timeReverseOrder; }
    public void setTimeReverseOrder(boolean timeReverseOrder) { this.timeReverseOrder = timeReverseOrder; }
}