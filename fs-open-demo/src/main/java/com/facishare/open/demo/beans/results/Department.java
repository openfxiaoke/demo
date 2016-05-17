package com.facishare.open.demo.beans.results;

import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author huanghp
 * @date 2015-10-12
 */
public class Department implements Serializable {

    private static final long serialVersionUID = 3486839815456351252L;

    /**
     * 部门Id
     */
    private Integer id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 父部门Id
     */
    private Integer parentId;

    /**
     * 部门排序号
     */
    private Integer order;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.name, this.parentId, this.order);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Department other = (Department) obj;

        return Objects.equals(id, other.id) && Objects.equals(name, other.name)
                && Objects.equals(parentId, other.parentId) && Objects.equals(order, other.order);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("parentId", parentId)
                .add("order", order)
                .toString();
    }

}
