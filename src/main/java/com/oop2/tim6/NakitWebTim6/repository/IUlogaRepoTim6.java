package com.oop2.tim6.NakitWebTim6.repository;

import com.oop2.tim6.NakitWebTim6.model.Uloga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface IUlogaRepoTim6 extends JpaRepository<Uloga, Integer> {
    Uloga findByIdUloge(int id);
}
