package controllers;

import java.io.IOException;

import boundary.ClientUI;
import client.Client;
import common.Message;
import graphics.GraphicsImporter;
import i_book.Employee;
import i_book.GeneralUser;
import i_book.User;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;

/**
 * @author Ofir
 * System Controller: A father to most of the page-controllers in the system.
 * Basically, this is a simple frame for most of the pages in the system.
 * It includes a basic functionalities that are commonly used by the different system pages (eg. menu, logo bar, logout and go back button).
 */

public abstract class SystemController extends AbstractController {
	@FXML
	private AnchorPane toggleMenuAnchor;
	@FXML
	protected AnchorPane menuAnchor;
	@FXML
	private Label arrowLabel;
	@FXML
	private Label userLabel;
	@FXML
	private ImageView goBackImage;
	@FXML
	private AnchorPane searchBookButton;
	@FXML
	private AnchorPane searchReviewButton;
	@FXML
	private AnchorPane membershipButton;
	@FXML
	protected ScrollPane scrollPane;
	@FXML
	protected AnchorPane scrollAnchor;

	/** 
	 * 
	 *  This method initializes the controller and updates the static variable 'instance' at AbstractController class.
	 *  Also, it loads to the 'logged-in user' section the user's first and last name.
	 *  
	 */
	
	public void initialize() {
		super.initialize();
		userLabel.setText(ClientUI.user.getFname() + " " + ClientUI.user.getLname());
	}
	
	/**
	 * Simply calling the ClientUI.goBack() method.
	 */
	public void goBackOnClick() {
		ClientUI.goBack();
	}

	/**
	 * Simply changes the 'Go Back' button's image on hover action.
	 */
	
	public void goBackOnHover() {
		goBackImage.setImage(new Image(GraphicsImporter.class.getResource("button_hover.png").toString()));
	}

	/**
	 * Simply changes the 'Go Back' button's image to origin on hover action.
	 */
	
	public void goBackOffHover() {
		goBackImage.setImage(new Image(GraphicsImporter.class.getResource("orange_button.png").toString()));
	}

	/**
	 * This method called when the menu's 'Search Book' button is pressed. 
	 * It redirects the user to the search book page. 
	 * Also, informing the 'Search Book' page that this is just a regular search book functionality, using that 'what' static variable.
	 */
	
	public void searchBookOnClick() {
		SearchBookController.what = "search book";
		ClientUI.setScene("SearchBookGUI.fxml");
	}

	/**
	 * This method called when the menu's 'Search Review' button is pressed. 
	 * It redirects the user to the search review page. 
	 */
	
	public void searchReviewOnClick() {
		ClientUI.setScene("SearchReviewGUI.fxml");
	}
	
	/**
	 * This method called when the menu's 'Memberships' button is pressed. 
	 * First, it checks if the user own a membership,
	 * If he does, it redirects the user to a page that displays his current membership details (end date, etc...)
	 * Else, it redirects the user to the available membership plans in the system.
	 */
	
	public void membershipOnClick() {

		if (ClientUI.member == null) {
			ClientUI.setScene("MembershipsGUI.fxml");
		}

		else {
			ClientUI.setScene("UserMembershipGUI.fxml");
		}
	}
	
	/**
	 * This method is called when the user's mouse reaches hover the menu's drag-area.
	 * It moves the menu anchor to a spot where it's visible to the user, using the moveAnchor method.
	 */
	
	public void toggleMenuOnHover() {
		arrowLabel.setVisible(false);
		toggleMenuAnchor.setVisible(true);
		menuAnchor.setLayoutX(menuAnchor.getLayoutX() + 15);
		moveAnchor(0, 15);
	}

	/**
	 * This method is called when the user's mouse reaches out of the menu's drag-area.
	 * It moves the menu anchor to a spot where it's invisible to the user, using the moveAnchor method.
	 */
	
	public void toggleMenuOffHover() {
		toggleMenuAnchor.setVisible(false);
		arrowLabel.setVisible(true);
		moveAnchor(-175, -15);
	}

