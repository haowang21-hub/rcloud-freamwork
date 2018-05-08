package com.rcloud.framework.common.controller;

import static com.rcloud.framework.common.http.ResponseMessage.ok;

import java.io.Serializable;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rcloud.framework.common.entity.web.Model;
import com.rcloud.framework.common.service.BaseService;

import io.swagger.annotations.ApiOperation;

public interface DeleteController<E, PK extends Serializable> extends SupperController<E, Serializable, Model> {

    BaseService<E, PK> getService();

    @DeleteMapping(path = "/{id:.+}")
    @ApiOperation("删除数据")
    default ResponseEntity<?> deleteByPrimaryKey(@PathVariable PK id) {
        int k = getService().deleteByPk(id);
        if (k > 0) {
            return ResponseEntity.ok(ok("OK"));
        }
        return ResponseEntity.noContent().build();
    }

}
