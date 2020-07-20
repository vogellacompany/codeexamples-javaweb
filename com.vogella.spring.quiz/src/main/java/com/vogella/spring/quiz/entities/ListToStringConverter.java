package com.vogella.spring.quiz.entities;
import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

	
public class ListToStringConverter implements AttributeConverter<List<String>, String> {
    @Override
    public String convertToDatabaseColumn(List<String> attribute) {
        if (attribute == null || attribute.isEmpty()){
            return "";
        }
       StringBuilder sb = new StringBuilder();
       attribute.stream().limit(attribute.size()-1).forEach(s -> sb.append(s).append(","));
       sb.append(attribute.get(attribute.size()-1));
       return sb.toString();
    }

    @Override
    public List<String> convertToEntityAttribute(String dbData) {
        if (StringUtils.isEmpty(dbData)){
            return new ArrayList<>();
        }
        String[] data = dbData.split(",");
        return   Arrays.stream(data).map(String::trim).collect(Collectors.toList());
    }

}