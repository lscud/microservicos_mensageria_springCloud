package io.github.lscud.mscloudgateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaClient //para registrar no servidor eureka
@EnableDiscoveryClient
public class MscloudgatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscloudgatewayApplication.class, args);
	}

	@Bean
	public RouteLocator routes(RouteLocatorBuilder builder){ //esse cara ira fazer o roteamento das nossas rotas para o discovery server
		return builder
				.routes()
				//definir as rotas
				.route(r -> r.path("/clientes/**").uri("lb://msclientes")) //toda vez que eu fizer requisição para "/clientes/**" o roteador irá direcionar para "lb://msclientes"
				.route(r -> r.path("/cartoes/**").uri("lb://mscartoes"))
				.route(r -> r.path("/avaliacoes-credito/**").uri("lb://msavaliadorcredito"))


				//--------------
				.build();

	}
}
