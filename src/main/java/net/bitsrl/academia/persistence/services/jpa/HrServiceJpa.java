package net.bitsrl.academia.persistence.services.jpa;

import net.bitsrl.academia.model.Agent;
import net.bitsrl.academia.model.Course;
import net.bitsrl.academia.persistence.repositories.DataException;
import net.bitsrl.academia.persistence.repositories.abstractions.RepositoryAgent;
import net.bitsrl.academia.persistence.repositories.abstractions.RepositoryCourse;
import net.bitsrl.academia.persistence.services.abstractions.HRService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.Collection;

public class HrServiceJpa implements HRService {
    private RepositoryAgent agentRepo;
    private RepositoryCourse courseRepo;
    private EntityManager em;
    public HrServiceJpa(RepositoryAgent agentR, RepositoryCourse courseR,EntityManager em) {
        this.agentRepo = agentR;
        this.courseRepo = courseR;
        this.em = em;
    }


    @Override
    public Agent createAgent(Agent toInsert) throws DataException {
        em.getTransaction().begin();
        try {
            Agent inserted = agentRepo.create(toInsert);
            em.getTransaction().commit();
            return inserted;
        } catch (PersistenceException e) {
            em.getTransaction().rollback();
            throw new DataException("errore della creazione dell' agent con JPA", e);
        }
    }

    @Override
    public boolean deleteAgent(int agentId) throws DataException {
        em.getTransaction().begin();
        try {
            agentRepo.delete(agentId);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException pe) {
            em.getTransaction().rollback();
            throw new DataException("errore nella cancellazione dell' agent con JPA", pe);
        }
    }

    @Override
    public boolean updateAgent(int agentId, Agent toUpdate) throws DataException {
        em.getTransaction().begin();
        try {
            agentRepo.update(agentId, toUpdate);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException pe) {
            em.getTransaction().rollback();
            throw new DataException("errore nella cancellazione dell' agent con JPA", pe);
        }
    }

    @Override
    public Collection<Agent> getAllAgents() throws DataException {
        try {
            return agentRepo.getAll();
        } catch (PersistenceException pe) {
            throw new DataException("errore nella lista dell' agent con JPA", pe);
        }
    }

    @Override
    public Collection<Agent> getAgentsByLastNameLike(String pattern) throws DataException {
        try {
            return agentRepo.getByLastnameLike(pattern);
        } catch (PersistenceException pe) {
            throw new DataException("errore nella lista like su lastname dell' agent con JPA", pe);
        }
    }


    //-----------------------------------------------------------------------------------------------------------------
    //CORSOOOOOOOO
    @Override
    public Course createCourse(Course toInsert) throws DataException{
        em.getTransaction().begin();
        try {
            Course inserted = courseRepo.create(toInsert);
            em.getTransaction().commit();
            return inserted;
        } catch (PersistenceException pe) {
            em.getTransaction().rollback();
            throw new DataException("errore nella creazione di un nuovo corso con JPA", pe);
        }
    }

    @Override
    public boolean deleteCourse(int courseId) throws DataException{
        em.getTransaction().begin();
        try {
            courseRepo.delete(courseId);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException pe) {
            em.getTransaction().rollback();
            throw new DataException("errore nella cancellazione del corso con JPA", pe);
        }
    }

    @Override
    public boolean updateCourse(int courseId, Course toUpdate) throws DataException{
        em.getTransaction().begin();
        try {
            courseRepo.update(courseId, toUpdate);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException pe) {
            em.getTransaction().rollback();
            throw new DataException("errore nell'aggiornamento del corso con JPA",pe);
        }
    }

    @Override
    public Collection<Course> getAllCourses() throws DataException{
        try {
            return courseRepo.getAll();
        } catch (PersistenceException pe) {
            throw new DataException("errore nella lista di corsi tramite JPA", pe);
        }
    }

    @Override
    public Collection<Course> getCoursesByTitleLike(String pattern) throws DataException{
        try {
            return courseRepo.getByTitleLike(pattern);
        } catch (PersistenceException pe) {
            throw new DataException("errore nella lista corsi like title tramite JPA", pe);
        }
    }
}
