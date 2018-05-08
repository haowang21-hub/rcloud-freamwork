package com.rcloud.framework.common.service;

import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

public interface BaseService<E, PK extends Serializable> {

    BaseMapper<E> getMapper();

    /**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param entity 实体对象
     * @return int
     */
    default E insert(E data) {
        getMapper().insert(data);
        return data;
    }

    /**
     * 根据主键查询
     * 
     * @param id 主键
     * @return 查询结果,无结果时返回{@code null}
     */
    default E selectByPk(PK id) {
        return getMapper().selectById(id);
    }

    /**
     * 根据多个主键查询
     * 
     * @param id 主键集合
     * @return 查询结果,如果无结果返回空集合,而不是返回{@code null}
     */
    default List<E> selectByPk(List<PK> idList) {
        return getMapper().selectBatchIds(idList);
    }

    /**
     * 查询所有结果
     * 
     * @return 所有结果,如果无结果则返回空集合,而不是返回{@code null}
     */
    default List<E> select() {
        return getMapper().selectList(null);
    }

    default List<E> selectList(E data) {
        return getMapper().selectList(new EntityWrapper<E>(data));
    }

    /**
     * 修改记录信息
     *
     * @param data 要修改的对象
     * @return 影响记录数
     */
    int updateByPk(PK id, E data);

    /**
     * 根据主键删除记录
     *
     * @param pk 主键
     * @return 影响记录数
     */
    default int deleteByPk(PK pk) {
        return getMapper().deleteById(pk);
    }

    /**
     * Description: 分页查询
     * 
     * @author wanghao
     * @param page 分页查询参数
     * @return PagerResult<E>
     */
    default List<E> selectPageList(Pagination page, E data) {
        return getMapper().selectPage(page, new EntityWrapper<E>(data));
    }
}
