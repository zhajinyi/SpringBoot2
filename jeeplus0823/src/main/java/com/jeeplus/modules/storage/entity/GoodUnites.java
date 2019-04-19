package com.jeeplus.modules.storage.entity;

import com.jeeplus.common.persistence.DataEntity;

/**
 * @Author BL
 * @Date: 2018/08/21
 * @Description 2018/08/22 完成对计量单位的增删改查 批量删除
 * @Version
 */
public class GoodUnites extends DataEntity<GoodUnites> {
    private String unitName;//单位组名称`uname`的别名
    private String unit;//主计量单位

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
