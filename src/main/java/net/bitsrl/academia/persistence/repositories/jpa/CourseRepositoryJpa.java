package net.bitsrl.academia.persistence.repositories.jpa;

import net.bitsrl.academia.model.Course;
import net.bitsrl.academia.model.Course;
import net.bitsrl.academia.persistence.repositories.DataException;
import net.bitsrl.academia.persistence.repositories.abstractions.RepositoryCourse;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Collection;

public class CourseRepositoryJpa implements RepositoryCourse {
    private EntityManager em;
    public CourseRepositoryJpa(EntityManager em) {this.em = em;}

    @Override
    public Course create(Course toInsert) throws DataException {
        em.persist(toInsert);
        return toInsert;
    }

    @Override
    public boolean delete(int courseId) {
        Course toDelete = em.getReference(Course.class, courseId);
        em.remove(toDelete);
        return true;
    }

    @Override
    public boolean update(int courseId, Course toUpdate) {
        Course updated = em.merge(toUpdate);
        return true;
    }

    @Override
    public Collection<Course> getAll() {
        return em.createQuery("select a from Course a", Course.class)
                .getResultList();
    }
    @Override
    public Collection<Course> getByTitleLike(String pattern) {
        TypedQuery q = em.createQuery("select a from Course a where a.title LIKE :pattern", Course.class);
        q.setParameter("pattern", "%" + pattern + "%");
        return q.getResultList();
    }
}
