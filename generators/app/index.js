'use strict';
var yeoman = require('yeoman-generator');
var chalk = require('chalk');
var yosay = require('yosay');

module.exports = yeoman.generators.Base.extend({
  prompting: function () {
    var done = this.async();
    this.prompt({
      type: 'input',
      name: 'className',
      message: 'Nome da Classe',
      default: this.appname
    }, function (answers) {
      this.classe = answers.className;
      this.pacote = formatPackageName(this.classe);
      this.variavel = formatVariableName(this.classe);
      this.url = formatUrlName(this.classe);
      this.tabela = formatTableName(this.classe);
      this.log(answers);
      done()
    }.bind(this));
  },
  writing: {
    config: function () {
      this.template('spm-backend/src/main/java/com/thunderbees/spm/_CommandHandler.java', 'src/main/java/com/thunderbees/spm/' + this.pacote + '/' + this.classe + 'CommandHandler.java', this, {});
      this.template('spm-backend/src/main/java/com/thunderbees/spm/_Controller.java', 'src/main/java/com/thunderbees/spm/' + this.pacote + '/' + this.classe + 'Controller.java', this, {});
      this.template('spm-backend/src/main/java/com/thunderbees/spm/_CriadoEvent.java', 'src/main/java/com/thunderbees/spm/' + this.pacote + '/' + this.classe + 'CriadoEvent.java', this, {});
      this.template('spm-backend/src/main/java/com/thunderbees/spm/_CriarCommand.java', 'src/main/java/com/thunderbees/spm/' + this.pacote + '/Criar' + this.classe + 'Command.java', this, {});
      this.template('spm-backend/src/main/java/com/thunderbees/spm/_EditadoEvent.java', 'src/main/java/com/thunderbees/spm/' + this.pacote + '/' + this.classe + 'EditadoEvent.java', this, {});
      this.template('spm-backend/src/main/java/com/thunderbees/spm/_EditarCommand.java', 'src/main/java/com/thunderbees/spm/' + this.pacote + '/Editar' + this.classe + 'Command.java', this, {});
      this.template('spm-backend/src/main/java/com/thunderbees/spm/_Entity.java', 'src/main/java/com/thunderbees/spm/' + this.pacote + '/' + this.classe + '.java', this, {});
      this.template('spm-backend/src/main/java/com/thunderbees/spm/_FindByParameter.java', 'src/main/java/com/thunderbees/spm/' + this.pacote + '/' + this.classe + 'FindbyParameter.java', this, {});
      this.template('spm-backend/src/main/java/com/thunderbees/spm/_Id.java', 'src/main/java/com/thunderbees/spm/' + this.pacote + '/' + this.classe + 'Id.java', this, {});
      this.template('spm-backend/src/main/java/com/thunderbees/spm/_Repository.java', 'src/main/java/com/thunderbees/spm/' + this.pacote + '/' + this.classe + 'Repository.java', this, {});
    }
  }
});

function formatVariableName(name) {
  return name.charAt(0).toLowerCase() + name.slice(1);
}

function formatPackageName(name) {
  return name.toLowerCase();
}

function formatUrlName(name) {
  return name.toLowerCase();
}

function formatTableName(name) {
  return name.toUpperCase();
}
