namespace JOB.DATA.Migrations
{
    using System.Data.Entity.Migrations;

    internal sealed class Configuration : DbMigrationsConfiguration<JOB.DATA.Contexto>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = false;
        }
    }
}