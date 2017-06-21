namespace JOB.DATA.Migrations
{
    using System.Data.Entity.Migrations;

    public partial class valorduracao : DbMigration
    {
        public override void Up()
        {
            AddColumn("jobup.PROPOSTA_SERVICO", "VALOR_DURACAO_SERVICO", c => c.Int(nullable: false));
        }

        public override void Down()
        {
            DropColumn("jobup.PROPOSTA_SERVICO", "VALOR_DURACAO_SERVICO");
        }
    }
}