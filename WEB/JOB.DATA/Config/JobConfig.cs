using JOB.DATA.Domain;
using System.Data.Entity.ModelConfiguration;

namespace JOB.DATA.Config
{
    public class JobConfig : EntityTypeConfiguration<Domain.JOB>
    {
        public JobConfig()
        {
            HasKey(c => new { c.ID_JOB });

            Property(p => p.TITULO)
                .HasMaxLength(100)
               .IsRequired();

            Property(p => p.OBSERVACOES)
                .HasMaxLength(1000)
                .IsRequired();
        }
    }
}