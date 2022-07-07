package br.ic.ufal.apps.comunidade;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Comunidade {
	@Id
	@GeneratedValue
	private int Id;

	private String nome;
	private String donologin;
	private String descricao;

	private ArrayList<String> usuariosparticipantes;
	
	private int chatID;

	public Comunidade() {
		nome = new String();
		donologin = new String();
		descricao = new String();
		usuariosparticipantes = new ArrayList<String>();
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDonologin() {
		return donologin;
	}

	public void setDonologin(String donologin) {
		this.donologin = donologin;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public ArrayList<String> getUsuariosparticipantes() {
		return usuariosparticipantes;
	}

	public void setUsuariosparticipantes(ArrayList<String> usuariosparticipantes) {
		this.usuariosparticipantes = usuariosparticipantes;
	}

	public int getChatID() {
		return chatID;
	}

	public void setChatID(int chatID) {
		this.chatID = chatID;
	}
}