package com.yoosal.zqmh.util.filefilter;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by qinmingtao on 2016/8/29.
 * Desc 压缩文件过滤
 */
public class CompressFileFilter implements FileFilter {
    public boolean accept(File file) {
        if (file != null && (file.getName().endsWith(".zip") || file.getName().endsWith(".rar"))) {
            return true;
        }
        return false;
    }
}
