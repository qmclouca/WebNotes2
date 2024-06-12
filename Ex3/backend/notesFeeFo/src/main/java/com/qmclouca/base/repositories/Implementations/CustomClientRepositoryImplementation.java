package com.qmclouca.base.repositories.Implementations;

import com.qmclouca.base.models.Client;
import com.qmclouca.base.repositories.CustomClientRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CustomClientRepositoryImplementation implements CustomClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Client> getClientsByName(String name) {
        String query = "SELECT c FROM Client c WHERE LOWER(c.name) LIKE CONCAT('%', LOWER(:name), '%')";
        return entityManager.createQuery(query, Client.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public Optional<Client> getClientByName(String name) {
        String query = "SELECT c FROM Client c WHERE LOWER(c.name) = LOWER(:name)";

        List<Client> result = entityManager.createQuery(query, Client.class)
                .setParameter("name", name)
                .setMaxResults(1)
                .getResultList();
        if (result != null && !result.isEmpty()) {
            return Optional.of(result.get(0));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        String query = "SELECT c FROM Client c WHERE c.id = :id";
        TypedQuery<Client> typedQuery = entityManager.createQuery(query, Client.class)
                .setParameter("id", id);

        try {
            Client result = typedQuery.getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Client> findByClientNameAndPassword(String userName, String password) {
        String query = "SELECT c FROM Client c WHERE c.clientName = :userName AND c.password = :password";
        TypedQuery<Client> typedQuery = entityManager.createQuery(query, Client.class)
                .setParameter("userName", userName)
                .setParameter("password", password);
        try {
            Client result = typedQuery.getSingleResult();
            return Optional.of(result);
        } catch (NoResultException e) {
            return Optional.empty();
        }
    }
}
