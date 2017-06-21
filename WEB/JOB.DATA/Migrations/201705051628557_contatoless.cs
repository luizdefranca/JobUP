namespace JOB.DATA.Migrations
{
    using System.Data.Entity.Migrations;

    public partial class contatoless : DbMigration
    {
        public override void Up()
        {
            DropForeignKey("jobup.CONTATO", "ID_USUARIO", "jobup.USUARIO");
            DropForeignKey("jobup.ENDERECO", "ID_USUARIO", "jobup.USUARIO");
            DropIndex("jobup.CONTATO", new[] { "ID_USUARIO" });
            DropIndex("jobup.ENDERECO", new[] { "ID_USUARIO" });
            AddColumn("jobup.USUARIO", "UF", c => c.Int(nullable: false));
            AddColumn("jobup.USUARIO", "CEP", c => c.String(nullable: false, maxLength: 8, unicode: false));
            AddColumn("jobup.USUARIO", "LOGRADOURO", c => c.String(nullable: false, maxLength: 254, unicode: false));
            AddColumn("jobup.USUARIO", "COMPLEMENTO", c => c.String(maxLength: 254, unicode: false));
            AddColumn("jobup.USUARIO", "BAIRRO", c => c.String(nullable: false, maxLength: 254, unicode: false));
            AddColumn("jobup.USUARIO", "CIDADE", c => c.String(nullable: false, maxLength: 254, unicode: false));
            AddColumn("jobup.USUARIO", "FIXO", c => c.String(maxLength: 14, unicode: false));
            AddColumn("jobup.USUARIO", "CELULAR", c => c.String(maxLength: 14, unicode: false));
            AddColumn("jobup.USUARIO", "EMAIL", c => c.String(nullable: false, maxLength: 254, unicode: false));
            DropTable("jobup.CONTATO");
            DropTable("jobup.ENDERECO");
        }

        public override void Down()
        {
            CreateTable(
                "jobup.ENDERECO",
                c => new
                {
                    ID_USUARIO = c.Guid(nullable: false),
                    UF = c.Int(nullable: false),
                    CEP = c.String(nullable: false, maxLength: 8, unicode: false),
                    LOGRADOURO = c.String(nullable: false, maxLength: 254, unicode: false),
                    COMPLEMENTO = c.String(maxLength: 254, unicode: false),
                    BAIRRO = c.String(nullable: false, maxLength: 254, unicode: false),
                    CIDADE = c.String(nullable: false, maxLength: 254, unicode: false),
                })
                .PrimaryKey(t => t.ID_USUARIO);

            CreateTable(
                "jobup.CONTATO",
                c => new
                {
                    ID_USUARIO = c.Guid(nullable: false),
                    FIXO = c.String(maxLength: 14, unicode: false),
                    CELULAR = c.String(maxLength: 14, unicode: false),
                    EMAIL = c.String(nullable: false, maxLength: 254, unicode: false),
                })
                .PrimaryKey(t => t.ID_USUARIO);

            DropColumn("jobup.USUARIO", "EMAIL");
            DropColumn("jobup.USUARIO", "CELULAR");
            DropColumn("jobup.USUARIO", "FIXO");
            DropColumn("jobup.USUARIO", "CIDADE");
            DropColumn("jobup.USUARIO", "BAIRRO");
            DropColumn("jobup.USUARIO", "COMPLEMENTO");
            DropColumn("jobup.USUARIO", "LOGRADOURO");
            DropColumn("jobup.USUARIO", "CEP");
            DropColumn("jobup.USUARIO", "UF");
            CreateIndex("jobup.ENDERECO", "ID_USUARIO");
            CreateIndex("jobup.CONTATO", "ID_USUARIO");
            AddForeignKey("jobup.ENDERECO", "ID_USUARIO", "jobup.USUARIO", "ID_USUARIO", cascadeDelete: true);
            AddForeignKey("jobup.CONTATO", "ID_USUARIO", "jobup.USUARIO", "ID_USUARIO", cascadeDelete: true);
        }
    }
}