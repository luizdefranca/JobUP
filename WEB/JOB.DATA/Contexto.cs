using JOB.DATA.Config;
using JOB.DATA.Domain;
using System;
using System.Data.Entity;
using System.Data.Entity.ModelConfiguration.Conventions;
using System.Linq;
using System.Xml.Linq;

namespace JOB.DATA
{
    public class Contexto : DbContext
    {
        //"Server=DESKTOP-.\SQLEXPRESS;Database=mundoup;Trusted_Connection=true;Connection Timeout=30;"
        public Contexto()
            : base(GetConnectionString())
        {
            Configuration.LazyLoadingEnabled = false; 
            Configuration.ProxyCreationEnabled = false;
            Configuration.AutoDetectChangesEnabled = false;
        }

        private static string GetConnectionString()
        {
            if (Environment.GetEnvironmentVariable("CONNECTION_STRING") != null)
            {
                return Environment.GetEnvironmentVariable("CONNECTION_STRING");
            }
            else
            {
                var path = @"c:\settings_jobup.xml";

                var xdoc = XDocument.Load(path);
                var valor = xdoc.Elements().Elements().First(f => f.Name == "CONNECTION_STRING").Value;

                return valor;
            }
        }

        public DbSet<USUARIO> Usuario { get; set; }
        //public DbSet<ENDERECO> Endereco { get; set; }
        //public DbSet<CONTATO> Contato { get; set; }
        public DbSet<PERFIL_PROFISSIONAL> PerfilProfissional { get; set; }
        public DbSet<AVALIACAO> Avaliacao { get; set; }
        public DbSet<ESPECIALIDADE> Especialidade { get; set; }
        public DbSet<SUB_ESPECIALIDADE> SubEspecialidade { get; set; }
        public DbSet<FORMACAO> Formacao { get; set; }
        public DbSet<SERVICO> Servico { get; set; }
        public DbSet<OFERTA_SERVICO> Oferta { get; set; }
        public DbSet<PROPOSTA_SERVICO> Proposta { get; set; }

        protected override void OnModelCreating(DbModelBuilder modelBuilder)
        {
            modelBuilder.Conventions.Remove<PluralizingTableNameConvention>(); //plularização de objetos
            modelBuilder.Conventions.Remove<OneToManyCascadeDeleteConvention>(); //deleção em cascata de filho
            modelBuilder.Conventions.Remove<ManyToManyCascadeDeleteConvention>(); //deleção em cascata de relações n x n

            //o ideal é ter um esquema por projeto/sistema/área de atuação
            modelBuilder.HasDefaultSchema("jobup");

            //tipo padrão para string é varchar
            modelBuilder.Properties<string>()
                .Configure(p => p.IsUnicode(false));

            //tamanho padrão para string é de 254
            modelBuilder.Properties<string>()
                .Configure(p => p.HasMaxLength(254));

            //Configuring/Mapping Properties and Types with the Fluent API: https://msdn.microsoft.com/en-us/data/jj591617.aspx
            modelBuilder.Configurations.Add(new UsuarioConfig());
            //modelBuilder.Configurations.Add(new EnderecoConfig());
            //modelBuilder.Configurations.Add(new ContatoConfig());
            modelBuilder.Configurations.Add(new PerfilProfissionalConfig());
            modelBuilder.Configurations.Add(new AvaliacaoConfig());
            modelBuilder.Configurations.Add(new FormacaoConfig());

            modelBuilder.Configurations.Add(new EspecialidadeConfig());
            modelBuilder.Configurations.Add(new SubEspecialidadeConfig());
            modelBuilder.Configurations.Add(new ServicoConfig());
            modelBuilder.Configurations.Add(new OfertaConfig());
            modelBuilder.Configurations.Add(new PropostaConfig());
        }
    }
}