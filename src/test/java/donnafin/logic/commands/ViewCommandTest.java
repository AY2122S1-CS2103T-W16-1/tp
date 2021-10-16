package donnafin.logic.commands;

import donnafin.commons.core.index.Index;
import donnafin.logic.PersonAdapter;
import donnafin.model.AddressBook;
import donnafin.model.Model;
import donnafin.model.ModelManager;
import donnafin.model.UserPrefs;
import donnafin.model.person.Person;
import donnafin.testutil.PersonBuilder;
import donnafin.ui.Ui;
import org.junit.jupiter.api.Test;

import static donnafin.logic.commands.CommandTestUtil.assertCommandAction;
import static donnafin.logic.commands.CommandTestUtil.assertCommandFailure;
import static donnafin.logic.commands.CommandTestUtil.assertCommandSuccess;
import static donnafin.logic.commands.HomeCommand.MESSAGE_SUCCESS;

public class ViewCommandTest {

    @Test
    public void execute_view_success_and_no_side_effect() {
        final Model model = new ModelManager(new AddressBook(), new UserPrefs(), null);
        final Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), null);
        Person person = new PersonBuilder().build();
        model.addPerson(new PersonBuilder().build());
        expectedModel.addPerson(new PersonBuilder().build());
        String expectedResult = String.format(ViewCommand.MESSAGE_VIEW_PERSON_SUCCESS, person.getName());

        CommandResult expectedCommandResult = new CommandResult(expectedResult);
        assertCommandSuccess(new ViewCommand(Index.fromOneBased(1)), model, expectedCommandResult, expectedModel);
        assertCommandAction(new ViewCommand(Index.fromOneBased(1)), CommandTestUtil.UiActionType.SHOW_VIEW);
    }

    @Test
    public void execute_view_failure() {
        final Model model = new ModelManager(new AddressBook(), new UserPrefs(), null);
        assertCommandFailure(new ViewCommand(Index.fromOneBased(1)), model, ViewCommand.VIEW_COMMAND_ERROR);
    }
}
