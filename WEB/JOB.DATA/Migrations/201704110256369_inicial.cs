namespace JOB.DATA.Migrations
{
    using System.Data.Entity.Migrations;

    public partial class inicial : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "jobup.AVALIACAO",
                c => new
                {
                    ID_USUARIO = c.Guid(nullable: false),
                    ID_ESPECIALIDADE = c.Int(nullable: false),
                    ID_CLIENTE = c.Guid(nullable: false),
                    DT_ULT_AVALIACAO = c.DateTime(nullable: false),
                    NOTA = c.Short(nullable: false),
                    COMENTARIO = c.String(maxLength: 254, unicode: false),
                })
                .PrimaryKey(t => new { t.ID_USUARIO, t.ID_ESPECIALIDADE, t.ID_CLIENTE })
                .ForeignKey("jobup.PERFIL_PROFISSIONAL", t => new { t.ID_USUARIO, t.ID_ESPECIALIDADE }, cascadeDelete: true)
                .Index(t => new { t.ID_USUARIO, t.ID_ESPECIALIDADE });

            CreateTable(
                "jobup.PERFIL_PROFISSIONAL",
                c => new
                {
                    ID_USUARIO = c.Guid(nullable: false),
                    ID_ESPECIALIDADE = c.Int(nullable: false),
                    DT_APROVACAO = c.DateTime(),
                    APROVADO = c.Boolean(nullable: false),
                    RESUMO_CURRICULO = c.String(nullable: false, maxLength: 254, unicode: false),
                })
                .PrimaryKey(t => new { t.ID_USUARIO, t.ID_ESPECIALIDADE })
                .ForeignKey("jobup.ESPECIALIDADE", t => t.ID_ESPECIALIDADE)
                .ForeignKey("jobup.USUARIO", t => t.ID_USUARIO, cascadeDelete: true)
                .Index(t => t.ID_USUARIO)
                .Index(t => t.ID_ESPECIALIDADE);

            CreateTable(
                "jobup.ESPECIALIDADE",
                c => new
                {
                    ID_ESPECIALIDADE = c.Int(nullable: false, identity: true),
                    DESCRICAO = c.String(nullable: false, maxLength: 254, unicode: false),
                    EXIGIR_COMPROVACAO = c.Boolean(nullable: false),
                })
                .PrimaryKey(t => t.ID_ESPECIALIDADE);

            CreateTable(
                "jobup.FORMACAO",
                c => new
                {
                    ID_USUARIO = c.Guid(nullable: false),
                    ID_ESPECIALIDADE = c.Int(nullable: false),
                    ID_FORMACAO = c.Int(nullable: false),
                    INSTITUICAO = c.String(nullable: false, maxLength: 254, unicode: false),
                    NOME_CURSO = c.String(nullable: false, maxLength: 254, unicode: false),
                    ANO_FORMACAO = c.Short(nullable: false),
                    DT_APROVACAO = c.DateTime(),
                    APROVADO = c.Boolean(nullable: false),
                })
                .PrimaryKey(t => new { t.ID_USUARIO, t.ID_ESPECIALIDADE, t.ID_FORMACAO })
                .ForeignKey("jobup.PERFIL_PROFISSIONAL", t => new { t.ID_USUARIO, t.ID_ESPECIALIDADE }, cascadeDelete: true)
                .Index(t => new { t.ID_USUARIO, t.ID_ESPECIALIDADE });

            CreateTable(
                "jobup.USUARIO",
                c => new
                {
                    ID_USUARIO = c.Guid(nullable: false, identity: true),
                    NOME = c.String(nullable: false, maxLength: 100, unicode: false),
                    CPF = c.String(nullable: false, maxLength: 11, unicode: false),
                    RG_UF = c.Int(nullable: false),
                    RG_NR = c.String(nullable: false, maxLength: 8, unicode: false),
                    DT_NASCIMENTO = c.DateTime(nullable: false),
                    DT_INCLUSAO = c.DateTime(nullable: false),
                    DT_ALTERACAO = c.DateTime(),
                    DT_APROVACAO = c.DateTime(),
                    DT_ATIVACAO = c.DateTime(),
                    DT_ORDENACAO = c.DateTime(nullable: false),
                    APROVADO = c.Boolean(nullable: false),
                    ATIVO = c.Boolean(nullable: false),
                })
                .PrimaryKey(t => t.ID_USUARIO)
                .Index(t => t.CPF, unique: true, name: "IX_Usuario_CPF");

            CreateTable(
                "jobup.CONTATO",
                c => new
                {
                    ID_USUARIO = c.Guid(nullable: false),
                    FIXO = c.String(maxLength: 14, unicode: false),
                    CELULAR = c.String(maxLength: 14, unicode: false),
                    EMAIL = c.String(nullable: false, maxLength: 254, unicode: false),
                })
                .PrimaryKey(t => t.ID_USUARIO)
                .ForeignKey("jobup.USUARIO", t => t.ID_USUARIO, cascadeDelete: true)
                .Index(t => t.ID_USUARIO);

            CreateTable(
                "jobup.ENDERECO",
                c => new
                {
                    ID_USUARIO = c.Guid(nullable: false),
                    UF = c.Int(nullable: false),
                    CEP = c.String(nullable: false, maxLength: 8, unicode: false),
                    LOGRADOURO = c.String(nullable: false, maxLength: 254, unicode: false),
                    COMPLEMENTO = c.String(maxLength: 254, unicode: false),
                    BAIRRO = c.String(nullable: false, maxLength: 254, unicode: false),
                    CIDADE = c.String(nullable: false, maxLength: 254, unicode: false),
                })
                .PrimaryKey(t => t.ID_USUARIO)
                .ForeignKey("jobup.USUARIO", t => t.ID_USUARIO, cascadeDelete: true)
                .Index(t => t.ID_USUARIO);
        }

        public override void Down()
        {
            DropForeignKey("jobup.PERFIL_PROFISSIONAL", "ID_USUARIO", "jobup.USUARIO");
            DropForeignKey("jobup.ENDERECO", "ID_USUARIO", "jobup.USUARIO");
            DropForeignKey("jobup.CONTATO", "ID_USUARIO", "jobup.USUARIO");
            DropForeignKey("jobup.FORMACAO", new[] { "ID_USUARIO", "ID_ESPECIALIDADE" }, "jobup.PERFIL_PROFISSIONAL");
            DropForeignKey("jobup.PERFIL_PROFISSIONAL", "ID_ESPECIALIDADE", "jobup.ESPECIALIDADE");
            DropForeignKey("jobup.AVALIACAO", new[] { "ID_USUARIO", "ID_ESPECIALIDADE" }, "jobup.PERFIL_PROFISSIONAL");
            DropIndex("jobup.ENDERECO", new[] { "ID_USUARIO" });
            DropIndex("jobup.CONTATO", new[] { "ID_USUARIO" });
            DropIndex("jobup.USUARIO", "IX_Usuario_CPF");
            DropIndex("jobup.FORMACAO", new[] { "ID_USUARIO", "ID_ESPECIALIDADE" });
            DropIndex("jobup.PERFIL_PROFISSIONAL", new[] { "ID_ESPECIALIDADE" });
            DropIndex("jobup.PERFIL_PROFISSIONAL", new[] { "ID_USUARIO" });
            DropIndex("jobup.AVALIACAO", new[] { "ID_USUARIO", "ID_ESPECIALIDADE" });
            DropTable("jobup.ENDERECO");
            DropTable("jobup.CONTATO");
            DropTable("jobup.USUARIO");
            DropTable("jobup.FORMACAO");
            DropTable("jobup.ESPECIALIDADE");
            DropTable("jobup.PERFIL_PROFISSIONAL");
            DropTable("jobup.AVALIACAO");
        }
    }
}