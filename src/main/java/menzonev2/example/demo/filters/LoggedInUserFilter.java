//package menzonev2.example.demo.filters;
//
//import menzonev2.example.demo.domain.entities.User;
//import menzonev2.example.demo.domain.services.models.UserServiceModel;
//import menzonev2.example.demo.repositories.UserRepository;
//import menzonev2.example.demo.web.AuthenticatedUserService;
//import org.modelmapper.ModelMapper;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@Component
//public class LoggedInUserFilter implements Filter {
//
//    private final AuthenticatedUserService authenticatedUserService;
//    private final UserRepository userRepository;
//    private final ModelMapper mapper;
//
//    public LoggedInUserFilter(AuthenticatedUserService authenticatedUserService, UserRepository userRepository, ModelMapper mapper) {
//        this.authenticatedUserService = authenticatedUserService;
//        this.userRepository = userRepository;
//        this.mapper = mapper;
//    }
//
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
//                         FilterChain filterChain) throws IOException, ServletException {
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        String username = authentication.getName();
//
//
////        User user = this.userRepository.findByUsername(username).orElse(null);
////
////        UserServiceModel sessionUser = this.mapper.map(user , UserServiceModel.class);
//
//        if (username == null){
//            filterChain.doFilter(servletRequest , servletResponse);
//        }
//
//        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
//
//
//
//        session.setAttribute("user" , username);
//        filterChain.doFilter(servletRequest , servletResponse);
//    }
//}
