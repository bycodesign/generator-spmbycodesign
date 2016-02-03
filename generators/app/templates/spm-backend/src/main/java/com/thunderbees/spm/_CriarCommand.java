package com.thunderbees.spm.<%=pacote%>;

  import com.fasterxml.jackson.annotation.JsonCreator;
  import com.fasterxml.jackson.annotation.JsonProperty;
  import com.thunderbees.spm.BasicAbstractCommand;

  import arquitetura.geral.empresa.EmpresaId;

public class Criar<%=classe%>Command extends BasicAbstractCommand<<%=classe%>Id>{

private static final long serialVersionUID=1L;

public Criar<%=classe%>Command(){
  entityId=new<%=classe%>Id();
  }

@JsonCreator
public Criar<%=classe%>Command(@JsonProperty("contextEmpresaId")EmpresaId contextEmpresaId){
  this(new<%=classe%>Id(),contextEmpresaId);
  }

public Criar<%=classe%>Command(<%=classe%>Id entityId,EmpresaId contextEmpresaId){
  this.entityId=entityId;
  this.contextEmpresaId=contextEmpresaId;
  }

  }
