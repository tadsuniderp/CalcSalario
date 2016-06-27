public double CalculaSalario() {

        nome = String.format(editNome.getText().toString());
        salario = Double.parseDouble(editSalMensal.getText().toString());
        numDep = Integer.parseInt(editNumDp.getText().toString());

        if (salario <= 1399.12) { //Descobre a faixa de INSS
            fxInss = 8;
        } else if (salario >= 1399.13 && salario <= 2331.88) {
            fxInss = 9;
        } else {
            fxInss = 11;
        }

        //------------------------------------------------

        if (salario <= 1903.98) { //Descobre a faixa de IRRF
            fxIrrf = 0;
        } else if (salario >= 1903.99 && salario <= 2826.65) {
            fxIrrf = 7.5;
        } else if (salario >= 2826.66 && salario <= 3751.05) {
            fxIrrf = 15;
        } else if (salario >= 3751.06 && salario <= 4664.67){
            fxIrrf = 22.5;
        } else{
            fxIrrf = 27.5;
        }
		
		vlInss = (salario * fxInss) / 100; //Descobre o valor de INSS a ser descontado
		
		

        if (fxIrrf > 0){
            vlDep = numDep * 189.59; //Descobre o valor dos dependentes
            vlInss = (salario * fxInss) / 100; //Descobre o valor de INSS a ser descontado
            salBaseInss = salario - vlInss; //Valor liquido do salario
            salBaseIrrf = salBaseInss - vlDep; //Descobre a base de IRRF
            vlIrrf1 = (salBaseIrrf * fxIrrf) / 100; //Descobre o valor do IRRF sem dedução
        }

        if (fxIrrf == 7.5){
            vlIrrfT = vlIrrf1 - 142.8;
        }

        if (fxIrrf == 15.0){
            vlIrrfT = vlIrrf1 - 354.8;
        }

        if (fxIrrf == 22.5){
            vlIrrfT = vlIrrf1 - 636.13;
        }
        if (fxIrrf == 27.5){
            vlIrrfT = vlIrrf1 - 869.36;
        }

        vlTotalDesc = vlIrrfT + vlInss;
        resultado = salario - vlTotalDesc;

        DecimalFormat formato = new DecimalFormat("#,###.00");

        textNome.setText(String.valueOf(nome));
        textDep.setText(String.valueOf(numDep));
        textSalario.setText(String.valueOf(formato.format(salario)));
        textINSS.setText(String.valueOf(formato.format(vlInss)));
        textIRRF.setText(String.valueOf(formato.format(vlIrrfT)));
        textResultado.setText(String.valueOf(formato.format(resultado)));
        textFxINSS.setText(String.valueOf(fxInss));
        textFxIRRF.setText(String.valueOf(fxIrrf));
        return resultado;
    }