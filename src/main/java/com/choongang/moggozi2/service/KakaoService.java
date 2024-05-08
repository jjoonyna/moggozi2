package com.choongang.moggozi2.service;


import java.util.Collection;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.choongang.moggozi2.common.Const;
import com.choongang.moggozi2.entity.CustomUserDetails;
import com.choongang.moggozi2.entity.User;
import com.choongang.moggozi2.repository.UserRepository;
import com.choongang.moggozi2.transformer.Trans;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class KakaoService {
	
	private final HttpSession httpSession;	
	
	@Autowired
	private UserRepository newrepository;
	
	@Autowired
	private UserService service;
	
	@Autowired
	public HttpCallService httpCallService;
	

	@Value("${rest-api-key}")
	private String REST_API_KEY;
	
	@Value("${redirect-uri}")
	private String REDIRECT_URI;	
	
	@Value("${authorize-uri}")
	private String AUTHORIZE_URI;		
	
	@Value("${token-uri}")
	public String TOKEN_URI;			
	
	@Value("${client-secret}")
	private String CLIENT_SECRET;	
	
	@Value("${kakao-api-host}")
	private String KAKAO_API_HOST;	
	
	
	public RedirectView goKakaoOAuth() {
       return goKakaoOAuth("");
	}
	
	public RedirectView goKakaoOAuth(String scope) {
	   
	   String uri = AUTHORIZE_URI+"?redirect_uri="+REDIRECT_URI+"&response_type=code&client_id="+REST_API_KEY;
	   if(!scope.isEmpty()) uri += "&scope="+scope;
			   
       return new RedirectView(uri);
	}	
	
	public ModelAndView loginCallback(String code,Model model,Authentication authentication) {	
		String param = "grant_type=authorization_code&client_id="+REST_API_KEY+"&redirect_uri="+REDIRECT_URI+"&client_secret="+CLIENT_SECRET+"&code="+code;
		String rtn = httpCallService.Call(Const.POST, TOKEN_URI, Const.EMPTY, param);
        httpSession.setAttribute("token", Trans.token(rtn, new JsonParser()));  
        
        //유저 정보 가져오기
        String uri = KAKAO_API_HOST + "/v2/user/me";
        String result = httpCallService.CallwithToken(Const.GET, uri, httpSession.getAttribute("token").toString());
        
        //json데이터 파싱
        JsonParser parser = new JsonParser(); 
        JsonElement element = parser.parse(result);
		JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
		JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
		String usernick = properties.getAsJsonObject().get("nickname").getAsString();
		String username = kakao_account.getAsJsonObject().get("email").getAsString();
		String role = "";
		
		//db 저장
		User user = new User();
		boolean newUser = newrepository.existsByUsername(username);
		if(!newUser) {
			user.setUsername(username);
			user.setUsernick(usernick);
			service.snsuserjoin(user);
		}
		
		if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            username = authentication.getName();
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (!authorities.isEmpty()) {
                GrantedAuthority auth = authorities.iterator().next();
                role = auth.getAuthority();
            }

            // 사용자의 닉네임 가져오기
            if (authentication.getPrincipal() instanceof UserDetails) {
                usernick = ((CustomUserDetails) authentication.getPrincipal()).getUsernick();
            }
        }
		
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/main");
		modelAndView.addObject("username", username);
		modelAndView.addObject("role", role);
		modelAndView.addObject("usernick", usernick);
		return modelAndView;
	}
	
	
	public String logout() {	
		String uri = KAKAO_API_HOST + "/v1/user/logout";
		return httpCallService.CallwithToken(Const.POST, uri, httpSession.getAttribute("token").toString());
	}	
	
	
	
			
	public String getProfile() {	
		String uri = KAKAO_API_HOST + "/v2/user/me";
		return httpCallService.CallwithToken(Const.GET, uri, httpSession.getAttribute("token").toString());
	}
	
	public String getFriends() {	
		String uri = KAKAO_API_HOST + "/v1/api/talk/friends";
		return httpCallService.CallwithToken(Const.GET, uri, httpSession.getAttribute("token").toString());
	}	
	
	public String message() {	
		String uri = KAKAO_API_HOST + "/v2/api/talk/memo/default/send";
		return httpCallService.CallwithToken(Const.POST, uri, httpSession.getAttribute("token").toString(), Trans.default_msg_param);
	}		
	
	public String friendMessage(String uuids) {	
		String uri = KAKAO_API_HOST + "/v1/api/talk/friends/message/default/send";
		return httpCallService.CallwithToken(Const.POST, uri, httpSession.getAttribute("token").toString(), Trans.default_msg_param+"&receiver_uuids=["+uuids+"]");
	}	
	

}
