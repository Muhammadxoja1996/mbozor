//package uz.market.mbozor.service;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.lang.NonNull;
//import org.springframework.stereotype.Component;
//import org.springframework.web.filter.OncePerRequestFilter;
//import uz.market.mbozor.component.HttpSession;
//import uz.market.mbozor.repository.UserAuthRepository;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Author: Muhammadxo'ja
// * Date: 25.02.2022
// * Time: 21:24
// */
//@Component
//public class RequestFilter extends OncePerRequestFilter {
//
//    private final HttpSession httpSession;
//    private static final List<String> whiteList = new ArrayList<>();
//    private final UserAuthRepository userAuthRepository;
//
//    static {
//        whiteList.add("/login");
//        whiteList.add("/logout");
//        whiteList.add("/swagger-ui/index.html");
//    }
//
//    public RequestFilter(HttpSession httpSession, UserAuthRepository userAuthRepository) {
//        this.httpSession = httpSession;
//        this.userAuthRepository = userAuthRepository;
//    }
//
//    @Override
//    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
//        if (checkWhiteList(request.getRequestURI())) {
//            filterChain.doFilter(request, response);
//        } else {
//            if (httpSession.getToken() == null) {
//                response.sendError(HttpStatus.UNAUTHORIZED.value(),HttpStatus.UNAUTHORIZED.getReasonPhrase());
//            } else {
//                if (userAuthRepository.existsByToken(httpSession.getToken())) {
//                    filterChain.doFilter(request, response);
//                } else {
//                    response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
//                }
//            }
//        }
//    }
//
//    private boolean checkWhiteList(String requestURI) {
//        for (String w : whiteList) {
//            if (w.equals(requestURI)) {
//                return true;
//            }
//        }
//        return false;
//    }
//}
