/*
 * Atividade 1 de POO
 * Nomes dos integrantes: Julio Cesar C. S., Guilherme Ramos, Fabiano
 */
package escola;
import javax.swing.JOptionPane;
import entities.*;
import model.exceptions.*;
import static entities.Alunos.*;
import static entities.Pessoas.*;
import static entities.Professor.*;
import static entities.Tecnico.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Escola {

	public static void main(String[] args) {
		String RA, IRA, departamento, materia = null, data, nome, cpf = null, telefone = null, verificar;
		int op = 0, op2 = 0, cod = 0;
		Alunos aluno;
		Tecnico tecnico;
		Professor professor;
		LocalDate dataNascimento = null;
		boolean dataValida = false;
		boolean op4 = true, status = false, existe;

		List<Alunos> ListAlunos = new ArrayList<>();
		List<Tecnico> ListTecnico = new ArrayList<>();
		List<Professor> ListProfessor = new ArrayList<>();

		String[] opcoes = { "Sair", "Cadastrar", "Editar", "Consultar" };
		String[] opcoes1 = { "Sair", "Aluno\n", "Tecnico Adm\n", "Professor" };
		String[] opcoes2 = { "Sair", "Aluno\n", "Tecnico Adm\n", "Professor", "Exibir todos" };

		String nomeCompleto = JOptionPane.showInputDialog("Digite seu nome completo:");
		final String senhaGerada = gerarSenha(nomeCompleto);

		boolean autenticado = false;
		while (!autenticado) {
			String senhaDigitada = JOptionPane.showInputDialog("Digite a senha:");

			if (senhaDigitada.equals(senhaGerada)) {
				autenticado = true;
				JOptionPane.showMessageDialog(null, "Senha correta! Acesso permitido."); //
			} else {
				int opcao = JOptionPane.showConfirmDialog(null, "Senha incorreta! Deseja tentar novamente?",
						"Erro de Autenticação", JOptionPane.YES_NO_OPTION);
				if (opcao == JOptionPane.NO_OPTION) {
					encerrarPrograma();
				}
			}
		}

		do {
			op = JOptionPane.showOptionDialog(null, // Componente pai (null para centralizar na tela)
					"Selecione a opção desejada:", // Mensagem para exibir
					"\tPagina inicial:", // Título da janela
					JOptionPane.DEFAULT_OPTION, // Tipo de opções (DEFAULT_OPTION exibe botões OK/CANCELAR)
					JOptionPane.PLAIN_MESSAGE, // Tipo de mensagem (PLAIN_MESSAGE exibe somente o texto)
					null, // Ícone personalizado (null para usar o padrão)
					opcoes, // Opções disponíveis
					opcoes[0] // Opção padrão selecionada
			);
			switch (op) {
			case 0:
				encerrarPrograma(); // funcao que encerra o programa
				break;
			case 1:
				op2 = JOptionPane.showOptionDialog(null,
						"Selecione para cadastrar:", 
						"Pagina de cadastro:", 
						JOptionPane.DEFAULT_OPTION, 
						JOptionPane.PLAIN_MESSAGE, 
						null, 
						opcoes1, 
						opcoes1[0] 
				);
				switch (op2) {
				case 0:
					encerrarPrograma();
					break;
				case 1:
					nome = JOptionPane.showInputDialog("Digite o nome do aluno (cadastro): ");
					// VALIDAÇÃO DO CPF (se é falso ou se ja esta cadastrado)
					while (!status) {
						try {
							cpf = JOptionPane.showInputDialog("Digite o cpf do Aluno (cadastro): ");
							verificarCpf(ListAlunos, ListTecnico, ListProfessor, cpf);
							Pessoas.validaCpf(cpf);
							status = true;

						} catch (DomainException e) {
							JOptionPane.showMessageDialog(null, "O CPF não é valido ou ja esta cadastrado",
									"CPF INVALIDO", JOptionPane.ERROR_MESSAGE);
						}

					}
					status = false;
					// TELEFONE
					op4 = false;
					do {
						try {
							telefone = JOptionPane.showInputDialog("Digite o telefone fixo do aluno (cadastro): ");
							validaTel(telefone);
							op4 = true;
						} catch (DomainException e) {
							JOptionPane.showMessageDialog(null, "Telefone inválido, digite novamente!",
									"TELEFONE INVALIDO", JOptionPane.ERROR_MESSAGE);
						}
					} while (!op4); // fim do do while
					// DATA
					while (!dataValida) {
						data = JOptionPane.showInputDialog("Digite a data de nascimento (formato: dd/MM/yyyy):");
						try {

							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
							dataNascimento = LocalDate.parse(data, formatter);
							dataIsAfter(dataNascimento);
							dataValida = true;

						} catch (DateTimeParseException e) {
							JOptionPane.showMessageDialog(null,
									"Formato de data inválido! Por favor, digite a data no formato dd/MM/yyyy.",
									"ERRO - FORMATO INVALIDO", JOptionPane.ERROR_MESSAGE);
						} catch (DomainException e) { // excessão personalizada
							JOptionPane.showMessageDialog(null,
									"Data posterior a data atual! Por favor, digite a data no formato dd/MM/yyyy.",
									"ERRO - FORMATO INVALIDO", JOptionPane.ERROR_MESSAGE);
						}

					}
					dataValida = false;

					RA = JOptionPane.showInputDialog("Digite o RA (cadastro): ");

					IRA = JOptionPane.showInputDialog(" Digite O IRA (cadastro): ");

					aluno = new Alunos(nome, cpf, dataNascimento, telefone, IRA, RA);
					ListAlunos.add(aluno);
					JOptionPane.showMessageDialog(null, "ALUNO CADASTRADO COM SUCESSO");

					break;

				// CADASTRO DO TECNICO
				case 2:
					nome = JOptionPane.showInputDialog("Digite o nome do Tecnico (cadastro): ");
					// CPF
					while (!status) {
						try {
							cpf = JOptionPane.showInputDialog("Digite o cpf do Tecnico (cadastro): ");
							verificarCpf(ListAlunos, ListTecnico, ListProfessor, cpf);
							Pessoas.validaCpf(cpf);
							status = true;

						} catch (DomainException e) {
							JOptionPane.showMessageDialog(null, "O CPF não é valido ou ja esta cadastrado",
									"CPF INVALIDO", JOptionPane.ERROR_MESSAGE);
						}

					}
					status = false;
					// TELEFONE
					op4 = false;
					do {
						try {
							telefone = JOptionPane.showInputDialog("Digite o telefone fixo do Tecnico (cadastro): ");
							validaTel(telefone);
							op4 = true;
						} catch (DomainException e) {
							JOptionPane.showMessageDialog(null, "Telefone inválido, digite novamente!",
									"TELEFONE INVALIDO", JOptionPane.ERROR_MESSAGE);
						}
					} while (op4 != true); // fim do do while

					// DATA
					while (!dataValida) {
						data = JOptionPane.showInputDialog("Digite a data de nascimento (formato: dd/MM/yyyy):");
						try {

							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
							dataNascimento = LocalDate.parse(data, formatter);
							dataIsAfter(dataNascimento);
							dataValida = true;

						} catch (DateTimeParseException e) {
							JOptionPane.showMessageDialog(null,
									"Formato de data inválido! Por favor, digite a data no formato dd/MM/yyyy.",
									"ERRO - FORMATO INVALIDO", JOptionPane.ERROR_MESSAGE);
						} catch (DomainException e) {
							JOptionPane.showMessageDialog(null,
									"Data posterior a data atual! Por favor, digite a data no formato dd/MM/yyyy.",
									"ERRO - FORMATO INVALIDO", JOptionPane.ERROR_MESSAGE);
						}
					}
					dataValida = false;
					cod = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do Tecnico (cadastro): "));
					departamento = JOptionPane.showInputDialog("Digite o departamento do Tecnico (cadastro): ");
					tecnico = new Tecnico(nome, cpf, dataNascimento, telefone, cod, departamento);
					ListTecnico.add(tecnico);
					JOptionPane.showMessageDialog(null, "TECNICO ADM CADASTRADO COM SUCESSO");
					break;
				case 3:
					nome = JOptionPane.showInputDialog("Digite o nome do Professor (cadastro): ");
					// CPF
					while (!status) {
						try {
							cpf = JOptionPane.showInputDialog("Digite o cpf do Professor (cadastro): ");
							verificarCpf(ListAlunos, ListTecnico, ListProfessor, cpf);
							Pessoas.validaCpf(cpf);
							status = true;

						} catch (DomainException e) {
							JOptionPane.showMessageDialog(null, "O CPF não é valido ou ja esta cadastrado",
									"CPF INVALIDO", JOptionPane.ERROR_MESSAGE);
						}

					}
					status = false;
					// TELEFONE
					op4 = false;
					do {
						try {
							telefone = JOptionPane.showInputDialog("Digite o telefone fixo do Professor (cadastro): ");
							validaTel(telefone);
							op4 = true;
						} catch (DomainException e) {
							JOptionPane.showMessageDialog(null, "Telefone inválido, digite novamente!",
									"TELEFONE INVALIDO", JOptionPane.ERROR_MESSAGE);
						}
					} while (op4 != true); // fim do do while

					while (!dataValida) {
						data = JOptionPane.showInputDialog("Digite a data de nascimento (formato: dd/MM/yyyy):");
						try {

							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
							dataNascimento = LocalDate.parse(data, formatter);
							dataIsAfter(dataNascimento);
							dataValida = true;

						} catch (DateTimeParseException e) {
							JOptionPane.showMessageDialog(null,
									"Formato de data inválido! Por favor, digite a data no formato dd/MM/yyyy.",
									"ERRO - FORMATO INVALIDO", JOptionPane.ERROR_MESSAGE);
						} catch (DomainException e) {
							JOptionPane.showMessageDialog(null,
									"Data posterior a data atual! Por favor, digite a data no formato dd/MM/yyyy.",
									"ERRO - FORMATO INVALIDO", JOptionPane.ERROR_MESSAGE);
						}
					}
					dataValida = false;
					cod = Integer.parseInt(JOptionPane.showInputDialog("Digite o codigo do Professor (cadastro): "));
					materia = JOptionPane.showInputDialog("Digite a area de atuação do Professor (cadastro):");

					professor = new Professor(nome, cpf, dataNascimento, telefone, cod, materia);
					ListProfessor.add(professor);
					JOptionPane.showMessageDialog(null, "PROFESSOR CADASTRADO COM SUCESSO");
					break;
				}

				break;

			// EDITAR
			case 2:

				op2 = JOptionPane.showOptionDialog(null, 
						"Selecione para editar:", 
						"Pagina de edição:", 
						JOptionPane.DEFAULT_OPTION, 
						JOptionPane.PLAIN_MESSAGE,
						null, 
						opcoes1, 
						opcoes1[0] 
				);
				switch (op2) {
				case 0:
					encerrarPrograma();
					break;
				case 1:
					existe = false;
					verificar = JOptionPane.showInputDialog("Digite o CPF do aluno (editar):");
					for (Alunos x : ListAlunos) {
						if (x.getCpf().equals(verificar)) {
							existe = true;
							nome = JOptionPane.showInputDialog("Digite o novo nome do aluno (editar): ");

							// CPF
							x.setNome(nome);
							cpf = JOptionPane.showInputDialog("Digite o novo cpf do aluno (editar): ");
							if (!cpf.equals(x.getCpf())) {
								while (!status) {
									try {
										cpf = JOptionPane.showInputDialog("Digite novamente o cpf do Aluno (editar): ");
										if (!cpf.equals(x.getCpf()))
											verificarCpf(ListAlunos, ListTecnico, ListProfessor, cpf);
										Pessoas.validaCpf(cpf);
										status = true;

									} catch (DomainException e) {
										JOptionPane.showMessageDialog(null, "O CPF não é valido ou ja esta cadastrado",
												"CPF INVALIDO", JOptionPane.ERROR_MESSAGE);
									}

								}
								x.setCpf(cpf);
								status = false;

							}

							// TELEFONE

							op4 = false;
							do {
								try {
									telefone = JOptionPane
											.showInputDialog("Digite o novo telefone fixo do aluno (cadastro): ");
									validaTel(telefone);
									op4 = true;
								} catch (DomainException e) {
									JOptionPane.showMessageDialog(null, "Telefone inválido, digite novamente!",
											"TELEFONE INVALIDO", JOptionPane.ERROR_MESSAGE);
								}
							} while (op4 != true); // fim do do while

							x.setTelefone(telefone);
							while (!dataValida) {
								data = JOptionPane
										.showInputDialog("Digite a nova data de nascimento (formato: dd/MM/yyyy):");
								try {

									DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
									dataNascimento = LocalDate.parse(data, formatter);
									dataIsAfter(dataNascimento);
									dataValida = true;

								} catch (DateTimeParseException e) {
									JOptionPane.showMessageDialog(null,
											"Formato de data inválido! Por favor, digite a data no formato dd/MM/yyyy.",
											"ERRO - FORMATO INVALIDO", JOptionPane.ERROR_MESSAGE);
								} catch (DomainException e) {
									JOptionPane.showMessageDialog(null,
											"Data posterior a data atual! Por favor, digite a data no formato dd/MM/yyyy.",
											"ERRO - FORMATO INVALIDO", JOptionPane.ERROR_MESSAGE);
								}
							}
							dataValida = false;

							x.setDataNasc(dataNascimento);
							RA = JOptionPane.showInputDialog("Digite o novo RA do aluno (editar): ");
							x.setRA(RA);
							IRA = JOptionPane.showInputDialog(" Digite o novo IRA do aluno (editar): ");
							x.setIRA(IRA);

							JOptionPane.showMessageDialog(null, "DADOS DO ALUNO EDITADO COM SUCESSO");

						}
					}
					if (!existe) {
						JOptionPane.showMessageDialog(null, "O cpf digitado nao esta cadastrado", "ERRO",
								JOptionPane.ERROR_MESSAGE);
					}
					break;
				// ´Tecnico - EDITAR
				case 2:
					existe = false;
					verificar = JOptionPane.showInputDialog("Digite o novo CPF do tecnico (editar):");
					for (Tecnico x : ListTecnico) {
						if (x.getCpf().equals(verificar)) {
							existe = true;

							nome = JOptionPane.showInputDialog("Digite o novo nome do Tecnico (editar)");
							// CPF
							x.setNome(nome);
							cpf = JOptionPane.showInputDialog("Digite o novo CPF do tecnico (editar):");
							if (!cpf.equals(x.getCpf())) {
								while (!status) {
									try {
										cpf = JOptionPane
												.showInputDialog("Digite novamente o cpf do Tecnico (editar): ");
										if (!cpf.equals(x.getCpf()))
											verificarCpf(ListAlunos, ListTecnico, ListProfessor, cpf);
										Pessoas.validaCpf(cpf);
										status = true;

									} catch (DomainException e) {
										JOptionPane.showMessageDialog(null, "O CPF não é valido ou ja esta cadastrado",
												"CPF INVALIDO", JOptionPane.ERROR_MESSAGE);
									}

								}
								x.setCpf(cpf);
								status = false;

							}

							// TELEFONE
							op4 = false;
							do {
								try {
									telefone = JOptionPane
											.showInputDialog("Digite o novo telefone fixo do Tecnico (editar): ");
									validaTel(telefone);
									op4 = true;
								} catch (DomainException e) {
									JOptionPane.showMessageDialog(null, "Telefone inválido, digite novamente!",
											"TELEFONE INVALIDO", JOptionPane.ERROR_MESSAGE);
								}
							} while (op4 != true); // fim do do while
							op4 = true;

							x.setTelefone(telefone);
							while (!dataValida) {
								data = JOptionPane
										.showInputDialog("Digite a nova data de nascimento (formato: dd/MM/yyyy):");
								try {

									DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
									dataNascimento = LocalDate.parse(data, formatter);
									dataIsAfter(dataNascimento);
									dataValida = true;

								} catch (DateTimeParseException e) {
									JOptionPane.showMessageDialog(null,
											"Formato de data inválido! Por favor, digite a data no formato dd/MM/yyyy.",
											"ERRO - FORMATO INVALIDO", JOptionPane.ERROR_MESSAGE);
								} catch (DomainException e) {
									JOptionPane.showMessageDialog(null,
											"Data posterior a data atual! Por favor, digite a data no formato dd/MM/yyyy.",
											"ERRO - FORMATO INVALIDO", JOptionPane.ERROR_MESSAGE);
								}
							}
							dataValida = false;
							x.setDataNasc(dataNascimento);
							cod = Integer
									.parseInt(JOptionPane.showInputDialog("Digite o novo codigo do Tecnico(editar):"));
							x.setCod(cod);
							departamento = JOptionPane
									.showInputDialog("Digite o novo departamento do Tecnico(editar):");
							x.setDepartamento(departamento);
							JOptionPane.showMessageDialog(null, "DADOS DO TECNICO ADM EDITADO COM SUCESSO");

							break;
						}
					}
					if (!existe) {
						JOptionPane.showMessageDialog(null, "O cpf digitado nao esta cadastrado", "ERRO",
								JOptionPane.ERROR_MESSAGE);
					}

					break;

				// Professor - EDITAR
				case 3:
					existe = false;
					verificar = JOptionPane.showInputDialog("Digite o novo cpf do Professor(editar):");
					for (Professor x : ListProfessor) {

						if (x.getCpf().equals(verificar)) {
							existe = true;

							nome = JOptionPane.showInputDialog("Digite o novo nome do Professor(editar):");
							// CPF
							x.setNome(nome);

							cpf = JOptionPane.showInputDialog("Digite o novo cpf do Professor(editar):");
							if (!cpf.equals(x.getCpf())) {
								cpf = JOptionPane.showInputDialog("Digite o cpf do Professor(editar):");
								while (!status) {
									try {
										cpf = JOptionPane
												.showInputDialog("Digite novamente cpf do Professor (editar): ");
										if (!cpf.equals(x.getCpf()))
											verificarCpf(ListAlunos, ListTecnico, ListProfessor, cpf);
										Pessoas.validaCpf(cpf);
										status = true;

									} catch (DomainException e) {
										JOptionPane.showMessageDialog(null, "O CPF não é valido ou ja esta cadastrado",
												"CPF INVALIDO", JOptionPane.ERROR_MESSAGE);
									}

								}

								status = false;

							}
							x.setCpf(cpf);

							// TELEFONE
							op4 = false;
							do {
								try {
									telefone = JOptionPane
											.showInputDialog("Digite o novo telefone fixo do Professor (editar): ");
									validaTel(telefone);
									op4 = true;
								} catch (DomainException e) {
									JOptionPane.showMessageDialog(null, "Telefone inválido, digite novamente!",
											"TELEFONE INVALIDO", JOptionPane.ERROR_MESSAGE);
								}
							} while (op4 != true); // fim do do while

							x.setTelefone(telefone);
							while (!dataValida) {
								data = JOptionPane
										.showInputDialog("Digite a nova data de nascimento (formato: dd/MM/yyyy):");
								try {

									DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
									dataNascimento = LocalDate.parse(data, formatter);
									dataIsAfter(dataNascimento);
									dataValida = true;

								} catch (DateTimeParseException e) {
									JOptionPane.showMessageDialog(null,
											"Formato de data inválido! Por favor, digite a data no formato dd/MM/yyyy.",
											"ERRO - FORMATO INVALIDO", JOptionPane.ERROR_MESSAGE);
								} catch (DomainException e) {
									JOptionPane.showMessageDialog(null,
											"Data posterior a data atual! Por favor, digite a data no formato dd/MM/yyyy.",
											"ERRO - FORMATO INVALIDO", JOptionPane.ERROR_MESSAGE);
								}
							}
							dataValida = false;
							x.setDataNasc(dataNascimento);
							materia = JOptionPane.showInputDialog("Digite a nova materia disciplinada pelo Professor");
							x.setMateria(materia);
							cod = Integer.parseInt(JOptionPane.showInputDialog("Digite o novo codigo do Professor"));
							x.setCod(cod);
							JOptionPane.showMessageDialog(null, "DADOS DO PROFESSOR EDITADO COM SUCESSO");

							break;

						}
					}
					if (!existe) {
						JOptionPane.showMessageDialog(null, "O cpf digitado nao esta cadastrado", "ERRO",
								JOptionPane.ERROR_MESSAGE);
					}
					break;
				}
				break;

			case 3:
				op2 = JOptionPane.showOptionDialog(null, 
						"Selecione para consultar:", 
						"Pagina de consulta:", 
						JOptionPane.DEFAULT_OPTION, 
						JOptionPane.PLAIN_MESSAGE, 
						null, 
						opcoes2, 
						opcoes2[0]
				);
				switch (op2) {
				case 0:
					encerrarPrograma();
					break;
				case 1:
					try {
						verificar = JOptionPane.showInputDialog("Digite o CPF do aluno (consulta):");
						checkUsuario(ListAlunos, verificar);
					} catch (DomainException e) {
						JOptionPane.showMessageDialog(null, "O cpf digitado nao esta cadastrado", "ERRO",
								JOptionPane.ERROR_MESSAGE);
					}

					break;
				case 2:
					existe = false;
					verificar = JOptionPane.showInputDialog("Digite o CPF do Tecnico (consulta):");
					for (Tecnico x : ListTecnico) {
						if (x.getCpf().equals(verificar)) {
							existe = true;
							JOptionPane.showMessageDialog(null, x.toString(), "USUARIO ENCONTRADO",
									JOptionPane.INFORMATION_MESSAGE);
						}

					}
					if (!existe) {
						JOptionPane.showMessageDialog(null, "O cpf digitado nao esta cadastrado", "ERRO",
								JOptionPane.ERROR_MESSAGE);
					}
					break;
				case 3:

					existe = false;
					verificar = JOptionPane.showInputDialog("Digite o CPF do Professor (consulta):");
					for (Professor x : ListProfessor) {
						if (x.getCpf().equals(verificar)) {
							existe = true;
							JOptionPane.showMessageDialog(null, x.toString(), "USUARIO ENCONTRADO",
									JOptionPane.INFORMATION_MESSAGE);
						}

					}
					if (!existe) {
						JOptionPane.showMessageDialog(null, "O cpf digitado nao esta cadastrado", "ERRO",
								JOptionPane.ERROR_MESSAGE);
					}
					break;
				case 4:
					for (Professor x : ListProfessor) {
						JOptionPane.showMessageDialog(null, x.toString(), "Dados Professor: " + x.getNome(),
								JOptionPane.INFORMATION_MESSAGE);
					}
					for (Tecnico x : ListTecnico) {
						JOptionPane.showMessageDialog(null, x.toString(), "Dados do Tecnico: " + x.getNome(),
								JOptionPane.INFORMATION_MESSAGE);
					}
					for (Alunos x : ListAlunos) {
						JOptionPane.showMessageDialog(null, x.toString(), "Dados do Aluno: " + x.getNome(),
								JOptionPane.INFORMATION_MESSAGE);
					}

					break;
				}
				break;
			}

		} while (op != 0);
	}

	final public static String gerarSenha(String nomeCompleto) {
		String primeiroNome = nomeCompleto.split(" ")[0];
		String dataAtual = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyyyy"));
		return primeiroNome.toUpperCase() + dataAtual;
	}

	public static void encerrarPrograma() {
		JOptionPane.showMessageDialog(null, "Programa encerrado.");
		System.exit(0);
	}

}
