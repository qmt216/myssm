package com.yoosal.zqmh.util;

import java.util.Date;
import java.util.Random;

/**
 * Created by 秦明涛 on 2016/1/15.
 * Desc 生成随机数
 */
public class RandomUtil {
    public static Random rand = new Random(new Date().getTime());

    public static int nextInt(int i){
        return rand.nextInt(i);
    }

    /**
     * 生成两个数之间的随机数
     * @param from 最小
     * @param to 最大
     * @return 随机数
     */
    public static int nextInt(int from, int to){
        return rand.nextInt(to - from) + from;
    }
}
