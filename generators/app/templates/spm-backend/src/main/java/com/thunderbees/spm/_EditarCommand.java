package com.thunderbees.spm.<%=pacote%>;

  import com.fasterxml.jackson.annotation.JsonCreator;
  import com.fasterxml.jackson.annotation.JsonProperty;
  import com.thunderbees.spm.BasicAbstractCommand;

  import arquitetura.geral.empresa.EmpresaId;

public class Editar<%=classe%>Command extends BasicAbstractCommand<<%=classe%>Id>{

private static final long serialVersionUID=1L;

public Editar<%=classe%>Command(){
  }

@JsonCreator
public Editar<%=classe%>Command(
@JsonProperty("entityId")<%=classe%>Id entityId,
@JsonProperty("entityVersion")Integer entityVersion,
@JsonProperty("contextEmpresaId")EmpresaId contextEmpresaId){
  this.entityId=entityId;
  this.entityVersion=entityVersion;
  }

  }
