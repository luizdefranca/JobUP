namespace JOB.DATA.Migrations
{
    using System.Data.Entity.Migrations;
    using System.Linq;

    internal sealed class Configuration : DbMigrationsConfiguration<JOB.DATA.Contexto>
    {
        public Configuration()
        {
            AutomaticMigrationsEnabled = false;
        }

        protected override void Seed(JOB.DATA.Contexto context)
        {
            if (context.Usuario.Any()) return;

            BasicoSeed.Seed(context);
        }
    }
}