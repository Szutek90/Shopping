package com.app.file_operator.impl;

import com.app.model.preference.Preference;

public class PreferenceFileOperator extends AbstractFileOperator<Preference> {

    @Override
    Preference parse(String line) {
        return Preference.parse(line);
    }
}
