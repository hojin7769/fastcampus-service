package org.delivery.storeadmin.config.objectMapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfig {
    @Bean
    public ObjectMapper objectMapper() {
        var objectMapper = new ObjectMapper();

        objectMapper.registerModule(new Jdk8Module()); //jdk8 버전 이후 클래스
        objectMapper.registerModule(new JavaTimeModule()); //localDate
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false); //모르는 속성이 있어도 무시
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false); //빈값을 보내도 무시

        //날짜관련 직렬화
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        //스네이크 케이스
        objectMapper.setPropertyNamingStrategy(new PropertyNamingStrategies.SnakeCaseStrategy());


        return objectMapper;
    }
}
