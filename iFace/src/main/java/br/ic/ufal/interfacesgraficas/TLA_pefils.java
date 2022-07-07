package br.ic.ufal.interfacesgraficas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import br.ic.ufal.bdOP.BD_OP;
import br.ic.ufal.usuario.Usuario;

public class TLA_pefils {

	JPanel ctppainel;
	JPanel ctpjanelaant;

	JFrame frmjanela;

	Usuario usuario;
	Usuario amigo;

	private JButton btnadicionar;
	private JButton btnbuscar;
	private JButton btncancelar;

	private JLabel lblprousuario;
	private JLabel lblperfil;
	private JLabel lblnome;
	private JLabel lblprofissao;
	private JLabel lbltrabalho;
	private JLabel lblfaculdade;

	private JTextField txtprousuario;
	private JTextField txtprofissao;
	private JTextField txttrabalho;
	private JTextField txtfaculdade;

	public TLA_pefils(TLA_login tla_login, Usuario user) {
		usuario = user;
		ctpjanelaant = (JPanel) tla_login.getContentPane();
		ctppainel = new JPanel();
		tla_login.getContentPane().setVisible(false);
		tla_login.setContentPane(ctppainel);
		ctppainel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ctppainel.setLayout(null);
		ctppainel.setVisible(true);
		frmjanela = tla_login;

		lblprousuario = new JLabel("Procurar usuário:");
		lblprousuario.setBounds(90, 15, 120, 20);
		lblprousuario.setVisible(true);
		frmjanela.getContentPane().add(lblprousuario);

		txtprousuario = new JTextField("");
		txtprousuario.setBounds(50, 35, 200, 20);
		txtprousuario.setVisible(true);
		frmjanela.getContentPane().add(txtprousuario);

		lblperfil = new JLabel("Perfil:");
		lblperfil.setFont(new Font("Arial",1,15));
		lblperfil.setBounds(5, 110, 80, 20);
		frmjanela.getContentPane().add(lblperfil);
		
		lblnome = new JLabel("Nome: ");
		lblnome.setFont(new Font("Arial",0,15));
		lblnome.setBounds(5, 130, 200, 20);
		frmjanela.getContentPane().add(lblnome);

		lblprofissao = new JLabel("Profissão:");
		lblprofissao.setFont(new Font("Arial",0,13));
		lblprofissao.setBounds(15, 190, 80, 20);
		frmjanela.getContentPane().add(lblprofissao);
		
		txtprofissao = new JTextField();
		txtprofissao.setFont(new Font("Arial",0,13));
		txtprofissao.setBounds(85, 190, 80, 20);
		txtprofissao.setEditable(false);
		frmjanela.getContentPane().add(txtprofissao);
		
		lblfaculdade = new JLabel("Faculdade:");
		lblfaculdade.setFont(new Font("Arial",0,13));
		lblfaculdade.setBounds(15, 230, 80, 20);
		frmjanela.getContentPane().add(lblfaculdade);
		
		txtfaculdade = new JTextField();
		txtfaculdade.setFont(new Font("Arial",0,13));
		txtfaculdade.setBounds(85, 230, 80, 20);
		txtfaculdade.setEditable(false);
		frmjanela.getContentPane().add(txtfaculdade);
		
		lbltrabalho = new JLabel("Trabalho:");
		lbltrabalho.setFont(new Font("Arial",0,13));
		lbltrabalho.setBounds(15, 270, 80, 20);
		frmjanela.getContentPane().add(lbltrabalho);
		
		txttrabalho = new JTextField();
		txttrabalho.setFont(new Font("Arial",0,13));
		txttrabalho.setBounds(85, 270, 80, 20);
		txttrabalho.setEditable(false);
		frmjanela.getContentPane().add(txttrabalho);
		
		btnadicionar = new JButton();
		btnadicionar.setBounds(5, 295, 100, 30);
		btnadicionar.setText("Adicionar!");
		frmjanela.getContentPane().add(btnadicionar);
		
		btnbuscar = new JButton();
		btnbuscar.setFont(new Font("Arial", 1, 15));
		btnbuscar.setBounds(100, 55, 100, 30);
		btnbuscar.setLayout(null);
		btnbuscar.setText("Buscar!");
		frmjanela.getContentPane().add(btnbuscar);
		btnbuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					amigo = new BD_OP().BD_BUSCARPLOGIN(getTxtprousuario().getText());
					//amigo = new CRUDImpl().usuarioporlogin(getTxtprousuario().getText());
					if(amigo != null){
						
						lblperfil = new JLabel("Perfil:");
						lblnome.setText("Nome: "+amigo.getNome());
						lblprofissao.setText("Profissão:");
						txtprofissao.setText(amigo.getProfissao());
						lblfaculdade.setText("Faculdade:");
						txtfaculdade.setText(amigo.getFaculdade());
						lbltrabalho.setText("Trabalho:");
						txttrabalho.setText(amigo.getTrabalho());
						
						btnadicionar.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								if((!(usuario.getAmigos().contains(amigo.getLogin())) && !(usuario.getLogin().contains(amigo.getLogin()))) && !(amigo.getAmigospendentes().contains(usuario.getLogin())) && !(usuario.getAmigospendentes().contains(amigo.getLogin()))){
									amigo.getAmigospendentes().add(usuario.getLogin());
									new BD_OP().BD_UPDATEINFOS(amigo);
									//new CRUDImpl().updateInstance(amigo);
									new TLA_avisos().sucesso_solicitacaodeamizade();
								}else{
									new TLA_avisos().erro_solicitacaodeamizade();
								}

							}
						});
					}else{
						new TLA_avisos().erro_usuarionaoexiste();
					}
				} catch (Exception e2) {
					//e2.printStackTrace();
				}
			}
		});

		btncancelar = new JButton();
		btncancelar.setFont(new Font("Arial", 0, 8));
		btncancelar.setBounds(215, 295, 70, 30);
		btncancelar.setLayout(null);
		btncancelar.setText("Cancelar");
		frmjanela.getContentPane().add(btncancelar);
		btncancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TLA_inicial((TLA_login) frmjanela, usuario);
			}
		});

	}

	public JTextField getTxtprousuario() {
		return txtprousuario;
	}
}
