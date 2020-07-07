package com.alexfaster.service.impl;

import com.alexfaster.model.DirectoryNode;
import com.alexfaster.model.FileNode;
import com.alexfaster.model.Node;
import com.alexfaster.service.FileAnalyzerService;
import com.alexfaster.service.RootReaderService;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

public class RootReaderServiceImpl implements RootReaderService {

    private final FileAnalyzerService fileAnalyzerService;

    public RootReaderServiceImpl(final FileAnalyzerService fileAnalyzerService) {
        this.fileAnalyzerService = fileAnalyzerService;
    }

    public Node readFileStructure(final String path) throws FileNotFoundException {
        var file = new File(path);
        if (!file.exists()) {
            throw new FileNotFoundException("Path " + path + " doesn't exist");
        }
        if (file.isDirectory()) {
            return handleDirectory(file);
        } else {
            return handleFile(file);
        }
    }

    private Node handleFile(final File file) {
        var lines = fileAnalyzerService.countLines(file);
        return new FileNode(file.getName(), lines);
    }

    private Node handleDirectory(final File file) {
        var files = file.listFiles();
        var directoryNode = new DirectoryNode(file.getName());
        for (int i = 0; files != null && i < files.length; i++) {
            var currentNode = files[i];
            if (currentNode.isDirectory()) {
                directoryNode.addChild(handleDirectory(currentNode));
            } else {
                directoryNode.addChild(handleFile(currentNode));
            }
        }
        return directoryNode;
    }

    public void showTree(final Node root) {
        printNode(root, 0);
        showTreeHelper(root, 4);
    }

    private void showTreeHelper(final Node root, final int shift) {
        List<Node> children = root.getChildren();
        if (!children.isEmpty()) {
            for (Node node : children) {
                printNode(node, shift);
                if (!node.getChildren().isEmpty()) {
                    showTreeHelper(node, shift + 4);
                }
            }
        }
    }

    private void printNode(final Node node, final int shift) {
        var sb = new StringBuilder();
        for (int i = 0; i < shift; i++) {
            sb.append(" ");
        }
        sb.append(node);
        System.out.println(sb.toString());
    }
}
