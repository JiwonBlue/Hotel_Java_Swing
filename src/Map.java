package team5;

import java.io.File;

import javax.swing.*;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Map extends JFrame {
	public Map() {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Hotel everest map");
		setSize(400, 573);
		setLocation(760, 197);
		setResizable(false);
		JFXPanel fxPanel = new JFXPanel();
		add(fxPanel);

		Platform.runLater(new Runnable() {

			public void run() {

				initAndLoadWebView(fxPanel);
			}

		});

		setVisible(true);
	}

	public static void initAndLoadWebView(final JFXPanel fxPanel) {
		Group group = new Group();
		Scene scene = new Scene(group);
		fxPanel.setScene(scene);

		WebView webView = new WebView();

		group.getChildren().add(webView);
		webView.setMinSize(400, 573);
		webView.setMaxSize(400, 573);

		WebEngine webEngine = webView.getEngine();

		webEngine.load(new File("maps/map.html").toURI().toString());
	}
	/*
	 * public static void main(String[] args) { new Map(); }
	 */
}