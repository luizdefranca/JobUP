namespace JOB.DATA.Migrations
{
    using System.Data.Entity.Migrations;

    public partial class servico_plus_dois_2 : DbMigration
    {
        public override void Up()
        {
            CreateIndex("jobup.SERVICO", new[] { "ID_USUARIO", "ID_ESPECIALIDADE" });
            AddForeignKey("jobup.SERVICO", new[] { "ID_USUARIO", "ID_ESPECIALIDADE" }, "jobup.PERFIL_PROFISSIONAL", new[] { "ID_USUARIO", "ID_ESPECIALIDADE" }, cascadeDelete: true);
        }

        public override void Down()
        {
            DropForeignKey("jobup.SERVICO", new[] { "ID_USUARIO", "ID_ESPECIALIDADE" }, "jobup.PERFIL_PROFISSIONAL");
            DropIndex("jobup.SERVICO", new[] { "ID_USUARIO", "ID_ESPECIALIDADE" });
        }
    }
}