namespace JOB.DATA.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class updates : DbMigration
    {
        public override void Up()
        {
            AddColumn("jobup.OFERTA_SERVICO", "JUSTIFICATIVA", c => c.String(maxLength: 254, unicode: false));
            AlterColumn("jobup.PROPOSTA_SERVICO", "ACEITA", c => c.Boolean());
        }
        
        public override void Down()
        {
            AlterColumn("jobup.PROPOSTA_SERVICO", "ACEITA", c => c.Boolean(nullable: false));
            DropColumn("jobup.OFERTA_SERVICO", "JUSTIFICATIVA");
        }
    }
}
