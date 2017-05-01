namespace JOB.DATA.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class servico_plus_dois1 : DbMigration
    {
        public override void Up()
        {
            RenameTable(name: "jobup.PERFIL_PROFISSIONALSUB_ESPECIALIDADE", newName: "PERFIL_SUB_ESPECIALIDADES");
        }
        
        public override void Down()
        {
            RenameTable(name: "jobup.PERFIL_SUB_ESPECIALIDADES", newName: "PERFIL_PROFISSIONALSUB_ESPECIALIDADE");
        }
    }
}
