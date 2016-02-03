package com.thunderbees.spm.<%=pacote%>;

  import arquitetura.impl.AbstractEventGeneric;

public class<%=classe%>EditadoEvent extends AbstractEventGeneric<Editar<%=classe%>Command>{

private static final long serialVersionUID=1L;

private<%=classe%>Id entityId;
private int entityVersion;

public<%=classe%>EditadoEvent(Editar<%=classe%>Command sourceMessage){
  super(sourceMessage);
  this.entityId=sourceMessage.getEntityId();
  this.entityVersion=sourceMessage.getEntityVersion();
  }

public<%=classe%>Id getEntityId(){
  return entityId;
  }

public int getEntityVersion(){
  return entityVersion;
  }

  }
