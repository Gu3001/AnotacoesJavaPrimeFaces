public List<PessoaFisica> getAniversariantes() {
        Date hoje = new Date();
        int mesAtual = hoje.getMonth() + 1;
        int proximoMes = hoje.getMonth() + 2;

        Calendar cal = Calendar.getInstance();
        cal.setTime(hoje);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int limitDay = day + 11;

        //Se o dia limite for menor que 31, nem precisa tentar buscar datas do próximo mês
        if (limitDay <= 31) {
            return aniversariantes = pessoaFisicaFacade.listaAniversariantesMesAtual(mesAtual, day, limitDay);
        } 
        //Se o dia limite for maior que 31, encontrar a diferença de dias que foram para o mês seguinte e atribuir novo valor a variável.
        else {
            //Tratamento em caso do mês atual ser Dezembro, atribui-se o valor '1' referente a Janeiro do novo ano.
            if(proximoMes == 13){
                proximoMes = 1;
            }
            
            limitDay = limitDay - 31;
            return aniversariantes = pessoaFisicaFacade.listaAniversariantes(mesAtual, proximoMes, day, limitDay);
        }
    }