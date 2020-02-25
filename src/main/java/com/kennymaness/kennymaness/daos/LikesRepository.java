package com.kennymaness.kennymaness.daos;

import com.kennymaness.kennymaness.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LikesRepository extends JpaRepository<Likes, Integer> {
}
