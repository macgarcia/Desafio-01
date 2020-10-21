package com.macgarcia.resource;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.macgarcia.model.InfoDto;
import com.macgarcia.service.StartProcessQ1;
import com.macgarcia.service.StartProcessQ2;
import com.macgarcia.service.StartProcessQ3;
import com.macgarcia.service.StartProcessQ4;
import com.macgarcia.service.StratService;

@Path(value = "/api")
public class StratResource {

	StratService service;

	StartProcessQ1 processQ1;

	StartProcessQ2 processQ2;

	StartProcessQ3 processQ3;

	StartProcessQ4 processQ4;
	
	@Inject
	public StratResource(StratService service, StartProcessQ1 processQ1, StartProcessQ2 processQ2,
			StartProcessQ3 processQ3, StartProcessQ4 processQ4) {
		this.service = service;
		this.processQ1 = processQ1;
		this.processQ2 = processQ2;
		this.processQ3 = processQ3;
		this.processQ4 = processQ4;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response inserirDados(@RequestBody InfoDto dto) {
		boolean salvouAreas = service.salvarAreas(dto.getAreas());
		boolean salvouFunci = service.salvarFuncionarios(dto.getFuncionarios());
		return salvouAreas && salvouFunci ? Response.ok().build() : Response.status(Status.BAD_REQUEST).build();
	}

	@GET
	@Path(value = "/q1")
	@Produces(MediaType.TEXT_PLAIN)
	public Response questao1() {
		return Response.ok().entity(processQ1.questao1()).build();
	}

	@GET
	@Path(value = "/q2")
	@Produces(MediaType.TEXT_PLAIN)
	public Response questao2() {
		return Response.ok().entity(processQ2.questao2()).build();
	}

	@GET
	@Path(value = "/q3")
	@Produces(MediaType.TEXT_PLAIN)
	public Response questao3() {
		return Response.ok().entity(processQ3.questao3()).build();
	}

	@GET
	@Path(value = "/q4")
	@Produces(MediaType.TEXT_PLAIN)
	public Response questao4() {
		return Response.ok().entity(processQ4.questao4()).build();
	}
}
