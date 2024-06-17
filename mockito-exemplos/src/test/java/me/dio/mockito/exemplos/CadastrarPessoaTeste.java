package me.dio.mockito.exemplos;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CadastrarPessoaTeste {

    @Mock
    private ApiDosCorreios apiDosCorreios;

    @InjectMocks
    private CadastrarPessoa cadastrarPessoa;

    @Test
    void validarDadosDeCadastro() {
        DadosLocalizacao dadosLocalizacao = new DadosLocalizacao("SC", "Indaial", "Rua 1", "Ap", "Tapajós");
        Mockito.when(apiDosCorreios.buscaDadosComBaseNoCep("89080160")).thenReturn(dadosLocalizacao);
        Pessoa matheus = cadastrarPessoa.cadastrarPessoa("Matheus", "123456789", LocalDate.now(), "89080160");

        Assertions.assertEquals("Matheus", matheus.getNome());
        Assertions.assertEquals("123456789", matheus.getDocumento());
        Assertions.assertEquals("SC", matheus.getEndereco().getUf());
        Assertions.assertEquals("Ap", matheus.getEndereco().getComplemento());
    }
}
