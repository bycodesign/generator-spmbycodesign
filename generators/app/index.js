'use strict';
var generators = require('yeoman-generator');
var chalk = require('chalk');
var yosay = require('yosay');
var uuid = require('node-uuid');
var _s = require('underscore.string');

const RESERVED_WORDS_JAVA = ["ABSTRACT", "CONTINUE", "FOR", "NEW", "SWITCH", "ASSERT", "DEFAULT", "GOTO", "PACKAGE", "SYNCHRONIZED", "BOOLEAN", "DO", "IF", "PRIVATE", "THIS", "BREAK", "DOUBLE", "IMPLEMENTS", "PROTECTED", "THROW", "BYTE", "ELSE", "IMPORT", "PUBLIC", "THROWS", "CASE", "ENUM", "INSTANCEOF", "RETURN", "TRANSIENT", "CATCH", "EXTENDS", "INT", "SHORT", "TRY", "CHAR", "FINAL", "INTERFACE", "STATIC", "VOID", "CLASS", "FINALLY", "LONG", "STRICTFP", "VOLATILE", "CONST", "FLOAT", "NATIVE", "SUPER", "WHILE"];

const RESERVED_WORDS_POSTGRES = ["ALL", "ANALYSE", "ANALYZE", "AND", "ANY", "ARRAY", "AS", "ASC", "ASYMMETRIC", "AUTHORIZATION", "BINARY", "BOTH", "CASE", "CAST", "CHECK", "COLLATE", "COLLATION", "COLUMN", "CONCURRENTLY", "CONSTRAINT", "CREATE", "CROSS", "CURRENT_CATALOG", "CURRENT_DATE", "CURRENT_ROLE", "CURRENT_SCHEMA", "CURRENT_TIME", "CURRENT_TIMESTAMP", "CURRENT_USER", "DEFAULT", "DEFERRABLE", "DESC", "DISTINCT", "DO", "ELSE", "END", "EXCEPT", "FALSE", "FETCH", "FOR", "FOREIGN", "FROM", "FULL", "GRANT", "GROUP", "HAVING", "ILIKE", "IN", "INITIALLY", "INNER", "INTERSECT", "INTO", "IS", "ISNULL", "JOIN", "LATERAL", "LEADING", "LEFT", "LIKE", "LIMIT", "LOCALTIME", "LOCALTIMESTAMP", "NATURAL", "NOT", "NOTNULL", "NULL", "OFFSET", "ON", "ONLY", "OR", "ORDER", "OUTER", "OVERLAPS", "PLACING", "PRIMARY", "REFERENCES", "RETURNING", "RIGHT", "SELECT", "SESSION_USER", "SIMILAR", "SOME", "SYMMETRIC", "TABLE", "THEN", "TO", "TRAILING", "TRUE", "UNION", "UNIQUE", "USER", "USING", "VARIADIC", "VERBOSE", "WHEN", "WHERE", "WINDOW", "WITH"];

const SOURCE_DIRECTORY = 'spm-backend/src/main/java/com/thunderbees/spm/';

const SOURCE_RESOURCE_DIRECTORY = 'spm-backend/src/main/resources/changelog/';

const DESTINATION_DIRECTORY = 'src/main/java/com/thunderbees/spm/';

const TARGET_RESOURCE_DIRECTORY = 'src/main/resources/changelog/';

