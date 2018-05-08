package com.rcloud.framework.common.controller;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;

import com.rcloud.framework.common.entity.web.Model;
import com.rcloud.framework.common.utils.ClassUtils;

public interface SupperController<E, PK extends Serializable, M extends Model> {

    @SuppressWarnings("unchecked")
    default E m2e(M data) {
        Class<?> clazz = ClassUtils.getGenericType(this.getClass());
        try {
            E entity = (E) clazz.newInstance();
            BeanUtils.copyProperties(data, entity);
            return entity;
        }
        catch (InstantiationException | IllegalAccessException e) {
        }
        return null;
    }

}
