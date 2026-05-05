package entities;

import ultilities.Registravel;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class coordenador extends funcionarios implements Registravel {

    public coordenador(String name, Integer id) {
        super(name, id);
    }

    //Horas extras permitadas: 5hrs.
    @Override
    public void registrarPonto(LocalDateTime entrada, LocalDateTime saida) {

        if (saida.isBefore(entrada)) {
            throw new IllegalArgumentException("ERRO! A hora da saida nao pode ser antes da entrada.");
        }
        if(entrada.toLocalTime().isBefore(LocalTime.of(6, 0)) || saida.toLocalTime().isAfter(LocalTime.of(22,0))){
            throw new IllegalArgumentException("ERRO! A hora da saida nao pode ser antes da entrada.");
        }

        long minutosTrabalhados = Duration.between(entrada, saida).toMinutes();
        double horasTotais = (minutosTrabalhados / 60) - 1;

        double horasExtras = horasTotais - 8.0;
        if (horasExtras > 5) {
            throw new IllegalArgumentException("Erro! as horas totais desse trabalhador ultrapassou o permitido.");
        }else{
            System.out.println("Ponto registrado com sucesso: " + horasTotais + " horas líquidas.");
        }
    }

}
