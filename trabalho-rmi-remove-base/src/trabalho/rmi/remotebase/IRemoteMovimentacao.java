package trabalho.rmi.remotebase;

import java.rmi.Remote;
import java.rmi.RemoteException;

import trabalho.rmi.servidor.Pessoa;

public interface IRemoteMovimentacao extends Remote{
	
	public void depositar(int id,String nome, double valor) throws RemoteException;
	
	public double consultar(int id, String nome, double saldo) throws RemoteException;
	
	public void sacar(int id,String nome, double valor) throws RemoteException;
	
	public void cadastrar(int id, String nome, double saldo) throws RemoteException;
	
	public void conectar(int id, String nome, double saldo) throws RemoteException;

}
