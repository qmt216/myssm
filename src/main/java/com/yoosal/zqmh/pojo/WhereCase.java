package com.yoosal.zqmh.pojo;

/**
 * Created by qinmingtao on 2016/8/18.
 * Desc
 */
public class WhereCase {
    private String condition;
    private String order;
    private int start;
    private int limit;

    public WhereCase() {
    }

    public WhereCase(String condition) {
        this.condition = condition;
    }

    public WhereCase(String condition, String order) {
        this.condition = condition;
        this.order = order;
    }

    public WhereCase(String condition, String order, int start, int limit) {
        this.condition = condition;
        this.order = order;
        this.start = start;
        this.limit = limit;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
