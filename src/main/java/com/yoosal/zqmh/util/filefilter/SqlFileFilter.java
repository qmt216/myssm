package com.yoosal.zqmh.util.filefilter;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by qinmingtao on 2016/8/29.
 * Desc sql文件过滤
 */
public class SqlFileFilter implements FileFilter {
    public boolean accept(File file) {
        if(file!=null && file.getName().endsWith(".sql")){
            return true;
        }
        return false;
    }
}
