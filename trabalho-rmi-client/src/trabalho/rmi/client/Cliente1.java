package trabalho.rmi.client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import trabalho.rmi.remotebase.IRemoteMovimentacao;
import trabalho.rmi.servidor.Pessoa;


public class Cliente1 {	
	
	public static void main(String[] args) {
		
		System.out.println("Registrando no servidor remoto...");
		Pessoa p = new Pessoa();
		p.setId(1);
		p.setNome("Rodrigo");
		p.setSaldo(556.0);
		
		Pessoa p2 = new Pessoa();
		p2.setId(2);
		p2.setNome("Claudio");
		p2.setSaldo(665.00);
		try {
			Registry registry = LocateRegistry.getRegistry(9876);
			
			IRemoteMovimentacao stub = (IRemoteMovimentacao) registry.lookup("servidor_aula");
			
			System.out.println(p.getNome());
			
			stub.conectar(p.getId(),p.getNome(),p.getSaldo());
			stub.depositar(p.getId(),p.getNome(),850.00);
			double valor = stub.consultar(p.getId(),p.getNome(),p.getSaldo());
			
			stub.sacar(p.getId(),p.getNome(),405.0);
			
			System.out.println(valor);
			
			valor = stub.consultar(p.getId(),p.getNome(),p.getSaldo());
			
			System.out.println(valor);
			
			
			
			System.out.println("\n\n" + p2.getNome());
			
			stub.conectar(p2.getId(), p2.getNome(), p2.getSaldo());
			stub.depositar(p2.getId(), p2.getNome(), 665.00);
			valor = stub.consultar(p2.getId(),p2.getNome(),p2.getSaldo());
			
			System.out.println(valor);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
