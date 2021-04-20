package br.com.lucas.pagamentos.controller;

import br.com.lucas.pagamentos.dto.conta.ContaEmpresaDto;
import br.com.lucas.pagamentos.dto.conta.ContaEmpresaResDto;
import br.com.lucas.pagamentos.dto.conta.ContaFuncionarioDto;
import br.com.lucas.pagamentos.dto.conta.ContaFuncionarioResDto;
import br.com.lucas.pagamentos.service.conta.ContaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@RequestMapping("/api/contas")
public class ContaController {

    @Autowired
    ContaService contaService;

    @ApiOperation(value = "Retorna uma lista de contas das empresas cadastradas")
    @GetMapping(value = "/empresas")
    public ResponseEntity<List<ContaEmpresaResDto>> findAllContasEmpresas() {

        try {
            List<ContaEmpresaResDto> contaEmpresas = contaService.findAllContasEmpresas();

            if (contaEmpresas.size() > 0) {
                return new ResponseEntity<>(contaEmpresas, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(value = "Retorna a conta da empresa cadastrada pelo id da conta")
    @GetMapping(value = "/empresas/{id}")
    public ResponseEntity<ContaEmpresaResDto>
    findAllContasEmpresas(@PathVariable("id") Long id) {

        try {

            ContaEmpresaResDto contaEmpresa = contaService.findContaEmpresaById(id);

            if (contaEmpresa != null) {
                return new ResponseEntity<>(contaEmpresa, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(value = "Adiciona o valor do depósito à conta da empresa pelo id da conta")
    @PutMapping(value = "/empresas/{id}")
    public ResponseEntity<ContaEmpresaResDto>
    updateContaEmpresa(@PathVariable("id") Long id, @RequestBody ContaEmpresaDto contaEmpresaDto) {

        try {

            if (contaEmpresaDto != null) {
                ContaEmpresaResDto contaEmpresa = contaService.updateContaEmpresa(id, contaEmpresaDto);

                if (contaEmpresa != null) {
                    return new ResponseEntity<>(contaEmpresa, HttpStatus.OK);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Retorna uma lista de contas dos funcionários cadastrados")
    @GetMapping(value = "/funcionarios")
    public ResponseEntity<List<ContaFuncionarioResDto>> findAllContasFuncionarios() {

        try {

            List<ContaFuncionarioResDto> contaFuncionarios = contaService.findAllContasFuncionarios();

            if (contaFuncionarios.size() > 0) {
                return new ResponseEntity<>(contaFuncionarios, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(value = "Retorna a conta do funcionário cadastrado pelo id da conta")
    @GetMapping(value = "/funcionarios/{id}")
    public ResponseEntity<ContaFuncionarioResDto>
    findAllContasFuncionarios(@PathVariable("id") Long id) {

        try {

            ContaFuncionarioResDto contaFuncionario = contaService.findContaFuncionarioById(id);

            if (contaFuncionario != null) {
                return new ResponseEntity<>(contaFuncionario, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(contaFuncionario, HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(value = "Adiciona o valor do pagamento à conta do funcionário pelo id da conta")
    @PutMapping(value = "/funcionarios/{id}")
    public ResponseEntity<ContaFuncionarioResDto>
    updateContaFuncionario(@PathVariable("id") Long id,
                           @RequestBody ContaFuncionarioDto contaFuncionarioDto) {

        try {

            if (contaFuncionarioDto != null) {
                ContaFuncionarioResDto contaFuncionario =
                        contaService.updateContaFuncionario(id, contaFuncionarioDto);

                if (contaFuncionario != null) {
                    return new ResponseEntity<>(contaFuncionario, HttpStatus.OK);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


}
