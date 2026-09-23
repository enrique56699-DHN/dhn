/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HIK;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author win 10
 */
// Clase para coordenadas de rostro (opcional)
@JsonIgnoreProperties(ignoreUnknown = true)
public class FaceRect {
    private int height;
    private int width;
    private int x;
    private int y;
    
    // Getters y Setters
    public int getHeight() { return height; }
    public void setHeight(int height) { this.height = height; }
    
    public int getWidth() { return width; }
    public void setWidth(int width) { this.width = width; }
    
    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
}



