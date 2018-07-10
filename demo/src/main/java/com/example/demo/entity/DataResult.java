package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

/**
 * easyui返回对象
 *
 * @author lishunli
 * @create 2017-11-17 10:26
 **/
public class DataResult implements Serializable{
        private long total;
        private List<?> rows;


        public long getTotal() {
            return total;
        }

        public void setTotal(long total) {
            this.total = total;
        }

        public List<?> getRows() {
            return rows;
        }

        public void setRows(List<?> rows) {
            this.rows = rows;
        }
}
