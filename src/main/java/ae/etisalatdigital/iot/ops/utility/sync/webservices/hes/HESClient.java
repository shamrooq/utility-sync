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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
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
import org.apache.http.HttpStatus;
import org.apache.log4j.Logger;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.client.spi.ConnectorProvider;
import org.json.JSONArray;
import static ae.etisalatdigital.iot.ops.utility.sync.util.UtilityConstants.FWD_SLASH_STR;
import java.util.Collections;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.CipherSuite;
import okhttp3.ConnectionSpec;
import okhttp3.TlsVersion;

/**
 *
 * @author appadmin
 */
@Stateless
public class HESClient extends RestClient {
    
    private static final Logger LOGGER = Logger.getLogger(HESClient.class);
    private static final String SERVICE_USER_NAME = "malik";
    private static final String SERVICE_PASSWORD = "malik1";
    private static final String SERVICE_URL = "https://10.0.109.224:443/etisalat-digital/iot-ops/api-gw/hes-api/oum-ws/services/rest/EquipmentWebService";
    //"http://10.0.109.224:8080/oum-ws/services/rest/EquipmentWebService";

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
        
        String resourceURL = "/devices/type";
        
        String response = callGetMethod(SERVICE_URL, resourceURL, String.class);
        JSONArray arr = new JSONArray(response);
        System.out.println("JSONARR : " + arr);
        
    }

    public EquipmentResponseModel addNewMeterOnHES(BOMMeterDTO meter){
        String resourceURL = "/equipment";
        EquipmentRequestModel request = new EquipmentRequestModel();
        request.setCode(String.valueOf(meter.getId()));
        request.setSerialNumber(meter.getMeterSerial());
        Map<String, Object> paramsMap = new HashMap<>();
        populateCient();
        EquipmentResponseModel response = callPostMethod(SERVICE_URL, resourceURL,request, EquipmentResponseModel.class,paramsMap);
        return response;
    }

    public List<EquipmentResponseModel> updateGtwToMeterMappingOnHES(BOMGatewayEstDTO gateway, List<BOMMeterDTO> meters) {
        String url = SERVICE_URL+UtilityConstants.FWD_SLASH_STR+"equipment";
        EquipmentRequestModel request;
        List<EquipmentResponseModel> responseModelList=new ArrayList<>();
        EquipmentResponseModel response;
        for(BOMMeterDTO meter:meters){
            request = new EquipmentRequestModel();
            request.setCode(String.valueOf(meter.getId()));
            request.setSerialNumber(meter.getMeterSerial());
            request.setParentCode(String.valueOf(gateway.getId()));
            try{
                response = sendPut(url+FWD_SLASH_STR+String.valueOf(meter.getId()),request,EquipmentResponseModel.class);
            }
            catch(IOException ioe){
                response = new EquipmentResponseModel();
                response.setErrorCode(ioe.getMessage());
                response.setErrorNumber(Long.valueOf(HttpStatus.SC_INTERNAL_SERVER_ERROR));
                response.setStackTrace(Arrays.toString(ioe.getStackTrace()));
            }
            responseModelList.add(response);
        }
        return responseModelList;
    }
    
    public EquipmentResponseModel addNewSimOnHES(BOMGatewayEstDTO gateway) throws Exception{
        String apiURL = "communicationsEquipment";
        CommunicationEquipmentModel request = new CommunicationEquipmentModel();
        Property property = new Property();
        if(null!=gateway.getSimDetailsDTO()){
            request.setCode(gateway.getSimDetailsDTO().getId().toString());
            request.setDescription(gateway.getSimDetailsDTO().getSimICCID().toString());
            request.setCommunicationsEquipmentType(gateway.getSimDetailsDTO().getCommunicationEquipmentType());
            if(!gateway.getSimDetailsDTO().getChannelType().isEmpty()){
                request.setChannelGroup(gateway.getSimDetailsDTO().getChannelType());
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
        //EquipmentResponseModel response = //callPostMethod("http://10.0.109.224:8080", apiURL,request, EquipmentResponseModel.class,paramsMap);
        EquipmentResponseModel response = sendPost(SERVICE_URL+FWD_SLASH_STR+apiURL,request,EquipmentResponseModel.class);
        return response;
    }

    public EquipmentResponseModel updateGatewayOnHES(Requests utilityReq, BOMGatewayEstDTO gateway) throws Exception{
        //String apiURL = "/equipment";
        EquipmentRequestModel request = new EquipmentRequestModel();
        request.setAccountNumber(utilityReq.getAccountNumber());
        request.setCode(String.valueOf(gateway.getId()));
        //request.setCode(gateway.getSerialNumber());
        request.setSerialNumber(gateway.getSerialNumber());
        if(UtilityConstants.WATER_GATEWAY_STR.equalsIgnoreCase(gateway.getGatewaysType())){
            request.setTypeId(Long.valueOf(1998));
            request.setUtilityId(Long.valueOf(2));
        }
        else if(UtilityConstants.ELECTRIC_GATEWAY_STR.equalsIgnoreCase(gateway.getGatewaysType())){
            request.setTypeId(Long.valueOf(1997));
            request.setUtilityId(Long.valueOf(1));
        }
        request.setModelId(gateway.getGatewayModel().getDeviceHESModelId());
        /* 
        Map<String, Object> paramsMap = new HashMap<>();
        populateCient();
        EquipmentResponseModel response = callPostMethod(SERVICE_URL, apiURL,request, EquipmentResponseModel.class,paramsMap);*/
        EquipmentResponseModel response = sendPost(SERVICE_URL+FWD_SLASH_STR+"equipment",request,EquipmentResponseModel.class);
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

                /*@Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }*/
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
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
        //httpClient = new OkHttpClient();
        // form parameters
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(requestBody);
        LOGGER.info("Request json string is "+json);
        RequestBody body = RequestBody.create(json, MediaType.get(javax.ws.rs.core.MediaType.APPLICATION_JSON));
        Request.Builder builder = new Request.Builder().url(url);
        /*Request request = sslConfig(builder)
                .post(body)
                .build();*/
        T t =null;
        try (Response response = sslConfig().newCall(addHeaders(builder).post(body).build()).execute()) {
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

    private <T> T sendPut(String url, Object requestBody, Class<T> responseType) throws IOException {
        //httpClient = new OkHttpClient();
        ObjectMapper objectMapper = new ObjectMapper();
        T t = null;
        try{
            String json = objectMapper.writeValueAsString(requestBody);
            LOGGER.info("Request json string is " + json + "\n url is "+url);
            RequestBody body = RequestBody.create(json, MediaType.get(javax.ws.rs.core.MediaType.APPLICATION_JSON));
            Request.Builder builder = new Request.Builder().url(url);
            try (Response response = sslConfig().newCall(
                    addHeaders(builder)
                            .put(body)
                            .build()).execute()) {
                if (null == response || !response.isSuccessful()) {
                    throw new IOException("Exception " + response);
                }
                LOGGER.info("TLS Version is : "+response.handshake().tlsVersion().name());
                ResponseBody responseBody = response.body();
                if (null != responseBody) {
                    json = responseBody.string();
                    LOGGER.info("Response json string is " + json);
                    objectMapper = new ObjectMapper();
                    t = objectMapper.readValue(json, responseType);
                }
            }
            catch (IOException e) {
                throw new IOException(e);
            }
        }
        catch (JsonProcessingException jpe) {
            throw new IOException(jpe);
        }      
        return t;
    }
    
    private OkHttpClient sslConfig(){
        OkHttpClient client = null;
        try{
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            TrustManager[] certs = getTrustManager();
            sslContext.init(null, certs, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            OkHttpClient.Builder sslBuilder = new OkHttpClient.Builder();
            sslBuilder.sslSocketFactory(sslSocketFactory, (X509TrustManager)certs[0]);
            sslBuilder.hostnameVerifier(getHostnameVerifier());
            ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
            //.tlsVersions(TlsVersion.TLS_1_3)
                    .allEnabledCipherSuites()
                    .allEnabledTlsVersions()
                    .cipherSuites(
                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384,
                            CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256,
                            CipherSuite.TLS_DHE_RSA_WITH_AES_128_GCM_SHA256).build();
            client = sslBuilder.connectionSpecs(Collections.singletonList(spec))
                    //.addNetworkInterceptor(new RequestHeadersInterceptor(addHeaders()))
                    .build();
        }
        catch(NoSuchAlgorithmException | KeyManagementException e){
        }
        return client;
    }
    private Map<String, String> addHeaders(){
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("User-Agent", "OkHttp Bot");
        headerMap.put(HttpHeaders.CONTENT_TYPE, javax.ws.rs.core.MediaType.APPLICATION_JSON);
        headerMap.put("Authorization", Credentials.basic(SERVICE_USER_NAME, SERVICE_PASSWORD));
        headerMap.put("apikey", "9c1861bc-94ad-4e30-bf01-9271eaa0cd48");
        return headerMap;
    }
    private Request.Builder addHeaders(Request.Builder builder){
        builder.addHeader("User-Agent", "OkHttp Bot");
        builder.addHeader(HttpHeaders.CONTENT_TYPE, javax.ws.rs.core.MediaType.APPLICATION_JSON);
        builder.addHeader("Authorization", Credentials.basic(SERVICE_USER_NAME, SERVICE_PASSWORD));
        builder.addHeader("apikey", "9c1861bc-94ad-4e30-bf01-9271eaa0cd48");
        return builder;
    }
}
