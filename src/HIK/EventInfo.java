/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HIK;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author win 10
 */
// Clase para información del evento
@JsonIgnoreProperties(ignoreUnknown = true)
public class EventInfo {
    private int major;
    private int minor;
    private String time;
    private Integer serialNo;
    private String currentVerifyMode;
    private String mask;
    private Integer cardType;
    private String name;
    private Integer cardReaderNo;
    private Integer doorNo;
    
    @JsonProperty("employeeNoString")
    private String employeeNo;
    
    private String userType;
    private FaceRect faceRect;
    
    // Getters y Setters
    public int getMajor() { return major; }
    public void setMajor(int major) { this.major = major; }
    
    public int getMinor() { return minor; }
    public void setMinor(int minor) { this.minor = minor; }
    
    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
    
    public Integer getSerialNo() { return serialNo; }
    public void setSerialNo(Integer serialNo) { this.serialNo = serialNo; }
    
    public String getCurrentVerifyMode() { return currentVerifyMode; }
    public void setCurrentVerifyMode(String currentVerifyMode) { this.currentVerifyMode = currentVerifyMode; }
    
    public String getMask() { return mask; }
    public void setMask(String mask) { this.mask = mask; }
    
    public Integer getCardType() { return cardType; }
    public void setCardType(Integer cardType) { this.cardType = cardType; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Integer getCardReaderNo() { return cardReaderNo; }
    public void setCardReaderNo(Integer cardReaderNo) { this.cardReaderNo = cardReaderNo; }
    
    public Integer getDoorNo() { return doorNo; }
    public void setDoorNo(Integer doorNo) { this.doorNo = doorNo; }
    
    public String getEmployeeNo() { return employeeNo; }
    public void setEmployeeNo(String employeeNo) { this.employeeNo = employeeNo; }
    
    public String getUserType() { return userType; }
    public void setUserType(String userType) { this.userType = userType; }
    
    public FaceRect getFaceRect() { return faceRect; }
    public void setFaceRect(FaceRect faceRect) { this.faceRect = faceRect; }
}

