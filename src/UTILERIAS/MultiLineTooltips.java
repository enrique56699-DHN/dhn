/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UTILERIAS;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class MultiLineTooltips 
{
    /*private static final int DIALOG_TOOLTIP_MAX_SIZE = 75;
    private static final int SPACE_BUFFER = 10;

    public static String splitToolTip(String tip)
    {
        return splitToolTip(tip,DIALOG_TOOLTIP_MAX_SIZE);
    }
    public static String splitToolTip(String tip,int length)
    {
        if(tip.length()<=length + SPACE_BUFFER )
        {
            return tip;
        }

        List<String>  parts = new ArrayList<>();

        int maxLength = 0;
        String overLong = tip.substring(0, length + SPACE_BUFFER);
        int lastSpace = overLong.lastIndexOf(' ');
        if(lastSpace >= length)
        {
            parts.add(tip.substring(0,lastSpace));
            maxLength = lastSpace;
        }
        else
        {
            parts.add(tip.substring(0,length));
            maxLength = length;
        }

        while(maxLength < tip.length())
        {
            if(maxLength + length < tip.length())
            {
                parts.add(tip.substring(maxLength, maxLength + length));
                maxLength+=maxLength+length;
            }
            else
            {
                parts.add(tip.substring(maxLength));
                break;
            }
        }

        StringBuilder  sb = new StringBuilder("<html>");
        for(int i=0;i<parts.size() - 1;i++)
        {
            sb.append(parts.get(i)+"<br>");
        }
        sb.append(parts.get(parts.size() - 1));
        sb.append(("</html>"));
        return sb.toString();
    }*/
    
    /** Private constructor for utility class. */
    private MultiLineTooltips() {
    }

    /** Default max length of the tool tip when split with {@link #splitToolTip(String)}. */
    private static final int DIALOG_TOOLTIP_MAX_SIZE = 75;

    /** A function that splits a string into sections of {@value #DIALOG_TOOLTIP_MAX_SIZE} characters or less.
     * If you want the lines to be shorter or longer call {@link #splitToolTip(String, int)}.
     * @param toolTip The tool tip string to be split
     * @return the tool tip string with HTML formatting to break it into sections of the correct length
     */
    public static String splitToolTip(final String toolTip) {
        return splitToolTip(toolTip, DIALOG_TOOLTIP_MAX_SIZE);
    }

    /**  An overloaded function that splits a tool tip string into sections of a specified length.
     * @param toolTip The tool tip string to be split
     * @param desiredLength The maximum length of the tool tip per line
     * @return The tool tip string with HTML formatting to break it into sections of the correct length
     */
    public static String splitToolTip(final String toolTip, final int desiredLength) {
        if (toolTip.length() <= desiredLength) {
            return toolTip;
        }

        List<String>  parts = new ArrayList<>();
        int stringPosition = 0;

        while (stringPosition < toolTip.length()) {
            if (stringPosition + desiredLength < toolTip.length()) {
                String tipSubstring = toolTip.substring(stringPosition, stringPosition + desiredLength);
                int lastSpace = tipSubstring.lastIndexOf(' ');
                if (lastSpace == -1 || lastSpace == 0) {
                    parts.add(toolTip.substring(stringPosition, stringPosition + desiredLength));
                    stringPosition += desiredLength;
                } else {
                    parts.add(toolTip.substring(stringPosition, stringPosition + lastSpace));
                    stringPosition += lastSpace;
                }
            } else {
                parts.add(toolTip.substring(stringPosition));
                break;
            }
        }

        StringBuilder  sb = new StringBuilder("<html>");
        for (int i = 0; i < parts.size() - 1; i++) {
            sb.append(parts.get(i) + "<br>");
        }
        sb.append(parts.get(parts.size() - 1));
        sb.append(("</html>"));
        return sb.toString();
    }
    
}
