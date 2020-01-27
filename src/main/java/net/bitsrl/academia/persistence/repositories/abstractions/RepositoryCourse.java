package net.bitsrl.academia.persistence.repositories.abstractions;

import net.bitsrl.academia.model.Course;
import net.bitsrl.academia.persistence.repositories.DataException;

import java.util.Collection;

public interface RepositoryCourse {
    Course create(Course toInsert) throws DataException;
    boolean delete(int courseId) throws DataException;
    boolean update(int courseId, Course toUpdate) throws DataException;
    Collection<Course> getAll() throws DataException;
    Collection<Course> getByTitleLike(String pattern) throws DataException;
}
