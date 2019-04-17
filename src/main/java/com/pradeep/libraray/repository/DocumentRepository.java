package com.pradeep.libraray.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pradeep.libraray.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer>{

}
