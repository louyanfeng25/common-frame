package com.baiyan.common.base.utils;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 集合工具类
 *
 * @author baiyan
 * @date 2020/11/13
 */
public class CollectionUtil extends CollectionUtils {

    /**
     * 按数量切割成多个list
     */
    public static <T> List<List<T>> groupListByQuantity(List<T> list, int quantity) {
        if (list == null || list.size() == 0) {
            return new ArrayList<>();
        }

        if (quantity <= 0) {
            new IllegalArgumentException("Wrong quantity.");
        }

        List<List<T>> wrapList = new ArrayList();
        int count = 0;
        while (count < list.size()) {
            wrapList.add(new ArrayList<>(list.subList(count, (count + quantity) > list.size() ? list.size() : count + quantity)));
            count += quantity;
        }

        return wrapList;
    }
    /**
     * 交集
     */
    public static <T> List<T> setRetain(List<T> maskOne, List<T> maskTwo) {
        Set<T> set = new HashSet<>();
        set.addAll(maskOne);
        set.retainAll(maskTwo);
        return new ArrayList<>(set);
    }

    /**
     * 差集
     */
    public static <T> List<T> setRemove(List<T> maskOne, List<T> maskTwo){
        Set<T> set = new HashSet<>();
        set.addAll(maskOne);
        set.removeAll(maskTwo);
        return new ArrayList<>(set);
    }

    /**
     * 补集
     */
    public static <T> List<T> setRepair(List<T> maskOne, List<T> maskTwo){
        List<T> retain = setRetain(maskOne, maskTwo);
        // 减去交集
        List<T> remove = setRemove(maskOne, retain);
        return new ArrayList<>(remove);
    }
}
