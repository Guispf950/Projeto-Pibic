package controller;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;

public class AcerteAPalavraConsultarQuestaoController implements Initializable{

	
	@FXML    TableView<Map<String, String>> tableView = new TableView<>();


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		ObservableList<Map<String, String>> dados = FXCollections.observableArrayList(
                criarJogo("Paris", "Torre Eiffel"),
                criarJogo("Londres", "Big Ben"),
                criarJogo("Roma", "Coliseu"),
                criarJogo("Nova York", "Estátua da Liberdade"),
                criarJogo("Tóquio", "Monte Fuji"),
                criarJogo("Sydney", "Ópera de Sydney"),
                criarJogo("Cairo", "Pirâmides de Gizé"),
                criarJogo("Rio de Janeiro", "Cristo Redentor"),
                criarJogo("Pequim", "Grande Muralha"),
                criarJogo("Moscou", "Catedral de São Basílio")
        );
		 
		// Adicionar dados à TableView
        tableView.setItems(dados);

	}
	
	private Map<String, String> criarJogo(String palavra, String dica) {
        Map<String, String> jogo = new HashMap<>();
        jogo.put("palavra", palavra);
        jogo.put("dica", dica);
        return jogo;
    }
	
	@FXML
	public void onBttnCriar() {
		mudarCena("/view/QuizCriarView.fxml");	
	}
	
	@FXML
	public void onBttnConsultar() {
		mudarCena("/view/QuizConsultarView.fxml");	
	}
	
	@FXML
	public void onBttnAlterar() {
		mudarCena("/view/QuizAlterarView.fxml");	
	}
	
	@FXML
	public void onBttnExcluir() {
		mudarCena("/view/QuizExcluirView.fxml");	
	}
	
	private void mudarCena(String absoluteView) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(absoluteView));
		try {
			Parent parent = loader.load();
			Scene scene = new Scene(parent);
			Main.setMainScene(scene);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
/* @Override
    public void start(Stage stage) {
        // Criar TableView
        TableView<Jogo> tableView = new TableView<>();

        // Criar colunas
        TableColumn<Jogo, String> colunaPalavra = new TableColumn<>("Palavra");
        colunaPalavra.setCellValueFactory(new PropertyValueFactory<>("palavra"));

        TableColumn<Jogo, String> colunaDica = new TableColumn<>("Dica");
        colunaDica.setCellValueFactory(new PropertyValueFactory<>("dica"));

        // Adicionar colunas à TableView
        tableView.getColumns().addAll(colunaPalavra, colunaDica);

        // Criar dados
        ObservableList<Jogo> dados = FXCollections.observableArrayList(
                new Jogo("Paris", "Torre Eiffel"),
                new Jogo("Londres", "Big Ben"),
                new Jogo("Roma", "Coliseu"),
                new Jogo("Nova York", "Estátua da Liberdade"),
                new Jogo("Tóquio", "Monte Fuji"),
                new Jogo("Sydney", "Ópera de Sydney"),
                new Jogo("Cairo", "Pirâmides de Gizé"),
                new Jogo("Rio de Janeiro", "Cristo Redentor"),
                new Jogo("Pequim", "Grande Muralha"),
                new Jogo("Moscou", "Catedral de São Basílio")
        );

        // Adicionar dados à TableView
        tableView.setItems(dados);*/