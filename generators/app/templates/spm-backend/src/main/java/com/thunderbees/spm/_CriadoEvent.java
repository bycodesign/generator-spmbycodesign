package com.thunderbees.spm.<%=pacote%>;

import arquitetura.geral.empresa.EmpresaId;
import arquitetura.impl.AbstractEventGeneric;

public class <%=classe%>CriadoEvent extends AbstractEventGeneric<Criar<%=classe%>Command> {

    private static final long serialVersionUID = 1L;

    private <%=classe%>Id entityId;
<%for(fieldId in fields){%>
    private <%=fields[fieldId].fieldType%> <%=fields[fieldId].fieldName%>;<%}%>
    private EmpresaId contextEmpresaId;

    public <%=classe%>CriadoEvent(Criar<%=classe%>Command sourceMessage) {
        super(sourceMessage);
        entityId = sourceMessage.getEntityId();<%for(fieldId in fields) {%>
        this.<%=fields[fieldId].fieldName%> = <%=fields[fieldId].fieldName%>;<%}%>
        contextEmpresaId = sourceMessage.getContextEmpresaId();
    }

    public <%=classe%>Id getEntityId() {
        return entityId;
    }
<%for(fieldId in fields){%>
    public <%=fields[fieldId].fieldType%> get<%=fields[fieldId].capitalizedFieldName%>() {
        return this.<%=fields[fieldId].fieldName%>;
    }
<%}%>
    public EmpresaId getContextEmpresaId() {
        return contextEmpresaId;
    }

}
