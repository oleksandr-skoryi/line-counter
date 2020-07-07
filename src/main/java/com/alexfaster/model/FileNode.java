package com.alexfaster.model;

import java.util.ArrayList;
import java.util.List;

public class FileNode extends Node {

    private final int lineCount;

    public FileNode(final String name, final int lineCount) {
        super(name);
        this.lineCount = lineCount;
    }

    public int getLineCount() {
        return lineCount;
    }

    public List<Node> getChildren() {
        return new ArrayList<>();
    }
}
