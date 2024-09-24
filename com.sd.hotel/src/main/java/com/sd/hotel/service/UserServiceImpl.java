package com.sd.hotel.service;

import java.io.PrintWriter;
import java.sql.Date;
import java.util.Map;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sd.hotel.dto.CustomUserDetails;
import com.sd.hotel.dto.MemberDto;
import com.sd.hotel.mapper.MemberMapper;
import com.sd.hotel.utils.MyJavaMailUtils;
import com.sd.hotel.utils.MySecurityUtils;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {
	
	private final MemberMapper memberMapper;
	private final MyJavaMailUtils myJavaMailUtils;
	private final CustomAuthenticationProvider authenticationProvider;
	
	public UserServiceImpl(MemberMapper memberMapper, MyJavaMailUtils myJavaMailUtils, CustomAuthenticationProvider authenticationProvider) {
		super();
		this.memberMapper = memberMapper;
		this.myJavaMailUtils = myJavaMailUtils;
		this.authenticationProvider = authenticationProvider;
	}

	@Override
	public ResponseEntity<Map<String, Object>> checkId(Map<String, Object> params) {
		boolean enableId = memberMapper.getMemberByMap(params) == null;
		return new ResponseEntity<>(Map.of("enableId",enableId), HttpStatus.OK);
	}

	
    /*
     * 구글 앱 비밀번호 설정 방법
     * 1. 구글에 로그인한다.
     * 2. [계정] - [보안]
     * 3. [Google에 로그인하는 방법] - [2단계 인증]을 사용 설정한다.
     * 4. 검색란에 "앱 비밀번호"를 검색한다.
     * 5. 앱 이름을 "myapp"으로 작성하고 [만들기] 버튼을 클릭한다.
     * 6. 16자리 비밀번호가 나타나면 복사해서 사용한다. (비밀번호 사이 공백은 모두 제거한다.)
     */
	
	@Override
	public ResponseEntity<Map<String, Object>> sendCode(Map<String, Object> params) {
		
		String code = MySecurityUtils.getRandomString(6, true, true);
		System.out.println("인증코드:" + code);
		
		myJavaMailUtils.sendMail((String)params.get("email")
				,"Hotel 회원가입 인증요청"
				,"<div>인증코드는 "+code+" 입니다");
		
		return new ResponseEntity<>(Map.of("code",code), HttpStatus.OK);
		
	}
	
	
	@Override
	public void signup(HttpServletRequest request, HttpServletResponse response) {
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		String meberId = request.getParameter("memberId");
		String name = MySecurityUtils.getPrvetnXss(request.getParameter("name"));
		String password = passwordEncoder.encode(request.getParameter("password"));
		String tel = request.getParameter("tel");
		String gender = request.getParameter("gender");
		String email = request.getParameter("memberEmail");
		Date birth = Date.valueOf(request.getParameter("birth"));
		String role = request.getParameter("role");
		
		MemberDto member = MemberDto.builder()
													.memberId(meberId)
													.memberEmail(email)
													.password(password)
													.name(name)
													.tel(tel)
													.gender(gender)
													.birth(birth)
													.role(role)
												.build();
												
		int insertCount = memberMapper.insertMember(member);
		
		try {
			
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			
			if(insertCount == 1) {
				
				out.println("alert('회원가입 되었습니다. 로그인을 진행해주세요.');");
				out.println("location.href='" + request.getContextPath() + "/user/login.page';");
				
			}else {
				out.println("alert('회원가입이 실패했습니다.');");
				out.println("history.back();");
			}
			out.println("</script>");
			out.flush();
			out.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
				
	}
	
	
	@Override
	public MemberDto getMemberById(String memberId) {

		MemberDto member = memberMapper.getMemberById(memberId);
		
		return member;
	}
	
	
	// user mypage 정보 수정
	@Override
	public void modifyMember(HttpServletRequest request) {
		
		String memberId = request.getParameter("memberId");
		String name = request.getParameter("name");
		Date birth = Date.valueOf(request.getParameter("birth"));
		String tel = request.getParameter("tel");
		String mail = request.getParameter("memberEmail");
		
		System.out.println(mail);
		
		MemberDto member = MemberDto.builder()
																.memberId(memberId)
																.name(name)
																.birth(birth)
																.tel(tel)
																.memberEmail(mail)
															.build();
		
		System.out.println(member);
		
		memberMapper.updateMember(member);
		System.out.println("뀨"+memberMapper.updateMember(member));
		
		
		MemberDto updateMember = memberMapper.getMemberById(memberId);
		CustomUserDetails user = new CustomUserDetails(updateMember);
		
		System.out.println("유저!!!:"+user);
		
		
		Authentication currentAuth = SecurityContextHolder.getContext().getAuthentication();
		Authentication newauth = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(user,currentAuth.getCredentials() ,user.getAuthorities() ));
   
		System.out.println("???auth:"+newauth);
		System.out.println("?");
		SecurityContextHolder.getContext().setAuthentication(newauth);

	}

	
}
