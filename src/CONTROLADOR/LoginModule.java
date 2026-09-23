/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

/**
 *
 * @author Admin
 */

import com.netsdk.lib.NetSDKLib;
import com.netsdk.lib.ToolKits;
import com.sun.jna.Callback;
import java.io.File;

public class LoginModule 
{
    public static NetSDKLib netsdk = NetSDKLib.NETSDK_INSTANCE;
  
    public static NetSDKLib configsdk = NetSDKLib.CONFIG_INSTANCE;
  
    public static NetSDKLib.NET_DEVICEINFO_Ex m_stDeviceInfo = new NetSDKLib.NET_DEVICEINFO_Ex();

    public static NetSDKLib.LLong m_hLoginHandle = new NetSDKLib.LLong(0L);

    private static boolean bInit = false;

    private static boolean bLogopen = false;
  
    public static boolean init(NetSDKLib.fDisConnect disConnect, NetSDKLib.fHaveReConnect haveReConnect) 
    {
        bInit = netsdk.CLIENT_Init((Callback)disConnect, null);
        if (!bInit) 
        {
            System.out.println("Initialize SDK failed");
            return false;
        } 
        
        NetSDKLib.LOG_SET_PRINT_INFO setLog = new NetSDKLib.LOG_SET_PRINT_INFO();
        File path = new File("./sdklog/");
        if (!path.exists())
            path.mkdir(); 
        String logPath = path.getAbsoluteFile().getParent() + "\\sdklog\\" + ToolKits.getDate() + ".log";
        setLog.nPrintStrategy = 0;
        setLog.bSetFilePath = 1;
        System.arraycopy(logPath.getBytes(), 0, setLog.szLogFilePath, 0, (logPath.getBytes()).length);
        System.out.println(logPath);
        setLog.bSetPrintStrategy = 1;
        bLogopen = netsdk.CLIENT_LogOpen(setLog);
        if (!bLogopen)
            System.err.println("Failed to open NetSDK log"); 
        netsdk.CLIENT_SetAutoReconnect((Callback)haveReConnect, null);
        int waitTime = 5000;
        int tryTimes = 1;
        netsdk.CLIENT_SetConnectTime(waitTime, tryTimes);
        NetSDKLib.NET_PARAM netParam = new NetSDKLib.NET_PARAM();
        netParam.nConnectTime = 10000;
        netParam.nGetConnInfoTime = 3000;
        netParam.nGetDevInfoTime = 3000;
        netsdk.CLIENT_SetNetworkParam(netParam);
        return true;
    }
  
    public static void cleanup() 
    {
        if (bLogopen)
            netsdk.CLIENT_LogClose(); 
        if (bInit)
            netsdk.CLIENT_Cleanup(); 
    }
  
    public static boolean login(String m_strIp, int m_nPort, String m_strUser, String m_strPassword) 
    {
        NetSDKLib.NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY pstInParam = new NetSDKLib.NET_IN_LOGIN_WITH_HIGHLEVEL_SECURITY();
        pstInParam.nPort = m_nPort;
        pstInParam.szIP = m_strIp.getBytes();
        pstInParam.szPassword = m_strPassword.getBytes();
        pstInParam.szUserName = m_strUser.getBytes();
        NetSDKLib.NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY pstOutParam = new NetSDKLib.NET_OUT_LOGIN_WITH_HIGHLEVEL_SECURITY();
        pstOutParam.stuDeviceInfo = m_stDeviceInfo;
        m_hLoginHandle = netsdk.CLIENT_LoginWithHighLevelSecurity(pstInParam, pstOutParam);
        if (m_hLoginHandle.longValue() == 0L) 
        {
            System.err.printf("Login Device[%s] Port[%d]Failed. %s\n", new Object[] { m_strIp, m_nPort, ToolKits.getErrorCodePrint() });
        } 
        else 
        {
            System.out.println("Login Success [ " + m_strIp + " ]");
        } 
        return !(m_hLoginHandle.longValue() == 0L);
    }
  
    public static boolean logout() 
    {
        if (m_hLoginHandle.longValue() == 0L)
            return false; 
        boolean bRet = netsdk.CLIENT_Logout(m_hLoginHandle);
        if (bRet)
            m_hLoginHandle.setValue(0L); 
        return bRet;
    }
    
}
