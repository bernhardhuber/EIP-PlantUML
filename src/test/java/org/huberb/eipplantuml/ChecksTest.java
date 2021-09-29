/*
 * The MIT License
 *
 * Copyright 2021 berni3.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.huberb.eipplantuml;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import net.sourceforge.plantuml.syntax.SyntaxChecker;
import net.sourceforge.plantuml.syntax.SyntaxResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.unix4j.Unix4j;

/**
 *
 * @author berni3
 */
public class ChecksTest {

    @Test
    public void test_rlabel() {
        final File puml = new EIPPumlFileSupplier().createEIPElementsPumlFile();
        final List<org.unix4j.line.Line> lineList = Unix4j.grep("r_label", puml).toLineList();
        final String m = "lines: " + lineList;
        assertEquals(0, lineList.size(), m);
    }

    /**
     * Test sprites puml, and sprites png files are in sync.
     */
    @Test
    public void testSpritesPumlPngFiles() {
        final String pumlExt = ".puml";
        final String pngExt = ".png";
        final String spritesDirAsString = "./sprites";
        final List<File> spritesPumlFileList = Unix4j
                .find(spritesDirAsString, "*" + pumlExt)
                .toStringStream()
                .sorted()
                .map((String filename) -> new File(filename))
                .collect(Collectors.toList());
        final List<File> spritesPngFileList = Unix4j
                .find(spritesDirAsString, "*" + pngExt)
                .toStringStream()
                .sorted()
                .map((String filename) -> new File(filename))
                .collect(Collectors.toList());
        assertEquals(spritesPumlFileList.size(), spritesPngFileList.size());

        for (int i = 0; i < spritesPumlFileList.size(); i++) {
            //---
            final File spritesPumlFile = spritesPumlFileList.get(i);
            assertTrue(spritesPumlFile.getName().endsWith(pumlExt), spritesPumlFile.toString());
            final String spritesPumlBasename = spritesPumlFile.getName().replace(pumlExt, "");
            //---
            final File spritesPngFile = spritesPngFileList.get(i);
            assertTrue(spritesPngFile.getName().endsWith(pngExt), spritesPngFile.toString());
            final String spritesPngBasename = spritesPngFile.getName().replace(pngExt, "");
            //---
            assertEquals(spritesPumlBasename, spritesPngBasename);
        }
    }

    /**
     * Test syntax of puml files.
     *
     * @param puml
     * @throws IOException
     */
    @ParameterizedTest
    @MethodSource(value = "pumlFilesForSyntaxCheck")
    public void test_syntaxPumlFiles(File puml) throws IOException {
        assertPumlSyntax(puml);
    }

    /**
     * Test syntax of sprite puml files.
     *
     * @param puml
     * @throws IOException
     */
    @ParameterizedTest
    @MethodSource(value = "pumlSpritesForSyntaxCheck")
    public void test_syntax(File puml) throws IOException {
        assertPumlSyntax(puml);
    }

    void assertPumlSyntax(File puml) throws IOException {
        final Path pumlPath = puml.toPath();
        final List<String> allLines = Files.readAllLines(pumlPath, Charset.forName("utf-8"));
        allLines.add(0, "@startuml");
        allLines.add("@enduml");

        final SyntaxResult syntaxResult = SyntaxChecker.checkSyntax(allLines);
        final String m = "" + syntaxResult.getDescription()
                + ", " + syntaxResult.getErrors()
                + ", " + syntaxResult.getLineLocation();
        assertEquals(0, syntaxResult.getErrors().size(), m);
        assertEquals(false, syntaxResult.isError(), m);
    }

    /**
     * Parameterized test method source providing puml files.
     *
     * @return
     */
    static Stream<File> pumlFilesForSyntaxCheck() {
        final List<File> list = Arrays.asList(
                new EIPPumlFileSupplier().createEIPElementsPumlFile(),
                new EIPPumlFileSupplier().createEIPPlantUmlPumlFile());
        return list.stream();
    }

    /**
     * Parameterized test method source providing sprite puml files.
     *
     * @return
     */
    static Stream<File> pumlSpritesForSyntaxCheck() {
        final String spritesDirAsString = "./sprites";
        final List<File> spritesPumlFileList = Unix4j
                .find(spritesDirAsString, "*.puml")
                .toStringStream()
                .map((String filename) -> new File(filename))
                .collect(Collectors.toList());
        return spritesPumlFileList.stream();
    }

    static class EIPPumlFileSupplier {
        //---

        File createEIPElementsPumlFile() {
            // TODO use maven: ${project.dir} System.getProperty("project.dir");
            return createPumlFile(".", "EIP_Elements.puml");
        }

        //---
        File createEIPPlantUmlPumlFile() {
            // TODO use maven: ${project.dir} System.getProperty("project.dir");
            return createPumlFile("./dist", "EIP-PlantUML.puml");
        }

        File createPumlFile(String dir, String puml) {
            final File dirAsFile = new File(dir);
            assertEquals(true, dirAsFile.exists());
            final File pumlAsFile = new File(dirAsFile, puml);
            final String m = "file: " + pumlAsFile.toString();
            assertEquals(true, pumlAsFile.exists(), m);
            assertEquals(true, pumlAsFile.canRead(), m);
            return pumlAsFile;
        }
    }
}
