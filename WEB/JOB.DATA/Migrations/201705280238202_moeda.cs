namespace JOB.DATA.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class moeda : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "jobup.HISTORICO_MOEDA",
                c => new
                    {
                        ID_USUARIO = c.Guid(nullable: false),
                        DT_MOVIMENTACAO = c.DateTime(nullable: false),
                        VALOR_MOVIMENTADO = c.Short(nullable: false),
                        MOTIVO = c.String(maxLength: 254, unicode: false),
                    })
                .PrimaryKey(t => new { t.ID_USUARIO, t.DT_MOVIMENTACAO })
                .ForeignKey("jobup.USUARIO", t => t.ID_USUARIO)
                .Index(t => t.ID_USUARIO);
            
            AddColumn("jobup.USUARIO", "MOEDA", c => c.Short(nullable: false));
        }
        
        public override void Down()
        {
            DropForeignKey("jobup.HISTORICO_MOEDA", "ID_USUARIO", "jobup.USUARIO");
            DropIndex("jobup.HISTORICO_MOEDA", new[] { "ID_USUARIO" });
            DropColumn("jobup.USUARIO", "MOEDA");
            DropTable("jobup.HISTORICO_MOEDA");
        }
    }
}
