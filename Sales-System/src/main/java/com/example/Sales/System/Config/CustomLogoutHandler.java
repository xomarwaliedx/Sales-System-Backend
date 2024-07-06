package com.example.Sales.System.Config;

import com.example.Sales.System.entity.Token;
import com.example.Sales.System.repository.TokenRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomLogoutHandler implements LogoutHandler {

    private final TokenRepository tokenRepository;

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String authHeader = request.getHeader("Authorization");
        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            return;
        }
        String token = authHeader.substring(7);

        Token tokens = tokenRepository.findByToken(token).orElse(null);
        if(tokens!=null){
            tokens.setLoggedOut(true);
            tokenRepository.save(tokens);
        }
    }
}
