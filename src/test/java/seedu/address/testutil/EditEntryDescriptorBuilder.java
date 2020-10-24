package seedu.address.testutil;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditJournalEntryCommand.EditEntryDescriptor;
import seedu.address.model.journal.Date;
import seedu.address.model.journal.Description;
import seedu.address.model.journal.Entry;
import seedu.address.model.journal.Title;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.model.tag.Tag;

/**
 * Utility class to help with building EditEntryDescriptor objects.
 */
public class EditEntryDescriptorBuilder {

    private final EditEntryDescriptor descriptor;

    public EditEntryDescriptorBuilder() {
        descriptor = new EditEntryDescriptor();
    }

    public EditEntryDescriptorBuilder(EditEntryDescriptor descriptor) {
        this.descriptor = new EditEntryDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEntryDescriptor} with fields containing
     * {@code entry}'s details
     */
    public EditEntryDescriptorBuilder(Entry entry) {
        descriptor = new EditEntryDescriptor();
        descriptor.setTitle(entry.getTitle());
        descriptor.setDescription(entry.getDescription());
        descriptor.setDate(entry.getDate());
        descriptor.setContactList(entry.getContactList());
        descriptor.setTags(entry.getTags());
    }

    public EditEntryDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    public EditEntryDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    public EditEntryDescriptorBuilder withDate(String date) {
        descriptor.setDate(new Date(date));
        return this;
    }

    public EditEntryDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags)
                .map(Tag::new)
                .collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    public EditEntryDescriptorBuilder withContacts(Person... contacts) {
        UniquePersonList contactList = new UniquePersonList();
        Arrays.stream(contacts).forEach(contactList::add);
        descriptor.setContactList(contactList.asUnmodifiableObservableList());
        return this;
    }

    public EditEntryDescriptor build() {
        return descriptor;
    }


}