module.exports = generators.Base.extend({
    _askForField: function (cb) {
        this.fieldId++;
        var fieldNamesUnderscored = [];
        var prompts = [
            {
                type: 'confirm',
                name: 'fieldAdd',
                message: 'Adicionar campo à Entidade?',
                default: true
            },
            {
                when: function (response) {
                    return response.fieldAdd == true;
                },
                type: 'input',
                name: 'fieldName',
                validate: function (input) {
                    if (!(/^([a-zA-Z0-9_]*)$/.test(input))) {
                        return 'O nome do campo não pode conter Caracteres Especiais';
                    } else if (input == '') {
                        return 'O nome do campo não pode ser vazio';
                    } else if (input.charAt(0) == input.charAt(0).toUpperCase()) {
                        return 'O nome do campo deve começar com uma letra minúscula';
                    } else if (input == 'id' || input == 'empresaId' || input == 'version') {
                        return 'Já existe um campo com esse nome';
                    } else if (RESERVED_WORDS_JAVA.indexOf(input.toUpperCase()) != -1) {
                        return 'O nome do campo não pode conter palavras reservadas do Java';
                    } else if (RESERVED_WORDS_POSTGRES.indexOf(input.toUpperCase()) != -1) {
                        return 'O nome do campo não pode conter palavras reservadas do PostgreSQL';
                    }
                    return true;
                },
                message: 'Qual o nome do Campo?'
            },
            {
                when: function (response) {
                    return response.fieldAdd == true;
                },
                type: 'list',
                name: 'fieldType',
                message: 'Qual o tipo do campo?',
                choices: [
                    {
                        value: 'String',
                        name: 'String - Description'
                    },
                    {
                        value: 'Integer',
                        name: 'Integer'
                    },
                    {
                        value: 'Long',
                        name: 'Long'
                    },
                    {
                        value: 'Float',
                        name: 'Float - Percentage'
                    },
                    {
                        value: 'Double',
                        name: 'Double - Decimal'
                    },
                    {
                        value: 'BigDecimal',
                        name: 'BigDecimal - Monetary'
                    },
                    {
                        value: 'Date',
                        name: 'Date'
                    },
                    {
                        value: 'Timestamp',
                        name: 'Timestamp'
                    },
                    {
                        value: 'Boolean',
                        name: 'Boolean'
                    }
                ],
                default: 0
            }
        ];
        this.prompt(prompts, function (props) {
            if (props.fieldAdd) {
                var field = {
                    fieldId: this.fieldId,
                    fieldName: props.fieldName,
                    capitalizedFieldName: formatClassName(props.fieldName),
                    underscoredFieldName: formatUrlName(props.fieldName, "_"),
                    fieldType: props.fieldType,
                    liquibaseFieldType: getLiquibaseFieldType(props.fieldType)

                };
                fieldNamesUnderscored.push(_s.underscored(props.fieldName));
                this.fields.push(field);
                this._askForField(cb);
            } else {
                cb();
            }
        }.bind(this));
    },
    initializing: {
        inicializandoVariaveis: function () {
            this.fieldId = 0;
            this.fields = [];
        }
    },
    writing: {
        config: function () {
            var destination = DESTINATION_DIRECTORY + this.pacote + '/';
            this.template(SOURCE_DIRECTORY + '_CommandHandler.java', destination + this.classe + 'CommandHandler.java', this, {});
            this.template(SOURCE_DIRECTORY + '_Controller.java', destination + this.classe + 'Controller.java', this, {});
            this.template(SOURCE_DIRECTORY + '_CriadoEvent.java', destination + this.classe + 'CriadoEvent.java', this, {});
            this.template(SOURCE_DIRECTORY + '_CriarCommand.java', destination + 'Criar' + this.classe + 'Command.java', this, {});
            this.template(SOURCE_DIRECTORY + '_EditadoEvent.java', destination + this.classe + 'EditadoEvent.java', this, {});
            this.template(SOURCE_DIRECTORY + '_EditarCommand.java', destination + 'Editar' + this.classe + 'Command.java', this, {});
            this.template(SOURCE_DIRECTORY + '_Entity.java', destination + this.classe + '.java', this, {});
            this.template(SOURCE_DIRECTORY + '_FindByParameter.java', destination + this.classe + 'FindByParameter.java', this, {});
            this.template(SOURCE_DIRECTORY + '_Id.java', destination + this.classe + 'Id.java', this, {});
            this.template(SOURCE_DIRECTORY + '_Repository.java', destination + this.classe + 'Repository.java', this, {});
            this.template(SOURCE_RESOURCE_DIRECTORY + '_changelog.xml', TARGET_RESOURCE_DIRECTORY + this.classe + "_changelog.xml", this, {});
            this.template(SOURCE_RESOURCE_DIRECTORY + '_changelogMenu.xml', TARGET_RESOURCE_DIRECTORY + this.classe + "_changelogMenu.xml", this, {});
        }
    },
    prompting: {
        perguntaTabela: function () {
            var cb = this.async();
            this.prompt({
                type: 'input',
                name: 'className',
                message: 'Nome da Classe',
                validate: function (input) {
                    if (!(/^([a-zA-Z0-9_]*)$/.test(input))) {
                        return 'O Nome da Classe não pode conter Caracteres Especiais';
                    } else if (input == '') {
                        return 'O Nome da Classe não pode ser vazio';
                    } else if (input.charAt(0) == input.charAt(0).toLowerCase()) {
                        return 'O Nome da Classe deve começar com uma letra maiúscula';
                    }
                    return true;
                },
                default: this.appname
            }, function (answers) {
                this.classe = formatClassName(answers.className);
                this.pacote = formatPackageName(this.classe);
                this.variavel = formatVariableName(this.classe);
                this.url = formatUrlName(this.classe, "-");
                this.tabela = formatTableName(this.classe);
                this.itemDeAcessoID = uuid.v4();
                this.itemDeAcessoListID = uuid.v4();
                this.itemDeAcessoEditID = uuid.v4();
                this.itemDeAcessoCreateID = uuid.v4();
                this.log(answers);
                cb()
            }.bind(this));
        },
        perguntaCampos: function () {
            var cb = this.async();
            this._askForField(cb);
        }
    }
});

function formatClassName(name) {
    if (name) {
        return name.charAt(0).toUpperCase() + name.slice(1);
    }
}

function formatVariableName(name) {
    if (name) {
        return name.charAt(0).toLowerCase() + name.slice(1);
    }
}

function formatPackageName(name) {
    return name.toLowerCase();
}

function formatUrlName(name, replacement) {
    return name.charAt(0).toLowerCase() + name.slice(1).replace(/([A-Z])/g, replacement + "$1").toLowerCase();
}

function formatTableName(name) {
    return name.charAt(0).toLowerCase() + name.slice(1).replace(/([A-Z])/g, "_" + "$1").toLowerCase();
}

function getLiquibaseFieldType(fieldType) {
    switch (fieldType) {
        case 'String': return 'DESCRIPTION';
        case 'BigDecimal': return 'MONETARY';
        case 'Date': return 'DATE';
        case 'Timestamp': return 'TIMESTAMP';
        case 'Double': return 'DECIMAL';
        case 'Float': return 'PERCENTAGE';
    }
    return fieldType.toUpperCase();
}