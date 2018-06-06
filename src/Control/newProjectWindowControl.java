package Control;
import java.io.IOException;
import java.util.List;

import Model.Material;
/**
 * @author Reza Amjad
 * this controller class is in charge of getting user input and do all the calculations 
 * and send all the information to the project manager
 */
import Model.ProjectManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class newProjectWindowControl {
	
	@FXML  private TabPane tab;
	@FXML  private Tab noneElectericTab;
	@FXML  private Tab ElectericTab;
	@FXML  private GridPane leftGrid;
	@FXML  private GridPane righttGrid;
	@FXML  private TextField height;
	@FXML  private TextField width;
	@FXML  private ComboBox<Material> material;
	@FXML  private TextField total;
	@FXML  private Button cancel;
	@FXML  private Button save;
	@FXML  private Button calculate;
	@FXML  private Label error;

	
	private double projectHeight;
	private double projectWidth;
	private String usedMaterial;
	private ProjectManager Manager;
	private double totalCost = 0;
	
	@FXML private TableView<Material> table;
	@FXML private TableColumn<Material, String> nameCol;
	@FXML private TableColumn<Material, Double> heightCol;
	@FXML private TableColumn<Material, Double> widthCol;
	@FXML private TableColumn<Material, Double> priceCol;
	
	 /*
	  * this function validate user input
	  */
	 @FXML
	 private void checkHeight() {
	       try {
	    	 if((Double.parseDouble(height.getText()))> 0) {
	    		 setProjectHeight(Double.parseDouble(height.getText()));
	    	 }
			}catch (NumberFormatException e) {
				error.setVisible(true);
				height.clear();
		       }					
	 }
	
	
	 
	 /**
	  * @author Reza Amjad
	  * this function validate user input
	  **/
	 @FXML
	 private void checkWidth() {
		 try {
	    	 if(setProjectWidth(Double.parseDouble(width.getText())) > 0) {
	    		 setProjectWidth(Double.parseDouble(height.getText()));
	    	 }
			}catch (NumberFormatException e) {
				error.setVisible(true);
				width.clear();
		       }
	 }
	/**
	 * @author Reza Amjad
	 * this function is responsible for sending all the informantion back to the data manager.
	 * when user click on the save button
	 */
	@FXML
	private void saveClicked() {
		
	}
	/**
	 * @author Reza Amjad
	 * this function will call the calculation function
	 * when calculate button clicked
	 * 
	 */
	@FXML
	private void calculateClicked() {
		
		totalCost += calculation();
		String result = String.valueOf(totalCost);
		total.setText(result);
	}
	
	@FXML
	private void cancelClicked() {
		((Stage)(cancel.getScene().getWindow())).close();
	}
	/**
	 * @author Reza Amjad
	 * add the list of materials to the menu button so user can choose
	 * @throws IOException 
	 */
	
	public void loadTheList(ObservableList<Material> mat) {
		material.getItems().addAll(mat);
	}
	
	 /**
	  * @author Reza Amjad 
	  * this class will creat a table to hold the materials that user have selected
	  */
	 
	 private void selectedMaterials(ComboBox cb) {
		 nameCol.setCellValueFactory(new PropertyValueFactory<Material,String>("cb.getValue().getName()"));
		 heightCol.setCellValueFactory(new PropertyValueFactory<Material,Double>("cb.getValue().getHeight()"));
		 widthCol.setCellValueFactory(new PropertyValueFactory<Material,Double>("cb.getValue().getWidth()"));
		 priceCol.setCellValueFactory(new PropertyValueFactory<Material,Double>("cb.getValue().getPrice()"));
	 }
	
	/**
	 * @author Reza Amjad
	 * this function is responsible for doing all the calculations
	 */
	private double calculation() {
		selectedMaterials(material);
		double length = getProjectHeight() % material.getValue().getHeight();
		double width = getProjectWidth() % material.getValue().getWidth();
		int result = (int) Math.ceil( length * width);
		double totalCost = result * material.getValue().getPrice();
		return totalCost;
	}

	public double getProjectHeight() {
		return projectHeight;
	}

	public double setProjectHeight(double projectHeight) {
		this.projectHeight = projectHeight;
		return projectHeight;
	}

	public double getProjectWidth() {
		return projectWidth;
	}

	public double setProjectWidth(double projectWidth) {
		this.projectWidth = projectWidth;
		return projectWidth;
	}
	
	public void addManager(ProjectManager theManager) {
		Manager = theManager;
	}
}
