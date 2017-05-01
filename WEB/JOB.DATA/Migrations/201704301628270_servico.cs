namespace JOB.DATA.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class servico : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "jobup.SUB_ESPECIALIDADE",
                c => new
                    {
                        ID_SUB_ESPECIALIDADE = c.Int(nullable: false, identity: true),
                        ID_ESPECIALIDADE = c.Int(nullable: false),
                        DESCRICAO = c.String(nullable: false, maxLength: 254, unicode: false),
                    })
                .PrimaryKey(t => t.ID_SUB_ESPECIALIDADE)
                .ForeignKey("jobup.ESPECIALIDADE", t => t.ID_ESPECIALIDADE)
                .Index(t => t.ID_ESPECIALIDADE);
            
            CreateTable(
                "jobup.OFERTA_SERVICO",
                c => new
                    {
                        ID_SERVICO = c.Guid(nullable: false),
                        ID_USUARIO = c.Guid(nullable: false),
                        DT_OFERTA = c.DateTime(nullable: false),
                        ACEITA = c.Boolean(nullable: false),
                    })
                .PrimaryKey(t => new { t.ID_SERVICO, t.ID_USUARIO })
                .ForeignKey("jobup.SERVICO", t => t.ID_SERVICO, cascadeDelete: true)
                .Index(t => t.ID_SERVICO);
            
            CreateTable(
                "jobup.SERVICO",
                c => new
                    {
                        ID_SERVICO = c.Guid(nullable: false),
                        ID_USUARIO = c.Guid(nullable: false),
                        ID_ESPECIALIDADE = c.Int(nullable: false),
                        ID_SUB_ESPECIALIDADE = c.Int(nullable: false),
                        DT_CADASTRO = c.DateTime(nullable: false),
                        PUBLICO = c.Boolean(nullable: false),
                        DS_TITULO = c.String(nullable: false, maxLength: 256, unicode: false),
                        DS_OBSERVACOES = c.String(maxLength: 1000, unicode: false),
                        VL_SUGERIDO = c.Double(),
                    })
                .PrimaryKey(t => t.ID_SERVICO);
            
            CreateTable(
                "jobup.PROPOSTA_SERVICO",
                c => new
                    {
                        ID_SERVICO = c.Guid(nullable: false),
                        ID_USUARIO = c.Guid(nullable: false),
                        DT_PROPOSTA = c.DateTime(nullable: false),
                        VL_PROPOSTA = c.Double(nullable: false),
                        JUSTIFICATIVA = c.String(maxLength: 254, unicode: false),
                        ACEITA = c.Boolean(nullable: false),
                    })
                .PrimaryKey(t => new { t.ID_SERVICO, t.ID_USUARIO })
                .ForeignKey("jobup.SERVICO", t => t.ID_SERVICO, cascadeDelete: true)
                .Index(t => t.ID_SERVICO);
            
            CreateTable(
                "jobup.PERFIL_SUB_ESPECIALIDADES",
                c => new
                    {
                        ID_USUARIO = c.Guid(nullable: false),
                        ID_ESPECIALIDADE = c.Int(nullable: false),
                        ID_SUB_ESPECIALIDADE = c.Int(nullable: false),
                    })
                .PrimaryKey(t => new { t.ID_USUARIO, t.ID_ESPECIALIDADE, t.ID_SUB_ESPECIALIDADE })
                .ForeignKey("jobup.PERFIL_PROFISSIONAL", t => new { t.ID_USUARIO, t.ID_ESPECIALIDADE })
                .ForeignKey("jobup.SUB_ESPECIALIDADE", t => t.ID_SUB_ESPECIALIDADE)
                .Index(t => new { t.ID_USUARIO, t.ID_ESPECIALIDADE })
                .Index(t => t.ID_SUB_ESPECIALIDADE);
            
            AlterColumn("jobup.PERFIL_PROFISSIONAL", "RESUMO_CURRICULO", c => c.String(nullable: false, maxLength: 1000, unicode: false));
            DropTable("jobup.JOB");
        }
        
        public override void Down()
        {
            CreateTable(
                "jobup.JOB",
                c => new
                    {
                        ID_JOB = c.Long(nullable: false, identity: true),
                        ID_USUARIO_CLIENTE = c.Guid(nullable: false),
                        ID_USUARIO_PROFISSIONAL = c.Guid(nullable: false),
                        ID_ESPECIALIDADE = c.Int(nullable: false),
                        DT_JOB = c.DateTime(nullable: false),
                        TITULO = c.String(nullable: false, maxLength: 100, unicode: false),
                        OBSERVACOES = c.String(nullable: false, maxLength: 1000, unicode: false),
                        VALOR_SUGERIDO = c.Double(),
                        JOB_ACEITO = c.Boolean(nullable: false),
                    })
                .PrimaryKey(t => t.ID_JOB);
            
            DropForeignKey("jobup.PROPOSTA_SERVICO", "ID_SERVICO", "jobup.SERVICO");
            DropForeignKey("jobup.OFERTA_SERVICO", "ID_SERVICO", "jobup.SERVICO");
            DropForeignKey("jobup.PERFIL_SUB_ESPECIALIDADES", "ID_SUB_ESPECIALIDADE", "jobup.SUB_ESPECIALIDADE");
            DropForeignKey("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "ID_USUARIO", "ID_ESPECIALIDADE" }, "jobup.PERFIL_PROFISSIONAL");
            DropForeignKey("jobup.SUB_ESPECIALIDADE", "ID_ESPECIALIDADE", "jobup.ESPECIALIDADE");
            DropIndex("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "ID_SUB_ESPECIALIDADE" });
            DropIndex("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "ID_USUARIO", "ID_ESPECIALIDADE" });
            DropIndex("jobup.PROPOSTA_SERVICO", new[] { "ID_SERVICO" });
            DropIndex("jobup.OFERTA_SERVICO", new[] { "ID_SERVICO" });
            DropIndex("jobup.SUB_ESPECIALIDADE", new[] { "ID_ESPECIALIDADE" });
            AlterColumn("jobup.PERFIL_PROFISSIONAL", "RESUMO_CURRICULO", c => c.String(nullable: false, maxLength: 254, unicode: false));
            DropTable("jobup.PERFIL_SUB_ESPECIALIDADES");
            DropTable("jobup.PROPOSTA_SERVICO");
            DropTable("jobup.SERVICO");
            DropTable("jobup.OFERTA_SERVICO");
            DropTable("jobup.SUB_ESPECIALIDADE");
        }
    }
}
