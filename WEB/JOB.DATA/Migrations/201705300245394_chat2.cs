namespace JOB.DATA.Migrations
{
    using System.Data.Entity.Migrations;

    public partial class chat2 : DbMigration
    {
        public override void Up()
        {
            DropPrimaryKey("jobup.CHAT");
            AddPrimaryKey("jobup.CHAT", new[] { "ID_SERVICO", "DT_MENSAGEM" });
        }

        public override void Down()
        {
            DropPrimaryKey("jobup.CHAT");
            AddPrimaryKey("jobup.CHAT", new[] { "ID_SERVICO", "ID_USUARIO" });
        }
    }
}