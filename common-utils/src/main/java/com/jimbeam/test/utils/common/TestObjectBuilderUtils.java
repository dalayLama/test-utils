package com.jimbeam.test.utils.common;

import lombok.experimental.UtilityClass;
import org.jeasy.random.EasyRandom;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.modelmapper.record.RecordModule;

@UtilityClass
public class TestObjectBuilderUtils {

    private final EasyRandom EASY_RANDOM = new EasyRandom();

    private final ModelMapper MODEL_MAPPER = new ModelMapper();

    static {
        MODEL_MAPPER.registerModule(new RecordModule());
        MODEL_MAPPER.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
                .setMethodAccessLevel(Configuration.AccessLevel.PRIVATE);
    }

    public static <T> T randomObject(Class<T> targetClass) {
        if (targetClass.isRecord()) {
            throw new IllegalArgumentException("Target class can't be a record");
        }
        return EASY_RANDOM.nextObject(targetClass);
    }

    public static <T> T map(Object source, Class<T> target) {
        if (target.isRecord()) {
            throw new IllegalArgumentException("Target can't be a record");
        }
        return MODEL_MAPPER.map(source, target);
    }

}
