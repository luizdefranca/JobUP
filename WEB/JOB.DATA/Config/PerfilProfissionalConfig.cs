using JOB.DATA.Domain;
using System.Data.Entity.ModelConfiguration;

namespace JOB.DATA.Config
{
    public class PerfilProfissionalConfig : EntityTypeConfiguration<PERFIL_PROFISSIONAL>
    {
        public PerfilProfissionalConfig()
        {
            HasKey(c => new { c.ID_USUARIO, c.ID_ESPECIALIDADE });

            Property(p => p.DT_APROVACAO)
               .IsOptional();

            Property(p => p.APROVADO)
                .IsRequired();

            Property(p => p.RESUMO_CURRICULO)
                .HasMaxLength(1000)
                .IsRequired();

            HasMany(n => n.AVALIACOES)
                .WithRequired(n => n.PERFIL_PROFISSIONAL)
                .HasForeignKey(n => new { n.ID_USUARIO, n.ID_ESPECIALIDADE })
                .WillCascadeOnDelete(true);

            HasMany(n => n.FORMACOES)
               .WithRequired(n => n.PERFIL_PROFISSIONAL)
               .HasForeignKey(n => new { n.ID_USUARIO, n.ID_ESPECIALIDADE })
               .WillCascadeOnDelete(true);
        }
    }
}