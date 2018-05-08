/*
 * Copyright 2016 http://www.hswebframework.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.rcloud.framework.common.controller;

import static com.rcloud.framework.common.http.ResponseMessage.ok;

import java.io.Serializable;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.rcloud.framework.common.entity.PagerResult;
import com.rcloud.framework.common.entity.web.Model;
import com.rcloud.framework.common.http.ResponseMessage;
import com.rcloud.framework.common.service.BaseService;

import io.swagger.annotations.ApiOperation;

/**
 * 通用查询控制器。
 *
 * @param <E> 实体类型
 * @param <PK> 主键类型
 * @param <M> 查询条件实体类型
 * @author wanghao
 * @see 1.0
 */
public interface QueryController<E, PK extends Serializable, M extends Model>
    extends SupperController<E, Serializable, Model> {

    BaseService<E, PK> getService();

    @GetMapping
    @ApiOperation(value = "根据字段条件分页查询", responseReference = "get")
    default ResponseMessage<PagerResult<E>> list(Pagination page, M data) {
        E entity = m2e(data);
        List<E> resultList = getService().selectPageList(page, entity);
        PagerResult<E> result = new PagerResult<>();
        result.setData(resultList);
        result.setTotal(page.getTotal());
        return ok(result);
    }

    @GetMapping("/list")
    @ApiOperation(value = "不分页动态查询", responseReference = "get")
    default ResponseMessage<List<E>> listNoPaging(M data) {
        E entity = m2e(data);
        return ok(getService().selectList(entity));
    }

    @GetMapping(path = "/{id:.+}")
    @ApiOperation("根据主键查询")
    default ResponseEntity<ResponseMessage<E>> getByPrimaryKey(@PathVariable PK id) {
        E entity = getService().selectByPk(id);
        if (entity != null) {
            return ResponseEntity.ok(ok(entity));
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping(path = "/ids")
    @ApiOperation("根据主键查询多条记录")
    default ResponseMessage<List<E>> getByPrimaryKey(@RequestParam List<PK> ids) {
        return ok(getService().selectByPk(ids));
    }
}
