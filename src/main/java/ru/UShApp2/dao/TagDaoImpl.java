package ru.UShApp2.dao;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import ru.UShApp2.model.Tag;

import java.util.List;

@Repository
public class TagDaoImpl implements TagDao {

    SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    @Override
    @SuppressWarnings(value = "uncheked")
    public List<Tag> listTag() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Tag> tagList = session.createQuery("FROM ru.UShApp2.model.Tag").list();

        return tagList;
    }

    Session session;

    public Session getSession() {
        return session;
    }

    @Override
    @SuppressWarnings("uncheked")
    public Tag save(Tag tag) {
        return (Tag) getSession().save(tag);
    }

    @Override
    public Tag findById(int idTag) {
        Criteria cr = getSession()
                .createCriteria(Tag.class, "Tag")
                .add(Restrictions.eq("idTag", "idTag"));
        return (Tag) cr.uniqueResult();
    }

    @Override
    public Tag findByName(String tagname) {
        Criteria cr = getSession()
                .createCriteria(Tag.class, tagname)
                .add(Restrictions.like("tagname", tagname));
        return (Tag) cr.uniqueResult();
    }
}
