namespace JOB.DATA.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class noservico : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("jobup.SERVICO", new[] { "ID_USUARIO", "ID_ESPECIALIDADE" }, "jobup.PERFIL_PROFISSIONAL");
            DropIndex("jobup.SERVICO", new[] { "ID_USUARIO", "ID_ESPECIALIDADE" });
        }
        
        public override void Down()
        {
            CreateIndex("jobup.SERVICO", new[] { "ID_USUARIO", "ID_ESPECIALIDADE" });
            AddForeignKey("jobup.SERVICO", new[] { "ID_USUARIO", "ID_ESPECIALIDADE" }, "jobup.PERFIL_PROFISSIONAL", new[] { "ID_USUARIO", "ID_ESPECIALIDADE" }, cascadeDelete: true);
        }
    }
}
