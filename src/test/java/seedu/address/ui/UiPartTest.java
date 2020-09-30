package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.Assert.assertThrows;

import java.net.URL;
import java.nio.file.Path;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javafx.fxml.FXML;
import seedu.address.MainApp;

public class UiPartTest {

    private static final String MISSING_FILE_PATH =
            "UiPartTest/missingFile.fxml";
    private static final String INVALID_FILE_PATH =
            "UiPartTest/invalidFile.fxml";
    private static final String VALID_FILE_PATH =
            "UiPartTest/validFile.fxml";
    private static final String VALID_FILE_WITH_FX_ROOT_PATH =
            "UiPartTest/validFileWithFxRoot.fxml";
    private static final TestFxmlObject VALID_FILE_ROOT =
            new TestFxmlObject("Hello World!");

    private URL getTestFileUrl(String testFilePath) {
        String testFilePathInView = "/view/" + testFilePath;
        URL testFileUrl = MainApp.class.getResource(testFilePathInView);
        assertNotNull(testFileUrl, testFilePathInView + " does not exist.");
        return testFileUrl;
    }

    @Nested
    @DisplayName("constructor")
    class Constructor {
        @TempDir
        public Path testFolder;

        @Test
        @DisplayName("should throw NullPointerException if file url is null")
        public void constructor_nullFileUrl_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    new TestUiPart<Object>((URL) null));
            assertThrows(NullPointerException.class, () ->
                    new TestUiPart<Object>((URL) null, new Object()));
        }

        @Test
        @DisplayName("should throw AssertionError if file url is missing")
        public void constructor_missingFileUrl_throwsAssertionError()
                throws Exception {
            URL missingFileUrl =
                    new URL(testFolder.toUri().toURL(), MISSING_FILE_PATH);
            assertThrows(AssertionError.class, () ->
                    new TestUiPart<Object>(missingFileUrl));
            assertThrows(AssertionError.class, () ->
                    new TestUiPart<Object>(missingFileUrl, new Object()));
        }

        @Test
        @DisplayName("should throw AssertionError if file url is invalid")
        public void constructor_invalidFileUrl_throwsAssertionError() {
            URL invalidFileUrl = getTestFileUrl(INVALID_FILE_PATH);
            assertThrows(AssertionError.class, () ->
                    new TestUiPart<Object>(invalidFileUrl));
            assertThrows(AssertionError.class, () ->
                    new TestUiPart<Object>(invalidFileUrl, new Object()));
        }

        @Test
        @DisplayName("should load file if file url is valid")
        public void constructor_validFileUrl_loadsFile() {
            URL validFileUrl = getTestFileUrl(VALID_FILE_PATH);
            assertEquals(
                    VALID_FILE_ROOT,
                    new TestUiPart<TestFxmlObject>(validFileUrl).getRoot()
            );
        }

        @Test
        @DisplayName("should load file if file with fx root url is valid")
        public void constructor_validFileWithFxRootUrl_loadsFile() {
            URL validFileUrl = getTestFileUrl(VALID_FILE_WITH_FX_ROOT_PATH);
            TestFxmlObject root = new TestFxmlObject();
            assertEquals(
                    VALID_FILE_ROOT,
                    new TestUiPart<TestFxmlObject>(validFileUrl, root).getRoot()
            );
        }

        @Test
        @DisplayName("should throw NullPointerException if file name is null")
        public void constructor_nullFileName_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    new TestUiPart<Object>((String) null));
            assertThrows(NullPointerException.class, () ->
                    new TestUiPart<Object>((String) null, new Object()));
        }

        @Test
        @DisplayName("should throw NullPointerException if file name is "
                + "missing")
        public void constructor_missingFileName_throwsNullPointerException() {
            assertThrows(NullPointerException.class, () ->
                    new TestUiPart<Object>(MISSING_FILE_PATH));
            assertThrows(NullPointerException.class, () ->
                    new TestUiPart<Object>(
                            MISSING_FILE_PATH,
                            new Object()
                    )
            );
        }

        @Test
        @DisplayName("should throw AssertionError if file name is invalid")
        public void constructor_invalidFileName_throwsAssertionError() {
            assertThrows(AssertionError.class, () ->
                    new TestUiPart<Object>(INVALID_FILE_PATH));
            assertThrows(AssertionError.class, () ->
                    new TestUiPart<Object>(
                            INVALID_FILE_PATH,
                            new Object()
                    )
            );
        }
    }

    /**
     * UiPart used for testing.
     * It should only be used with invalid FXML files or the valid file located at {@link VALID_FILE_PATH}.
     */
    private static class TestUiPart<T> extends UiPart<T> {

        @FXML
        private TestFxmlObject validFileRoot; // Check that @FXML annotations work

        TestUiPart(URL fxmlFileUrl, T root) {
            super(fxmlFileUrl, root);
        }

        TestUiPart(String fxmlFileName, T root) {
            super(fxmlFileName, root);
        }

        TestUiPart(URL fxmlFileUrl) {
            super(fxmlFileUrl);
            assertEquals(VALID_FILE_ROOT, validFileRoot);
        }

        TestUiPart(String fxmlFileName) {
            super(fxmlFileName);
            assertEquals(VALID_FILE_ROOT, validFileRoot);
        }
    }
}
