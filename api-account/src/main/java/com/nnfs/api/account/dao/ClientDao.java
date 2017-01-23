package com.nnfs.api.account.dao;

import com.nnfs.api.account.domain.Client;

public interface ClientDao extends GenericDao<Client> {
	public Client findByClientId(String clientId);

	public Client findByClientSecret(String clientSecret);
}
