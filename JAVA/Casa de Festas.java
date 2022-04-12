/////////////////////////////////////////////////// Facade

public List<PessoaFisica> listaAniversariantesMesAtual(int mesAtual, int day, int limitDay) {
        Query q = em.createQuery("FROM PessoaFisica As a WHERE ((extract (month from (a.nascimento))) = '"+mesAtual+"'"
                                + " and ((extract (day from (a.nascimento))) >= '"+day+"'"
                                + " and (extract (day from (a.nascimento))) <= '"+limitDay+"'))"
                                + " order by" 
                                + " extract(month from a.nascimento)," 
                                + " extract(day from a.nascimento)");
        return q.getResultList();
    }
    
    public List<PessoaFisica> listaAniversariantes(int mesAtual, int proximoMes, int day, int limitDay) {
        Query q = em.createQuery("FROM PessoaFisica As a WHERE ((extract (month from (a.nascimento))) = '"+mesAtual+"'"
                                + " and (extract (day from (a.nascimento))) >= '"+day+"')"
                                + " or ((extract (month from (a.nascimento))) = '"+proximoMes+"'"
                                + " and (extract (day from (a.nascimento))) <= '"+limitDay+"')"
                                + " order by" 
                                + " extract(month from a.nascimento)," 
                                + " extract(day from a.nascimento)");
        return q.getResultList();
    } 
    
    public List<PessoaFisica> listaAniversarioDeCasamentoMesAtual(int mesAtual, int day, int limitDay) {
        Query q = em.createQuery("FROM Pessoa As a WHERE ((extract (month from (a.dataDeCasamento))) = '"+mesAtual+"'"
                                + " and ((extract (day from (a.dataDeCasamento))) >= '"+day+"'"
                                + " and (extract (day from (a.dataDeCasamento))) <= '"+limitDay+"'))"
                                + " order by" 
                                + " extract(month from a.dataDeCasamento)," 
                                + " extract(day from a.dataDeCasamento)");
        return q.getResultList();
    }
    
    public List<PessoaFisica> listaAniversarioDeCasamentoProximoMes(int mesAtual, int proximoMes, int day, int limitDay) {
        Query q = em.createQuery("FROM Pessoa As a WHERE ((extract (month from (a.dataDeCasamento))) = '"+mesAtual+"'"
                                + " and (extract (day from (a.dataDeCasamento))) >= '"+day+"')"
                                + " or ((extract (month from (a.dataDeCasamento))) = '"+proximoMes+"'"
                                + " and (extract (day from (a.dataDeCasamento))) <= '"+limitDay+"')"
                                + " order by" 
                                + " extract(month from a.dataDeCasamento)," 
                                + " extract(day from a.dataDeCasamento)");
        return q.getResultList();
    } 


////////////////////////////////// Login Controle

    @EJB
    PessoaFisicaFacade pessoaFisicaFacade;
    List<PessoaFisica> aniversariantes = new ArrayList<>();
    List<PessoaFisica> aniversariosCasamento = new ArrayList<>();

    public List<PessoaFisica> getAniversariantes() {
        Date hoje = new Date();
        int mesAtual = hoje.getMonth() + 1;
        int proximoMes = hoje.getMonth() + 2;

        Calendar cal = Calendar.getInstance();
        cal.setTime(hoje);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int limitDay = day + 30;
        
        if (limitDay <= 31) {
            return aniversariantes = pessoaFisicaFacade.listaAniversariantesMesAtual(mesAtual, day, limitDay); 
            
        } else {
            
            if(proximoMes == 13){
                proximoMes = 1;
            }
            
            limitDay = limitDay - 31;
            return aniversariantes = pessoaFisicaFacade.listaAniversariantes(mesAtual, proximoMes, day, limitDay);
        }
    }

    public List<PessoaFisica> getAniversariosCasamento() {
        Date hoje = new Date();
        int mesAtual = hoje.getMonth() + 1;
        int proximoMes = hoje.getMonth() + 2;

        Calendar cal = Calendar.getInstance();
        cal.setTime(hoje);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int limitDay = day + 30;

        if (limitDay <= 31) {
            return aniversariantes = pessoaFisicaFacade.listaAniversarioDeCasamentoMesAtual(mesAtual, day, limitDay);
       
        } else {
            
            if(proximoMes == 13){
                proximoMes = 1;
            }
            
            limitDay = limitDay - 31;
            return aniversariantes = pessoaFisicaFacade.listaAniversarioDeCasamentoProximoMes(mesAtual, proximoMes, day, limitDay);
        }
    }

///////////////////////////////////////// Index

    <h:form>
            <p:tabView>
                <p:tab title="Aniversariantes">
                    <p:dataTable value="#{loginControle.aniversariantes}" var="n" style="text-align: center" reflow="true" emptyMessage="Nenhum aniversariante">
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
                <p:tab title="Aniversário de Casamento">
                    <p:dataTable value="#{loginControle.aniversariosCasamento}" var="n" style="text-align: center" reflow="true" emptyMessage="Nenhum aniversariante">
                        <p:column headerText="Cliente">
                            <h:outputLabel value="#{n.nome}"/>
                        </p:column>
                        <p:column headerText="Contato" width="150">
                            <h:outputLabel value="#{n.celular}"/>
                        </p:column>
                        <p:column headerText="E-mail" width="250">
                            <h:outputLabel value="#{n.email}"/>
                        </p:column>
                        <p:column headerText="Data" width="150">
                            <h:outputText value="#{n.dataDeCasamento}">
                                <f:convertDateTime pattern="dd/MM"/>
                            </h:outputText>
                        </p:column>
                    </p:dataTable>
                </p:tab>
            </p:tabView>
        </h:form>