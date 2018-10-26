package br.com.vr.sorteioapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import br.com.vr.sorteioapp.SorteioAppApplication;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import({ springfox.documentation.spring.data.rest.configuration.SpringDataRestConfiguration.class,
		springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration.class })
@ComponentScan(basePackageClasses = { SorteioAppApplication.class })
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.tags(new Tag("API - Sorteio", "Controlador com endpoints de sorteio"))
				.groupName("public-api")
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.vr.sorteioapp"))
				.paths(PathSelectors.regex("/api/.*"))
				.build()
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Sorteio API")
				.description("Sorteio API reference for developers")
				.version("1.0").build();
	}

}