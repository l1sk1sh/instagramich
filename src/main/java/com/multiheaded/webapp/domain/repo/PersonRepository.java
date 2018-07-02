package com.multiheaded.webapp.domain.repo;

import com.multiheaded.webapp.domain.Person;
import org.springframework.data.repository.CrudRepository;

// TODO перенести repo пакет в інше місце? Чи узагалі викинути в domain?
public interface PersonRepository extends CrudRepository<Person, Long> {
}
