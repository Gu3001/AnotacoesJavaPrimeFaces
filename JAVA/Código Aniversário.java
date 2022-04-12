/////////////////////////PESSOA FACADE

public List<PessoaFisica> listaAniversarianteMesCorrente(int mes, int day) {
    Query q = em.createQuery("FROM PessoaFisica As a WHERE (extract (month from (a.nascimento))) = '"+mes+"'"
                                + " and (extract (day from (a.nascimento))) >= '"+day+"'"
                                + " order by" 
                                + " extract(month from a.nascimento)," 
                                + " extract(day from a.nascimento)");
    return q.getResultList();
}
    
public List<PessoaFisica> listaAniversarianteMesSeguinte(int mes, int day) {
    Query q = em.createQuery("FROM PessoaFisica As a WHERE (extract (month from (a.nascimento))) = '"+mes+"'"            
                                + " order by" 
                                + " extract(month from a.nascimento)," 
                                + " extract(day from a.nascimento)");
    return q.getResultList();
}



/////////////////////////CONTROLE LOGIN

@EJB
    PessoaFisicaFacade pessoaFisicaFacade;
    List<PessoaFisica> aniversariantesMesCorrente = new ArrayList<>();
    List<PessoaFisica> aniversariantesMesSeguinte = new ArrayList<>();
  
    public List<PessoaFisica> getAniversariantesMesCorrente() {
        Date hoje = new Date();
        int mes = hoje.getMonth()+1;
        Calendar cal = Calendar.getInstance();
        cal.setTime(hoje);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println("Mês Atual: "+mes);
        System.out.println("DAY::::::::::: "+day);
        return aniversariantesMesCorrente = pessoaFisicaFacade.listaAniversarianteMesCorrente(mes, day);
    }    

    public List<PessoaFisica> getAniversariantesMesSeguinte() {
        Date hoje = new Date();
        Calendar cal = Calendar.getInstance();
        int mes = hoje.getMonth()+2;
        cal.setTime(hoje);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println("Mês Seguinte: "+mes);
        System.out.println("DAY::::::::::: "+day);
        return aniversariantesMesSeguinte = pessoaFisicaFacade.listaAniversarianteMesSeguinte(mes, day);
    }
}


//////////////// INDEX.xhtml

<h:form>
            <p:panel header="Próximos Aniversariantes">
                <p:tabView>
                    <p:tab title="Mês Corrente">
                        <p:dataTable value="#{loginControler.aniversariantesMesCorrente}" var="n" style="text-align: center" reflow="true" emptyMessage="Nenhum aniversariante">
                            <p:column headerText="Cliente">
                                <h:outputLabel value="#{n.nome}"/>
                            </p:column>
                            <p:column headerText="Contato" width="150">
                                <h:outputLabel value="#{n.celular}"/>
                            </p:column>
                            <p:column headerText="E-mail" width="250">
                                <h:outputLabel value="#{n.email}"/>
                            </p:column>
                            <p:column headerText="Data de Aniversário" width="150">
                                <h:outputText value="#{n.nascimento}">
                                    <f:convertDateTime pattern="dd/MM"/>
                                </h:outputText>
                            </p:column>
                        </p:dataTable>
                    </p:tab>
                    <p:tab title="Mês Seguinte">
                        <p:dataTable value="#{loginControler.aniversariantesMesSeguinte}" var="n" style="text-align: center" reflow="true" emptyMessage="Nenhum aniversariante">
                            <p:column headerText="Cliente">
                                <h:outputLabel value="#{n.nome}"/>
                            </p:column>
                            <p:column headerText="Contato" width="150">
                                <h:outputLabel value="#{n.celular}"/>
                            </p:column>
                            <p:column headerText="E-mail" width="250">
                                <h:outputLabel value="#{n.email}"/>
                            </p:column>
                            <p:column headerText="Data de Aniversário" width="150">
                                <h:outputText value="#{n.nascimento}">
                                    <f:convertDateTime pattern="dd/MM"/>
                                </h:outputText>
                            </p:column>
                        </p:dataTable>
                    </p:tab>
                </p:tabView>
            </p:panel>
        </h:form>