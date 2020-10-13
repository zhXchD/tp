package seedu.address.model.journal;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;

public class TitleContainsKeywordsPredicate implements Predicate<Entry> {

    private final List<String> keywords;

    public TitleContainsKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Entry entry) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(entry.getTitle().toString(), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TitleContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((TitleContainsKeywordsPredicate) other).keywords)); // state check
    }
}
