import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class EntityManagerFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
		throws IOException, ServletException{
	
	EntityManager em = null;
	arg0.setAttribute("em", em);// pendura na requisição, até o jsp ser processado
	arg2.doFilter(arg0, arg1);
	em.close();// fecha após o jsp ser processado
	
//	
//    EntityManagerFactory emf = // obtem EntityManagerFactory
//    EntityManager em = emf.createEntityManager();
//
//    request.setAttribute("em", em);
//
//    chain.doFilter(request, response);
//
//    em.close();   
//	
	
		
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

}
