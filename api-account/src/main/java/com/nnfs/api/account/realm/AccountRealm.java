package com.nnfs.api.account.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.nnfs.api.account.constant.PromptMsg;
import com.nnfs.api.account.domain.Group;
import com.nnfs.api.account.domain.Permission;
import com.nnfs.api.account.domain.Role;
import com.nnfs.api.account.dto.AccountDto;
import com.nnfs.api.account.dto.RoleDto;
import com.nnfs.api.account.service.AccountService;
import com.nnfs.api.account.service.RoleService;

public class AccountRealm extends AuthorizingRealm {

	@Autowired
	private AccountService accountService;

	@Autowired
	private RoleService roleService;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof AccountToken;
	}

	/**
	 * 授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String accountName = (String) principals.getPrimaryPrincipal();
		AccountDto accountDto = accountService.getAccountByName(accountName);
		if (null != accountDto) {
			List<Role> roles = accountDto.getRoles();
			List<Group> groups = accountDto.getGroups();
			SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
			List<String> roleNames = new ArrayList<>();
			if (CollectionUtils.isNotEmpty(roles)) {
				List<String> account_roleNames = new ArrayList<>();
				roles.forEach(role -> account_roleNames.add(role.getName()));
				roleNames.addAll(account_roleNames);
			}
			if (CollectionUtils.isNotEmpty(groups)) {
				List<Role> group_roles = new ArrayList<>();
				for (Group group : groups) {
					if (CollectionUtils.isNotEmpty(group.getRoles())) {
						group_roles.addAll(group.getRoles());
					}
				}
				if (CollectionUtils.isNotEmpty(group_roles)) {
					List<String> group_roleNames = new ArrayList<>();
					group_roles.forEach(grole -> group_roleNames.add(grole.getName()));
					roleNames.addAll(group_roleNames);
				}
			}

			if (CollectionUtils.isNotEmpty(roleNames)) {
				simpleAuthorizationInfo.setRoles(new HashSet<>(roleNames));
				List<String> permissionNames = new ArrayList<>();
				for (String roleName : roleNames) {
					RoleDto roleDto = this.roleService.getRoleByName(roleName);
					List<Permission> permissions = roleDto.getPermissions();
					for (Permission permission : permissions) {
						permissionNames.add(permission.getName());
					}
				}
				simpleAuthorizationInfo.setStringPermissions(new HashSet<>(permissionNames));
			}
			return simpleAuthorizationInfo;
		} else {
			throw new UnknownAccountException(PromptMsg.Account_does_not_exist.getMsg());
		}
	}

	/**
	 * 认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		AccountToken statelessToken = (AccountToken)token;
		String accountName = statelessToken.getUsername();
		AccountDto accountDto = this.accountService.getAccountByName(accountName);
		if (null == accountDto) {
			throw new UnknownAccountException(PromptMsg.Account_does_not_exist.getMsg());
		}
		if (accountDto.isLocked()) {
			throw new LockedAccountException(PromptMsg.Account_is_locked.getMsg()); // 帐号锁定
		}
		return new SimpleAuthenticationInfo(accountDto.getName(), accountDto.getPassword(),
				ByteSource.Util.bytes(accountDto.getSalt()), this.getName());
	}
	
	private String getAccessToken(String accountName){
		return "helloword!";
	}

}
