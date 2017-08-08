package com.spare.house.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dada on 2017/6/30.
 * House Trend
 */
@Data
public class HouseTrend implements Serializable {

    private long serialVersionUID = 1L;

    private String title;

    private String link;

    private String houseId;

    private List<Trend> trendList;

    public class Trend implements Serializable {
        private long serialVersionUID = 1L;

        private String date;

        private String price;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Trend{" +
                    "date='" + date + '\'' +
                    ", price='" + price + '\'' +
                    '}';
        }
    }

}
