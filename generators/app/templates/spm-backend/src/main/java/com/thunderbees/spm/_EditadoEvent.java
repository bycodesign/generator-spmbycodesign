package com.thunderbees.spm.<%=pacote%>;

import arquitetura.impl.AbstractEventGeneric;

public class <%=classe%>EditadoEvent extends AbstractEventGeneric<Editar<%=classe%>Command> {

    private static final long serialVersionUID = 1L;

    private <%=classe%>Id entityId;
    private int entityVersion;
<%for(fieldId in fields){%>
    private <%=fields[fieldId].fieldType%> <%=fields[fieldId].fieldName%>;<%}%>

    public <%=classe%>EditadoEvent(Editar<%=classe%>Command sourceMessage) {
        super(sourceMessage);
        this.entityId = sourceMessage.getEntityId();
        this.entityVersion = sourceMessage.getEntityVersion();<%for (fieldId in fields) {%>
        this.<%=fields[fieldId].fieldName%> = sourceMessage.get<%=fields[fieldId].capitalizedFieldName%>();<%}%>
    }

    public <%=classe%>Id getEntityId(){
        return entityId;
    }

    public int getEntityVersion(){
        return entityVersion;
    }
<%for(fieldId in fields){%>
    public <%=fields[fieldId].fieldType%> get<%=fields[fieldId].capitalizedFieldName%>() {
        return this.<%=fields[fieldId].fieldName%>;
    }
<%}%>

}
