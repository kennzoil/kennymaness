package com.kennymaness.kennymaness.daos;

import com.kennymaness.kennymaness.models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface CommentsRepository extends JpaRepository<Comments, Integer> {
}
