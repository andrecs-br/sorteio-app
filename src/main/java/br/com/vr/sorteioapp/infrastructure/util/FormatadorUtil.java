package br.com.vr.sorteioapp.infrastructure.util;


import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FormatadorUtil {

	public static final String DIA_MES_ANO = "dd/MM/yyyy";

	private FormatadorUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static String format(BigDecimal valor, int tamanhoAEsquerda, int tamanhoADireita) {
		if( valor == null ){
				return null;
		}
		
		String valorString = String.valueOf(valor);
		if( !valorString.contains(".") ){
			valorString = valorString + ".00";
		}
		String parteInteira = valorString.substring(0, valorString.indexOf('.'));
		String parteInteiraFormatada = StringUtils.leftPad(parteInteira, tamanhoAEsquerda, "0");
		String parteDecimal = valorString.substring(valorString.indexOf('.') + 1, valorString.length());
		String parteDecimalFormatada = StringUtils.rightPad(parteDecimal, tamanhoADireita, "0");

		return parteInteiraFormatada + parteDecimalFormatada;
	}

	public static String format(Integer valor, int tamanhoAEsquerda) {
		if( valor == null ){
			return null;
	}
		String valorString = String.valueOf(valor.intValue());
		return StringUtils.leftPad(valorString, tamanhoAEsquerda, "0");
	}

	public static String format(Long valor, int tamanhoAEsquerda) {
		if( valor == null ){
			return null;
	}
		return format(valor.intValue(), tamanhoAEsquerda);
	}
	
	public static String format(String valor, int tamanhoAEsquerda) {
		if( valor == null ){
			return null;
	}
		return StringUtils.leftPad(valor, tamanhoAEsquerda, "0");
	}
	
	/**
	 * Metodo responsavel por retornar uma data string de acordo com os parâmetros informados.
	 * @param date - Data a ser passada como parâmetro.
	 * @param inputPattern - Formatado de entrada da data informada no parâmetro.
	 * @param outputPattern - Formatado desejado na data retornada.
	 * @return String - Data formatada.
	 * @throws ParseException - Excecao ao formatar a data.
	 */
	public static String format(String date, final String inputPattern, final String outputPattern)
			throws ParseException {
		if (date == null || StringUtils.isBlank(date)) {
			return null;
		}
		DateFormat formatIn = new SimpleDateFormat(inputPattern);
		DateFormat formatOut = new SimpleDateFormat(outputPattern);
		
		Date dateOut = formatIn.parse(date);
		date = formatOut.format(dateOut);

		return date;
	}
	
	/**
	 * Metodo reponsavel por converter uma {@link Date} para {@link String} usando o
	 * padrao de formatacao default.(dd/MM/yyyy)
	 * 
	 * @param date
	 *            - Data string a ser convertida.
	 * @return {@link Date}
	 * @throws ParseException
	 *             - Excecao ao parsear a data.
	 */
	public static Date convertStringToDate(final String date) throws ParseException {
		if (date == null || StringUtils.isBlank(date)) {
			return null;
		}

		return new SimpleDateFormat(DIA_MES_ANO).parse(date);
	}
	
	/**
	 * Formatar Date para String
	 * @param date Date
	 * @return Data no formato padrao dd/MM/yyyy <br>Exemplo: <b>31/12/2016</b>
	 */
	public static String asString(Date date) {
		return asString(date, DIA_MES_ANO);
	}

	/**
	 * Formatar Date para String
	 * @param date Date
	 * @param pattern Formato para conversao da data
	 * @return Data no formato especificado
	 */
	public static String asString(Date date, String pattern) {
		if(date != null) {
			SimpleDateFormat formatter = new SimpleDateFormat(pattern, new Locale("pt", "BR"));
			return formatter.format(date);
		}

		return null;
	}
	
	
	public static String formatCNPJ(String cnpj, int tamanho) {
		return format(formatOnlyNumbers(cnpj), tamanho);
	}
	
	public static String formatCPF(String cpf, int tamanho) {
		return format(formatOnlyNumbers(cpf), tamanho);	
	}

	public static String maxLength(String valor, int maxLength) {
		if( StringUtils.isNotEmpty(valor) ){
			if( valor.length() > maxLength ){
				valor = valor.substring(0, maxLength);
			}
			return valor;
		}
		return null;
	}

	public static String formatOnlyNumbers(String text) {
		if( text != null ){
			return text.replaceAll("\\D+","");
		}
		return null;
	}


	public static boolean isValidDate(String text) {
		SimpleDateFormat format = new SimpleDateFormat(DIA_MES_ANO);
		try {
			format.setLenient(false);
			format.parse(text);
			return true;
		}
		catch(ParseException e){
			return false;
		}
	}
}

