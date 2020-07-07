package com.alexfaster.service;

import com.alexfaster.service.impl.RootReaderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileNotFoundException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RootReaderServiceTest {

    @Mock
    private FileAnalyzerService analyzerService;

    @InjectMocks
    private RootReaderServiceImpl readerService;

    @Test
    @DisplayName("Test folder node")
    public void testFolderNode() throws FileNotFoundException {
        when(analyzerService.countLines(any()))
                .thenReturn(10);
        var path = getTestResource("files").getAbsolutePath();
        var root = readerService.readFileStructure(path);
        int actualLineCount = root.getLineCount();
        assertThat(root.getChildren())
                .hasSize(8);
        assertThat(actualLineCount)
                .isEqualTo(80);
    }

    @Test
    @DisplayName("Test file node")
    public void testFileNode() throws FileNotFoundException {
        when(analyzerService.countLines(any()))
                .thenReturn(10);
        var path = getTestResource("files/testBlockLineComments.txt").getAbsolutePath();
        var root = readerService.readFileStructure(path);
        int actualLineCount = root.getLineCount();
        assertThat(root.getChildren())
                .isEmpty();
        assertThat(actualLineCount)
                .isEqualTo(10);
    }

    private File getTestResource(final String name) {
        var classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(name)
                .getFile());
    }

}