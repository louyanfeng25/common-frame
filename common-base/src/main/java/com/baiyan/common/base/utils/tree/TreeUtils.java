package com.baiyan.common.base.utils.tree;

import java.util.*;

/**
 * 树工具类
 *
 * @author baiyan
 * @date 2020/11/13
 */
public class TreeUtils {

    /**
     * 生成一颗树
     * 利用treeFactory将source转成树节点
     * 树节点继承Treeable
     *
     * @param source
     * @param root 根节点
     * @param treeFactory 将source转成树节点，生成默认节点
     * @param <I>
     * @param <O>
     * @return
     */
    public static <I, O extends Treeable> List<O> buildTree(List<I> source, I root, TreeFactory<I, O> treeFactory) {
        Map<Object, O> mapping = new LinkedHashMap<>();
        O treeRoot;
        // 如果没有root就生成一个默认节点
        if (Objects.isNull(root)) {
            treeRoot = treeFactory.buildDefaultRoot();
        } else {
            treeRoot = treeFactory.convert(root);
        }
        mapping.put(treeRoot.key(), treeRoot);

        source.stream()
                .forEach(i -> {
                    O o = treeFactory.convert(i);
                    mapping.putIfAbsent(o.key(), o);
                });

        mapping.forEach((key, value) -> Optional.ofNullable(value)
                .filter(t -> !Objects.equals(treeRoot, value))
                .map(t -> mapping.get(t.parentKey()))
                .ifPresent(parent -> parent.add(value)));
        return (List<O>) treeRoot.children();
    }

}
