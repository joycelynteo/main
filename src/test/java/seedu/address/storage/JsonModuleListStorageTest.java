package seedu.address.storage;

import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.ModuleList;

public class JsonModuleListStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonModuleListStorageTest");

    @Test
    public void readModuleList_nullFilePath_throwsNullPointerException() {
        JsonModuleListStorage storage = new JsonModuleListStorage(null);
        assertThrows(NullPointerException.class, storage::readModuleList);
    }

    private java.util.Optional<ModuleList> readModuleList(String filePath) throws Exception {
        return new JsonModuleListStorage(Paths.get(filePath).toString())
                .readModuleList(addToTestDataPathIfNotNull(filePath).toString());
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_throwsDataConversionException() throws Exception {
        assertThrows(DataConversionException.class, () -> readModuleList("NonExistentFile.json"));
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readModuleList("notJsonFormatModuleList.json"));
    }

    @Test
    public void readModuleList_invalidModuleModuleList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readModuleList("invalidModuleModuleList.json"));
    }

    @Test
    public void readModuleList_invalidAndValidModuleModuleList_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readModuleList("invalidAndValidModuleModuleList.json"));
    }
}
