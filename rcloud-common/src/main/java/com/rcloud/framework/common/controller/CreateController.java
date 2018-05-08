package com.rcloud.framework.common.controller;

import static com.rcloud.framework.common.http.ResponseMessage.ok;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rcloud.framework.common.entity.web.Model;
import com.rcloud.framework.common.http.ResponseMessage;
import com.rcloud.framework.common.service.BaseService;

import io.swagger.annotations.ApiOperation;

/**
 * <Description> 新增通用Controller
 * 
 * @author wanghao
 * @CreateDate 2018年3月20日 下午1:49:01
 * @since V1.0
 * @see com.rcloud.framework.common.controller
 */
public interface CreateController<E, PK extends Serializable, M extends Model>
    extends SupperController<E, Serializable, Model> {

    BaseService<E, PK> getService();

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation(value = "新增")
    default ResponseMessage<E> add(@RequestBody M data) {
        E entity = m2e(data);
        if (entity == null) {
            return ResponseMessage.error(500, "Entity conversion failed.");
        }
        return ok(getService().insert(entity));
    }
}
