using JOB.DATA.Domain;
using System.Data.Entity.ModelConfiguration;

namespace JOB.DATA.Config
{
    public class AvaliacaoConfig : EntityTypeConfiguration<AVALIACAO>
    {
        public AvaliacaoConfig()
        {
            HasKey(c => new { c.ID_USUARIO, c.ID_ESPECIALIDADE, c.ID_CLIENTE });

            Property(p => p.NOTA)
               .IsRequired();

            Property(p => p.COMENTARIO)
                .IsOptional();
        }
    }
}