package com.alexfaster.service;

import com.alexfaster.model.Node;

import java.io.FileNotFoundException;

public interface RootReaderService {

    Node readFileStructure(final String path) throws FileNotFoundException;

}
