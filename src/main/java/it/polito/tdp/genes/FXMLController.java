package it.polito.tdp.genes;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.genes.model.Adiacenza;
import it.polito.tdp.genes.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class FXMLController {
	
	private Model model ;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnStatistiche;

    @FXML
    private Button btnRicerca;

    @FXML
    private ComboBox<String> boxLocalizzazione;

    @FXML
    private TextArea txtResult;

    @FXML
    void doRicerca(ActionEvent event) {
    	txtResult.clear();
    	String locazione = boxLocalizzazione.getValue();
    	if(locazione==null) {
    		txtResult.appendText("\nSeleziona una locazione!\n");
    		return;
    	}
    	List<String> percorso = this.model.calcolaPercorso(locazione);
    	txtResult.setText("Percorso massimo a partire dal vertice "+locazione+": "+this.model.getPesoOttimo()+"\n");
    	for(String s:percorso)
    		txtResult.appendText(s+"\n");
    	

    }

    @FXML
    void doStatistiche(ActionEvent event) {
    	String locazione = boxLocalizzazione.getValue();
    	if(locazione==null) {
    		txtResult.appendText("\nSeleziona una locazione!\n");
    		return;
    	}
    	List<Adiacenza> vicini = this.model.getStatistiche(locazione);
    	txtResult.appendText("\nAdiacenti a "+locazione+":\n");
    	for(Adiacenza a: vicini)
    		txtResult.appendText(a.toString()+"\n");

    }

    @FXML
    void initialize() {
        assert btnStatistiche != null : "fx:id=\"btnStatistiche\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnRicerca != null : "fx:id=\"btnRicerca\" was not injected: check your FXML file 'Scene.fxml'.";
        assert boxLocalizzazione != null : "fx:id=\"boxLocalizzazione\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		txtResult.clear();
    	String ris= this.model.creaGrafo();
    	txtResult.setText(ris);
    	boxLocalizzazione.getItems().addAll(this.model.getVertici());
	}
}
