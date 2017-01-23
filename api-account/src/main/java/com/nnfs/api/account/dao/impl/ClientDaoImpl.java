package com.nnfs.api.account.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nnfs.api.account.dao.ClientDao;
import com.nnfs.api.account.domain.Client;
import com.nnfs.api.account.mapper.ClientMapper;

@Repository
public class ClientDaoImpl extends GenericDaoImpl<Client> implements ClientDao {

	@Autowired
	private ClientMapper clientMapper;

	@Override
	public Client findByClientId(String clientId) {
		return this.clientMapper.findByClientId(clientId);
	}

	@Override
	public Client findByClientSecret(String clientSecret) {
		return this.clientMapper.findByClientSecret(clientSecret);
	}

}
