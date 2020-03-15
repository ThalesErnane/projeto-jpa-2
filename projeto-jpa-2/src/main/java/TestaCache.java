import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import br.com.caelum.JpaConfigurator;
import br.com.caelum.model.Produto;

public class TestaCache {

	public static void main(String[] args) {
	
		
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(JpaConfigurator.class);

        EntityManagerFactory emf = (EntityManagerFactory) ctx.getBean(EntityManagerFactory.class);        
        EntityManager em = emf.createEntityManager();

        Produto produto = em.find(Produto.class, 1);
        Produto produto1= em.find(Produto.class, 1);
        
        
        System.out.println("Nome: " + produto.getNome());
        
        System.out.println(produto.getNome());
        System.out.println(produto1.getNome());
        
        // cache de primeiro nivel é por EntityManager
        // na segundo busca ele pega o valor em cache não utilizando o segundo select no BD
        em.close();
	}

}
