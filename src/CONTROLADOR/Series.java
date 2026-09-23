/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROLADOR;

/**
 *
 * @author Admin
 */

import com.fasterxml.jackson.databind.ObjectMapper;
//import javax.xml.ws.Response;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;


public class Series 
{   
        
    public static Response readSeries(String FINI,String FFIN) {
            
            //La URL a consultar con los parametros de idSerie y fechas            formato AAAA-MM-DD
            String url="https://www.banxico.org.mx/SieAPIRest/service/v1/series/SF43718/datos/"+FINI+"/"+FFIN+"";
            //String url="https://www.banxico.org.mx/SieAPIRest/service/v1/series/SF43718/datos/2024-12-18/2024-12-19";
            //Se especifica la versión del protocolo TLS 1.3
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory (
                            SSLContexts.createDefault(),
                            new String [] {"TLSv1.2"},null,
                            SSLConnectionSocketFactory.getDefaultHostnameVerifier());
            Response response;
            try (CloseableHttpClient httpclient = 
                HttpClients.custom().setSSLSocketFactory(sslsf).build()) {
                //Se realiza una petición GET
                HttpGet getMethod = new HttpGet(url);
                //Se solicita que la respuesta esté en formato JSON
                getMethod.setHeader("Content-Type", "application/json");
                //Se envía el header Bmx-Token con el token de consulta
                //Modificar por el token de consulta propio
                getMethod.setHeader("Bmx-Token", "5a43dec86ebe7141f1b2de5871694bf99b3b088e8507450eeb55e6358d1af742");
                HttpResponse httResponse = httpclient.execute(getMethod);
                StatusLine statusLine = httResponse.getStatusLine();
                //En caso de ser exitosa la petición se devuelve un estatus HTTP 200
                if  (statusLine.getStatusCode() != HttpStatus.SC_OK) {
                    System.out.println("Mensaje de error "+statusLine.getReasonPhrase() );
                    throw new RuntimeException ("Http codigo de error: "+statusLine);
                }   
                //Se utiliza Jackson para mapear el JSON a objetos Java
                ObjectMapper mapper = new ObjectMapper();
                //response = (Response) mapper.readTree(httResponse.getEntity().getContent());
                response = mapper.readValue(httResponse.getEntity().getContent(),Response.class);
                httpclient.close();
                
            }
            catch(Exception e)
            {
                response = null;
            }
            
            return response;

	}

	/*public static void main(String[] args) {
		try {
			//Response response = readSeries();
                        Response response =(Response) readSeries();
                        
			Serie serie=response.getBmx().getSeries().get(0);
			System.out.println("Serie: "+serie.getTitulo());
			for(DataSerie data:serie.getDatos()){
				//Se omiten las observaciones sin dato (N/E)
				if(data.getDato().equals("N/E")) continue;
				System.out.println("Fecha: "+data.getFecha());
				System.out.println("Dato: "+data.getDato());
			}
			
		} catch(Exception e) {
			System.out.println("ERROR: "+e.getMessage());
		}
	}*/

}
