/*
 * Copyright (c) from 2018 to present, Etisalat Digital and/or its affiliates. All rights reserved.
 * ETISALAT DIGITAL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 */
package ae.etisalatdigital.iot.ops.utility.sync.webservices.hes;

import ae.etisalatdigital.commonUtils.exception.WebServiceException;
import ae.etisalatdigital.commonUtils.ws.rest.RestClient;
import ae.etisalatdigital.commonUtils.ws.rest.RestClientFilter;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMGatewayEstDTO;
import ae.etisalatdigital.iot.ops.utility.sync.dtos.BOMMeterDTO;
import ae.etisalatdigital.iot.ops.utility.sync.entities.Requests;
import ae.etisalatdigital.iot.ops.utility.sync.util.UtilityConstants;
import ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.models.CommunicationEquipmentModel;
import ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.models.EquipmentRequestModel;
import ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.models.EquipmentResponseModel;
import ae.etisalatdigital.iot.ops.utility.sync.webservices.hes.models.Property;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import okhttp3.Credentials;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.http.HttpHeaders;
import org.apache.log4j.Logger;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.client.spi.ConnectorProvider;
import org.json.JSONArray;

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
    private OkHttpClient httpClient;
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
        
        String resourceURL = "/equipment";
        
        String response = callGetMethod(SERVICE_URL, resourceURL, String.class);
        JSONArray arr = new JSONArray(response);
        System.out.println("JSONARR : " + arr);
        
    }

    public EquipmentResponseModel addNewMeterOnHES(BOMMeterDTO meter){
        String resourceURL = "/equipment";
        EquipmentRequestModel request = new EquipmentRequestModel();
        request.setCode(meter.getMeterSerial());
        request.setSerialNumber(meter.getMeterSerial());
        Map<String, Object> paramsMap = new HashMap<>();
        populateCient();
        EquipmentResponseModel response = callPostMethod(SERVICE_URL, resourceURL,request, EquipmentResponseModel.class,paramsMap);
        return response;
    }

    public List<EquipmentResponseModel> addNewMeterOnHES(BOMGatewayEstDTO gateway, List<BOMMeterDTO> meters) throws Exception{
        String resourceURL = "/equipment";
        EquipmentRequestModel request;
        List<EquipmentResponseModel> responseModelList=new ArrayList<>();
        EquipmentResponseModel response;
        for(BOMMeterDTO meter:meters){
            request = new EquipmentRequestModel();
            request.setCode(meter.getMeterSerial());
            request.setSerialNumber(meter.getMeterSerial());
            request.setParent_code(gateway.getSerialNumber());
            response = sendPut(SERVICE_URL+resourceURL,request,EquipmentResponseModel.class);
            responseModelList.add(response);
        }
        return responseModelList;
    }
    
    public EquipmentResponseModel addNewSimOnHES(BOMGatewayEstDTO gateway) throws Exception{
        String resourceURL = "/oum-ws/services/rest/EquipmentWebService/communicationsEquipment";
        CommunicationEquipmentModel request = new CommunicationEquipmentModel();
        Property property = new Property();
        if(null!=gateway.getSimDetailsDTO()){
            request.setCode(gateway.getSimDetailsDTO().getSimICCID().toString());
            request.setDescription(gateway.getSimDetailsDTO().getDescription());
            request.setCommunicationsEquipmentType(gateway.getSimDetailsDTO().getCommunicationEquipmentType());
            if(!gateway.getSimDetailsDTO().getChannelGroup().isEmpty()){
                request.setChannelGroup(gateway.getSimDetailsDTO().getChannelGroup());
            }
            property.setIp(gateway.getSimDetailsDTO().getIp());
            property.setPort(gateway.getSimDetailsDTO().getPort());
        }
        else{
            request.setDescription("new sim for gateway for gateway with SN : "+gateway.getSerialNumber());
            request.setCommunicationsEquipmentType(gateway.getSimDetailsDTO().getCommunicationEquipmentType());
            property.setIp("1.1.1.1");
            property.setPort("8080");
        }
        request.setProperty(property);
        //Map<String, Object> paramsMap = new HashMap<>();
        //populateCient();
        //EquipmentResponseModel response = //callPostMethod("http://10.0.109.224:8080", resourceURL,request, EquipmentResponseModel.class,paramsMap);
        EquipmentResponseModel response = sendPost(SERVICE_URL+"/"+"communicationsEquipment",request,EquipmentResponseModel.class);
        return response;
    }

    public EquipmentResponseModel addNewGatewayOnHES(Requests utilityReq, BOMGatewayEstDTO gateway) throws Exception{
        //String resourceURL = "/equipment";
        EquipmentRequestModel request = new EquipmentRequestModel();
        request.setAccountNumber(utilityReq.getAccountNumber());
        //request.setCode(String.valueOf(gateway.getId()));
        request.setCode(gateway.getSerialNumber());
        request.setSerialNumber(gateway.getSerialNumber());
        if(UtilityConstants.WATER_GATEWAY_STR.equalsIgnoreCase(gateway.getGatewaysType())){
            request.setType_id(Long.valueOf(1998));
            request.setUtility_id(Long.valueOf(2));
        }
        else if(UtilityConstants.ELECTRIC_GATEWAY_STR.equalsIgnoreCase(gateway.getGatewaysType())){
            request.setType_id(Long.valueOf(1997));
            request.setUtility_id(Long.valueOf(1));
        }
        request.setModel_id(gateway.getGatewayModelId());
        /* 
        Map<String, Object> paramsMap = new HashMap<>();
        populateCient();
        EquipmentResponseModel response = callPostMethod(SERVICE_URL, resourceURL,request, EquipmentResponseModel.class,paramsMap);*/
        EquipmentResponseModel response = sendPost(SERVICE_URL+"/"+"equipment",request,EquipmentResponseModel.class);
        return response;
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

    private <T> T sendPost(String url, Object requestBody, Class<T> responseType) throws Exception {
        httpClient = new OkHttpClient();
        // form parameters
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(requestBody);
        LOGGER.info("Request json string is "+json);
        RequestBody formBody = RequestBody.create(json, MediaType.get(javax.ws.rs.core.MediaType.APPLICATION_JSON));
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader(HttpHeaders.CONTENT_TYPE, javax.ws.rs.core.MediaType.APPLICATION_JSON)
                .addHeader("Authorization", Credentials.basic(SERVICE_USER_NAME, SERVICE_PASSWORD))
                .post(formBody)
                .build();
        T t =null;
        try (Response response = httpClient.newCall(request).execute()) {
            if (null == response || !response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            ResponseBody responseBody = response.body();
            try {
                if(null!=responseBody){
                    json = responseBody.string();
                    LOGGER.info("Response json string is "+json);
                    objectMapper = new ObjectMapper();
                    t = objectMapper.readValue(json, responseType);
                }
            } catch (IOException ex) {
                LOGGER.error("Exception caught" , ex);
                throw new IOException("Unexpected code " + response);
            }
            return t;
        }
    }

    private <T> T sendPut(String url, Object requestBody, Class<T> responseType) throws Exception {
        httpClient = new OkHttpClient();
        // form parameters
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(requestBody);
        LOGGER.info("Request json string is "+json);
        RequestBody formBody = RequestBody.create(json, MediaType.get(javax.ws.rs.core.MediaType.APPLICATION_JSON));
        Request request = new Request.Builder()
                .url(url)
                .addHeader("User-Agent", "OkHttp Bot")
                .addHeader(HttpHeaders.CONTENT_TYPE, javax.ws.rs.core.MediaType.APPLICATION_JSON)
                .addHeader("Authorization", Credentials.basic(SERVICE_USER_NAME, SERVICE_PASSWORD))
                .put(formBody)
                .build();
        T t =null;
        try (Response response = httpClient.newCall(request).execute()) {
            if (null == response || !response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            ResponseBody responseBody = response.body();
            try {
                if(null!=responseBody){
                    json = responseBody.string();
                    LOGGER.info("Response json string is "+json);
                    objectMapper = new ObjectMapper();
                    t = objectMapper.readValue(json, responseType);
                }
            } catch (IOException ex) {
                LOGGER.error("Exception caught" , ex);
                throw new IOException("Unexpected code " + response);
            }
            return t;
        }
    }
}
