package uz.market.mbozor.service;

import org.apache.catalina.connector.Response;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;
import uz.market.mbozor.component.HttpSession;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author: Muhammadxo'ja
 * Date: 25.02.2022
 * Time: 21:24
 */
@Component
public class RequestFilter extends OncePerRequestFilter {

    private final HttpSession httpSession;
    private static final List<String> whiteList = new ArrayList<>();

    static {
        whiteList.add("/login");
        whiteList.add("/logout");
    }

    public RequestFilter(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        if (checkWhiteList(request.getRequestURI())) {
            filterChain.doFilter(request, response);
        } else {
            if (httpSession.getToken() == null) {
                ContentCachingResponseWrapper wrapper = new ContentCachingResponseWrapper(response);
                wrapper.setStatus(401);
                filterChain.doFilter(request, wrapper);
            } else {
                filterChain.doFilter(request, response);
            }
        }
    }

    private boolean checkWhiteList(String requestURI) {
        for (String w : whiteList) {
            if (w.equals(requestURI)) {
                return true;
            }
        }
        return false;
    }
}
