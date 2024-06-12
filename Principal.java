import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

public class Principal {
    public static void main(String[] args) {
        // 3.1 - Inserir todos os funcionários
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, Month.JANUARY, 15), new BigDecimal("3000.00"), "Analista"));
        funcionarios.add(new Funcionario("Maria", LocalDate.of(1985, Month.MARCH, 20), new BigDecimal("3500.00"), "Gerente"));
        funcionarios.add(new Funcionario("Pedro", LocalDate.of(1988, Month.DECEMBER, 10), new BigDecimal("2800.00"), "Programador"));

        // 3.2 - Remover o funcionário "João"
        funcionarios.removeIf(funcionario -> funcionario.getNome().equals("João"));

        // 3.3 - Imprimir todos os funcionários
        System.out.println("Funcionários:");
        funcionarios.forEach(System.out::println);

        // 3.4 - Aumentar salário em 10%
        funcionarios.forEach(funcionario -> funcionario.aumentarSalario(new BigDecimal("0.1")));

        // 3.5 - Agrupar os funcionários por função em um MAP
        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        // 3.6 - Imprimir os funcionários agrupados por função
        System.out.println("\nFuncionários por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println(funcao + ":");
            lista.forEach(System.out::println);
        });

        // 3.8 - Imprimir os funcionários que fazem aniversário no mês 10 e 12
        System.out.println("\nFuncionários com aniversário em outubro e dezembro:");
        funcionarios.stream()
                .filter(funcionario -> funcionario.getDataNascimento().getMonthValue() == 10 ||
                        funcionario.getDataNascimento().getMonthValue() == 12)
                .forEach(System.out::println);

        // 3.9 - Imprimir o funcionário com a maior idade
        System.out.println("\nFuncionário com a maior idade:");
        Funcionario maisVelho = Collections.max(funcionarios, Funcionario.comparadorIdade);
        System.out.println("Nome: " + maisVelho.getNome() + ", Idade: " +
                maisVelho.getDataNascimento().until(LocalDate.now()).getYears());

        // 3.10 - Imprimir a lista de funcionários por ordem alfabética
        System.out.println("\nFuncionários em ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);

        // 3.11 - Imprimir o total dos salários dos funcionários
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        System.out.println("\nTotal dos salários: " + totalSalarios);

        // 3.12 - Imprimir quantos salários mínimos ganha cada funcionário
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("\nSalários mínimos dos funcionários:");
        funcionarios.forEach(funcionario -> {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_DOWN);
            System.out.println(funcionario.getNome() + ": " + salariosMinimos);
        });

    }
}
