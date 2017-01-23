package com.nnfs.api.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nnfs.api.account.dao.ClientDao;
import com.nnfs.api.account.domain.Client;
import com.nnfs.api.account.dto.ClientDto;
import com.nnfs.api.account.service.ClientService;

@Service
public class ClientServiceImpl extends GenericServiceImpl<ClientDto, Client> implements ClientService {

	@Autowired
	private ClientDao clientDaoImpl;

	@Override
	public ClientDto findByClientId(String clientId) {
		return this.convertToDto(clientDaoImpl.findByClientId(clientId));
	}

	@Override
	public ClientDto findByClientSecret(String clientSecret) {
		return this.convertToDto(this.clientDaoImpl.findByClientSecret(clientSecret));
	}

	@Override
	public Client convertToDomain(ClientDto t) {
		Client client = new Client();
		client.setClientId(t.getClientId());
		client.setId(t.getId());
		client.setName(t.getName());
		client.setSecret(t.getSecret());
		return client;
	}

	@Override
	public ClientDto convertToDto(Client d) {
		ClientDto dto = new ClientDto();
		dto.setClientId(d.getClientId());
		dto.setId(d.getId());
		dto.setName(d.getName());
		dto.setSecret(d.getSecret());
		return dto;
	}

}
