namespace JOB.DATA.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class servico_plus_dois_3 : DbMigration
    {
        public override void Up()
        {
            CreateIndex("jobup.OFERTA_SERVICO", "ID_USUARIO");
            CreateIndex("jobup.PROPOSTA_SERVICO", "ID_USUARIO");
            AddForeignKey("jobup.OFERTA_SERVICO", "ID_USUARIO", "jobup.USUARIO", "ID_USUARIO");
            AddForeignKey("jobup.PROPOSTA_SERVICO", "ID_USUARIO", "jobup.USUARIO", "ID_USUARIO");
        }
        
        public override void Down()
        {
            DropForeignKey("jobup.PROPOSTA_SERVICO", "ID_USUARIO", "jobup.USUARIO");
            DropForeignKey("jobup.OFERTA_SERVICO", "ID_USUARIO", "jobup.USUARIO");
            DropIndex("jobup.PROPOSTA_SERVICO", new[] { "ID_USUARIO" });
            DropIndex("jobup.OFERTA_SERVICO", new[] { "ID_USUARIO" });
        }
    }
}
