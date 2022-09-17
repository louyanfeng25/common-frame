package com.baiyan.common.base.utils.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象树
 *
 * @author baiyan
 * @date 2020/11/13
 */
public abstract class AbstractTree<T extends Treeable> implements Treeable<T> {

    private List<T> children = new ArrayList<>();

    @Override
    public T children(List<T> children) {
        this.children = children;
        return (T) this;
    }

    @Override
    public List<T> children() {
        return this.children;
    }

    @Override
    public void add(T child) {
        this.children.add(child);
    }

    public void setChildren(List<T> children) {
        this.children = children;
    }

    public List<T> getChildren() {
        return this.children;
    }
}
