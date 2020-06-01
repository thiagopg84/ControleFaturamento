import java.util.*;
public class Empresa {

	protected String nomeDaEmpresa;
	protected String cnpj;
	protected String dataAberturaEmpresa;
	protected double mediaSalarioEmpresa;
	protected double faturamentoAnualSede;
	protected double totalEncargosEmpresa = 0;
	protected double faturamentoAnualEmpresa = 0;
	protected double numeroDeColaboradores = 0;
	double balancoAnualEmpresa;

	ArrayList<String> listaSocios = new ArrayList<>();
	ArrayList<String> listaNomesFiliais = new ArrayList<>();
	List<Filiais> listaFiliais = new ArrayList<>();
	
	public void getListaComparativaFaturamento() {
		
		if (listaFiliais.size() > 0) {
			Empresa.Filiais filiais =  Collections.max(listaFiliais, Comparator.comparing(s -> s.getFaturamento()));
	        System.out.println(filiais.nomeFilial + " foi a filial que mais faturou, com faturamento de " + filiais.getFaturamento());

	        filiais =  Collections.min(listaFiliais, Comparator.comparing(s -> s.getFaturamento()));
	        System.out.println(filiais.nomeFilial + " foi a filial que menos faturou, com faturamento de " + filiais.getFaturamento());	
		} else {
			System.out.println("N�o existem filiais cadastradas!");
		}
	}

	public void setDadosEmpresa(String nomeDaEmpresa, String cnpj, String dataAberturaEmpresa, double faturamentoAnualSede) {
		this.nomeDaEmpresa = nomeDaEmpresa;
		this.cnpj = cnpj;
		this.dataAberturaEmpresa = dataAberturaEmpresa;
		this.faturamentoAnualSede = faturamentoAnualSede;
						
		faturamentoAnualEmpresa += this.faturamentoAnualSede;
	}
	
	public void getDadosEmpresa() {
		this.balancoAnualEmpresa = faturamentoAnualEmpresa - totalEncargosEmpresa;
		this.mediaSalarioEmpresa = totalEncargosEmpresa / numeroDeColaboradores;
		System.out.println("\n" + "Nome da empresa: " + this.nomeDaEmpresa + "\n" + "CNPJ da empresa: " + this.cnpj + "\n" + "Data de abertura da empresa: " + this.dataAberturaEmpresa + "\n" + "Faturamento anual da empresa: R$ " + faturamentoAnualEmpresa + "\n" + "Balan�o anual da empresa: R$ " + balancoAnualEmpresa + "\n" + "M�dia salarial da empresa: R$ " + mediaSalarioEmpresa + "\n");

		if(listaNomesFiliais.size() > 0) {
			System.out.println("Lista de filiais: ");
			StringBuffer sb = new StringBuffer();
  		      for (String s : this.listaNomesFiliais) {
		         sb.append(s);
		         sb.append("\n");
		      }
		      String str = sb.toString();
		      System.out.println(str);
		}
		
		if (this.balancoAnualEmpresa < 0) {
			System.out.println("A empresa " + this.nomeDaEmpresa + " teve faturamento negativo. Nomes dos s�cios: ");
			
			StringBuffer sb = new StringBuffer();
		      for (String s : this.listaSocios) {
		         sb.append(s);
		         sb.append("\n");
		      }
		      String str = sb.toString();
		      System.out.println(str);
		}
	}

	public class Socios {
		protected String nomeCompletoSocio;
		protected String cpfSocio;
		protected String telefoneSocio;
		protected String dataNascimentoSocio;

		public void setDadosSocios(String nomeCompletoSocio, String cpfSocio, String telefoneSocio, String dataNascimentoSocio) {
			this.nomeCompletoSocio = nomeCompletoSocio;
			this.cpfSocio = cpfSocio;
			this.telefoneSocio = telefoneSocio;
			this.dataNascimentoSocio = dataNascimentoSocio;
			
			listaSocios.add(this.nomeCompletoSocio);
		}
	}

	public class Colaboradores {
		protected String matriculaColaborador;
		protected String nomeCompletoColaborador;
		protected double salarioAnualColaborador;

		public void setDadosColaborador(String matriculaColaborador, String nomeCompletoColaborador, double salarioAnualColaborador) {
			this.matriculaColaborador = matriculaColaborador;
			this.nomeCompletoColaborador = nomeCompletoColaborador;
			this.salarioAnualColaborador = salarioAnualColaborador;
			
			totalEncargosEmpresa += this.salarioAnualColaborador;
			numeroDeColaboradores++;
		}

		public void getDadosColaborador() {
			System.out.println("\n" + "Nome completo do colaborador: " + this.nomeCompletoColaborador + "\n" + "Matr�cula do colaborador: " + this.matriculaColaborador + "\n" + "Sal�rio anual do colaborador: R$ " + this.salarioAnualColaborador);
		}
	}

	public class Filiais {
		protected String nomeFilial;
		protected String nomeResponsavelFilial;
		protected double faturamentoAnualFilial;
		protected String enderecoFilial;
		protected String codigoFilial;
		protected String dataInauguracaoFilial;
		
