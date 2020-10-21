---
layout: page
title: User Guide
---

IntelliJournal is a **desktop app for managing journals as well as contacts,
optimised for use via a Command Line Interface** (CLI) while still having the
benefits of a Graphical User Interface (GUI). If you can type fast,
IntelliJournal can help you record down journal entries with contacts as well
as location information faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `intellijournal.jar` from
   [here](https://github.com/AY2021S1-CS2103T-W17-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your
   IntelliJournal.

1. Double-click the file to start the app. The GUI similar to the below should
   appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it.
   e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists all contacts.

   * **`addc`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block
     123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`deletec`**`3` : deletes the 3rd contact shown in the current list.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as
  `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero
  times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`,
  `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME`
       is also acceptable.

</div>

### Viewing help menu: `help`

Gives the explanation and format of commands in the system and does not change
the current displaying tab.

Format: `help [COMMAND]`
* The `COMMAND` argument supplied will indicate which command explanation to
show.
* If no `COMMAND` argument is supplied, all commands will be shown.

### Adding a contact: `addc`

Adds a contact to the address book and switches to the address book tab.

format: `addc n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [at/DATE_AND_TIME]
         [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have any number of tags (including 0)
</div>

Examples:
* `addc n/John Doe` Adds a contact with the name of `Robert`.
* `addc n/Betsy Crowe t/client t/important` Adds a contact with the name of
`Robert` and tags of `client` and `important`.

### Adding a journal entry: `addj`

Adds a journal entry to the journal and switches to the journal tab.

format: `addj n/NAME [at/DATE_AND_TIME] [d/DESCRIPTION] [with/CONTACT_NAMES] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A journal entry can have any number of contacts or tags (including 0)

`CONTACT_NAME` must be an existing name in the address book.
</div>

Examples:
* `addj n/Meeting with client` Adds a journal entry with the name
`Meeting with client`.
* `addj n/Meeting with client at/2020-9-20 14:00 d/Tea` Adds a journal
entry with the name `Meeting with client`, a date and time of `2020-9-20 14:00`
and content `Tea`.

### Listing all contacts: `listc`

Lists all the contacts in the address book and switches to the address book tab.

Format: `listc`

### Listing all journal entries: `listj`

Lists all journal entries in the journal and switches to the journal tab.

Format: `listj`

### Viewing a contact or journal entry: `view`
Opens up a contact or journal entry to show further details and switches to the corresponding tab.

Format `view in/SCOPE index/INDEX`
* `INDEX` refers to the index number of the contact shown in the list.
* `SCOPE` must be `c` (refers to contact) or `j` (refers to journal entry).

Examples:
* `view in/c index/4` Views the 4th contact in the address book.
* `view in/c index/8` Views the 8th contact in the address book.
* `view in/j index/4` Views the 4th journal entry in the journal.
* `view in/j index/8` Views the 8th journal entry in the journal.

### Find contacts or journal entries with keywords on fileds: `find`
Finds a list of contacts or journal entries that satisfy the requirements
on particular fields given by the user.

Format `view in/SCOPE [different valid combinations dependent on SCOPE]`
* `view in/c [n/NAME_KEYWORDS] [p/PHONE_KEYWORDS] [e/EMAIL_KEYWORDS]
[a/ADDRESS_KEYWORDS] [t/TAG]`
* `view in/j [n/TITLE_KEYWORDS] [at/DATE_AND_TIME] [with/CONTACT_NAME_KEYWORDS]
[d/DESCRIPTION_KEYWORDS] [t/TAG]`

Examples:
* `view in/c n/Alice` Finds all contacts whose name contains "Alice".
* `view in/n n/Alice p/65` Find all contacts whose name contains "Alice" and
phone number contains "65".
* `view in/c n/Alice p/65 e/@u.nus.edu a/RC4 t/Student` Find all contacts whose name
contains "Alice" and phone number contains "65" and email contains "@u.nus.edu" and
address name contains "RC4" and tagged "Student" (Note that for `TAG`, IntelliJournal
searches for the exact same tag instead of find the ones that contain the searched string).
* `view in/j n/Meeting d/plan for 2021 with/Alice t/Meeting at/2020-10-10` Finds all
journal entries that have a name containing "Meeting", have a description containing
"plan for 2021", with someone whose name contains "Alice", tagged "Meeting", happening
on Oct 10, 2020.

### Deleting a contact: `deletec`

Deletes a contact from the address book and switches to the address book tab.

Format `deletec INDEX`
* `INDEX` refers to the index number of the contact in the list.

Examples:
* `deletec 4` Deletes the 4th contact in the address book.
* `deletec 8` Deletes the 8th contact in the address book.

### Deleting a journal entry: `deletej`

Deletes a journal entry from the journal and switches to the journal tab.

Format `deletej INDEX`
* `INDEX` refers to the index number of the journal shown in the list.

Examples:
* `deletej 4` Deletes the 4th journal entry in the journal.
* `deletej 8` Deletes the 8th journal entry in the journal.

### Deleting the address book: `clearc`

Clears all contacts in the addressbook and switches to the address book tab.

Format: `clearc`

### Deleting the journal: `clearj`

Clears all journal entries in the journal and switches to the journal tab.

Format: `clearj`

### Switch the displaying tab: `switch`

Switches the current displaying tab to the other tab.

Format: `switch`

### Exiting the program: `exit`

Exits the program.

Format: `exit`

### Saving the data

IntelliJournal data is saved in the hard disk automatically after any command
that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file
       it creates with the file that contains the data of your previous
       IntelliJournal home folder.

--------------------------------------------------------------------------------

## Command Summary

<table>
    <thead>
        <tr>
            <th>Command</th>
            <th>Format</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><b>Viewing help menu</b></td>
            <td><code>help [COMMAND]</code></td>
        </tr>
        <tr>
            <td rowspan=2><b>Adding a contact</b></td>
            <td>
                <code>
                    addcontact n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] 
                    [at/DATE_AND_TIME] [t/TAG]…
                </code>
            </td>
        </tr>
        <tr>
            <td>
                <code>
                    addc n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] 
                    [at/DATE_AND_TIME] [t/TAG]…
                </code>
            </td>
        </tr>
        <tr>
            <td rowspan=3><b>Adding a journal entry</b></td>
            <td>
                <code>
                    addjournal n/NAME [at/DATE_AND_TIME] [d/DESCRIPTION]
                    [with/CONTACT_NAME] [t/TAG]…
                </code>
            </td>
        </tr>
        <tr>
            <td>
                <code>
                    addj n/NAME [at/DATE_AND_TIME] [d/DESCRIPTION]
                    [with/CONTACT_NAME] [t/TAG]…
                </code>
            </td>
        </tr>
        <tr>
            <td>
                <code>
                    adde n/NAME [at/DATE_AND_TIME] [d/DESCRIPTION]
                    [with/CONTACT_NAME] [t/TAG]…​
                </code>
            </td>
        </tr>
        <tr>
            <td rowspan="2"><b>Listing all contacts</b></td>
            <td><code>listc</code></td>
        </tr>
        <tr>
            <td><code>lc</code></td>
        </tr>
        <tr>
            <td rowspan="2"><b>Listing all journal entries</b></td>
            <td><code>listj</code></td>
        </tr>
        <tr>
            <td><code>lj</code></td>
        </tr>
        <tr>
            <td rowspan="2"><b>Viewing a contact or journal entry</b></td>
            <td><code>view in/SCOPE INDEX</code></td>
        </tr>
        <tr>
            <td><code>v in/SCOPE INDEX</code></td>
        </tr>
        <tr>
            <td rowspan="2"><b>Removing a contact</b></td>
            <td><code>deletec INDEX</code></td>
        </tr>
        <tr>
            <td><code>delc INDEX</code></td>
        </tr>
        <tr>
            <td rowspan="2"><b>Removing a journal entry</b></td>
            <td><code>deletej INDEX</code></td>
        </tr>
        <tr>
            <td><code>delj INDEX</code></td>
        </tr>
        <tr>
            <td rowspan="2"><b>Clearing the address book</b></td>
            <td><code>clearc</code></td>
        </tr>
        <tr>
            <td><code>cc</code></td>
        </tr>
        <tr>
            <td rowspan="2"><b>Clearing the journal</b></td>
            <td><code>clearj</code></td>
        </tr>
        <tr>
            <td><code>cj</code></td>
        </tr>
        <tr>
            <td rowspan="2"><b>Switching the display tab</b></td>
            <td><code>switch</code></td>
        </tr>
        <tr>
            <td><code>swt</code></td>
        </tr>
        <tr>
            <td rowspan="3"><b>Exiting the program</b></td>
            <td><code>exit</code></td>
        </tr>
        <tr>
            <td><code>quit</code></td>
        </tr>
        <tr>
            <td><code>q</code></td>
        </tr>
    </tbody>
</table>
