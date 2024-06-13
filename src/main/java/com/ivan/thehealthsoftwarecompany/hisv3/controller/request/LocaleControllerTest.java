package com.ivan.thehealthsoftwarecompany.hisv3.controller.request;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/locale")
public class LocaleControllerTest {

    //Con Tag ,si se repiten los datos del texto y la desc se pueden agrupar las operations
    @Tag(name="Locale", description = "Métodos del locale, para test.")
    @Schema(name = "Nombre del esquema", description = "Descripcion del schema", example = "Texto del ejemplo del schema")
    @Operation(summary = "Hola con cambio de idioma", description = "A partir del LocaleInterceptor cambia el idioma")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "500", content = @Content(schema = @Schema()))
    })
    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @Tag(name="Locale", description = "Métodos del locale, para test.")
    @Operation(summary = "Hola con cambio de idioma", description = "A partir del LocaleInterceptor cambia el idioma")
    @GetMapping("/hello")
    public String hello1() {
        return "hello";
    }

    @Tag(name="Locale", description = "Métodos del locale, para test.")
    @Operation(summary = "Hola con cambio de idioma", description = "A partir del LocaleInterceptor cambia el idioma")
    @GetMapping("/hello/{id}")
    public String helloWithId(@Parameter(name = "id", description = "Number of hello", example = "1", required = true) @PathVariable int id) {
        return "hello: "+id;
    }
}
