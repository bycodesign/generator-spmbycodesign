package com.thunderbees.spm.<%=pacote%>;

import javax.persistence.Embeddable;

import arquitetura.api.EntityId;

@Embeddable
public class <%=classe%>Id extends EntityId {

    private static final long serialVersionUID = 1L;

    public <%=classe%>Id() {
        super();
    }

    public <%=classe%>Id(String value) {
        super(value);
    }

}