package br.com.lucas.pagamentos.config.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/api/**"))
                .build()
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()))
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessageForGET())
                .globalResponseMessage(RequestMethod.POST, responseMessageForPOST())
                .globalResponseMessage(RequestMethod.PUT, responseMessageForPUT())
                .globalResponseMessage(RequestMethod.DELETE, responseMessageForDELETE())
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot REST API Documentation for Pagamentos")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }

    private List<ResponseMessage> responseMessageForGET() {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(200)
                    .message("OK")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(400)
                    .message("Bad Request")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(403)
                    .message("Token Inválido")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(404)
                    .message("Not Found!")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Internal Server Error")
                    .build());
        }};
    }

    private List<ResponseMessage> responseMessageForPOST() {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(201)
                    .message("Created")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(400)
                    .message("Bad Request")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(403)
                    .message("Token Inválido!")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Internal Server Error")
                    .build());
        }};
    }

    private List<ResponseMessage> responseMessageForPUT() {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(200)
                    .message("OK")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(400)
                    .message("Bad Request")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(403)
                    .message("Token Inválido!")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(404)
                    .message("Not Found")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Internal Server Error")
                    .build());
        }};
    }

    private List<ResponseMessage> responseMessageForDELETE() {
        return new ArrayList<ResponseMessage>() {{
            add(new ResponseMessageBuilder()
                    .code(204)
                    .message("No Content")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(403)
                    .message("Token Inválido!")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(404)
                    .message("Not Found")
                    .build());
            add(new ResponseMessageBuilder()
                    .code(500)
                    .message("Internal Server Error")
                    .build());
        }};
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(PathSelectors.any())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
    }

}
