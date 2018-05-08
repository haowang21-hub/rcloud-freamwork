package com.rcloud.framework.common.controller;

import static com.rcloud.framework.common.http.ResponseMessage.ok;

import java.io.Serializable;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rcloud.framework.common.entity.web.Model;
import com.rcloud.framework.common.http.ResponseMessage;
import com.rcloud.framework.common.service.BaseService;

import io.swagger.annotations.ApiOperation;

public interface UpdateController<E, PK extends Serializable, M> extends SupperController<E, Serializable, Model> {

    BaseService<E, PK> getService();

    /**
     * Description: 修改实体通用接口
     * 
     * @author wanghao
     * @param id 主键
     * @param data 实体信息
     * @return
     */
    @PutMapping(path = "/{id}")
    @ApiOperation("修改数据")
    default ResponseMessage<Integer> updateByPrimaryKey(@PathVariable PK id, @RequestBody E data) {
        return ok(getService().updateByPk(id, data));
    }
}
