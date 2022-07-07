package br.ic.ufal.apps.comunidade;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ic.ufal.apps.chat.Chat;
import br.ic.ufal.apps.chat.TLA_chataberta;
import br.ic.ufal.bdOP.BD_OP;
import br.ic.ufal.interfacesgraficas.TLA_avisos;
import br.ic.ufal.interfacesgraficas.TLA_login;
import br.ic.ufal.usuario.Usuario;

public class TLA_perfildacomunidade {

	private JPanel ctppainel;

	private JFrame frmjanela;

	private Usuario usuario;
	private Comunidade comunidade;

	private JLabel lblnome;
	private JLabel lbldescricao;
	private JLabel lbladicionarusuario;

	private JTextField txtadicionarusuario;

	private JButton btnadicionar;
	private JButton btnremover;
	private JButton btnchat;
	private JButton btncancelar;
	
	private JLabel lblrequisitarcomunidade;
	private JButton btnrequisitarcomunidade;

	public TLA_perfildacomunidade(TLA_login tla_login, Usuario user, Comunidade com) {
		usuario = user;
		comunidade = com;
		ctppainel = new JPanel();
		tla_login.getContentPane().setVisible(false);
		tla_login.setContentPane(ctppainel);
		ctppainel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ctppainel.setLayout(null);
		ctppainel.setVisible(true);
		frmjanela = tla_login;

		lblnome = new JLabel("Nome da comunidade: " + comunidade.getNome());
		lblnome.setFont(new Font("Arial", 0, 15));
		lblnome.setBounds(5, 15, 270, 20);
		frmjanela.getContentPane().add(lblnome);

		lbldescricao = new JLabel("Descrição: " + comunidade.getDescricao());
		lbldescricao.setFont(new Font("Arial", 0, 15));
		lbldescricao.setBounds(5, 30, 200, 200);
		frmjanela.getContentPane().add(lbldescricao);

		if(new BD_OP().BD_BUSCARPLOGIN(comunidade.getDonologin()) == null){
			lblrequisitarcomunidade = new JLabel("Comunidade sem dono!");
			lblrequisitarcomunidade.setFont(new Font("Arial",1,11));
			lblrequisitarcomunidade.setBounds(5, 20, 120, 20);
			frmjanela.getContentPane().add(lblrequisitarcomunidade);
			
			btnrequisitarcomunidade = new JButton("Requisitar");
			btnrequisitarcomunidade.setFont(new Font("Arial",1,12));
			btnrequisitarcomunidade.setBounds(5, 20, 100, 20);
			frmjanela.getContentPane().add(btnrequisitarcomunidade);
			btnrequisitarcomunidade.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					comunidade.setDonologin(usuario.getLogin());
					if(!comunidade.getUsuariosparticipantes().contains(usuario.getLogin())){
						comunidade.getUsuariosparticipantes().add(usuario.getLogin());
						Integer i = comunidade.getId();
						String s = i.toString();
						usuario.getComunidadesIds().add(s);
					}
					new BD_OP().BD_UPDATEINFOS(comunidade);
					new BD_OP().BD_UPDATEINFOS(usuario);
					new TLA_perfildacomunidade((TLA_login) frmjanela, usuario, comunidade);
					new TLA_avisos().sucesso_requisitarcomunidade();
				}
			});
			
		}
		
		if (comunidade.getUsuariosparticipantes().contains(usuario.getLogin())) {

			btnchat = new JButton();
			btnchat.setFont(new Font("Arial", 1, 10));
			btnchat.setBounds(220, 5, 70, 30);
			btnchat.setLayout(null);
			btnchat.setText("Chat");
			frmjanela.getContentPane().add(btnchat);
			btnchat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Integer i = comunidade.getChatID();
					String s = i.toString();
					Chat chat = new BD_OP().BD_BUSCARCHATPID(s);
					new TLA_chataberta((TLA_login) frmjanela, usuario, chat);
				}
			});
		}

		if (comunidade.getDonologin().contains(usuario.getLogin())) {

			lbladicionarusuario = new JLabel("Adicionar usuário:");
			lbladicionarusuario.setBounds(15, 245, 140, 20);
			lbladicionarusuario.setVisible(true);
			frmjanela.getContentPane().add(lbladicionarusuario);

			txtadicionarusuario = new JTextField("");
			txtadicionarusuario.setBounds(15, 270, 200, 20);
			txtadicionarusuario.setVisible(true);
			frmjanela.getContentPane().add(txtadicionarusuario);

			btnadicionar = new JButton();
			btnadicionar.setFont(new Font("Arial", 1, 15));
			btnadicionar.setBounds(15, 295, 120, 30);
			btnadicionar.setLayout(null);
			btnadicionar.setText("Adicionar!");
			frmjanela.getContentPane().add(btnadicionar);
			btnadicionar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Usuario nparticipante = new BD_OP().BD_BUSCARPLOGIN(txtadicionarusuario.getText());
					if (nparticipante != null) {
						if(!comunidade.getUsuariosparticipantes().contains(nparticipante.getLogin())){
							comunidade.getUsuariosparticipantes().add(nparticipante.getLogin());
							Integer i = comunidade.getId();
							String s = i.toString();
							nparticipante.getComunidadesIds().add(s);
							new BD_OP().BD_UPDATEINFOS(comunidade);
							new BD_OP().BD_UPDATEINFOS(nparticipante);
							new TLA_avisos().sucesso_cadastro();
						}else{
							new TLA_avisos().erro_solicitacaodecomunidade();
						}
					}else{
						new TLA_avisos().erro_usuarionaoexiste();
					}
					
				}
			});
			
			btnremover = new JButton();
			btnremover.setFont(new Font("Arial", 1, 8));
			btnremover.setBounds(135, 295, 80, 30);
			btnremover.setLayout(null);
			btnremover.setText("Remover!");
			frmjanela.getContentPane().add(btnremover);
			btnremover.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Usuario nparticipante = new BD_OP().BD_BUSCARPLOGIN(txtadicionarusuario.getText());
					if (nparticipante != null) {
						if(comunidade.getUsuariosparticipantes().contains(nparticipante.getLogin())){
							comunidade.getUsuariosparticipantes().remove(usuario.getLogin());
							Integer i = comunidade.getId();
							String s = i.toString();
							nparticipante.getComunidadesIds().remove(s);
							new BD_OP().BD_UPDATEINFOS(comunidade);
							new BD_OP().BD_UPDATEINFOS(nparticipante);
							new TLA_avisos().sucesso_removerconta();
						}else{
							new TLA_avisos().erro_usuarioforadacomunidade();
						}
					}else{
						new TLA_avisos().erro_usuarionaoexiste();
					}
					
				}
			});
			
		}

		btncancelar = new JButton();
		btncancelar.setFont(new Font("Arial", 0, 8));
		btncancelar.setBounds(215, 295, 70, 30);
		btncancelar.setLayout(null);
		btncancelar.setText("Cancelar");
		frmjanela.getContentPane().add(btncancelar);
		btncancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TLA_comunidade((TLA_login) frmjanela, usuario);
			}
		});

	}
}
