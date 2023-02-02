package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import entities.Contract;
import entities.Installment;
import services.ContractService;
import services.PaypalService;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Scanner sc = new Scanner(System.in);

		System.out.println("Entre os dados do contrato:");
		System.out.println("Numero:");
		int numero = sc.nextInt();
		System.out.println("Data (dd/MM/yyyy):");
		LocalDate date = LocalDate.parse(sc.next(), dtf);
		System.out.println("Valor do contrato:");
		double valorCont = sc.nextDouble();
		Contract obj = new Contract(numero, date, valorCont);

		System.out.println("Entre com o numero de parcelas:");
		int qtdParcelas = sc.nextInt();

		ContractService contractService = new ContractService(new PaypalService());
		contractService.processContract(obj, qtdParcelas);
		System.out.println("Parcelas:");
		for (Installment installment : obj.getInstallments()) {
			System.out.println(installment);
		}
		sc.close();
	}

}
