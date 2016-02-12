package com.thunderbees.spm.<%=pacote%>;

  import com.fasterxml.jackson.annotation.JsonCreator;
  import com.fasterxml.jackson.annotation.JsonProperty;
  import com.thunderbees.spm.BasicAbstractCommand;
  import arquitetura.geral.empresa.EmpresaId;

public class Criar<%=classe%>Command extends BasicAbstractCommand<<%=classe%>Id> {

private static final long serialVersionUID=1L;
<%for(fieldId in fields){%>
    private <%=fields[fieldId].fieldType%> <%=fields[fieldId].fieldName%>;<%}%>

    public Criar<%=classe%>Command() {
        entityId = new <%=classe%>Id();
    }

    @JsonCreator
    public Criar<%=classe%>Command(<%for(fieldId in fields) {%>@JsonProperty("<%=fields[fieldId].fieldName%>") <%=fields[fieldId].fieldType%> <%=fields[fieldId].fieldName%>, <%}%>@JsonProperty("contextEmpresaId") EmpresaId contextEmpresaId) {
        this(new <%=classe%>Id(), contextEmpresaId<%for(fieldId in fields) {%>, <%=fields[fieldId].fieldName%><%}%>);
    }

    public Criar<%=classe%>Command(<%=classe%>Id entityId, <%for(fieldId in fields) {%><%=fields[fieldId].fieldType%> <%=fields[fieldId].fieldName%>, <%}%>EmpresaId contextEmpresaId) {
        this.entityId = entityId;
        this.contextEmpresaId = contextEmpresaId;<%for(fieldId in fields) {%>
        this.<%=fields[fieldId].fieldName%> = <%=fields[fieldId].fieldName%>;<%}%>
    }
<%for(fieldId in fields){%>
    public <%=fields[fieldId].fieldType%> get<%=fields[fieldId].capitalizedFieldName%>() {
        return this.<%=fields[fieldId].fieldName%>;
    }
<%}%>
}
