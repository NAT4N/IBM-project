package com.MDCRIBM.api.model.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionConfig extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EmptyResultDataAccessException.class, ObjectNotFoundException.class})
    public ResponseEntity errorNotFound()
    {
        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity errorBadRequest()
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Remova o argumento ID do corpo do JSON");
    }
    @ExceptionHandler({IllegalStateException.class, NullPointerException.class})
    public ResponseEntity errorBadRequestMissArgs()
    {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                "Estao faltando argumentos no JSON !" +
                        "Lista de todos os argumentos abaixo" +
                        "\n{\n" +
                        "  \"nomeProduto\": \"string\"\n" +
                        "  \"cdPrograma\": \"string\",\n" +
                        "  \"cdSubPrograma\": \"string\",\n" +
                        "  \"cdFonteRecurso\": \"string\",\n" +
                        "  \"cdTipoSeguro\": \"string\",\n" +
                        "  \"cdEstado\": \"string\",\n" +
                        "  \"cdProduto\": \"string\",\n" +
                        "  \"cdMunicipio\": \"string\",\n" +
                        "  \"cdModalidade\": \"string\",\n" +
                        "  \"Municipio\": \"string\", \n" +
                        "  \"MesEmissao\": \"string\",\n" +
                        "  \"VlCusteio\": 0,\n" +
                        "  \"AnoEmissao\": \"string\",\n" +
                        "  \"Atividade\": \"string\",\n" +
                        "  \"AreaInvest\": 0,\n" +
                        "}\n" +
                        "Nenhum dos argumentos pode ser nulo !");
    }

}
