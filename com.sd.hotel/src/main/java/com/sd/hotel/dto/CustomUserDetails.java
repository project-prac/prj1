package com.sd.hotel.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
public class CustomUserDetails implements UserDetails {
	
	/*
	 CustomUserDetails:UserDetails 의 경우 사용자의 아이디, 비밀번호, 권한 등의
	 기본적인 사용자 정보만을 포함하고 있다.
	 사용자에 대한 추가 정보가 필요할 경우, UserDetails구현체를 그대로 쓰기보다 
	 확장된 사용자 정보클래스를 만들어 추가정보를 PrincipalUser에 담는 것이 유리함
	 ※ PrincipalUser :는 Spring Security에서 사용자 인증과 권한 관리를 할 때,
	  기본 제공되는 UserDetails 인터페이스를 구현한 커스터마이즈된 사용자 객체
	 */
	
	/* 
	  UserDetails : 인증된 사용자의 정보를 담는 인터페이스,
	  사용자의 인증관련정보(사용자 이름, 비밀번호, 권한 등)을 제공함
	 */

	private static final long serialVersionUID = 1L;
	/*자바 직렬화(serialization)와 관련된 것입니다. 
	 * 직렬화는 객체의 상태를 바이트 스트림으로 변환하여 저장하거나 네트워크를 통해 전송하는 기능입니다. 
	 * 이 과정에서 클래스는 serialVersionUID라는 고유한 ID를 가집니다.
	 * serialVersionUID는 클래스가 직렬화/역직렬화될 때, 
	 * 직렬화된 객체가 해당 클래스의 현재 버전과 호환되는지 확인하는 데 사용됩니다. 
	 * 만약 클래스가 변경되었지만 serialVersionUID가 일치하지 않으면 InvalidClassException이 발생할 수 있습니다.
	*/
	//private final MemberDto member;
	//private EmployeeDto emp;
	
	private final UserDto user;
	private final UserDetailDto userDetail;
	
	//private final Integer memberNo;
	
	public CustomUserDetails(UserDto user) {
		this.user = user;
		this.userDetail = null;
	//	this.memberNo = null;
	}
	
	public CustomUserDetails(UserDto user, UserDetailDto userDetail) {
		this.user = user;
		this.userDetail = userDetail;
		//this.memberNo = null;
	}
	
	public CustomUserDetails(UserDto user, UserDetailDto userDetail,Integer memberNo) {
		this.user = user;
		this.userDetail = userDetail;
		//this.memberNo = memberNo;
	} 

	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<GrantedAuthority> collection = new ArrayList<>();
		
		collection.add(new GrantedAuthority() {
			
			@Override
			public String getAuthority() {
				return user.getRole();
			}
		});
		return collection;
	}

	@Override
	public String getPassword() {
		System.out.println(user.getPassword());
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserId();
	}
	
	
	public String getName() {
		return user.getName();
	}
	
	public String getMail() {
		return userDetail.getEmail();
	}
			
	public String getGender() {
		return userDetail.getGender();
	}

	public String getTel() {
		return userDetail.getTel();
	}
	
	public Date getBirth() {
		return userDetail.getBirth();
	}
	
	public UserDto getUserDto() {
		return this.user;
	}
	
	public UserDetailDto getUserDetailDto() {
		return this.userDetail;
	}
	
	public int getUserNo() {
    return user.getNo();
	}
	/**/

	
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}
