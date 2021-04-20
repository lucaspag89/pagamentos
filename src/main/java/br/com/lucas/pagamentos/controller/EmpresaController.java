package br.com.lucas.pagamentos.controller;

import br.com.lucas.pagamentos.dto.empresa.EmpresaDto;
import br.com.lucas.pagamentos.dto.empresa.EmpresaResDto;
import br.com.lucas.pagamentos.service.empresa.EmpresaService;
import io.swagger.annotations.ApiModelProperty;
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
@RequestMapping("/api/empresas")
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @ApiOperation(value = "Retorna uma lista de empresas cadastradas")
    @GetMapping()
    public ResponseEntity<List<EmpresaResDto>> findAllEmpresas() {

        try {

            List<EmpresaResDto> empresas = empresaService.findAllEmpresas();

            if (empresas.size() > 0) {
                return new ResponseEntity<>(empresas, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(value = "Retorna empresa cadastrada pelo id")
    @GetMapping(value = "/{id}")
    public ResponseEntity<EmpresaResDto> findEmpresaById(@PathVariable("id") Long id) {

        try {

            EmpresaResDto empresa = empresaService.findByEmpresaId(id);

            if (empresa != null) {
                return new ResponseEntity<>(empresa, HttpStatus.OK);

            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @ApiOperation(value = "Registra uma nova empresa com uma conta atrelada")
    @PostMapping()
    public ResponseEntity<EmpresaResDto> registerEmpresa(@RequestBody EmpresaDto empresaDto) {

        try {

            if (empresaDto != null) {
                EmpresaResDto empresa = empresaService.registerEmpresa(empresaDto);

                if (empresa != null) {
                    return new ResponseEntity<>(empresa, HttpStatus.CREATED);

                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Atualiza os dados da empresa cadastrada pelo id")
    @PutMapping(value = "/{id}")
    public ResponseEntity<EmpresaResDto> updateEmpresa(
            @PathVariable("id") Long id, @RequestBody EmpresaDto empresaDto) {

        try {

            if (empresaDto != null) {
                EmpresaResDto empresa = empresaService.updateEmpresa(id, empresaDto);

                if (empresa != null) {
                    return new ResponseEntity<>(empresa, HttpStatus.OK);

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

    @ApiOperation(value = "Exclui o cadastro da empresa pelo id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> removeEmpresa(@PathVariable("id") Long id) {

        try {

            Boolean empresa = empresaService.removeEmpresa(id);

            if (empresa) {
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
