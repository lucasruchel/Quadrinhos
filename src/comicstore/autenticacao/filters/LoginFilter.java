package comicstore.autenticacao.filters;

import comicstore.autenticacao.beans.AutenticacaoBean;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wheezy on 24/11/15.
 */
public class LoginFilter implements Filter {
    @Inject
    AutenticacaoBean autenticacaoBean;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //Verifica se nosso ManagedBean ainda não
        //foi instanciado ou caso a
        //variável loggedIn seja false, assim saberemos que
        // o usuário não está logado
        if(autenticacaoBean == null || !autenticacaoBean.isLoggedIn()){
            String contextPath = ((HttpServletRequest) request).getContextPath();

            //Redirecionamos o usuário imediatamente
            //para a página de login.xhtml
            ((HttpServletResponse) response).sendRedirect(contextPath+"/login/login.xhtml");
        }
        else{
            chain.doFilter(request,response);
        }
    }

    @Override
    public void destroy() {

    }
}
