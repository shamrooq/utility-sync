/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.webservices.hes;

import ae.etisalatdigital.commonUtils.exception.WebServiceException;
import ae.etisalatdigital.commonUtils.ws.rest.RestClient;
import ae.etisalatdigital.commonUtils.ws.rest.RestClientFilter;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import org.apache.log4j.Logger;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.client.spi.ConnectorProvider;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author appadmin
 */
@Stateless
public class HESClient extends RestClient {
    
    private static final Logger LOGGER = Logger.getLogger(HESClient.class);
    private static final String SERVICE_USER_NAME = "malik";
    private static final String SERVICE_PASSWORD = "malik1";
    private static final String SERVICE_URL = "http://10.0.109.224:8080/oum-ws/services/rest/EquipmentWebService";
    
    public HESClient(){
        
    }
    
    private void populateCient(){
        
        ClientConfig clientConfig = new ClientConfig();
        ConnectorProvider connectorProvider = new ApacheConnectorProvider();
        
        try{
            clientConfig.connectorProvider(connectorProvider);
        
            SSLContext sslContext = SSLContext.getInstance("ssl");
            sslContext.init(null, getTrustManager(), null);
            
            ClientBuilder builder = ClientBuilder.newBuilder().withConfig(clientConfig)
                                                              .sslContext(sslContext)
                                                              .hostnameVerifier(getHostnameVerifier());
            
            Client client = builder.build();
            RestClientFilter clientFilter = new RestClientFilter();
            
            ///Map<String, String> customHeaders = new HashMap<>();
            ///customHeaders.put("Authorization", "Basic bWFsaWs6bWFsaWsx");
            ///clientFilter.setCustomHeaders(customHeaders);
            
            client.register(clientFilter);
            
            HttpAuthenticationFeature httpAuthFeature = HttpAuthenticationFeature.basic(SERVICE_USER_NAME, SERVICE_PASSWORD);
            client.register(httpAuthFeature);
            setClient(client);
            
            
            
        }catch(KeyManagementException | NoSuchAlgorithmException ex){
            LOGGER.error(ex);
        }
    }
    
    public void GetAllDevices() throws WebServiceException {
        
        populateCient();
        
        String resourceURL = "/devices/type";
        
        String response = callGetMethod(SERVICE_URL, resourceURL, String.class);
        JSONArray arr = new JSONArray(response);
        System.out.println("JSONARR : " + arr);
        
    }
    
    /**
     * Create a trust manager that does not validate certificate chains.
     * 
     * @return 
     */
    private TrustManager[] getTrustManager() {
        return new TrustManager[] {
            new X509TrustManager() {

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };
    }
    
    /**
     * Create host name verifier that allow all host names.
     * 
     * @return 
     */
    private HostnameVerifier getHostnameVerifier() {
        return (String hostname, SSLSession session) -> true;
    }
}
