package com.jimbeam.test.utils.common.model;

import java.util.List;
import java.util.Map;


public record SomeRecord(
        String stringValue,
        int intValue,
        String[] stringArrayValue,
        List<Integer> integerArrayValue,
        Map<String, Double> stringDoubleMap
) {}

