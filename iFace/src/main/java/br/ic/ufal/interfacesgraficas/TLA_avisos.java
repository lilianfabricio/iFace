package br.ic.ufal.interfacesgraficas;

import javax.swing.JOptionPane;

public class TLA_avisos {
	
	public TLA_avisos(){
		
	}
	//SUCESSOS
	public void sucesso_login(){
		JOptionPane.showMessageDialog(null, "Login realizado com sucesso!");
		//System.out.println("Login realizado com sucesso!");
	}
	
	public void sucesso_requisitarcomunidade(){
		JOptionPane.showMessageDialog(null, "Agora esta comunidade é sua!");
	}
	
	public void sucesso_cadastro(){
		JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!");
		//System.out.println("Cadastro realizado com sucesso!");
	}
	public void sucesso_updateinfos(){
		JOptionPane.showMessageDialog(null, "Update realizado com sucesso!");
		//System.out.println("Update realizado com sucesso!");
	}
	
	public void sucesso_deslogar(){
		JOptionPane.showMessageDialog(null, "Deslogado!");
		//System.out.println("Deslogado!");
	}
	
	public void sucesso_removerconta(){
		JOptionPane.showMessageDialog(null, "Conta removida!");
		//System.out.println("Conta removida do sistema!");
	}
	
	public void sucesso_solicitacaodeamizade(){
		JOptionPane.showMessageDialog(null, "A solicitação foi enviada com sucesso!");
		//System.out.println("A solicitação foi enviada com sucesso!");
	}
	
	public void sucesso_solicitacaodeamizadeconcluida(){
		JOptionPane.showMessageDialog(null, "Amigo adicionado!");
		//System.out.println("Amigo adicionado!");
	}
	
	public void sucesso_amigoremovido(){
		JOptionPane.showMessageDialog(null, "Amigo removido!");
		//System.out.println("Amigo removido!");
	}
	
	public void sucesso_chatdeletado(){
		JOptionPane.showMessageDialog(null, "Chat deletado com sucesso!");
		//System.out.println("Chat deletado com sucesso!");
	}
	
	public void sucesso_comunidadecriada(){
		JOptionPane.showMessageDialog(null, "Comunidade criada com sucesso!");
		//System.out.println("Comunidade criada com sucesso!");
	}
	
	//EndSUCESSOS
	//ERROS
	public void erro_login(){
		JOptionPane.showMessageDialog(null, "Login ou Senha estão errados!");
		//System.out.println("Login ou Senha estão errados!");
	}
	
	public void erro_servidor(){
		JOptionPane.showMessageDialog(null, "Servidor está offline!");
		//System.out.println("Servidor está offline!");
	}
	
	public void erro_cadastro(){
		JOptionPane.showMessageDialog(null, "Login já está em uso!");
		//System.out.println("Login já está em uso!");
	}
	
	public void erro_inesperado(){
		JOptionPane.showMessageDialog(null, "Algo inesperado aconteceu!\nTente novamente após reabir o programa!");
		//System.out.println("Algo inesperado aconteceu!\nTente novamente após reabir o programa!");
	}
	
	public void erro_updateinfos(){
		JOptionPane.showMessageDialog(null, "Não foi possível editar/salvar o perfil.\nTente novamente após reabrir o programa");
		//System.out.println("Não foi possível editar/salvar o perfil.\nTente novamente após reabrir o programa");
	}
	
	public void erro_deslogar(){
		JOptionPane.showMessageDialog(null, "Não foi possível deslogar do perfil.\nTente fechar manualmente o programa");
		//System.out.println("Não foi possível deslogar do perfil.\nTente fechar manualmente o programa");
	}
	
	public void erro_removerconta(){
		JOptionPane.showMessageDialog(null, "Não foi possível remover conta do sistema.\nRelogue e tente novamente remover a conta.");
		//System.out.println("Não foi possível remover conta do sistema.\nRelogue e tente novamente remover a conta.");
	}
	
	public void erro_solicitacaodeamizade(){
		JOptionPane.showMessageDialog(null, "Não foi possível enviar a solicitação.\nO usuário já tem você em amigos/amigos pendentes.");
		//System.out.println("Não foi possível enviar a solicitação.\nO usuário já tem você em amigos/amigos pendentes.");
	}
	
	public void erro_solicitacaodecomunidade(){
		JOptionPane.showMessageDialog(null, "Não foi possível enviar a solicitação.\nO usuário já na comunidade.");
	}
	
	public void erro_usuarionaoexiste(){
		JOptionPane.showMessageDialog(null, "O usuário não existe.");
		//System.out.println("O usuário não existe.");
	}
	
	public void erro_amigoremovido(){
		JOptionPane.showMessageDialog(null, "Amigo não pôde ser removido!");
		//System.out.println("Amigo não pôde ser removido!");
	}
	
	public void erro_comunidadenaoexiste(){
		JOptionPane.showMessageDialog(null, "A Comunidade não existe!");
		//System.out.println("A Comunidade não existe!");
	}
	
	public void erro_comunidanaocriada(){
		JOptionPane.showMessageDialog(null, "A Comunidade não foi criada!");
		//System.out.println("A Comunidade não foi criada!");
	}
	
	public void erro_usuarioforadacomunidade(){
		JOptionPane.showMessageDialog(null, "O usuário não participa da comunidade!");
	}
	//EndErros
}
