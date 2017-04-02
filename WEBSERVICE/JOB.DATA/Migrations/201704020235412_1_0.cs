namespace JOB.DATA.Migrations
{
    using System;
    using System.Data.Entity.Migrations;
    
    public partial class _1_0 : DbMigration
    {
        public override void Up()
        {
            CreateTable(
                "jobup.USUARIO",
                c => new
                    {
                        ID_USUARIO = c.Int(nullable: false, identity: true),
                        NOME = c.String(nullable: false, maxLength: 100, unicode: false),
                        CPF = c.String(nullable: false, maxLength: 11, unicode: false),
                        RG_UF = c.Int(nullable: false),
                        RG_NR = c.String(nullable: false, maxLength: 8, unicode: false),
                        DT_NASCIMENTO = c.DateTime(nullable: false),
                        DT_INCLUSAO = c.DateTime(nullable: false),
                        DT_ALTERACAO = c.DateTime(),
                        DT_APROVACAO = c.DateTime(),
                        DT_ORDENACAO = c.DateTime(nullable: false),
                        APROVADO = c.Boolean(nullable: false),
                    })
                .PrimaryKey(t => t.ID_USUARIO)
                .Index(t => t.CPF, unique: true, name: "IX_Usuario_CPF");
            
        }
        
        public override void Down()
        {
            DropIndex("jobup.USUARIO", "IX_Usuario_CPF");
            DropTable("jobup.USUARIO");
        }
    }
}
