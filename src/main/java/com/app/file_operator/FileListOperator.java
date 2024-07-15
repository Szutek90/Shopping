package com.app.file_operator;

import java.util.List;

public interface FileListOperator <T>{
    List<T> load(String filename);
}
