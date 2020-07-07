package com.alexfaster;

import com.alexfaster.service.impl.RootReaderServiceImpl;
import com.alexfaster.service.impl.SmartFileAnalyzerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileNotFoundException;

public class Application {

    private static final Logger logger = LogManager.getLogger(Application.class);

    public static void main(String[] args) {
        if (args.length != 1) {
            logger.error("Unique path to root resource should be specified");
            return;
        }
        var path = args[0];
        var fileAnalyzerService = new SmartFileAnalyzerService();
        var rootReaderService = new RootReaderServiceImpl(fileAnalyzerService);
        try {
            var root = rootReaderService.readFileStructure(path);
            rootReaderService.showTree(root);
        } catch (FileNotFoundException e) {
            logger.error("Resource for path {} not found", path);
        }

    }

}
