package br.com.edusync.api.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "tb_funcionario_responsavel")
public class FuncionarioResponsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer codigo;

    @Column(length = 255)
    private String nome;

    @Column(length = 50)
    private String cpf;

    @Column(length = 20)
    private String cargo;

}
