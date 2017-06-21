namespace JOB.DATA.Migrations
{
    using System.Data.Entity.Migrations;

    public partial class servico_plus_dois_1 : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "PERFIL_PROFISSIONAL_ID_USUARIO", "PERFIL_PROFISSIONAL_ID_ESPECIALIDADE" }, "jobup.PERFIL_PROFISSIONAL");
            DropForeignKey("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "SUB_ESPECIALIDADE_ID_ESPECIALIDADE", "SUB_ESPECIALIDADE_ID_SUB_ESPECIALIDADE" }, "jobup.SUB_ESPECIALIDADE");
            DropIndex("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "PERFIL_PROFISSIONAL_ID_USUARIO", "PERFIL_PROFISSIONAL_ID_ESPECIALIDADE" });
            DropIndex("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "SUB_ESPECIALIDADE_ID_ESPECIALIDADE", "SUB_ESPECIALIDADE_ID_SUB_ESPECIALIDADE" });
            DropTable("jobup.PERFIL_SUB_ESPECIALIDADES");
        }

        public override void Down()
        {
            CreateTable(
                "jobup.PERFIL_SUB_ESPECIALIDADES",
                c => new
                {
                    PERFIL_PROFISSIONAL_ID_USUARIO = c.Guid(nullable: false),
                    PERFIL_PROFISSIONAL_ID_ESPECIALIDADE = c.Int(nullable: false),
                    SUB_ESPECIALIDADE_ID_ESPECIALIDADE = c.Int(nullable: false),
                    SUB_ESPECIALIDADE_ID_SUB_ESPECIALIDADE = c.Int(nullable: false),
                })
                .PrimaryKey(t => new { t.PERFIL_PROFISSIONAL_ID_USUARIO, t.PERFIL_PROFISSIONAL_ID_ESPECIALIDADE, t.SUB_ESPECIALIDADE_ID_ESPECIALIDADE, t.SUB_ESPECIALIDADE_ID_SUB_ESPECIALIDADE });

            CreateIndex("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "SUB_ESPECIALIDADE_ID_ESPECIALIDADE", "SUB_ESPECIALIDADE_ID_SUB_ESPECIALIDADE" });
            CreateIndex("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "PERFIL_PROFISSIONAL_ID_USUARIO", "PERFIL_PROFISSIONAL_ID_ESPECIALIDADE" });
            AddForeignKey("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "SUB_ESPECIALIDADE_ID_ESPECIALIDADE", "SUB_ESPECIALIDADE_ID_SUB_ESPECIALIDADE" }, "jobup.SUB_ESPECIALIDADE", new[] { "ID_ESPECIALIDADE", "ID_SUB_ESPECIALIDADE" });
            AddForeignKey("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "PERFIL_PROFISSIONAL_ID_USUARIO", "PERFIL_PROFISSIONAL_ID_ESPECIALIDADE" }, "jobup.PERFIL_PROFISSIONAL", new[] { "ID_USUARIO", "ID_ESPECIALIDADE" });
        }
    }
}