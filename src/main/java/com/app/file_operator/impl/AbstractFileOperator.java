package com.app.file_operator.impl;

import com.app.file_operator.FileListOperator;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractFileOperator<T> implements FileListOperator<T> {
    abstract T parse(String line);

    @Override
    public List<T> load(String filename) {
        try(var lines = Files.lines(Paths.get(filename))){
            var result = new ArrayList<T>();
            for(var line: lines.toList()){
                result.add(parse(line));
            }
            return result;
        }
        catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}
