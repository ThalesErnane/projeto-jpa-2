import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import br.com.caelum.JpaConfigurator;

public class TestaPool {

	public static void main(String[] args) throws PropertyVetoException, SQLException {
		
		
		ComboPooledDataSource dataSource = (ComboPooledDataSource) new JpaConfigurator().getDataSource();
		Connection conncetion = dataSource.getConnection();
		
        System.out.println("Conexões existentes: " + dataSource.getNumConnections());
        System.out.println("Conexões ocupadas: " + dataSource.getNumBusyConnections());// mostra o numero de conexões, ocupadas
        System.out.println("Conexões ociosas: " + dataSource.getNumIdleConnections()); // numero de conexões osciosas
		

}
}