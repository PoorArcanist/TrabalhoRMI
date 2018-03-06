package trabalho.rmi.servidor;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import trabalho.rmi.remotebase.IRemoteMovimentacao;

public class Servidor implements IRemoteMovimentacao{

	
	List<Pessoa> lista = new ArrayList<>();

	public static void main(String[] args) {
		System.out.println("CONSTRUINDO SERVIDOR REMOTO!");
		Servidor servidor = new Servidor();
		
		try {
			IRemoteMovimentacao stub = (IRemoteMovimentacao) UnicastRemoteObject.exportObject(servidor, 0);
		
			Registry registry = LocateRegistry.getRegistry(9876);
			
			registry.bind("servidor_aula", stub);
			
			System.out.println("SERVIDOR INICIADO!");
			
		} catch (RemoteException | AlreadyBoundException e) {
			e.printStackTrace();
		}
	}


	@Override
	public void cadastrar(int id, String nome, double saldo) throws RemoteException {
		Pessoa p = new Pessoa();
		p.setId(id);
		p.setNome(nome);
		p.setSaldo(saldo);
		lista.add(p);
		
	}


	@Override
	public void conectar(int id, String nome, double saldo) throws RemoteException {
		int cadastrado = 0;
		for (Pessoa pessoa : lista) {
			if(pessoa.getId() == id) {
				System.out.println("Ta cadastrado");
				cadastrado = 1;
			}
		}
		if(cadastrado == 0) {
			cadastrar(id,nome,saldo);
		}
		
	}

	@Override
	public double consultar(int id, String nome, double saldo) throws RemoteException {
		Pessoa p = lista.get(id-1);
		return p.getSaldo();
	}

	@Override
	public void depositar(int id, String nome, double valor) throws RemoteException {
		System.out.println("depositando " + valor);
		Pessoa p = lista.get(id-1);
		System.out.println("\n\nsaldo atual" + p.getSaldo());
		p.setSaldo(p.getSaldo() + valor);
		
	}

	@Override
	public void sacar(int id, String nome, double valor) throws RemoteException {
		Date date = new Date();
		Pessoa p = lista.get(id-1);
		if(p.getUltimoSaque() == null) {
			if(p.getSaldo() < valor) {
				JOptionPane.showMessageDialog(null,"Pobre!");
			}
			else {
				p.setSaldo(p.getSaldo() - valor);
				p.setUltimoSaque(date);
			}
		}
		else if(p.getUltimoSaque()!= null) {

			if(date.getTime() - p.getUltimoSaque().getTime() < 120000) {
				System.out.println("Não passou dois minutos");
			}//120000
			else {
				p.setSaldo(p.getSaldo() - valor);
				p.setUltimoSaque(date);
			}
		}//data/hora pc
		//Data atual - ultimoSaque verificar se passou 2 min.,
	}


}
