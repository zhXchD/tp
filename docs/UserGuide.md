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

   * **`add`**`n/John Doe p/98765432 e/johnd@example.com a/John street, block
     123, #01-01` : Adds a contact named `John Doe` to the Address Book.

   * **`remove`**`3` : Removes the 3rd contact shown in the current list.

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

Gives the explanation and format of commands in the system.

Format: `help [COMMAND]`
* The `COMMAND` argument supplied will indicate which command explanation to
show.
* If no `COMMAND` argument is supplied, all commands will be shown.

### Adding a contact: `addc`

Adds a contact to the address book or journal entry to the journal.

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

Adds a journal entry to the journal.

format: `addj n/NAME [at/DATE_AND_TIME] [d/DESCRIPTION] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A journal entry can have any number of tags (including 0)
</div>

Examples:
* `addj n/Meeting with client` Adds a journal entry with the name
`Meeting with client`.
* `addj n/Meeting with client at/2020-9-20 1400 d/Tea` Adds a journal
entry with the name `Meeting with client`, a date and time of `2020-9-20 1400`
and content `Tea`.

### Listing all contacts: `listc`

Lists all the contacts in the address book.

Format: `listc`

### Listing all journal entries: `listj`

Lists all journal entries in the journal.

Format: `listj`

### Viewing a contact: `viewc`
Opens up a contact entry to show further details.

Format `viewc INDEX`
* `INDEX` refers to the index number of the contact shown in the list.

Examples:
* `viewc 4` Views the 4th contact in the address book.
* `viewc 8` Views the 8th contact in the address book.

### Viewing a journal entry: `viewc`
Opens up a journal entry to show further details.

Format `viewj INDEX`
* `INDEX` refers to the index number of the journal shown in the list.

Examples:
* `viewj 4` Views the 4th journal entry in the journal.
* `viewj 8` Views the 8th journal entry in the journal.

### Removing a contact: `removec`

Removes a contact from the address book.

Format `removec INDEX`
* `INDEX` refers to the index number of the contact in the list.

Examples:
* `removec 4` Removes the 4th contact in the address book.
* `removec 8` Removes the 8th contact in the address book.

### Removing a journal entry: `removej`

Removes a journal entry from the journal.

Format `removej INDEX`
* `INDEX` refers to the index number of the journal shown in the list.

Examples:
* `removej 4` Removes the 4th journal entry in the journal.
* `removej 8` Removes the 8th journal entry in the journal.

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

| Command                         | Format                                                                                 |
| :---                            | :---                                                                                   |
| **Viewing help menu**           | `help [COMMAND]`                                                                       |
| **Adding a contact**            | `addc n/NAME [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [at/DATE_AND_TIME] [t/TAG]…​` |
| **Adding a journal entry**      | `addj n/NAME [at/DATE_AND_TIME] [d/DESCRIPTION] [t/TAG]…​`                        |
| **Listing all contacts**        | `listc`                                                                                |
| **Listing all journal entries** | `listj`                                                                                |
| **Viewing a contact**           | `viewc INDEX`                                                                          |
| **Viewing a journal entry**     | `viewj INDEX`                                                                          |
| **Removing a contact**          | `removec INDEX`                                                                        |
| **Removing a journal entry**    | `removej INDEX`                                                                        |
| **Exiting the program**         | `exit`                                                                                 |
