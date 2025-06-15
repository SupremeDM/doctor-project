package com.moduletwo.innopolis.repository;

import com.moduletwo.innopolis.model.entity.ZoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZoneRepository extends JpaRepository<ZoneEntity, Long> {
}
