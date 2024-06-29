 
package controller;

import java.awt.Color;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import application.Main;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import model.bo.AcertePalavraQuestaoBO;
import model.dao.AcertePalavraQuestaoDAO;
import model.vo.AcertePalavraQuestaoVO;
 

public class AcertePalavraQuestaoController {
	
	public void adcionarQuestao(String dica, String palavra, String id_jogo) { // TROCAR TELA QUIZ PELA TELA DE ADCIONAR QUESTAO NO ACERTE PALAVRA
		new AcertePalavraQuestaoBO().adcionarQuestao(dica, palavra, id_jogo);
	}
	

	public boolean verificarResposta(String resposta, String id_jogo, int acertos, int numeroQuestao) {
		boolean acertou = new AcertePalavraQuestaoBO().verificarResposta(resposta, id_jogo, numeroQuestao);
		return acertou;
		
	}
	
	public AcertePalavraQuestaoVO consultarQuestaoId(int id) {
		AcertePalavraQuestaoVO questao = new AcertePalavraQuestaoVO();
		questao = new AcertePalavraQuestaoBO().consultarQuestaoId(id);
		return questao;	
	}
	
	public List<AcertePalavraQuestaoVO> consultarQuestoesId(String id_jogo) {
		
		List<AcertePalavraQuestaoVO> questoes = new ArrayList<>();
		questoes = new AcertePalavraQuestaoBO().consultarQuestoesId(id_jogo);
		return questoes;
	}
	
	public void finalizarAcertePalavra(String idAcertePalavra, AcerteAPalavraController tela, int acertos, Timestamp tempoInicial) {
			
		List<AcertePalavraQuestaoVO> questoes = new AcertePalavraQuestaoDAO().consultarQuestoes(idAcertePalavra);
		 
		String palavra = "";
		 
		
		  for (int i = 0; i < questoes.size(); i++) {
			  palavra = "";
	            for (int j = 0; j < questoes.get(i).getPalavra().length(); j++) {
	       
	                 palavra += tela.getSalvarTextFields()[i][j].getText().toUpperCase(); 
	                
	                
	            } 
	           
	           
	          boolean acertou =  new AcertePalavraQuestaoDAO().verificarResposta(palavra, idAcertePalavra, i+1);
	          if(acertou) {
	        	  for(int l = 0 ; l < questoes.get(i).getPalavra().length();l++ ) {
	        		  
	        		  tela.getSalvarTextFields()[i][l].setStyle("-fx-background-color: green;");
	        	 
	        		  
	        	  }
	        	  
	          }
	             
	        }  
	
		
		
		if (showConfirmationDialog("O jogo acabou, sua pontuação foi: " + acertos)) {
			
		}
		
		Date aux = new Date();
		Timestamp tempoFinal = new Timestamp(aux.getTime());
		new PontuacaoController().salvarPontuacao(idAcertePalavra, "guilherme", acertos, tempoInicial, tempoFinal);
		
	}
	
	private boolean showConfirmationDialog(String message) {
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
		alert.setTitle("Confirmação");
		alert.setHeaderText(null);
		alert.setContentText(message);

		// Botões do diálogo
		ButtonType buttonTypeOK = new ButtonType("Sim");
		ButtonType buttonTypeCancel = new ButtonType("Não");

		alert.getButtonTypes().setAll(buttonTypeOK, buttonTypeCancel);
		
		alert.initOwner(Main.getStage());


		// Exibe o diálogo e aguarda a resposta do usuário
		return alert.showAndWait().orElse(ButtonType.CANCEL) == buttonTypeOK;
	}
	
	 
	
}