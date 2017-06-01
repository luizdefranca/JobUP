namespace JOB.DATA.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class chat : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "jobup.CHAT",
                c => new
                    {
                        ID_SERVICO = c.Guid(nullable: false),
                        ID_USUARIO = c.Guid(nullable: false),
                        DT_MENSAGEM = c.DateTime(nullable: false),
                        MENSAGEM = c.String(nullable: false, maxLength: 254, unicode: false),
                        LIDA = c.Boolean(nullable: false),
                    })
                .PrimaryKey(t => new { t.ID_SERVICO, t.ID_USUARIO });
            
        }
        
        public override void Down()
        {
            DropTable("jobup.CHAT");
        }
    }
}