	/**
	 * Basically, moves the menu anchor in a 'slow-motion' way from x to d position, using some threads-play.
	 * 
	 * @param x 
	 * 	Start 'x' position of the menu anchor.
	 * @param d
	 * 	Destination 'x' position of the menu anchor.
	 */
	
	public void moveAnchor(int x, int d) {
		Task<Void> sleeper = new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
				}
				return null;
			}
		};
		sleeper.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent event) {
				if (d > 0) {
					if (menuAnchor.getLayoutX() < x) {
						menuAnchor.setLayoutX(menuAnchor.getLayoutX() + d);
						moveAnchor(x, d);
					}
				} else {
					if (menuAnchor.getLayoutX() > x) {
						menuAnchor.setLayoutX(menuAnchor.getLayoutX() + d);
						moveAnchor(x, d);
					}
				}
			}
		});
		new Thread(sleeper).start();
	}
	
	public void searchBookOnHover() {
		if (searchBookButton.getLayoutX() < 20) {
			searchBookButton.setLayoutX(searchBookButton.getLayoutX() + 5);
		}
	}

	public void searchBookOffHover() {
		searchBookButton.setLayoutX(0); 
	}

	public void searchReviewOnHover() {
		if (searchReviewButton.getLayoutX() < 20) {
			searchReviewButton.setLayoutX(searchReviewButton.getLayoutX() + 5);
		}
	}

	public void searchReviewOffHover() {
		searchReviewButton.setLayoutX(0);
	}

	public void membershipOnHover() {
		if (membershipButton.getLayoutX() < 20) {
			membershipButton.setLayoutX(membershipButton.getLayoutX() + 5);
		}
	}

	public void membershipOffHover() {
		membershipButton.setLayoutX(0);
	}
	
	/**
	 * This method logging out the user of the system and redirect to the log-in screen.
	 * It also clears the pages stack.
	 * If it is a user, the method notify the server to update the user's status to offline in database.
	 * @throws IOException
	 */

	public void logoutOnClick() throws IOException {
		ClientUI.pageStack.clear();
		ClientUI.setScene("LoginGUI.fxml");
		if (ClientUI.user instanceof User) {
			Message msg = new Message("system", 1, ClientUI.user);
			Client.instance.sendToServer(msg);
		}
	}
	/**
	 * This method is called when a user presses the 'GoodReading' logo.
	 * It is automatically redirect to the matches homepage (eg. user gets redirected to user homepage, manager to manager homepage...).
	 * In any case, it clears the pages stack.
	 */
	public void logoOnClick() {
		ClientUI.pageStack.clear();
		if (ClientUI.user instanceof User) {
			ClientUI.setScene("UserHomepageGUI.fxml");
			return;
		} else if (ClientUI.user instanceof Employee) {
			Employee emp = (Employee) ClientUI.user;
			switch (emp.getPosition()) {
			case "Librarian":
				ClientUI.setScene("LibrarianHomepageGUI.fxml");
				break;
			case "Library Manager":
				ClientUI.setScene("LibraryManagerHomepageGUI.fxml");
				break;
			case "Library Employee":
				ClientUI.setScene("LibraryEmployeeHomepageGUI.fxml");
				break;
			case "Manager":
				ClientUI.setScene("ManagerHomepageGUI.fxml");
			}
		}
	}
	
	/**
	 * This method implements AbstractController's method. It handles message
	 * from server.
	 * 
	 * @param msg
	 *            case 10: 
	 *				Whenever a user add new review to a book, a pop up alert is displayed to the online editor(s), librarian(s) or a library manager(s) whose in charge of manage reviews.
	 */
	
	public void handleMessage(Message msg) {
		switch (msg.getFunc()) {
		case 10:
			GeneralUser user = ClientUI.user;
			if (user instanceof Employee) {
				if (((Employee) user).getPosition().equals("Editor")
						|| ((Employee) user).getPosition().equals("Librarian")
						|| ((Employee) user).getPosition().equals("Library Manager")) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							Alert alert = new Alert(AlertType.INFORMATION);
							alert.setHeaderText("New Review Submitted");
							alert.setContentText("Go to Review Management to check it.");
							alert.showAndWait();
						}
					});
				}
			}
			break;
		}
	}

}
