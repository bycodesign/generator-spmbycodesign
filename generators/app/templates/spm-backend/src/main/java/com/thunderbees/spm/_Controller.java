package com.thunderbees.spm.<%=pacote%>;

  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.web.bind.annotation.RequestBody;
  import org.springframework.web.bind.annotation.RequestMapping;
  import org.springframework.web.bind.annotation.RequestMethod;
  import org.springframework.web.bind.annotation.RequestParam;
  import org.springframework.web.bind.annotation.ResponseBody;
  import org.springframework.web.bind.annotation.RestController;

  import arquitetura.api.CommandResponse;
  import arquitetura.app.controller.AbstractCommandController;

@RestController
@RequestMapping("/<%url%>")
public class<%=classe%>Controller extends AbstractCommandController{

@Autowired
private<%=classe%>Repository repository;

@RequestMapping(method = RequestMethod.POST)
@ResponseBody
public CommandResponse criar<%=classe%>(@RequestBody Criar<%=classe%>Command command){
  return dispatchAndBuildResponse(command);
  }

@RequestMapping(method = RequestMethod.PUT)
@ResponseBody
public CommandResponse editar<%=classe%>(@RequestBody Editar<%=classe%>Command command){
  return dispatchAndBuildResponse(command);
  }

@RequestMapping(method = RequestMethod.GET, params = {"entityId"})
@ResponseBody
public<%=classe%>recuperar<%=classe%>(@RequestParam String entityId){
  return repository.findOne(new<%=classe%>Id(entityId));
  }

  }
