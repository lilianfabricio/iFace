package br.ic.ufal.apps.chat;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import br.ic.ufal.usuario.Usuario;

@Entity
public class Chat {
	@Id
	@GeneratedValue
	private int Id;
	
	private ArrayList<String> participantes;
	private String chat;
	
	public Chat(){
		chat = new String("");
		participantes = new ArrayList<String>();
	}
	
	public void enviarparachat(Usuario usuario, String mensagem){
		chat.concat("\n"+usuario.getNome()+": "+mensagem.trim());
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getChat() {
		return chat;
	}

	public void setChat(String chat) {
		this.chat = chat;
	}

	public ArrayList<String> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(ArrayList<String> participantes) {
		this.participantes = participantes;
	}
	
	public void addParticipantes(String participante){
		this.participantes.add(participante);
	}
	
}
