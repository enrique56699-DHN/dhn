/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

import com.netsdk.common.Res;
import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.ToolKits;
import com.sun.jna.Pointer;
import java.awt.image.BufferedImage;
import java.util.Vector;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class Checador 
{
    private static final long serialVersionUID = 1L;  
    private Vector<String> chnList = new Vector<>();  
    private boolean isRealplay = false;  
    private static boolean isAttach = false;
    private static DisConnect disConnect = new DisConnect();  
    private static HaveReConnect haveReConnect = new HaveReConnect();  
    public static NetSDKLib.LLong m_hPlayHandle = new NetSDKLib.LLong(0L);  
    public static NetSDKLib.LLong m_hAttachHandle = new NetSDKLib.LLong(0L);   
    private static BufferedImage globalBufferedImage = null;  
    private static BufferedImage personBufferedImage = null;  
    private static BufferedImage candidateBufferedImage = null;  
    private static int groupId = 0;  
    private static int index = -1;

    private static class DisConnect implements NetSDKLib.fDisConnect 
    {
        private DisConnect(){}    
        @Override
        public void invoke(NetSDKLib.LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser) 
        {
            System.out.printf("Device[%s] Port[%d] DisConnect!\n", new Object[] { pchDVRIP, nDVRPort});
            String conexion = Res.string().getTargetRecognition() + " : " + Res.string().getDisConnectReconnecting();
        }       
    }
    
    private static class HaveReConnect implements NetSDKLib.fHaveReConnect 
    {
        private HaveReConnect() {}
        @Override
        public void invoke(NetSDKLib.LLong m_hLoginHandle, String pchDVRIP, int nDVRPort, Pointer dwUser) 
        {
            System.out.printf("ReConnect Device[%s] Port[%d]\n", new Object[] { pchDVRIP, nDVRPort});
            String conexion = Res.string().getTargetRecognition() + " : " + Res.string().getOnline();
            
        }
    }
   
    public boolean login(String IPAC, int PUERTO, String USER, String PASSWORD) 
    {
        if (LoginModule.login(IPAC,PUERTO, USER, PASSWORD)) 
        {

            for (int i = 1; i < LoginModule.m_stDeviceInfo.byChanNum + 1; i++)
            this.chnList.add(Res.string().getChannel() + " " + String.valueOf(i)); 
            //this.chnComboBox.setModel(new DefaultComboBoxModel<>(this.chnList));
            
        } 
        else 
        {
            JOptionPane.showMessageDialog(null, 
          
            Res.string().getLoginFailed() + ", " + ToolKits.getErrorCodeShow(), 
            Res.string().getErrorMessage(), 0);
            return false;
        } 
        return true;
    }
    
}
