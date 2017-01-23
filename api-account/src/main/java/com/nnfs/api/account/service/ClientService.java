package com.nnfs.api.account.service;

import com.nnfs.api.account.dto.ClientDto;

public interface ClientService extends GenericService<ClientDto> {
	public ClientDto findByClientId(String clientId);

	public ClientDto findByClientSecret(String clientSecret);
}
