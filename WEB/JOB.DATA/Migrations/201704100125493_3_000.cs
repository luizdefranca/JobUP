namespace JOB.DATA.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class _3_000 : DbMigration
    {
        public override void Up()
        {
            AlterColumn("jobup.CONTATO", "FIXO", c => c.String(maxLength: 14, unicode: false));
            AlterColumn("jobup.CONTATO", "CELULAR", c => c.String(maxLength: 14, unicode: false));
        }
        
        public override void Down()
        {
            AlterColumn("jobup.CONTATO", "CELULAR", c => c.String(nullable: false, maxLength: 14, unicode: false));
            AlterColumn("jobup.CONTATO", "FIXO", c => c.String(nullable: false, maxLength: 14, unicode: false));
        }
    }
}
