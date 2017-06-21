namespace JOB.DATA.Migrations
{
    using System.Data.Entity.Migrations;

    public partial class aceita_null : DbMigration
    {
        public override void Up()
        {
            AlterColumn("jobup.OFERTA_SERVICO", "ACEITA", c => c.Boolean());
        }

        public override void Down()
        {
            AlterColumn("jobup.OFERTA_SERVICO", "ACEITA", c => c.Boolean(nullable: false));
        }
    }
}