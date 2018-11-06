//@@author XiaoYunhan
package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.logic.DeadlineNotification;
import seedu.address.model.task.Task;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Task task;

    private final DeadlineNotification deadlineNotification;

    //@FXML
    //private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label taskname;
    @FXML
    private Label taskmodule;
    @FXML
    private Label taskdate;
    @FXML
    private Label taskpriority;
    @FXML
    private Label taskstate;

    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        deadlineNotification = new DeadlineNotification();
        id.setText(displayedIndex + ". ");
        taskname.setText(task.getName().fullName);
        taskmodule.setText(task.getModule().value);
        taskdate.setText(task.getDate().value);

        if (task.getPriority().value.equals("1")) {
            taskpriority.setText("High priority (1)");
        } else if (task.getPriority().value.equals("2")) {
            taskpriority.setText("Medium priority (2)");
        } else {
            taskpriority.setText("Low priority (3)");
        }

        if (task.getComplete()) {
            taskstate.setText("Status: Completed");
        } else {
            taskstate.setText("Status: Uncompleted");
        }

        /**
         * Modify color based on intervals between the deadline of the task and current time
         * Testing feature
         */
        if (deadlineNotification.notifyOrNot(task.getDayInTypeDate())) {
            taskname.setStyle("-fx-text-fill: #EEDD82");
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
