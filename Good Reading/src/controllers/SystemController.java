package controllers;

import java.io.IOException;

import boundary.ClientUI;
import client.Client;
import common.Message;
import graphics.GraphicsImporter;
import i_book.Employee;
import i_book.User;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;

public abstract class SystemController extends AbstractController {

	@FXML
	private AnchorPane toggleMenuAnchor;
	@FXML
	private AnchorPane menuAnchor;
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

	public void initialize() {
		super.initialize();
		userLabel.setText(ClientUI.user.getFname() + " " + ClientUI.user.getLname());
	}

	public void goBackOnClick() {
		//if (ClientUI.user instanceof User) {
			//Client.refresh();
			ClientUI.goBack();
		//}
		/*if (ClientUI.user instanceof Employee) {
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
			}
		}*/
	}

	public void goBackOnHover() {
		goBackImage.setImage(new Image(GraphicsImporter.class.getResource("button_hover.png").toString()));
	}

	public void goBackOffHover() {
		goBackImage.setImage(new Image(GraphicsImporter.class.getResource("orange_button.png").toString()));
	}

	public void searchBookOnClick() {
		SearchBookController.what = "search book";
		ClientUI.setScene("SearchBookGUI.fxml");
	}

	public void searchReviewOnClick() {
		ClientUI.setScene("SearchReviewGUI.fxml");
	}

	public void membershipOnClick() {

		if (ClientUI.member == null) {
			ClientUI.setScene("MembershipsGUI.fxml");
		}

		else {
			System.out.println(ClientUI.member.getUserId());
			ClientUI.setScene("UserMembershipGUI.fxml");
		}
	}

	public void toggleMenuOnHover() {
		arrowLabel.setVisible(false);
		toggleMenuAnchor.setVisible(true);
		menuAnchor.setLayoutX(menuAnchor.getLayoutX() + 15);
		moveAnchor(0, 15);
	}

	public void toggleMenuOffHover() {
		// scrollPane.setLayoutX(0);
		toggleMenuAnchor.setVisible(false);
		arrowLabel.setVisible(true);
		moveAnchor(-175, -15);
	}

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

	public void logoutOnClick() throws IOException {
		ClientUI.setScene("LoginGUI.fxml");
		if (ClientUI.user instanceof User) {
			Message msg = new Message("system", 1, ClientUI.user);
			Client.instance.sendToServer(msg);
		}
	}

	public void logoOnClick() {
		if (ClientUI.user instanceof User) {
			ClientUI.setScene("UserHomepageGUI.fxml");
			return;
		}
		else if (ClientUI.user instanceof Employee) {
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

}
