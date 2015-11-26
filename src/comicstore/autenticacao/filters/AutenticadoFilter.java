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
public class AutenticadoFilter implements Filter {
    @Inject
    AutenticacaoBean autenticacaoBean;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(autenticacaoBean == null || autenticacaoBean.isLoggedIn()){
            String contextPath = ((HttpServletRequest) request).getContextPath();

            //Redirecionamos o usuário imediatamente
            //para a página de index.xhtml
            ((HttpServletResponse) response).sendRedirect(contextPath+"/");
        }
        else{
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
