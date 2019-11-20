package com.cci.payments.dto;

import java.util.List;

public class PageDTOGeneric<T> {
    private boolean success;
    private long totalCount;
    private List<T> items;

    public PageDTOGeneric(boolean success) {
        this.success = success;
    }

    public PageDTOGeneric(boolean success, List<T> items) {
        this.success = success;
        this.items = items;
    }

    public PageDTOGeneric(boolean success, long totalCount, List<T> items) {
        this.success = success;
        this.totalCount = totalCount;
        this.items = items;
    }

    public boolean isSuccess() {
        return success;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public List<T> getItems() {
        return items;
    }

}
