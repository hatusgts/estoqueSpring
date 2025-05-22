package com.ti.estoque.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum TipoMovimentacao {
    ENTRADA,
    SAIDA,
    TRANSFERENCIA;

        @JsonCreator
    public static TipoMovimentacao fromString(String value) {
        try {
            return TipoMovimentacao.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException("TipoMovimentacao inválido: " + value);
        }
    }
}
