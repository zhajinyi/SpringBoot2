package com.jeeplus.modules.storage.entity;

import com.jeeplus.common.persistence.DataEntity;

/**
 * @Author BL
 * @Date: 2018/08/20
 * @Description
 * @Version
 */
public class PutType extends DataEntity<PutType> {

    private String num;//类型编码
    private String type;//类型名称
    private Integer isOut;//0:入库；1:出库
    private Integer isSys;//系统预置,0: 否；1: 是

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getIsOut() {
        return isOut;
    }

    public void setIsOut(Integer isOut) {
        this.isOut = isOut;
    }

    public Integer getIsSys() {
        return isSys;
    }

    public void setIsSys(Integer isSys) {
        this.isSys = isSys;
    }
}
