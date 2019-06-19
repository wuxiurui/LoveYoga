package org.team3.loveyoga.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;



public class UserRealm extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	
		//先不管角色授权和权限授权！！！！！
		
		
		
		/*System.out.println("正在授权...");
		//获得账号
		String account=(String) principals.getPrimaryPrincipal();
		//根据账号查询对应的角色(多对多)；
		User userRoles=userService.findRolesByAccount(account);
		System.out.println("该账号为："+"---------------"+account);
		System.out.println("该账号的角色为："+userRoles.getRoles());
		List<Role> roles=userRoles.getRoles();
		//用来装上面的角色的名字的set容器,遍历每一个角色
		Set<String> set=new HashSet<>();
		Set<String> permsSet=new HashSet<>();
		for (Role role : roles) {
			set.add(role.getRname());
			//接从数据库中查询角色对应的权限，根据角色的id查询到该角色对应的权限
			String rid=role.getRid();
			Role rolePerms=userService.findPermsByRid(rid);
			System.out.println("该角色拥有的权限为："+"---------"+rolePerms.getPerms());
			List<Perm> perms=rolePerms.getPerms();
			for (Perm perm : perms) {
				//遍历该角色拥有的每一个权限，然后装入权限的set集合中，最后返回给安全管理器
				permsSet.add(perm.getPname());
			}
		}
		
		
		SimpleAuthorizationInfo info =new SimpleAuthorizationInfo(set);  //该账号拥有的角色信息
		info.addStringPermissions(permsSet);   //该角色拥有的权限信息
*/		
		
		return null;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
	
		
		//先不管！！！！
		
		
		
		
		/*	System.out.println("正在认证...");
		//获取account,根据该account取数据库中查找User
		String account=(String) token.getPrincipal();
		User user=new User(account,null);
		User user2=userService.selectUser(user); 
		if (user2==null) {
			return null;
		}
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user2.getAccount(),user2.getPassword(),getName());*/
		return null;
	}

}
