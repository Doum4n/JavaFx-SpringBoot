package com.example.alpha.JavaFx.DatabaseConnection;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

public class getHttpConnection {
    static String response;

    public static ObjectMapper getData(String url) throws JsonProcessingException {
//         Tạo RestTemplate
        RestTemplate restTemplate = new RestTemplate();
//         Gửi yêu cầu GET đến API RESTful
        response = restTemplate.getForObject(url, String.class);
//         Chuyển đổi JSON sang đối tượng Java
        ObjectMapper mapper = new ObjectMapper();
//        List<KhachHang> myObjects = mapper.readValue(response, new TypeReference<>() {});
//        return FXCollections.observableArrayList(myObjects);
        return mapper;
    }

    public static String getResponse() {
        return response;
    }
}
