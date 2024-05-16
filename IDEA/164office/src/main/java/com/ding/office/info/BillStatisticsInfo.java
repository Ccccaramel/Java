package com.ding.office.info;

import java.math.BigDecimal;

public class BillStatisticsInfo {
    private Integer year;
    private Integer month;
    private BigDecimal valueOfMonth;
    private BigDecimal maxOfMonth;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public BigDecimal getValueOfMonth() {
        return valueOfMonth;
    }

    public void setValueOfMonth(BigDecimal valueOfMonth) {
        this.valueOfMonth = valueOfMonth;
    }

    public BigDecimal getMaxOfMonth() {
        return maxOfMonth;
    }

    public void setMaxOfMonth(BigDecimal maxOfMonth) {
        this.maxOfMonth = maxOfMonth;
    }
}
