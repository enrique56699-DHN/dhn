/*
 * Copyright (c) 1995, 2021, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */ 

package CONTROLADOR;

import javax.swing.*;
import java.beans.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

/* ImagePreview.java by FileChooserDemo2.java. */
public class ImagePreview extends JComponent
                          implements PropertyChangeListener {
    ImageIcon thumbnail = null;
    File file = null;
    private int w = 350;
    private int h = 150;

    public ImagePreview(JFileChooser fc) {
        setPreferredSize(new Dimension(w, h));
        fc.addPropertyChangeListener(this);
        //fc.setSize(1280, 720);
    }

    public void loadImage() throws IOException {
        if (file == null) {
            thumbnail = null;
            return;
        }

        //Don't use createImageIcon (which is a wrapper for getResource)
        //because the image we're trying to load is probably not one
        //of this program's own resources.
        //thumbnail = F.previsualizar(null, "", file.getPath());
        
        
        ////////////////////////////////////////////////////////////////////////
        ImageIcon tmpIcon = null;
        
        String []datos = file.getPath().split("\\.");        
        String extension = datos[1];
        switch(extension)
        {
            case "pdf":
                System.setProperty("fontbox.fontcache", "null");
                File pdfFile = new File(file.getPath());
                try {
                    // 1. Cargar el documento PDF
                    // Para PDFBox 3.x use Loader.loadPDF()
                    // Para PDFBox 2.x use PDDocument.load(pdfFile)
                    try (PDDocument document = Loader.loadPDF(pdfFile)) 
                    {

                        // 2. Instanciar PDFRenderer
                        PDFRenderer pdfRenderer = new PDFRenderer(document);

                        // 3. Renderizar la primera página (índice 0) a una imagen
                        // DPI bajo (96-150) para previsualización rápida
                        BufferedImage bim = pdfRenderer.renderImageWithDPI(0, 96, ImageType.RGB);

                        // 4. Mostrar la imagen en el JLabel
                        //label.setIcon(new ImageIcon(bim));
                        tmpIcon = new ImageIcon(bim);
                    }
                } catch (IOException e) {
                    if (e.getMessage().contains("PostScriptTable")) {
                        // Loguear el error pero continuar
                        System.err.println("Error en fuente, pero continuando: " + e.getMessage());
                    } else {
                        throw e; // Lanzar si es otro error
                    }
                    //System.err.println("Error en fuente, pero continuando: " + e.getMessage());
                    //e.printStackTrace();
                    //label.setText("Error al cargar PDF: " + e.getMessage());
                }
                break;
            default:
                tmpIcon = new ImageIcon(file.getPath());
                break;
        }
        
        //ImageIcon tmpIcon = new ImageIcon(file.getPath());
        if (tmpIcon != null) {
            if (tmpIcon.getIconWidth() > w) {
                thumbnail = new ImageIcon(tmpIcon.getImage().
                                          getScaledInstance(w, -1,
                                                      Image.SCALE_SMOOTH));
            } else if (tmpIcon.getIconHeight() > w) {
                thumbnail = new ImageIcon(tmpIcon.getImage().
                                          getScaledInstance(-1, h,
                                                      Image.SCALE_SMOOTH));
            } else { //no need to miniaturize
                thumbnail = tmpIcon;
            }
        }
        /////////////////////////////////////////////////////////////////////////
        
        /*if(tmpIcon != null)
        {
            try {
                // 1. Cargar la imagen original
                //iconOriginal = new ImageIcon(ruta);

                // 2. Obtener las dimensiones del JLabel para escalar
                //int anchoLabel = 200;
                //int altoLabel = 200;

                // 3. Escalar la imagen usando SCALE_SMOOTH para mejorar la calidad
                Image imgEscalada = tmpIcon.getImage().getScaledInstance(200, 390, Image.SCALE_SMOOTH);

                // 4. Crear un nuevo ImageIcon con la imagen escalada
                ImageIcon iconEscalado = new ImageIcon(imgEscalada);

                // 5. Asignar al JLabel
                //lblIMAGEN.setIcon(iconEscalado);
                thumbnail = iconEscalado;

            } catch (Exception e) {
                System.out.println("Error al cargar la imagen: " + e.getMessage());
            }     
        }*/
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        boolean update = false;
        String prop = e.getPropertyName();

        //If the directory changed, don't show an image.
        if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals(prop)) {
            file = null;
            update = true;

        //If a file became selected, find out which one.
        } else if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals(prop)) {
            file = (File) e.getNewValue();
            update = true;
        }

        //Update the preview accordingly.
        if (update) {
            thumbnail = null;
            if (isShowing()) {
                try {
                    loadImage();
                    repaint();
                } catch (IOException ex) {
                    Logger.getLogger(ImagePreview.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (thumbnail == null) {
            try {
                loadImage();
            } catch (IOException ex) {
                Logger.getLogger(ImagePreview.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (thumbnail != null) {
            int x = getWidth()/2 - thumbnail.getIconWidth()/2;
            int y = getHeight()/2 - thumbnail.getIconHeight()/2;

            if (y < 0) {
                y = 0;
            }

            if (x < 5) {
                x = 5;
            }
            thumbnail.paintIcon(this, g, x, y);
        }
    }
}
