package com.zexovian.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zexovian.api.entity.Contact;


public interface ContactRepository extends JpaRepository<Contact, Long> {
}