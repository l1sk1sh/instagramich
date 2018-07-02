package com.multiheaded.webapp.repository;

import com.multiheaded.webapp.domain.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// TODO перенести repo пакет в інше місце? Чи узагалі викинути в domain?
@RepositoryRestResource
public interface PersonRepository extends CrudRepository<Person, Long> {
}
