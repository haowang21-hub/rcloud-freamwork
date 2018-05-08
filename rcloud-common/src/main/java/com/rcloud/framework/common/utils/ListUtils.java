package com.rcloud.framework.common.utils;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

/**
 * <Description> 集合工具类
 * 
 * @author wanghao
 * @CreateDate 2018年3月16日 下午3:21:42
 * @since V1.0
 * @see com.rcloud.framework.common.utils
 */
public class ListUtils {

    public static boolean isEmpty(final List<?> list) {
        return CollectionUtils.isEmpty(list);
    }

    public static boolean isNotEmpty(final List<?> coll) {
        return !isEmpty(coll);
    }

}
