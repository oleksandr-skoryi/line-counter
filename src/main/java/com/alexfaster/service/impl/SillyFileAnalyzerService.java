package com.alexfaster.service.impl;

import com.alexfaster.service.FileAnalyzerService;

import java.io.File;

public class SillyFileAnalyzerService implements FileAnalyzerService {

    public int countLines(File file) {
        return 10;
    }

}
