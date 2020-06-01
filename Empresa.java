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
			System.out.println("Não existem filiais cadastradas!");
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
		System.out.println("\n" + "Nome da empresa: " + this.nomeDaEmpresa + "\n" + "CNPJ da empresa: " + this.cnpj + "\n" + "Data de abertura da empresa: " + this.dataAberturaEmpresa + "\n" + "Faturamento anual da empresa: R$ " + faturamentoAnualEmpresa + "\n" + "Balanço anual da empresa: R$ " + balancoAnualEmpresa + "\n" + "Média salarial da empresa: R$ " + mediaSalarioEmpresa + "\n");

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
			System.out.println("A empresa " + this.nomeDaEmpresa + " teve faturamento negativo. Nomes dos sócios: ");
			
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
			System.out.println("\n" + "Nome completo do colaborador: " + this.nomeCompletoColaborador + "\n" + "Matrícula do colaborador: " + this.matriculaColaborador + "\n" + "Salário anual do colaborador: R$ " + this.salarioAnualColaborador);
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
			System.out.println("\n" + "Nome do responsável pela filial: " + this.nomeResponsavelFilial + "\n" + "Faturamento Anual da filial: " + this.faturamentoAnualFilial  + "\n" + "Endereço da filial: " + this.enderecoFilial + "\n" + "Data de inauguração da filial: " + this.dataInauguracaoFilial + "\n");
		}
	}

	public static void main(String[] args) {
		
		// Criando empresa, sócios e filiais
		Empresa empresa01 = new Empresa();
		empresa01.setDadosEmpresa("Nome da empresa", "CNPJ", "Data de abertura", 00000);

		Empresa.Socios socio01 = empresa01.new Socios();
		socio01.setDadosSocios("Nome completo sócio 01", "CPF sócio 01", "Telefone sócio 01", "Data nascimento sócio 01");

		Empresa.Socios socio02 = empresa01.new Socios();
		socio02.setDadosSocios("Nome completo sócio 02", "CPF sócio 02", "Telefone sócio 02", "Data nascimento sócio 02");

		Empresa.Filiais filial01 = empresa01.new Filiais();
		filial01.setDadosFiliais("Nome da filial 01", "Nome responsável filial 01", 20000, "Endereço filial 01", "Código filial 01", "Data inauguração filial 01");

		Empresa.Filiais filial02 = empresa01.new Filiais();
		filial02.setDadosFiliais("Nome da filial 02", "Nome responsável filial 02", 30000, "Endereço filial 02", "Código filial 02", "Data inauguração filial 02");

		Empresa.Filiais filial03 = empresa01.new Filiais();
		filial03.setDadosFiliais("Nome da filial 03", "Nome responsável filial 03", 40000, "Endereço filial 03", "Código filial 03", "Data inauguração filial 03");

		// Colaboradores da sede
		Empresa.Colaboradores colaborador01 = empresa01.new Colaboradores();
		Empresa.Colaboradores colaborador02 = empresa01.new Colaboradores();
		Empresa.Colaboradores colaborador03 = empresa01.new Colaboradores();
		colaborador01.setDadosColaborador("Matrícula colaborador 01", "Nome completo colaborador 01", 200);
		colaborador02.setDadosColaborador("Matrícula colaborador 02", "Nome completo colaborador 02", 300);
		colaborador03.setDadosColaborador("Matrícula colaborador 03", "Nome completo colaborador 03", 400);

		// Colaboradores das filiais
		Filiais.ColaboradoresFiliais colaborador04 = filial01.new ColaboradoresFiliais();
		Filiais.ColaboradoresFiliais colaborador05 = filial01.new ColaboradoresFiliais();
		Filiais.ColaboradoresFiliais colaborador06 = filial01.new ColaboradoresFiliais();
		colaborador04.setDadosColaborador("Matrícula colaborador 04", "Nome completo colaborador 04", 200);
		colaborador05.setDadosColaborador("Matrícula colaborador 05", "Nome completo colaborador 05", 201);
		colaborador06.setDadosColaborador("Matrícula colaborador 06", "Nome completo colaborador 06", 202);

		Filiais.ColaboradoresFiliais colaborador07 = filial02.new ColaboradoresFiliais();
		Filiais.ColaboradoresFiliais colaborador08 = filial02.new ColaboradoresFiliais();
		Filiais.ColaboradoresFiliais colaborador09 = filial02.new ColaboradoresFiliais();
		colaborador07.setDadosColaborador("Matrícula colaborador 07", "Nome completo colaborador 04", 100);
		colaborador08.setDadosColaborador("Matrícula colaborador 08", "Nome completo colaborador 05", 101);
		colaborador09.setDadosColaborador("Matrícula colaborador 09", "Nome completo colaborador 06", 102);

		empresa01.getDadosEmpresa();
		empresa01.getListaComparativaFaturamento();
		filial01.getDadosFiliais();
		filial02.getDadosFiliais();
		filial03.getDadosFiliais();
	}    
}