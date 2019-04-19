package com.jeeplus.modules.storage.service;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.storage.dao.GoodUnitesDao;
import com.jeeplus.modules.storage.entity.GoodUnites;
import com.jeeplus.modules.storage.entity.PutType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

/**
 * @Author BL
 * @Date: 2018/08/21
 * @Description 2018/08/22 完成对计量单位的增删改查 批量删除
 * @Version
 */
@Service
public class GoodUnitesService extends CrudService<GoodUnitesDao, GoodUnites> {

    @Override
    public GoodUnites get(String id) {
        return super.get(id);
    }

    @Override
    public List<GoodUnites> findList(GoodUnites goodUnites) {
        return super.findList(goodUnites);
    }

    @Override
    public Page<GoodUnites> findPage(Page <GoodUnites> page, GoodUnites goodUnites) {
        return super.findPage(page, goodUnites);
    }

    @Override
    @Transactional(readOnly = false)
    public void save(GoodUnites goodUnites) {
        super.save(goodUnites);
    }

    @Override
    @Transactional(readOnly = false)
    public void delete(GoodUnites goodUnites) {
        super.delete(goodUnites);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteAll(Collection<GoodUnites> goodUnites) {
        super.deleteAll(goodUnites);
    }

}
