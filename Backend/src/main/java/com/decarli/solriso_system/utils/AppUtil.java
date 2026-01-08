package com.decarli.solriso_system.utils;

import com.decarli.solriso_system.model.exceptions.InvalidCpfException;

public class AppUtil {

    public boolean validateCPF(String cpf) {
        cpf = cpf.replaceAll("\\D", "");

        if (cpf.length() != 11) {
            throw new InvalidCpfException("CPF deve conter 11 dígitos");
        }

        // Elimina CPFs com todos os dígitos iguais
        if (cpf.matches("(\\d)\\1{10}")) {
            return false;
        }

        int sum = 0;

        // Primeiro dígito verificador
        for (int i = 0; i < 9; i++) {
            int digit = cpf.charAt(i) - '0';
            sum += digit * (10 - i);
        }

        int firstCheck = (sum * 10) % 11;
        if (firstCheck == 10) firstCheck = 0;

        if (firstCheck != (cpf.charAt(9) - '0')) {
            return false;
        }

        // Segundo dígito verificador
        sum = 0;
        for (int i = 0; i < 10; i++) {
            int digit = cpf.charAt(i) - '0';
            sum += digit * (11 - i);
        }

        int secondCheck = (sum * 10) % 11;
        if (secondCheck == 10) secondCheck = 0;

        return secondCheck == (cpf.charAt(10) - '0');
    }

}
