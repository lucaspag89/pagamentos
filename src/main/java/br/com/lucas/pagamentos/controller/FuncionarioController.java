package br.com.lucas.pagamentos.controller;

import br.com.lucas.pagamentos.dto.funcionario.FuncionarioDto;
import br.com.lucas.pagamentos.dto.funcionario.FuncionarioResDto;
import br.com.lucas.pagamentos.service.funcionario.FuncionarioService;
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
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    FuncionarioService funcionarioService;

    @ApiOperation(value = "Retorna uma lista de funcionarios cadastrados")
    @GetMapping()
    public ResponseEntity<List<FuncionarioResDto>> findAllFuncionarios() {

        try {

            List<FuncionarioResDto> funcionarios = funcionarioService.findAllFuncionarios();

            if (funcionarios.size() > 0) {
                return new ResponseEntity<>(funcionarios, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(value = "Retorna funcionário cadastrado pelo id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<FuncionarioResDto> findByFuncionarioId(@PathVariable("id") Long id) {

        try {

            FuncionarioResDto funcionario = funcionarioService.findByFuncionarioId(id);

            if (funcionario != null) {
                return new ResponseEntity<>(funcionario, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(value = "Registra um novo funcionário com uma conta atrelada")
    @PostMapping()
    public ResponseEntity<FuncionarioResDto>
    registerFuncionario(@RequestBody FuncionarioDto funcionarioDto) {

        try {
            if (funcionarioDto != null) {

                FuncionarioResDto funcionario = funcionarioService.registerFuncionario(funcionarioDto);

                if (funcionario != null) {

                    return new ResponseEntity<>(funcionario, HttpStatus.CREATED);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Atualiza os dados do funcionário cadastrado pelo id")
    @PutMapping(value = "/{id}")
    public ResponseEntity<FuncionarioResDto> updateFuncionario(
            @PathVariable("id") Long id, @RequestBody FuncionarioDto funcionarioDto) {

        try {

            if (funcionarioDto != null) {

                FuncionarioResDto funcionario = funcionarioService.updateFuncionario(id, funcionarioDto);

                if (funcionario != null) {
                    return new ResponseEntity<>(funcionario, HttpStatus.OK);

                } else {
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);

                }
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(value = "Exclui o cadastro do funcionário pelo id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> removeFuncionario(@PathVariable("id") Long id) {

        try {

            Boolean funcionario = funcionarioService.removeFuncionario(id);

            if (funcionario) {
                return new ResponseEntity<>(true, HttpStatus.NO_CONTENT);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
