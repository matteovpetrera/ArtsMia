/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.artsmia;

import java.net.URL;


import java.util.ResourceBundle;

import org.jgrapht.Graph;

import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.artsmia.model.ArtObject;
import it.polito.tdp.artsmia.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.jgrapht.alg.connectivity.ConnectivityInspector;

public class FXMLController {
	
	private Model model;
	
	private Graph<ArtObject, DefaultWeightedEdge> grafo;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="boxLUN"
    private ChoiceBox<?> boxLUN; // Value injected by FXMLLoader

    @FXML // fx:id="btnCalcolaComponenteConnessa"
    private Button btnCalcolaComponenteConnessa; // Value injected by FXMLLoader

    @FXML // fx:id="btnCercaOggetti"
    private Button btnCercaOggetti; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalizzaOggetti"
    private Button btnAnalizzaOggetti; // Value injected by FXMLLoader

    @FXML // fx:id="txtObjectId"
    private TextField txtObjectId; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    
    
    @FXML
    void doAnalizzaOggetti(ActionEvent event) {
    	
    	grafo = this.model.creaGrafo();
    	
    	this.txtResult.appendText("il grafo è stato correttamente creato e contiene:\n" 
    			+ grafo.vertexSet().size()+" vertici;\n" 
    			+ grafo.edgeSet().size()+" archi.");
    }

    @FXML
    void doCalcolaComponenteConnessa(ActionEvent event) {
    	
    	
    	txtResult.clear();
    	int id = Integer.valueOf(txtObjectId.getText());
    	ArtObject ogg = null;
    	
    	ConnectivityInspector<ArtObject, DefaultWeightedEdge> inspector = new ConnectivityInspector<>(grafo);
    	for(ArtObject o: model.listObjects()) {
    		if(o.getId()==id) {
    			ogg = o;
    		}
    	}
        System.out.println("Componente connessa di " + ogg + ": " + inspector.connectedSetOf(ogg));
    	
		
    	txtResult.appendText("Componente connessa di " + ogg + ": " + inspector.connectedSetOf(ogg));
    	
    }

    @FXML
    void doCercaOggetti(ActionEvent event) {
    	
    	//txtResult.clear();
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert boxLUN != null : "fx:id=\"boxLUN\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCalcolaComponenteConnessa != null : "fx:id=\"btnCalcolaComponenteConnessa\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCercaOggetti != null : "fx:id=\"btnCercaOggetti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnAnalizzaOggetti != null : "fx:id=\"btnAnalizzaOggetti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtObjectId != null : "fx:id=\"txtObjectId\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