		public class ColaboradoresFiliais extends Colaboradores {}

		public void setDadosFiliais (String nomeFilial, String nomeResponsavelFilial, double faturamentoAnualFilial, String enderecoFilial, String codigoFilial, String dataInauguracaoFilial) {
			this.nomeFilial = nomeFilial;
			this.nomeResponsavelFilial = nomeResponsavelFilial;
			this.faturamentoAnualFilial = faturamentoAnualFilial;
			this.enderecoFilial = enderecoFilial;
			this.codigoFilial = codigoFilial;
			this.dataInauguracaoFilial = dataInauguracaoFilial;
			
			listaNomesFiliais.add(this.nomeFilial);
			faturamentoAnualEmpresa += this.faturamentoAnualFilial;
			listaFiliais.add(this);
		}
		
		public double getFaturamento() {
			return this.faturamentoAnualFilial;
		}

		public void getDadosFiliais() {
			System.out.println("\n" + "Nome do respons�vel pela filial: " + this.nomeResponsavelFilial + "\n" + "Faturamento Anual da filial: " + this.faturamentoAnualFilial  + "\n" + "Endere�o da filial: " + this.enderecoFilial + "\n" + "Data de inaugura��o da filial: " + this.dataInauguracaoFilial + "\n");
		}
	}

	public static void main(String[] args) {
		
		// Criando empresa, s�cios e filiais
		Empresa empresa01 = new Empresa();
		empresa01.setDadosEmpresa("Nome da empresa", "CNPJ", "Data de abertura", 00000);

		Empresa.Socios socio01 = empresa01.new Socios();
		socio01.setDadosSocios("Nome completo s�cio 01", "CPF s�cio 01", "Telefone s�cio 01", "Data nascimento s�cio 01");

		Empresa.Socios socio02 = empresa01.new Socios();
		socio02.setDadosSocios("Nome completo s�cio 02", "CPF s�cio 02", "Telefone s�cio 02", "Data nascimento s�cio 02");

		Empresa.Filiais filial01 = empresa01.new Filiais();
		filial01.setDadosFiliais("Nome da filial 01", "Nome respons�vel filial 01", 20000, "Endere�o filial 01", "C�digo filial 01", "Data inaugura��o filial 01");

		Empresa.Filiais filial02 = empresa01.new Filiais();
		filial02.setDadosFiliais("Nome da filial 02", "Nome respons�vel filial 02", 30000, "Endere�o filial 02", "C�digo filial 02", "Data inaugura��o filial 02");

		Empresa.Filiais filial03 = empresa01.new Filiais();
		filial03.setDadosFiliais("Nome da filial 03", "Nome respons�vel filial 03", 40000, "Endere�o filial 03", "C�digo filial 03", "Data inaugura��o filial 03");

		// Colaboradores da sede
		Empresa.Colaboradores colaborador01 = empresa01.new Colaboradores();
		Empresa.Colaboradores colaborador02 = empresa01.new Colaboradores();
		Empresa.Colaboradores colaborador03 = empresa01.new Colaboradores();
		colaborador01.setDadosColaborador("Matr�cula colaborador 01", "Nome completo colaborador 01", 200);
		colaborador02.setDadosColaborador("Matr�cula colaborador 02", "Nome completo colaborador 02", 300);
		colaborador03.setDadosColaborador("Matr�cula colaborador 03", "Nome completo colaborador 03", 400);

		// Colaboradores das filiais
		Filiais.ColaboradoresFiliais colaborador04 = filial01.new ColaboradoresFiliais();
		Filiais.ColaboradoresFiliais colaborador05 = filial01.new ColaboradoresFiliais();
		Filiais.ColaboradoresFiliais colaborador06 = filial01.new ColaboradoresFiliais();
		colaborador04.setDadosColaborador("Matr�cula colaborador 04", "Nome completo colaborador 04", 200);
		colaborador05.setDadosColaborador("Matr�cula colaborador 05", "Nome completo colaborador 05", 201);
		colaborador06.setDadosColaborador("Matr�cula colaborador 06", "Nome completo colaborador 06", 202);

		Filiais.ColaboradoresFiliais colaborador07 = filial02.new ColaboradoresFiliais();
		Filiais.ColaboradoresFiliais colaborador08 = filial02.new ColaboradoresFiliais();
		Filiais.ColaboradoresFiliais colaborador09 = filial02.new ColaboradoresFiliais();
		colaborador07.setDadosColaborador("Matr�cula colaborador 07", "Nome completo colaborador 04", 100);
		colaborador08.setDadosColaborador("Matr�cula colaborador 08", "Nome completo colaborador 05", 101);
		colaborador09.setDadosColaborador("Matr�cula colaborador 09", "Nome completo colaborador 06", 102);

		empresa01.getDadosEmpresa();
		empresa01.getListaComparativaFaturamento();
		filial01.getDadosFiliais();
		filial02.getDadosFiliais();
		filial03.getDadosFiliais();
	}    
}