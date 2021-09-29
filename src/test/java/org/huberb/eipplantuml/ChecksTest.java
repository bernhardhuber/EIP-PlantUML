/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    public void test_rlable() {
        final File puml = createEIPElementsPumlFile();
        final List<org.unix4j.line.Line> lineList = org.unix4j.Unix4j.grep("r_label", puml).toLineList();
        final String m = "lines: " + lineList;
        assertEquals(0, lineList.size(), m);
    }

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

    @ParameterizedTest
    @MethodSource(value = "pumlFilesForSyntaxCheck")
    public void test_syntaxPumlFiles(File puml) throws IOException {
        assertPumlSyntax(puml);
    }

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

    static Stream<File> pumlFilesForSyntaxCheck() {
        List<File> list = Arrays.asList(createEIPElementsPumlFile(), createEIPPlantUmlPumlFile());
        return list.stream();
    }

    static Stream<File> pumlSpritesForSyntaxCheck() {
        final String spritesDirAsString = "./sprites";
        final List<File> spritesPumlFileList = Unix4j
                .find(spritesDirAsString, "*.puml")
                .toStringStream()
                .map((String filename) -> new File(filename))
                .collect(Collectors.toList());
        return spritesPumlFileList.stream();
    }

    //---
    static File createEIPElementsPumlFile() {
        // TODO use maven: ${project.dir} System.getProperty("project.dir");
        final String projectDirAsString = ".";
        final File projectDirFile = new File(projectDirAsString);
        assertEquals(true, projectDirFile.exists());
        File puml = new File(projectDirFile, "EIP_Elements.puml");
        final String m = "file: " + puml.toString();
        assertEquals(true, puml.exists(), m);
        assertEquals(true, puml.canRead(), m);
        return puml;
    }

    //---
    static File createEIPPlantUmlPumlFile() {
        // TODO use maven: ${project.dir} System.getProperty("project.dir");
        final String projectDirAsString = "./dist";
        final File projectDirFile = new File(projectDirAsString);
        assertEquals(true, projectDirFile.exists());
        File puml = new File(projectDirFile, "EIP-PlantUML.puml");
        final String m = "file: " + puml.toString();
        assertEquals(true, puml.exists(), m);
        assertEquals(true, puml.canRead(), m);
        return puml;
    }
}
