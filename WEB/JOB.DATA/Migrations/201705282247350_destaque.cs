namespace JOB.DATA.Migrations
{
    using System.Data.Entity.Migrations;

    public partial class destaque : DbMigration
    {
        public override void Up()
        {
            AddColumn("jobup.USUARIO", "PERFIL_DESTAQUE", c => c.Boolean(nullable: false));
        }

        public override void Down()
        {
            DropColumn("jobup.USUARIO", "PERFIL_DESTAQUE");
        }
    }
}