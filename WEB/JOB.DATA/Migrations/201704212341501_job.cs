namespace JOB.DATA.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class job : DbMigration
    {
        public override void Up()
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
            
        }
        
        public override void Down()
        {
            DropTable("jobup.JOB");
        }
    }
}
