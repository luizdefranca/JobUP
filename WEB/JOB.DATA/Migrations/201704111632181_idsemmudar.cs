namespace JOB.DATA.Migrations
{
    using System.Data.Entity.Migrations;

    public partial class idsemmudar : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("jobup.CONTATO", "ID_USUARIO", "jobup.USUARIO");
            DropForeignKey("jobup.ENDERECO", "ID_USUARIO", "jobup.USUARIO");
            DropForeignKey("jobup.PERFIL_PROFISSIONAL", "ID_USUARIO", "jobup.USUARIO");
            DropPrimaryKey("jobup.USUARIO");
            AlterColumn("jobup.USUARIO", "ID_USUARIO", c => c.Guid(nullable: false));
            AddPrimaryKey("jobup.USUARIO", "ID_USUARIO");
            AddForeignKey("jobup.CONTATO", "ID_USUARIO", "jobup.USUARIO", "ID_USUARIO", cascadeDelete: true);
            AddForeignKey("jobup.ENDERECO", "ID_USUARIO", "jobup.USUARIO", "ID_USUARIO", cascadeDelete: true);
            AddForeignKey("jobup.PERFIL_PROFISSIONAL", "ID_USUARIO", "jobup.USUARIO", "ID_USUARIO", cascadeDelete: true);
        }

        public override void Down()
        {
            DropForeignKey("jobup.PERFIL_PROFISSIONAL", "ID_USUARIO", "jobup.USUARIO");
            DropForeignKey("jobup.ENDERECO", "ID_USUARIO", "jobup.USUARIO");
            DropForeignKey("jobup.CONTATO", "ID_USUARIO", "jobup.USUARIO");
            DropPrimaryKey("jobup.USUARIO");
            AlterColumn("jobup.USUARIO", "ID_USUARIO", c => c.Guid(nullable: false, identity: true));
            AddPrimaryKey("jobup.USUARIO", "ID_USUARIO");
            AddForeignKey("jobup.PERFIL_PROFISSIONAL", "ID_USUARIO", "jobup.USUARIO", "ID_USUARIO", cascadeDelete: true);
            AddForeignKey("jobup.ENDERECO", "ID_USUARIO", "jobup.USUARIO", "ID_USUARIO", cascadeDelete: true);
            AddForeignKey("jobup.CONTATO", "ID_USUARIO", "jobup.USUARIO", "ID_USUARIO", cascadeDelete: true);
        }
    }
}