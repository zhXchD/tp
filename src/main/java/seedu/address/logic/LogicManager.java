package seedu.address.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.IntelliJournalParser;
import seedu.address.logic.parser.ValidCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyJournal;
import seedu.address.model.journal.Entry;
import seedu.address.model.person.Person;
import seedu.address.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE =
            "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final IntelliJournalParser intelliJournalParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and
     * {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        intelliJournalParser = new IntelliJournalParser();
        initCommandAlias();
    }

    /**
     * Constructs a {@code LogicManager} with the given {@code Model},
     * {@code Storage} and {@code UUID}.
     */
    public LogicManager(Model model, Storage storage, UUID uuid) {
        this.model = model;
        this.storage = storage;
        intelliJournalParser = new IntelliJournalParser(uuid);
        initCommandAlias();
    }

    @Override
    public CommandResult execute(String commandText)
            throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = intelliJournalParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveAddressBook(model.getAddressBook());
            storage.saveJournal(model.getJournal());
            storage.saveAliasMap(model.getAliasMap());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    private void initCommandAlias() {
        ValidCommand.update(model.getAliasMap());
    }

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ReadOnlyJournal getJournal() {
        return model.getJournal();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override
    public ObservableList<Entry> getFilteredEntryList() {
        return model.getFilteredEntryList();
    }

    @Override
    public ObservableList<Person> getRecentPersonList() {
        return model.getRecentPersonList();
    }

    @Override
    public ObservableList<Person> getFrequentPersonList() {
        return model.getFrequentPersonList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
