package com.learning.common.repository;

import com.learning.common.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.UUID;

@NoRepositoryBean
public interface BaseRepository<C extends BaseEntity> extends JpaRepository<C , UUID> {
}
