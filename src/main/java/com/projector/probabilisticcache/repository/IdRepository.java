package com.projector.probabilisticcache.repository;

import com.projector.probabilisticcache.model.IdEntity;
import org.springframework.data.repository.CrudRepository;

public interface IdRepository extends CrudRepository<IdEntity, Integer> {
}
