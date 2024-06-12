package com.qmclouca.base.repositories;

import com.qmclouca.base.models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{
    List<Note> findAllByClientId(Long clientId);
}
