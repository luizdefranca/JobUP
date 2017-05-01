using JOB.DATA.Domain;
using System.Data.Entity.ModelConfiguration;

namespace JOB.DATA.Config
{
    public class ServicoConfig : EntityTypeConfiguration<SERVICO>
    {
        public ServicoConfig()
        {
            HasKey(c => new { c.ID_SERVICO });

            Property(p => p.DS_TITULO)
                .HasMaxLength(256)
               .IsRequired();

            Property(p => p.DS_OBSERVACOES)
                .HasMaxLength(1000)
                .IsOptional();

            HasMany(n => n.OFERTAS)
                .WithRequired(n => n.SERVICO)
                .HasForeignKey(n => n.ID_SERVICO)
                .WillCascadeOnDelete(true);

            HasMany(n => n.PROPOSTAS)
                .WithRequired(n => n.SERVICO)
                .HasForeignKey(n => n.ID_SERVICO)
                .WillCascadeOnDelete(true);
        }
    }
}