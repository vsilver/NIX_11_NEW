package com.service;

import com.model.product.Manufacturer;
import com.model.product.OperationSystem;
import com.model.product.Phone;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class MapperService {
    public static Phone phoneMapper(Map<String, String> map) {
        OperationSystem operationSystem = new OperationSystem();
        operationSystem.setDesignation(map.get("designation"));
        operationSystem.setVersion(Integer.parseInt(map.get("version")));
        return new Phone(map.get("title"),
                Integer.parseInt(map.get("count")),
                Long.parseLong(map.get("price")),
                map.get("model"),
                Manufacturer.valueOf(map.get("manufacturer")),
                LocalDateTime.parse(map.get("created"), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")),
                map.get("currency"),
                operationSystem);
    }
}
