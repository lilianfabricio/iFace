package br.ic.ufal.interfacesgraficas;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import br.ic.ufal.bdOP.BD_OP;
import br.ic.ufal.usuario.Usuario;

public class TLA_amigos {
	
	private JPanel ctppainel;

	private JFrame frmjanela;

	private Usuario usuario;
	private Usuario amigo;
	
	private JLabel lblseusamigos;
	private JLabel lblamigospendentes;

	private JButton btnaceitaramigopendente;
	private JButton btnremoveramigo;
	private JButton btncancelar;
	
	private JComboBox<String> cbamigos;
	private JComboBox<String> cbamigospendentes;
	
	
	public TLA_amigos(TLA_login tla_login, Usuario user){
		usuario = user;
		ctppainel = new JPanel();
		tla_login.getContentPane().setVisible(false);
		tla_login.setContentPane(ctppainel);
		ctppainel.setBorder(new EmptyBorder(5, 5, 5, 5));
		ctppainel.setLayout(null);
		ctppainel.setVisible(true);
		frmjanela = tla_login;
		
		lblseusamigos = new JLabel("Seus Amigos:");
		lblseusamigos.setBounds(15, 30, 100, 20);
		tla_login.getContentPane().add(lblseusamigos);
		
		lblamigospendentes = new JLabel("Amigos pendentes:");
		lblamigospendentes.setBounds(170, 30, 120, 20);
		tla_login.getContentPane().add(lblamigospendentes);
		
		cbamigos = new JComboBox<String>();
		cbamigos.setBounds(15, 50, 120, 20);
		
		this.gerarcombobox(cbamigos, usuario.getAmigos());
		
		/*for(String n : usuario.getAmigos()){
			cbamigos.addItem(n);
		}*/
		
		cbamigospendentes  = new JComboBox<String>();
		cbamigospendentes.setBounds(170, 50, 120, 20);
		
		this.gerarcombobox(cbamigospendentes, usuario.getAmigospendentes());
		/*for(String n : usuario.getAmigospendentes()){
			cbamigospendentes.addItem(n);
		}*/
		
		frmjanela.getContentPane().add(cbamigos);
		frmjanela.getContentPane().add(cbamigospendentes);
	
		btnaceitaramigopendente = new JButton("Aceitar");
		btnaceitaramigopendente.setBounds(170,80,120,20);
		frmjanela.getContentPane().add(btnaceitaramigopendente);
		btnaceitaramigopendente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					amigo = new BD_OP().BD_BUSCARPLOGIN((String) getCbamigospendentes().getSelectedItem());
					//amigo = new CRUDImpl().usuarioporlogin((String) getCbamigospendentes().getSelectedItem());
					if(amigo != null){
						usuario.getAmigos().add(amigo.getLogin());
						amigo.getAmigos().add(usuario.getLogin());
						usuario.getAmigospendentes().remove(amigo.getLogin());
						
						new BD_OP().BD_UPDATEINFOS(usuario);
						new BD_OP().BD_UPDATEINFOS(amigo);
						
						new TLA_amigos((TLA_login) frmjanela, usuario);
						new TLA_avisos().sucesso_solicitacaodeamizadeconcluida();
					}else{
						usuario.getAmigospendentes().remove((String) getCbamigospendentes().getSelectedItem());
						new BD_OP().BD_UPDATEINFOS(usuario);
						new TLA_amigos((TLA_login) frmjanela, usuario);
						new TLA_avisos().erro_usuarionaoexiste();
					}
				} catch (Exception e2) {
					usuario.getAmigospendentes().remove((String) getCbamigospendentes().getSelectedItem());
					new BD_OP().BD_UPDATEINFOS(usuario);
					new TLA_amigos((TLA_login) frmjanela, usuario);
					new TLA_avisos().erro_usuarionaoexiste();
				}
			}
		});
		
		btnremoveramigo = new JButton("Remover");
		btnremoveramigo.setBounds(15,80,120,20);
		frmjanela.getContentPane().add(btnremoveramigo);
		btnremoveramigo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					amigo = new BD_OP().BD_BUSCARPLOGIN((String) getCbamigos().getSelectedItem());
					//amigo = new CRUDImpl().usuarioporlogin((String) getCbamigos().getSelectedItem());
					if(amigo != null){
						usuario.getAmigos().remove(amigo.getLogin());
						amigo.getAmigos().remove(usuario.getLogin());
						
						new BD_OP().BD_UPDATEINFOS(usuario);
						new BD_OP().BD_UPDATEINFOS(amigo);
						
						//new CRUDImpl().updateInstance(usuario);
						//new CRUDImpl().updateInstance(amigo);
						new TLA_amigos((TLA_login) frmjanela, usuario);
						new TLA_avisos().sucesso_amigoremovido();
					}else{
						usuario.getAmigos().remove((String) getCbamigos().getSelectedItem());
						new BD_OP().BD_UPDATEINFOS(usuario);
						new TLA_amigos((TLA_login) frmjanela, usuario);
						new TLA_avisos().erro_usuarionaoexiste();
					}
				} catch (Exception e2) {
					usuario.getAmigos().remove((String) getCbamigos().getSelectedItem());
					new BD_OP().BD_UPDATEINFOS(usuario);
					new TLA_amigos((TLA_login) frmjanela, usuario);
					new TLA_avisos().erro_usuarionaoexiste();
				}
			}
		});
		
		btncancelar = new JButton();
		btncancelar.setFont(new Font("Arial",0,8));
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
	
	public JComboBox<String> getCbamigos() {
		return cbamigos;
	}

	public JComboBox<String> getCbamigospendentes() {
		return cbamigospendentes;
	}
	
	public <E> void gerarcombobox(JComboBox<E> combobox, ArrayList<E> list){
		for(E n: list){
			combobox.addItem(n);
		}
		
	}
	
}
