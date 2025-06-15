package com.moduletwo.innopolis.repository;

import com.moduletwo.innopolis.model.entity.SpecialityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpecialityRepository extends JpaRepository<SpecialityEntity, Long> {
}
