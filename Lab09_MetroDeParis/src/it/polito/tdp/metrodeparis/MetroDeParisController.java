/**
 * Sample Skeleton for 'MetroDeParis.fxml' Controller Class
 */

package it.polito.tdp.metrodeparis;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.metrodeparis.model.Fermata;
import it.polito.tdp.metrodeparis.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class MetroDeParisController {

	private Model model ;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="comboPartenza"
    private ComboBox<Fermata> comboPartenza; // Value injected by FXMLLoader

    @FXML // fx:id="comboArrivo"
    private ComboBox<Fermata> comboArrivo; // Value injected by FXMLLoader

    @FXML // fx:id="buttonPercorso"
    private Button buttonPercorso; // Value injected by FXMLLoader

    @FXML // fx:id="txtArea"
    private TextArea txtArea; // Value injected by FXMLLoader

    @FXML
    void doPercorso(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert comboPartenza != null : "fx:id=\"comboPartenza\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert comboArrivo != null : "fx:id=\"comboArrivo\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert buttonPercorso != null : "fx:id=\"buttonPercorso\" was not injected: check your FXML file 'MetroDeParis.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'MetroDeParis.fxml'.";

    }
    
    
    public void setModel(Model model) {
		this.model = model;
		
		// riempi la prima tendina con l'elenco completo delle fermate
		comboPartenza.getItems().addAll(this.model.getFermate());
		
		
	}
}


