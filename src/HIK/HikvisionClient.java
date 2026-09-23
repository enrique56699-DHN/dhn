/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HIK;

/**
 *
 * @author win 10
 */
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class HikvisionClient {
    private static final String BASE_URL = "http://192.168.1.150";
    private static final String USERNAME = "admin";
    private static final String PASSWORD = "User2837.";
    
    private CloseableHttpClient httpClient;
    private ObjectMapper objectMapper;
    
    public HikvisionClient() {
        this.objectMapper = new ObjectMapper();
        setupHttpClient();
    }
    
    private void setupHttpClient() {
        CredentialsProvider provider = new BasicCredentialsProvider();
        provider.setCredentials(
            AuthScope.ANY,
            new UsernamePasswordCredentials(USERNAME, PASSWORD)
        );
        
        this.httpClient = HttpClients.custom()
            .setDefaultCredentialsProvider(provider)
            .build();
    }
    
    public AcsEventResponse getAccessEvents(Date startDate, Date endDate, int maxResults) throws Exception {
        String url = BASE_URL + "/ISAPI/AccessControl/AcsEvent?format=json";
        
        // Formatear fechas
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String startTime = sdf.format(startDate);
        String endTime = sdf.format(endDate);
        
        // Crear solicitud
        String searchID = UUID.randomUUID().toString();
        AcsEventCond cond = new AcsEventCond(searchID, maxResults, startTime, endTime);
        SearchRequest request = new SearchRequest(cond);
        
        // Convertir a JSON
        String jsonRequest = objectMapper.writeValueAsString(request);
        
        // Crear POST request
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader("Content-Type", "application/json");
        httpPost.setEntity(new StringEntity(jsonRequest, "UTF-8"));
        
        // Ejecutar solicitud
        HttpResponse response = httpClient.execute(httpPost);
        String responseBody = EntityUtils.toString(response.getEntity());
        
        // Verificar código de respuesta
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new Exception("Error en la solicitud: " + statusCode + " - " + responseBody);
        }
        
        // Parsear respuesta
        return objectMapper.readValue(responseBody, AcsEventResponse.class);
    }
    
    public void close() throws Exception {
        if (httpClient != null) {
            httpClient.close();
        }
    }
}