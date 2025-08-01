package com.marcelodebug.clientcrud.repositories;

import com.marcelodebug.clientcrud.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
