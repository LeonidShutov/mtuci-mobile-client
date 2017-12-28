package com.shutovleonid.mtuci_mobile_client;

public class ApiUtils {

    private ApiUtils() {}

    public static final String BASE_URL = "http://52.178.191.209:8080/";

    public static APIService getAPIService() {
        return RetrofitClient.getClient(BASE_URL).create(APIService.class);
    }
}