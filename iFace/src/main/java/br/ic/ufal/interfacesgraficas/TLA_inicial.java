package br.ic.ufal.interfacesgraficas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ic.ufal.apps.chat.TLA_chat;
import br.ic.ufal.apps.comunidade.TLA_comunidade;
import br.ic.ufal.bdOP.BD_OP;
import br.ic.ufal.usuario.Usuario;

public class TLA_inicial {

	private TLA_login frmjanela;

	private JPanel ctpjanelaant;
	private JPanel ctppainel;

	private JMenuBar menubar;
	private JMenu opcoes;
	private JMenuItem miamigos;
	private JMenuItem mibuscarusuario;
	private JMenuItem michats;
	private JMenuItem micomunidades;
	private JMenuItem miremoverconta;
	private JMenuItem mideslogar;

	private JButton btneditarPerfil;
	private JButton btnsalvarPerfil;

	private JLabel lblnome;
	private JLabel lblperfil;
	private JLabel lblprofissao;
	private JLabel lblfaculdade;
	private JLabel lbltrabalho;

	private JTextField txtprofissao;
	private JTextField txtfaculdade;
	private JTextField txttrabalho;

	private Usuario usuario;

	public TLA_inicial(TLA_login tla_login, Usuario user) {
		usuario = user;
		ctpjanelaant = (JPanel) tla_login.getContentPane();
		tla_login.getContentPane().setVisible(false);
		ctppainel = new JPanel();
		ctppainel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ctppainel.setLayout(null);
		ctppainel.setVisible(true);
		tla_login.setContentPane(ctppainel);
		frmjanela = tla_login;

		opcoes = new JMenu("Opções");
		menubar = new JMenuBar();
		menubar.add(opcoes);
		frmjanela.setJMenuBar(menubar);

		miamigos = new JMenuItem("Amigos");
		mibuscarusuario = new JMenuItem("Buscar Usuario");
		michats = new JMenuItem("Chats");
		micomunidades = new JMenuItem("Comunidades");
		miremoverconta = new JMenuItem("Remover conta do IFace");
		mideslogar = new JMenuItem("Deslogar");

		opcoes.add(miamigos);
		opcoes.add(mibuscarusuario);
		opcoes.add(michats);
		opcoes.add(micomunidades);
		opcoes.add(miremoverconta);
		opcoes.add(mideslogar);

		lblnome = new JLabel("Bem vindo, " + usuario.getNome());
		lblnome.setFont(new Font("Arial", 0, 15));
		lblnome.setBounds(5, 2, 200, 20);
		frmjanela.getContentPane().add(lblnome);

		lblperfil = new JLabel("Perfil:");
		lblperfil.setFont(new Font("Arial", 1, 15));
		lblperfil.setBounds(5, 40, 80, 20);
		frmjanela.getContentPane().add(lblperfil);

		lblprofissao = new JLabel("Profissão:");
		lblprofissao.setFont(new Font("Arial", 0, 13));
		lblprofissao.setBounds(15, 80, 80, 20);
		frmjanela.getContentPane().add(lblprofissao);

		txtprofissao = new JTextField(usuario.getProfissao());
		txtprofissao.setFont(new Font("Arial", 0, 13));
		txtprofissao.setBounds(85, 80, 200, 20);
		txtprofissao.setEditable(false);
		frmjanela.getContentPane().add(txtprofissao);

		lblfaculdade = new JLabel("Faculdade:");
		lblfaculdade.setFont(new Font("Arial", 0, 13));
		lblfaculdade.setBounds(15, 120, 80, 20);
		frmjanela.getContentPane().add(lblfaculdade);

		txtfaculdade = new JTextField(usuario.getFaculdade());
		txtfaculdade.setFont(new Font("Arial", 0, 13));
		txtfaculdade.setBounds(85, 120, 200, 20);
		txtfaculdade.setEditable(false);
		frmjanela.getContentPane().add(txtfaculdade);

		lbltrabalho = new JLabel("Trabalho:");
		lbltrabalho.setFont(new Font("Arial", 0, 13));
		lbltrabalho.setBounds(15, 160, 80, 20);
		frmjanela.getContentPane().add(lbltrabalho);

		txttrabalho = new JTextField(usuario.getTrabalho());
		txttrabalho.setFont(new Font("Arial", 0, 13));
		txttrabalho.setBounds(85, 160, 200, 20);
		txttrabalho.setEditable(false);
		frmjanela.getContentPane().add(txttrabalho);

		btneditarPerfil = new JButton("Editar perfil");
		btneditarPerfil.setBounds(190, 40, 95, 20);
		btneditarPerfil.setFont(new Font("Arial", 3, 10));
		btneditarPerfil.setVisible(true);
		frmjanela.getContentPane().add(btneditarPerfil);

		btnsalvarPerfil = new JButton("Salvar perfil");
		btnsalvarPerfil.setBounds(190, 40, 95, 20);
		btnsalvarPerfil.setFont(new Font("Arial", 3, 10));
		btnsalvarPerfil.setVisible(false);
		frmjanela.getContentPane().add(btnsalvarPerfil);

		miamigos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TLA_amigos(frmjanela, usuario);
			}
		});

		mibuscarusuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frmjanela.getContentPane().setVisible(false);
					new TLA_pefils(frmjanela, usuario);
				} catch (Exception e2) {
					//e2.printStackTrace();
				}
			}
		});
		
		michats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TLA_chat(frmjanela, usuario);
			}
		});
		
		micomunidades.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new TLA_comunidade(frmjanela, usuario);
				} catch (Exception e2) {
					new TLA_avisos().erro_inesperado();
				}
				
			}
		});

		miremoverconta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					frmjanela.getContentPane().setVisible(false);
					menubar.setVisible(false);
					frmjanela.setContentPane(frmjanela.getCtppainelinicial());
					new BD_OP().BD_REMOVERCONTA(usuario);
					new TLA_avisos().sucesso_removerconta();
					frmjanela.getContentPane().setVisible(true);
				} catch (Exception e2) {
					new TLA_avisos().erro_removerconta();
				}
			}
		});

		mideslogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					usuario = new Usuario();
					frmjanela.getContentPane().setVisible(false);
					menubar.setVisible(false);
					frmjanela.setContentPane(frmjanela.getCtppainelinicial());
					frmjanela.getContentPane().setVisible(true);
					
				} catch (Exception e2) {
					new TLA_avisos().erro_deslogar();
				}
			}
		});

		btneditarPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					getTxtfaculdade().setEditable(true);
					getTxttrabalho().setEditable(true);
					getTxtprofissao().setEditable(true);
					btneditarPerfil.setVisible(false);
					btnsalvarPerfil.setVisible(true);
					btneditarPerfil.setVisible(false);
					btnsalvarPerfil.setVisible(true);
				} catch (Exception e2) {
					new TLA_avisos().erro_updateinfos();
				}
			}
		});

		btnsalvarPerfil.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					btneditarPerfil.setVisible(true);
					btnsalvarPerfil.setVisible(false);
					getTxtfaculdade().setEditable(false);
					getTxttrabalho().setEditable(false);
					getTxtprofissao().setEditable(false);
					usuario.setFaculdade(getTxtfaculdade().getText());
					usuario.setProfissao(getTxtprofissao().getText());
					usuario.setTrabalho(getTxttrabalho().getText());
					new BD_OP().BD_UPDATEINFOS(usuario);
					new TLA_avisos().sucesso_updateinfos();
				} catch (Exception e2) {
					new TLA_avisos().erro_updateinfos();
				}
			}
		});
	}

	public JTextField getTxtprofissao() {
		return txtprofissao;
	}

	public JTextField getTxtfaculdade() {
		return txtfaculdade;
	}

	public JTextField getTxttrabalho() {
		return txttrabalho;
	}

	public JPanel getCtppainel() {
		return ctppainel;
	}
}
