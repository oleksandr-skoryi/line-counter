package com.alexfaster.service.impl;

import com.alexfaster.service.FileAnalyzerService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class SmartFileAnalyzerService implements FileAnalyzerService {

    @Override
    public int countLines(final File file) {
        var result = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            var inComment = false;
            String line;
            while ((line = br.readLine()) != null) {
                line = normalizeLine(line);
                if (!isLineComment(line) && !line.isEmpty()) {
                    var analyzeResult = analyzeLine(line, inComment);
                    inComment = analyzeResult.inComment;
                    if (analyzeResult.count > 0) {
                        result++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String normalizeLine(final String line) {
        return line.replaceAll("\\t+", "")
                .replaceAll("\\b+", "")
                .replaceAll("\\s+", "");
    }

    private boolean isLineComment(final String line) {
        return line.startsWith("//");
    }

    private AnalyzeResult analyzeLine(final String normalizedLine, boolean inComment) {
        var inString = false;
        var count = 0;
        for (int i = 0; i < normalizedLine.length(); i++) {
            var chunk = normalizedLine.substring(
                    i,
                    Math.min(
                            i + 2,
                            normalizedLine.length()
                    )
            );
            if (String.valueOf(normalizedLine.charAt(i)).equals("\"") && !inComment) {
                inString = !inString;
            } else if (chunk.equals("/*") && !inString) {
                inComment = true;
                i++;
            } else if (chunk.equals("*/") && !inString) {
                inComment = false;
                i++;
            } else if (!inComment && !inString && chunk.equals("//")) {
                break;
            } else if (!inComment) {
                count++;
            }
        }
        return new AnalyzeResult(count, inComment);
    }

    static class AnalyzeResult {

        public final int count;
        public final boolean inComment;

        public AnalyzeResult(final int count, final boolean inComment) {
            this.count = count;
            this.inComment = inComment;
        }
    }
}
