package com.thunderbees.spm.<%=pacote%>;

  import javax.persistence.AttributeOverride;
  import javax.persistence.AttributeOverrides;
  import javax.persistence.Column;
  import javax.persistence.Entity;

  import arquitetura.api.AbstractEntity;
  import arquitetura.geral.empresa.EmpresaId;

@Entity(name = "<%=tabela%>")
public class<%=classe%>extends AbstractEntity<<%=classe%>Id>{

private static final long serialVersionUID=1L;

@AttributeOverrides({@AttributeOverride(name = "value", column = @Column(name = "empresa_id"))})
private EmpresaId empresaId;

public<%=classe%>(){
  super();
  }

public<%=classe%>(Criar<%=classe%>Command command){
  this();
  apply(new<%=classe%>CriadoEvent(command));
  }

public void on(<%=classe%>CriadoEvent event){
  id=event.getEntityId();
  empresaId=event.getContextEmpresaId();
  }

public void handle(Editar<%=classe%>Command command){
  apply(new<%=classe%>EditadoEvent(command));
  }

public void on(<%=classe%>EditadoEvent event){
  }

public EmpresaId getEmpresaId(){
  return empresaId;
  }
  }
