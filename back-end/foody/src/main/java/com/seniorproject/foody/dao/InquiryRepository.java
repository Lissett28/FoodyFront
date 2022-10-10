package com.seniorproject.foody.dao;

import com.seniorproject.foody.entities.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {
}