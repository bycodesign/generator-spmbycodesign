package com.thunderbees.spm.<%=pacote%>;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import arquitetura.api.BasicAbstractCommand;

import arquitetura.geral.empresa.EmpresaId;

public class Editar<%=classe%>Command extends BasicAbstractCommand<<%=classe%>Id> {

    private static final long serialVersionUID = 1L;
<%for(fieldId in fields){%>
    private <%=fields[fieldId].fieldType%> <%=fields[fieldId].fieldName%>;<%}%>

    public Editar<%=classe%>Command() {
    }

    @JsonCreator
    public Editar<%=classe%>Command(
            @JsonProperty("entityId") <%=classe%>Id entityId,
            @JsonProperty("entityVersion") Integer entityVersion,<%for (fieldId in fields) {%>
            @JsonProperty("<%=fields[fieldId].fieldName%>") <%=fields[fieldId].fieldType%> <%=fields[fieldId].fieldName%>,<%}%>
            @JsonProperty("contextEmpresaId") EmpresaId contextEmpresaId) {
        this.entityId = entityId;
        this.entityVersion = entityVersion;<%for(fieldId in fields) {%>
        this.<%=fields[fieldId].fieldName%> = <%=fields[fieldId].fieldName%>;<%}%>
    }
<%for(fieldId in fields){%>
    public <%=fields[fieldId].fieldType%> get<%=fields[fieldId].capitalizedFieldName%>() {
        return this.<%=fields[fieldId].fieldName%>;
    }
<%}%>
}
