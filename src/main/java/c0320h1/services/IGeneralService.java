package c0320h1.services;

import java.util.Optional;

public interface IGeneralService<T> {
	Iterable<T> findAll();

	void save(T entity);

	void update(T entity);

	Optional<T> findById(Long id);

	void deleteById(Long id);

	void delete(T entity);
}
