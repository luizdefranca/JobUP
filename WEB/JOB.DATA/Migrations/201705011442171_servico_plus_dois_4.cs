namespace JOB.DATA.Migrations
{
    using System.Data.Entity.Migrations;

    public partial class servico_plus_dois_4 : DbMigration
    {
        public override void Up()
        {
            AlterColumn("jobup.SERVICO", "ID_SUB_ESPECIALIDADE", c => c.Int());
        }

        public override void Down()
        {
            AlterColumn("jobup.SERVICO", "ID_SUB_ESPECIALIDADE", c => c.Int(nullable: false));
        }
    }
}