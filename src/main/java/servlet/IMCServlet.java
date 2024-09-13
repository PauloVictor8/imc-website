package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/calculateimc")
public class IMCServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		double peso = Float.parseFloat(req.getParameter("peso"));
		double altura = Float.parseFloat(req.getParameter("altura"));
		IMC imc = new IMC(peso, altura);
		double valor = imc.getValor();
		String valorFormatado = String.format("%.2f", valor);
		String resultado = imc.getResultado();
		
		PrintWriter out = resp.getWriter();
		out.print("<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "<head>\r\n"
				+ "<meta charset=\"UTF-8\">\r\n"
				+ "<title>IMC</title>\r\n"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"./styles.css\">"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "	<form>\r\n"
				+ "		<ul>"
				+ "			<li> " + "Peso: " + peso + " kg\r\n"
				+ "			<li> " + "Altura: " + altura + " m\r\n"
				+ "			<li> " + "Resultado: " + valorFormatado + " kg/m2\r\n"
				+ "			<li> " + resultado + "\r\n"
				+ "		</ul>"
				+ "		<br/>"
				+ "		<a href=\"index.html\">voltar</a>\r\n"
				+ "	</form>\r\n"
				+ "</body>\r\n"
				+ "</html>");
	}
	
	public class IMC {
		private double altura;
		private double peso;
		private double valor;
		private String resultado;
		
		IMC(double peso, double altura) {
			this.peso = peso;
			this.altura = altura;
			
			this.setValor();
		}
		
		void setValor() {
			this.valor = peso / (altura * altura);
		}
		
		double getValor() {
			return this.valor;
		}
		
		String getResultado() {
			double valor = this.valor;
			
			if (valor < 18.5) {
			    this.resultado = "Baixo peso";
			} else if (valor >= 18.5 && valor < 24.9) {
			    this.resultado = "Peso adequado";
			} else if (valor >= 25 && valor < 29.9) {
			    this.resultado = "Sobrepeso";
			} else if (valor >= 30 && valor < 34.9) {
			    this.resultado = "Obesidade grau 1";
			} else if (valor >= 35 && valor < 39.9) {
			    this.resultado = "Obesidade grau 2\r\n";
			} else if (valor >= 40) {
			    this.resultado = "Obesidade extrema";
			}
			
			return this.resultado;
		}
	}

}
