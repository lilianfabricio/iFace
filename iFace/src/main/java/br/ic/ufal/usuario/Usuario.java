package br.ic.ufal.usuario;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {
	@Id
	@GeneratedValue
	private int Id;
	
	private String nome;
	private String login;
	private String senha;

	private String profissao;
	private String trabalho;
	private String faculdade;
	
	private ArrayList<String> amigos;
	private ArrayList<String> amigospendentes;
	//PACOTE DE NOMES(CONVERSAS).
	private ArrayList<String> chatsIds;
	private ArrayList<String> comunidadesIds;
	
	public Usuario(){

		nome = new String();
		login = new String();
		senha = new String();
		profissao = new String();
		trabalho = new String();
		faculdade = new String();
		amigos = new ArrayList<String>();
		amigospendentes = new ArrayList<String>();
		chatsIds  = new ArrayList<String>();
		comunidadesIds  = new ArrayList<String>();
	}
	
	//GETS
	public int getId() {
		return Id;
	}
	public String getNome() {
		return nome;
	}
	public String getLogin() {
		return login;
	}
	public String getSenha() {
		return senha;
	}
	public String getProfissao() {
		return profissao;
	}
	public String getTrabalho() {
		return trabalho;
	}
	public String getFaculdade() {
		return faculdade;
	}
	public ArrayList<String> getAmigos() {
		return amigos;
	}
	public ArrayList<String> getAmigospendentes() {
		return amigospendentes;
	}

	public ArrayList<String> getChatsIds() {
		return chatsIds;
	}

	public ArrayList<String> getComunidadesIds() {
		return comunidadesIds;
	}
	//EndGETS
	//SETS
	public void setId(int id) {
		Id = id;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}
	public void setTrabalho(String trabalho) {
		this.trabalho = trabalho;
	}
	public void setFaculdade(String faculdade) {
		this.faculdade = faculdade;
	}
	public void setAmigos(ArrayList<String> amigos) {
		this.amigos = amigos;
	}

	public void setAmigospendentes(ArrayList<String> amigospendentes) {
		this.amigospendentes = amigospendentes;
	}

	public void setChatsIds(ArrayList<String> chatsIds) {
		this.chatsIds = chatsIds;
	}
	
	public void setComunidadesIds(ArrayList<String> comunidadesIds) {
		this.comunidadesIds = comunidadesIds;
	}
	//EndSETS
	public void addAmigos(String amigo){
		this.amigos.add(amigo);
	}
	
	public void addChatsIds(String chatID){
		this.chatsIds.add(chatID);
	}
}
