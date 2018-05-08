package com.rcloud.framework.common.controller;

import java.io.Serializable;

import com.rcloud.framework.common.entity.web.Model;
import com.rcloud.framework.common.service.BaseService;

/**
 * <Description> 通用增删改查控制器
 * 
 * @author wanghao
 * @CreateDate 2018年3月19日 上午9:06:37
 * @since V1.0
 * @see com.rcloud.framework.common.controller
 */
public interface CrudController<E, PK extends Serializable, M extends Model>
    extends QueryController<E, PK, M>, UpdateController<E, PK, M>, CreateController<E, PK, M>, DeleteController<E, PK> {

    @Override
    BaseService<E, PK> getService();
}
