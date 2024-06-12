import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;

public class Funcionario extends Pessoa {
    private BigDecimal salario;
    private String funcao;

    public Funcionario(String nome, LocalDate dataNascimento, BigDecimal salario, String funcao) {
        super(nome, dataNascimento);
        this.salario = salario;
        this.funcao = funcao;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public String getFuncao() {
        return funcao;
    }

    public void aumentarSalario(BigDecimal percentual) {
        salario = salario.multiply(BigDecimal.ONE.add(percentual));
    }

    @Override
    public String toString() {
        return super.toString() + ", Salário: " + salario + ", Função: " + funcao;
    }

    // Comparador para encontrar o funcionário mais velho
    public static Comparator<Funcionario> comparadorIdade = Comparator.comparingInt(
            funcionario -> funcionario.getDataNascimento().until(LocalDate.now()).getYears());
}
