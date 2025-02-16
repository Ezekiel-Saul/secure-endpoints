package com.zql.secure_endpoints.entity;


public class DumpData {
    private Long id;
    private String data;

    public DumpData() {
    }

    public DumpData(Long id, String data) {
        this.id = id;
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DumpData{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
