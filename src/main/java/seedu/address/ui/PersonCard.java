package seedu.address.ui;

import java.util.Comparator;

import javafx.animation.ScaleTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import seedu.address.model.person.Meeting;
import seedu.address.model.person.Person;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label relationship;
    @FXML
    private Label policy;
    @FXML
    private FlowPane tags;



    @FXML
    private Label meetingDate;
    @FXML
    private Label meetingTime;
    @FXML
    private Label meetingAgenda;
    @FXML
    private Label meetingNotes;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        address.setText(person.getAddress().value);
        email.setText(person.getEmail().value);
        relationship.setText(person.getRelationship().value);


        if (!person.getMeetings().isEmpty()) {
            // Get the first meeting as an example
            Meeting firstMeeting = person.getMeetings().get(0);
            meetingDate.setText(firstMeeting.getMeetingDate().toString());
            meetingTime.setText(firstMeeting.getMeetingTime().toString());
            meetingAgenda.setText(firstMeeting.getAgenda());
            meetingNotes.setText(firstMeeting.getNotes());
        } else {
            // If there are no meetings, you could hide the labels or set them to a default text
            meetingDate.setText("");
            meetingTime.setText("");
            meetingAgenda.setText("");
            meetingNotes.setText("");
        }

        if (person.isClient()) {
            if (person.getPolicy().value.equals("")) {
                policy.setText("No policy assigned");
                policy.setStyle("-fx-background-color: #f54242");
            } else {
                policy.setText("Policy: " + person.getPolicy().value);
                policy.setStyle("-fx-background-color: #1fab2f");
            }
        } else {
            policy.setManaged(false);
        }
        person.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        applyHoverEffect(cardPane);

    }




    private void applyHoverEffect(Node node) {
        DropShadow hoverShadow = new DropShadow();
        hoverShadow.setColor(Color.CHARTREUSE);
        hoverShadow.setRadius(30);
        hoverShadow.setSpread(0.5);

        node.addEventHandler(MouseEvent.MOUSE_ENTERED, e -> {
            node.setEffect(hoverShadow);
        });

        node.addEventHandler(MouseEvent.MOUSE_EXITED, e -> {
            node.setEffect(null);
        });
    }



}
