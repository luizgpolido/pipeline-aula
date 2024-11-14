package com.vemser.rest.storys;

public class ProductStory {

    public static final String EPIC_PRODUCT = "Products";

    public static final String USER_STORY_PRODUCT_POST = "Como usuário do sistema, desejo enviar produtos no sistema para cadastrar no banco de dados";
    public static final String USER_STORY_PRODUCT_GET = "Como usuário do sistema, desejo enviar uma requisição para visualizar os produtos cadastrados no banco de daods";

    public static final String CT_006 = "CT006: Validar listar todos os produtos com autenticação";
    public static final String CT_007 = "CT007: Tentar listar todos os produtos sem autenticação";
    public static final String CT_008 = "CT008: Validar criar produto com dados válidos";
    public static final String CT_009 = "CT009: Tenta criar produto com campos incorretos (Teste Parametrizado)";
    public static final String CT_010 = "CT010: Validar contrato do JSON em cadastrar produto";
    public static final String CT_011 = "CT011: Validar contrato do JSON em listar produtos sem autenticação";
    public static final String CT_012 = "CT012: Validar contrato do JSON em listar produtos com autenticação";
    public static final String CT_017 = "CT017: Tenta listar todos os produtos (AUTH) com token inválido";
    public static final String CT_018 = "CT018: Valida listar produto por ID com ID válido";
    public static final String CT_019 = "CT019: Tenta listar produto por ID com ID negativo";
    public static final String CT_020 = "CT020: Tenta listar produto por ID com ID com letras";
    public static final String CT_021 = "CT021: Valida contrato do JSON em listar produto por ID";


}
