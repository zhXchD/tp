package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Paths;
import java.util.logging.Level;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class ConfigTest {
    private Config defaultConfig;
    private Config otherConfig;

    @BeforeEach
    private void beforeEach() {
        defaultConfig = new Config();
        otherConfig = new Config();
    }

    @Nested
    @DisplayName("toString method")
    class ToString {
        @Test
        @DisplayName("should return string of default config")
        public void toString_defaultObject_stringReturned() {
            String defaultConfigAsString = "Current log level : INFO\n"
                    + "Preference file Location : preferences.json";

            assertEquals(defaultConfigAsString, defaultConfig.toString());
        }

        @Test
        @DisplayName("should return string of alternate config")
        public void toString_altObject_stringReturned() {
            String otherConfigAsString = "Current log level : FINE\n"
                    + "Preference file Location : different.json";
            otherConfig.setUserPrefsFilePath(Paths.get("different.json"));
            otherConfig.setLogLevel(Level.FINE);

            assertEquals(otherConfigAsString, otherConfig.toString());
        }
    }

    @Nested
    @DisplayName("equals method")
    class Equals {
        @Test
        @DisplayName("should equals itself")
        public void equals_self_true() {
            Config defaultConfig = new Config();
            assertTrue(defaultConfig.equals(defaultConfig));
        }

        @Test
        @DisplayName("should equals config with same log level and userprefs "
                + "filepath")
        public void equals_sameLogLevelAndUserPrefsFilePath_true() {
            otherConfig.setLogLevel(Level.INFO);
            otherConfig.setUserPrefsFilePath(Paths.get("preferences.json"));

            assertTrue(defaultConfig.equals(otherConfig));
        }

        @Test
        @DisplayName("should not equal config with different log level")
        public void equals_differentLogLevel_true() {
            otherConfig.setLogLevel(Level.FINE);

            assertFalse(defaultConfig.equals(otherConfig));
        }

        @Test
        @DisplayName("should not equal config with different userprefs "
                + "filepath")
        public void equals_differentUserPrefsFilePath_true() {
            otherConfig.setUserPrefsFilePath(Paths.get("different.json"));

            assertFalse(defaultConfig.equals(otherConfig));
        }
    }
}
