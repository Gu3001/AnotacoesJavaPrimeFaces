///////////////////////////////////////// Facade

public List<PessoaFisica> listaAniversariantes(int mesAtual, int proximoMes, int day) {
        Query q = em.createQuery("FROM PessoaFisica As a WHERE ((extract (month from (a.nascimento))) = '"+mesAtual+"'"
                                + " and (extract (day from (a.nascimento))) >= '"+day+"')"
                                + " or (extract (month from (a.nascimento))) = '"+proximoMes+"'"
                                + " order by" 
                                + " extract(month from a.nascimento)," 
                                + " extract(day from a.nascimento)");
        return q.getResultList();
    }



/////////////////////////////////////// Login Controle

@EJB
    PessoaFisicaFacade pessoaFisicaFacade;
    List<PessoaFisica> aniversariantes = new ArrayList<>();
  
    public List<PessoaFisica> getAniversariantes() {
        Date hoje = new Date();
        int mesAtual = hoje.getMonth()+1;
        int proximoMes = hoje.getMonth()+2;
        Calendar cal = Calendar.getInstance();
    	cal.setTime(hoje);
    	int day = cal.get(Calendar.DAY_OF_MONTH);
        System.out.println("Mês Atual: "+mesAtual);
        System.out.println("Mês Seguinte: "+proximoMes);
        System.out.println("DAY::::::::::: "+day);
        return aniversariantes = pessoaFisicaFacade.listaAniversariantes(mesAtual, proximoMes, day);
    } 



///////////////////////////////////// INDEX

<h:form>
            <p:tabView>
                <p:tab title="Aniversariantes">
                    <p:dataTable value="#{loginControler.aniversariantes}" var="n" style="text-align: center" reflow="true" emptyMessage="Nenhum aniversariante">
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
        </h:form>  