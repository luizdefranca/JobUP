namespace JOB.DATA.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class servico_plus : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("jobup.PERFIL_SUB_ESPECIALIDADES", "ID_SUB_ESPECIALIDADE", "jobup.SUB_ESPECIALIDADE");
            DropIndex("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "ID_SUB_ESPECIALIDADE" });
            RenameColumn(table: "jobup.PERFIL_SUB_ESPECIALIDADES", name: "ID_USUARIO", newName: "PERFIL_PROFISSIONAL_ID_USUARIO");
            RenameColumn(table: "jobup.PERFIL_SUB_ESPECIALIDADES", name: "ID_ESPECIALIDADE", newName: "PERFIL_PROFISSIONAL_ID_ESPECIALIDADE");
            RenameColumn(table: "jobup.PERFIL_SUB_ESPECIALIDADES", name: "ID_SUB_ESPECIALIDADE", newName: "SUB_ESPECIALIDADE_ID_ESPECIALIDADE");
            RenameIndex(table: "jobup.PERFIL_SUB_ESPECIALIDADES", name: "IX_ID_USUARIO_ID_ESPECIALIDADE", newName: "IX_PERFIL_PROFISSIONAL_ID_USUARIO_PERFIL_PROFISSIONAL_ID_ESPECIALIDADE");
            DropPrimaryKey("jobup.SUB_ESPECIALIDADE");
            DropPrimaryKey("jobup.PERFIL_SUB_ESPECIALIDADES");
            AddColumn("jobup.SERVICO", "TEMPO_SERVICO", c => c.Int(nullable: false));
            AddColumn("jobup.PROPOSTA_SERVICO", "DURACAO_SERVICO", c => c.Int(nullable: false));
            AddColumn("jobup.PERFIL_SUB_ESPECIALIDADES", "SUB_ESPECIALIDADE_ID_SUB_ESPECIALIDADE", c => c.Int(nullable: false));
            AlterColumn("jobup.SUB_ESPECIALIDADE", "ID_SUB_ESPECIALIDADE", c => c.Int(nullable: false));
            AlterColumn("jobup.PROPOSTA_SERVICO", "JUSTIFICATIVA", c => c.String(maxLength: 1000, unicode: false));
            AddPrimaryKey("jobup.SUB_ESPECIALIDADE", new[] { "ID_ESPECIALIDADE", "ID_SUB_ESPECIALIDADE" });
            AddPrimaryKey("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "PERFIL_PROFISSIONAL_ID_USUARIO", "PERFIL_PROFISSIONAL_ID_ESPECIALIDADE", "SUB_ESPECIALIDADE_ID_ESPECIALIDADE", "SUB_ESPECIALIDADE_ID_SUB_ESPECIALIDADE" });
            CreateIndex("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "SUB_ESPECIALIDADE_ID_ESPECIALIDADE", "SUB_ESPECIALIDADE_ID_SUB_ESPECIALIDADE" });
            AddForeignKey("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "SUB_ESPECIALIDADE_ID_ESPECIALIDADE", "SUB_ESPECIALIDADE_ID_SUB_ESPECIALIDADE" }, "jobup.SUB_ESPECIALIDADE", new[] { "ID_ESPECIALIDADE", "ID_SUB_ESPECIALIDADE" });
        }
        
        public override void Down()
        {
            DropForeignKey("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "SUB_ESPECIALIDADE_ID_ESPECIALIDADE", "SUB_ESPECIALIDADE_ID_SUB_ESPECIALIDADE" }, "jobup.SUB_ESPECIALIDADE");
            DropIndex("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "SUB_ESPECIALIDADE_ID_ESPECIALIDADE", "SUB_ESPECIALIDADE_ID_SUB_ESPECIALIDADE" });
            DropPrimaryKey("jobup.PERFIL_SUB_ESPECIALIDADES");
            DropPrimaryKey("jobup.SUB_ESPECIALIDADE");
            AlterColumn("jobup.PROPOSTA_SERVICO", "JUSTIFICATIVA", c => c.String(maxLength: 254, unicode: false));
            AlterColumn("jobup.SUB_ESPECIALIDADE", "ID_SUB_ESPECIALIDADE", c => c.Int(nullable: false, identity: true));
            DropColumn("jobup.PERFIL_SUB_ESPECIALIDADES", "SUB_ESPECIALIDADE_ID_SUB_ESPECIALIDADE");
            DropColumn("jobup.PROPOSTA_SERVICO", "DURACAO_SERVICO");
            DropColumn("jobup.SERVICO", "TEMPO_SERVICO");
            AddPrimaryKey("jobup.PERFIL_SUB_ESPECIALIDADES", new[] { "ID_USUARIO", "ID_ESPECIALIDADE", "ID_SUB_ESPECIALIDADE" });
            AddPrimaryKey("jobup.SUB_ESPECIALIDADE", "ID_SUB_ESPECIALIDADE");
            RenameIndex(table: "jobup.PERFIL_SUB_ESPECIALIDADES", name: "IX_PERFIL_PROFISSIONAL_ID_USUARIO_PERFIL_PROFISSIONAL_ID_ESPECIALIDADE", newName: "IX_ID_USUARIO_ID_ESPECIALIDADE");
            RenameColumn(table: "jobup.PERFIL_SUB_ESPECIALIDADES", name: "SUB_ESPECIALIDADE_ID_ESPECIALIDADE", newName: "ID_SUB_ESPECIALIDADE");
            RenameColumn(table: "jobup.PERFIL_SUB_ESPECIALIDADES", name: "PERFIL_PROFISSIONAL_ID_ESPECIALIDADE", newName: "ID_ESPECIALIDADE");
            RenameColumn(table: "jobup.PERFIL_SUB_ESPECIALIDADES", name: "PERFIL_PROFISSIONAL_ID_USUARIO", newName: "ID_USUARIO");
            CreateIndex("jobup.PERFIL_SUB_ESPECIALIDADES", "ID_SUB_ESPECIALIDADE");
            AddForeignKey("jobup.PERFIL_SUB_ESPECIALIDADES", "ID_SUB_ESPECIALIDADE", "jobup.SUB_ESPECIALIDADE", "ID_SUB_ESPECIALIDADE");
        }
    }
}
