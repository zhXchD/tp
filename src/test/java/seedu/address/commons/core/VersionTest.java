package seedu.address.commons.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class VersionTest {

    @Nested
    @DisplayName("parsing string to version")
    class VersionParse {
        @Test
        @DisplayName("should parse correctly")
        public void versionParsing_acceptableVersionString_parsedVersionCorrectly() {
            verifyVersionParsedCorrectly("V0.0.0ea", 0, 0, 0, true);
            verifyVersionParsedCorrectly("V3.10.2", 3, 10, 2, false);
            verifyVersionParsedCorrectly("V100.100.100ea", 100, 100, 100, true);
        }

        @Test
        @DisplayName("should throw IllegalArgumentException when not a version "
                + "string")
        public void versionParsing_wrongVersionString_throwIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () ->
                    Version.fromString("This is not a version string"));
        }

        private void verifyVersionParsedCorrectly(
                String versionString,
                int major,
                int minor,
                int patch,
                boolean isEarlyAccess
        ) {
            assertEquals(new Version(major, minor, patch, isEarlyAccess),
                    Version.fromString(versionString));
        }
    }

    @Nested
    @DisplayName("Version constructor")
    class Constructor {
        @Test
        @DisplayName("should construct Version with correct parameters")
        public void versionConstructor_correctParameter_valueAsExpected() {
            Version version = new Version(19, 10, 20, true);

            assertEquals(19, version.getMajor());
            assertEquals(10, version.getMinor());
            assertEquals(20, version.getPatch());
            assertTrue(version.isEarlyAccess());
        }
    }

    @Nested
    @DisplayName("toString method")
    class ToString {
        @Test
        @DisplayName("should return valid string representations")
        public void versionToString_validVersion_correctStringRepresentation() {
            // boundary at 0
            Version version = new Version(0, 0, 0, true);
            assertEquals("V0.0.0ea", version.toString());

            // normal values
            version = new Version(4, 10, 5, false);
            assertEquals("V4.10.5", version.toString());

            // big numbers
            version = new Version(100, 100, 100, true);
            assertEquals("V100.100.100ea", version.toString());
        }
    }

    @Nested
    @DisplayName("compareTo method")
    class Compare {
        private Version one;
        private Version another;

        @Test
        @DisplayName("should have earlier patch come first")
        public void versionComparable_differentPatches_compareToIsCorrect() {
            one = new Version(0, 0, 5, false);
            another = new Version(0, 0, 0, false);
            assertTrue(one.compareTo(another) > 0);
            assertTrue(another.compareTo(one) < 0);

            one = new Version(2, 15, 0, false);
            another = new Version(2, 15, 5, false);
            assertTrue(one.compareTo(another) < 0);
            assertTrue(another.compareTo(one) > 0);
        }

        @Test
        @DisplayName("should have earlier minor come first")
        public void compareTo_differentMinors_compareToIsCorrect() {
            one = new Version(0, 0, 0, false);
            another = new Version(0, 5, 0, false);
            assertTrue(one.compareTo(another) < 0);
            assertTrue(another.compareTo(one) > 0);
        }

        @Test
        @DisplayName("should have earlier major come first")
        public void compareTo_differentMajors_compareToIsCorrect() {
            one = new Version(10, 0, 0, true);
            another = new Version(0, 0, 0, true);
            assertTrue(one.compareTo(another) > 0);
            assertTrue(another.compareTo(one) < 0);
        }

        @Test
        @DisplayName("should have earlier major versions come first regardless "
                + "of minor versions")
        public void compareTo_differentMajorAndMinors_compareToIsCorrect() {
            one = new Version(10, 0, 0, true);
            another = new Version(0, 1, 0, true);
            assertTrue(one.compareTo(another) > 0);
            assertTrue(another.compareTo(one) < 0);
        }

        @Test
        @DisplayName("should have earlier minor versions come first regardless "
                + "of patch versions")
        public void compareTo_differentMinorAndPatches_compareToIsCorrect() {
            one = new Version(0, 0, 10, false);
            another = new Version(0, 1, 0, false);
            assertTrue(one.compareTo(another) < 0);
            assertTrue(another.compareTo(one) > 0);
        }

        @Test
        @DisplayName("should have early access versions come first")
        public void compareTo_differentEarlyAccessState_compareToIsCorrect() {
            one = new Version(2, 15, 0, true);
            another = new Version(2, 15, 0, false);
            assertTrue(one.compareTo(another) < 0);
            assertTrue(another.compareTo(one) > 0);

            one = new Version(2, 15, 0, true);
            another = new Version(2, 15, 5, false);
            assertTrue(one.compareTo(another) < 0);

            one = new Version(2, 15, 0, false);
            another = new Version(2, 15, 5, true);
            assertTrue(one.compareTo(another) < 0);
        }

        @Test
        @DisplayName("should be equal if versions are equal")
        public void versionComparable_equalVersions_compareToIsCorrect() {
            one = new Version(0, 0, 0, true);
            another = new Version(0, 0, 0, true);
            assertTrue(one.compareTo(another) == 0);

            one = new Version(11, 12, 13, false);
            another = new Version(11, 12, 13, false);
            assertTrue(one.compareTo(another) == 0);
        }
    }

    @Nested
    @DisplayName("hashCode method")
    class HashCode {
        @Test
        @DisplayName("should generate correct hashcode")
        public void hashCode_validVersion_hashCodeIsCorrect() {
            Version one = new Version(100, 100, 100, true);
            assertEquals(100100100, one.hashCode());

            one = new Version(10, 10, 10, false);
            assertEquals(1010010010, one.hashCode());
        }
    }

    @Nested
    @DisplayName("equals method")
    class Equals {
        @Test
        @DisplayName("should be equal if version is same")
        public void equals_validVersion_equalIsCorrect() {
            Version one = new Version(0, 0, 0, false);
            Version another = new Version(0, 0, 0, false);
            assertTrue(one.equals(another));

            one = new Version(100, 191, 275, true);
            another = new Version(100, 191, 275, true);
            assertTrue(one.equals(another));
        }
    }
}
