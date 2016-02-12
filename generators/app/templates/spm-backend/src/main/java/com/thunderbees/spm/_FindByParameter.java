package com.thunderbees.spm.<%=pacote%>;

import org.springframework.stereotype.Component;

import arquitetura.impl.query.SQLQuery;

@Component
public class <%=classe%>FindByParameter extends SQLQuery {

    public <%=classe%>FindByParameter() {
        super("select tabela.id, <%for (fieldId in fields) {%>tabela.<%=fields[fieldId].underscoredFieldName%>, <%}%>tabela.version " +
                " from <%=tabela%> tabela " +
                " where ");
    }
}
