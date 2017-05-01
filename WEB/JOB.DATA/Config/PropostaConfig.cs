using JOB.DATA.Domain;
using System.Data.Entity.ModelConfiguration;

namespace JOB.DATA.Config
{
    public class PropostaConfig : EntityTypeConfiguration<PROPOSTA_SERVICO>
    {
        public PropostaConfig()
        {
            HasKey(c => new { c.ID_SERVICO, c.ID_USUARIO });

            Property(p => p.JUSTIFICATIVA)
                .HasMaxLength(1000)
                .IsOptional();
        }
    }
}