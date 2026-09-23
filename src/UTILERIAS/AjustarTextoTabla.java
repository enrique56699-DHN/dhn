/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UTILERIAS;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Enrique Orozco Alcala
 */
public class AjustarTextoTabla extends JTextArea implements TableCellRenderer
{

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        this.setText(value.toString());
        this.setWrapStyleWord(true);
        this.setLineWrap(true);
        this.setFont(table.getFont());
        this.setBorder(table.getBorder());
        this.setBackground(table.getBackground());

        return this;
        
    }
    
}
