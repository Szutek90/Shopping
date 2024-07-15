package com.app.file_operator;

import java.util.Map;

public interface FileMapOperator <T>{
    Map<T, Integer> load(String filename);
}
