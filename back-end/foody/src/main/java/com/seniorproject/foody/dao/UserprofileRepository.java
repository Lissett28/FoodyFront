package com.seniorproject.foody.dao;

import com.seniorproject.foody.entities.Userprofile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserprofileRepository extends JpaRepository<Userprofile, Long> {
}