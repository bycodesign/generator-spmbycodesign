package com.thunderbees.spm.<%=pacote%>;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import arquitetura.api.AbstractCommandHandler;
import arquitetura.api.annotations.CommandHandler;
import arquitetura.api.annotations.MessageHandler;
import arquitetura.impl.AbstractCommand;

@Component
@MessageHandler
@Transactional
public class<%=classe%>CommandHandler extends AbstractCommandHandler {

    private static final Logger logger = LoggerFactory.getLogger(<%=classe%>CommandHandler.class);

    private <%=classe%>Repository repository;

    @Autowired
    public void setRepository(<%=classe%>Repository repository) {
        this.repository=repository;
    }

    public <%=classe%>Repository getRepository() {
        return repository;
    }

    @CommandHandler
    public void handle(Criar<%=classe%>Command command) {
        log(command);

        <%=classe%> <%=variavel%> = new <%=classe%>(command);
        getRepository().save(<%=variavel%>);

        addEventsToDispatchFromEntity(<%=variavel%>);
    }

    @CommandHandler
    public void handle(Editar<%=classe%>Command command) {
        log(command);

        <%=classe%> <%=variavel%> = getRepository().findOne(command.getEntityId());
        <%=variavel%>.handle(command);
        getRepository().save(<%=variavel%>);

        addEventsToDispatchFromEntity(<%=variavel%>);
    }

    private void log(AbstractCommand command){
        logger.debug("Processing {} in {}.", command, this);
    }
}
