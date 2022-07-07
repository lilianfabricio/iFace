package br.ic.ufal.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import br.ic.ufal.apps.chat.Chat;
import br.ic.ufal.apps.comunidade.Comunidade;
import br.ic.ufal.usuario.Usuario;



public class CRUDImpl {

	final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();
	final SessionFactory sessionFactory = new Configuration().configure("./META-INF/hibernate.cfg.xml")
			.buildSessionFactory();

	Session session = threadLocal.get();

	public <E> void addInstance(E instance) {
		session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			session.save(instance);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		}

	}

	public <E> void updateInstance(E instance) {

		session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			session.update(instance);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		}

	}

	public <E> void deleteInstance(E instance) {

		session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			session.delete(instance);
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		}

	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Usuario> getAllInstances() {
		session = sessionFactory.openSession();
		List<Usuario> list = null;

		try {
			session.beginTransaction();

			list = session.createCriteria(Usuario.class).list();

			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}

		return list;

	}

	public int autheticationUser(String login, String senha) {
		session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			@SuppressWarnings("deprecation")
			Criteria crit = session.createCriteria(Usuario.class);
			crit.add(Restrictions.eq("login", login));
			crit.add(Restrictions.eq("senha", senha));
			crit.setMaxResults(1);

			Usuario user = (Usuario) crit.uniqueResult();

			session.close();

			if (user != null) {
				return user.getId();
			} else {
				return -1;
			}

		} catch (HibernateException e) {
			System.out.println("autheticationUser");
			// e.printStackTrace();
			session.getTransaction().rollback();

		}
		return -1;
	}

	public Usuario getInstance(int idUser) {
		session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			@SuppressWarnings("deprecation")
			Criteria crit = session.createCriteria(Usuario.class);
			crit.add(Restrictions.eq("id", idUser));
			crit.setMaxResults(1);
			Usuario user = (Usuario) crit.uniqueResult();
			session.close();
			return user;

		} catch (HibernateException e) {
			// e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	public Usuario usuarioporlogin(String loginUsuario) {
		session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			@SuppressWarnings("deprecation")
			Criteria crit = session.createCriteria(Usuario.class);
			crit.add(Restrictions.eq("login", loginUsuario));
			crit.setMaxResults(1);

			Usuario usuario = (Usuario) crit.uniqueResult();

			session.close();

			return usuario;

		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return null;
		}
	}

	public boolean checarlogin(String login) {
		session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			@SuppressWarnings("deprecation")
			Criteria crit = session.createCriteria(Usuario.class);
			crit.add(Restrictions.eq("login", login));
			crit.setMaxResults(1);
			Usuario usuario = (Usuario) crit.uniqueResult();
			session.close();
			return usuario != null;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Usuario> usuariopornome(String NomeUsuario) {
		session = sessionFactory.openSession();
		List<Usuario> list;
		try {
			session.beginTransaction();

			// @SuppressWarnings("deprecation")
			// Criteria crit = session.createCriteria(Usuario.class);
			// crit.add(Restrictions.eq("nome", NomeUsuario));

			list = session.createCriteria(Usuario.class).add(Restrictions.eq("nome", NomeUsuario)).list();

			session.close();

			return list;

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		}
		return null;
	}

	public boolean checkForEqualEmail(String email) {
		session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			@SuppressWarnings("deprecation")
			Criteria crit = session.createCriteria(Usuario.class);
			crit.add(Restrictions.eq("email", email));
			crit.setMaxResults(1);

			Usuario user = (Usuario) crit.uniqueResult();

			session.close();

			return user != null;

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();

		}
		return false;
	}

	public Chat chatpID(String chatID) {
		int i = Integer.parseInt(chatID);
		session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			@SuppressWarnings("deprecation")
			Criteria crit = session.createCriteria(Chat.class);
			crit.add(Restrictions.eq("id", i));
			crit.setMaxResults(1);
			Chat chat = (Chat) crit.uniqueResult();
			session.close();

			return chat;

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}
	
	public Comunidade comunidadepID(String comunidadeID) {
		int i = Integer.parseInt(comunidadeID);
		session = sessionFactory.openSession();

		try {
			session.beginTransaction();
			@SuppressWarnings("deprecation")
			Criteria crit = session.createCriteria(Comunidade.class);
			crit.add(Restrictions.eq("id", i));
			crit.setMaxResults(1);
			Comunidade comunidade = (Comunidade) crit.uniqueResult();
			session.close();

			return comunidade;

		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	public Chat chatpUsuarios(String usuario, String usuario2) {

		session = sessionFactory.openSession();

		try {
			session.beginTransaction();

			@SuppressWarnings("deprecation")
			Criteria crit = session.createCriteria(Chat.class);
			crit.add(Restrictions.eq("usuario", usuario));
			crit.add(Restrictions.eq("usuario", usuario2));
			crit.add(Restrictions.eq("usuario2", usuario));
			crit.add(Restrictions.eq("usuario2", usuario2));
			crit.setMaxResults(1);

			Chat chat = (Chat) crit.uniqueResult();
			session.close();
			return chat;

		} catch (HibernateException e) {
			// e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

}
