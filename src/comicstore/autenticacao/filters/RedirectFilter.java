package comicstore.autenticacao.filters;

import comicstore.autenticacao.beans.AutenticacaoBean;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wheezy on 26/11/15.
 */
public class RedirectFilter implements Filter {
    @Inject
    AutenticacaoBean autenticacaoBean;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(autenticacaoBean == null || !autenticacaoBean.isLoggedIn()){
            String contextPath = ((HttpServletRequest) request).getContextPath();


            ((HttpServletResponse) response).sendRedirect(contextPath+"/login/login.xhtml");
        }
        else chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
