package com.alexfaster.model;

import java.util.List;

public abstract class Node {

    private final String name;

    public Node(final String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract List<Node> getChildren();

    public abstract int getLineCount();

    @Override
    public String toString() {
        return getName() + " : " + getLineCount();
    }
}
