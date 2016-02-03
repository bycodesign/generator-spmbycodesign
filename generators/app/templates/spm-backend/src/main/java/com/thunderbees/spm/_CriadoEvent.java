package com.thunderbees.spm.<%=pacote%>;

  import arquitetura.geral.empresa.EmpresaId;
  import arquitetura.impl.AbstractEventGeneric;

public class<%=classe%>CriadoEvent extends AbstractEventGeneric<Criar<%=classe%>Command>{

private static final long serialVersionUID=1L;

private<%=classe%>Id entityId;

private EmpresaId contextEmpresaId;

public<%=classe%>CriadoEvent(Criar<%=classe%>Command sourceMessage){
  super(sourceMessage);
  entityId=sourceMessage.getEntityId();
  contextEmpresaId=sourceMessage.getContextEmpresaId();
  }

public<%=classe%>Id getEntityId(){
  return entityId;
  }

public EmpresaId getContextEmpresaId(){
  return contextEmpresaId;
  }

  }
