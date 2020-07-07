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
        return new FileNode(file.getAbsolutePath(), lines);
    }

    private Node handleDirectory(final File file) {
        var files = file.listFiles();
        var directoryNode = new DirectoryNode(file.getAbsolutePath());
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

    public void showTree(Node root) {
        List<Node> children = root.getChildren();
        System.out.println(root);
        if (!children.isEmpty()) {
            for (Node node : children) {
                System.out.println(node);
                if (!node.getChildren().isEmpty()) {
                    showTree(node);
                }
            }
        }
    }
}
