// ClientServiceImplementation.java
package com.qmclouca.base.services.Implementations;

import com.qmclouca.base.models.Client;
import com.qmclouca.base.repositories.ClientRepository;
import com.qmclouca.base.services.ClientService;
import jakarta.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ClientServiceImplementation implements ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImplementation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) {
        client.setPassword(client.getPassword());
        return clientRepository.save(client);
    }

    @Override
    public List<Client> getClientsByName(String name) {
        return clientRepository.getClientsByName(name);
    }

    @Override
    public List<Client> getAllClients(){
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> getClientByName(String name){
        return clientRepository.getClientByName(name);
    }

    @Override
    public boolean deleteClient(String name){
        Optional<Client> client = clientRepository.getClientByName(name);
        if (client.isPresent()){
            clientRepository.delete(client.get());
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteClient(Long id){
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()){
            clientRepository.delete(client.get());
            return true;
        } else {
            return false;
        }
    }

    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client getClientByNameAndPassword(String name, String password) throws NoResultException {
        Client client = clientRepository.findByClientName(name);
        if (Objects.equals(password, client.getPassword())) {
            return client;
        } else {
            throw new NoResultException("Nome de usuário ou senha incorreta.");
        }
    }
}
