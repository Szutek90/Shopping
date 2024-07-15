package com.app.model.preference;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PreferenceTest {

     @Test
    @DisplayName("When parsing")
    void test(){
         Assertions.assertThat(Preference.parse("1 KOSMETYKI")).isEqualTo(
                 new Preference(1, "KOSMETYKI")
         );
     }
}
