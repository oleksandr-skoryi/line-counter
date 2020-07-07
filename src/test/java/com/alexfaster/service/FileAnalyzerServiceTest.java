package com.alexfaster.service;


import com.alexfaster.service.impl.SmartFileAnalyzerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class FileAnalyzerServiceTest {

    private FileAnalyzerService analyzer;

    @BeforeEach
    public void init() {
        analyzer = new SmartFileAnalyzerService();
    }

    @Test
    @DisplayName("Test without comments")
    public void testWithoutComments() {
        var file = getTestResource("testWithoutComments.txt");
        var actual = analyzer.countLines(file);
        assertThat(actual).isEqualTo(8);
    }

    @Test
    @DisplayName("Test 'Line Comment' on the start of the line")
    public void testLineCommentOnStart() {
        var file = getTestResource("testLineCommentsOnStart.txt");
        var actual = analyzer.countLines(file);
        assertThat(actual).isEqualTo(8);
    }

    @Test
    @DisplayName("Test 'Line Comment' in the middle of the line")
    public void testLineCommentInTheMiddle() {
        var file = getTestResource("testLineCommentsInTheMiddle.txt");
        var actual = analyzer.countLines(file);
        assertThat(actual).isEqualTo(8);
    }

    @Test
    @DisplayName("Test commented out method")
    public void testCommentedOutMethod() {
        var file = getTestResource("testCommentedOutMethod.txt");
        var actual = analyzer.countLines(file);
        assertThat(actual).isEqualTo(5);
    }

    @Test
    @DisplayName("Test block line comments")
    public void testBlockLineComments() {
        var file = getTestResource("testBlockLineComments.txt");
        var actual = analyzer.countLines(file);
        assertThat(actual).isEqualTo(8);
    }

    @Test
    @DisplayName("Test line comment inside block comment")
    public void testLineCommentInsideBlockComment() {
        var file = getTestResource("testLineCommentInsideBlockComment.txt");
        var actual = analyzer.countLines(file);
        assertThat(actual).isEqualTo(7);
    }

    @Test
    @DisplayName("Test string with comments")
    public void testStringWithComments() {
        var file = getTestResource("testStringWithComments.txt");
        var actual = analyzer.countLines(file);
        assertThat(actual).isEqualTo(9);
    }

    @Test
    @DisplayName("Test hell mix")
    public void testHellMix() {
        var file = getTestResource("testHellMix.txt");
        var actual = analyzer.countLines(file);
        assertThat(actual).isEqualTo(8);
    }

    private File getTestResource(final String name) {
        var classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource("files/" + name)
                .getFile());
    }

}