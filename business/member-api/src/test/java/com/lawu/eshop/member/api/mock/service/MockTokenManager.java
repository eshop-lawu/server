package com.lawu.eshop.member.api.mock.service;

import org.springframework.stereotype.Service;

import com.lawu.authorization.constants.TokenClearType;
import com.lawu.authorization.manager.TokenManager;

@Service
public class MockTokenManager implements TokenManager {

	@Override
	public void delRelationship(String account, TokenClearType tokenClearType) {

	}

	@Override
	public void delRelationshipByToken(String token) {

	}

	@Override
	public String createToken(String type, String userNo, Long userId, String account) {
		return "fdsffffffsdafasfasdfsadfsadfsafdd";
	}

	@Override
	public String getAccount(String token) {
		return null;
	}

	@Override
	public TokenClearType getTokenClearType(String token) {
		return null;
	}
}
