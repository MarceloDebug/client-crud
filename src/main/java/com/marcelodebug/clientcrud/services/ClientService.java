package com.marcelodebug.clientcrud.services;

import com.marcelodebug.clientcrud.dto.ClientDto;
import com.marcelodebug.clientcrud.entities.Client;
import com.marcelodebug.clientcrud.repositories.ClientRepository;
import com.marcelodebug.clientcrud.services.exceptions.DatabaseException;
import com.marcelodebug.clientcrud.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDto> findAll(Pageable pageable){
       Page<Client> clients = clientRepository.findAll(pageable);
       return clients.map(x-> new ClientDto(x));
    }

    @Transactional(readOnly = true)
    public ClientDto findById(Long id){
        Client client = clientRepository.findById(id).get();
        return new ClientDto(client);
    }

    @Transactional
    public ClientDto insertClient(ClientDto dto){
        Client client = new Client();
        copyToDto(client, dto);
        client = clientRepository.save(client);
        return new ClientDto(client);
    }

    @Transactional
    public ClientDto updateClient(Long id, ClientDto dto){
        Client client = clientRepository.getReferenceById(id);
        copyToDto(client, dto);
        client = clientRepository.save(client);
        return new ClientDto(client);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id){
        if(!clientRepository.existsById(id)){
            throw new ResourceNotFoundException("Usuário não encontrado");
        }
        try{
            clientRepository.deleteById(id);
        }
        catch(DataIntegrityViolationException e){
            throw new DatabaseException("Falha de integridade referencial");
        }

    }

    private void copyToDto(Client c, ClientDto dto){
        c.setBirthDate(dto.getBirthDate());
        c.setChildren(dto.getChildren());
        c.setCpf(dto.getCpf());
        c.setName(dto.getName());
        c.setIncome(dto.getIncome());
    }

}
