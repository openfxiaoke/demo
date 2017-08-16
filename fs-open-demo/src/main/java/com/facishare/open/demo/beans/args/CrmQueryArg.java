package com.facishare.open.demo.beans.args;

import com.google.common.base.MoreObjects;

import java.util.Arrays;
import java.util.List;

/**
 * Query Arg
 * Created by zhongcy on 2017/1/6.
 */
public class CrmQueryArg extends BaseArg{

    private static final long serialVersionUID = -6119269380581225350L;

    private String currentOpenUserId;

    private String apiName;

    private SearchQuery searchQuery;

    public String getCurrentOpenUserId() {
        return currentOpenUserId;
    }

    public void setCurrentOpenUserId(String currentOpenUserId) {
        this.currentOpenUserId = currentOpenUserId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public SearchQuery getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(SearchQuery searchQuery) {
        this.searchQuery = searchQuery;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("currentOpenUserId", currentOpenUserId)
                .add("apiName", apiName)
                .add("searchQuery", searchQuery)
                .toString();
    }

    public static class SearchQuery{
        //偏移量
        private int offset = 0;

        //获取数据条数，取最大值1000
        private int limit = 500;

        private DataProjection dataProjection;

        private List<RangeCondition> rangeConditions;

        private List<Order> orders;

        public int getOffset() {
            return offset;
        }

        public void setOffset(int offset) {
            this.offset = offset;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public DataProjection getDataProjection() {
            return dataProjection;
        }

        public void setDataProjection(DataProjection dataProjection) {
            this.dataProjection = dataProjection;
        }

        public List<RangeCondition> getRangeConditions() {
            return rangeConditions;
        }

        public void setRangeConditions(List<RangeCondition> rangeConditions) {
            this.rangeConditions = rangeConditions;
        }

        public List<Order> getOrders() {
            return orders;
        }

        public void setOrders(List<Order> orders) {
            this.orders = orders;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("offset", offset)
                    .add("limit", limit)
                    .add("dataProjection", dataProjection)
                    .add("rangeConditions", rangeConditions)
                    .add("orders", orders)
                    .toString();
        }
    }
    public static class DataProjection{
        private List<String> fieldNames = Arrays.asList("_id","name","last_modified_time");

        public List<String> getFieldNames() {
            return fieldNames;
        }

        public void setFieldNames(List<String> fieldNames) {
            this.fieldNames = fieldNames;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("fieldNames", fieldNames)
                    .toString();
        }
    }

    public static class RangeCondition{
        private String fieldName = "last_modified_time";

        private Long from;

        //默认不包含最低值
        private boolean includeLower = false;

        private Long to = 4102358400000L;

        private boolean includeUpper = false;


        public String getFieldName() {
            return fieldName;
        }

        public void setFieldName(String fieldName) {
            this.fieldName = fieldName;
        }

        public Long getFrom() {
            return from;
        }

        public void setFrom(Long from) {
            this.from = from;
        }

        public boolean isIncludeLower() {
            return includeLower;
        }

        public void setIncludeLower(boolean includeLower) {
            this.includeLower = includeLower;
        }

        public Long getTo() {
            return to;
        }

        public void setTo(Long to) {
            this.to = to;
        }

        public boolean isIncludeUpper() {
            return includeUpper;
        }

        public void setIncludeUpper(boolean includeUpper) {
            this.includeUpper = includeUpper;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("fieldName", fieldName)
                    .add("from", from)
                    .add("includeLower", includeLower)
                    .add("to", to)
                    .add("includeUpper", includeUpper)
                    .toString();
        }
    }

    public static class Order{
        //降序
        private boolean ascending = false;

        //字段,默认按照最后更新时间排序
        private String field = "last_modified_time";

        public boolean isAscending() {
            return ascending;
        }

        public void setAscending(boolean ascending) {
            this.ascending = ascending;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                    .add("ascending", ascending)
                    .add("field", field)
                    .toString();
        }
    }

}

