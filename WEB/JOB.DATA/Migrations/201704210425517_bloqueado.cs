namespace JOB.DATA.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class bloqueado : DbMigration
    {
        public override void Up()
        {
            AddColumn("jobup.USUARIO", "BLOQUEADO", c => c.Boolean(nullable: false));
        }
        
        public override void Down()
        {
            DropColumn("jobup.USUARIO", "BLOQUEADO");
        }
    }
}
