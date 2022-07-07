package br.ic.ufal.bdOP;

import br.ic.ufal.apps.chat.Chat;
import br.ic.ufal.apps.comunidade.Comunidade;
import br.ic.ufal.dao.CRUDImpl;
import br.ic.ufal.interfacesgraficas.TLA_avisos;
import br.ic.ufal.usuario.Usuario;

public class BD_OP {
	private CRUDImpl crudimpl;
	private Usuario usuario;
	private Chat chat;
	private Comunidade comunidade;
	
	public BD_OP() {
		crudimpl = new CRUDImpl();
	}

	public Usuario BD_LOGAR(String login, String senha) {
		usuario = crudimpl.getInstance(crudimpl.autheticationUser(login, senha));
		return usuario;
	}

	public Usuario BD_BUSCARPLOGIN(String loginUsuario) {
		try {
			usuario = new CRUDImpl().usuarioporlogin(loginUsuario);
			return usuario;
		} catch (Exception e) {
			return null;
		}

	}

	public boolean BD_CADASTRAR(String login, String senha, String nome) {
		usuario = new Usuario();
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setNome(nome);
		try {
			if (!crudimpl.checarlogin(usuario.getLogin())) {
				crudimpl.addInstance(usuario);
				return true;
			}
			return false;
		} catch (Exception e) {
			new TLA_avisos().erro_inesperado();
			return false;
		}
	}

	public <E> void BD_UPDATEINFOS(E instance) {
		try {
			new CRUDImpl().updateInstance(instance);
		} catch (Exception e) {
			new TLA_avisos().erro_inesperado();
		}

	}

	public void BD_REMOVERCONTA(Usuario usuario) {
		try {
			Usuario amigo;
			for (String n : usuario.getAmigos()) {
				amigo = new CRUDImpl().usuarioporlogin(n);
				amigo.getAmigos().remove(usuario.getLogin());
				new BD_OP().BD_UPDATEINFOS(amigo);
			}
			Comunidade comunidade;
			for (String n : usuario.getComunidadesIds()){
				comunidade = new BD_OP().BD_BUSCARCOMUNIDADEPID(n);
				comunidade.getUsuariosparticipantes().remove(usuario.getLogin());
				new BD_OP().BD_UPDATEINFOS(comunidade);
			}
			
			new CRUDImpl().deleteInstance(usuario);
		} catch (Exception e) {
			new TLA_avisos().erro_inesperado();
		}
	}

	@SuppressWarnings("finally")
	public Chat BD_CADASTRARCHAT() {
		chat = new Chat();
		try {
			crudimpl.addInstance(chat);
		} catch (Exception e) {
			new TLA_avisos().erro_inesperado();
			chat = null;
		} finally {
			return chat;
		}
	}

	@SuppressWarnings("finally")
	public Chat BD_BUSCARCHATPID(String chatID) {
		try {
			chat = new CRUDImpl().chatpID(chatID);
		} catch (Exception e) {
			e.printStackTrace();
			chat = null;
			new TLA_avisos().erro_inesperado();
		} finally {
			return chat;
		}
	}

	@SuppressWarnings("finally")
	public Comunidade BD_CADASTRARCOMUNIDADE(String nome, String dono, String descricao) {
		comunidade = new Comunidade();
		comunidade.setNome(nome);
		comunidade.setDonologin(dono);
		comunidade.setDescricao(descricao);
		comunidade.setChatID(new BD_OP().BD_CADASTRARCHAT().getId());
		try {
			crudimpl.addInstance(comunidade);
		} catch (Exception e) {
			new TLA_avisos().erro_inesperado();
			comunidade = null;
		} finally {
			return comunidade;
		}
	}
	
	@SuppressWarnings("finally")
	public Comunidade BD_BUSCARCOMUNIDADEPID(String comunidadeID) {
		try {
			comunidade = new CRUDImpl().comunidadepID(comunidadeID);
		} catch (Exception e) {
			e.printStackTrace();
			comunidade = null;
			new TLA_avisos().erro_inesperado();
		} finally {
			return comunidade;
		}
	}

}
