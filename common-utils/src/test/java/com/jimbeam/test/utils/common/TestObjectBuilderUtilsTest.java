package com.jimbeam.test.utils.common;

import com.jimbeam.test.utils.common.model.AnotherObject;
import com.jimbeam.test.utils.common.model.SomeObject;
import com.jimbeam.test.utils.common.model.SomeRecord;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

public class TestObjectBuilderUtilsTest {

    @Test
    void shouldConvertObjectToObject() {
        var source = TestObjectBuilderUtils.randomObject(SomeObject.class);
        var result = TestObjectBuilderUtils.map(source, AnotherObject.class);

        assertThat(result).usingRecursiveComparison().isEqualTo(source);
    }

    @Test
    void shouldConvertRecordToObject() {
        var source = new SomeRecord("str", 123, new String[0], new ArrayList<>(), new HashMap<>());
        var result = TestObjectBuilderUtils.map(source, SomeObject.class);

        assertThat(result).usingRecursiveComparison().isEqualTo(source);
    }

}
