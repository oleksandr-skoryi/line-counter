package com.alexfaster.model;

import java.util.ArrayList;
import java.util.List;

public class DirectoryNode extends Node {

    private final List<Node> children;

    public DirectoryNode(final String name) {
        super(name);
        children = new ArrayList<>();
    }

    public int getLineCount() {
        return children.stream()
                .mapToInt(Node::getLineCount)
                .sum();
    }

    public void addChild(Node child) {
        children.add(child);
    }

    public List<Node> getChildren() {
        return new ArrayList<>(children);
    }
}
