using JOB.DATA.Domain;
using System.Data.Entity.ModelConfiguration;

namespace JOB.DATA.Config
{
    public class SubEspecialidadeConfig : EntityTypeConfiguration<SUB_ESPECIALIDADE>
    {
        public SubEspecialidadeConfig()
        {
            HasKey(c => new { c.ID_ESPECIALIDADE, c.ID_SUB_ESPECIALIDADE });

            Property(p => p.DESCRICAO)
               .IsRequired();
        }
    }
}