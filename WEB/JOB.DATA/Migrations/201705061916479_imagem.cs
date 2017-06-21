namespace JOB.DATA.Migrations
{
    using System.Data.Entity.Migrations;

    public partial class imagem : DbMigration
    {
        public override void Up()
        {
            AddColumn("jobup.ESPECIALIDADE", "IMAGEM", c => c.String(maxLength: 254, unicode: false));
        }

        public override void Down()
        {
            DropColumn("jobup.ESPECIALIDADE", "IMAGEM");
        }
    }
}