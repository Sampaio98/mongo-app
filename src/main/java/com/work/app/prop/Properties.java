package com.work.app.prop;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class Properties {

    @Value("${api.city.find.url}")
    public String cityFindUrl;
}
