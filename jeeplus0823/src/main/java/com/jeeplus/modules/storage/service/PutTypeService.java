package com.jeeplus.modules.storage.service;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.storage.dao.PutTypeDao;
import com.jeeplus.modules.storage.entity.PutType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;

/**
 * @Author BL
 * @Date: 2018/08/20
 * @Description 2018/08/21 完成对出入库类别的增删改查 批量删除
 * @Version
 */
@Service
public class PutTypeService extends CrudService<PutTypeDao, PutType> {
//    @Resource
//    private PutTypeDao putTypeDao;

    @Override
    public PutType get(String id) {
        return super.get(id);
    }

    @Override
    public List<PutType> findList(PutType putType) {
        return super.findList(putType);
    }

    @Override
    public Page<PutType> findPage(Page <PutType> page, PutType putType) {
        return super.findPage(page, putType);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(PutType putType) {
        super.save(putType);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(PutType putType) {
        super.delete(putType);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteAll(Collection<PutType> putType) {
        super.deleteAll(putType);
    }

}
