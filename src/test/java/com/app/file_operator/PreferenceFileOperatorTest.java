package com.app.file_operator;

import com.app.file_operator.impl.PreferenceFileOperator;
import com.app.model.preference.Preference;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class PreferenceFileOperatorTest {
    @Test
    @DisplayName("When loading preferences")
    void test() {
        var operator = new PreferenceFileOperator();
        Assertions.assertThat(operator.load("src/test/resources/preferences.csv"))
                .containsExactlyInAnyOrderElementsOf(List.of(
                        new Preference(1, "KOSMETYKI"),
                        new Preference(2, "MOTORYZACJA")));
    }
}
