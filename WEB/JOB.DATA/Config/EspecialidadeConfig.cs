using JOB.DATA.Domain;
using System.Data.Entity.ModelConfiguration;

namespace JOB.DATA.Config
{
    public class EspecialidadeConfig : EntityTypeConfiguration<ESPECIALIDADE>
    {
        public EspecialidadeConfig()
        {
            HasKey(c => new { c.ID_ESPECIALIDADE });

            Property(p => p.DESCRICAO)
               .IsRequired();

            Property(p => p.EXIGIR_COMPROVACAO)
                .IsRequired();

            HasMany(n => n.PERFIS_PROFISSIONAIS)
                .WithRequired(n => n.ESPECIALIDADE)
                .HasForeignKey(n => n.ID_ESPECIALIDADE);

            HasMany(n => n.SUB_ESPECIALIDADES)
                .WithRequired(n => n.ESPECIALIDADE)
                .HasForeignKey(n => n.ID_ESPECIALIDADE);
        }
    }
}