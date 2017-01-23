package com.nnfs.api.account.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.nnfs.api.account.domain.Client;

@Mapper
public interface ClientMapper extends GenericMapper<Client>{
	public Client findByClientId(String clientId);

	public Client findByClientSecret(String clientSecret);
}
